package com.czerwo.armybuilder.exceptions;

public class NoRequestedOrderedUnitException extends RuntimeException {
    public NoRequestedOrderedUnitException(int orderedUnitId) {
        super("Ordered unit with id: " + orderedUnitId + " not found!");
    }
}
