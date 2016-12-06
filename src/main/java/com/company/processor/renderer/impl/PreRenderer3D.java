package com.company.processor.renderer.impl;

import com.company.entity.camera.Camera;
import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.Object3D;
import com.company.entity.impl.object3d.Vertex3D;
import com.company.environment.scene.impl.Scene3D;
import com.company.processor.renderer.RendererRegister;
import com.company.processor.renderer.generic.PreRenderer;
import com.company.processor.renderer.generic.Renderer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class PreRenderer3D extends PreRenderer {

    private ExecutorService executorService = Executors.newFixedThreadPool(3);
    private Scene3D scene;
    private Camera camera;
    private List<Object3D> bufferedObjects;
    private Renderer renderer;

    public PreRenderer3D(Scene3D scene, Camera camera, Renderer.RendererType rendererType) {
        this.scene = scene;
        this.camera = camera;
        bufferedObjects = new ArrayList<>();

        renderer = RendererRegister.getRenderer(rendererType);
        renderer.setCamera(camera);
    }

    @Override
    public void run() {
        repositionObjects();
        renderer.run();
        try {
            ImageIO.write(renderer.getRenderedImage(), "jpg", new FileOutputStream(new File("C:\\Users\\aistratii\\desktop\\output.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeRenderer(Renderer.RendererType rendererType) {
        this.renderer = RendererRegister.getRenderer(rendererType);
    }

    private void repositionObjects(){
        List<Object3D> objects = scene.getObjects();

        objects = displaceObjects(objects);
        objects = rotateObjects(objects);
        objects = displaceObjectsWithCameraStateful(objects, camera);
        objects = rotateWithCamera(objects, camera);

        bufferedObjects = objects;
        renderer.setObjects(bufferedObjects);

        System.out.println("Printing original objects...");
        scene.getObjects().stream().forEach(System.out::println);
        System.out.println();

        System.out.println("Printing rotated objects...");
        objects.stream().forEach(System.out::println);
        System.out.println();
    }

    @Override
    protected List<Object3D> rotateObjects(List<Object3D> objects){
        return objects.stream().map(object -> {
            Coordinates3D coord = object.getCoord();

            List<Vertex3D> vertexes = object.getVertexes().stream()
                    .map(vertex -> rotateVertex(coord, vertex))
                    .collect(Collectors.toList());

            return new Object3D(object).setVertexes(vertexes);
        }).collect(Collectors.toList());
    }

    @Override
    protected float[] rotate(float x, float y, float sin, float cos) {
        /*System.out.println(new StringBuilder()
                .append("x=")
                .append(x).append(", y=")
                .append(y).append(", sin=")
                .append(sin).append(", cos=")
                .append(cos));*/
        return new float[]{x*cos - y*sin, x*sin + y*cos};
    }

    private List<Object3D> displaceObjects(List<Object3D> objects) {
        return objects.stream().
                map(object -> new Object3D(object)
                        .setVertexes(displaceVertexes(object, object.getCoord())))
                .collect(Collectors.toList());
    }

    private List<Vertex3D> displaceVertexes(Object3D object, Coordinates3D coord) {

        return object.getVertexes().stream().map(vertex ->
            new Vertex3D(
                    vertex.getX() + coord.getX(),
                    vertex.getY() + coord.getY(),
                    vertex.getZ() + coord.getZ())
        ).collect(Collectors.toList());
    }

    private Vertex3D rotateVertex(Coordinates3D coord, Vertex3D vertex) {
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

        return new Vertex3D(x, y, z);
    }

    private List<Object3D> rotateWithCamera(List<Object3D> objects, Camera camera) {
        return objects.stream().map(object -> {
            Coordinates3D coord = camera.getCoord();

            List<Vertex3D> vertexes = object.getVertexes().stream()
                    .map(vertex -> rotateVertex(coord, vertex))
                    .collect(Collectors.toList());

            return object.setVertexes(vertexes);
        }).collect(Collectors.toList());

    }

    private List<Object3D> displaceObjectsWithCameraStateful(List<Object3D> objects, Camera camera) {
        return objects.stream().map(object ->
            new Object3D(object).setVertexes(
                    displaceVertexes(object, camera.getCoord())
        )).collect(Collectors.toList());
    }
}