package com.oem;

public interface GameServices {

    Integer createGame();

    Integer addGame(Integer gameId, Game game);

    Guess checkTheGuess(Integer gameId, String guess);

}
