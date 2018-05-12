package com.oem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServicesImpl implements GameServices {

    @Autowired
    private GameRepository dao;

    @Override
    public Long createPlayer(Player player) {
        return dao.save(player).getPlayer_id();
    }

    @Override
    public Iterable<Player> getTopTen() {
        return dao.findAll();
    }

    @Override
    public void deletePlayer(Long player_id) {
        dao.delete(player_id);
    }

}
