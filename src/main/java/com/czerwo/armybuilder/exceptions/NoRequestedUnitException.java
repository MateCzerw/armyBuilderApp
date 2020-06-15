package com.czerwo.armybuilder.exceptions;

public class NoRequestedUnitException extends RuntimeException{
    public NoRequestedUnitException(int unitId) {
        super("Unit with id: " + unitId + " not found!");
    }
}
