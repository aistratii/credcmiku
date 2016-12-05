package com.company.objectloader;

import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.Object3D;
import com.company.entity.impl.object3d.Vertex3D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ObjectLoader{

    public static Object3D fromFile(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        Object3D obj = new Object3D().setCoord(new Coordinates3D());

        while(sc.hasNext()){
            String line = sc.nextLine();
            if (line.charAt(0) == 'v'){
                //Ver1
                //String [] buffer = Arrays.copyOfRange(line.split(" "), 1, line.split(" ").length);

                //Ver2
                String buffer[] = Arrays
                        .stream(line.split(" "))
                        .filter(e ->{
                            try {
                                Float.parseFloat(e);
                                return true;
                            } catch(Exception ex){return false;}
                        })
                        .collect(Collectors.toList()).toArray(new String[0]);

                Vertex3D vtx =
                        new Vertex3D(
                                Float.parseFloat(buffer[0]),
                                Float.parseFloat(buffer[1]),
                                Float.parseFloat(buffer[2])
                        );
                obj.addVertex(vtx);
            }
        }
        return obj;
    }
}
