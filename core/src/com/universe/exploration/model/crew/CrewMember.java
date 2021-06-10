package com.universe.exploration.model.crew;

import com.universe.exploration.model.crew.attribute.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CrewMember {
    private int id;

    private String name;

    private int age;

    private float health;

    private CrewmemberSex sex;

    private Map<Class <? extends CrewMemberAttribute>, CrewMemberAttribute> crewMemberAttributes = new HashMap<>();

    private CrewMemberStatus status;

    private Set<CrewMemberCondition> condition = new HashSet<>();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public CrewmemberSex getSex() {
        return sex;
    }

    public Morale getMorale() {
        return ((Morale) crewMemberAttributes.get(Morale.class));
    }

    public Intelligence getIntelligence() {
        return ((Intelligence) crewMemberAttributes.get(Intelligence.class));
    }

    public Strength getStrength() {
        return ((Strength) crewMemberAttributes.get(Strength.class));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(CrewmemberSex sex) {
        this.sex = sex;
    }

    public void setMorale(int morale) {
        crewMemberAttributes.put(Morale.class, new Morale(morale));
    }

    public void setIntelligence(int intelligence) {
        crewMemberAttributes.put(Intelligence.class, new Intelligence(intelligence));
    }

    public void setStrength(int strength) {
        crewMemberAttributes.put(Strength.class, new Strength(strength));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Agility getAgility() {
        return ((Agility) crewMemberAttributes.get(Agility.class));
    }

    public void setAgility(int agility) {
        crewMemberAttributes.put(Agility.class, new Agility(agility));
    }

    public CrewMemberStatus getStatus() {
        return status;
    }

    public void setStatus(CrewMemberStatus status) {
        this.status = status;
    }

    public Set<CrewMemberCondition> getCondition() {
        return condition;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public Map<Class <? extends CrewMemberAttribute>, CrewMemberAttribute> getCrewMemberAttributes() {
        return crewMemberAttributes;
    }
}
