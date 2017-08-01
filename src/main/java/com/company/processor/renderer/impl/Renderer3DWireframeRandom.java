package com.company.processor.renderer.impl;

import com.company.context.generic.Connector;
import com.company.context.generic.SceneContext;
import com.company.context.impl.Connector3D;
import com.company.context.impl.ConnectorPort3D;
import com.company.context.impl.RandomContext;
import com.company.entity.generic.Entity;
import com.company.entity.impl.object3d.Coordinates3D;
import com.company.entity.impl.object3d.Object3D;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.util.stream.Collectors.toList;

public class Renderer3DWireframeRandom extends Renderer3DWireframe {

    private final Random random;

    public Renderer3DWireframeRandom(){
        this.random = new Random();
        random.setSeed(System.currentTimeMillis());
    }

    @Override
    public <T extends SceneContext> void run(T sceneContext) {
        List<Object3D> entities = randomizeEnteties((RandomContext) sceneContext);
        run(entities, sceneContext.getCamera());
    }

    /**
     * some mock implementation, will need to add context awarneess later, just simply add 1 element
     */
    private List<Object3D> randomizeEnteties(RandomContext sceneContext) {
        addNewObjects(sceneContext);
        //clearObjects(sceneContext);

        return sceneContext.getConnectors()
                .stream()
                .map(connector -> (Object3D)connector.getEntity())
                .collect(toList());
    }

    private void addNewObjects(RandomContext sceneContext) {
        List<Object3D> buffer = new ArrayList<>();


        sceneContext.getScene().getEntities().stream().forEach(entity -> {
            if (isCanAttachSomethingNew(entity, sceneContext)){

               pickAnObject(entity, sceneContext).ifPresent(newObject -> {
                   utilAlignObjectToConnector(newObject, (Connector3D) sceneContext.getConnectorByEntityName(entity.getName()).get());
                   buffer.add(newObject);
               });

            }
        });

        buffer.forEach(obj -> sceneContext.getScene().addEntity(obj));
    }

    private void clearObjects(RandomContext sceneContext) {
        //rule will be that a random object will be deleted
        if (sceneContext.getScene().getEntities().size() == sceneContext.getConnectors().size() - 1 )
            sceneContext
                    .getScene()
                    .getEntities()
                    .remove(random.nextInt(sceneContext.getScene().getEntities().size()));
    }

    /**
     * some mock implementation, will need to add context awarneess later, just simply add 1 element
     */
    private Optional<Object3D> pickAnObject(Object3D entity, RandomContext sceneContext){
        List<ConnectorPort3D> freePorts = sceneContext
                .getConnectorByEntityName(entity.getName())
                .get().getFreePorts();

        ConnectorPort3D entityPort = freePorts.get(random.nextInt(freePorts.size()));

        List<Connector3D> compatibleConnectors = sceneContext
                .getConnectors().stream()
                .filter(connector -> connector.isCompatible(entityPort))
                .map(e -> (Connector3D) e)
                .collect(toList());

        Connector3D slaveConnector = compatibleConnectors.get(random.nextInt(compatibleConnectors.size()));

        Optional<ConnectorPort3D> slavePort = slaveConnector.getFreePorts().stream()
                    .filter(port -> port.isConnectable(entityPort.getCurrentType()))
                    .findFirst();

        if (slavePort.isPresent()){
            slaveConnector.attachTo(entityPort, slavePort.get());
            return Optional.of(new Object3D(slaveConnector.getEntity()));
        }

        return Optional.empty();
    }

    boolean isCanAttachSomethingNew(Entity entity, RandomContext context){
        //rule will be: No more than 1 attached object
        Connector connector = context.getConnectorByEntityName(entity.getName()).orElse(null);

        return connector != null
                && connector.getBusyPorts().size() < 1
                && (connector.getBusyPorts().size() + connector.getFreePorts().size() > 0);
    }


    //util
    private Object3D utilAlignObjectToConnector(Object3D entity, Connector3D entityConnector) {

        Coordinates3D newCoord = utilAddCoordinates(entityConnector.getEntity().getCoord(),
                                                    entityConnector.getCoordinates());

        entity.setCoord(newCoord);

        return entity;
    }

    //util
    private Coordinates3D utilAddCoordinates(Coordinates3D coordinate1, Coordinates3D coordinate2) {
        Coordinates3D result = new Coordinates3D();

        result.setX(coordinate1.getX() + coordinate2.getX());
        result.setY(coordinate1.getY() + coordinate2.getY());
        result.setZ(coordinate1.getZ() + coordinate2.getZ());
        result.setAngleX(coordinate1.getAngleX() + coordinate2.getAngleX());
        result.setAngleY(coordinate1.getAngleY() + coordinate2.getAngleY());
        result.setAngleZ(coordinate1.getAngleZ() + coordinate2.getAngleZ());

        return result;
    }
}
