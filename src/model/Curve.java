package model;

import isel.leic.pg.Console;

//import static pt.isel.poo.powermap.PowerMapView.CELL_HEIGHT;
//import static pt.isel.poo.powermap.PowerMapView.CELL_WIDTH;

public class Curve extends Cell{

    public Curve() {
        super();
        mDirecions[0] = false; //up
        mDirecions[1] = false; //right
        mDirecions[2] = true; //down
        mDirecions[3] = true; //left
        mCenter = 'o';
    }
}
