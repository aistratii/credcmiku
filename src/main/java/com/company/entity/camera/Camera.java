package com.company.entity.camera;

import com.company.entity.generic.Coordinates;
import com.company.entity.generic.Entity;
import com.company.entity.generic.EntityProperties;
import com.company.entity.generic.EntityProperty;
import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.EntityCameraProperties;
import com.company.entity.impl.object3d.EntityPropertiesObject3D;

public class Camera implements Entity {
    private EntityProperties entityProperties = new EntityCameraProperties();

    private Coordinates3D coord;
    private float focus;
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
    public void addAdditionalProperty(EntityProperty property) {
        entityProperties.addProperty(property);
    }

    @Override
    public EntityProperties getAdditionalProperties() {
        return entityProperties;
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
}
