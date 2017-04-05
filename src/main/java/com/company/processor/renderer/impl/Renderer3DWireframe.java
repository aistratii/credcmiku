package com.company.processor.renderer.impl;

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

public class Renderer3DWireframe implements Renderer{

    private int pointSize = 4;
    private Color lineColor = new Color(255,255,255);
    private Color pointColor = new Color(100,100,200);
    private Camera camera;
    private List<Object3D> objects;
    BufferedImage renderedImage;

    @Override
    public void run() {
        List<Edge2D> projectedPoints = getProjectedPoints();
        renderedImage = drawOnFrame(projectedPoints);
    }

    private BufferedImage drawOnFrame(List<Edge2D> projectedPoints) {
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

    private List<Edge2D> getProjectedPoints(){

        List<Edge2D> newEdges = new ArrayList<>();

        objects.stream().forEach(object -> object.getFaces().forEach(face -> face.getEdges().forEach(edge -> {
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

    @Override
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    @Override
    public <T extends Entity> void setObjects(List<T> objects) {
        this.objects = objects.stream()
                .filter(v -> v instanceof Object3D)
                .map(v -> (Object3D) v)
                .collect(Collectors.toList());
    }

    public void setPointSizeh(int pointSize) {
        this.pointSize = pointSize;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setPointColor(Color pointColor) {
        this.pointColor = pointColor;
    }
}
