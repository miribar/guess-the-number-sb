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
    public Iterable<Player> findAll() {
        return playerDao.findAll();
    }

    @Override
    public Player getWorstGuess() {
            return playerDao.findTop1ByOrderByGuessesDesc();
    }

    @Override
    public void deletePlayer(Long player_id) {
         playerDao.delete(player_id);
    }

}
