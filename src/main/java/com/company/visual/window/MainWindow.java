package com.company.visual.window;

import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.Object3D;
import com.company.objectloader.ObjectLoader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

import static java.util.Arrays.asList;

public class MainWindow extends JFrame{
    private Viewport currentViewPort;

    public MainWindow(String name, int width, int height){
        super(name);
        this.setSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void addViewport(Viewport viewport){
        if (currentViewPort != null)
            this.remove(currentViewPort);

        this.currentViewPort = viewport;
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

    public List<Object3D> openFile() {
        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new FileNameExtensionFilter("*.OBJ file", "obj"));
        fc.showOpenDialog(this);

        Object3D object3D = null;
        try {
            object3D = ObjectLoader.fromFile(fc.getSelectedFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        object3D.setCoord(new Coordinates3D());
        object3D.getCoord();

        return asList(object3D);
    }

    enum Layouts{
        GRID
    }
}
