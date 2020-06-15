package com.czerwo.armybuilder.models.data;

import com.czerwo.armybuilder.auth.ApplicationUser;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    private ApplicationUser host;

    //private List<ApplicationUser> players = new ArrayList<>();

    //@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date time;

    @ManyToMany
    private List<ApplicationUser> participants = new ArrayList<>();

    public Tournament() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApplicationUser getHost() {
        return host;
    }

    public void setHost(ApplicationUser host) {
        this.host = host;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void addParticipant(ApplicationUser user) {
        participants.add(user);
    }

    public void removeParticipant(ApplicationUser user) {
        participants.remove(user);
    }

    public List<ApplicationUser> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ApplicationUser> participants) {
        this.participants = participants;
    }
}
