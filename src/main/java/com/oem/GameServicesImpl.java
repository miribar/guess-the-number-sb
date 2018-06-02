package com.oem;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GameServicesImpl implements GameServices {

    private AtomicInteger atomicInteger = new AtomicInteger();      // generates a sequential gameId starting with '0'
    private HashMap<Integer, Game> gameDao = new HashMap<>();       // hashmap that stores all the games

    public Integer createGame() {
        Game newGame = new Game();
        StringBuilder secretNum = new StringBuilder();
        newGame.setSecretNum(generateSecretNum(secretNum));
        Integer gameId = setNewGameId();
        //for hashmap debug:
        //System.out.println("hashmap has:");
        //gameDao.forEach((key, value) -> System.out.println(key + ":" + value.getSecretNum()));
        return addGame(gameId, newGame);
    }

    @Override
    public Integer addGame(Integer gameId, Game newGame) {
        gameDao.put(gameId, newGame);
        return gameId;
    }

    @Override
    public Guess checkTheGuess(Integer gameId, String guess) {
        //create an object only for the guesses data and return it to the UI
        Guess currentGame = new Guess();

        for (int i=0; i<guess.length(); i++) {
            // Check digits in place
            if (guess.charAt(i) == gameDao.get(gameId).getSecretNum().charAt(i)) {
                currentGame.setNumDigitsInPlace();
            }
            else
            // Check digits not in place
            if (guess.indexOf(gameDao.get(gameId).getSecretNum().charAt(i)) != -1) {
                currentGame.setNumDigitsNotInPlace();
            }
        }
        // In case all digits are in place, we have a winner
        if (currentGame.getNumDigitsInPlace() == 4) {
            gameDao.get(gameId).setGameWon(true);
        }

        // Increase number of guesses in current game
        gameDao.get(gameId).setNumOfGuesses();
        return currentGame;
    }

    private Integer setNewGameId() {
        return this.atomicInteger.getAndIncrement();
    }

    private String generateSecretNum(StringBuilder secretNum) {
        String NUMBERS = "1234567890";
        Random rnd = new Random();
        while (secretNum.length() < 4) {                                // length of the random string
            int index = (int)(rnd.nextFloat() * NUMBERS.length());
            char nextDigit = NUMBERS.charAt(index);                     // select char at random index in NUMBERS
            if (secretNum.indexOf(String.valueOf(nextDigit)) == -1)     // if digit not used yet
                secretNum.append(nextDigit);
        }
        return secretNum.toString();
    }
  
}
