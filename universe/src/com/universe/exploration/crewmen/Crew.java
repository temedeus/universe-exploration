/**
 * 
 */
package com.universe.exploration.crewmen;

import java.util.List;

public class Crew {

    private List<Crewman> crewmen;

    public void addCrewman(Crewman crewman) {
	crewmen.add(crewman);
    }

    public void removeCrewman(Crewman c) {
	crewmen.remove(c);
    }
}
