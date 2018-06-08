package com.oem;

public interface GameServices {

    Integer createGame();

    Integer addGame(Integer gameId, Game game);

    Guess checkTheGuess(String playerName, Integer gameId, String guess);

}
