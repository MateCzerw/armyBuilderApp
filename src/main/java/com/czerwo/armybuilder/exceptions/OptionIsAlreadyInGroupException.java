package com.czerwo.armybuilder.exceptions;

public class OptionIsAlreadyInGroupException extends RuntimeException {
    public OptionIsAlreadyInGroupException(int optionId) {
        super("Option with id: " + optionId + " is already in group!");
    }
}
