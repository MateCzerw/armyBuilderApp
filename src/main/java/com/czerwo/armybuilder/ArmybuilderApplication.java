package com.czerwo.armybuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.MailSender;

@SpringBootApplication
public class ArmybuilderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArmybuilderApplication.class, args);
    }



}
