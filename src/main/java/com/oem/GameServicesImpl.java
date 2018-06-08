package com.oem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GameServicesImpl implements GameServices {

    @Autowired
    private PlayerServices playerServices;                          // because we need to use player services here

    private AtomicInteger atomicInteger = new AtomicInteger();      // generates a sequential gameId starting with '0'
    private HashMap<Integer, Game> gameDao = new HashMap<>();       // hashmap that will store all the games

    public Integer createGame() {
        Game newGame = new Game();
        StringBuilder secretNum = new StringBuilder();
        newGame.setSecretNum(generateSecretNum(secretNum));
        Integer gameId = setNewGameId();
        return addGame(gameId, newGame);
    }

    @Override
    public Integer addGame(Integer gameId, Game newGame) {
        gameDao.put(gameId, newGame);
        return gameId;
    }

    @Override
    public Guess checkTheGuess(String playerName, Integer gameId, String guess) {
        //create an object only for the guesses data and return it to the UI
        Guess currentGuess = new Guess();

        for (int i=0; i<guess.length(); i++) {
            // Check digits in place
            if (guess.charAt(i) == gameDao.get(gameId).getSecretNum().charAt(i)) {
                currentGuess.setNumDigitsInPlace();
            }
            else
            // Check digits not in place
            if (guess.indexOf(gameDao.get(gameId).getSecretNum().charAt(i)) != -1) {
                currentGuess.setNumDigitsNotInPlace();
            }
        }

        // In any case, increase number of guesses in current game hashmap entry
        gameDao.get(gameId).setNumOfGuesses();

        // In case all digits are in place, we have a winner
        if (currentGuess.getNumDigitsInPlace() == 4) {
            // If DB table is full - get worst player
            // And if worst than current player (or same as), delete it
            if (playerServices.countPlayers() == 10) {
                Player worstPlayer = playerServices.getWorstPlayer();
                if (worstPlayer.getNumOfGuesses() >= gameDao.get(gameId).getNumOfGuesses()) {
                    playerServices.deletePlayer(worstPlayer.getPlayer_id());
                }
            }
            //Add current winner to the DB
            Player winner = new Player(playerName, gameDao.get(gameId).getNumOfGuesses());
            long playerId = playerServices.createPlayer(winner);
            System.out.println("Player #" + playerId + " was added to the DB");
        }
        //For hashmap debug:
        gameDao.forEach((key, value) -> System.out.println(String.format("gameId: %s secret num: %s guesses: %s",
                        key, value.getSecretNum(), value.getNumOfGuesses())));

        return currentGuess;
    }

    //     Implementation methods  //

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
