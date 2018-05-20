package com.oem;

public interface PlayerServices {

    Long createPlayer(Player player);

    Iterable<Player> getTopTen();

    void deletePlayer(Long player_id);
}
