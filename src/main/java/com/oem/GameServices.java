package com.oem;

import java.util.concurrent.atomic.AtomicInteger;

public interface GameServices {

    AtomicInteger createGame();

    AtomicInteger addGame(AtomicInteger gameId, Game game);

}
