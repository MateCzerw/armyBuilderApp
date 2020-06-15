package com.czerwo.armybuilder.exceptions;

public class GroupIsAlreadyInUnitException extends RuntimeException {
    public GroupIsAlreadyInUnitException(int groupId) {
        super("Group with id: " + groupId + " is already in unit!");
    }
}
