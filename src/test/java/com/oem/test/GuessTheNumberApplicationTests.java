package com.oem.test;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuessTheNumberApplicationTests {
	private int[] rndNum = new int[4];
	Random rnd = new Random();
	private boolean[] helper = new boolean[10];

	@Test
	public void contextLoads() {

		for (int i = 0; i < 4; i++) {
			int tmp = rnd.nextInt(10);
			while ((helper[tmp])) {
				tmp = rnd.nextInt(10);
			}
			rndNum[i] = tmp;
			helper[tmp] = true;
		}

		for (int b : rndNum) {
			System.out.println(b);
		}
	}
}
