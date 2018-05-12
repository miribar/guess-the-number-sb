package com.oem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController                             //For serving REST requests, all responses default format is JSON
@RequestMapping("/rest/api/")  		        //this creates the service main entry point
public class GameboardController {

    @Autowired                              //this injects the services into the controller, wires them together
    private GameServices services;

    @GetMapping("/getscores")
    public Iterable<Player> getTopTen() {
        return services.getTopTen();
    }

    @PostMapping("/add")
    public Long addPlayer(@RequestBody Player player) {  //@RequestBody is a JSON and we get the params from outside
        return services.createPlayer(player);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePlayer(@RequestBody Long player_id) {
        services.deletePlayer(player_id);
    }
}
