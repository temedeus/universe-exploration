package com.universe.exploration.universe;

import com.universe.exploration.Configuration;
import com.universe.exploration.GeneralTools;

public class BackgroundAssembler {
	/**
	 * Store star positions in an array.
	 */
	private int[][] starPositions;
	
	/**
	 * Counter for fetching star positions.
	 */
	private int starMapCounter = 0;
	
	/**
	 * Star count per tile. (Randomly generated per each tile.)
	 */
	private int starCount = 0;
	
	/**
	 * Constructor for background assembler. Generates star positions and stores them in an array.
	 */
	public BackgroundAssembler() {
		// On construct ensure star map counter is zero and generate star count based on configured boundaries.
		starMapCounter = 0;
		this.starCount = GeneralTools.getRandomInteger(1,2);
		
		this.starPositions = new int[this.starCount][2];

		// Populate star positions array
		for(int x = 0; x < this.starCount; x++) {
			this.starPositions[x][0] = GeneralTools.getRandomInteger(0, Configuration.bgSize);
			this.starPositions[x][1] = GeneralTools.getRandomInteger(0, Configuration.bgSize);
		}
	}
	
	/**
	 * Return star position and increment counter
	 * @return
	 */
	public int[] fetchStarPosition() {
		int[] starPosition = this.starPositions[this.starMapCounter];
		this.starMapCounter++;
		
		return starPosition;
	}
	
	/**
	 * Restart counter if for some reason we wish to draw the identical star map again.
	 */
	public void restartCounter() {
		this.starMapCounter = 0;
	}
	
	/**
	 * Get star count per current tile
	 * @return integer star count
	 */
	public int getStarCount() {
		return this.starCount;
	}
}
