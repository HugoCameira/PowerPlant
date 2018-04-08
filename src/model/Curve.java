package model;

public class Curve extends Cell{

    public Curve() {
        super();
        type = 'c';
        mDirecions[0] = false; //up
        mDirecions[1] = false; //right
        mDirecions[2] = true; //down
        mDirecions[3] = true; //left
        mCenter = 'o';
    }
}
