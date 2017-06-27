package com.company.context.impl;

import com.company.context.generic.SceneContext;
import com.company.entity.camera.Camera;
import com.company.entity.impl.object3d.*;
import com.company.container.scene.impl.Scene3D;
import com.company.entity.impl.object3d.Coordinates3D;
import com.company.processor.renderer.RendererRegister;
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
    private Renderer renderer;

    public SceneContext3D(Scene3D scene, Camera camera, Renderer.RendererType rendererType) {
        this.scene = scene;
        this.camera = camera;

        renderer = RendererRegister.getRenderer(rendererType);
    }

    public SceneContext3D(Camera camera, Renderer.RendererType rendererType) {
        this.scene = new Scene3D();
        this.camera = camera;

        renderer = RendererRegister.getRenderer(rendererType);
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

    @Override
    public Camera getMainCamera() {
        return camera;
    }
}

