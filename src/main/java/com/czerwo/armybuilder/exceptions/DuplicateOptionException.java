package com.czerwo.armybuilder.exceptions;

public class DuplicateOptionException extends RuntimeException{
    public DuplicateOptionException(String name) {
        super("Option with name: " + name + " is duplicated!");
    }
}
