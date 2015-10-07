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
	HashMap<String, BasicWindow> windowmap;
	
	public WindowContainer() {
		windowmap = new HashMap<String, BasicWindow>();
	}
	
	public void add(String key, BasicWindow window) {
		windowmap.put(key, window);
	}
	
	public boolean remove(String key) {
		return (windowmap.remove((String)key) != null) ? true: false;
	}
}
