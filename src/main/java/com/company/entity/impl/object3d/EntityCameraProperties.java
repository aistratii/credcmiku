package com.company.entity.impl.object3d;

import com.company.entity.generic.EntityProperties;
import com.company.entity.generic.EntityProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EntityCameraProperties implements EntityProperties {
    private Map<Class, EntityProperty> properties;

    public EntityCameraProperties(){
        properties = new HashMap<>();
    }

    @Override
    public <T extends EntityProperty> T getProperty(Class<T> clazz) {
        Optional<EntityProperty> result = Optional.empty();
        try{
            result = Optional.of(properties.get(clazz));
        } catch (ClassCastException ex){
            ex.printStackTrace();
        }
        return result.isPresent() ? (T)result.get() : null;
    }

    @Override
    public <T extends EntityProperty> void addProperty(T property) {
        properties.put(property.getClass(), property);
    }
}
