package com.czerwo.armybuilder.models.data;


import com.czerwo.armybuilder.models.data.Validation.NumberOfModels.NumberOfModelsMatch;
import com.czerwo.armybuilder.models.data.options.Option;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
//@NumberOfModelsMatch
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

    @ManyToMany
    private List<Option> choosenOptions = new ArrayList<>();

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

    public List<Option> getChoosenOptions() {
        return choosenOptions;
    }

    public void setChoosenOptions(List<Option> choosenOptions) {
        this.choosenOptions = choosenOptions;
    }

    public void removeAllOptions() {
        choosenOptions.clear();
    }

    public void addChosenOption(Option option) {
        choosenOptions.add(option);
    }
}
