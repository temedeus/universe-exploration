package com.universe.exploration.common.tools;

import java.util.List;
import java.util.Random;

/**
 * Generic randomization tools.
 *
 * @author 6.6.2015 Teemu Puurunen
 */
public class RandomizationTools {
    /**
     * Generate random integer based on upper and lower limits.
     *
     * @param integer lower
     * @param integer upper
     * @return integer
     */
    public static final int getRandomInteger(int lower, int upper) {
        // Random rand = new Random();
        int randomNumber = (int) (Math.random() * (upper - lower + 1)) + lower;

        return randomNumber;
    }

    public static final boolean randomBoolean() {
        return (getRandomInteger(0, 1) == 1) ? true : false;
    }

    /**
     * Generate random double using given limits
     *
     * @param min
     * @param max
     * @return
     */
    public static final double getRandomDouble(double min, double max) {
        Random r = new Random();
        double randomDouble = min + (max - min) * r.nextDouble();

        return randomDouble;
    }

    /**
     * Return a random item from a list of weighted items.
     *
     * @param items
     * @return WeightedRandomizationItem
     */
    public static final WeightedRandomizationItem getWeightedRandomItem(
            List<WeightedRandomizationItem> items) {

        // Compute the total weight of all items together
        double tw = 0.0d;
        for (WeightedRandomizationItem item : items) {
            tw += item.getWeight();
        }

        double random = Math.random() * tw;
        for (WeightedRandomizationItem item : items) {

            random -= item.getWeight();
            if (random <= 0.0d) {
                return item;
            }
        }

        return items.get(0);
    }
}
