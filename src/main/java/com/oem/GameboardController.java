package com.oem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController                                 //For serving REST requests, all responses default format is JSON
@RequestMapping("/rest/api/")  		            //this creates the services main API endpoint
public class GameboardController {

    @Autowired                                //this injects the playerServices into the controller, wires them together
    private PlayerServices playerServices;

    @Autowired
    private GameServices gameServices;

    //     Player API endpoints (against DB)  //

    @GetMapping("/getscores")
    public Iterable<Player> findAll() {
        return playerServices.findAll();
    }

    @PostMapping("/addplayer")
    public Long addPlayer(@RequestBody Player player) {     //@RequestBody is a JSON and we get the params from outside
        return playerServices.createPlayer(player);
    }

    @GetMapping("/getworstguess")
    public Player getWorstGuess() {
        return playerServices.getWorstGuess();
    }

    // Will use the Player returned from getWorstGuess() to delete that player from DB
    @DeleteMapping("/deleteplayer/{id}")
    public void deletePlayer(@PathVariable("id") Long playerId) {       //@PathVariable uses the var 'id' from request
        playerServices.deletePlayer(playerId);
    }

    //     Game API endpoints (against hashmap)  //

    @PostMapping("/addgame")
    public Integer addGame(Integer gameId, Game game) {
        return gameServices.addGame(gameId, game);
    }

    @PostMapping("/creategame")
    public Integer createGame() {
        return gameServices.createGame();
    }

    @GetMapping("/check-the-guess/{id}/{guess}")
    public Guess checkTheGuess(@PathVariable("id") Integer gameId, @PathVariable("guess") String guess) {
        return gameServices.checkTheGuess(gameId, guess);
    }

}
