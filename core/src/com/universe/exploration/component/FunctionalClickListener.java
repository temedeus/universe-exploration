package com.universe.exploration.component;

import com.badlogic.gdx.scenes.scene2d.InputEvent;

/**
 * {@link com.badlogic.gdx.scenes.scene2d.utils.ClickListener} is not a functional interface. As such use this in
 * factories to pass lambdas as click events.
 */
@FunctionalInterface
public interface FunctionalClickListener {
    void onClick(InputEvent event, float x, float y);
}
