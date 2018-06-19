/**
 *
 */
package com.universe.exploration.userinterface;

import com.universe.exploration.UniverseExploration;
import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.userinterface.components.window.BasicWindow;
import com.universe.exploration.userinterface.components.window.WindowFactory;
import com.universe.exploration.userinterface.components.window.WindowType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Handles instances
 *
 * @author 7.10.2015 Teemu Puurunen
 */
public class WindowContainer {
    private HashMap<WindowType, BasicWindow> windowmap;

    private List<WindowType> windowTypesContained;

    private UEListener notifyOfSpecifiedWindowsChanged;

    private WindowFactory windowFactory;

    public WindowContainer() {
        windowmap = new HashMap<WindowType, BasicWindow>();
        windowFactory = new WindowFactory(this);
    }

    public void add(WindowType key, BasicWindow window) {
        // updatePauseStatus();
        checkIfNeedToAlert(key, WindowContainerEvent.ADD);
        windowmap.put(key, window);
    }

    /**
     * If following windows are added, {@link #notifyOfSpecifiedWindowsChanged}
     * will be fired.
     */
    public void setWindowsThatMustAlert(WindowType... windowTypes) {
        this.windowTypesContained = Arrays.asList(windowTypes);
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
            closeChildren(key.relatedViews());
            windowmap.remove(key);
            // updatePauseStatus();
        }
    }

    private void updatePauseStatus() {
        boolean foundPauseable = false;
        for (WindowType type : windowmap.keySet()) {
            if (type.windowPausesGame()) {
                foundPauseable = true;
            }
        }

        UniverseExploration.setGameStatusPaused(foundPauseable);
    }

    private void closeChildren(List<WindowType> dependencies) {
        if (dependencies != null) {
            for (WindowType dependency : dependencies) {
                closeWindow(dependency);
            }
        }
    }

    public void closeAllWindows() {
        for (WindowType type : WindowType.values()) {
            closeWindow(type);
        }
    }

    private void checkIfNeedToAlert(WindowType key, WindowContainerEvent event) {
        if (windowTypesContained.contains(key)) {
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
     * @param specificedWindowChangeListener the specificedWindowChangeListener to set
     */
    public void setSpecificedWindowChangeListener(UEListener specificedWindowChangeListener) {
        this.notifyOfSpecifiedWindowsChanged = specificedWindowChangeListener;
    }

    /**
     * @return the windowFactory
     */
    public WindowFactory getWindowFactory() {
        return windowFactory;
    }

}
