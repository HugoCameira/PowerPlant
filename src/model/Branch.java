package model;

public class Branch extends Cell{

    public Branch() {
        super();
        type = 'T';
        mDirecions[0] = true; //up
        mDirecions[1] = false; //right
        mDirecions[2] = true; //down
        mDirecions[3] = true; //left
        mCenter = 'o';
    }
}
