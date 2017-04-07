package com.company.visual.window;

import com.company.processor.renderer.generic.SceneContext;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Viewport <T extends SceneContext> extends JPanel {
    private SceneContext renderInterface;

    public Viewport(){}

    public Viewport setRenderInterface(T renderInterface){
        this.renderInterface = renderInterface;
        return this;
    }

    public Viewport(T renderInterface){
        this.renderInterface = renderInterface;
    }

    public void update(){
        renderInterface.run();
        repaint();
        paintComponent(this.getGraphics());
    }

    @Override
    public void paintComponent(Graphics g){
        renderInterface.run();
        BufferedImage bufferedImage = renderInterface.getRenderer().getRenderedImage();
        g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), null);
    }
}
