package com.oem;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GameServicesImpl implements GameServices {

    AtomicLong gameId = new AtomicLong();
    private HashMap<AtomicLong, Game> gameDao = new HashMap<AtomicLong, Game>();

    @Override
    public void createGame(AtomicLong gameId, Game game) {
        gameId.getAndIncrement();
        this.gameDao.put(gameId, game);
    }

    @Override
    public Game fetchGame(AtomicLong gameId) {
        return this.gameDao.get(gameId);
    }
}
