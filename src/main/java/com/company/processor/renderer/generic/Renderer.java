package com.company.processor.renderer.generic;

import com.company.context.generic.SceneContext;
import com.company.entity.camera.Camera;
import com.company.entity.generic.Entity;
import com.company.entity.impl.object3d.Object3D;

import java.awt.image.BufferedImage;
import java.util.List;

public interface Renderer<T extends Entity> {

    void run(List<T> objects, Camera camera);

    <T extends SceneContext> void run(T sceneContext);

    BufferedImage getRenderedImage();

    enum RendererType{
        WIREFRAME
    }
}
