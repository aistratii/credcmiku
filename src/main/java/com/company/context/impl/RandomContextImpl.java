package com.company.context.impl;

import com.company.container.scene.generic.Scene;
import com.company.container.scene.impl.Scene3D;
import com.company.context.generic.RandomContext;
import com.company.context.generic.SceneContext;
import com.company.entity.generic.Entity;
import com.company.entity.impl.object3d.Object3D;

import java.util.List;

public class RandomContextImpl implements RandomContext<SceneContext3D> {
    private SceneContext<Scene3D> sceneContext;
    //boundbox
    private int width, height;

    //kind of shapes that are to be generated?
    List<Object3D> objects;

    //object needs connectors
    List<Connector3D> connectors;

    //cleaner of the old objects

    @Override
    public void setContext(SceneContext3D ctx) {
        sceneContext = ctx;
    }

    @Override
    public void triggerCheck() {

    }
}
