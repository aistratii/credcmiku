package com.company;

import com.company.entity.camera.Camera;
import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.Object3D;
import com.company.environment.scene.impl.Scene3D;
import com.company.objectloader.ObjectLoader;
import com.company.processor.renderer.RendererRegister;
import com.company.processor.renderer.generic.Renderer;
import com.company.processor.renderer.impl.RenderInterface3D;
import com.company.processor.renderer.impl.Renderer3DWireframe;
import com.company.visual.window.MainWindow;
import com.company.visual.window.Viewport;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        //ready the object
        Object3D object3D = ObjectLoader.fromFile("C:\\Users\\aistratii\\Desktop\\test.obj");
        List<Object3D> objects = new ArrayList<>();
        objects.add(object3D);

        object3D.setCoord(new Coordinates3D());
        object3D.getCoord();

        //create scene
        Scene3D scene3D = new Scene3D(objects);

        //create the render
        Renderer renderer = new Renderer3DWireframe();
        RendererRegister.register(Renderer.RendererType.WIREFRAME, renderer);

        RenderInterface3D renderInterface =
                new RenderInterface3D(scene3D,
                        new Camera(500, 500, 400f)
                                .setCoord(new Coordinates3D().setZ(3f).setX(-0.5f).setY(-0.5f).setAngleY(30)),
                        Renderer.RendererType.WIREFRAME);

        //Window
        MainWindow mainWindow = new MainWindow("Interactive renderer", 500, 500);
        Viewport viewport = new Viewport(renderInterface);
        mainWindow.addViewport(viewport);

        //select the object
        JFileChooser fc = new JFileChooser();
        //fc.showOpenDialog(MainWindow.this);

        //render
        rendererWhileRotating(2f, 0.2f, 0.7f, 30, viewport, object3D);
    }

    private static void rendererWhileRotating(float degX, float degY, float degZ,
                                              int timeDelta, Viewport viewport, Object3D object3D) {

        Coordinates3D coord = object3D.getCoord();

        while(true){

            if (degX != 0f) coord.setAngleX((coord.getAngleX() + degX) % 360);
            if (degY != 0f) coord.setAngleY((coord.getAngleY() + degY) % 360);
            if (degZ != 0f) coord.setAngleZ((coord.getAngleZ() + degZ) % 360);
            viewport.update();
            try {
                Thread.sleep(timeDelta);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
