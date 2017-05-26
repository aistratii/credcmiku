package com.company.context.impl;

import com.company.container.scene.generic.Scene;
import com.company.container.scene.impl.Scene3D;
import com.company.context.generic.Connector;
import com.company.context.generic.RandomContext;
import com.company.context.generic.SceneContext;
import com.company.entity.generic.Entity;
import com.company.entity.impl.object3d.Object3D;

import java.util.List;

public class RandomContextImpl implements RandomContext{
    private SceneContext sceneContext;
    private List<? extends Connector> connectors;

    //boundbox
    private int width, height;

    @Override
    public void setContext(SceneContext ctx) {
        sceneContext = ctx;
    }

    @Override
    public void triggerCheck() {

    }

    @Override
    public void addConnectors(List list) {
        connectors.addAll(list);
    }
}
