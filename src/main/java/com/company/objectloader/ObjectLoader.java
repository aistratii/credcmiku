package com.company.objectloader;

import com.company.entity.impl.object3d.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ObjectLoader{

    public static Object3D fromFile(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        Object3D obj = new Object3D().setCoord(new Coordinates3D());
        List<Vertex3D> vertex3DList = new ArrayList<>();

        while(sc.hasNext()){
            String line = sc.nextLine();
            if (line.charAt(0) == 'v'){
                Float coords[] = Arrays.stream(line.split(" "))
                        .filter(element -> {
                            try{
                                Float.parseFloat(element);
                                return true;
                            } catch (Exception ex){return false;}
                        }).map(element -> Float.parseFloat(element))
                        .collect(Collectors.toList())
                        .toArray(new Float[0]);

                vertex3DList.add(new Vertex3D( coords[0], coords[1], coords[2]));
            }
            else if (line.charAt(0) == 'f'){
                List<Integer> rowIndexes =
                        Arrays.stream(line.split(" "))
                        .filter(element -> {
                            try{
                                Integer.parseInt(element);
                                return true;
                            } catch (Exception ex){return false;}
                        }).map(element -> Integer.parseInt(element))
                        .collect(Collectors.toList());

                Face3D face = new Face3D();

                for (int i = 0; i < rowIndexes.size() -1; i++){
                    face.addEdge(new Edge3D(vertex3DList.get(rowIndexes.get(i) -1), vertex3DList.get(rowIndexes.get(i +1) -1)));
                }

                obj.addFace(face);
            }
        }
        return obj;
    }
}
