package com.company.context.generic;

import com.company.context.impl.ConnectorPort3D;
import com.company.entity.generic.Coordinates;
import com.company.entity.generic.Entity;
import com.company.entity.generic.EntityProperties;
import com.company.entity.generic.EntityProperty;

import java.util.List;
import java.util.Set;

/**
 * It links and object and some ports. In a way assinging somehow to an objects some ports,
 * to be connected with other ports.
 * Contains an object and ports.
 * @param <E> object type
 * @param <P> connector-type
 */
public interface Connector <E extends Entity, P extends ConnectorPort> {
    void setEntity(E entity);
    void setCoordiantes(Coordinates coordiantes);
    E getEntity();
    List<P> getFreePorts();
    List<P> getBusyPorts();

    void addPort(P newPort);
    void attachTo(ConnectorPort thisPort, ConnectorPort otherPort);
    void detach();
    boolean isSlave();
    Coordinates getCoordinates();
    boolean isCompatible(P connectorPort);
}
