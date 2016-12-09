package com.company.entity.impl.object3d;

public class Edge3D {
    private Vertex3D v1, v2;

    public Edge3D(Vertex3D v1, Vertex3D v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vertex3D getV1() {
        return v1;
    }

    public Vertex3D getV2() {
        return v2;
    }

    @Override
    public String toString() {
        return "Edge3D{" +
                "v1=" + v1 +
                ", v2=" + v2 +
                '}';
    }
}
