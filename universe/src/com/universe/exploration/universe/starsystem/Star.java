/**
 * 
 */
package com.universe.exploration.universe.starsystem;

/**
 * 
 * Star
 * 
 * @author 6.6.2015 Teemu Puurunen 
 *
 */
public class Star {

	/**
	 * Reference to the graphic image
	 * @access private
	 */
	private String graphic;

	/**
	 * Solar mass
	 * @access private
	 */
	private double solarMass;
	
	/**
	 * Solar radius
	 * @access private
	 */
	private double solarRadius;
	
	/**
	 * Constructor for star.
	 * At least generate minimum values (equivalent to sun).
	 */
	public Star() {
		this.graphic = "star1.jpg";
		this.solarMass = 1.0;
		this.solarRadius = 1.0;
	}
	
	/**
	 * @return the graphic
	 */
	public String getGraphic() {
		return graphic;
	}

	/**
	 * @param graphic the graphic to set
	 */
	public void setGraphic(String graphic) {
		this.graphic = graphic;
	}

	/**
	 * @return the solarMass
	 */
	public double getSolarMass() {
		return solarMass;
	}

	/**
	 * @param solarMass the solarMass to set
	 */
	public void setSolarMass(double solarMass) {
		this.solarMass = solarMass;
	}

	/**
	 * @return the solarRadius
	 */
	public double getSolarRadius() {
		return solarRadius;
	}

	/**
	 * @param solarRadius the solarRadius to set
	 */
	public void setSolarRadius(double solarRadius) {
		this.solarRadius = solarRadius;
	}
}
