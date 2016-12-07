package com.company.entity.impl.object3d;

import com.company.entity.generic.Coordinates;
import com.company.entity.generic.Entity;
import com.sun.javafx.geom.Edge;
import sun.security.provider.certpath.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Object3D implements Entity{

    private Coordinates3D coord;
    private List<Face3D> faces;

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
}
