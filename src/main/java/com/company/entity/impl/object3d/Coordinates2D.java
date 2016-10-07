package com.company.entity.impl.object3d;

import com.company.entity.generic.Coordinates;

public class Coordinates2D implements Coordinates{
    private float x, y;

    public Coordinates2D addX(float x) {
        this.x = x;
        return this;
    }

    public Coordinates2D addY(float y) {
        this.y = y;
        return this;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
