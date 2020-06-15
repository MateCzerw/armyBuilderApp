package com.czerwo.armybuilder.exceptions;



public class NoRequestedRosterException extends RuntimeException {
    public NoRequestedRosterException(int id) {
        super("Roster with id: " + id + " not exist!");

    }
}
