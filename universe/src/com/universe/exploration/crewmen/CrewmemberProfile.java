/**
 * 
 */
package com.universe.exploration.crewmen;

public class CrewmemberProfile {
    private CrewmemberSex sex;
    private Nationality nationality;
    private String name;

    public CrewmemberProfile(CrewmemberSex sex, Nationality nationality, String name) {
	this.sex = sex;
	this.name = name;
	this.nationality = nationality;
    }

    /**
     * @return the sex
     */
    public CrewmemberSex getSex() {
	return sex;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(CrewmemberSex sex) {
	this.sex = sex;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
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
