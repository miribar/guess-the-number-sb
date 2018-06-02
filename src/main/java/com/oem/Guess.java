package com.oem;

public class Guess {

    private int numDigitsInPlace = 0;
    private int numDigitsNotInPlace = 0;

    public int getNumDigitsInPlace() {
        return numDigitsInPlace;
    }

    public void setNumDigitsInPlace() {
        this.numDigitsInPlace++;
    }

    public int getNumDigitsNotInPlace() {
        return numDigitsNotInPlace;
    }

    public void setNumDigitsNotInPlace() {
        this.numDigitsNotInPlace++;
    }

}
