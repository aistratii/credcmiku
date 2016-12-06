package com.company.environment.scene.impl;

import com.company.entity.impl.object3d.Object3D;
import com.company.environment.scene.generic.Scene;

import java.util.ArrayList;
import java.util.List;

public class Scene3D implements Scene {
    private List<Object3D> objects;

    public Scene3D(List<Object3D> objects) {
        this.objects = objects;
    }

    public Scene3D(){
        List<Object3D> list = new ArrayList<>();
        this.objects = list;
    }

    @Override
    public List<Object3D> getObjects() {
        return objects;
    }
}
