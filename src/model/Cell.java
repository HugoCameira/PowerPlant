package model;


public abstract class Cell {
    protected static Plant model;
    public char type;
    public boolean[] mDirecions = new boolean[4];
    public char mCenter;
    public boolean mPower;

    public Cell() {
        mDirecions[0] = false; //up
        mDirecions[1] = false; //right
        mDirecions[2] = false; //down
        mDirecions[3] = false; //left
        mPower = false;
    }

    public char getType() {
        return type;
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
        boolean aux;

        for (int i = 0; i < position; i++) {
            aux = mDirecions[i + 1];
            mDirecions[i + 1] = temp;
            temp =aux;
        }
        mDirecions[0] = last;
    }

    public static Cell newInstance(char type) {
        switch (type) {
            case '-':
                return new Line();
            case 'c':
                return new Curve();
            case 'T':
                return new Branch();
            case 'H':
                return new House();
            case 'P':
                return new Source();
            case '.':
                return new Empty();
        }
        return null;
    }
}
