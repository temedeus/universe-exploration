/**
 * 
 */
package com.universe.exploration.crewmen;

import java.util.List;

import com.universe.exploration.common.CoreConfiguration;
import com.universe.exploration.common.tools.RandomizationTools;

/**
 * Before utilizing this factory you must first populate the
 * {@link #maleProfiles} using {@link #addToNames(CrewmemberSex, String)}.
 * Otherwise you will get zero crewmen.
 * 
 * @author 21.2.2016 Teemu Puurunen
 *
 */
public class CrewFactory {

    private List<CrewmemberProfile> maleProfiles;
    private List<CrewmemberProfile> femaleProfiles;

    public CrewFactory(List<CrewmemberProfile> maleProfiles, List<CrewmemberProfile> femaleProfiles) {
	this.maleProfiles = maleProfiles;
	this.femaleProfiles = femaleProfiles;
    }

    public Crew createRandomizedCrew() {
	Crew crew = new Crew();
	for (int x = 0; x < CoreConfiguration.MAX_CREWMEN; x++) {
	    crew.addCrewman(createRandomCrewman());
	}
	return crew;
    }

    private Crewman createRandomCrewman() {
	boolean male = RandomizationTools.randomBoolean();
	List<CrewmemberProfile> selectedSexList = (male) ? maleProfiles : femaleProfiles;
	
	int random = RandomizationTools.getRandomInteger(0, selectedSexList.size());
	CrewmemberProfile profile = selectedSexList.get(random);
	Crewman crewman = new Crewman();
	crewman.setSex(profile.getSex());
	crewman.setName(profile.getName());
	return crewman;
    }

    /**
     * @param maleProfiles
     *            the maleProfiles to set
     */
    public void setMaleProfiles(List<CrewmemberProfile> maleProfiles) {
	this.maleProfiles = maleProfiles;
    }

    /**
     * @param femaleProfiles
     *            the femaleProfiles to set
     */
    public void setFemaleProfiles(List<CrewmemberProfile> femaleProfiles) {
	this.femaleProfiles = femaleProfiles;
    }
}
