package com.company.processor.renderer.generic;

import com.company.entity.generic.Entity;
import com.company.entity.impl.object3d.Object3D;

import java.awt.image.BufferedImage;
import java.util.List;

public abstract class PreRenderer {
   // protected abstract <T extends Entity> List<T> rotate(List<T> objects);
    protected abstract List<Object3D> rotateObjects(List<Object3D> objects);
    protected abstract float[] rotate(float x, float y, float sin, float cos);
    public abstract void run();
    public abstract void changeRenderer(Renderer.RendererType rendererType);
}
