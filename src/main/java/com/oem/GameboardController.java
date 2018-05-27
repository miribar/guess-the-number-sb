package com.oem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

@RestController                             //For serving REST requests, all responses default format is JSON
@RequestMapping("/rest/api/")  		        //this creates the services main API endpoint
public class GameboardController {

    @Autowired                              //this injects the playerServices into the controller, wires them together
    private PlayerServices playerServices;

    @Autowired
    private GameServices gameServices;

    //     Player API endpoints   //

    @GetMapping("/getscores")
    public Iterable<Player> getTopTen() {
        return playerServices.getTopTen();
    }

    @PostMapping("/addplayer")
    public Long addPlayer(@RequestBody Player player) {  //@RequestBody is a JSON and we get the params from outside
        return playerServices.createPlayer(player);
    }

    @DeleteMapping("/deleteplayer/{id}")
    public void deletePlayer(@PathVariable("id") Long playerId) {   //@PathVariable uses the var 'id' from request
        playerServices.deletePlayer(playerId);
    }

    //     Game API endpoints   //

    @PostMapping("/addgame")
    public Integer addGame(Integer gameId, Game game) {
        return gameServices.addGame(gameId, game);
    }

    @PostMapping("/creategame")
    public Integer createGame() {
        return gameServices.createGame();
    }

    //@GetMapping("/check-the-guess")
    @GetMapping("/check-the-guess/{id}/{guess}")
    public Game checkTheGuess(@PathVariable("id") Integer gameId, @PathVariable("guess") String number) {
        return gameServices.checkTheGuess(gameId, number);
    }

}
