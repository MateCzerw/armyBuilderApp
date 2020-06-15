package com.czerwo.armybuilder.exceptions;

public class DuplicateTournamentException extends RuntimeException {
    public DuplicateTournamentException(String tournamentName) {

        super("Tournament with name: " + tournamentName + " is duplicated!");

    }
}
