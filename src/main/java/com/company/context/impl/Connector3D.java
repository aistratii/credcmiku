package com.company.context.impl;

import com.company.context.generic.Connector;
import com.company.entity.impl.object3d.Object3D;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Connector3D implements Connector<Object3D> {
    private Object3D object3D;
    private Set<Port> ports;

    @Override
    public void setEntity(Object3D entity) {
        this.object3D = entity;
    }

    @Override
    public Object3D getEntity() {
        return this.object3D;
    }

    @Override
    public Set<Port> getFreePorts() {
        return ports
                .stream()
                .filter(port -> port.isFree())
                .collect(Collectors.toSet());
    }

    @Override
    public void addPort(Port newPort) {
        if (ports == null)
            ports = new HashSet<>();

        ports.add(newPort);
    }

    class Port3D implements Port{

        @Override
        public boolean isConnectable() {
            return false;
        }

        @Override
        public boolean isFree() {
            return false;
        }

        @Override
        public void attach(Object entity) {

        }

        @Override
        public void detach() {

        }
    }
}
