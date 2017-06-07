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
    private final Coordinates3D coordinates;

    public ConnectorPort3D(Set<String> compatibleTypes, String currentType, Coordinates3D coordinates) {
        this.compatibleTypes = compatibleTypes;
        this.currentType = currentType;
        this.coordinates = coordinates;
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
    public void attachToItself(ConnectorPort3D slavePort) {
        connectedPort = slavePort;
        slavePort.attachTo(this);

        Coordinates3D slaveCoords = slavePort.getCoordinates();

        slaveCoords.setX(coordinates.getX());
        slaveCoords.setY(coordinates.getY());
        slaveCoords.setZ(coordinates.getZ());

        slaveCoords.setAngleX(coordinates.getAngleX() + 180);
        slaveCoords.setAngleY(coordinates.getAngleY() + 180);
        slaveCoords.setAngleZ(coordinates.getAngleZ() + 180);
    }

    @Override
    public void attachTo(ConnectorPort3D masterPort) {
        connectedPort = masterPort;
        masterPort.attachToItself(this);

        Coordinates3D masterCoord = masterPort.getCoordinates();

        coordinates.setX(masterCoord.getX());
        coordinates.setY(masterCoord.getY());
        coordinates.setZ(masterCoord.getZ());

        coordinates.setAngleX(masterCoord.getAngleX() + 180);
        coordinates.setAngleY(masterCoord.getAngleY() + 180);
        coordinates.setAngleZ(masterCoord.getAngleZ() + 180);
    }

    @Override
    public void detach() {
        ConnectorPort3D tmp = connectedPort;
        connectedPort = null;

        if (tmp != null)
            tmp.detach();
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
