package com.universe.exploration.component.boardgrid;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public class GridNode  {
    private ImageButton imageButton;
    private Integer x;
    private Integer y;
    private Integer index;

    public GridNode(ImageButton imageButton, Integer coordinateX, Integer coordinateY) {
        this.imageButton = imageButton;
        this.x = coordinateX;
        this.y = coordinateY;
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    public void setImageButton(ImageButton imageButton) {
        this.imageButton = imageButton;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
