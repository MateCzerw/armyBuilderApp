package com.czerwo.armybuilder.models.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class UnitDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Max(10)
    @Min(1)
    private int advanceRate;

    @Max(10)
    @Min(1)
    private int marchRate;

    @Max(10)
    @Min(1)
    private int discipline;

    @Max(10)
    @Min(1)
    private int healthPoints;

    @Max(10)
    @Min(1)
    private int defensiveSkill;

    @Max(10)
    @Min(1)
    private int resilience;

    @Max(6)
    @Min(0)
    private int armour;

    @Max(10)
    @Min(0)
    private int agility;

    @Max(10)
    @Min(1)
    private int attackValue;

    @Max(10)
    @Min(1)
    private int offensiveSkill;

    @Max(10)
    @Min(1)
    private int strength;

    @Max(10)
    @Min(0)
    private int armourPenetration;



    public UnitDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdvanceRate() {
        return advanceRate;
    }

    public void setAdvanceRate(int advanceRate) {
        this.advanceRate = advanceRate;
    }

    public int getMarchRate() {
        return marchRate;
    }

    public void setMarchRate(int marchRate) {
        this.marchRate = marchRate;
    }

    public int getDiscipline() {
        return discipline;
    }

    public void setDiscipline(int discipline) {
        this.discipline = discipline;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getDefensiveSkill() {
        return defensiveSkill;
    }

    public void setDefensiveSkill(int defensiveSkill) {
        this.defensiveSkill = defensiveSkill;
    }

    public int getResilience() {
        return resilience;
    }

    public void setResilience(int resilience) {
        this.resilience = resilience;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    public int getOffensiveSkill() {
        return offensiveSkill;
    }

    public void setOffensiveSkill(int offensiveSkill) {
        this.offensiveSkill = offensiveSkill;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getArmourPenetration() {
        return armourPenetration;
    }

    public void setArmourPenetration(int armourPenetration) {
        this.armourPenetration = armourPenetration;
    }


}
