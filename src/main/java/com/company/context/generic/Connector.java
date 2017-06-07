package com.company.context.generic;

import com.company.entity.generic.Entity;
import com.company.entity.generic.EntityProperties;
import com.company.entity.generic.EntityProperty;

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
    E getEntity();
    Set<P> getFreePorts();
    void addPort(P newPort);
}
