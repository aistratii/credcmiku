package com.company.context.generic;

public interface RandomContext<T extends SceneContext<?>> {
    void setContext(T sceneContext);
    void triggerCheck();
}
