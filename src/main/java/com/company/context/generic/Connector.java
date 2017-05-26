package com.company.context.generic;

import com.company.entity.generic.Entity;
import com.company.entity.generic.EntityProperties;
import com.company.entity.generic.EntityProperty;

import java.util.Set;

public interface Connector <E extends Entity> {
    //<K extends EntityProperties> K getProperty(/*TODO*/);
    //<K extends EntityProperty> void addProperty(K property);
    void setEntity(E entity);
    E getEntity();
    Set<Port> getFreePorts();
    void addPort(Port newPort);

    interface Port<E> {
        boolean isConnectable();
        boolean isFree();
        void attach(E entity);
        void detach();
    }
}
