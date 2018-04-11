package model;

import java.util.Random;

public class Plant {
    private Cell[][] mBoard;
    private int mNumLines;
    private int mNumColumns;
    private int moves;
    private Listener listener;

    public Plant(int height, int width) {
        mBoard = new Cell[height][width];
        mNumColumns = width;
        mNumLines = height;
    }

    public int getMoves() {
        return moves;
    }

    public boolean touch(int line, int col) {
        if (getCell(line, col) != null) {
            rotate(line, col);
            refreshPower();
            refreshBoard();
            moves++;
            return true;
        }
        return false;
    }

    private void refreshBoard() {
        for (int l = 0; l < getHeight(); l++) {
            for (int c = 0; c < getWidth(); c++) {
                listener.cellChanged(l, c, getCell(l, c));
            }
        }
    }

    public void init() {
        Random r = new Random();
        int low = 0;
        int high = 3;
        int result;
        for (int l = 0; l < getHeight(); l++) {
            for (int c = 0; c < getWidth(); c++) {
                result = r.nextInt(high - low) - low;
                for (int x = 0; x <= result; x++) {
                    rotate(l, c);
                }
            }
        }
        refreshPower();
    }

    public void putCell(int l, int c, Cell cell) {
        mBoard[l][c] = cell;
    }

    public interface Listener {
        void cellChanged(int lin, int col, Cell cell);
    }

    public void setListener(Listener view) {
        listener = view;
    }

    public int getHeight() {
        return mNumLines;
    }

    public int getWidth() {
        return mNumColumns;
    }

    public Cell getCell(int l, int c) {
        return mBoard[l][c];
    }


    public boolean isCompleted() {
        for (int line = 0; line < mNumLines; line++) {
            for (int col = 0; col < mNumColumns; col++) {
                if (mBoard[line][col] instanceof House) {
                    if (!mBoard[line][col].mPower) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void rotate(int lin, int col) {
        turnOffPower();
        mBoard[lin][col].rotate();
        refreshPower();

    }

    /**
     * Starts on each power piece and spreads the power to next pieces
     */
    public void refreshPower() {
        for (int line = 0; line < mNumLines; line++) {
            for (int col = 0; col < mNumColumns; col++) {
                if (mBoard[line][col] instanceof Source) {
                    Cell Cell = mBoard[line][col];
                    if (line != 0) {
                        if (Cell.mDirecions[0] && mBoard[line - 1][col].mDirecions[2]) {
                            propagatePower(mBoard[line - 1][col], line - 1, col, 2);
                            continue;
                        }
                    }
                    if (col != mNumColumns - 1) {
                        if (Cell.mDirecions[1] && mBoard[line][col + 1].mDirecions[3]) {
                            propagatePower(mBoard[line][col + 1], line, col + 1, 3);
                            continue;
                        }
                    }
                    if (line != mNumLines - 1) {
                        if (Cell.mDirecions[2] && mBoard[line + 1][col].mDirecions[0]) {
                            propagatePower(mBoard[line + 1][col], line + 1, col, 0);
                            continue;
                        }
                    }
                    if (col != 0) {
                        if (Cell.mDirecions[3] && mBoard[line][col - 1].mDirecions[1]) {
                            propagatePower(mBoard[line][col - 1], line, col - 1, 1);
                        }
                    }
                }
            }
        }
    }


    private void propagatePower(Cell piece, int line, int col, int powerInc) {
        piece.turnPowerOn();
        if (line != 0 && powerInc != 0) {
            if (piece.mDirecions[0] && mBoard[line - 1][col].mDirecions[2]) {
                propagatePower(mBoard[line - 1][col], line - 1, col, 2);
            }
        }
        if (col != mNumColumns - 1 && powerInc != 1) {
            if (piece.mDirecions[1] && mBoard[line][col + 1].mDirecions[3]) {
                propagatePower(mBoard[line][col + 1], line, col + 1, 3);
            }
        }
        if (line != mNumLines - 1 && powerInc != 2) {
            if (piece.mDirecions[2] && mBoard[line + 1][col].mDirecions[0]) {
                propagatePower(mBoard[line + 1][col], line + 1, col, 0);
            }
        }
        if (col != 0 && powerInc != 3) {
            if (piece.mDirecions[3] && mBoard[line][col - 1].mDirecions[1]) {
                propagatePower(mBoard[line][col - 1], line, col - 1, 1);
            }
        }
    }

    private void turnOffPower() {
        for (int line = 0; line < mNumLines; line++) {
            for (int col = 0; col < mNumColumns; col++) {
                mBoard[line][col].turnPowerOff();
            }
        }
    }

}
