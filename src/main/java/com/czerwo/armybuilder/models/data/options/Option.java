package com.czerwo.armybuilder.models.data.options;

import com.czerwo.armybuilder.models.data.OrderedUnit;
import com.czerwo.armybuilder.models.data.Unit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ooption")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min=3, message = "Option name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min=3, message = "Option description be at least 3 characters long")
    private String description;

    @ManyToMany
    private List<GroupOfOptions> groupsOfOptions = new ArrayList<>();

    @ManyToMany(mappedBy = "choosenOptions", cascade = CascadeType.ALL)
    private List<OrderedUnit> orderedUnits = new ArrayList<>();

    public Option() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void removeFromGroup(GroupOfOptions groupOfOptions) {
        groupsOfOptions.remove(groupOfOptions);
    }
}
