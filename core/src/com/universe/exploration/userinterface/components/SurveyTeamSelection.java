/**
 * 
 */
package com.universe.exploration.userinterface.components;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.universe.exploration.crewmember.Crew;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.userinterface.ButtonFactory;

/**
 * @author 4.4.2016 Teemu Puurunen
 *
 */
public class SurveyTeamSelection {

    public SurveyTeamSelection(Crew crew) {
	unselectedCrewMembers.addAll(crew.getAliveCrewmen());
    }

    private List<CrewMember> unselectedCrewMembers = new ArrayList<CrewMember>();

    private List<CrewMember> selectedCrewMembers = new ArrayList<CrewMember>();

    private Table unselectedCrewMemberTable = new Table();

    private Table selectedCrewMemberTable = new Table();

    public Table createSurveyTeamSelectionTable() {
	Table table = new Table();

	table.add(new UELabel(Localizer.getInstance().get("TITLE_ADD_CREWMEMBERS_TO_SURVEYTEAM")));
	table.row();

	table.add(unselectedCrewMemberTable);
	table.row();

	table.add(new UELabel(Localizer.getInstance().get("TITLE_REMOVE_CREWMBMERS_FROM_SURVEYTEAM")));
	table.row();

	table.add(selectedCrewMemberTable);
	table.row();

	refreshTables();

	return table;
    }

    private void crewManTable(Table table, List<CrewMember> crewmen, SurveyTeamCrewMemberModifyType type) {
	table.reset();

	if (crewmen.size() == 0) {
	    table.add(new UELabel("-- nothing found --"));
	} else {
	    int x = 0;
	    for (CrewMember crewmember : crewmen) {
		x++;

		table.padBottom(15);
		table.padRight(15);

		if (type.equals(SurveyTeamCrewMemberModifyType.ADD)) {
		    table.add(new ButtonFactory().createTextButton(crewmember.getName(), createAddCrewmanToSurveyTeam(crewmember)));
		}

		if (type.equals(SurveyTeamCrewMemberModifyType.REMOVE)) {
		    table.add(new ButtonFactory().createTextButton(crewmember.getName(), createRemoveCrewmanFromSurveyTeam(crewmember)));
		}

		// 5 per row seems good.
		if (x == 5) {
		    table.row();
		}
	    }
	}

    }

    private void refreshTables() {
	crewManTable(unselectedCrewMemberTable, unselectedCrewMembers, SurveyTeamCrewMemberModifyType.ADD);
	crewManTable(selectedCrewMemberTable, selectedCrewMembers, SurveyTeamCrewMemberModifyType.REMOVE);
    }

    private ClickListener createAddCrewmanToSurveyTeam(final CrewMember crewMember) {
	return new ClickListener() {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see
	     * com.badlogic.gdx.scenes.scene2d.utils.ClickListener#clicked(com
	     * .badlogic.gdx.scenes.scene2d.InputEvent, float, float)
	     */
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		unselectedCrewMembers.remove(crewMember);
		selectedCrewMembers.add(crewMember);
		refreshTables();
	    }
	};
    }

    private ClickListener createRemoveCrewmanFromSurveyTeam(final CrewMember crewMember) {
	return new ClickListener() {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see
	     * com.badlogic.gdx.scenes.scene2d.utils.ClickListener#clicked(com
	     * .badlogic.gdx.scenes.scene2d.InputEvent, float, float)
	     */
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		selectedCrewMembers.remove(crewMember);
		unselectedCrewMembers.add(crewMember);
		refreshTables();
	    }
	};
    }

    enum SurveyTeamCrewMemberModifyType {
	ADD, REMOVE;
    }
}
