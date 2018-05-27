package com.oem;

import java.util.concurrent.atomic.AtomicInteger;

public interface GameServices {

    Integer createGame();

    Integer addGame(Integer gameId, Game game);

    Game checkTheGuess(Integer gameId, String number);

}
