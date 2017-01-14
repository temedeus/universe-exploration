/**
 * 
 */
package com.universe.exploration.resource;

import java.util.List;

/**
 * Describes the resources that were found during the survey mission.
 * 
 * @author 25.10.2015 Teemu Puurunen
 *
 */
public class ResourcesFoundBean {
    private List<Resource> resources;

    /**
     * @return the resources
     */
    public List<Resource> getResources() {
	return resources;
    }

    /**
     * @param resources
     *            the resources to set
     */
    public void addToResources(Resource resource) {
	this.resources.add(resource);
    }

}
