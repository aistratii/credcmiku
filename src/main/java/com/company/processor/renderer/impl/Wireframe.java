package com.company.processor.renderer.impl;

import com.company.entity.impl.object3d.Object3D;
import com.company.environment.scene.Scene;

import java.util.List;

public class Wireframe implements  Runnable {

    private Scene scene;

    public Wireframe addScene(Scene scene) {
        this.scene = scene;
        return this;
    }

    public void run() {
        List<Object3D> objects = scene.getObjects();
        Renderer3D renderer = scene.getRenderer();

        List<Object3D> rotatedObjects = renderer.rotateStateless(objects);
        renderer.displaceObjectsStateful(objects);
    }
}
