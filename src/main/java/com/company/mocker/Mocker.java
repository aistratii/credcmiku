package com.company.mocker;

import com.company.context.generic.SceneContext;
import com.company.context.impl.Connector3D;
import com.company.context.impl.ConnectorPort3D;
import com.company.context.impl.SceneContext3D;
import com.company.entity.impl.object3d.Object3D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

public class Mocker {
    public static List<Connector3D> getMockedConnectors(SceneContext3D context3D) {
        List<Connector3D> connectors = new ArrayList<>();
        final Set<String> compatiblePortTypes = new HashSet<>(asList("Type1", "Type2", "Type3"));

        //con1
        Connector3D connector3D1 = new Connector3D();
        connector3D1.addPort(new ConnectorPort3D(compatiblePortTypes, "Type1"));
        connector3D1.addPort(new ConnectorPort3D(compatiblePortTypes, "Type2"));
        connector3D1.addPort(new ConnectorPort3D(compatiblePortTypes, "Type1"));
        connector3D1.setEntity(new Object3D(context3D.getScene().getEntities().get(0)));

        connectors.add(connector3D1);

        //con2
        Connector3D connector3D2 = new Connector3D();
        connector3D2.addPort(new ConnectorPort3D(compatiblePortTypes, "Type1"));
        connector3D2.addPort(new ConnectorPort3D(compatiblePortTypes, "Type2"));
        connector3D2.addPort(new ConnectorPort3D(compatiblePortTypes, "Type1"));
        connector3D2.setEntity(new Object3D(context3D.getScene().getEntities().get(0)));

        connectors.add(connector3D2);

        //con3
        Connector3D connector3D3 = new Connector3D();
        connector3D3.addPort(new ConnectorPort3D(compatiblePortTypes, "Type1"));
        connector3D3.addPort(new ConnectorPort3D(compatiblePortTypes, "Type2"));
        connector3D3.addPort(new ConnectorPort3D(compatiblePortTypes, "Type1"));
        connector3D3.setEntity(new Object3D(context3D.getScene().getEntities().get(0)));

        connectors.add(connector3D3);

        return connectors;
    }
}
