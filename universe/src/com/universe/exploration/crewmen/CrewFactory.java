/**
 * 
 */
package com.universe.exploration.crewmen;

import java.util.List;

import com.universe.exploration.common.CoreConfiguration;
import com.universe.exploration.common.tools.RandomizationTools;

/**
 * Before utilizing this factory you must first populate the {@link #names}
 * using {@link #addToNames(CrewmemberSex, String)}. Otherwise you will get zero crewmen.
 * 
 * @author 21.2.2016 Teemu Puurunen
 *
 */
public class CrewFactory {

    private List<CrewmemberProfile> names;

    public CrewFactory(List<CrewmemberProfile> names) {
	this.names = names;
    }

    public Crew createRandomizedCrew() {
	Crew crew = new Crew();
	for (int x = 0; x < CoreConfiguration.MAX_CREWMEN; x++) {
	    crew.addCrewman(createRandomCrewman());
	}
	return crew;
    }

    private Crewman createRandomCrewman() {
	int random = RandomizationTools.getRandomInteger(0, names.size());
	CrewmemberProfile name = names.get(random);
	Crewman crewman = new Crewman();
	crewman.setSex(name.getSex());
	crewman.setName(name.getName());
	return new Crewman();
    }

    public void addToNames(CrewmemberSex sex, Nationality nationality, String name) {
	names.add(new CrewmemberProfile(sex, nationality, name));
    }

    /**
     * @return the names
     */
    public List<CrewmemberProfile> getNames() {
	return names;
    }
}
