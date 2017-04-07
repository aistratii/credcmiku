package com.company.entity.generic;

import com.company.entity.impl.object3d.Coordinates3D;

public interface Entity <T extends Coordinates, U extends Entity>{
    T getCoord();
    U setCoord(T coord);
}
