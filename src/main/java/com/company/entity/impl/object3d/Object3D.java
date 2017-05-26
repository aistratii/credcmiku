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
    private String name;

    public Object3D(){
        this.name = ObjectNameGenerator.getName();
        coord = new Coordinates3D();
        faces = new ArrayList<>();
    }

    public Object3D(List<Face3D> faces){
        this.name = ObjectNameGenerator.getName();
        this.faces = faces;
    }

    public Object3D(Object3D that){
        this.name = ObjectNameGenerator.getName();
        this.coord = new Coordinates3D(that.getCoord());
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

    public String getName() {
        return name;
    }

    private static class ObjectNameGenerator{
        private static int name = 0;

        public static String getName(){
            return "Object-" + name++;
        }
    }
}
