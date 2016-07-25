/**
 * 
 */
package com.universe.exploration.listener;

import java.util.EventObject;

/**
 * @author 25.8.2015 Teemu Puurunen
 *
 */
interface IUEListener {
    public void handleEventClassEvent(EventObject e);

    void handleEventClassEvent();
}
