/**
 *
 */
package com.universe.exploration.resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes the resources that were found during the survey mission.
 *
 * @author 25.10.2015 Teemu Puurunen
 */
public class ResourcesFoundBean {
    private List<Resource> resources;

    public ResourcesFoundBean() {
        resources = new ArrayList<Resource>();
    }

    /**
     * @return the resources
     */
    public List<Resource> getResources() {
        return resources;
    }

    /**
     * @param resources the resources to set
     */
    public void addToResources(Resource resource) {
        this.resources.add(resource);
    }

}
