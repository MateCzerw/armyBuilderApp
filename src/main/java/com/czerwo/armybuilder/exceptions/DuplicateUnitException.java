package com.czerwo.armybuilder.exceptions;

public class DuplicateUnitException extends RuntimeException {
    public DuplicateUnitException(String name) {
        super("Unit with name: " + name + " is duplicated!");
    }
}
