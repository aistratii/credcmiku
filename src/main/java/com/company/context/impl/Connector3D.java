package com.company.context.impl;

import com.company.context.generic.Connector;
import com.company.context.generic.ConnectorPort;
import com.company.entity.generic.Coordinates;
import com.company.entity.generic.Entity;
import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.Object3D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Connector3D implements Connector<Object3D, ConnectorPort3D> {
    //Object's coordinates will be relative to the center of Connector's coordinates
    private Object3D object3D;

    private String name;
    private Set<ConnectorPort3D> ports;
    private Coordinates3D coordinates3D;

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
    public Set<ConnectorPort3D> getFreePorts() {
        return ports
                .stream()
                .filter(port -> port.isFree())
                .collect(Collectors.toSet());
    }

    @Override
    public void addPort(ConnectorPort3D newPort) {
        if (ports == null) ports = new LinkedHashSet<>();
        ports.add(newPort);
    }

    @Override
    public Coordinates3D getCoordinates() {
        return coordinates3D;
    }

    @Override
    public void attachTo(ConnectorPort thisPort, ConnectorPort otherPort) {
        thisPort.attachTo(otherPort);
    }
}
