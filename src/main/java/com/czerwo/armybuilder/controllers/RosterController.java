package com.czerwo.armybuilder.controllers;

import com.czerwo.armybuilder.auth.ApplicationUserService;
import com.czerwo.armybuilder.exceptions.NoRequestedArmyException;
import com.czerwo.armybuilder.exceptions.NoRequestedOrderedUnitException;
import com.czerwo.armybuilder.exceptions.NoRequestedRosterException;
import com.czerwo.armybuilder.models.data.Army;
import com.czerwo.armybuilder.models.data.OrderedUnit;
import com.czerwo.armybuilder.models.data.Roster;
import com.czerwo.armybuilder.models.data.Unit;
import com.czerwo.armybuilder.models.data.options.GroupOfOptions;
import com.czerwo.armybuilder.models.data.options.Option;
import com.czerwo.armybuilder.services.ArmyService;
import com.czerwo.armybuilder.services.RosterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rosters")
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

        return "rosters/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("roster", new Roster());
        model.addAttribute("armies", armyService.findAll());

        return "rosters/add";
    }

    @PostMapping("/add")
    public String add(@Valid Roster roster, BindingResult bindingResult, @RequestParam int armyId, Principal principal, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "rosters/add";
        }

        rosterService.addNewRoster(roster, armyId, principal);

        redirectAttributes.addFlashAttribute("message", "Roster added!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/rosters";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {

        rosterService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Roster deleted!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/rosters";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        Roster roster = rosterService.findById(id).orElseThrow(() -> new NoRequestedRosterException(id));

        model.addAttribute("roster", roster);
        model.addAttribute("armies", armyService.findAll());

        return "rosters/editRoster";
    }

    @PostMapping("/edit/{rosterId}")
    public String edit(@Valid Roster roster,
                       BindingResult bindingResult,
                       Principal principal,
                       Model model,
                       RedirectAttributes redirectAttributes,
                       @PathVariable int rosterId,
                       @RequestParam int armyId) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "Roster not edited!");
            model.addAttribute("alertClass", "alert-danger");
            return "rosters/add";
        }

        rosterService.editRoster(roster, armyId, principal);

        redirectAttributes.addFlashAttribute("message", "Roster added!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/rosters";
    }

    // edit units
//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable int id, Model model) {
//
//        Roster roster = rosterService.findById(id).get();
//        List<OrderedUnit> listOfOrderedUnits = rosterService.getListOfOrderedUnits(roster);
//        model.addAttribute("listOfOrderedUnits", listOfOrderedUnits);
//        model.addAttribute("rosterId", id);
//
//        return "rosters/edit";
//    }


    @GetMapping("/{rosterId}/add-unit")
    public String addUnitToRoster(Model model, @PathVariable int rosterId) {

        Roster roster = rosterService.findById(rosterId).orElseThrow(() -> new NoRequestedRosterException(rosterId));
        Army army = armyService.findByIdWithUnits(roster.getArmy().getId()).orElseThrow(() -> new NoRequestedArmyException(roster.getArmy().getId()));

        List<Unit> units = army.getUnits();
        model.addAttribute("units", units);
        model.addAttribute("orderedUnit", new OrderedUnit());
        model.addAttribute("rosterId", rosterId);
        return "rosters/addUnit";
    }

    @PostMapping("/{rosterId}/add-unit")
    public String addUnitToRoster(OrderedUnit orderedUnit,
                                  //                             BindingResult bindingResult,
                                  @PathVariable int rosterId,
                                  @RequestParam int unitId,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {

//        if(bindingResult.hasErrors()){
//            model.addAttribute("message", "Choosen unit has errors!");
//            model.addAttribute("alertClass", "alert-danger");
//            return "rosters/addUnit";
//        }


        rosterService.addUnitToRoster(rosterId, orderedUnit, unitId);

        redirectAttributes.addFlashAttribute("message", "Unit added!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/rosters";
    }

    @GetMapping("/{rosterId}/delete-unit/{orderedUnitId}")
    public String deleteOrderedUnit(Model model, @PathVariable int rosterId, @PathVariable int orderedUnitId) {
        rosterService.deleteOrderedUnitFromRoster(orderedUnitId, rosterId);
        return "redirect:/rosters";
    }

//    @GetMapping("/{rosterId}/delete-unit/{orderedUnitId}")
//    public String editOrderedUnit(Model model, @PathVariable int rosterId, @PathVariable int orderedUnitId){
//        OrderedUnit orderedUnit = rosterService.getOrderedUnit(orderedUnitId).get();
//        model.addAttribute("orderedUnit", orderedUnit);
//        model.addAttribute("rosterId", rosterId);
//        return "roster/editUnit";
//    }

    @GetMapping("/{rosterId}/edit-unit/{orderedUnitId}")
    public String editOrderedUnit(@PathVariable int rosterId, @PathVariable int orderedUnitId, Model model) {

        OrderedUnit orderedUnit = rosterService.findOrderedUnitById(orderedUnitId).orElseThrow(() -> new NoRequestedOrderedUnitException(orderedUnitId));

        List<GroupOfOptions> groupOfOptions = orderedUnit.getUnit().getGroupsOfOptions();

        List<Integer> chosenOptionsId = orderedUnit.getChoosenOptions().stream().map(option -> option.getId()).collect(Collectors.toList());


        model.addAttribute("orderedUnitMinNumberOfModels", orderedUnit.getUnit().getMinSizeOfUnit());
        model.addAttribute("orderedUnitMaxNumberOfModels", orderedUnit.getUnit().getMaxSizeOfUnit());
        model.addAttribute("numberOfModels", orderedUnit.getNumberOfModels());
        model.addAttribute("groupOfOptions", groupOfOptions);
        model.addAttribute("rosterId", rosterId);
        model.addAttribute("chosenOptionsId", chosenOptionsId);

        return "rosters/editUnit";
    }


    @PostMapping("/{rosterId}/edit-unit/{orderedUnitId}")
    public String editOrderedUnit(@PathVariable int rosterId,
                                  @PathVariable int orderedUnitId,
                                  @RequestParam int numberOfModels,
                                  @RequestParam List<Integer> options,
                                  RedirectAttributes redirectAttributes) {

        rosterService.editOrderedUnit(orderedUnitId, numberOfModels, options);

        redirectAttributes.addFlashAttribute("message", "Unit successfully edited!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/rosters";
    }


}
