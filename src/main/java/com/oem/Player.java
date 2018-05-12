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
    private int num_of_guesses;

    public Long getPlayer_id() {
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

    public int getNum_of_guesses() {
        return num_of_guesses;
    }

    public void setNum_of_guesses(int num_of_guesses) {
        this.num_of_guesses = num_of_guesses;
    }

}
