package com.oem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServicesImpl implements PlayerServices {

    @Autowired
    private PlayerRepository playerDao;

    @Override
    public long countPlayers() {
        return playerDao.count();
    }

    @Override
    public Long createPlayer(Player player) {
        return playerDao.save(player).getPlayer_id();
    }

    @Override
    public Iterable<Player> findAll() {
        return playerDao.findAll();
    }

    @Override
    public Player getWorstPlayer() {
            return playerDao.findTop1ByOrderByGuessesDesc();
    }

    @Override
    public void deletePlayer(Long player_id) {
         playerDao.delete(player_id);
    }

    @Override
    public void deleteAll() {
        playerDao.deleteAll();
    }

}
