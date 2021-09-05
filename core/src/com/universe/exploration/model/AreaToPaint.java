package com.universe.exploration.model;

import java.util.List;
import java.util.Vector;

public class AreaToPaint {
    private List<Vector> paintSelected;
    private List<Vector> paintRed;

    private AreaToPaint(Builder builder) {
        paintSelected = builder.paintSelected;
        paintRed = builder.paintRed;
    }

    public static final class Builder {
        private List<Vector> paintSelected;
        private List<Vector> paintRed;

        public Builder() {
        }

        public Builder paintSelected(List<Vector> val) {
            paintSelected = val;
            return this;
        }

        public Builder paintRed(List<Vector> val) {
            paintRed = val;
            return this;
        }

        public AreaToPaint build() {
            return new AreaToPaint(this);
        }
    }
}
