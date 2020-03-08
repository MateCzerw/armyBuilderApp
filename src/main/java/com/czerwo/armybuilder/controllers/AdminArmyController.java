package com.czerwo.armybuilder.controllers;

import com.czerwo.armybuilder.models.ArmyRepository;
import com.czerwo.armybuilder.models.data.Army;
import com.czerwo.armybuilder.services.ArmyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/admin/army")
public class AdminArmyController {

    ArmyService armyService;

    @Autowired
    public AdminArmyController(ArmyService armyService) {
        this.armyService = armyService;
    }

    @GetMapping
    public String index(Model model){

        List<Army> armies = armyService.findAll();
        model.addAttribute("armies", armies);
        return "admin/army/index";
    }

    @GetMapping("/add")
    public String addArmy(Model model){
        model.addAttribute("army", new Army());
        return "admin/army/add";
    }

    @PostMapping("/add")
    public String addArmy(@Valid Army army, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "admin/army/add";
        }

        redirectAttributes.addFlashAttribute("message", "Army added");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");


        armyService.save(army);
        return "redirect:/admin/army/add";
    }

    @GetMapping("/edit/{id}")
    public String editArmy(@PathVariable int id, Model model){
        Optional<Army> optionalArmy = armyService.findById(id);
        Army army = optionalArmy.get();
        model.addAttribute("army", army);
        return "admin/army/edit";
    }

    @PostMapping("/edit")
    public String editArmy(@Valid Army army, BindingResult bindingResult, RedirectAttributes redirectAttributes){


        if(bindingResult.hasErrors()){
            return "admin/army/edit";
        }

        Optional<Army> armyFromRepo = armyService.findById(army.getId());
        Army armyToUpdate = armyFromRepo.get();
        armyToUpdate.setName(army.getName());
        redirectAttributes.addFlashAttribute("message", "Army added");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        armyService.save(armyToUpdate);

        return "redirect:/admin/army/edit/" + army.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes)
    {
        armyService.deleteById(id);

        redirectAttributes.addFlashAttribute("message", "Army deleted");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/admin/army";
    }


}
