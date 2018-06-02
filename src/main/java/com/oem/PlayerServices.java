package com.oem;

public interface PlayerServices {

    Long createPlayer(Player player);

    Iterable<Player> findAll();

    Player getWorstGuess();

    void deletePlayer(Long player_id);

}
