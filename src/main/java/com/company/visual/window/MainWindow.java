package com.company.visual.window;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    public MainWindow(String name, int width, int height){
        super(name);
        this.setSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void addViewport(Viewport viewport){
        add(viewport);
        repaint();
        revalidate();
    }
}
