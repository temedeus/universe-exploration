/**
 * 
 */
package com.universe.exploration.starsystem.components;

import com.universe.exploration.common.tools.StarsystemComponentType;

/**
 * @author 7.7.2015 Teemu Puurunen 
 *
 */
public class StarSystemComponent implements IStarSystemComponent {

	/**
	 * componentName
	 */
	protected StarsystemComponentType componentType;

	/**
	 * @return the componentName
	 */
	public StarsystemComponentType getcomponentType() {
		return this.componentType;
	}

	/**
	 * @param componentName the componentName to set
	 */
	public void setcomponentType(StarsystemComponentType componentType) {
		this.componentType = componentType;
	}
}
