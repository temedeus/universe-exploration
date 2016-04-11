/**
 * 
 */
package com.universe.exploration.userinterface;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.userinterface.components.window.BasicWindow;
import com.universe.exploration.userinterface.components.window.WindowType;

/**
 * @author 7.10.2015 Teemu Puurunen
 *
 */
public class WindowContainer {
    private HashMap<WindowType, BasicWindow> windowmap;

    private WindowType[] windowTypes;

    // TODO: maybe this could be moved apart from this class? I dont think this
    // should be responsible of such abstract things.
    private UEListener notifyOfSpecifiedWindowsChanged;

    public WindowContainer() {
	windowmap = new HashMap<WindowType, BasicWindow>();
    }

    public void add(WindowType key, BasicWindow window) {
	if (hasWindow(key)) {
	    windowmap.get(key).remove();
	}

	checkIfNeedToAlert(key, WindowContainerEvent.ADD);
	windowmap.put(key, window);
    }

    /**
     * If following windows are added, {@link #notifyOfSpecifiedWindowsChanged}
     * will be fired.
     */
    public void setWindowsThatMustAlert(WindowType... windowTypes) {
	this.windowTypes = windowTypes;
    }

    /**
     * Closes window based on given key.
     * 
     * @param key
     * @return
     */
    public void closeWindow(WindowType key) {
	BasicWindow window = windowmap.get(key);
	if (window != null) {
	    window.remove();
	    checkIfNeedToAlert(key, WindowContainerEvent.REMOVE);
	    closeChildren(key.retreiveChildWindows());
	    windowmap.remove(key);
	}
    }

    private void closeChildren(List<WindowType> dependencies) {
	if (dependencies != null) {
	    for (WindowType dependency : dependencies) {
		closeWindow(dependency);
	    }
	}
    }
    
    public void closeAllWindows() {
	for(WindowType type : WindowType.values()) {
	    closeWindow(type);
	}
    }

    private void checkIfNeedToAlert(WindowType key, WindowContainerEvent event) {
	if (Arrays.asList(windowTypes).contains(key)) {
	    fireSpecificedWindowChangeListener(event);
	}
    }

    public boolean hasWindow(WindowType key) {
	BasicWindow window = windowmap.get(key);
	return (window != null) ? true : false;
    }

    private void fireSpecificedWindowChangeListener(WindowContainerEvent windowContainerEvent) {
	notifyOfSpecifiedWindowsChanged.handleEventClassEvent(new UEEvent(windowContainerEvent));
    }

    /**
     * @param specificedWindowChangeListener
     *            the specificedWindowChangeListener to set
     */
    public void setSpecificedWindowChangeListener(UEListener specificedWindowChangeListener) {
	this.notifyOfSpecifiedWindowsChanged = specificedWindowChangeListener;
    }

}
