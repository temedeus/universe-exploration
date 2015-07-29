/**
 * 
 */
package com.universe.exploration.starsystem.components;

import com.universe.exploration.common.tools.StarsystemComponentTypes;

/**
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
	 * If not overridden, return false. This is pretty much component specific.
	 * @return
	 */
	public boolean configure() {
		return false;
	}
}
