package com.oem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServicesImpl implements GameServices {

    @Autowired
    private GameRepository dao;

    @Override
    public Player createEntry(String name, int num_of_guesses) {
        return dao.save(new Player(null, name, num_of_guesses));
    }

}
