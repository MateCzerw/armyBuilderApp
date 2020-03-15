package com.czerwo.armybuilder.models.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int baseCost;

    private int additionalModelsCost;

    private int minSizeOfUnit;

    private int maxSizeOfUnit;

    @ManyToOne
    Army army;

    @OneToOne
    UnitDetails unitDetails;

    @OneToMany
    @JoinColumn(name = "unit_id")
    List<OrderedUnit> orderedUnits = new ArrayList<>();

    public Unit() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<OrderedUnit> getOrderedUnits() {
        return orderedUnits;
    }

    public void setOrderedUnits(List<OrderedUnit> orderedUnits) {
        this.orderedUnits = orderedUnits;
    }

    public int getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(int baseCost) {
        this.baseCost = baseCost;
    }

    public int getAdditionalModelsCost() {
        return additionalModelsCost;
    }

    public void setAdditionalModelsCost(int additionalModelsCost) {
        this.additionalModelsCost = additionalModelsCost;
    }

    public int getMinSizeOfUnit() {
        return minSizeOfUnit;
    }

    public void setMinSizeOfUnit(int minSizeOfUnit) {
        this.minSizeOfUnit = minSizeOfUnit;
    }

    public int getMaxSizeOfUnit() {
        return maxSizeOfUnit;
    }

    public void setMaxSizeOfUnit(int maxSizeOfUnit) {
        this.maxSizeOfUnit = maxSizeOfUnit;
    }
}
