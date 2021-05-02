package com.universe.exploration.component.button;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.universe.exploration.ComponentStyle;
import com.universe.exploration.component.FunctionalClickListener;

/**
 * Button factory.
*/
public class ButtonFactory {
    private final Skin uiSkin = ComponentStyle.userInterfaceSkin;

    /**
     * Create button.
     *
     * @param caption
     * @param clickListener
     * @return
     */
    public TextButton createTextButton(String caption, FunctionalClickListener clickListener) {
        final TextButton button = new TextButton(caption, uiSkin, "default");
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickListener.onClick(event, x, y);
            }
        });
        button.setTransform(true);
        button.getLabel().setFontScale(3f);
        button.setOrigin(Align.center);

        return button;
    }
}
