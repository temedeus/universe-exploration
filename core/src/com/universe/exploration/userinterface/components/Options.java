package com.universe.exploration.userinterface.components;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.userinterface.ButtonFactory;
import com.universe.exploration.userinterface.UIComponentFactory;
import com.universe.exploration.userinterface.WindowContainer;
import com.universe.exploration.userinterface.components.window.BasicWindow;
import com.universe.exploration.userinterface.components.window.WindowType;

public class Options {
    public BasicWindow createOptionsWindow(WindowContainer windowContainer) {
        UETable table = new UETable();
        table.align(Align.left | Align.top);

        final VolumeSlider volumeSlider = new VolumeSlider();
        volumeSlider.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeMusicVolume(volumeSlider.getValue());
            }
        });

        table.add(UIComponentFactory.createSpacer());
        table.addRow(new UELabel(Localizer.getInstance().get("LABEL_VOLUME")));
        table.add(createVolumeChangeButton(Localizer.getInstance().get("BTN_MIN_VOLUME"), 0f, volumeSlider));
        table.add(volumeSlider);
        table.addRow(createVolumeChangeButton(Localizer.getInstance().get("BTN_MAX_VOLUME"), 100f, volumeSlider));
        table.add(UIComponentFactory.createSpacer());

        BasicWindow window = windowContainer.getWindowFactory().createWindow(WindowType.OPTIONS_WINDOW, table, null);

        return window;
    }

    public TextButton createVolumeChangeButton(String caption, final float value, final Slider volumeslider) {
        return new ButtonFactory().createTextButton(caption, new ClickListener() {
            /*
             * (non-Javadoc)
             *
             * @see
             * com.badlogic.gdx.scenes.scene2d.utils.ClickListener#clicked(com
             * .badlogic.gdx.scenes.scene2d.InputEvent, float, float)
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeMusicVolume(value);
                volumeslider.setValue(value);
            }
        });
    }
    private void changeMusicVolume(float newVolumeVal) {
        float val = newVolumeVal / 100;
        if (val <= 100) {
            UniverseExploration.audioManager.getCurrentlyPlayingBackgroundMusic().setVolume(val);
        }
    }
}
