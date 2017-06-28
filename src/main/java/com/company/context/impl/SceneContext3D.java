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
    private Scene3D scene;
    private Camera camera;

    public SceneContext3D(Scene3D scene, Camera camera) {
        this.scene = scene;
        this.camera = camera;
    }

    public SceneContext3D(Camera camera, Renderer.RendererType rendererType) {
        this.scene = new Scene3D();
        this.camera = camera;
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
    public Camera getCamera() {
        return camera;
    }

    @Override
    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}

