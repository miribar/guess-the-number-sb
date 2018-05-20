package com.oem;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GameServicesImpl implements GameServices {

    AtomicLong gameId = new AtomicLong();                           // generates a sequential gameId starting with '0'
    StringBuilder secretNum = new StringBuilder("1234567890");      // initializes the StringBuilder var with 10 digits
    private HashMap<AtomicLong, Game> gameDao = new HashMap<>();    // will store all games

    public void createGame() {
        // this method will generate the random secret number string out of 10 digits
        // and set it in a new Game instance
        Game game = new Game();
        game.setGameId(this.gameId);
        game.setSecretNum(this.secretNum);
        // For debug
        System.out.println("Game id: " + game.getGameId() + " secret num: " + game.getSecretNum() + " digits in place: "
                + game.getNumDigitsInPlace() + " digits not in place: " + game.getNumDigitsNotInPlace()
                + " num of moves: " + game.getNumOfMoves());
    }

    @Override
    public void addGame(AtomicLong gameId, Game newGame) {
        this.gameId.getAndIncrement();
        // For debug
        System.out.println("In addGame game id: " + this.gameId);
        this.gameDao.put(this.gameId, newGame);
    }

    @Override
    public Game fetchGame() {
        System.out.println("In fetchGame game list size is: " + this.gameDao.size());
        Game fetchedGame = this.gameDao.get(this.gameId);
        // For debug
        System.out.println("In fetchedGame gameId is: " + fetchedGame.getGameId());         // This returns null
        System.out.println("In fetchedGame secretNum is: " + fetchedGame.getSecretNum());   // This returns null
        return fetchedGame;
    }
}

// TODO: Use GeneralResponse to wrap everything with exceptions handling