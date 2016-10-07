package com.company.processor.renderer.impl;

import com.company.entity.generic.Entity;
import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.Object3D;
import com.company.entity.impl.object3d.Vertex3D;
import com.company.processor.renderer.generic.Renderer;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Renderer3D implements Renderer {

    private ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Override
    public <T extends Entity> List<T> rotateStateless(List<T> objects){
        return (List<T>) rotateObjectStateless((List<Object3D>) objects);
    }

    //@Override
    public CompletableFuture<Float> rotateAroundOrigin(float x, float y, float cos, float sin) {

        CompletableFuture<Float> cf1 = new CompletableFuture<>();
        cf1.supplyAsync(() -> x*cos - y*sin, executorService);
        return cf1;
    }


    public void displaceObjectsStateful(List<Object3D> objects) {
        objects.stream().forEach(object -> displaceVertexesStateful(object));
    }

    private void displaceVertexesStateful(Object3D object) {
        object.getVertexes().stream().forEach(vertex -> {
            vertex.setX(vertex.getX() + object.getCoord().getX());
            vertex.setY(vertex.getY() + object.getCoord().getY());
            vertex.setZ(vertex.getZ() + object.getCoord().getZ());
        });
    }

    private List<Object3D> rotateObjectStateless(List<Object3D> objects){
        List<Object3D> result =
                objects.stream().map(object -> {
                    Coordinates3D coord = object.getCoord();

                    List<Vertex3D> vertexes = object.getVertexes().stream()
                            .map(vertex -> rotateVertexStateless(coord, vertex))
                            .collect(Collectors.toList());

                    return new Object3D().addVertexes(vertexes).addCoord(coord);
                }).collect(Collectors.toList());

        return result;
    }

    private Vertex3D rotateVertexStateless(Coordinates3D coord, Vertex3D vertex) {
        CompletableFuture<Float> xFuture =
                rotateAroundOrigin( vertex.getX(), vertex.getY(), coord.getAngleXSin(), coord.getAngleXCos());

        CompletableFuture<Float> yFuture =
                rotateAroundOrigin( vertex.getY(), vertex.getZ(), coord.getAngleYSin(), coord.getAngleYCos());

        CompletableFuture<Float> zFuture =
                rotateAroundOrigin( vertex.getZ(), vertex.getX(), coord.getAngleZSin(), coord.getAngleZCos());

        float newX = 0f, newY = 0f, newZ = 0f;

        try {
            newX = xFuture.get();
            newY = yFuture.get();
            newZ = zFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return new Vertex3D().addCoords(newX, newY, newZ);
    }
}
