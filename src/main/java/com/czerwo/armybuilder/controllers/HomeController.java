package com.czerwo.armybuilder.controllers;

import com.czerwo.armybuilder.auth.ApplicationUser;
import com.czerwo.armybuilder.auth.ApplicationUserRepository;
import com.czerwo.armybuilder.auth.ApplicationUserService;
import com.czerwo.armybuilder.models.TokenRepository;
import com.czerwo.armybuilder.models.data.Token;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    private final ApplicationUserService applicationUserService;
    private final TokenRepository tokenRepository;
    private final ApplicationUserRepository applicationUserRepository;


    public HomeController(ApplicationUserService applicationUserService, TokenRepository tokenRepository, ApplicationUserRepository applicationUserRepository) {
        this.applicationUserService = applicationUserService;
        this.tokenRepository = tokenRepository;
        this.applicationUserRepository = applicationUserRepository;
    }

    @GetMapping
    public String home(){
        return "index";
    }

    @GetMapping("login")
    public String login(){

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new ApplicationUser());
        return "register";

    }

    @PostMapping("/register")
    public String register(Model model, ApplicationUser applicationUser){
        applicationUserService.addUser(applicationUser);
        return "redirect:/";

    }



    @GetMapping("/token")
    public String singup(@RequestParam String value){
        Token byValue = tokenRepository.findByValue(value);
        ApplicationUser applicationUser = byValue.getApplicationUser();
        applicationUser.setEnabled(true);
        applicationUser.setAccountNonExpired(true);
        applicationUser.setAccountNonLocked(true);
        applicationUser.setCredentialsNonExpired(true);
        applicationUserRepository.save(applicationUser);


        return "redirect:/";
    }



}
