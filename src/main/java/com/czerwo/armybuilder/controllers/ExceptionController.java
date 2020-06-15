package com.czerwo.armybuilder.controllers;

import com.czerwo.armybuilder.exceptions.NoRequestedArmyException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = NoRequestedArmyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleError(NoRequestedArmyException noRequestedArmyException, Model model) {

        model.addAttribute("message", noRequestedArmyException.getMessage());
        return "errors/norequestedarmy";
    }




}
