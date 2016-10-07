package com.company.entity.impl.object3d;

import com.company.entity.generic.Coordinates;
import com.company.entity.generic.Entity;

import java.util.List;

public class Object3D implements Entity{

    private Coordinates3D coord;
    private List<Vertex3D> vertexes;


    public <T extends Coordinates>void setCoord(T coord) {
        this.coord = (Coordinates3D) coord;
    }

    public Object3D addVertexes(List<Vertex3D> vertexes) {
        this.vertexes = vertexes;
        return this;
    }

    public Object3D addCoord(Coordinates3D coord) {
        this.coord = coord;
        return this;
    }

    public Coordinates3D getCoord() {
        return coord;
    }

    public List<Vertex3D> getVertexes() {
        return vertexes;
    }

}
