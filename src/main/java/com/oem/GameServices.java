package com.oem;

public interface GameServices {

    Long createPlayer(Player player);

    Iterable<Player> getTopTen();

    void deletePlayer(Long player_id);
}
