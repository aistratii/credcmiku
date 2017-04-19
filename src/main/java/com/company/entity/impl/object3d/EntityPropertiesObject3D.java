package com.company.entity.impl.object3d;

import com.company.entity.generic.EntityProperties;
import com.company.entity.generic.EntityProperty;

import java.util.*;

public class EntityPropertiesObject3D implements EntityProperties {
    private Map<Class, EntityProperty> properties;

    public EntityPropertiesObject3D(){
        properties = new HashMap<>();
    }

    @Override
    public <T extends EntityProperty> T getProperty(Class clazz) {
        Optional<EntityProperty> result = Optional.empty();
        try{
            result = Optional.of((T)properties.get(clazz));
        } catch (ClassCastException ex){
            ex.printStackTrace();
        }
        return result.isPresent() ? (T)result.get() : null;
    };

    @Override
    public <T extends EntityProperty> void addProperty(T property) {
        properties.put(property.getClass(), property);
    }
}
