package com.company.entity.camera;

import com.company.entity.generic.Coordinates;
import com.company.entity.generic.Entity;
import com.company.entity.impl.object3d.Coordinates3D;

public class Camera implements Entity {

    private Coordinates3D coord;
    private int focus;
    private int width, height;

    public Camera(){
        coord = new Coordinates3D();
        width = 10;
        height = 10;
        focus = 10;
    }

    @Override
    public Camera setCoord(Coordinates coord) {
        this.coord = (Coordinates3D) coord;
        return this;
    }

    @Override
    public Coordinates3D getCoord() {
        return coord;
    }

    public Camera(int width, int height, int focus) {
        coord = new Coordinates3D();
        this.width = width;
        this.height = height;
        this.focus = focus;
    }

    public int getFocus() {
        return focus;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
