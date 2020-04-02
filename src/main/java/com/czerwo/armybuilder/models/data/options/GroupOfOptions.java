package com.czerwo.armybuilder.models.data.options;

import com.czerwo.armybuilder.models.data.Unit;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GroupOfOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public GroupOfOptions() {
    }

    @OneToMany(mappedBy = "groupOfOptions")
    private List<Option> options = new ArrayList<>();

    @ManyToMany(mappedBy = "groupOfOptions")
    private List<Unit> units = new ArrayList<>();

}
