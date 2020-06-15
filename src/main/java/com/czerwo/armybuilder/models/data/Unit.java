package com.czerwo.armybuilder.models.data;

import com.czerwo.armybuilder.models.data.options.GroupOfOptions;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min=5, message = "Army name must be at least 5 characters long")
    private String name;

    @Max(1000)
    @Min(3)
    private int baseCost;

    @Max(1000)
    @Min(3)
    private int additionalModelsCost;

    @Max(100)
    @Min(1)
    private int minSizeOfUnit;

    @Max(100)
    @Min(1)
    private int maxSizeOfUnit;

    @ManyToOne
    Army army;

    @OneToOne(cascade = {CascadeType.ALL})
    UnitDetails unitDetails;

    @OneToMany
    @JoinColumn(name = "unit_id")
    List<OrderedUnit> orderedUnits = new ArrayList<>();

    @ManyToMany(mappedBy = "units", cascade = CascadeType.ALL)
    List<GroupOfOptions> groupsOfOptions = new ArrayList<>();

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

    public List<GroupOfOptions> getGroupsOfOptions() {
        return groupsOfOptions;
    }

    public void setGroupsOfOptions(List<GroupOfOptions> groupsOfOptions) {
        this.groupsOfOptions = groupsOfOptions;
    }

    public void addGroupOfOptions(GroupOfOptions groupOfOptions) {
        groupsOfOptions.add(groupOfOptions);
    }

    public void removeGroup(GroupOfOptions groupOfOptions) {
        groupsOfOptions.remove(groupOfOptions);
    }
}
