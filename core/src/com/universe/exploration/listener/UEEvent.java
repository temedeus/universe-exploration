/**
 *
 */
package com.universe.exploration.listener;

/**
 * @author 25.8.2015 Teemu Puurunen
 */
public class UEEvent extends java.util.EventObject {
    private static final long serialVersionUID = -8867027440945524371L;

    private Object payload;

    public UEEvent(Object source) {
        super(source);
        setPayload(source);
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public Object getPayLoad() {
        return payload;
    }
}