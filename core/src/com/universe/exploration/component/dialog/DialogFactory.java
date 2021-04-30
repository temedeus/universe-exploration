/**
 *
 */
package com.universe.exploration.component.dialog;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.universe.exploration.ComponentStyle;
import com.universe.exploration.component.button.ButtonFactory;

/**
 * @author 25.8.2015 Teemu Puurunen
 */
public class DialogFactory {
    private final Skin skin = ComponentStyle.userInterfaceSkin;

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
    public <T extends Actor> Dialog createDialog(final DialogType windowType, T contentTable, ClickListener okAction) {
        final Dialog window = createWindowFrame(windowType);

        Table buttontable = new Table();

        if (okAction != null) {
            buttontable.add(new ButtonFactory().createTextButton(windowType.getLocalizedOkButtonCaption(), (event, x, y) -> {
            }));
        } else {
            buttontable.add(
                    new ButtonFactory().createTextButton(windowType.getLocalizedOkButtonCaption(), (event, x, y) -> {
                        window.remove();
                    }));
        }

        if (windowType.isHasCancelButton()) {
            buttontable.add(
                    new ButtonFactory().createTextButton("Cancel", (event, x, y) -> {
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
     * @param okAction
     * @param secondaryAction
     * @return
     */
    public Dialog createWindowWithSecondaryAction(DialogType windowType, Table contentTable, String secondaryButtonTitle,
                                                  ClickListener okAction, ClickListener secondaryAction) {
        final Dialog window = createWindowFrame(windowType);

        Table buttontable = new Table();
        buttontable.add(new ButtonFactory().createTextButton(windowType.getLocalizedOkButtonCaption(), (event, x, y) -> {
            window.remove();
        }));
        buttontable.add(new ButtonFactory().createTextButton(secondaryButtonTitle, (event, x, y) -> {
            window.remove();
        }));

        buttontable.row();

        Table table = new Table();
        table.add(contentTable);
        table.row();
        table.add(buttontable);

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
