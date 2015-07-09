/**
 * 
 */
package com.universe.exploration.starsystem.components;

/**
 * @author 7.7.2015 Teemu Puurunen 
 *
 */
public class StarSystemComponent implements IStarSystemComponent {

	/**
	 * Graphics filename
	 */
	protected String graphicsFile;

	/**
	 * @return the graphicsFile
	 */
	public String getGraphicsFile() {
		return graphicsFile;
	}

	/**
	 * @param graphicsFile the graphicsFile to set
	 */
	public void setGraphicsFile(String graphicsFile) {
		this.graphicsFile = graphicsFile;
	}
}
