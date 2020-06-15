package com.czerwo.armybuilder.exceptions;

import com.czerwo.armybuilder.models.data.Roster;

import java.util.function.Consumer;

public class DuplicateRosterException extends RuntimeException {
    public DuplicateRosterException(String name) {

        super("Roster with name: " + name + " is duplicated!");
    }
}
