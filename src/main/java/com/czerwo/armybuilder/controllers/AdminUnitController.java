package com.czerwo.armybuilder.controllers;

import com.czerwo.armybuilder.models.data.Army;
import com.czerwo.armybuilder.models.data.Unit;
import com.czerwo.armybuilder.models.data.UnitDetails;
import com.czerwo.armybuilder.services.ArmyService;
import com.czerwo.armybuilder.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/admin/unit")
public class AdminUnitController {

    UnitService unitService;
    ArmyService armyService;

    @Autowired
    public AdminUnitController(UnitService unitService,ArmyService armyService) {

        this.unitService = unitService;
        this.armyService = armyService;
    }

    @GetMapping
    public String index(Model model){

        model.addAttribute("units", unitService.getUnits());
        model.addAttribute("armies", armyService.findAll());
        return "admin/unit/index";
    }


    @GetMapping("/add")
    public String add(Model model){

        model.addAttribute("unit", new Unit());
        model.addAttribute("armies", armyService.findAll());
        return "admin/unit/add";
    }

    @PostMapping("/add")
    public String add(Unit unit, @RequestParam( value = "armyId", required = true) int armyId){

        Optional<Army> army = armyService.findById(armyId);
        Army army1 = army.get();
        unit.setArmy(army1);
        unit.setUnitDetails(new UnitDetails());
        unitService.save(unit);
        return "redirect:/admin/unit/add";
    }

    @GetMapping("/edit/{id}")
    public String add(Model model, @PathVariable int id){

        Optional<Unit> unitFromRepo = unitService.findById(id);
        Unit unit = unitFromRepo.get();
        model.addAttribute("unit", unit);
        model.addAttribute("chosenArmy", unit.getArmy().getId());
        model.addAttribute("armies", armyService.findAll());
        return "/admin/unit/edit";
    }

    @PostMapping("/edit")
    public String edit(Unit unit, @RequestParam( value = "armyId", required = true) int armyId){

        Optional<Army> armyFromRepo = armyService.findById(armyId);
        Optional<Unit> unitFromRepo = unitService.findById(unit.getId());

        Army armyToSave = armyFromRepo.get();
        Unit unitToSave = unitFromRepo.get();
        unitToSave.setName(unit.getName());
        unitToSave.setPoints(unit.getPoints());
        unitToSave.setArmy(armyToSave);
        unitService.save(unitToSave);
        return "redirect:/admin/unit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes)
    {
        unitService.deleteById(id);

        redirectAttributes.addFlashAttribute("message", "unit deleted");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/admin/unit";
    }





}
