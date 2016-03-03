/**
 * 
 */
package com.universe.exploration.crewmen;

/**
 * Crewman bean.
 * 
 * @author 25.8.2015 Teemu Puurunen
 *
 */
public class Crewman {
    private String name;

    private int age;

    private CrewmemberSex sex;

    private int morale;

    private int intelligence;

    private int strength;

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
    public int getMorale() {
	return morale;
    }

    /**
     * @return the intelligence
     */
    public int getIntelligence() {
	return intelligence;
    }

    /**
     * @return the strength
     */
    public int getStrength() {
	return strength;
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
	this.morale = morale;
    }

    /**
     * @param intelligence
     *            the intelligence to set
     */
    public void setIntelligence(int intelligence) {
	this.intelligence = intelligence;
    }

    /**
     * @param strength
     *            the strength to set
     */
    public void setStrength(int strength) {
	this.strength = strength;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Crewman [name=" + name + ", age=" + age + ", sex=" + sex + ", morale=" + morale + ", intelligence=" + intelligence
		+ ", strength=" + strength + "]";
    }
}
