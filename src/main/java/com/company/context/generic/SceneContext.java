package com.company.context.generic;

import com.company.container.scene.generic.Scene;
import com.company.entity.camera.Camera;
import com.company.entity.impl.object3d.Object3D;
import com.company.processor.renderer.generic.Renderer;

import java.awt.image.BufferedImage;

public abstract class SceneContext<T extends Scene> {
    public abstract void changeRenderer(Renderer.RendererType rendererType);
    public abstract Renderer getRenderer();
    public abstract BufferedImage getRenderedImage();
    public abstract void setScene(T scene);
    public abstract T getScene();
    public abstract Camera getMainCamera();
}
