package com.company.entity.impl.object3d;

import java.util.ArrayList;
import java.util.List;

public class Face3D {
    private List<Edge3D> edges;

    public Face3D() {
        edges = new ArrayList<>();
    }

    public Face3D(List<Edge3D> edges) {
        this.edges = edges;
    }

    public List<Edge3D> getEdges() {
        return edges;
    }

    public void addEdge(Edge3D edge){
        edges.add(edge);
    }

    public void addEdge(Vertex3D vertex1, Vertex3D vertex2){
        edges.add(new Edge3D(vertex1, vertex2));
    }

    @Override
    public String toString() {
        return "Face3D{" +
                "edges=" + edges +
                '}';
    }
}
