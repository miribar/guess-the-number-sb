package com.oem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "high_scores")
public class Player {

    @Id
    @GeneratedValue
    private Long player_id;
    private String player_name;
    private int guesses;

    public Player() {

    }

    public Player(String player_name, int guesses) {
        this.player_name = player_name;
        this.guesses = guesses;
    }

    Long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Long player_id) {
        this.player_id = player_id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public int getNumOfGuesses() {
        return guesses;
    }

    public void setNumOfGuesses(int guesses) {
        this.guesses = guesses;
    }

}
