package com.oem;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GameServicesImpl implements GameServices {

    AtomicInteger gameId = new AtomicInteger();                     // generates a sequential gameId starting with '0'
    StringBuilder secretNum = new StringBuilder("1234567890");      // initializes the StringBuilder var with 10 digits
    private HashMap<AtomicInteger, Game> gameDao = new HashMap<AtomicInteger, Game>();    // will store all games

    public AtomicInteger createGame() {
        // this method will generate the random secret number string out of 10 digits
        // and set it in a new Game instance

        Game newGame = new Game();
        gameId.getAndIncrement();
        newGame.setSecretNum(secretNum);
        return addGame(gameId, newGame);

        //for hashmap debug:
        //gameDao.forEach((key, value) -> System.out.println(key + ":" + value.getSecretNum()));
    }

    @Override
    public AtomicInteger addGame(AtomicInteger gameId, Game newGame) {
        gameDao.put(gameId, newGame);
        return gameId;
    }
}

// TODO: Use GeneralResponse to wrap everything with exceptions handling