package com.company.processor.renderer.generic;

import com.company.entity.impl.object3d.Object3D;

import java.util.List;

public abstract class Renderer {
   // protected abstract <T extends Entity> List<T> rotateStateless(List<T> objects);
    protected abstract List<Object3D> rotateStateless(List<Object3D> objects);
    protected abstract float[] rotate(float x, float y, float sin, float cos);


    public enum RendererType{
        WIREFRAME
    }
}
