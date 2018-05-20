package com.oem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServicesImpl implements PlayerServices {

    @Autowired
    private PlayerRepository playerDao;

    @Override
    public Long createPlayer(Player player) {
        return playerDao.save(player).getPlayer_id();
    }

    @Override
    public Iterable<Player> getTopTen() {
        return playerDao.findAll();
    }

    @Override
    public void deletePlayer(Long player_id) {
         playerDao.delete(player_id);
    }

}
