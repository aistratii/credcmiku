package com.company.entity.impl.object3d;

import com.company.entity.generic.Coordinates;
import com.company.entity.generic.Entity;
import com.company.entity.generic.EntityProperties;
import com.company.entity.generic.EntityProperty;

import java.util.ArrayList;
import java.util.List;

public class Object3D implements Entity{

    private Coordinates3D coord;
    private List<Face3D> faces;
    private EntityPropertiesObject3D additionalProperties = new EntityPropertiesObject3D();

    public Object3D(){
        coord = new Coordinates3D();
        faces = new ArrayList<>();
    }

    public Object3D(List<Face3D> faces){
        this.faces = faces;
    }

    public Object3D(Object3D that){
        this.coord = that.getCoord();
        this.faces = that.getFaces();
    }

    public void addFace(Face3D face){ faces.add(face); }

    public Object3D setFaces(List<Face3D> faces) {
        this.faces = faces;
        return this;
    }

    @Override
    public Coordinates3D getCoord() {
        return coord;
    }

    public List<Face3D> getFaces() {
        return faces;
    }

    @Override
    public Object3D setCoord(Coordinates coord) {
        this.coord = (Coordinates3D) coord;
        return this;
    }

    @Override
    public void addAdditionalProperty(EntityProperty property) {
        additionalProperties.addProperty(property);
    }

    @Override
    public EntityPropertiesObject3D getAdditionalProperties() {
        return additionalProperties;
    }

    @Override
    public String toString() {
        return "Object3D{" +
                "coord=" + coord +
                ", faces=" + faces +
                '}';
    }
}
