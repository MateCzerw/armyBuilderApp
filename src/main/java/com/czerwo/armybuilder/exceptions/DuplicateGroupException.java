package com.czerwo.armybuilder.exceptions;

public class DuplicateGroupException extends RuntimeException {
    public DuplicateGroupException(String name) {
        super("Group with name: " + name + " is duplicated!");
    }
}
