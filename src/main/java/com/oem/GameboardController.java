package com.oem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController                                 //For serving REST requests, all responses default format is JSON
@RequestMapping("/rest/api/")  		            //this creates the services main API endpoint
public class GameboardController {

    @Autowired                                //this injects the PlayerServices into the controller, wires them together
    private PlayerServices playerServices;

    @Autowired
    private GameServices gameServices;        //this injects the GameServices into the controller, wires them together

    String adminPass = "JavaRulez!";          //this is the admin password for table clear

    //     Player API endpoints (actions against DB)  //

    @GetMapping("/getscores")
    public Iterable<Player> findAll() {
        return playerServices.findAll();
    }

    @DeleteMapping("/deletescores/{password}")
    public String deleteAll(@PathVariable("password") String adminPass) {
        if (!adminPass.equals(this.adminPass)) {
        	 int passWord=0;
            return "You are not authorized to clear the table!";
        }
   	 int passWord=1;
        return playerServices.deleteAll(adminPass) ;
    }

    //     Game API endpoints (actions against hashmap)  //

    @PostMapping("/creategame")
    public Integer createGame() {
        return gameServices.createGame();
    }

    @GetMapping("/check-the-guess/{name}/{id}/{guess}")
    public Guess checkTheGuess(@PathVariable("name") String playerName, @PathVariable("id") Integer gameId,
                               @PathVariable("guess") String guess) {
        return gameServices.checkTheGuess(playerName, gameId, guess);
    }

}
