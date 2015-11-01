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
	HashMap<WindowTypes, BasicWindow> windowmap;
	
	public WindowContainer() {
		windowmap = new HashMap<WindowTypes, BasicWindow>();
	}
	
	public void add(WindowTypes key, BasicWindow window) {
		windowmap.put(key, window);
	}
	
	/**
	 * Closes window based on given key.
	 * 
	 * @param key
	 * @return
	 */
	public boolean closeWindow(WindowTypes key) {
		BasicWindow window = windowmap.get(key);
		if(window != null) {
			window.remove();
			return (windowmap.remove((WindowTypes)key) != null) ? true : false;
		} else {
			return true;
		}
	}
	
	public boolean hasWindow(WindowTypes key) {
		BasicWindow window = windowmap.get(key);
		return (window != null) ? true : false;
	}
}
