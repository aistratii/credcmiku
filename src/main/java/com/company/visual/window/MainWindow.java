package com.company.visual.window;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    private Viewport viewportPanel;

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

    public void setLayout(Layouts layout){
        switch (layout){
            case GRID: {
                setLayout(new GridLayout());
                repaint();
                revalidate();
                break;
            }
        }
    }

    enum Layouts{
        GRID
    }
}
