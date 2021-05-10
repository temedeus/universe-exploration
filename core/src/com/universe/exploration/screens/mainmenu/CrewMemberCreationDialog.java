package com.universe.exploration.screens.mainmenu;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.component.FunctionalClickListener;
import com.universe.exploration.component.button.ButtonFactory;
import com.universe.exploration.model.crew.CrewMember;

import java.util.List;

public class CrewMemberCreationDialog extends Table {
    public CrewMemberCreationDialog(List<CrewMember> crewMembers) {

        int x = 0;

        for (CrewMember crewmember : crewMembers) {
            x++;
            Table cell = new Table();
            cell.padBottom(15);
            cell.padRight(15);
            cell.add(new Label(crewmember.getName(), UniverseExploration.commonUIAssets.getUserInterfaceSkin()));
            cell.row();
            cell.add(new Label(UniverseExploration.getLocaliser().get(crewmember.getStatus().toString()),
                    UniverseExploration.commonUIAssets.getUserInterfaceSkin()));
            cell.row();
            cell.add(new ButtonFactory().createTextButton(UniverseExploration.getLocaliser().get("BTN_SHOW_DETAILS"),
                    createCrewmemberDetailsClickListerener(crewmember)));
            add(cell);

            // 5 per row seems good.
            if (x == 5) {
                row();
            }
        }
    }

    private FunctionalClickListener createCrewmemberDetailsClickListerener(final CrewMember crewMember) {
        return ((event, x, y) -> {
        });
    }

}
