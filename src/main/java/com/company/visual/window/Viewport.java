package com.company.visual.window;

import com.company.processor.renderer.generic.PreRenderer;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Viewport extends JPanel {
    private PreRenderer preRenderer;
    private BufferedImage bufferedImage;

    public Viewport(){
        bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public <T extends PreRenderer> Viewport setPreRenderer(T preRenderer){
        this.preRenderer = preRenderer;
        return this;
    }

    public Viewport(PreRenderer preRenderer){
        bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        this.preRenderer = preRenderer;
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), null);
        preRenderer.run();
    }
}
