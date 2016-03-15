/**
 * 
 */
package com.universe.exploration.crewmember.attribute;

/**
 * @author 15.3.2016 Teemu Puurunen
 *
 */
public abstract class CrewMemberAttribute implements ICrewMemberAttribute {
    int value;

    /**
     * 
     */
    public CrewMemberAttribute(int value) {
	this.value = value;
    }

    public void setValue(int value) {
	this.value = value;
    }
}
