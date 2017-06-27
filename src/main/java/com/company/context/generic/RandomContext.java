package com.company.context.generic;

import com.company.context.impl.Connector3D;

import java.util.List;

public interface RandomContext<T extends SceneContext> {
    void triggerCheck();

    void addConnectors(List<? extends Connector> connectors);
}
