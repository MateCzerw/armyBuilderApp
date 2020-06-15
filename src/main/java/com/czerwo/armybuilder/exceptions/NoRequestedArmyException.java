package com.czerwo.armybuilder.exceptions;

public class NoRequestedArmyException extends RuntimeException {
    public NoRequestedArmyException(int id){
        super("Army with id: " + id + " not found!");
    }
}
