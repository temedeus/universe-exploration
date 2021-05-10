package com.universe.exploration.component.group;

import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.universe.exploration.utils.GdxHelper;

public class VerticalCenteredGroup extends VerticalGroup {
    public VerticalCenteredGroup() {
        super();
        align(Align.center | Align.center);
        setPosition(GdxHelper.getScreenCenterX(), GdxHelper.getScreenCenterY());
        padTop(30);
        padRight(30);
    }
}
