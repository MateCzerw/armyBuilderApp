package com.czerwo.armybuilder.models.data.options;

import com.czerwo.armybuilder.models.data.Unit;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GroupOfOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min=3, message = "Group name must be at least 3 characters long")
    private String name;

    public GroupOfOptions() {
    }

    @ManyToMany(mappedBy = "groupsOfOptions", cascade = CascadeType.ALL)
    private List<Option> options = new ArrayList<>();

    @ManyToMany
    private List<Unit> units = new ArrayList<>();


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

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public void addOption(Option option) {
        options.add(option);
    }


    public void removeOption(Option option) {
        options.remove(option);
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void removeFromUnit(Unit unit) {
        units.remove(unit);
    }
}
