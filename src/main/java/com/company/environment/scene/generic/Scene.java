package com.company.environment.scene.generic;

import com.company.entity.generic.Entity;
import com.company.entity.impl.object3d.Object3D;
import com.company.processor.renderer.impl.Renderer3D;
import com.company.processor.renderer.generic.Renderer;

import javax.swing.*;
import java.util.List;

public interface Scene {
    public <T extends Entity> List<T> getObjects();

    public <T extends Renderer> T getRenderer();
}
