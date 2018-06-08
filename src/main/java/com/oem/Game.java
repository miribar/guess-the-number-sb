package com.oem;

import java.util.concurrent.atomic.AtomicLong;

public class Game {

    private AtomicLong gameId;          // generated in the service (singleton)
    private StringBuilder secretNum;    // generated in the service (singleton)
    private int numOfMoves = 0;
    private int numDigitsInPlace = 0;
    private int numDigitsNotInPlace = 0;
    private String adminUserName;
    private String adminPassword;

    public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public AtomicLong getGameId() {
        return gameId;
    }

    public void setGameId(AtomicLong gameId) {
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
}
