package com.company.entity.generic;

public interface Entity <T extends Coordinates, U extends Entity>{
    T getCoord();
    U setCoord(T coord);
}
