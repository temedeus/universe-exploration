/**
 * 
 */
package com.universe.exploration.userinterface.components;

/**
 * Generic UI components.
 * 
 * @author 1.2.2016 Teemu Puurunen
 *
 */
public class GenericComponents {

    private static GenericComponents genericComponents;

    private GenericComponents() {
	// Ensure this class is instantiated once.
    }

    /**
     * Singleton for {@link GenericComponents).
     * 
     * @return
     */
    public static GenericComponents getInstance() {
	return (genericComponents == null) ? instantiate() : genericComponents;
    }

    private static GenericComponents instantiate() {
	genericComponents = new GenericComponents();
	return genericComponents;
    }

    /**
     * Creates a spacer element.
     * 
     * @return {@link Spacer}
     */
    public UELabel createSpacer() {
	return new Spacer();
    }
}
