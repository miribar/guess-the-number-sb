package com.oem;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicLong;

public class Game {

    private Long gameId;          // generated in the service (singleton)
    private StringBuilder secretNum;    // generated in the service (singleton)
    private int numOfMoves = 0;
    private int numDigitsInPlace = 0;
    private int numDigitsNotInPlace = 0;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public StringBuilder getSecretNum() {
        return secretNum;
    }

    public void setSecretNum(StringBuilder secretNum) {
        this.secretNum = secretNum;
    }

    public int getNumOfMoves() {
        return numOfMoves;
    }

    public void setNumOfMoves(int numOfMoves) {
        this.numOfMoves = numOfMoves;
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

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", secretNum=" + secretNum +
                ", numOfMoves=" + numOfMoves +
                ", numDigitsInPlace=" + numDigitsInPlace +
                ", numDigitsNotInPlace=" + numDigitsNotInPlace +
                '}';
    }
}
