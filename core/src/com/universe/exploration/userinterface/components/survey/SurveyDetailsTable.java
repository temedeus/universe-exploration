/**
 *
 */
package com.universe.exploration.userinterface.components.survey;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
        Table cell = new Table();
        cell.padBottom(15);
        cell.padRight(15);
        cell.add(new UELabel(Localizer.getInstance().get("LABEL_NAME") + survey.getSurveyName()));
        cell.row();
        cell.add(new UELabel(new CrewMemberTools().concatenateCrewMemberListNames((survey.getSurveyTeam()))));
        cell.row();
        add(cell);
    }
}
