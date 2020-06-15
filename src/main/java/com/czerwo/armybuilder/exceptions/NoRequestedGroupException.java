package com.czerwo.armybuilder.exceptions;

public class NoRequestedGroupException extends RuntimeException {
    public NoRequestedGroupException(int groupId) {
        super("Group with id: " + groupId + " not found!");
    }
}
