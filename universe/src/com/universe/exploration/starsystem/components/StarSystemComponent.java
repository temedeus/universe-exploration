/**
 * 
 */
package com.universe.exploration.starsystem.components;

import com.universe.exploration.common.tools.StarsystemComponentTypes;

/**
 * <p>Any class extending StarSystemComponent describes component properties,
 * maximum and minimum values, but NOT the current state of the astronomical body!</p>
 * 
 * <p>You "install" one of these components into a model and from there on
 * it acts upon boundaries and settings defined in one of these classes.</p>
 * 
 * @author 7.7.2015 Teemu Puurunen 
 *
 */
public class StarSystemComponent implements IStarSystemComponent {

	/**
	 * componentName
	 */
	protected StarsystemComponentTypes componentType;

	/**
	 * @return the componentName
	 */
	public StarsystemComponentTypes getcomponentType() {
		return this.componentType;
	}

	/**
	 * @param componentName the componentName to set
	 */
	public void setcomponentType(StarsystemComponentTypes componentType) {
		this.componentType = componentType;
	}
	
	/**
	 * If not overridden, return false. This is pretty much component specific, but each
	 * component should have one.
	 * @return
	 */
	public boolean configure() {
		return false;
	}
	
	/**
	 * Calculates sprite size based on defined "real" spacial values.
	 * Must be overridden in each file that extends this class.
	 */
	public int getSpriteSize() {
		return 64; // Return at least something
	}
}
