/**
 * 
 */
package com.universe.exploration.crewmember;

import java.util.List;

import com.universe.exploration.common.CoreConfiguration;
import com.universe.exploration.common.tools.RandomizationTools;
import com.universe.exploration.crew.CrewMemberStatus;

/**
 * Before utilizing this factory you must first populate {@link #maleProfiles}
 * and {@link #femaleProfiles} using {@link #addToNames(CrewmemberSex, String)}.
 * Otherwise you will get zero crewmen.
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
	    crew.addCrewman(createRandomCrewman(x));
	}
	return crew;
    }

    private CrewMember createRandomCrewman(int id) {
	boolean male = RandomizationTools.randomBoolean();
	List<CrewmemberProfile> selectedSexList = (male) ? maleProfiles : femaleProfiles;

	int random = RandomizationTools.getRandomInteger(0, selectedSexList.size() - 1);
	CrewmemberProfile profile = selectedSexList.get(random);

	CrewMember crewmember = new CrewMember();

	// Basic setup
	crewmember.setId(id);
	crewmember.setSex(profile.getSex());
	crewmember.setName(profile.getName());
	crewmember
		.setAge(RandomizationTools.getRandomInteger(CrewCharacteristicsBoundaries.MIN_AGE, CrewCharacteristicsBoundaries.MAX_AGE));
	crewmember.setNationality(profile.getNationality());
	crewmember.setStatus(CrewMemberStatus.ALIVE);
	crewmember.setHealth(100);

	// Crewman characteristics
	setupCrewmanCharacteristics(crewmember);

	return crewmember;
    }

    private void setupCrewmanCharacteristics(CrewMember crewmember) {
	crewmember.setAgility(RandomizationTools.getRandomInteger(CrewCharacteristicsBoundaries.MIN_AGILITY,
		CrewCharacteristicsBoundaries.MAX_AGILITY));

	crewmember.setMorale(RandomizationTools.getRandomInteger(CrewCharacteristicsBoundaries.MIN_INITIAL_MORALE,
		CrewCharacteristicsBoundaries.MAX_MORALE));

	crewmember.setIntelligence(RandomizationTools.getRandomInteger(CrewCharacteristicsBoundaries.MIN_INTELLIGENCE,
		CrewCharacteristicsBoundaries.MAX_INTELLIGENCE));

	crewmember.setStrength(RandomizationTools.getRandomInteger(CrewCharacteristicsBoundaries.MIN_STRENGTH,
		CrewCharacteristicsBoundaries.MAX_STRENGTH));
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
