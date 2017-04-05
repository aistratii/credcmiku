package com.company.processor.renderer.generic;

import com.company.entity.generic.Entity;
import com.company.entity.impl.object3d.Object3D;
import com.company.environment.scene.generic.Scene;

import java.awt.image.BufferedImage;
import java.util.List;

public abstract class RenderInterface {
    public abstract void run();
    public abstract void changeRenderer(Renderer.RendererType rendererType);
    public abstract Renderer getRenderer();
    public abstract BufferedImage getRenderedImage();
    public abstract <T extends Scene> void setScene(T scene);
}
