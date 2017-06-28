package com.company.visual.window;

import com.company.context.generic.SceneContext;
import com.company.processor.renderer.RendererRegister;
import com.company.processor.renderer.generic.Renderer;

import javax.swing.JPanel;
import javax.swing.text.View;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Viewport extends JPanel {
    private SceneContext sceneContext;
    private Renderer render;

    public <T extends SceneContext> void setSceneContext(T sceneContext){
        this.sceneContext = sceneContext;
    }

    public <S extends SceneContext> Viewport(S sceneContext){
        this.sceneContext = sceneContext;
    }

    public void update(){
        render.run(sceneContext);
        repaint();
        paintComponent(this.getGraphics());
    }

    @Override
    public void paintComponent(Graphics g){
        BufferedImage bufferedImage = render.getRenderedImage();
        g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), null);
    }

    public void setRenderer(Renderer.RendererType renderer) {
        this.render = RendererRegister.getRenderer(renderer);
    }
}
