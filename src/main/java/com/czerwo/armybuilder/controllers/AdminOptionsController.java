package com.czerwo.armybuilder.controllers;

import com.czerwo.armybuilder.exceptions.DuplicateGroupException;
import com.czerwo.armybuilder.exceptions.NoRequestedArmyException;
import com.czerwo.armybuilder.exceptions.NoRequestedGroupException;
import com.czerwo.armybuilder.exceptions.NoRequestedUnitException;
import com.czerwo.armybuilder.models.data.Army;
import com.czerwo.armybuilder.models.data.options.GroupOfOptions;
import com.czerwo.armybuilder.models.data.options.Option;
import com.czerwo.armybuilder.services.OptionService;
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
@RequestMapping("/admin/group-of-options")
public class AdminOptionsController {

    private OptionService optionService;

    public AdminOptionsController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping
    public String index(Model model) {

        List<GroupOfOptions> listOfGroups = optionService.findAll();
        model.addAttribute("listOfGroups", listOfGroups);

        return "admin/groupOfOptions/index";
    }

    @GetMapping("/add")
    public String addGroup(Model model) {

        model.addAttribute("groupOfOptions", new GroupOfOptions());

        return "admin/groupOfOptions/add";
    }

    @PostMapping("/add")
    public String addGroup(@Valid GroupOfOptions groupOfOptions,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {

            //model.addAttribute("group", groupOfOptions);
            return "admin/groupOfOptions/add";
        }

        if (!optionService.findGroupByName(groupOfOptions.getName()).isPresent()) {
            redirectAttributes.addFlashAttribute("message", "Group edited");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            optionService.saveGroup(groupOfOptions);
        } else {
            throw new DuplicateGroupException(groupOfOptions.getName());
        }

        return "redirect:/admin/group-of-options";

    }


    @GetMapping("/edit/{id}")
    public String editGroup(@PathVariable int id, Model model) {

        Optional<GroupOfOptions> groupById = optionService.findGroupById(id);

        groupById.ifPresent(it -> model.addAttribute("groupOfOptions", it));

        return groupById.map(it -> "admin/groupOfOptions/edit").orElseThrow(() -> new NoRequestedGroupException(id));
    }

    @PostMapping("/edit")
    public String editGroup(@Valid GroupOfOptions groupOfOptions,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {

            //model.addAttribute("group", groupOfOptions);
            return "admin/groupOfOptions/edit";
        }

        if (optionService.findGroupById(groupOfOptions.getId()).isPresent()) {
            redirectAttributes.addFlashAttribute("message", "Group edited");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            optionService.updateGroup(groupOfOptions);
        } else {
            throw new NoRequestedGroupException(groupOfOptions.getId());
        }

        return "redirect:/admin/group-of-options";

    }


    @GetMapping("/delete/{groupId}")
    public String delete(@PathVariable int groupId, RedirectAttributes redirectAttributes) {

        if (optionService.findGroupById(groupId).isPresent()) {
            redirectAttributes.addFlashAttribute("message", "group deleted");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            optionService.deleteGroupById(groupId);
        } else throw new NoRequestedGroupException(groupId);


        return "redirect:/admin/group-of-options";
    }

    @GetMapping("/{groupId}/options")
    public String optionsIndex(@PathVariable int groupId, Model model) {
        Optional<GroupOfOptions> groupByIdWithOptions = optionService.findGroupByIdWithOptions(groupId);
        groupByIdWithOptions.ifPresent(it -> {
            model.addAttribute("listOfOptions", it.getOptions());
            model.addAttribute("groupId", it.getId());
        });
        return groupByIdWithOptions.map(it -> "admin/options/index").orElseThrow(() -> new NoRequestedGroupException(groupId));
    }

    @GetMapping("/{groupId}/options/add")
    public String createOption(@PathVariable int groupId, Model model) {

        model.addAttribute("option", new Option());
        model.addAttribute("groupId", groupId);

        return "admin/options/add";
    }

    @PostMapping("/{groupId}/options/add")
    public String createOption(@Valid Option option, BindingResult bindingResult, @PathVariable int groupId, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            //model.addAttribute("group", groupOfOptions);
            return "admin/options/add";
        }

        redirectAttributes.addFlashAttribute("message", "Option added!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        optionService.createOptionAndAddToGroup(option, groupId);

        return "redirect:/admin/group-of-options/" + groupId + "/options";
    }


    @GetMapping("/{groupId}/options/add-existing")
    public String addExistingOption(@PathVariable int groupId, Model model) {

        List<Option> allOptions = optionService.findAllOptions();
        model.addAttribute("allOptions", allOptions);
        model.addAttribute("optionList", new ArrayList<Integer>());
        model.addAttribute("groupId", groupId);

        return "admin/options/addExisting";
    }


    @PostMapping("/{groupId}/options/add-existing")
    public String createOption(@RequestParam(name = "optionList") List<Integer> optionList,
                               @PathVariable int groupId,
                               RedirectAttributes redirectAttributes) {


        optionService.addOptionsToGroup(optionList, groupId);
        redirectAttributes.addFlashAttribute("message", "Options added to group!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");


        return "redirect:/admin/group-of-options/" + groupId + "/options";
    }

//todo delete option from group of options
    @GetMapping("/{groupId}/options/{optionId}/delete")
    public String deleteOptionFromGroup(@PathVariable int groupId, @PathVariable int optionId, RedirectAttributes redirectAttributes)
    {

        optionService.removeOptionFromGroup(groupId,optionId);
        redirectAttributes.addFlashAttribute("message", "Options successfully deleted from a group!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/admin/group-of-options/" + groupId + "/options";
    }

}
