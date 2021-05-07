/**
 *
 */
package com.universe.exploration.component.dialog;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.component.FunctionalClickListener;
import com.universe.exploration.component.button.ButtonFactory;

/**
 * @author 25.8.2015 Teemu Puurunen
 */
public class DialogFactory {
    private final Skin skin = UniverseExploration.commonUIAssets.getUserInterfaceSkin();

    private static final String STYLE = "default";

    /**
     * Create a window based on {@link DialogType}. With no
     * <code>okAction</code> specified, just close the window.
     * @param windowType
     * @param contentTable
     * @param okAction
     * @param <T>
     * @return
     */
    public <T extends Actor> Dialog createDialog(final DialogType windowType, T contentTable, FunctionalClickListener okAction) {
        final Dialog window = createWindowFrame(windowType);

        Table buttontable = new Table();
        ButtonFactory buttonFactory = new ButtonFactory();
        FunctionalClickListener okActionWithClose = (event, x, y) -> {
            okAction.onClick(event,x,y);
            window.remove();
        };

        buttontable.add(buttonFactory.createTextButton(windowType.getLocalizedOkButtonCaption(), okActionWithClose));

        if (windowType.isHasCancelButton()) {
            buttontable.add(
                    buttonFactory.createTextButton("Cancel", (event, x, y) -> {
                        window.remove();
                    }));
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
     * @param primaryAction
     * @param secondaryAction
     * @return
     */
    public Dialog createWindowWithSecondaryAction(DialogType windowType, Table contentTable, String secondaryButtonTitle,
                                                  FunctionalClickListener primaryAction, FunctionalClickListener secondaryAction) {
        final Dialog window = createWindowFrame(windowType);


        FunctionalClickListener primaryActionWithClose = (event, x, y) -> {
            primaryAction.onClick(event,x,y);
            window.remove();
        };

        FunctionalClickListener secondaryActionWithClose = (event, x, y) -> {
            primaryAction.onClick(event,x,y);
            window.remove();
        };

        Table buttonTable = new Table();
        buttonTable.add(new ButtonFactory().createTextButton(windowType.getLocalizedOkButtonCaption(), primaryActionWithClose));
        buttonTable.add(new ButtonFactory().createTextButton(secondaryButtonTitle, secondaryActionWithClose));

        buttonTable.row();

        Table table = new Table();
        table.add(contentTable);
        table.row();
        table.add(buttonTable);

        window.add(table);

        return window;
    }

    private Dialog createWindowFrame(final DialogType windowType) {
        if (windowType.getDialogSetup().equals(DialogSetup.LARGE))
            return new LargeDialog(windowType, skin, STYLE);

        if (windowType.getDialogSetup().equals(DialogSetup.MEDIUM))
            return new MediumDialog(windowType, skin, STYLE);

        if (windowType.getDialogSetup().equals(DialogSetup.SMALL))
            return new SmallDialog(windowType, skin, STYLE);

        return null;
    }

    private <T extends Actor> Table combineDataAndButtonbar(T contentTable, Table buttontable) {
        Table table = new Table();
        table.add(contentTable);
        table.row();
        table.add(buttontable);

        return table;
    }
}
