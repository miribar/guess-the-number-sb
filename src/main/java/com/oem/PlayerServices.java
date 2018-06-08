package com.oem;

public interface PlayerServices {
    long countPlayers();

    Long createPlayer(Player player);

    Iterable<Player> findAll();

    Player getWorstPlayer();

    void deletePlayer(Long player_id);

    void deleteAll();
}
