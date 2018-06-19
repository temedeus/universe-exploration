/**
 *
 */
package com.universe.exploration.userinterface.data;


/**
 * <p>
 * Emulates {@link DataPair}.
 * </p>
 * <p>
 * Problem with running unit tests is the static class that generates Skin
 * objects. If you figure out how to mock those classes or any other way to run
 * unit tests, this class can be removed. In a sense I suppose this class is
 * theoretically a "mock" of a kind?
 * </p>
 *
 * @author 1.10.2015 Teemu Puurunen
 */
public class TestDataPair extends DataPair {
    /**
     * Use this ID to identify HUD item
     */
    private String id;

    /**
     * Explanation label
     */
    private String label;

    /**
     * Concrete value
     */
    private String value;

    /**
     * Holds a pair of labels for displaying data/value pair.
     *
     * @param label
     * @param value
     * @param skin
     */
    public TestDataPair(String id, String label, String value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }

    /**
     * @return the id
     */
    public String getIdToString() {
        return id;
    }

    /**
     * @return the label
     */
    public String getLabelToString() {
        return label;
    }

    /**
     * @return the value
     */
    public String getValueToString() {
        return value;
    }

    /**
     * Update player statistic value
     *
     * @param value
     */
    public void updateValue(String value) {
        this.value = value;
    }
}
