/**
 * 
 */
package com.universe.exploration.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.universe.exploration.GdxHelper;
import com.universe.exploration.UniverseExploration;

/**
 * @author 27.12.2015 Teemu Puurunen
 *
 */
public class StarWrapper {
	private float starAlpha;
	private Sprite star;

	/**
	 * 
	 */
	public StarWrapper(Sprite star) {
		this.star = star;
		starAlpha = 1.0f;
	}

	public void update() {
		// TODO: sort this offset. It likely has something to do with initiating
		// the sprite and its offsets etc.
		float starX = GdxHelper.getScreenCenterX() - this.star.getScaleX() * 2
				- 2800;
		float starY = GdxHelper.getScreenCenterY() - this.star.getScaleY() * 2
				- 2600;

		star.rotate((float) 0.1);
		star.setPosition(starX, starY);
		star.setAlpha(starAlpha);

		if (!UniverseExploration.planetaryMovement) {
			if (starAlpha > 0.0f) {
				starAlpha -= 0.05f;
			}
		} else {
			if (starAlpha < 1.0f) {
				starAlpha += 0.05f;
			}
		}
	}

	/**
	 * @return the star
	 */
	public Sprite getStar() {
		return star;
	}
}
