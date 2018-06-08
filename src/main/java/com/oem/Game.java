package com.oem;

public class Game {

    private String secretNum;        // generated in the service (singleton)
    private int numOfGuesses = 0;

    public String getSecretNum() {
        return secretNum;
    }

    public void setSecretNum(String secretNum) {
        this.secretNum = secretNum;
    }

    public int getNumOfGuesses() {
        return numOfGuesses;
    }

    public void setNumOfGuesses() {
        this.numOfGuesses++;
    }
  
}
