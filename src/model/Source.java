package model;

public class Source extends Cell {

    public Source() {
        super();
        type = 'P';
        mDirecions[0] = true; //up
        mDirecions[1] = false; //right
        mDirecions[2] = false; //down
        mDirecions[3] = false; //left
        mCenter = 'P';
        mPower = true;
    }

    @Override
    public void turnPowerOff() {
        // Since it's a source the power is always on
    }
}
