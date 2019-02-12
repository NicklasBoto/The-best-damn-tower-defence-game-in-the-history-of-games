package edu.chl.hajo.td.model;

import lombok.Getter;

/*
    A single logical position in the TDMap

    *** Nothing to do here ***

 */
public class TDTile {

    @Getter
    private boolean isOccupied = false;

    public void setOccupied(boolean b) {
        isOccupied = b;
    }

}
