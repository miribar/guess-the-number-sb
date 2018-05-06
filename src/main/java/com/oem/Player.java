package com.oem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "high_scores")
public class Player {

    @Id
    @GeneratedValue
    private Long player_id;
    private String player_name;
    private int num_of_guesses;

    public Player() {
    }

    public Player(Long id, String name, int num_of_guesses) {
        super();
        this.player_id = id;
        this.player_name = name;
        this.num_of_guesses = num_of_guesses;
    }
}
