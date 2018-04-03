package model;


public abstract class Cell {
    protected static Plant model;

    //TODO:
    public boolean[] mDirecions = new boolean[4];
    public char mCenter;
    public boolean mPower;

    public Position positionUp = new Position(0, 1);
    public Position positionRight = new Position(1, 2);
    public Position positionDown = new Position(2, 1);
    public Position positionLeft = new Position(1, 0);
    public Position positionCenter = new Position(1, 1);

    public Cell() {
        mDirecions[0] = false; //up
        mDirecions[1] = false; //right
        mDirecions[2] = false; //down
        mDirecions[3] = false; //left
        boolean mPower = false;
    }

    public void turnPowerOn(){
        mPower = true;
    }

    public void turnPowerOff(){
        mPower = false;
    }

    public void rotate() {
        int position = mDirecions.length-1;
        boolean last = mDirecions[position];
        boolean temp = mDirecions[0];
        boolean temp1;

        for (int i = 0; i < position; i++) {
            temp1 = mDirecions[i + 1];
            mDirecions[i + 1] = temp;
            temp =temp1;
        }
        mDirecions[0] = last;
    }

    public static Cell newInstance(char type) {
        switch (type) {
            case '.':
                return new Line();
            case 'c':
                return new Curve();
            case 'T':
                return new Branch();
            case 'H':
                return new House();
            case 'P':
                return new Source();
            case ' ':
                return new Empty();
        }
        return null;
    }
}
