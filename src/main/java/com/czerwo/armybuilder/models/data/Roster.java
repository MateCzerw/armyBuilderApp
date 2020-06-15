package com.czerwo.armybuilder.models.data;

import com.czerwo.armybuilder.auth.ApplicationUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Roster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 4, max = 15, message = "Correct length of name is 4-:-15")
    private String name;

    @ManyToOne
    private Army army;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "roster_id")
    List<OrderedUnit> orderedUnits = new ArrayList<>();

    @ManyToOne
    private ApplicationUser applicationUser;

    public Roster() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public List<OrderedUnit> getOrderedUnits() {
        return orderedUnits;
    }

    public void setOrderedUnits(List<OrderedUnit> orderedUnits) {
        this.orderedUnits = orderedUnits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addUnit(OrderedUnit orderedUnit) {
        orderedUnits.add(orderedUnit);
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public void removeOrderedUnits() {

    orderedUnits.clear();

    }

    public void removeOrderedUnit(OrderedUnit orderedUnit) {
        orderedUnits.remove(orderedUnit);
    }
}
