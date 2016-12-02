package com.company;

import com.company.entity.camera.Camera;
import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.Object3D;
import com.company.environment.scene.impl.Scene3D;
import com.company.objectloader.ObjectLoader;
import com.company.processor.renderer.generic.Renderer;
import com.company.processor.renderer.impl.Renderer3D;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Object3D object3D = ObjectLoader.fromFile("C:\\Users\\aistratii\\Desktop\\test.obj");
        List<Object3D> objects = new ArrayList<>();
        objects.add(object3D);

        object3D.setCoord(new Coordinates3D().addAngleZ(90));


        Scene3D scene3D = new Scene3D(objects);
        Renderer3D renderer3D = new Renderer3D(scene3D, new Camera(10, 10), Renderer.RendererType.WIREFRAME);

        renderer3D.run();
    }


}
