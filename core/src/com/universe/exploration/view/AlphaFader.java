/**
 * 
 */
package com.universe.exploration.view;

public class AlphaFader {
	private float alphaMin;
	
	private float alphaMax;
	
	private float decrementRate;
	
	private float incrementRate;
	
	private float alphaValue;
	
	private boolean alphaMinimized;
		
	/**
	 * 
	 */
	public AlphaFader(float alphaMin, float alphaMax, float decrementRate, float incrementRate) {
		this.alphaMin = alphaMin;
		this.alphaMax = alphaMax;
		this.decrementRate = decrementRate;
		this.incrementRate = incrementRate;
	}
	
	public float updateAlpha(boolean decreaseAlphaValue) {
		if (decreaseAlphaValue) {
			if (alphaValue > alphaMin) {
				alphaValue -= decrementRate;
			} else {
				alphaMinimized = true;
			}
		} else {
			if (alphaValue < alphaMax) {
				alphaValue += incrementRate;
			} else {
				alphaMinimized = false;
			}
		}
		
		return alphaValue;
	}

	/**
	 * @return the alphaMinimized
	 */
	public boolean isAlphaMinimized() {
		return alphaMinimized;
	}

	/**
	 * @return the alphaValue
	 */
	public float getAlphaValue() {
		return alphaValue;
	}

	/**
	 * @param alphaValue the alphaValue to set
	 */
	public void setAlphaValue(float alphaValue) {
		this.alphaValue = alphaValue;
	}
}