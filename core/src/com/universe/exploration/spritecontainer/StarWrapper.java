/**
 *
 */
package com.universe.exploration.spritecontainer;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.universe.exploration.UniverseExploration;

/**
 * @author 27.12.2015 Teemu Puurunen
 */
public class StarWrapper {
    private Sprite star;

    private boolean alphaReachedMinimum = false;

    private static float alphaMin = 0.0f;

    private static float alphaMax = 1.0f;

    private static float decrementRate = 0.05f;

    private static float incrementRate = 0.05f;

    private final Fader starFader = new Fader(alphaMin, alphaMax, decrementRate, incrementRate);

    public StarWrapper(Sprite star) {
        this.star = star;
        starFader.setAlphaValue(alphaMax);
    }

    public void update(float centerX, float centerY) {
        float starX = centerX - star.getWidth() / 2;
        float starY = centerY - star.getHeight() / 2;

        star.rotate((float) 0.1);
        star.setPosition(starX, starY);
        star.setAlpha(starFader.updateAlpha(!UniverseExploration.gameStatus.isPlanetaryMovementActive()));
        alphaReachedMinimum = starFader.isAlphaMinimized();
    }

    public Sprite getStar() {
        return star;
    }

    public boolean isAlphaReachedMinimum() {
        return alphaReachedMinimum;
    }
}
