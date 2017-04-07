package com.company.processor.renderer.generic;

import com.company.container.scene.generic.Scene;
import com.company.entity.impl.object3d.Object3D;

import java.awt.image.BufferedImage;

public abstract class SceneContext<T extends Scene> {
    public abstract void run();
    public abstract void changeRenderer(Renderer.RendererType rendererType);
    public abstract Renderer getRenderer();
    public abstract BufferedImage getRenderedImage();
    public abstract void setScene(T scene);
    public abstract T getScene();
}
