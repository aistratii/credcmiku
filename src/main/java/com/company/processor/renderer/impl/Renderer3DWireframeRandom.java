package com.company.processor.renderer.impl;

import com.company.context.generic.SceneContext;
import com.company.context.impl.RandomContext;
import com.company.entity.camera.Camera;
import com.company.entity.impl.object3d.Edge2D;
import com.company.entity.impl.object3d.Object3D;

import java.util.List;

public class Renderer3DWireframeRandom extends Renderer3DWireframe {
    @Override
    public <T extends SceneContext> void run(T sceneContext) {
        //((RandomContext)sceneContext).getConnectors()
        List<Object3D> entities = null;


        run(entities, sceneContext.getCamera());
    }
}
