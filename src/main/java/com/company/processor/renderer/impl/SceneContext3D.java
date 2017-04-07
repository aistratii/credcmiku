package com.company.processor.renderer.impl;

import com.company.entity.camera.Camera;
import com.company.entity.impl.object3d.*;
import com.company.container.scene.impl.Scene3D;
import com.company.processor.renderer.RendererRegister;
import com.company.processor.renderer.generic.SceneContext;
import com.company.processor.renderer.generic.Renderer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class SceneContext3D extends SceneContext<Scene3D> {

    private ExecutorService executorService = Executors.newFixedThreadPool(3);
    private Scene3D scene;
    private Camera camera;
    private List<Object3D> bufferedObjects;
    private Renderer renderer;

    public SceneContext3D(Scene3D scene, Camera camera, Renderer.RendererType rendererType) {
        this.scene = scene;
        this.camera = camera;
        bufferedObjects = new ArrayList<>();

        renderer = RendererRegister.getRenderer(rendererType);
        renderer.setCamera(camera);
    }

    public SceneContext3D(Camera camera, Renderer.RendererType rendererType) {
        this.scene = new Scene3D();
        this.camera = camera;
        bufferedObjects = new ArrayList<>();

        renderer = RendererRegister.getRenderer(rendererType);
        renderer.setCamera(camera);
    }

    @Override
    public void run() {
        repositionObjects();
        renderer.run();
    }

    @Override
    public void changeRenderer(Renderer.RendererType rendererType) {
        this.renderer = RendererRegister.getRenderer(rendererType);
    }

    @Override
    public Renderer getRenderer() {
        return renderer;
    }

    @Override
    public BufferedImage getRenderedImage() {
        return renderer.getRenderedImage();
    }

    @Override
    public  void setScene(Scene3D scene) {
            this.scene =  scene;
    }

    @Override
    public Scene3D getScene() {
        return scene;
    }

    private void repositionObjects(){
        List<Object3D> objects = scene.getEntities();

        objects = objects.stream().map(object ->
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
    }
}

class Transformer{
    private Vertex3D vtx;
    private Coordinates3D objectCoord;
    private Camera camera;

    Transformer(Vertex3D vtx, Coordinates3D coordinates3D, Camera camera){
        this.vtx = new Vertex3D(vtx);
        this.objectCoord = coordinates3D;
        this.camera = camera;
    }

    Vertex3D andGet(){
        return vtx;
    }

    Transformer rotateVertexToGlobal(){
        this.vtx = new Vertex3D(rotateVertex(objectCoord, vtx));
        return this;
    }

    Transformer displaceVertexToGlobal(){
        vtx = new Vertex3D(vtx.getX() + objectCoord.getX(), vtx.getY() + objectCoord.getY(), vtx.getZ() + objectCoord.getZ());
        return this;
    }

    Transformer rotateVertexToCamera(){
        vtx = new Vertex3D(rotateVertex(camera.getCoord(), vtx));
        return this;
    }

    Transformer displaceVertexToCamera(){
        vtx = new Vertex3D(vtx.getX() + camera.getCoord().getX(), vtx.getY() + camera.getCoord().getY(), vtx.getZ() + camera.getCoord().getZ());
        return this;
    }

    Transformer scale(float scale){
        vtx = new Vertex3D(vtx.getX() * scale, vtx.getY() * scale, vtx.getZ() * scale);
        return this;
    }


    private float[] rotate(float x, float y, float sin, float cos) {
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