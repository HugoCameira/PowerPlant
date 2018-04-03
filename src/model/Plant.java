package model;

import console.tile.TilePanel;
import ctrl.Game;
import exception.BadInputException;

import java.io.FileReader;
import java.io.IOException;

public class Plant {
    private Cell[][] mBoard;
    private int mNumLines;
    private int mNumColumns;
    private int moves;

    public Plant(int height, int width){
    mBoard = new Cell[height][width];
    }

    public int getMoves() {
        return moves;
    }

    public boolean touch(int line, int col) {
        return false;
        //TODO
    }

    public void init() {
        //TODO
    }

    public void putCell(int l, int c, Cell cell) {
        mBoard[l][c] = cell;
    }

    public interface Listener {
        void cellChanged(int lin, int col, Cell cell);
    }
    //TODO:

    public int getHeight() {
        return mNumLines;
    }

    public int getWidth() {
        return mNumColumns;
    }

    public Cell[][] getBoard() {
        return mBoard;
    }

    public Cell getCell(int l, int c){
        return mBoard[l][c];
    }

    public void setListener(Listener view) {
        //TODO
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

    public void loadLevel(FileReader file) throws BadInputException, IOException {

        mNumColumns = file.read() - '0';
        if (file.read() != ' ') {
            throw new BadInputException("RIP TXT");
        }
        if (file.read() != 'x') {
            throw new BadInputException("RIP TXT");
        }
        if (file.read() != ' ') {
            throw new BadInputException("RIP TXT");
        }
        mNumLines = file.read() - '0';
        mBoard = new Cell[mNumLines][mNumColumns];


        for (int line = 0; line < mNumLines; line++) {
            file.read();
            file.read();
            for (int col = 0; col < mNumColumns; col++) {
                char current = (char) file.read();
                switch (current) {
                    case '.':
                        mBoard[line][col] = new Line();
                        break;
                    case 'c':
                        mBoard[line][col] = new Curve();
                        break;
                    case 'T':
                        mBoard[line][col] = new Branch();
                        break;
                    case 'H':
                        mBoard[line][col] = new House();
                        break;
                    case 'P':
                        mBoard[line][col] = new Source();
                        break;
                    case ' ':
                        mBoard[line][col] = new Empty();
                        break;
                    default:
                        throw new BadInputException("RIP TXTv2");
                }
            }
        }
    }
 }
