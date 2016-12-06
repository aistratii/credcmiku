package com.company.environment.scene.generic;

import com.company.entity.generic.Entity;

import java.util.List;

public interface Scene {
    public <T extends Entity> List<T> getObjects();
}
