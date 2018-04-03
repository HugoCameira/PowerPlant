package model;

import isel.leic.pg.Console;

//import static pt.isel.poo.powermap.PowerMapView.CELL_HEIGHT;
//import static pt.isel.poo.powermap.PowerMapView.CELL_WIDTH;

public class Branch extends Cell{

    public Branch() {
        super();
        mDirecions[0] = true; //up
        mDirecions[1] = false; //right
        mDirecions[2] = true; //down
        mDirecions[3] = true; //left
        mCenter = 'o';
    }
}
