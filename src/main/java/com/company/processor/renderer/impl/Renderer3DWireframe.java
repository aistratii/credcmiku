package com.company.processor.renderer.impl;

import com.company.entity.camera.Camera;
import com.company.entity.generic.Entity;
import com.company.entity.impl.object3d.Object3D;
import com.company.entity.impl.object3d.Vertex2D;
import com.company.entity.impl.object3d.Vertex3D;
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

    private int lineWidth = 1;
    private Color lineColor = new Color(0,0,0);
    private Camera camera;
    private List<Object3D> objects;
    BufferedImage renderedImage;

    @Override
    public void run() {
        List<Vertex2D> projectedPoints = getProjectedPoints();
        renderedImage = drawOnFrame(projectedPoints);
    }

    private BufferedImage drawOnFrame(List<Vertex2D> projectedPoints) {
        BufferedImage frame = new BufferedImage(camera.getWidth(), camera.getHeight(), BufferedImage.TYPE_INT_RGB);

        projectedPoints.forEach(vertex ->{
            if (abs(vertex.getX()) <= camera.getWidth() && abs(vertex.getY()) <= camera.getHeight())
                frame.setRGB((int)(vertex.getX() + camera.getHeight()/2),
                        (int)(camera.getHeight()/2 - vertex.getY()),
                        new Color(150, 150, 150).getRGB());
        });

        return frame;
    }

    private List<Vertex2D> getProjectedPoints(){
        List<Vertex2D> result = new ArrayList<>();

        objects.stream()
                .map(object -> object.getVertexes())
                .forEach(list ->
                    list.forEach(vertex3D ->{
                        // z/x = z1/x1 ~> z/x = focus/x1
                        // ^ => x1 = focus*x/z

                        float newX = camera.getFocus()*vertex3D.getX()/vertex3D.getZ();
                        float newY = camera.getFocus()*vertex3D.getY()/vertex3D.getZ();

                        result.add(new Vertex2D(newX, newY));
                    })
                );

        return result;
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

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }
}
