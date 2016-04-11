/**
 * 
 */
package com.universe.exploration.casualty;

import java.util.ArrayList;

/**
 * <p>
 * List of applicable survey incidents. Essentially this class is an array. A
 * separate class (extending ArrayList) was created to ease work of the factory
 * class {@link ApplicableSurveyIncidentFactory}. One can only use
 * {@link SurveyIncidentCategory} with this class.
 * </p>
 * 
 * @author 25.10.2015 Teemu Puurunen
 *
 */
class ApplicableSurveyIncidentCategoryList extends ArrayList<SurveyIncidentCategory> {

    /**
     * Generated serial ID.
     */
    private static final long serialVersionUID = -6512394029378977651L;

}