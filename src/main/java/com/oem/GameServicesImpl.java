package com.oem;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GameServicesImpl implements GameServices {

    private AtomicInteger atomicInteger = new AtomicInteger();               // generates a sequential gameId starting with '0'
    private Integer gameId = 0;
    private StringBuilder secretNum = new StringBuilder("1234567890");       // initializes the StringBuilder var with 10 digits
    private HashMap<Integer, Game> gameDao = new HashMap<Integer, Game>();   // will store all games

    public Integer createGame() {
        // this method will generate the random secret number string out of 10 digits
        // and set it in a new Game instance

        Game newGame = new Game();
        gameId = setNewGameId();
        newGame.setSecretNum(secretNum);
        return addGame(gameId, newGame);

        //for hashmap debug:
        //gameDao.forEach((key, value) -> System.out.println(key + ":" + value.getSecretNum()));
    }

    @Override
    public Integer addGame(Integer gameId, Game newGame) {
        gameDao.put(gameId, newGame);
        return gameId;
    }

    @Override
    public Game checkTheGuess(Integer gameId, String number) {
        //create an object out of fetched DB data and return it to the UI
        Game currentGame = gameDao.get(gameId);
        currentGame.setSecretNum(null);
        return currentGame;
    }

    private Integer setNewGameId() {
        return this.atomicInteger.getAndIncrement();
    }
  
}

// TODO: Use GeneralResponse to wrap everything with exceptions handling