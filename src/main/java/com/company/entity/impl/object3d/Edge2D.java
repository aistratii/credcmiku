package com.company.entity.impl.object3d;

public class Edge2D {
    private Vertex2D vtx1, vtx2;

    public Edge2D(Vertex2D vtx1, Vertex2D vtx2) {
        this.vtx1 = vtx1;
        this.vtx2 = vtx2;
    }

    public Vertex2D getVtx1() {
        return vtx1;
    }

    public Vertex2D getVtx2() {
        return vtx2;
    }

    @Override
    public String toString() {
        return "Edge2D{" +
                "vtx1=" + vtx1 +
                ", vtx2=" + vtx2 +
                '}';
    }
}
