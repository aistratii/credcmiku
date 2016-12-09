package com.company.entity.impl.object3d;

import com.company.entity.generic.Coordinates;

import static java.lang.Math.*;

public class Coordinates3D implements Coordinates {

    private float x = 0f, y = 0f, z = 0f;
    private float angleX = 0f, angleY = 0f, angleZ = 0f;
    private float   angleXCos, angleXSin,
                    angleYCos, angleYSin,
                    angleZCos, angleZSin;
    private boolean isAngleXSinSet, isAngleXCosSet,
                    isAngleYSinSet, isAngleYCosSet,
                    isAngleZSinSet, isAngleZCosSet;

    public Coordinates3D addAngles(float angleX, float angleY, float angleZ) {
        this.angleX = angleX;
        this.angleY = angleY;
        this.angleZ = angleZ;
        return this;
    }

    public Coordinates3D(){};

    public Coordinates3D addCoords(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Coordinates3D setX(float x) {
        this.x = x;
        return this;
    }

    public Coordinates3D setY(float y) {
        this.y = y;
        return this;
    }

    public Coordinates3D setZ(float z) {
        this.z = z;
        return this;
    }

    public Coordinates3D setAngleX(float angleX) {
        this.angleX = angleX;
        isAngleXSinSet = isAngleXCosSet = false;
        return this;
    }

    public Coordinates3D setAngleY(float angleY) {
        this.angleY = angleY;
        isAngleYSinSet = isAngleYCosSet = false;
        return this;
    }

    public Coordinates3D setAngleZ(float angleZ) {
        this.angleZ = angleZ;
        isAngleZSinSet = isAngleZCosSet = false;
        return this;
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

    public float getAngleX() {
        return angleX;
    }

    public float getAngleY() {
        return angleY;
    }

    public float getAngleZ() {
        return angleZ;
    }


    public float getAngleXSin() {
        if (isAngleXSinSet)
            return angleXSin;
        else {
            isAngleXSinSet = true;
            angleXSin = (float) (sin(toRadians(angleX)));
            return angleXSin;
        }
    }

    public float getAngleXCos() {
        if (isAngleXCosSet)
            return angleXCos;
        else {
            isAngleXCosSet = true;
            angleXCos = (float) (cos(toRadians(angleX)));
            return angleXCos;
        }
    }

    public float getAngleYSin() {
        if (isAngleYSinSet)
            return angleYSin;
        else {
            isAngleYSinSet = true;
            angleYSin = (float) (sin(toRadians(angleY)));
            return angleYSin;
        }
    }

    public float getAngleYCos() {
        if (isAngleYCosSet)
            return angleYCos;
        else {
            isAngleYCosSet = true;
            angleYCos = (float) (cos(toRadians(angleY)));
            return angleYCos;
        }
    }

    public float getAngleZSin() {
        if (isAngleZSinSet)
            return angleZSin;
        else {
            isAngleZSinSet = true;
            angleZSin = (float) (sin(toRadians(angleZ)));
            return angleZSin;
        }
    }

    public float getAngleZCos() {
        if (isAngleZCosSet)
            return angleZCos;
        else {
            isAngleZCosSet = true;
            angleZCos = (float) (cos(toRadians(angleZ)));
            return angleZCos;
        }
    }

    @Override
    public String toString() {
        return "Coordinates3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", angleX=" + angleX +
                ", angleY=" + angleY +
                ", angleZ=" + angleZ +
                '}';
    }
}
