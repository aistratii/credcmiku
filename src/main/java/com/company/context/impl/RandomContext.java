package com.company.context.impl;

import com.company.container.scene.generic.Scene;
import com.company.container.scene.impl.Scene3D;
import com.company.context.generic.Connector;
import com.company.context.generic.SceneContext;
import com.company.entity.camera.Camera;
import com.company.entity.generic.Entity;
import com.company.entity.impl.object3d.Object3D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RandomContext extends SceneContext<Scene3D>{
    private List<? extends Connector> connectors = new ArrayList<>();
    private Scene3D scene;
    private Camera camera;

    public RandomContext(){
        scene = new Scene3D();
    }

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

    public List<? extends Connector> getConnectors() {
        return connectors;
    }

    public void addConnectors(List list) {
        connectors.addAll(list);
    }

    public Optional<? extends Connector> getConnectorByEntityName(String name){
        return connectors.stream()
                .filter(connector -> connector.getEntity().getName().equals(name))
                .findFirst();
    }
}
