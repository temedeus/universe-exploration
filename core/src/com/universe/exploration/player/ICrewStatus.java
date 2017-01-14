/**
 * 
 */
package com.universe.exploration.player;

import com.universe.exploration.crew.CrewMemberCondition;
import com.universe.exploration.resource.Resource;

/**
 * Interface for different crew statuses.
 * 
 * @author 14.1.2017 Teemu Puurunen
 *
 */
public interface ICrewStatus {
    /**
     * Determine on which resource given {@link CrewStatus} links to.
     */
    Class<? extends Resource> mapCrewStatusToResource();

    CrewMemberCondition deprivationCausesCondition();

    void incrementValue(float increment);

    void decrementValue(float decrement);

    CrewStatusSetup getSetup();

    float getValue();

    boolean isProvidedWarningOnDepletion();

    void setProvidedWarningOnDepletion(boolean providedWarningOnDepletion);

    float healthDecreaseWhenDepleated();

    float getDecrement();

}