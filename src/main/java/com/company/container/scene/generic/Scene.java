package com.company.container.scene.generic;

import com.company.entity.generic.Entity;

import java.util.List;

public interface Scene <T extends Entity>{
    List<? extends T> getEntities();
}
