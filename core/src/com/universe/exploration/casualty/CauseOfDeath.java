/**
 * 
 */
package com.universe.exploration.casualty;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.crewmember.attribute.Agility;
import com.universe.exploration.crewmember.attribute.CrewMemberAttribute;
import com.universe.exploration.crewmember.attribute.Intelligence;
import com.universe.exploration.crewmember.attribute.Morale;
import com.universe.exploration.crewmember.attribute.Strength;

/**
 * <p>
 * A cause of death of a crewman.
 * </p>
 * <p>
 * Method {@link #listOfContributingAttributes()} defines which classes
 * extending abstract {@link CrewMemberAttribute} affect on chances of being killed by
 * given cause of death.
 * </p>
 * 
 * @author 25.10.2015 Teemu Puurunen
 *
 */
public enum CauseOfDeath {
    // No oxygen on the planet
    HELMET_BREACH("HELMET_BREACH", CauseOfDeathCategory.LACK_OF_OXYGEN),
    RIP_ON_SUIT("RIP_ON_SUIT", CauseOfDeathCategory.LACK_OF_OXYGEN),

    // Animals present
    ATTACKED_BY_ANIMALS("ATTACKED_BY_ANIMALS", CauseOfDeathCategory.ANIMAL) {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.universe.exploration.casualty.CauseOfDeath#
	 * listOfContributingAttributes()
	 */
	@Override
	public List<Class<? extends CrewMemberAttribute>> listOfContributingAttributes() {
	    List<Class<? extends CrewMemberAttribute>> listOfApplicableAttributes = new ArrayList<Class<? extends CrewMemberAttribute>>();
	    listOfApplicableAttributes.add(Strength.class);
	    return listOfContributingAttributes();
	}
    },

    // Civilization present
    KILLED_BY_LOCALS("KIA_BY_LOCALS", CauseOfDeathCategory.CIVILIZATION) {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.universe.exploration.casualty.CauseOfDeath#
	 * listOfContributingAttributes()
	 */
	@Override
	public List<Class<? extends CrewMemberAttribute>> listOfContributingAttributes() {
	    List<Class<? extends CrewMemberAttribute>> listOfApplicableAttributes = new ArrayList<Class<? extends CrewMemberAttribute>>();
	    listOfApplicableAttributes.add(Strength.class);
	    return listOfContributingAttributes();
	}
    },
    SEDUCED_BY_AMAZONS("SEDUCED_BY_AMAZONS", CauseOfDeathCategory.CIVILIZATION) {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.universe.exploration.casualty.CauseOfDeath#
	 * listOfContributingAttributes()
	 */
	@Override
	public List<Class<? extends CrewMemberAttribute>> listOfContributingAttributes() {
	    List<Class<? extends CrewMemberAttribute>> listOfApplicableAttributes = new ArrayList<Class<? extends CrewMemberAttribute>>();
	    listOfApplicableAttributes.add(Morale.class);
	    listOfApplicableAttributes.add(Intelligence.class);
	    return listOfContributingAttributes();
	}
    },

    // General
    FELL_OFF_CLIFF("FELL_OFF_CLIFF", CauseOfDeathCategory.GENERAL) {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.universe.exploration.casualty.CauseOfDeath#
	 * listOfContributingAttributes()
	 */
	@Override
	public List<Class<? extends CrewMemberAttribute>> listOfContributingAttributes() {
	    List<Class<? extends CrewMemberAttribute>> listOfApplicableAttributes = new ArrayList<Class<? extends CrewMemberAttribute>>();
	    listOfApplicableAttributes.add(Agility.class);
	    listOfApplicableAttributes.add(Strength.class);
	    return listOfContributingAttributes();
	}

    },
    MALNUTRION("MALNUTRITION", CauseOfDeathCategory.GENERAL) {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.universe.exploration.casualty.CauseOfDeath#
	 * listOfContributingAttributes()
	 */
	@Override
	public List<Class<? extends CrewMemberAttribute>> listOfContributingAttributes() {
	    List<Class<? extends CrewMemberAttribute>> listOfApplicableAttributes = new ArrayList<Class<? extends CrewMemberAttribute>>();
	    listOfApplicableAttributes.add(Strength.class);
	    return listOfContributingAttributes();
	}
    },
    CREWMEN_WENT_NUTS("CREWMEN_WENT_NUTS", CauseOfDeathCategory.GENERAL) {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.universe.exploration.casualty.CauseOfDeath#
	 * listOfContributingAttributes()
	 */
	@Override
	public List<Class<? extends CrewMemberAttribute>> listOfContributingAttributes() {
	    List<Class<? extends CrewMemberAttribute>> listOfApplicableAttributes = new ArrayList<Class<? extends CrewMemberAttribute>>();
	    listOfApplicableAttributes.add(Morale.class);
	    return listOfContributingAttributes();
	}
    },
    WATER_DEPRIVATION("WATER_DEPRIVATION", CauseOfDeathCategory.GENERAL) {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.universe.exploration.casualty.CauseOfDeath#
	 * listOfContributingAttributes()
	 */
	@Override
	public List<Class<? extends CrewMemberAttribute>> listOfContributingAttributes() {
	    List<Class<? extends CrewMemberAttribute>> listOfApplicableAttributes = new ArrayList<Class<? extends CrewMemberAttribute>>();
	    listOfApplicableAttributes.add(Strength.class);
	    return listOfContributingAttributes();
	}
    };

    private String localizationKey;

    private CauseOfDeathCategory causeOfDeathCategory;

    private CauseOfDeath(String localizationKey, CauseOfDeathCategory causeOfDeathCategory) {
	this.localizationKey = localizationKey;
	this.causeOfDeathCategory = causeOfDeathCategory;
    }

    /**
     * List of {@link CrewMemberAttribute} that affect given cause of death.
     * Default implementation returns null and as such is affected by planet
     * conditions only.
     * 
     * @return
     */
    public List<Class<? extends CrewMemberAttribute>> listOfContributingAttributes() {
	return null;
    }

    /**
     * @return the localizationKey
     */
    public String getLocalizationKey() {
	return localizationKey;
    }

    /**
     * @param localizationKey
     *            the localizationKey to set
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
     * @param causeOfDeathCategory
     *            the causeOfDeathCategory to set
     */
    public void setCauseOfDeathCategory(CauseOfDeathCategory causeOfDeathCategory) {
	this.causeOfDeathCategory = causeOfDeathCategory;
    }
}
