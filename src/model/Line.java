package model;

public class Line extends Cell{

    public Line() {
        super();
        type = '-';
        mDirecions[0] = false; //up
        mDirecions[1] = true; //right
        mDirecions[2] = false; //down
        mDirecions[3] = true; //left
        mCenter = 'o';
    }
}
