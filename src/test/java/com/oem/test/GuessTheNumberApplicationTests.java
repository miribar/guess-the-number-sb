package com.oem.test;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuessTheNumberApplicationTests {

	@Test
	public void contextLoads() {

		String SALTCHARS = "1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
/*		while (salt.length() < 18) { // length of the random string.3
			
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
*/		for (int i = 0; i < 3; i++) {
			salt.append(SALTCHARS.charAt(i));
			
		}
		String saltStr = salt.toString();
		System.out.println(saltStr); 
	}
}
