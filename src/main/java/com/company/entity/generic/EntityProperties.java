package com.company.entity.generic;

public interface EntityProperties {
    <T extends EntityProperty> T getProperty(Class clazz);
    <T extends EntityProperty> void addProperty(T property);
}
