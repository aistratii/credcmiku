package com.company.processor.renderer.impl;

import com.company.entity.camera.Camera;
import com.company.entity.impl.object3d.Object3D;
import com.company.environment.scene.impl.Scene3D;

import java.util.List;

public class Wireframe implements Runnable {

    private Scene3D scene;
    private Camera camera;

    public Wireframe(Scene3D scene, Camera camera) {
        this.scene = scene;
        this.camera = camera;
    }

    public void run() {
        List<Object3D> objects = scene.getObjects();
        Renderer3D renderer = scene.getRenderer();

        List<Object3D> rotatedObjects = renderer.rotateStateless(objects);
        //renderer.displaceObjectsStateful(rotatedObjects);

        //renderer.rotateWithCameraStateful(objects, camera);
        //renderer.displaceObjectsWithCameraStateful(objects, camera);

        objects.stream().forEach(System.out::println);
        rotatedObjects.stream().forEach(System.out::println);
    }
}
