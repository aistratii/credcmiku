package com.company;

import com.company.context.generic.Connector;
import com.company.context.impl.Connector3D;
import com.company.context.impl.ConnectorPort3D;
import com.company.context.impl.RandomContext;
import com.company.entity.camera.Camera;
import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.Object3D;
import com.company.container.scene.impl.Scene3D;
import com.company.objectloader.ObjectLoader;
import com.company.processor.renderer.RendererRegister;
import com.company.context.generic.SceneContext;
import com.company.processor.renderer.generic.Renderer;
import com.company.context.impl.SceneContext3D;
import com.company.processor.renderer.impl.Renderer3DWireframe;
import com.company.processor.renderer.impl.Renderer3DWireframeRandom;
import com.company.visual.window.MainWindow;
import com.company.visual.window.Viewport;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //Window
        MainWindow mainWindow = new MainWindow("Interactive renderer", 500, 500);

        //select the object
        List<Object3D> objects = mainWindow.openFile();


        //==************==
        //test random context
        RandomContext randomContext = initRandomContext();

        //init connectors based on the prototype of the object
        List<Connector> connectors = getMockedConnectors(objects.get(0));
        randomContext.addConnectors(connectors);
        randomContext.setScene(new Scene3D(randomContext.getConnectors().stream()
                        .map(connector -> (Object3D)connector.getEntity())
                        .collect(toList())));
        //realign objects
        randomContext.getScene().getEntities().forEach(entity -> entity.getCoord().setX(entity.getCoord().getX() + incr()));

        Viewport viewport = new Viewport(randomContext);
        mainWindow.addViewport(viewport);
        viewport.setRenderer(Renderer.RendererType.WIREFRAME_AND_RANDOM);
        //==************==

        //FIRE THIS UP MATE
        rendererWhileRotating(2f, 0.2f, 0.7f, 30, viewport, randomContext);
    }

    static int a = 0;
    static int incr(){return a += 1;}

    private static RandomContext initRandomContext() {
        RendererRegister.register(Renderer.RendererType.WIREFRAME_SIMPLE, new Renderer3DWireframe());
        RendererRegister.register(Renderer.RendererType.WIREFRAME_AND_RANDOM, new Renderer3DWireframeRandom());

        RandomContext randomContext = new RandomContext();
        randomContext.setCamera(new Camera(500, 500, 400f)
                .setCoord(new Coordinates3D().setZ(3f).setX(-0.5f).setY(-0.5f).setAngleY(30)));

        return randomContext;
    }

    public static List<Connector> getMockedConnectors(Object3D object) {
        List<Connector> connectors = new ArrayList<>();
        final Set<String> compatiblePortTypes = new HashSet<>(asList("Type1", "Type2", "Type3"));

        //con1
        Connector connector3D1 = new Connector3D();
        connector3D1.addPort(new ConnectorPort3D(compatiblePortTypes, "Type1", new Coordinates3D().addCoords(10, 0 , 0)));
        connector3D1.setEntity(new Object3D(object));

        connectors.add(connector3D1);

        //con2
        Connector connector3D2 = new Connector3D();
        connector3D2.addPort(new ConnectorPort3D(compatiblePortTypes, "Type2", new Coordinates3D().addCoords(0, 10 , 0)));
        connector3D2.setEntity(new Object3D(object));

        connectors.add(connector3D2);

        //con3
        Connector connector3D3 = new Connector3D();
        connector3D3.addPort(new ConnectorPort3D(compatiblePortTypes, "Type3", new Coordinates3D().addCoords(0, 0 , 10)));
        connector3D3.setEntity(new Object3D(object));

        connectors.add(connector3D3);

        return connectors;
    }

    private static void initScene(SceneContext<Scene3D> sceneContext, List<Object3D> objects) {
        Scene3D scene3D = new Scene3D(objects);
        sceneContext.setScene(scene3D);
    }

    private static void rendererWhileRotating(float degX, float degY, float degZ,
                                              int timeDelta, Viewport viewport, SceneContext<Scene3D> renderInterface) {
        while(true){
            List<Object3D> objects = new ArrayList<>(renderInterface
                    .getScene()
                    .getEntities());

            for (Object3D object: objects){
                Coordinates3D coord = object.getCoord();
                if (degX != 0f) coord.setAngleX((coord.getAngleX() + degX) % 360);
                if (degY != 0f) coord.setAngleY((coord.getAngleY() + degY) % 360);
                if (degZ != 0f) coord.setAngleZ((coord.getAngleZ() + degZ) % 360);
                viewport.update();
            }


            try {
                Thread.sleep(timeDelta);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
