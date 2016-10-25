package com.company;

import com.company.entity.camera.Camera;
import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.Object3D;
import com.company.entity.impl.object3d.Vertex3D;
import com.company.environment.scene.impl.Scene3D;
import com.company.processor.renderer.impl.Renderer3D;
import com.company.processor.renderer.impl.Wireframe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Object3D object3D = loadObjectFromFile("C:\\Users\\aistratii\\Desktop\\test.obj");
        List<Object3D> objects = new ArrayList<>();
        objects.add(object3D);

        object3D.setCoord(new Coordinates3D().addAngleZ(90));

        Renderer3D renderer3D = new Renderer3D();
        Scene3D scene3D = new Scene3D(objects, renderer3D);
        Wireframe wfrm = new Wireframe(scene3D, new Camera(10, 10));

        wfrm.run();
    }

    private static Object3D loadObjectFromFile(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        Object3D obj = new Object3D().setCoord(new Coordinates3D());

        while(sc.hasNext()){
            String line = sc.nextLine();
            if (line.charAt(0) == 'v'){

                /*System.out.println("***");
                System.out.println(line);*/

                String [] vertexes = Arrays.copyOfRange(line.split(" "), 1, line.split(" ").length);

                /*System.out.println(Arrays.toString(vertexes));
                System.out.println("***");*/

                Vertex3D vtx =
                        new Vertex3D().addCoords(
                            Float.parseFloat(vertexes[0]),
                            Float.parseFloat(vertexes[1]),
                            Float.parseFloat(vertexes[2])
                        );
                obj.addVertex(vtx);
            }

        }

        return obj;
    }
}
