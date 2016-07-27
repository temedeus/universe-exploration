/**
 * 
 */
package com.universe.exploration.userinterface.components.window;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.userinterface.ButtonFactory;
import com.universe.exploration.userinterface.components.BasicTable;
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
     * Creates quit game window. Utilizes {@link createOkWindow} by just issuing
     * it a ClickListener with action to quit application.
     * 
     * @return Window
     */
    public Window createQuitWindow(String caption) {
	return createOkWindow(caption, new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		Gdx.app.exit();
	    }
	});
    }

    /**
     * Creates OK window. OK button action can be configured.
     * 
     * @param okAction
     * @return UEWindow
     */
    public BasicWindow createOkWindow(String caption, ClickListener okAction) {
	final SmallWindow window = new SmallWindow(caption, skin, STYLE);

	window.add(new ButtonFactory().createTextButton(Localizer.getInstance().get("BTN_OK"), okAction));

	window.add(new ButtonFactory().createTextButton(Localizer.getInstance().get("BTN_CANCEL"), new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		window.remove();
	    }
	}));

	return window;
    }

    private <T extends Actor> Table combineDataAndButtonbar(T contentTable, Table buttontable) {
	Table table = new Table();
	table.add(contentTable);
	table.row();
	table.add(buttontable);

	return table;
    }

    /**
     * Creates a description window.
     * 
     * @param pgfx
     * @return
     */
    public <T extends Actor> BasicWindow createLargeDescriptionWindow(final WindowType windowType, T contentTable, ClickListener okAction) {
	final LargeWindow window = new LargeWindow(windowType.getLocalizedCaption(), skin, STYLE);

	Table buttontable = new Table();
	buttontable.add(new ButtonFactory().createTextButton(windowType.getLocalizedOkButtonCaption(), okAction));

	buttontable.add(new ButtonFactory().createTextButton(Localizer.getInstance().get("BTN_CANCEL"), new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		UniverseExploration.windowContainer.closeWindow(windowType);
	    }
	}));

	buttontable.row();

	window.add(combineDataAndButtonbar(contentTable, buttontable));

	return window;
    }

    /**
     * Creates a description window.
     * 
     * @param pgfx
     * @return
     */
    public <T extends Actor> BasicWindow createOKWindow(String caption, T contentTable) {
	final LargeWindow window = new LargeWindow(caption, skin, STYLE);

	Table buttontable = new Table();

	buttontable.add(new ButtonFactory().createTextButton(Localizer.getInstance().get("BTN_OK"), new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		window.remove();
	    }
	}));

	buttontable.row();

	window.add(combineDataAndButtonbar((Table) contentTable, buttontable));

	return window;
    }

    /**
     * Creates a description window.
     * 
     * @param pgfx
     * @return
     */
    public <T extends Actor> BasicWindow createMediumDescriptionWindow(final WindowType windowType, T contentTable, ClickListener okAction,
	    boolean hasCancelButton) {
	final MediumWindow window = new MediumWindow(windowType.getLocalizedCaption(), skin, STYLE);

	Table buttontable = new Table();
	buttontable.add(new ButtonFactory().createTextButton(windowType.getLocalizedOkButtonCaption(), okAction));

	if (hasCancelButton) {
	    buttontable.add(new ButtonFactory().createTextButton(Localizer.getInstance().get("BTN_CANCEL"),
		    createCancelClickListener(windowType)));
	}

	buttontable.row();

	window.add(combineDataAndButtonbar((Table) contentTable, buttontable));

	return window;
    }

    public ClickListener createCancelClickListener(final WindowType windowType) {
	return new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		UniverseExploration.windowContainer.closeWindow(windowType);
	    }
	};
    }

    /**
     * Creates a description window with secondary action.
     * 
     * @param pgfx
     * @return
     */
    public BasicWindow createDescriptionWindowWithSecondaryAction(WindowType windowType, BasicTable contentTable,
	    String secondaryButtonTitle, ClickListener okAction, ClickListener secondaryAction) {
	final SmallWindow window = new SmallWindow(windowType.getLocalizedCaption(), skin, STYLE);

	Table buttontable = new Table();
	buttontable.add(new ButtonFactory().createTextButton(windowType.getLocalizedOkButtonCaption(), okAction));
	buttontable.add(new ButtonFactory().createTextButton(Localizer.getInstance().get(secondaryButtonTitle), secondaryAction));

	buttontable.row();

	Table table = new Table();
	table.add(contentTable.getTable());
	table.row();
	table.add(buttontable);

	window.add(table);

	return window;
    }
}
