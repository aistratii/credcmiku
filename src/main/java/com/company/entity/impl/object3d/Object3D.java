package com.company.entity.impl.object3d;

import com.company.entity.generic.Coordinates;
import com.company.entity.generic.Entity;
import sun.security.provider.certpath.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Object3D implements Entity{

    private Coordinates3D coord;
    private List<Vertex3D> vertexes;

    public Object3D(){
        coord = new Coordinates3D();
        vertexes = new ArrayList<>();
    }

    public Object3D(Object3D that){
        this.coord = that.getCoord();
        this.vertexes = that.getVertexes();
    }

    public void addVertex(Vertex3D vtx){
        vertexes.add(vtx);
    }


    public Object3D setVertexes(List<Vertex3D> vertexes) {
        this.vertexes = vertexes;
        return this;
    }

    @Override
    public Coordinates3D getCoord() {
        return coord;
    }

    public List<Vertex3D> getVertexes() {
        return vertexes;
    }

    @Override
    public Object3D setCoord(Coordinates coord) {
        this.coord = (Coordinates3D) coord;
        return this;
    }

    @Override
    public String toString() {
        return "Object3D{" +
                "coord=" + coord +
                ", vertexes=" + vertexes +
                '}';
    }
}
