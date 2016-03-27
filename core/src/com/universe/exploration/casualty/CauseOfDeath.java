/**
 * 
 */
package com.universe.exploration.casualty;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.crewmember.attribute.CrewMemberAttribute;
import com.universe.exploration.crewmember.attribute.Strength;


/**
 * <p>
 * A cause of deaath of a crewman.
 * </p>
 * TODO: Perhaps KIAs could be configured in database and in code they would be used via beans?
 * @author 25.10.2015 Teemu Puurunen 
 *
 */
public enum CauseOfDeath {
	// No oxygen on the planet
	HELMET_BREACH("HELMET_BREACH", CauseOfDeathCategory.LACK_OF_OXYGEN),
	RIP_ON_SUIT("RIP_ON_SUIT", CauseOfDeathCategory.LACK_OF_OXYGEN),
	
	// Animals present
	ATTACKED_BY_ANIMALS("ATTACKED_BY_ANIMALS", CauseOfDeathCategory.ANIMAL),
	
	// Civilization present
	KILLED_BY_LOCALS("KIA_BY_LOCALS", CauseOfDeathCategory.CIVILIZATION),
	SEDUCED_BY_AMAZONS("SEDUCED_BY_AMAZONS", CauseOfDeathCategory.CIVILIZATION),
	
	// General
	FELL_OFF_CLIFF("FELL_OFF_CLIFF", CauseOfDeathCategory.GENERAL) {
	    /* (non-Javadoc)
	     * @see com.universe.exploration.casualty.CauseOfDeath#listOfContributingAttributes()
	     */
	    @Override
	    public List<CrewMemberAttribute> listOfContributingAttributes() {
	        List<CrewMemberAttribute> attributes = new ArrayList<CrewMemberAttribute>();
	        attributes.add(new Strength(0));
	        
	        return attributes;
	    }

	},
	MALNUTRION("MALNUTRITION", CauseOfDeathCategory.GENERAL),
	CREWMEN_WENT_NUTS("CREWMEN_WENT_NUTS", CauseOfDeathCategory.GENERAL),
	WATER_DEPRIVATION("WATER_DEPRIVATION", CauseOfDeathCategory.GENERAL);
	
	private String localizationKey;
	
	private CauseOfDeathCategory causeOfDeathCategory;
	
	private CauseOfDeath(String localizationKey, CauseOfDeathCategory causeOfDeathCategory) {
		this.localizationKey = localizationKey;
		this.causeOfDeathCategory = causeOfDeathCategory;
	}
	
	public List<CrewMemberAttribute> listOfContributingAttributes() {
	    return null;
	}
	
	/**
	 * @return the localizationKey
	 */
	public String getLocalizationKey() {
		return localizationKey;
	}

	/**
	 * @param localizationKey the localizationKey to set
	 */
	public void setLocalizationKey(String localizationKey) {
		this.localizationKey = localizationKey;
	}
	/**
	 * @return the causeOfDeathCategory
	 */
	public CauseOfDeathCategory getCauseOfDeathCategory() {
		return causeOfDeathCategory;
	}
	/**
	 * @param causeOfDeathCategory the causeOfDeathCategory to set
	 */
	public void setCauseOfDeathCategory(CauseOfDeathCategory causeOfDeathCategory) {
		this.causeOfDeathCategory = causeOfDeathCategory;
	}
}
