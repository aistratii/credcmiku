package com.company.processor.renderer.impl;

import com.company.entity.camera.Camera;
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

public class Renderer3D extends Renderer {

    private ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Override
    protected List<Object3D> rotateStateless(List<Object3D> objects){
        return rotateObjectStateless(objects);
    }

 /*   @Override
     protected CompletableFuture<Float> rotateAroundOrigin(float x, float y, float cos, float sin) {

        CompletableFuture<Float> cf1 = new CompletableFuture<>();
        cf1.supplyAsync(() -> x*cos - y*sin, executorService);
        return cf1;
    }*/

    protected float rotateAroundOrigin(float x, float y, float cos, float sin) {
        return x*cos - y*sin;
    }


    public void displaceObjectsStateful(List<Object3D> objects) {
        objects.stream().forEach(object -> displaceVertexesStateful(object));
    }

    private void displaceVertexesStateful(Object3D object) {
        Coordinates3D coord = object.getCoord();

        object.getVertexes().stream().forEach(vertex -> {
            vertex.setX(vertex.getX() + coord.getX());
            vertex.setY(vertex.getY() + coord.getY());
            vertex.setZ(vertex.getZ() + coord.getZ());
        });
    }

    private List<Object3D> rotateObjectStateless(List<Object3D> objects){
        List<Object3D> result =
                objects.stream().map(object -> {
                    Coordinates3D coord = object.getCoord();

                    List<Vertex3D> vertexes = object.getVertexes().stream()
                            .map(vertex -> rotateVertexStateless(coord, vertex))
                            .collect(Collectors.toList());

                    return new Object3D().setVertexes(vertexes).addCoord(coord);
                }).collect(Collectors.toList());

        return result;
    }

    private Vertex3D rotateVertexStateless(Coordinates3D coord, Vertex3D vertex) {
        float newX =
                rotateAroundOrigin( vertex.getX(), vertex.getY(), coord.getAngleXSin(), coord.getAngleXCos());

        float newY =
                rotateAroundOrigin( vertex.getY(), vertex.getZ(), coord.getAngleYSin(), coord.getAngleYCos());

        float newZ =
                rotateAroundOrigin( vertex.getZ(), vertex.getX(), coord.getAngleZSin(), coord.getAngleZCos());


        //float newX = 0f, newY = 0f, newZ = 0f;

        /*try {
            newX = xFuture.get();
            newY = yFuture.get();
            newZ = zFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        return new Vertex3D().addCoords(newX, newY, newZ);
    }

    public void rotateWithCameraStateful(List<Object3D> objects, Camera camera) {
        objects.stream().forEach(object -> {
            Coordinates3D coord = camera.getCoord();
            if (coord == null) System.out.println("null");

            List<Vertex3D> vertexes = object.getVertexes().stream()
                    .map(vertex -> rotateVertexStateless(coord, vertex))
                    .collect(Collectors.toList());

            object.setVertexes(vertexes);
        });
    }

    public void displaceObjectsWithCameraStateful(List<Object3D> objects, Camera camera) {
        objects.stream().forEach(object -> {
            object.getVertexes().stream().forEach(vertex -> {
                Coordinates3D coord = camera.getCoord();

                vertex.setX(vertex.getX() + coord.getX());
                vertex.setY(vertex.getY() + coord.getY());
                vertex.setZ(vertex.getZ() + coord.getZ());
            });
        });
    }
}
