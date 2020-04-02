package com.czerwo.armybuilder.models.data.options;

import javax.persistence.*;

@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private GroupOfOptions groupOfOptions;

    private String name;

    public Option() {
    }
}
