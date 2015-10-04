/**
 * 
 */
package com.universe.exploration.ueui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.ueui.components.LargeWindow;
import com.universe.exploration.ueui.components.SmallWindow;
import com.universe.exploration.ueui.components.BasicTable;
import com.universe.exploration.ueui.components.BasicWindow;

/**
 * <p>A lot of these windows are not very abstract by nature. This game probably never requires
 * any advanced functionality so we'll make do with what we have now. If game complexity
 * increases significantly, let's work on abstraction levels then.</p>
 * 
 * @author 25.8.2015 Teemu Puurunen 
 */
public class WindowFactory {
	Skin skin;
	WindowStyle windowStyle;
	ButtonFactory bf;
	public WindowFactory(Skin skin) {
		this.skin = skin;
		windowStyle = new WindowStyle(new BitmapFont(), Color.WHITE, skin.newDrawable("white", Color.BLACK));
		bf = new ButtonFactory(skin);
	}
	
	/**
	 * Creates quit game window. Utilizes {@link createOkWindow} by just issuing it a ClickListener
	 * with action to quit application.
	 * @return Window
	 */
	public Window createQuitWindow(String caption) {
		return createOkWindow(caption, new ClickListener() {
	    	@Override
	    	public void clicked(InputEvent event, float x, float y) {
	    		Gdx.app.exit();
	    	}
	    });
	}
	
	/**
	 * Creates OK window. OK button action can be configured.
	 * @param okAction
	 * @return UEWindow
	 */
	public BasicWindow createOkWindow(String caption, ClickListener okAction) {
	    final SmallWindow window = new SmallWindow(caption, windowStyle);

	    window.add(bf.createTextButton(Localizer.get("BTN_OK"), okAction));
	    
	    window.add(bf.createTextButton(Localizer.get("BTN_CANCEL"), new ClickListener() {
	    	@Override
	    	public void clicked(InputEvent event, float x, float y) {
	    		window.remove();
	    	}
	    }));
	    
	    return window; 
	}

	/**
	 * Creates a description window.
	 * @param pgfx
	 * @return
	 */
	public <T extends Actor> BasicWindow createDescriptionWindow(String caption, T contentTable, ClickListener okAction) {
	    final LargeWindow window = new LargeWindow(caption, windowStyle);
		
		Table buttontable = new Table();
		buttontable.add(bf.createTextButton(Localizer.get("BTN_SURVEY"), okAction));
		
		buttontable.add(bf.createTextButton(Localizer.get("BTN_CANCEL"), new ClickListener() {
	    	@Override
	    	public void clicked(InputEvent event, float x, float y) {
	    		window.remove();
	    	}
	    }));
		
		buttontable.row();
	    
		Table table = new Table();
		table.add(contentTable);
		table.row();
		table.add(buttontable);

	    window.add(table);
	    
	    return window; 
	}

	/**
	 * Creates a description window with secondary action.
	 * @param pgfx
	 * @return
	 */
	public BasicWindow createDescriptionWindow(String caption, BasicTable contentTable, String okButtonTitle, String secondaryButtonTitle, ClickListener okAction, ClickListener secondaryAction) {
	    final LargeWindow window = new LargeWindow(caption, windowStyle);
		
		Table buttontable = new Table();
		buttontable.add(bf.createTextButton(okButtonTitle, okAction));
		buttontable.add(bf.createTextButton(secondaryButtonTitle, secondaryAction));
		
		buttontable.row();
	    
		Table table = new Table();
		table.add(contentTable.getTable());
		table.row();
		table.add(buttontable);

	    window.add(table);
	    
	    return window; 
	}
}
