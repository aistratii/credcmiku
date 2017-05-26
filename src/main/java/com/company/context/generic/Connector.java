package com.company.context.generic;

import com.company.entity.generic.Entity;
import com.company.entity.generic.EntityProperties;
import com.company.entity.generic.EntityProperty;

import java.util.Set;

public interface Connector <E extends Entity, P extends ConnectorPort> {
    void setEntity(E entity);
    E getEntity();
    Set<P> getFreePorts();
    void addPort(P newPort);
}
