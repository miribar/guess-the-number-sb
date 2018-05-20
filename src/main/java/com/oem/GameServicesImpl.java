package com.oem;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GameServicesImpl implements GameServices {

    AtomicLong gameId = new AtomicLong();                           // generates a sequential gameId starting with '0'
    StringBuilder secretNum = new StringBuilder("1234567890");      // initializes the StringBuilder var with 10 digits
    private HashMap<String, Game> gameDao = new HashMap<String, Game>();    // will store all games

    public void createGame() {
        // this method will generate the random secret number string out of 10 digits
        // and set it in a new Game instance

        Game newGame = new Game();
        newGame.setGameId(gameId.longValue());
        newGame.setSecretNum(secretNum);

        addGame(gameId, newGame);
        //for hashmap debug:
        //gameDao.forEach((key, value) -> System.out.println(key + ":" + value));
    }

    @Override
    public void addGame(AtomicLong gameId, Game newGame) {
        gameDao.put(gameId.toString(), newGame);
        gameId.getAndIncrement();
    }

    @Override
    public String fetchGame(String gameId) {
        Game fetchedGame = gameDao.get(gameId);
        return fetchedGame.toString();
    }
}

// TODO: Use GeneralResponse to wrap everything with exceptions handling