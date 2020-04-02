package com.czerwo.armybuilder.controllers;

import com.czerwo.armybuilder.auth.ApplicationUserService;
import com.czerwo.armybuilder.models.data.Army;
import com.czerwo.armybuilder.models.data.OrderedUnit;
import com.czerwo.armybuilder.models.data.Roster;
import com.czerwo.armybuilder.models.data.Unit;
import com.czerwo.armybuilder.services.ArmyService;
import com.czerwo.armybuilder.services.RosterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/roster")
public class RosterController {

    RosterService rosterService;
    ArmyService armyService;
    ApplicationUserService applicationUserService;

    public RosterController(RosterService rosterService, ArmyService armyService, ApplicationUserService applicationUserService) {
        this.rosterService = rosterService;
        this.armyService = armyService;
        this.applicationUserService = applicationUserService;
    }

    @GetMapping
    public String index(Model model, Principal principal) {
        List<Roster> rosters = rosterService.findByUsername(principal.getName());
        model.addAttribute("rosters", rosters);

        return "roster/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("roster", new Roster());
        model.addAttribute("armies", armyService.findAll());

        return "roster/add";
    }

    @PostMapping("/add")
    public String add(Roster roster, @RequestParam int armyId, Principal principal) {

        Army army = armyService.findById(armyId).get();
        roster.setArmy(army);
        roster.setApplicationUser(applicationUserService.getUser(principal.getName()).get());
        rosterService.save(roster);

        return "redirect:/roster/add";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {


        rosterService.deleteById(id);

        return "redirect:/roster";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        Roster roster = rosterService.findById(id).get();
        List<OrderedUnit> listOfOrderedUnits = rosterService.getListOfOrderedUnits(roster);
        model.addAttribute("listOfOrderedUnits", listOfOrderedUnits);
        model.addAttribute("rosterId", id);

        return "roster/edit";
    }


    @GetMapping("/edit/{rosterId}/addUnit")
    public String addUnitToRoster(Model model, @PathVariable int rosterId){
        Roster roster = rosterService.findById(rosterId).get();
        List<Unit> units = roster.getArmy().getUnits();
        model.addAttribute("roster", roster);
        model.addAttribute("units", units);
        model.addAttribute("orderedUnit", new OrderedUnit());
        return "roster/addUnit";
    }

    @PostMapping("/edit/{rosterId}/addUnit")
    public String addUnitToRoster(@PathVariable int rosterId, OrderedUnit orderedUnit, @RequestParam int unitId){

        Roster roster = rosterService.findById(rosterId).get();

        orderedUnit.setUnit(rosterService.findUnitById(unitId).get());
        roster.addUnit(orderedUnit);
        rosterService.save(roster);

        return "redirect:/roster/edit/{rosterId}";
    }

    @GetMapping("/edit/{rosterId}/editUnit/{orderedUnitId}")
    public String editOrderedUnit(Model model, @PathVariable int rosterId, @PathVariable int orderedUnitId){
        OrderedUnit orderedUnit = rosterService.getOrderedUnit(orderedUnitId).get();
        model.addAttribute("orderedUnit", orderedUnit);
        model.addAttribute("rosterId", rosterId);
        return "roster/editUnit";
    }


    @PostMapping("/edit/{rosterId}/editUnit/{orderedUnitId}")
    public String editOrderedUnit(@PathVariable int rosterId, OrderedUnit orderedUnit, @PathVariable int orderedUnitId){


        rosterService.save(orderedUnit);

        return "redirect:/roster/edit/{rosterId}";
    }


}
