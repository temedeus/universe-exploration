package com.universe.exploration.common.tools;

/**
 * Randomization item that has a weight.
 *
 * @author Teemu Puurunen
 */
public class WeightedRandomizationItem {
    private double weight;

    private Object item;

    public WeightedRandomizationItem(double weight, Object item) {
        this.weight = weight;
        this.item = item;
    }

    public double getWeight() {
        return weight;
    }

    public Object getItem() {
        return item;
    }

}
