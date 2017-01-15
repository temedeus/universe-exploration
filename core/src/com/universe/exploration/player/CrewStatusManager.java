package com.universe.exploration.player;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.UniverseExploration;
import com.universe.exploration.common.CoreConfiguration;
import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.crewmember.Crew;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.resource.Resource;

/**
 * Manages {@link CrewStatus} attributed to the {@link Crew}.
 * 
 * @author 31.8.2015 Teemu Puurunen
 *
 */
public class CrewStatusManager {

    private List<ICrewStatus> statuses;

    private float power;

    private float time;

    private UEListener crewMemberStatusChangeListener;

    /**
     * Setup initial values. Start with full values
     */
    public CrewStatusManager() {
	statuses = new ArrayList<ICrewStatus>();
	statuses.add(new CrewStatusAir());
	statuses.add(new CrewStatusFood());
	statuses.add(new CrewStatusWater());
	time = CoreConfiguration.TIME_START;
	power = CoreConfiguration.MAX_POWER;
    }

    /**
     * Update crew statuses.
     */
    public void updateStatus() {
	List<CrewMember> crewmen = UniverseExploration.gameStatus.getCrew().getCrewMenAboardSpaceShip();
	increaseDaysPassed();

	int crewsize = crewmen.size();

	for (ICrewStatus status : statuses) {
	    if (status instanceof CrewStatusAir) {
		float airDecrement = status.getDecrement() * crewsize;
		status.decrementValue((power > 0) ? airDecrement : airDecrement * StatusConsumption.AIR_DECREMENT_WHEN_POWER_OUT);
	    } else {
		status.decrementValue(status.getDecrement() * crewsize);
	    }
	}

	updateCrewMemberStatuses(crewmen);
    }

    /**
     * Adjust resource value based on found resources.
     * 
     * @param resource
     */
    public void adjustStatusValue(Resource resource) {
	for (ICrewStatus status : statuses) {
	    if (status.mapCrewStatusToResource().equals(resource.getClass())) {
		status.incrementValue(resource.getAmount());
	    }
	}
    }

    /**
     * Updates crewmember statuses {@link #statuses}.
     * 
     * @param crewmen
     *            List of {@link CrewMember} subject to status change.
     */
    private void updateCrewMemberStatuses(List<CrewMember> crewmen) {
	List<ICrewStatus> statusesToDecrease = new ArrayList<ICrewStatus>();
	for (ICrewStatus status : statuses) {
	    float decrement = (status.getValue() == 0) ? StatusConsumption.HEALTH_DECREASE_WHEN_AIR_DEPLETED : 0;
	    if (decrement > 0 && !status.isProvidedWarningOnDepletion()) {
		fireCrewStatusChangeListener(Localizer.getInstance().get(status.getSetup().getDepletionMessageLocale()));
		status.setProvidedWarningOnDepletion(true);
		statusesToDecrease.add(status);
	    }
	}

	/**
	 * Perform health increase on each crewman individually. Stronger ones
	 * last more when bad condition hits.
	 */
	for (CrewMember member : crewmen) {
	    for (ICrewStatus status : statusesToDecrease) {
		member.decreaseHealth(status.healthDecreaseWhenDepleated()
			- ((status.healthDecreaseWhenDepleated() * ((float) member.getStrength().getValue() / 100)) / 2));
		member.addToCondition(status.deprivationCausesCondition());
	    }
	}
    }

    /**
     * Essentially we are using this listener to transmit messages for logging.
     * Not a good practise. TODO: Come up with better way to deal with logger.
     * 
     * @param message
     */
    private void fireCrewStatusChangeListener(String message) {
	if (crewMemberStatusChangeListener != null) {
	    crewMemberStatusChangeListener.handleEventClassEvent(new UEEvent(message));
	}
    }

    public void increaseDaysPassed() {
	time += CoreConfiguration.TIME_FLOW;
    }

    /**
     * Someones charging their phones
     * 
     * @param d
     */
    public void decreasePowerBy(float d) {
	power = MathTools.decreaseIfResultMoreOrEqualToZero(power, d);
    }

    /**
     * @return the power
     */
    public float getPower() {
	return power;
    }

    /**
     * @param power
     *            the power to set
     */
    public void setPower(float power) {
	this.power = power;
    }

    /**
     * @return the time
     */
    public float getTime() {
	return time;
    }

    /**
     * @param time
     *            the time to set
     */
    public void setTime(float time) {
	this.time = time;
    }

    /**
     * @return the crewMemberStatusChangeListener
     */
    public UEListener getCrewMemberStatusChangeListener() {
	return crewMemberStatusChangeListener;
    }

    /**
     * @param crewMemberStatusChangeListener
     *            the crewMemberStatusChangeListener to set
     */
    public void setCrewMemberStatusChangeListener(UEListener crewMemberStatusChangeListener) {
	this.crewMemberStatusChangeListener = crewMemberStatusChangeListener;
    }

    /**
     * Find given status class and its value.
     * 
     * @param clazz
     * @return
     */
    public float getStatusValue(CrewStatusSetup setup) {
	for (ICrewStatus status : statuses) {
	    if (status.getSetup() == setup) {
		return status.getValue();
	    }
	}

	return 0f;
    }
}
