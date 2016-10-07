package com.company.entity.generic;

import com.company.entity.impl.object3d.Coordinates3D;

public interface Entity {
    <T extends Coordinates> T getCoord();
    <T extends Entity, U extends Coordinates> T addCoord(U coord);
}
