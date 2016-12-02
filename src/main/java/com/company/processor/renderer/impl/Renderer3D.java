package com.company.processor.renderer.impl;

import com.company.entity.camera.Camera;
import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.Object3D;
import com.company.entity.impl.object3d.Vertex3D;
import com.company.environment.scene.impl.Scene3D;
import com.company.processor.renderer.generic.Renderer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Renderer3D extends Renderer {

    private ExecutorService executorService = Executors.newFixedThreadPool(3);
    private Scene3D scene;
    private Camera camera;
    private RendererType rendererType;

    public Renderer3D(Scene3D scene, Camera camera, RendererType type) {
        this.scene = scene;
        this.camera = camera;
        this.rendererType = type;
    }

    public void run() {
        List<Object3D> objects = scene.getObjects();
        Renderer3D renderer = this;

        List<Object3D> rotatedObjects = renderer.rotateStateless(objects);
        //renderer.displaceObjectsStateful(rotatedObjects);

        //renderer.rotateWithCameraStateful(objects, camera);
        //renderer.displaceObjectsWithCameraStateful(objects, camera);

        objects.stream().forEach(System.out::println);
        rotatedObjects.stream().forEach(System.out::println);
    }

    @Override
    protected List<Object3D> rotateStateless(List<Object3D> objects){
        return rotateObjectStateless(objects);
    }

    protected float[] rotate(float x, float y, float sin, float cos) {
        System.out.println(new StringBuilder()
                .append(x).append(" ")
                .append(y).append(" ")
                .append(sin).append(" ")
                .append(cos));
        return new float[]{x*cos - y*sin, x*sin + y*cos};
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
        return objects.stream().map(object -> {
                    Coordinates3D coord = object.getCoord();

                    List<Vertex3D> vertexes = object.getVertexes().stream()
                            .map(vertex -> rotateVertexStateless(coord, vertex))
                            .collect(Collectors.toList());

                    return new Object3D().setVertexes(vertexes).setCoord(coord);
                }).collect(Collectors.toList());
    }

    private Vertex3D rotateVertexStateless(Coordinates3D coord, Vertex3D vertex) {
        float   x = vertex.getX(),
                y = vertex.getY(),
                z = vertex.getZ();

        //y-z
        float tmp[] = rotate( y, z, coord.getAngleXSin(), coord.getAngleXCos());
        y = tmp[0];
        z = tmp[1];

        //y-z
        tmp = rotate( y, x, coord.getAngleZSin(), coord.getAngleZCos());
        y = tmp[0];
        x = tmp[1];

        //x-z
        tmp = rotate( x, z, coord.getAngleYSin(), coord.getAngleYCos());
        x = tmp[0];
        z = tmp[1];

        return new Vertex3D().addCoords(x, y, z);
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
