package com.company.context.impl;

import com.company.context.generic.ConnectorPort;
import com.company.entity.impl.object3d.Object3D;

import java.util.Set;

public class ConnectorPort3D implements ConnectorPort {
    private boolean isFree;
    private ConnectorPort3D connectedPort;
    private final Set<String> compatibleTypes;
    private final String currentType;

    public ConnectorPort3D(Set<String> compatibleTypes, String currentType) {
        this.compatibleTypes = compatibleTypes;
        this.currentType = currentType;
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
    public void attach(ConnectorPort otherPort) {
        connectedPort = (ConnectorPort3D) otherPort;
        otherPort.attach(this);
    }

    @Override
    public void detach() {
        connectedPort.detach();
        connectedPort = null;
    }
}
