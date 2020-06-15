package com.czerwo.armybuilder.exceptions;

public class DuplicateArmyException extends RuntimeException {
    public DuplicateArmyException(String name){
        super("Army with name: " + name + " is duplicated!");
    }
}
