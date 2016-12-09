package com.company.entity.impl.object3d;

import com.company.entity.generic.Coordinates;
import com.company.entity.generic.Entity;
import sun.security.provider.certpath.Vertex;

public class Vertex3D{
    private float x, y, z;

    public Vertex3D(Vertex3D that) {
        this.x = that.getX();
        this.y = that.getY();
        this.z = that.getZ();
    }

    public Vertex3D addCoords(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vertex3D(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Vertex3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
