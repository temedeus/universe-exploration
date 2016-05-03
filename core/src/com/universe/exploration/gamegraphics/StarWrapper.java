/**
 * 
 */
package com.universe.exploration.gamegraphics;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.common.tools.GdxHelper;

/**
 * @author 27.12.2015 Teemu Puurunen
 *
 */
public class StarWrapper {
    private Sprite star;

    private boolean alphaReachedMinimum = false;

    private static float alphaMin = 0.0f;

    private static float alphaMax = 1.0f;

    private static float decrementRate = 0.05f;

    private static float incrementRate = 0.05f;

    private final Fader starFader = new Fader(alphaMin, alphaMax, decrementRate, incrementRate);

    /**
	 * 
	 */
    public StarWrapper(Sprite star) {
	this.star = star;
	starFader.setAlphaValue(alphaMax);
    }

    public void update() {
	// TODO: sort this offset. It likely has something to do with initiating
	// the sprite and its offsets etc.
	float starX = GdxHelper.getScreenCenterX() - this.star.getScaleX() * 2 - 2800;
	float starY = GdxHelper.getScreenCenterY() - this.star.getScaleY() * 2 - 2600;

	star.rotate((float) 0.1);
	star.setPosition(starX, starY);
	star.setAlpha(starFader.updateAlpha(!UniverseExploration.gameStatus.isPlanetaryMovementActive()));
	alphaReachedMinimum = starFader.isAlphaMinimized();
    }

    /**
     * @return the star
     */
    public Sprite getStar() {
	return star;
    }

    /**
     * @return the zoomed
     */
    public boolean isAlphaReachedMinimum() {
	return alphaReachedMinimum;
    }
}
