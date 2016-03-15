/**
 * 
 */
package com.universe.exploration.crewmember;

import com.sun.xml.internal.ws.util.StringUtils;

public enum Nationality {
    AMERICAN, CHINESE, ENGLISH, FINNISH, RUSSIAN;

    @Override
    public String toString() {
	return StringUtils.decapitalize(super.toString());
    }
}
