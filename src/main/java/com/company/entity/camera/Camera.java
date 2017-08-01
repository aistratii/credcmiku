package com.company.entity.camera;

import com.company.entity.generic.Coordinates;
import com.company.entity.generic.Entity;
import com.company.entity.generic.EntityProperties;
import com.company.entity.generic.EntityProperty;
import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.EntityCameraProperties;
import com.company.entity.impl.object3d.EntityPropertiesObject3D;
import com.company.entity.impl.object3d.Object3D;

public class Camera implements Entity {
    private EntityProperties entityProperties = new EntityCameraProperties();

    private Coordinates3D coord;
    private float focus;
    private int width, height;
    private String name;

    public Camera(){
        this.name = Camera.ObjectNameGenerator.getName();
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
    public String getName() {
        return name;
    }

    @Override
    public Coordinates3D getCoord() {
        return coord;
    }

    public Camera(int width, int height, float focus) {
        coord = new Coordinates3D();
        this.width = width;
        this.height = height;
        this.focus = focus;
    }

    public float getFocus() {
        return focus;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private static class ObjectNameGenerator{
        private static int name = 0;

        public static String getName(){
            return "Camera-" + name++;
        }
    }
}
