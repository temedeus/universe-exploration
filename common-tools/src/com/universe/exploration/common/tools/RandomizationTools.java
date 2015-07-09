package com.universe.exploration.common.tools;

/**
 * RandomTools
 *  
 * Generic randomization tools.
 * 
 * @author 6.6.2015 Teemu Puurunen 
 *
 */
public class RandomizationTools {
	/**
	 * Generate random integer based on upper and lower limits.
	 * @param integer lower
	 * @param integer upper
	 * @return integer
	 */
	public static final int getRandomInteger(int lower, int upper) {
		//Random rand = new Random();
		int randomNumber =  (int)(Math.random() * (upper - lower + 1)) + lower;
		
		return randomNumber;
	}
	
	/**
	 * Get random string from a weighted array
	 * @param string[][] array of values and weights e.g ({{"apple", "3"},{"banana", "6"}})
	 * @return string
	 */
	public static final String getStringFromWeightedRandomArray(String[][]sourceForRandomization) {

		// Compute the total weight of all items together
		double tw = 0.0d;
		for (int x = 0; x < sourceForRandomization.length; x++) {
			tw += Double.parseDouble(sourceForRandomization[x][1]);
		}

		double random = Math.random() * tw;
		for (int i = 0; i < sourceForRandomization.length; ++i)
		{
		    random -= (Double.parseDouble(sourceForRandomization[i][1]));
		    if (random <= 0.0d)
		    {
		        return sourceForRandomization[i][0];
		    }
		}
		
		return sourceForRandomization[0][0];
	}
	
	/**
	 * Get weighted random array index.
	 * 
	 * @param weights
	 * @return
	 */
	public static final int getWeightedRandomArrayIndex(int[] weights) {
		int index = 0;
		return index;
	}
}

