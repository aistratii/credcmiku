package com.company.container.scene.impl;

import com.company.entity.impl.object3d.Object3D;
import com.company.container.scene.generic.Scene;

import java.util.ArrayList;
import java.util.List;

public class Scene3D implements Scene<Object3D> {
    private List<Object3D> objects;

    public Scene3D(List<Object3D> objects) {
        this.objects = objects;
    }

    public Scene3D(){
        this.objects = new ArrayList<>();
    }

    @Override
    public List<Object3D> getEntities() {
        return objects;
    }

    @Override
    public void addEntity(Object3D entity) {
        this.objects.add(entity);
    }
}
