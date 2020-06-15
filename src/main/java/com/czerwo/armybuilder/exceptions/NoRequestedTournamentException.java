package com.czerwo.armybuilder.exceptions;

public class NoRequestedTournamentException extends RuntimeException {
    public NoRequestedTournamentException(int tournamentId) {
        super("No requested tournament with id: " + tournamentId);
    }
}
