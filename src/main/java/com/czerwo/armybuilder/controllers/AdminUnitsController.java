package com.czerwo.armybuilder.controllers;

import com.czerwo.armybuilder.exceptions.NoRequestedArmyException;
import com.czerwo.armybuilder.exceptions.NoRequestedUnitException;
import com.czerwo.armybuilder.models.data.Army;
import com.czerwo.armybuilder.models.data.Unit;
import com.czerwo.armybuilder.models.data.UnitDetails;
import com.czerwo.armybuilder.models.data.options.GroupOfOptions;
import com.czerwo.armybuilder.models.data.options.Option;
import com.czerwo.armybuilder.services.ArmyService;
import com.czerwo.armybuilder.services.OptionService;
import com.czerwo.armybuilder.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminUnitsController {

    UnitService unitService;
    ArmyService armyService;
    OptionService optionService;

    public AdminUnitsController(UnitService unitService, ArmyService armyService, OptionService optionService) {
        this.unitService = unitService;
        this.armyService = armyService;
        this.optionService = optionService;
    }

    @GetMapping("/armies/{armyId}/units")
    public String index(Model model, @PathVariable int armyId) {

        Optional<Army> armyById = armyService.findByIdWithUnits(armyId);

        armyById.ifPresent(army -> {
            model.addAttribute("units", army.getUnits());
            model.addAttribute("armyId", armyId);
        });

        return armyById.map(army -> "admin/units/index").orElseThrow(() -> new NoRequestedArmyException(armyId));
    }


    @GetMapping("/armies/{armyId}/units/add")
    public String add(@PathVariable int armyId, Model model) {

        Optional<Army> armyById = armyService.findByIdWithUnits(armyId);

        armyById.ifPresent(army -> {
            model.addAttribute("unit", new Unit());
            model.addAttribute("armyId", armyId);
        });

        return armyById.map(army -> "admin/units/add").orElseThrow(() -> new NoRequestedArmyException(armyId));
    }

    @PostMapping("/armies/{armyId}/units/add")
    public String add(@Valid Unit unit, BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable int armyId, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("armyId", armyId);
            model.addAttribute("unit", unit);
            return "admin/units/add";
        }

        armyService.findById(armyId).orElseThrow(() -> new NoRequestedArmyException(armyId));


            if (!unitService.findByName(unit.getName()).isPresent()) {
                redirectAttributes.addFlashAttribute("message", "Unit added!");
                redirectAttributes.addFlashAttribute("alertClass", "alert-success");
                unitService.save(unit, armyId);
            } else {
                model.addAttribute("message", "Unit " + unit.getName() + " already exists. Please add a new one!");
                model.addAttribute("alertClass", "alert-danger");
                model.addAttribute("unit", unit);
                return "admin/units/add";
            }

        return "redirect:/admin/armies/" + armyId + "/units";
    }


    @GetMapping("/units/edit/{unitId}")
    public String edit(Model model, @PathVariable int unitId) {

        Optional<Unit> unitById = unitService.findById(unitId);
        if (unitById.isPresent()) {
            model.addAttribute("unit", unitById.get());
        } else throw new NoRequestedUnitException(unitId);

        return "admin/units/edit";
    }

    @PostMapping("units/edit")
    public String edit(@Valid Unit unit, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("unit", unit);
            return "admin/units/add";
        }

        Optional<Unit> unitById = unitService.findById(unit.getId());



        unitById.orElseThrow(() -> new NoRequestedUnitException(unit.getId()));

        if (!unitService.findByName(unit.getName()).isPresent() ||
                unitById
                        .map(it -> unit.getName().equals(it.getName())).
                        orElse(false)) {
            redirectAttributes.addFlashAttribute("message", "Unit edited!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            unitById.ifPresent(
                    it ->{
                        it.setName(unit.getName());
                        it.setBaseCost(unit.getBaseCost());
                        it.setAdditionalModelsCost(unit.getAdditionalModelsCost());
                        it.setMaxSizeOfUnit(unit.getMaxSizeOfUnit());
                        it.setMinSizeOfUnit(unit.getMinSizeOfUnit());
                        unitService.save(it);
                    }
            );

        } else {
            model.addAttribute("message", "Unit " + unit.getName() + " already exists. Please add a new one!");
            model.addAttribute("alertClass", "alert-danger");
            model.addAttribute("unit", unit);
            return "admin/units/add";
        }

        return unitById.map((it) -> "redirect:/admin/armies/" + it.getArmy().getId() + "/units").orElse("redirect:/admin/armies");
    }

    @GetMapping("/armies/{armyId}/units/delete/{unitId}")
    public String delete(@PathVariable int unitId, @PathVariable int armyId, RedirectAttributes redirectAttributes) {

        if (unitService.findById(unitId).isPresent()) {
            redirectAttributes.addFlashAttribute("message", "unit deleted");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            unitService.deleteById(unitId);
        } else throw new NoRequestedUnitException(unitId);


        return "redirect:/admin/armies/" + armyId + "/units";
    }



    @GetMapping("/units/edit-details/{unitId}")
    public String editUnitDetails(@PathVariable int unitId, Model model) {

        Optional<Unit> unitById = unitService.findById(unitId);

        Unit unit = unitById.orElseThrow(() -> new NoRequestedUnitException(unitId));
        UnitDetails unitDetails = unit.getUnitDetails();

        model.addAttribute("unitDetails", unitDetails);
        model.addAttribute("unit", unit);

        return "/admin/units/editUnitDetails";
    }



    @PostMapping("/units/edit-details/{unitId}")
    public String editUnitDetails(@Valid UnitDetails unitDetails,BindingResult bindingResult,@PathVariable int unitId, Model model) {

        if (bindingResult.hasErrors()) {

            return "/admin/units/editUnitDetails";
        }

        Optional<Unit> unitById = unitService.findById(unitId);
        unitById.orElseThrow(() -> new NoRequestedUnitException(unitId));



        unitService.saveUnitDetails(unitDetails);

        return unitById.map((it) -> "redirect:/admin/armies/" + it.getArmy().getId() + "/units").orElse("redirect:/admin/armies");
    }

    @GetMapping("/units/{unitId}/groups")
    public String usedGroupOfOptionsIndex(@PathVariable int unitId, Model model) {

        Optional<Unit> unitById = unitService.findById(unitId);
        Unit unit = unitById.orElseThrow(() -> new NoRequestedUnitException(unitId));

        model.addAttribute("groups", unit.getGroupsOfOptions());
        model.addAttribute("unitId", unit.getId());
        model.addAttribute("armyId", unit.getArmy().getId());
        return "admin/units/groupsIndex";
    }

    @GetMapping("/units/{unitId}/groups/add")
    public String addGroupOfOptions(@PathVariable int unitId, Model model) {

        List<GroupOfOptions> groups = optionService.findAll();


        model.addAttribute("groups", groups);
        model.addAttribute("groupList", new ArrayList<Integer>());
        model.addAttribute("unitId", unitId);
        return "admin/units/addGroups";
    }


    @PostMapping("/units/{unitId}/groups/add")
    public String addGroupOfOptions(@PathVariable int unitId, @RequestParam(name = "groupList") List<Integer> groupList) {

        unitService.addGroupOfOptionsToUnit(unitId,groupList);


        return "redirect:/admin/units/" + unitId + "/groups";
    }

    @GetMapping("units/{unitId}/groups/{groupId}/delete")
    public String deleteGroupFromUnit(@PathVariable int groupId, @PathVariable int unitId, RedirectAttributes redirectAttributes)
    {

        unitService.removeGroupFromUnit(groupId,unitId);
        redirectAttributes.addFlashAttribute("message", "Options successfully deleted from a group!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/admin/units/" + unitId + "/groups";
    }


}
