package model;

import isel.leic.pg.Console;

//import static pt.isel.poo.powermap.PowerMapView.CELL_HEIGHT;
//import static pt.isel.poo.powermap.PowerMapView.CELL_WIDTH;

public class Line extends Cell{

    public Line() {
        super();
        mDirecions[0] = false; //up
        mDirecions[1] = true; //right
        mDirecions[2] = false; //down
        mDirecions[3] = true; //left
        mCenter = 'o';
    }
}
