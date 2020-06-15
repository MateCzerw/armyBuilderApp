package com.czerwo.armybuilder.exceptions;

public class NoRequestedUserException extends RuntimeException {
    public NoRequestedUserException(String name) {
        super("User with name: " + name + " not exist!");
    }

    public NoRequestedUserException(int userId) {
        super("User with id: " + userId + " not exist!");

    }
}
