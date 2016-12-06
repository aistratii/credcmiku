package com.company;

import com.company.entity.camera.Camera;
import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.Object3D;
import com.company.environment.scene.impl.Scene3D;
import com.company.objectloader.ObjectLoader;
import com.company.processor.renderer.RendererRegister;
import com.company.processor.renderer.generic.PreRenderer;
import com.company.processor.renderer.generic.Renderer;
import com.company.processor.renderer.impl.PreRenderer3D;
import com.company.processor.renderer.impl.Renderer3DWireframe;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        //ready the object
        Object3D object3D = ObjectLoader.fromFile("C:\\Users\\aistratii\\Desktop\\test.obj");
        List<Object3D> objects = new ArrayList<>();
        objects.add(object3D);

        object3D.setCoord(new Coordinates3D().addAngleZ(90f));
        object3D.getCoord().setX(10);


        //create scene
        Scene3D scene3D = new Scene3D(objects);

        //create the render
        Renderer renderer = new Renderer3DWireframe();
        RendererRegister.register(Renderer.RendererType.WIREFRAME, renderer);

        PreRenderer3D renderer3D =
                new PreRenderer3D(scene3D, new Camera(100, 100, 20).
                        setCoord(new Coordinates3D()
                                .addCoords(2, 0 ,0)
                                .addAngleX(90f)),
                         Renderer.RendererType.WIREFRAME);

        //

        renderer3D.run();
    }
}
