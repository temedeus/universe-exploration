/**
 *
 */
package com.universe.exploration.userinterface.components.survey;

import com.universe.exploration.crewmember.CrewMemberTools;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.survey.Survey;
import com.universe.exploration.userinterface.components.UELabel;
import com.universe.exploration.userinterface.components.UETable;

/**
 * Survey details table.
 *
 * @author 10.1.2017 Teemu Puurunen
 */
public class SurveyDetailsTable extends UETable {
    public SurveyDetailsTable(Survey survey) {
        padBottom(15);
        padRight(15);
        addRow(new UELabel(Localizer.getInstance().get("LABEL_NAME") + survey.getSurveyName()));
        addRow(new UELabel(new CrewMemberTools().concatenateCrewMemberListNames((survey.getSurveyTeam()))));
    }
}
