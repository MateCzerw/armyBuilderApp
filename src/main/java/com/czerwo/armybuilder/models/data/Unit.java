package com.czerwo.armybuilder.models.data;

import javax.persistence.*;

@Entity
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int points;

    @ManyToOne
    Army army;

    @OneToOne
    UnitDetails unitDetails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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

    public UnitDetails getUnitDetails() {
        return unitDetails;
    }

    public void setUnitDetails(UnitDetails unitDetails) {
        this.unitDetails = unitDetails;
    }

    public Unit() {
    }
}
