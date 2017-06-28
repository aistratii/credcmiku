package com.company.context.impl;

import com.company.container.scene.generic.Scene;
import com.company.container.scene.impl.Scene3D;
import com.company.context.generic.Connector;
import com.company.context.generic.SceneContext;
import com.company.entity.camera.Camera;
import com.company.entity.impl.object3d.Object3D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RandomContext extends SceneContext<Scene3D>{
    private List<? extends Connector> connectors = new ArrayList<>();
    private Scene3D scene;
    private Camera camera;

    @Override
    public void setScene(Scene3D scene) {
        this.scene = scene;
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

    public void addConnectors(List list) {
        connectors.addAll(list);

        this.scene = new Scene3D(connectors.
                stream()
                .map(e -> (Object3D)e.getEntity())
                .collect(Collectors.toList()));

    }
}
