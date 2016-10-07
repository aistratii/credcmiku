package com.company.environment.scene;

import com.company.entity.impl.object3d.Object3D;
import com.company.processor.renderer.generic.Renderer;
import com.company.processor.renderer.impl.Renderer3D;

import java.util.List;

public class Scene {
    private List<Object3D> objects;
    private Renderer3D renderer;

    public List<Object3D> getObjects() {
        return objects;
    }

    public Renderer3D getRenderer() {
        return renderer;
    }
}
