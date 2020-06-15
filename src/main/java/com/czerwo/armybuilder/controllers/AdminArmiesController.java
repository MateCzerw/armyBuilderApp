package com.czerwo.armybuilder.controllers;

import com.czerwo.armybuilder.exceptions.NoRequestedArmyException;
import com.czerwo.armybuilder.models.data.Army;
import com.czerwo.armybuilder.services.ArmyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/armies")
public class AdminArmiesController {

    private final ArmyService armyService;

    @Autowired
    public AdminArmiesController(ArmyService armyService) {
        this.armyService = armyService;
    }

    @GetMapping
    public String index(Model model) {

        List<Army> armies = armyService.findAll();
        model.addAttribute("armies", armies);
        return "admin/armies/index";
    }

    @GetMapping("/add")
    public String addArmy(Model model) {
        model.addAttribute("army", new Army());
        return "admin/armies/add";
    }

    @PostMapping("/add")
    public String addArmy(@Valid Army army,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {

            return "admin/armies/add";
        }


        if (!armyService.findByName(army.getName()).isPresent()) {
            redirectAttributes.addFlashAttribute("message", "Army added");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            armyService.save(army);
        } else {
            redirectAttributes.addFlashAttribute("message", "Army " + army.getName() + " already exists. Please add a new one!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/admin/armies/add";
        }


        return "redirect:/admin/armies";

    }


    @GetMapping("/edit/{id}")
    public String editArmy(@PathVariable int id, Model model) {

        Optional<Army> army = armyService.findById(id);

        army.ifPresent(it -> model.addAttribute("army", it));
        army.ifPresent(it -> model.addAttribute("armyId", id));

        return army.map(it -> "admin/armies/edit").orElseThrow(() -> new NoRequestedArmyException(id));
    }

    @PostMapping("/edit")
    public String editArmy(@Valid Army army,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Model model) {


        if (bindingResult.hasErrors()) {

            //model.addAttribute("army", army);
            return "admin/armies/edit";
        }

        if (armyService.findById(army.getId()).isPresent()) {
            redirectAttributes.addFlashAttribute("message", "Army edited");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            armyService.update(army);
        } else {
            throw new NoRequestedArmyException(army.getId());
        }

        return "redirect:/admin/armies";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {


        Optional<Army> army = armyService.findById(id);

        if(army.isPresent()){
            redirectAttributes.addFlashAttribute("message", "Army deleted");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            armyService.deleteById(id);
        } else {
            throw new NoRequestedArmyException(id);
        }




        return "redirect:/admin/armies";
    }

//    @PostMapping("/add")
//    public String addArmy(@Valid Army army,
//                          BindingResult bindingResult,
//                          @RequestParam(value = "file", required = false) MultipartFile file,
//                          RedirectAttributes redirectAttributes,
//                          Model model) throws IOException {
//
//
//        if(bindingResult.hasErrors()){
//            return "admin/armies/add";
//        }
//
//        if(file != null){
//
//
//            String filename = file.getOriginalFilename();
//
//            if( !(filename.endsWith("jpg") || filename.endsWith("png"))){
//                redirectAttributes.addFlashAttribute("message", "Image must be a jpg or a png");
//                redirectAttributes.addFlashAttribute("alertClass", "alert-success");
//            }
//
//            if(!armyService.findByName(army.getName()).isPresent()){
//                redirectAttributes.addFlashAttribute("message", "Army added");
//                redirectAttributes.addFlashAttribute("alertClass", "alert-success");
//                armyService.save(army, file);
//            } else {
//                redirectAttributes.addFlashAttribute("message", "Army " + army.getName() + " already exists. Please add a new one!");
//                redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
//                return "redirect:/admin/armies/add";
//            }
//        }
//        else{
//            if(!armyService.findByName(army.getName()).isPresent())
//            armyService.save(army);
//        }
//
//
//
//
//
//        return "redirect:/admin/armies";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editArmy(@PathVariable int id, Model model){
//        Optional<Army> army = armyService.findById(id);
//        army.ifPresent(it -> model.addAttribute("army", it));
//        return army.map(it -> "admin/armies/edit"). orElseThrow(() -> new ResourceNotFoundException(id));
//    }
//
//    @PostMapping("/edit")
//    public String editArmy(@Valid Army army,
//                          BindingResult bindingResult,
//                          @RequestParam(value = "file", required = false) MultipartFile file,
//                          RedirectAttributes redirectAttributes,
//                          Model model) throws IOException {
//
//
//        if(bindingResult.hasErrors()){
//            return "admin/armies/add";
//        }
//
//        if(file == null){
//
//            redirectAttributes.addFlashAttribute("message", "Please add an image!");
//            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
//            return "redirect:/admin/armies/add";
//        }
//        else{
//            String filename = file.getOriginalFilename();
//
//            if( !(filename.endsWith("jpg") || filename.endsWith("png"))){
//                redirectAttributes.addFlashAttribute("message", "Image must be a jpg or a png");
//                redirectAttributes.addFlashAttribute("alertClass", "alert-success");
//            }
//
//            if(!armyService.findByName(army.getName()).isPresent()){
//                redirectAttributes.addFlashAttribute("message", "Army added");
//                redirectAttributes.addFlashAttribute("alertClass", "alert-success");
//                armyService.save(army, file);
//            } else {
//                redirectAttributes.addFlashAttribute("message", "Army " + army.getName() + " already exists. Please add a new one!");
//                redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
//                return "redirect:/admin/armies/add";
//            }
//        }
//
//
//
//
//        return "redirect:/admin/armies";
//    }


}
