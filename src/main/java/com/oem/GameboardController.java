package com.oem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController                             //For serving REST requests, all responses default format is JSON
@RequestMapping("/rest/api/")  		        //this creates the service main entry point
public class GameboardController {

    @Autowired                              //this injects the playerServices into the controller, wires them together
    private PlayerServices playerServices;

    @Autowired
    private GameServices gameServices;

    @GetMapping("/getscores")
    public Iterable<Player> getTopTen() {
        return playerServices.getTopTen();
    }

    @PostMapping("/add")
    public Long addPlayer(@RequestBody Player player) {  //@RequestBody is a JSON and we get the params from outside
        return playerServices.createPlayer(player);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePlayer(@PathVariable("id") Long playerId) {
        playerServices.deletePlayer(playerId);
    }

    @GetMapping("/getgame/{id}")
    public Game fetchGame(@PathVariable("id") AtomicLong gameId) {
        return gameServices.fetchGame(gameId);
    }

    @PostMapping("/creategame")
    public void createGame(@RequestBody AtomicLong gameId, Game game) {
        gameServices.createGame(gameId, game);
    }
}
