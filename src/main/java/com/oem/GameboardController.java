package com.oem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // For serving REST requests
public class GameboardController {
	private int uniqeGameId;

	@Autowired
	private GameServices services;

	@RequestMapping("/test")
	public String test() {
		return "Test!!!";
	}

	@RequestMapping("/gameid")
	public int getGameId() {
		return uniqeGameId;

	}

	// @RequestMapping("/code")
	// public int[] getCode{
	// return
}
