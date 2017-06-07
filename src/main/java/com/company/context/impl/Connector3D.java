package com.company.context.impl;

import com.company.context.generic.Connector;
import com.company.entity.impl.object3d.Object3D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Connector3D implements Connector<Object3D, ConnectorPort3D> {
    private Object3D object3D;
    private String name;
    private Set<ConnectorPort3D> ports;

    @Override
    public void setEntity(Object3D entity) {
        this.name = entity.getName() +" - [Connector]";
        this.object3D = entity;
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
}
