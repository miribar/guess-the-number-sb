package com.oem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     //For serving REST requests
public class GameboardController {

    @Autowired
    private GameServices services;

    @RequestMapping("/test")
    public String test() {
        return "Test!!!";
    }
}