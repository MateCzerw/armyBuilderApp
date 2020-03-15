package com.czerwo.armybuilder.models.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UnitDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int advanceRate;

    private int marchRate;

    private int discipline;

    private int healthPoints;

    private int defensiveSkill;

    private int resilience;

    private int armour;

    private int agility;

    private int attackValue;

    private int offensiveSkill;

    private int strength;

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
