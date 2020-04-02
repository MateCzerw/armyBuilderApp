package com.czerwo.armybuilder.models.data;


import com.czerwo.armybuilder.models.data.Validation.NumberOfModels.NumberOfModelsMatch;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@NumberOfModelsMatch
public class OrderedUnit {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    @NotNull
    private int numberOfModels;

    @ManyToOne
    private Unit unit;

    @ManyToOne
    private Roster roster;



    public OrderedUnit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfModels() {
        return numberOfModels;
    }

    public void setNumberOfModels(int numberOfModels) {
        this.numberOfModels = numberOfModels;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Roster getRoster() {
        return roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }
}
