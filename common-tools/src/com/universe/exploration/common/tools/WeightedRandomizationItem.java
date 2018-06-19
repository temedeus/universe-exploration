package com.universe.exploration.common.tools;

/**
 * Randomization item that has a weight.
 *
 * @author Teemu
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

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }
}
