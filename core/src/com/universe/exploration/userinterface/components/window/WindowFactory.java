/**
 * 
 */
package com.universe.exploration.userinterface.components.window;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.userinterface.ButtonFactory;
import com.universe.exploration.userinterface.components.UETable;
import com.universe.exploration.userinterface.skins.UserInterfaceBank;

/**
 * <p>
 * A lot of these windows are not very abstract by nature. This game probably
 * never requires any advanced functionality so we'll make do with what we have
 * now. If game complexity increases significantly, let's work on abstraction
 * levels then.
 * </p>
 * 
 * @author 25.8.2015 Teemu Puurunen
 */
public class WindowFactory {
    private Skin skin = UserInterfaceBank.userInterfaceSkin;

    private static final String STYLE = "default";

    /**
     * Create a window based on {@link WindowType}.
     * 
     * @param windowType
     * @param contentTable
     * @param okAction
     * @param hasCancelButton
     * @return
     */
    public <T extends Actor> BasicWindow createWindow(final WindowType windowType, T contentTable, ClickListener okAction) {
	final BasicWindow window = createWindowFrame(windowType);

	UETable buttontable = new UETable();
	buttontable.add(new ButtonFactory().createTextButton(windowType.getLocalizedOkButtonCaption(), okAction));

	if (windowType.isHasCancelButton()) {
	    buttontable.add(
		    new ButtonFactory().createTextButton(Localizer.getInstance().get("BTN_CANCEL"), createCancelClickListener(windowType)));
	}

	buttontable.row();

	window.add(combineDataAndButtonbar(contentTable, buttontable));

	return window;
    }

    /**
     * Creates a description window with secondary action.
     * 
     * @param windowType
     * @param contentTable
     * @param secondaryButtonTitle
     * @param okAction
     * @param secondaryAction
     * @return
     */
    public BasicWindow createWindowWithSecondaryAction(WindowType windowType, UETable contentTable, String secondaryButtonTitle,
	    ClickListener okAction, ClickListener secondaryAction) {
	final BasicWindow window = createWindowFrame(windowType);

	UETable buttontable = new UETable();
	buttontable.add(new ButtonFactory().createTextButton(windowType.getLocalizedOkButtonCaption(), okAction));
	buttontable.add(new ButtonFactory().createTextButton(Localizer.getInstance().get(secondaryButtonTitle), secondaryAction));

	buttontable.row();

	Table table = new Table();
	table.add(contentTable);
	table.row();
	table.add(buttontable);

	window.add(table);

	return window;
    }

    private BasicWindow createWindowFrame(final WindowType windowType) {
	if (windowType.getWindowSetup().equals(WindowSetup.LARGE))
	    return new LargeWindow(windowType, skin, STYLE);

	if (windowType.getWindowSetup().equals(WindowSetup.MEDIUM))
	    return new MediumWindow(windowType, skin, STYLE);

	if (windowType.getWindowSetup().equals(WindowSetup.SMALL))
	    return new SmallWindow(windowType, skin, STYLE);

	return null;
    }

    public ClickListener createCancelClickListener(final WindowType windowType) {
	return new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		UniverseExploration.windowContainer.closeWindow(windowType);
	    }
	};
    }

    private <T extends Actor> Table combineDataAndButtonbar(T contentTable, Table buttontable) {
	Table table = new Table();
	table.add(contentTable);
	table.row();
	table.add(buttontable);

	return table;
    }
}
