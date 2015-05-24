package com.universe.exploration.universe;

import com.universe.exploration.Configuration;
import com.universe.exploration.GeneralTools;

public class UniverseFactory {
	Universe universe;
	
	public Universe getUniverse() {
		return universe;
	}

	public void setUniverse(Universe universe) {
		this.universe = universe;
	}

	/**
	 * Universe factory constructor
	 */
	public UniverseFactory() {
		this.universe = new Universe();
		this.universe.setStarType(GeneralTools.weightedRandom(Configuration.startypeListing));
	}
}
