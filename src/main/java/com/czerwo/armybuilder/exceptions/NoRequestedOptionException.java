package com.czerwo.armybuilder.exceptions;

public class NoRequestedOptionException extends RuntimeException {
    public NoRequestedOptionException(int optionId) {
        super("Option with id: " + optionId + " not found!");
    }
}
