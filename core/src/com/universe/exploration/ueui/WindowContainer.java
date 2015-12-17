/**
 * 
 */
package com.universe.exploration.ueui;

import java.util.HashMap;

import com.universe.exploration.ueui.components.BasicWindow;

/**
 * @author 7.10.2015 Teemu Puurunen 
 *
 */
public class WindowContainer {
	HashMap<WindowType, BasicWindow> windowmap;
	
	public WindowContainer() {
		windowmap = new HashMap<WindowType, BasicWindow>();
	}
	
	public void add(WindowType key, BasicWindow window) {
		if(hasWindow(key)) {
			windowmap.get(key).remove();
		}
		
		windowmap.put(key, window);
	}
	
	/**
	 * Closes window based on given key.
	 * 
	 * @param key
	 * @return
	 */
	public boolean closeWindow(WindowType key) {
		BasicWindow window = windowmap.get(key);
		if(window != null) {
			window.remove();
			return (windowmap.remove((WindowType)key) != null) ? true : false;
		} else {
			return true;
		}
	}
	
	public boolean hasWindow(WindowType key) {
		BasicWindow window = windowmap.get(key);
		return (window != null) ? true : false;
	}
}
