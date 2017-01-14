/**
 * 
 */
package com.universe.exploration.resource;

import com.universe.exploration.survey.Survey;

/**
 * Describes a resource that can be found on a {@link Survey}.
 * 
 * @author 13.1.2017 Teemu Puurunen
 *
 */
public abstract class Resource implements IResource {
    protected float amount;

    protected ResourcesFoundBoundaries boundary;

    /**
     * @return the amount
     */
    public float getAmount() {
	return amount;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(float amount) {
	this.amount = amount;
    }

    /**
     * @return the boundary
     */
    public ResourcesFoundBoundaries getBoundary() {
	return boundary;
    }

    /**
     * @param boundary
     *            the boundary to set
     */
    public void setBoundary(ResourcesFoundBoundaries boundary) {
	this.boundary = boundary;
    }
}
