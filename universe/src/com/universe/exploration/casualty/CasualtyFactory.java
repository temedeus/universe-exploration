/**
 *
 */
package com.universe.exploration.casualty;

import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.crewmember.attribute.CrewMemberAttribute;
import com.universe.exploration.resource.Water;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.survey.Lifeform;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for {@link Casualty}. We need {@link PlanetCelestialComponent} to
 * properly generate the reason for death.
 *
 * @author 25.10.2015 Teemu Puurunen
 */
public class CasualtyFactory {

    private PlanetCelestialComponent planet;

    public CasualtyFactory(PlanetCelestialComponent planet) {
        this.planet = planet;
    }

    public Casualty createCasualty(CrewMember crewMember) {
        Casualty casualty = new Casualty();

        SurveyIncidentRandomPicker picker = new SurveyIncidentRandomPicker(createListofApplicableCauseOfDeath(planet));
        SurveyIncident incident = picker.pickRandomSurveyIncident();

        if (calculateIfIncidentHappens(incident, crewMember)) {
            casualty.setCauseOfDeath(incident);
            casualty.setMember(crewMember);

            return casualty;
        } else {
            return null;
        }
    }

    private List<SurveyIncidentCategory> createListofApplicableCauseOfDeath(PlanetCelestialComponent planet) {
        List<SurveyIncidentCategory> applicableSurveyIncidents = new ArrayList<SurveyIncidentCategory>();

        applicableSurveyIncidents.add(SurveyIncidentCategory.GENERAL);

        if (!planet.containsInstanceOfResource(Water.class)) {
            applicableSurveyIncidents.add(SurveyIncidentCategory.LACK_OF_OXYGEN);
        }

        if (planet.getLifeforms() != Lifeform.NONE) {
            applicableSurveyIncidents.addAll(planet.getLifeforms().provideSurveyIncidentCategoryList());
        }

        return applicableSurveyIncidents;
    }

    private boolean calculateIfIncidentHappens(SurveyIncident incident, CrewMember crewMember) {

        List<Class<? extends CrewMemberAttribute>> listOfApplicableAttributes = incident.listOfContributingAttributes();

        int attributeValueSum = 0;

        for (Class<? extends CrewMemberAttribute> clazz : listOfApplicableAttributes) {
            attributeValueSum += crewMember.getCrewMemberAttributes().get(clazz.getName()).getValue();
        }

        float odds = incident.getOdds() - (incident.getOdds() * (float) attributeValueSum / 100);

        return (MathTools.calculateIfOddsHit(odds)) ? true : false;
    }
}
