/**
 *
 */
package com.universe.exploration.model.crew.attribute;

/**
 * Abstract class for crew member attributes.
 *
 * @author 15.3.2016 Teemu Puurunen
 */
public abstract class CrewMemberAttribute implements ICrewMemberAttribute {
    protected int value;

    public CrewMemberAttribute(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
