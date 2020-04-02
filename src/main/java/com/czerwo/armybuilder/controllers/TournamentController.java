package com.czerwo.armybuilder.controllers;

import com.czerwo.armybuilder.models.data.Roster;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/tournament")
public class TournamentController {

    @GetMapping
    public String index(Model model, Principal principal) {

        return "tournament/index";
    }


    //get createTournament

    //post createTournament

    //get signUpForTournament

    //post signUpForTournament

    //get manageOwnTournament

    //post manageOwnTournament


    //tournament id

    //get TournamentLeaderboard

    //post TournamentLeaderboard

    //parings

    //resultInParing


}
