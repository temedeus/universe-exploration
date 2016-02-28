/**
 * 
 */
package com.universe.exploration.crewmen;

/**
 * @author 21.2.2016 Teemu Puurunen
 *
 */
public class Name {
    private CrewmemberSex sex;
    private String name;

    public Name(CrewmemberSex sex, String name) {
	this.sex = sex;
	this.name = name;
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
}
