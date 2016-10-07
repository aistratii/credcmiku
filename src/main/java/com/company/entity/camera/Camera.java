package com.company.entity.camera;

import com.company.entity.generic.Coordinates;
import com.company.entity.generic.Entity;
import com.company.entity.impl.object3d.Coordinates3D;

public class Camera implements Entity {

    private Coordinates3D coord;
    private int width, height;

    public Camera(){
        coord = new Coordinates3D();
        width = 10;
        height = 10;
    }

    @Override
    public Camera addCoord(Coordinates coord) {
        this.coord = (Coordinates3D) coord;
        return this;
    }

    @Override
    public Coordinates3D getCoord() {
        return coord;
    }

    public Camera(int width, int height) {
        coord = new Coordinates3D();
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
