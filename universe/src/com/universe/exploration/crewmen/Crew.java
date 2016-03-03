/**
 * 
 */
package com.universe.exploration.crewmen;

import java.util.ArrayList;
import java.util.List;

public class Crew {

    private List<Crewman> crewmen = new ArrayList<Crewman>();

    public void addCrewman(Crewman crewman) {
	crewmen.add(crewman);
    }

    public void removeCrewman(Crewman c) {
	crewmen.remove(c);
    }

    /**
     * @return the crewmen
     */
    public List<Crewman> getCrewmen() {
        return crewmen;
    }
}
