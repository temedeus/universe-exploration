/**
 *
 */
package com.universe.exploration.survey;

import com.universe.exploration.casualty.SurveyIncidentCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes the lifeforms present in the given planet. Lifeform enumeration
 * basically works hierarchically, ie. from simplest lifeform (bacteria) to
 * civilization the number of potential causes of death for a survey crew member
 * only increases.
 *
 * @author 27.9.2015 Teemu Puurunen
 */
public enum Lifeform {
    CIVILIZED("TITLE_LIFEFORMS_CIVILIZED", 4) {
        /*
         * (non-Javadoc)
         *
         * @see com.universe.exploration.survey.Lifeform#
         * provideSurveyIncidentCategoryList()
         */
        @Override
        public List<SurveyIncidentCategory> provideSurveyIncidentCategoryList() {
            List<SurveyIncidentCategory> categories = new ArrayList<SurveyIncidentCategory>();
            categories.add(SurveyIncidentCategory.CIVILIZATION);
            categories.add(SurveyIncidentCategory.BACTERIAL);
            categories.add(SurveyIncidentCategory.ANIMAL);

            return categories;
        }
    },
    ANIMAL("TITLE_LIFEFORMS_ANIMAL", 3) {
        /*
         * (non-Javadoc)
         *
         * @see com.universe.exploration.survey.Lifeform#
         * provideSurveyIncidentCategoryList()
         */
        @Override
        public List<SurveyIncidentCategory> provideSurveyIncidentCategoryList() {
            List<SurveyIncidentCategory> categories = new ArrayList<SurveyIncidentCategory>();
            categories.add(SurveyIncidentCategory.ANIMAL);
            categories.add(SurveyIncidentCategory.BACTERIAL);

            return categories;
        }
    },
    VEGETATION("TITLE_LIFEFORMS_VEGETATION", 2) {
        /*
         * (non-Javadoc)
         *
         * @see com.universe.exploration.survey.Lifeform#
         * provideSurveyIncidentCategoryList()
         */
        @Override
        public List<SurveyIncidentCategory> provideSurveyIncidentCategoryList() {
            List<SurveyIncidentCategory> categories = new ArrayList<SurveyIncidentCategory>();
            categories.add(SurveyIncidentCategory.BACTERIAL);

            return categories;
        }

    },
    BACTERIAL("TITLE_LIFEFORMS_BACTERIAL", 1) {
        /*
         * (non-Javadoc)
         *
         * @see com.universe.exploration.survey.Lifeform#
         * provideSurveyIncidentCategoryList()
         */
        @Override
        public List<SurveyIncidentCategory> provideSurveyIncidentCategoryList() {
            List<SurveyIncidentCategory> categories = new ArrayList<SurveyIncidentCategory>();
            categories.add(SurveyIncidentCategory.BACTERIAL);

            return categories;
        }
    },

    NONE("TITLE_LIFEFORMS_NONE", 0);

    private final String localKey;

    private final int rank;

    /**
     * @return the localKey
     */
    public String getLocalKey() {
        return localKey;
    }

    Lifeform(String localKey, int rank) {
        this.localKey = localKey;
        this.rank = rank;
    }

    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    public List<SurveyIncidentCategory> provideSurveyIncidentCategoryList() {
        return null;
    }
}
