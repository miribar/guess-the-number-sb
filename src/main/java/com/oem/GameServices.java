package com.oem;

import java.util.concurrent.atomic.AtomicLong;

public interface GameServices {

    void createGame();

    void addGame(AtomicLong gameId, Game game);

    Game fetchGame();
    
    void adminLogin( String adminUserName, String adminPassword);

}
