/**
 * 
 */
package com.universe.exploration.ueui.components;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.universe.exploration.GdxHelper;

/**
 * @author 20.9.2015 Teemu Puurunen 
 *
 */
public class UEWindow extends Window implements IUEWindow {
	/**
	 * @param title
	 * @param skin
	 */
	public UEWindow(String title, Skin skin) {
		super(title, skin);
		setDefault();
	}
	
	/**
	 * @param title
	 * @param skin
	 * @param styleName
	 */
	public UEWindow(String title, Skin skin, String styleName) {
		super(title, skin, styleName);
		setDefault();
	}
	
	/**
	 * @param title
	 * @param style
	 */
	public UEWindow(String title, WindowStyle style) {
		super(title, style);
		setDefault();
	}
	
	private void setDefault() {
	    setMovable(true);
	    padTop(20);
	    setWindowSize();
	    setPosition(GdxHelper.getScreenCenterX() - getWidth() / 2, GdxHelper.getScreenCenterY() - getHeight() / 2);
	   
	}
	
	public void setWindowSize() {
		setSize(WindowSetup.WINDOW_MEDIUM.getWidth(), WindowSetup.WINDOW_MEDIUM.getHeight());
	}
}