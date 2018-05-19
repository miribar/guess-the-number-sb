package com.oem;

import java.util.concurrent.atomic.AtomicLong;

public interface GameServices {

    void createGame(AtomicLong gameId, Game game);

    Game fetchGame(AtomicLong gameId);

}
