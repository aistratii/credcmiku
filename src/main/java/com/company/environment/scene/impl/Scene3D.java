package com.company.environment.scene.impl;

import com.company.entity.generic.Entity;
import com.company.entity.impl.object3d.Object3D;
import com.company.environment.scene.generic.Scene;
import com.company.processor.renderer.generic.Renderer;
import com.company.processor.renderer.impl.Renderer3D;

import java.util.List;

public class Scene3D implements Scene {
    private List<Object3D> objects;
    private Renderer3D renderer;

    public Scene3D(List<Object3D> objects, Renderer3D renderer) {
        this.objects = objects;
        this.renderer = renderer;
    }

    @Override
    public List<Object3D> getObjects() {
        return objects;
    }

    @Override
    public Renderer3D getRenderer() {
        return renderer;
    }
}
