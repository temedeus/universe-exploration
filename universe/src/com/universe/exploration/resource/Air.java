/**
 * 
 */
package com.universe.exploration.resource;

/**
 * @author 13.1.2017 Teemu Puurunen
 *
 */
public class Air extends Resource {

    /*
     * (non-Javadoc)
     * 
     * @see com.universe.exploration.resource.IResource#getResourceLocal()
     */
    @Override
    public String getResourceLocal() {
	return "GENERIC_AIR";
    }

    /* (non-Javadoc)
     * @see com.universe.exploration.resource.IResource#getBoundary()
     */
    @Override
    public ResourcesFoundBoundaries getBoundary() {
	return ResourcesFoundBoundaries.AIR;
    }

}
