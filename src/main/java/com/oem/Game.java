package com.oem;

import java.util.concurrent.atomic.AtomicInteger;

public class Game {

    private StringBuilder secretNum;        // generated in the service (singleton)
    private int numOfGuesses = 0;
    private int numDigitsInPlace = 0;
    private int numDigitsNotInPlace = 0;

    public StringBuilder getSecretNum() {
        return secretNum;
    }

    public void setSecretNum(StringBuilder secretNum) {
        this.secretNum = secretNum;
    }

    public int getNumOfGuesses() {
        return numOfGuesses;
    }

    public void setNumOfGuesses(int numOfGuesses) {
        this.numOfGuesses = numOfGuesses;
    }

    public int getNumDigitsInPlace() {
        return numDigitsInPlace;
    }

    public void setNumDigitsInPlace(int numDigitsInPlace) {
        this.numDigitsInPlace = numDigitsInPlace;
    }

    public int getNumDigitsNotInPlace() {
        return numDigitsNotInPlace;
    }

    public void setNumDigitsNotInPlace(int numDigitsNotInPlace) {
        this.numDigitsNotInPlace = numDigitsNotInPlace;
    }

}
