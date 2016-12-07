package com.company.processor.renderer.impl;

import com.company.entity.camera.Camera;
import com.company.entity.generic.Coordinates;
import com.company.entity.impl.object3d.*;
import com.company.environment.scene.impl.Scene3D;
import com.company.processor.renderer.RendererRegister;
import com.company.processor.renderer.generic.PreRenderer;
import com.company.processor.renderer.generic.Renderer;
import jdk.nashorn.internal.objects.annotations.Function;

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

        objects.stream().map(object ->
            new Object3D(object).setFaces(
                    object.getFaces().stream().map(face -> new Face3D(
                            face.getEdges().stream().map(edge -> new Edge3D(
                                    new Transformer(edge.getV1(), object.getCoord(), camera)
                                            .rotateVertexToGlobal().displaceVertexToGlobal().rotateVertexToCamera().displaceVertexToCamera().andGet(),
                                    new Transformer(edge.getV2(), object.getCoord(), camera)
                                            .rotateVertexToGlobal().displaceVertexToGlobal().rotateVertexToCamera().displaceVertexToCamera().andGet()
                            )).collect(Collectors.toList())
                    )).collect(Collectors.toList())
            )
        ).collect(Collectors.toList());

        bufferedObjects = objects;
        renderer.setObjects(bufferedObjects);

        /*objects = displaceObjects(objects);
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
        System.out.println();*/
    }
}

class Transformer{
    private Vertex3D vtx;
    private Coordinates3D objectCoord;
    private Camera camera;

    Transformer(Vertex3D vtx, Coordinates3D coordinates3D, Camera camera){
        this.vtx = vtx;
        this.objectCoord = coordinates3D;
        this.camera = camera;
    }

    Vertex3D andGet(){
        return vtx;
    }

    Transformer rotateVertexToGlobal(){
        this.vtx = rotateVertex(objectCoord, vtx);
        return this;
    }

    Transformer displaceVertexToGlobal(){
        vtx.setX(vtx.getX() + objectCoord.getX());
        vtx.setY(vtx.getY() + objectCoord.getY());
        vtx.setZ(vtx.getZ() + objectCoord.getZ());
        return this;
    }

    Transformer rotateVertexToCamera(){
        vtx = rotateVertex(camera.getCoord(), vtx);
        return this;
    }

    Transformer displaceVertexToCamera(){
        vtx.setX(vtx.getX() + camera.getCoord().getX());
        vtx.setY(vtx.getY() + camera.getCoord().getY());
        vtx.setZ(vtx.getZ() + camera.getCoord().getZ());
        return this;
    }


    private float[] rotate(float x, float y, float sin, float cos) {
        System.out.println(new StringBuilder()
                .append("x=")
                .append(x).append(", y=")
                .append(y).append(", sin=")
                .append(sin).append(", cos=")
                .append(cos));
        return new float[]{x*cos - y*sin, x*sin + y*cos};
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
}