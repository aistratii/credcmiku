package com.company.context.impl;

import com.company.context.generic.Connector;
import com.company.context.generic.ConnectorPort;
import com.company.entity.generic.Coordinates;
import com.company.entity.generic.Entity;
import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.Object3D;

import java.util.*;
import java.util.stream.Collectors;

public class Connector3D implements Connector<Object3D, ConnectorPort3D> {
    //Object's coordinates will be relative to the center of Connector's coordinates
    private Object3D object3D;
    private boolean isSlave = false;
    private String name;

    //Ports will have their coordinates relative to the center of Connector's Coordinates r
    private List<ConnectorPort3D> ports;
    private Coordinates3D coordinates3D;

    public Connector3D(){
        this.coordinates3D = new Coordinates3D();
    }

    @Override
    public void setEntity(Object3D entity) {
        this.name = entity.getName() +" - [Connector]";
        this.object3D = entity;
    }

    @Override
    public void setCoordiantes(Coordinates coordiantes) {
        this.coordinates3D = (Coordinates3D)coordiantes;
    }

    @Override
    public Object3D getEntity() {
        return this.object3D;
    }

    @Override
    public List<ConnectorPort3D> getFreePorts() {
        return ports
                .stream()
                .filter(port -> port.isFree())
                .collect(Collectors.toList());
    }

    @Override
    public List<ConnectorPort3D> getBusyPorts(){
        return ports
                .stream()
                .filter(port -> !port.isFree())
                .collect(Collectors.toList());
    }

    @Override
    public void addPort(ConnectorPort3D newPort) {
        if (ports == null) ports = new ArrayList<>();
        ports.add(newPort);
    }

    @Override
    public Coordinates3D getCoordinates() {
        return coordinates3D;
    }

    @Override
    public boolean isCompatible(ConnectorPort3D connectorPort) {
        return ports.stream()
                .filter(port -> port.isConnectable(connectorPort.getCurrentType()))
                .findAny()
                .isPresent();
    }

    @Override
    public void attachTo(ConnectorPort thisPort, ConnectorPort otherPort) {
        thisPort.attachTo(otherPort);
        this.isSlave = true;
    }

    @Override
    public void detach() {
        ports.stream().forEach(port -> {
            if (!port.isFree())
                port.detach();
        });
        this.isSlave = false;
    }

    @Override
    public boolean isSlave() {
        return isSlave;
    }
}
