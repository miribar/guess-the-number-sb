package com.oem;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServicesImpl implements GameServices {
	private int[] rndNum = new int[4];
	Random rnd = new Random();
	private boolean[] helper = new boolean[10];

	@Autowired
	private GameRepository dao;
	private int uniqeGameId = 1;

	@Override
	public Player createEntry(String name, int num_of_guesses) {
		return dao.save(new Player(null, name, num_of_guesses));
	}

	public synchronized int generateGameId() {
		this.uniqeGameId++;
		return uniqeGameId;
	}

	@Override
	public int[] generateSecretNumber() {
		for (int i = 0; i < 4; i++) {
			int tmp = rnd.nextInt(10);
			while ((helper[tmp])) {
				tmp = rnd.nextInt(10);
			}
			rndNum[i] = tmp;
			helper[tmp] = true;
		}
		return rndNum;
	}

}
