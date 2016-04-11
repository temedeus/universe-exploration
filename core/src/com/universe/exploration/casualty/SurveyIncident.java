/**
 * 
 */
package com.universe.exploration.casualty;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.crew.CrewMemberCondition;
import com.universe.exploration.crew.CrewMemberStatus;
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
public enum SurveyIncident {
    // No oxygen on the planet
    HELMET_BREACH("HELMET_BREACH", SurveyIncidentCategory.LACK_OF_OXYGEN) {
	/* (non-Javadoc)
	 * @see com.universe.exploration.casualty.SurveyIncident#causesStatus()
	 */
	@Override
	public CrewMemberStatus causesStatus() {
	    return CrewMemberStatus.KIA;
	}
	
	/* (non-Javadoc)
	 * @see com.universe.exploration.casualty.SurveyIncident#causesCondition()
	 */
	@Override
	public CrewMemberCondition causesCondition() {
	   return CrewMemberCondition.OXYGEN_DEPRIVATION;
	}
    },
    RIP_ON_SUIT("RIP_ON_SUIT", SurveyIncidentCategory.LACK_OF_OXYGEN) {
	@Override
	public CrewMemberStatus causesStatus() {
	    return CrewMemberStatus.KIA;
	}
	
	/* (non-Javadoc)
	 * @see com.universe.exploration.casualty.SurveyIncident#causesCondition()
	 */
	@Override
	public CrewMemberCondition causesCondition() {
	   return CrewMemberCondition.OXYGEN_DEPRIVATION;
	}
    },

    // Animals present
    ATTACKED_BY_ANIMALS("ATTACKED_BY_ANIMALS", SurveyIncidentCategory.ANIMAL) {
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
	
	@Override
	public CrewMemberStatus causesStatus() {
	    return CrewMemberStatus.KIA;
	}
	
	/* (non-Javadoc)
	 * @see com.universe.exploration.casualty.SurveyIncident#causesCondition()
	 */
	@Override
	public CrewMemberCondition causesCondition() {
	   return CrewMemberCondition.PHYSICAL_INJURY;
	}
    },

    // Civilization present
    KILLED_BY_LOCALS("KIA_BY_LOCALS", SurveyIncidentCategory.CIVILIZATION) {
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
	
	@Override
	public CrewMemberStatus causesStatus() {
	    return CrewMemberStatus.KIA;
	}
	
	/* (non-Javadoc)
	 * @see com.universe.exploration.casualty.SurveyIncident#causesCondition()
	 */
	@Override
	public CrewMemberCondition causesCondition() {
	   return CrewMemberCondition.PHYSICAL_INJURY;
	}
    },
    SEDUCED_BY_AMAZONS("SEDUCED_BY_AMAZONS", SurveyIncidentCategory.CIVILIZATION) {
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
	
	@Override
	public CrewMemberStatus causesStatus() {
	    return CrewMemberStatus.MIA;
	}
    },

    // General
    FELL_OFF_CLIFF("FELL_OFF_CLIFF", SurveyIncidentCategory.GENERAL) {
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
	
	@Override
	public CrewMemberStatus causesStatus() {
	    return CrewMemberStatus.KIA;
	}
	
	/* (non-Javadoc)
	 * @see com.universe.exploration.casualty.SurveyIncident#causesCondition()
	 */
	@Override
	public CrewMemberCondition causesCondition() {
	   return CrewMemberCondition.PHYSICAL_INJURY;
	}

    },
    MALNUTRION("MALNUTRITION", SurveyIncidentCategory.GENERAL) {
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
	
	@Override
	public CrewMemberStatus causesStatus() {
	    return CrewMemberStatus.KIA;
	}
	
	/* (non-Javadoc)
	 * @see com.universe.exploration.casualty.SurveyIncident#causesCondition()
	 */
	@Override
	public CrewMemberCondition causesCondition() {
	   return CrewMemberCondition.MALNUTRITION;
	}
    },
    CREWMEN_WENT_NUTS("CREWMEN_WENT_NUTS", SurveyIncidentCategory.GENERAL) {
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
	
	@Override
	public CrewMemberStatus causesStatus() {
	    return CrewMemberStatus.KIA;
	}
	
	/* (non-Javadoc)
	 * @see com.universe.exploration.casualty.SurveyIncident#causesCondition()
	 */
	@Override
	public CrewMemberCondition causesCondition() {
	   return CrewMemberCondition.PSYCHOSIS;
	}
    },
    WATER_DEPRIVATION("WATER_DEPRIVATION", SurveyIncidentCategory.GENERAL) {
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
	
	@Override
	public CrewMemberStatus causesStatus() {
	    return CrewMemberStatus.KIA;
	}
	
	/* (non-Javadoc)
	 * @see com.universe.exploration.casualty.SurveyIncident#causesCondition()
	 */
	@Override
	public CrewMemberCondition causesCondition() {
	   return CrewMemberCondition.WATER_DEPRIVATION;
	}
    };

    private String localizationKey;

    private SurveyIncidentCategory causeOfDeathCategory;

    private SurveyIncident(String localizationKey, SurveyIncidentCategory causeOfDeathCategory) {
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
     * Determines what condition crewman is exposed to due to this event.
     * @return
     */
    public CrewMemberCondition causesCondition() {
	return null;
    }
    
    
    /**
     * Determines what status is caused because of this event.
     * @return
     */
    public CrewMemberStatus causesStatus() {
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
    public SurveyIncidentCategory getCauseOfDeathCategory() {
	return causeOfDeathCategory;
    }

    /**
     * @param causeOfDeathCategory
     *            the causeOfDeathCategory to set
     */
    public void setCauseOfDeathCategory(SurveyIncidentCategory causeOfDeathCategory) {
	this.causeOfDeathCategory = causeOfDeathCategory;
    }
}
