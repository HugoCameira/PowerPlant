package model;

import isel.leic.pg.Console;

//import static pt.isel.poo.powermap.PowerMapView.CELL_HEIGHT;
//import static pt.isel.poo.powermap.PowerMapView.CELL_WIDTH;

public class House extends Cell{

    public House() {
        super();
        type = 'H';
        mDirecions[0] = true; //up
        mDirecions[1] = false; //right
        mDirecions[2] = false; //down
        mDirecions[3] = false; //left
        mCenter = 'H';
    }
}
