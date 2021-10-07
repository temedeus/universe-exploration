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
            return 0 + height / 2 + offsetY;
        }
    }, MIDDLE_TOP() {
        @Override
        public float calculatePositionX(float width, float height, float offsetX) {
            return Gdx.graphics.getWidth() / 2 - width / 2 + offsetX;
        }

        @Override
        public float calculatePositionY(float width, float height, float offsetY) {
            return Gdx.graphics.getHeight() - height + offsetY;
        }
    }, CENTER() {
        @Override
        public float calculatePositionX(float width, float height, float offsetX) {
            return Gdx.graphics.getWidth() / 2 - width / 2 + offsetX;
        }

        @Override
        public float calculatePositionY(float width, float height, float offsetY) {
            return Gdx.graphics.getHeight() / 2 - height / 2 + offsetY;
        }
    }, MIDDLE_BOTTOM() {
        @Override
        public float calculatePositionX(float width, float height, float offsetX) {
            return Gdx.graphics.getWidth() / 2 - (width / 2) + offsetX;
        }

        @Override
        public float calculatePositionY(float width, float height, float offsetY) {
            return 0 + height / 2 + offsetY;
        }
    }, RIGHT_TOP, RIGHT_MIDDLE, RIGHT_BOTTOM() {
        @Override
        public float calculatePositionX(float width, float height, float offsetX) {
            return Gdx.graphics.getWidth() - width + offsetX;
        }

        @Override
        public float calculatePositionY(float width, float height, float offsetY) {
            return 0 + height / 2 + offsetY;
        }
    };

    public float calculatePositionX(float width, float height, float offsetX) {
        return 0;
    }

    public float calculatePositionY(float width, float height, float offsetY) {
        return 0;
    }
}