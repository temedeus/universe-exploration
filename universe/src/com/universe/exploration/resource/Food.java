/**
 * 
 */
package com.universe.exploration.resource;

/**
 * @author 13.1.2017 Teemu Puurunen
 *
 */
public class Food extends Resource {

    /*
     * (non-Javadoc)
     * 
     * @see com.universe.exploration.resource.IResource#getResourceLocal()
     */
    @Override
    public String getResourceLocal() {
	return "GENERIC_FOOD";
    }

    /* (non-Javadoc)
     * @see com.universe.exploration.resource.IResource#getBoundary()
     */
    @Override
    public ResourcesFoundBoundary getResourcesFoundBoundary() {
	return ResourcesFoundBoundary.FOOD;
    }

}
