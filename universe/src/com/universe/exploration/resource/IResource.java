/**
 *
 */
package com.universe.exploration.resource;

/**
 * Interface for all resources.
 *
 * @author 13.1.2017 Teemu Puurunen
 */
interface IResource {
    String getResourceLocal();

    ResourcesFoundBoundary getResourcesFoundBoundary();
}
