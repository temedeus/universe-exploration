package com.universe.exploration.crewmember;

import java.util.HashMap;

import com.universe.exploration.crewmember.attribute.Agility;
import com.universe.exploration.crewmember.attribute.CrewMemberAttribute;
import com.universe.exploration.crewmember.attribute.Intelligence;
import com.universe.exploration.crewmember.attribute.Morale;
import com.universe.exploration.crewmember.attribute.Strength;

public class CrewMember {
    private int id;

    private String name;

    private int age;

    private CrewmemberSex sex;

    private Nationality nationality;

    private HashMap<String, CrewMemberAttribute> crewMemberAttributes = new HashMap<String, CrewMemberAttribute>();

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @return the age
     */
    public int getAge() {
	return age;
    }

    /**
     * @return the sex
     */
    public CrewmemberSex getSex() {
	return sex;
    }

    /**
     * @return the morale
     */
    public Morale getMorale() {
	return ((Morale) crewMemberAttributes.get(Morale.class.getName()));
    }

    /**
     * @return the intelligence
     */
    public Intelligence getIntelligence() {
	return ((Intelligence) crewMemberAttributes.get(Intelligence.class.getName()));
    }

    /**
     * @return the strength
     */
    public Strength getStrength() {
	return ((Strength) crewMemberAttributes.get(Strength.class.getName()));
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @param age
     *            the age to set
     */
    public void setAge(int age) {
	this.age = age;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(CrewmemberSex sex) {
	this.sex = sex;
    }

    /**
     * @param morale
     *            the morale to set
     */
    public void setMorale(int morale) {
	crewMemberAttributes.put(Morale.class.getName(), new Morale(morale));
    }

    /**
     * @param intelligence
     *            the intelligence to set
     */
    public void setIntelligence(int intelligence) {
	crewMemberAttributes.put(Intelligence.class.getName(), new Intelligence(intelligence));
    }

    /**
     * @param strength
     *            the strength to set
     */
    public void setStrength(int strength) {
	crewMemberAttributes.put(Strength.class.getName(), new Strength(strength));
    }

    /**
     * @return the id
     */
    public int getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
	this.id = id;
    }

    /**
     * @return the agility
     */
    public Agility getAgility() {
	return ((Agility) crewMemberAttributes.get(Agility.class.getName()));
    }

    /**
     * @param agility
     *            the agility to set
     */
    public void setAgility(int agility) {
	crewMemberAttributes.put(Agility.class.getName(), new Agility(agility));
    }

    /**
     * @return the nationality
     */
    public Nationality getNationality() {
	return nationality;
    }

    /**
     * @param nationality
     *            the nationality to set
     */
    public void setNationality(Nationality nationality) {
	this.nationality = nationality;
    }
}
