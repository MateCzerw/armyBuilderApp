package com.czerwo.armybuilder.models.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Roster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    private Army army;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "roster_id")
    List<OrderedUnit> orderedUnits = new ArrayList<>();

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
}
