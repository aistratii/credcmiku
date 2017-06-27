package com.company.context.generic;

import com.company.entity.generic.Coordinates;

/**
 * It is a logical entity that is a port for the Connector.
 * It connects with other ports that has a compatible type.
 */
public interface ConnectorPort<E extends Coordinates, P extends ConnectorPort> {
    boolean isConnectable(String thatType);
    boolean isFree();
    void attachTo(P otherPort);
    void detach();
    String getCurrentType();
    E getCoordinates();
}