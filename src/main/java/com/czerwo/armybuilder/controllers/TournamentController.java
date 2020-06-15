package com.czerwo.armybuilder.controllers;

import com.czerwo.armybuilder.auth.ApplicationUser;
import com.czerwo.armybuilder.models.data.Roster;
import com.czerwo.armybuilder.models.data.Tournament;
import com.czerwo.armybuilder.services.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.model.IModel;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {

    TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public String index(Model model, Principal principal) {

        List<Tournament> tournaments = tournamentService.findAllByParticipantsNotContaining(principal.getName());
        List<Tournament> ownTournaments = tournamentService.findOwnTournaments(principal.getName());
        List<Tournament> signedUpTournaments = tournamentService.findSignedUpTournaments(principal.getName());


        model.addAttribute("tournaments", tournaments);
        model.addAttribute("ownTournaments", ownTournaments);
        model.addAttribute("signedUpTournaments", signedUpTournaments);

        return "tournaments/index";
    }

    @GetMapping("/add")
    public String add(Model model) {


        model.addAttribute("tournament", new Tournament());

        return "tournaments/add";
    }

    @PostMapping("/add")
    public String add(Tournament tournament,
                      //                 BindingResult bindingResult,
                      RedirectAttributes redirectAttributes,
                      Principal principal,
                      Model model,
                      @RequestParam String tournamentTime) throws ParseException {

//        if(bindingResult.hasErrors()){
//            model.addAttribute("message", "There are errors");
//            model.addAttribute("alertClass", "alert-danger");
//
//            return "tournaments/add";
//        }
        tournamentService.addTournament(tournament, principal.getName(), tournamentTime);

        return "redirect:/tournaments";
    }

    @GetMapping("{tournamentId}/signup")
    public String signup(@PathVariable int tournamentId,
                         RedirectAttributes redirectAttributes,
                         Principal principal) {

        tournamentService.signupForTournament(tournamentId, principal.getName());

        redirectAttributes.addFlashAttribute("message", "Signed up for tournament!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/tournaments";
    }

    @GetMapping("{tournamentId}/unsubscribe")
    public String unsubscribe(@PathVariable int tournamentId,
                         RedirectAttributes redirectAttributes,
                         Principal principal) {

        tournamentService.unsubscribeFromTournament(tournamentId, principal.getName());

        redirectAttributes.addFlashAttribute("message", "Unsubscribed from tournament!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/tournaments";
    }

    @GetMapping("{tournamentId}/delete")
    public String delete(@PathVariable int tournamentId,
                         RedirectAttributes redirectAttributes,
                         Principal principal) {

        tournamentService.deleteOwnTournament(tournamentId, principal.getName());

        redirectAttributes.addFlashAttribute("message", "Deleted own tournament!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/tournaments";
    }

    @GetMapping("{tournamentId}/manage")
    public String delete(@PathVariable int tournamentId,
                         Model model,
                         Principal principal) {

        List<ApplicationUser> participants = tournamentService.manageOwnTournament(tournamentId, principal.getName());

        model.addAttribute("participants", participants);
        model.addAttribute("tournament", tournamentService.findById(tournamentId));

        return "tournaments/manage";
    }

    @GetMapping("{tournamentId}/remove/{userId}")
    public String removeParticipant(@PathVariable int tournamentId,
                         @PathVariable int userId,
                         RedirectAttributes redirectAttributes,
                         Principal principal) {

        tournamentService.removeParticipantFromTournament(tournamentId,userId, principal.getName());

        redirectAttributes.addFlashAttribute("message", "Participant deleted successfully!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/tournaments/" + tournamentId + "/manage";
    }





}
