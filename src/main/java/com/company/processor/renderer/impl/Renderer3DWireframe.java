package com.company.processor.renderer.impl;

import com.company.context.generic.SceneContext;
import com.company.entity.camera.Camera;
import com.company.entity.generic.Entity;
import com.company.entity.impl.object3d.*;
import com.company.processor.renderer.generic.Renderer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.abs;

public class Renderer3DWireframe implements Renderer<Object3D>{

    private int pointSize = 4;
    private Color lineColor = new Color(255,255,255);
    private Color pointColor = new Color(100,100,200);
    BufferedImage renderedImage;

    public Renderer3DWireframe(){
    }

    @Override
    public void run(List<Object3D> objects, Camera camera) {
        List<Edge2D> projectedPoints = getProjectedPoints(objects, camera);
        renderedImage = drawOnFrame(projectedPoints, camera);
    }

    @Override
    public <T extends SceneContext> void run(T sceneContext) {
        run(sceneContext.getScene().getEntities(), sceneContext.getMainCamera());
    }

    private BufferedImage drawOnFrame(List<Edge2D> projectedPoints, Camera camera) {
        BufferedImage frame = new BufferedImage(camera.getWidth(), camera.getHeight(), BufferedImage.TYPE_INT_RGB);

        projectedPoints.forEach(edge ->{

            //drawline
            frame.getGraphics().setColor(lineColor);
            frame.getGraphics().drawLine(
                    (int)edge.getVtx1().getX() + camera.getHeight()/2, camera.getHeight()/2 - (int)edge.getVtx1().getY(),
                    (int)edge.getVtx2().getX() + camera.getHeight()/2, camera.getHeight()/2 - (int)edge.getVtx2().getY());

            //draw vertexes
            frame.getGraphics().setColor(pointColor);
            Vertex2D vtx1 = edge.getVtx1();

            if (abs(vtx1.getX()) <= camera.getWidth() && abs(vtx1.getY()) <= camera.getHeight())
                frame.getGraphics().drawOval(
                        (int)(vtx1.getX() + camera.getHeight()/2) - pointSize/2,
                        (int)(camera.getHeight()/2 - vtx1.getY()) - pointSize/2,
                        pointSize,
                        pointSize);

            Vertex2D vtx2 = edge.getVtx2();

            if (abs(vtx2.getX()) <= camera.getWidth() && abs(vtx2.getY()) <= camera.getHeight())
                frame.getGraphics().drawOval(
                        (int)(vtx2.getX() + camera.getHeight()/2) - pointSize/2,
                        (int)(camera.getHeight()/2 - vtx2.getY()) - pointSize/2,
                        pointSize,
                        pointSize);
        });

        return frame;
    }

    private List<Edge2D> getProjectedPoints(List<Object3D> objects, Camera camera){

        List<Object3D> bufferedObjects =
            objects.stream().map(object ->
                    new Object3D(object).setFaces(
                            object.getFaces().stream().map(face -> new Face3D(
                                    face.getEdges().stream().map(edge -> new Edge3D(
                                            new Transformer(edge.getV1(), object.getCoord(), camera)
                                                    .rotateVertexToGlobal().displaceVertexToGlobal().rotateVertexToCamera().displaceVertexToCamera().andGet(),
                                            new Transformer(edge.getV2(), object.getCoord(), camera)
                                                    .rotateVertexToGlobal().displaceVertexToGlobal().rotateVertexToCamera().displaceVertexToCamera().andGet()
                                    )).collect(Collectors.toList())
                            )).collect(Collectors.toList())
                    )
            ).collect(Collectors.toList());

        List<Edge2D> newEdges = new ArrayList<>();

        bufferedObjects.stream().forEach(object -> object.getFaces().forEach(face -> face.getEdges().forEach(edge -> {
            // z/x = z1/x1 ~> z/x = focus/x1
            // ^ => x1 = focus*x/z

            float newX1 = safelyDivide(camera.getFocus()*edge.getV1().getX(), edge.getV1().getZ());
            float newY1 = safelyDivide(camera.getFocus()*edge.getV1().getY(), edge.getV1().getZ());

            float newX2 = safelyDivide(camera.getFocus()*edge.getV2().getX(), edge.getV2().getZ());
            float newY2 = safelyDivide(camera.getFocus()*edge.getV2().getY(),edge.getV2().getZ());

            newEdges.add(new Edge2D(new Vertex2D(newX1, newY1), new Vertex2D(newX2, newY2)));
        })));

        return newEdges;
    }

    private float safelyDivide(float v1, float v2) {
        if (v1 == 0 && v2== 0)
            return 0f;
        else if (v2 == 0)
            return 0f;
        else return v1/v2;
    }

    @Override
    public BufferedImage getRenderedImage() {
        return renderedImage;
    }

    public void setPointSize(int pointSize) {
        this.pointSize = pointSize;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setPointColor(Color pointColor) {
        this.pointColor = pointColor;
    }
}


class Transformer{
    private Vertex3D vtx;
    private Coordinates3D objectCoord;
    private Camera camera;

    Transformer(Vertex3D vtx, Coordinates3D coordinates3D, Camera camera){
        this.vtx = new Vertex3D(vtx);
        this.objectCoord = coordinates3D;
        this.camera = camera;
    }

    Vertex3D andGet(){
        return vtx;
    }

    Transformer rotateVertexToGlobal(){
        this.vtx = new Vertex3D(rotateVertex(objectCoord, vtx));
        return this;
    }

    Transformer displaceVertexToGlobal(){
        vtx = new Vertex3D(vtx.getX() + objectCoord.getX(), vtx.getY() + objectCoord.getY(), vtx.getZ() + objectCoord.getZ());
        return this;
    }

    Transformer rotateVertexToCamera(){
        vtx = new Vertex3D(rotateVertex(camera.getCoord(), vtx));
        return this;
    }

    Transformer displaceVertexToCamera(){
        vtx = new Vertex3D(vtx.getX() + camera.getCoord().getX(), vtx.getY() + camera.getCoord().getY(), vtx.getZ() + camera.getCoord().getZ());
        return this;
    }

    Transformer scale(float scale){
        vtx = new Vertex3D(vtx.getX() * scale, vtx.getY() * scale, vtx.getZ() * scale);
        return this;
    }


    private float[] rotate(float x, float y, float sin, float cos) {
        return new float[]{x*cos - y*sin, x*sin + y*cos};
    }

    private Vertex3D rotateVertex(Coordinates3D coord, Vertex3D vertex) {
        float   x = vertex.getX(),
                y = vertex.getY(),
                z = vertex.getZ();

        //y-z
        float tmp[] = rotate( y, z, coord.getAngleXSin(), coord.getAngleXCos());
        y = tmp[0];
        z = tmp[1];

        //y-x
        tmp = rotate( y, x, coord.getAngleZSin(), coord.getAngleZCos());
        y = tmp[0];
        x = tmp[1];

        //x-z
        tmp = rotate( x, z, coord.getAngleYSin(), coord.getAngleYCos());
        x = tmp[0];
        z = tmp[1];

        return new Vertex3D(x, y, z);
    }
}