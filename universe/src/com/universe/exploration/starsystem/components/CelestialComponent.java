/**
 * 
 */
package com.universe.exploration.starsystem.components;

/**
 * <p>
 * Any class extending CelestialComponent describes component properties,
 * maximum and minimum values, but NOT the current state of the astronomical
 * body!
 * </p>
 * 
 * <p>
 * You "install" one of these components into a model and from there on it acts
 * upon boundaries and settings defined in one of these classes.
 * </p>
 * 
 * @author 7.7.2015 Teemu Puurunen
 *
 */
public class CelestialComponent implements ICelestialComponent {
    protected int spriteSize;

    protected String graphicsFile;

    /**
     * @param componentName
     *            the componentName to set
     */
    public void setComponentName(String componentName) {
	this.componentName = componentName;
    }

    protected String componentName;

    /**
     * Initial angle
     */
    private double angle;

    /**
     * @return the angle
     */
    public double getAngle() {
	return angle;
    }

    /**
     * @param angle
     *            the angle to set
     */
    public void setAngle(double angle) {
	this.angle = angle;
    }

    /**
     * If not overridden, return false. This is pretty much component specific,
     * but each component should have one.
     * 
     * @return
     */
    public boolean configure() {
	return false;
    }

    /**
     * Calculates sprite size based on defined "real" spacial values. Must be
     * overridden in each file that extends this class.
     */
    public void setSpriteSize(int spriteSize) {
	this.spriteSize = spriteSize * 3;
    }

    /**
     * Calculates sprite size based on defined "real" spacial values. Must be
     * overridden in each file that extends this class.
     */
    public int getSpriteSize() {
	return spriteSize;
    }

    /**
     * @return the graphicsFile
     */
    public String getGraphicsFile() {
	return graphicsFile;
    }

    /**
     * @param graphicsFile
     *            the graphicsFile to set
     */
    public void setGraphicsFile(String graphicsFile) {
	this.graphicsFile = graphicsFile;
    }

    /**
     * @return the componentName
     */
    public String getComponentName() {
	return componentName;
    }
}
