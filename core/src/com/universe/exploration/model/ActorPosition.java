package com.universe.exploration.model;


import com.badlogic.gdx.Gdx;

public enum ActorPosition {
    LEFT_TOP() {
        @Override
        public float calculatePositionX(float width, float height, float offsetX) {
            return 0 + offsetX;
        }

        @Override
        public float calculatePositionY(float width, float height, float offsetY) {
            return 0 + offsetY;
        }
    }, LEFT_MIDDLE {
        @Override
        public float calculatePositionX(float width, float height, float offsetX) {
            return 0 + offsetX;
        }

        @Override
        public float calculatePositionY(float width, float height, float offsetY) {
            return Gdx.graphics.getHeight() / 2 + height / 2 + offsetY;
        }
    }, LEFT_BOTTOM() {
        @Override
        public float calculatePositionX(float width, float height, float offsetX) {
            return 0 + offsetX;
        }

        @Override
        public float calculatePositionY(float width, float height, float offsetY) {
            return Gdx.graphics.getHeight() - height + offsetY;
        }
    }, MIDDLE_TOP, CENTER() {
        @Override
        public float calculatePositionX(float width, float height, float offsetX) {
            return Gdx.graphics.getWidth() / 2 - width / 2 + offsetX;
        }

        @Override
        public float calculatePositionY(float width, float height, float offsetY) {
            return Gdx.graphics.getHeight() / 2 - height / 2 + offsetY;
        }
    }, MIDDLE_BOTTOM, RIGHT_TOP, RIGHT_MIDDLE, RIGHT_BOTTOM;

    public float calculatePositionX(float width, float height, float offsetX) {
        return 0;
    }

    public float calculatePositionY(float width, float height, float offsetY) {
        return 0;
    }
}