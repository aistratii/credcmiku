package com.company.processor.renderer.generic;

import com.company.entity.generic.Entity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Renderer {
    <T extends Entity> List<T> rotateStateless(List<T> objects);
    CompletableFuture<Float> rotateAroundOrigin(float x, float y, float sin, float cos);
}
