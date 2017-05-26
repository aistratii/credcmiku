package com.company;

import com.company.context.impl.Connector3D;
import com.company.context.impl.RandomContextImpl;
import com.company.entity.camera.Camera;
import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.Object3D;
import com.company.container.scene.impl.Scene3D;
import com.company.mocker.Mocker;
import com.company.objectloader.ObjectLoader;
import com.company.processor.renderer.RendererRegister;
import com.company.context.generic.SceneContext;
import com.company.processor.renderer.generic.Renderer;
import com.company.context.impl.SceneContext3D;
import com.company.processor.renderer.impl.Renderer3DWireframe;
import com.company.visual.window.MainWindow;
import com.company.visual.window.Viewport;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileNotFoundException;
import java.util.List;

import static java.util.Arrays.asList;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        //Renderer
        SceneContext<Scene3D> sceneContext = initRenderer();

        //Window
        MainWindow mainWindow = new MainWindow("Interactive renderer", 500, 500);
        Viewport viewport = new Viewport(sceneContext);
        mainWindow.addViewport(viewport);

        //select the object
        List<Object3D> objects = initObject(mainWindow);

        //create scene
        initScene(sceneContext, objects);

        //fill object with additional properties


        //FIRE THIS UP MATE
        rendererWhileRotating(2f, 0.2f, 0.7f, 30, viewport, sceneContext);

        //==************==
        //test random context
        RandomContextImpl randomContext = new RandomContextImpl();
        randomContext.setContext(sceneContext);
        List<Connector3D> connectors = Mocker.getMockedConnectors((SceneContext3D) sceneContext);
        randomContext.addConnectors(connectors);
        randomContext.triggerCheck();
    }

    private static List<Object3D> initObject(MainWindow mainWindow) throws FileNotFoundException {
        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new FileNameExtensionFilter("obj file", "obj"));
        fc.showOpenDialog(mainWindow);

        Object3D object3D = ObjectLoader.fromFile(fc.getSelectedFile());

        object3D.setCoord(new Coordinates3D());
        object3D.getCoord();

        return asList(object3D);
    }

    private static void initScene(SceneContext<Scene3D> renderInterface, List<Object3D> objects) {
        Scene3D scene3D = new Scene3D(objects);
        renderInterface.setScene(scene3D);
    }

    private static SceneContext<Scene3D> initRenderer() {
        Renderer<Object3D> renderer = new Renderer3DWireframe();
        RendererRegister.register(Renderer.RendererType.WIREFRAME, renderer);

        SceneContext3D renderInterface =
                new SceneContext3D(
                        new Camera(500, 500, 400f)
                                .setCoord(new Coordinates3D().setZ(3f).setX(-0.5f).setY(-0.5f).setAngleY(30)),
                        Renderer.RendererType.WIREFRAME);
        return renderInterface;
    }

    private static void rendererWhileRotating(float degX, float degY, float degZ,
                                              int timeDelta, Viewport viewport, SceneContext<Scene3D> renderInterface) {

        renderInterface
                .getScene()
                .getEntities()
                .forEach(object ->{
                    Coordinates3D coord = object.getCoord();

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
                });
    }
}
