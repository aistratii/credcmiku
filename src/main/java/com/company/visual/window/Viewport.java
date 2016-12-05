package com.company.visual.window;

import com.company.environment.scene.generic.Scene;
import com.company.environment.scene.impl.Scene3D;
import com.company.processor.renderer.generic.Renderer;
import com.company.processor.renderer.impl.Renderer3D;

import javax.swing.*;
import java.awt.*;

public class Viewport extends JPanel {
    private Scene scene;
    private Renderer renderer;

    public <T extends Scene> Viewport setScene(T scene){
        this.scene = scene;
        return this;
    }

    public <T extends Renderer> Viewport setRenderer(T renderer){
        this.renderer = renderer;
        return this;
    }

    public Viewport(Scene scene, Renderer renderer){
        this.scene = scene;
        this.renderer = renderer;
    }

    @Override
    public void paintComponent(Graphics g){

    }
}
