package com.company.objectloader;

import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.Object3D;
import com.company.entity.impl.object3d.Vertex3D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ObjectLoader{

    public static Object3D fromFile(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        Object3D obj = new Object3D().setCoord(new Coordinates3D());

        while(sc.hasNext()){
            String line = sc.nextLine();
            if (line.charAt(0) == 'v'){
                String [] vertexes = Arrays.copyOfRange(line.split(" "), 1, line.split(" ").length);

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
