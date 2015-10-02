/**
 * 
 */
package com.universe.exploration.ueui.components;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * @author 20.9.2015 Teemu Puurunen 
 *
 */
public class MediumWindow extends UEWindow {
	/**
	 * @param title
	 * @param skin
	 */
	public MediumWindow(String title, Skin skin) {
		super(title, skin);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param title
	 * @param skin
	 * @param styleName
	 */
	public MediumWindow(String title, Skin skin, String styleName) {
		super(title, skin, styleName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param title
	 * @param style
	 */
	public MediumWindow(String title, WindowStyle style) {
		super(title, style);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setWindowSize() {
		setSize(WindowSetup.WINDOW_MEDIUM.getWidth(), WindowSetup.WINDOW_MEDIUM.getHeight());
	}
}