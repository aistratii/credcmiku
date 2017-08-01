package com.company.context.impl;

import com.company.context.generic.ConnectorPort;
import com.company.entity.generic.Coordinates;
import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.Object3D;

import java.util.Set;

public class ConnectorPort3D implements ConnectorPort<Coordinates3D, ConnectorPort3D> {
    private boolean isFree;
    private ConnectorPort3D connectedPort;
    private final Set<String> compatibleTypes;
    private final String currentType;

    //Port's coordinates are relative to connector's on coordinates
    private final Coordinates3D coordinates;

    public ConnectorPort3D(Set<String> compatibleTypes, String currentType, Coordinates3D coordinates) {
        this.compatibleTypes = compatibleTypes;
        this.currentType = currentType;
        this.coordinates = coordinates;
        isFree = true;
    }

    @Override
    public boolean isConnectable(String thatType) {
        return compatibleTypes.contains(thatType);
    }

    @Override
    public boolean isFree() {
        return isFree;
    }


    @Override
    public void attachTo(ConnectorPort3D otherPort) {
        connectedPort = otherPort;
        otherPort.connectedPort = this;
        otherPort.isFree = false;
        isFree = false;
    }

    @Override
    public void detach() {
        ConnectorPort3D tmp = connectedPort;
        connectedPort = null;

        if (tmp != null)
            tmp.detach();

        tmp.isFree = true;
        isFree = true;
    }

    @Override
    public String getCurrentType() {
        return currentType;
    }

    @Override
    public Coordinates3D getCoordinates() {
        return coordinates;
    }
}
