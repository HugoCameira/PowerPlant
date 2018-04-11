package view;


import isel.leic.pg.Console;
import model.*;
import console.tile.Tile;


public abstract class CellTile extends Tile {
    public static final int SIDE = 3;
    public static final int CELL_HEIGHT = 3;
    public static final int CELL_WIDTH = 3;
    protected Cell cell;

    protected CellTile(Cell cell) {
        this.cell = cell;
        setSize(CELL_HEIGHT, CELL_WIDTH);
    }

    @Override
    public void paint() {
        clear();
        if (cell.mDirecions[0]) {
            backGroundChange(cell);
            print(0, SIDE / 2, '|');
        }
        if (cell.mDirecions[1]) {
            backGroundChange(cell);
            print(SIDE / 2, SIDE - 1, '-');
        }
        if (cell.mDirecions[2]) {
            backGroundChange(cell);
            print(SIDE - 1, SIDE / 2, '|');
        }
        if (cell.mDirecions[3]) {
            backGroundChange(cell);
            print(SIDE / 2, 0, '-');
        }
        backGroundChangeCenter(cell);
        print(SIDE / 2, SIDE / 2, cell.mCenter);

    }

    public void backGroundChange(Cell piece) {
        if (piece.mPower) {
            Console.setBackground(Console.YELLOW);
            Console.setForeground(Console.BLACK);
        } else {
            Console.setBackground(Console.BLACK);
            Console.setForeground(Console.WHITE);
        }

    }

    public void backGroundChangeCenter(Cell piece) {
        if (piece.mPower) {
            if (piece instanceof House) {
                Console.setBackground(Console.YELLOW);
                Console.setForeground(Console.RED);
            } else {
                Console.setBackground(Console.YELLOW);
                Console.setForeground(Console.BLACK);
            }
            if (piece instanceof Source) {
                Console.setBackground(Console.YELLOW);
                Console.setForeground(Console.BROWN);
            }
        } else {
            if (piece instanceof House) {
                Console.setBackground(Console.RED);
                Console.setForeground(Console.WHITE);
            } else {
                if (piece instanceof Empty) {

                } else {
                    Console.setBackground(Console.BLACK);
                    Console.setForeground(Console.WHITE);
                }
            }
        }

    }

    public static Tile newInstance(Cell cell) {
        switch (cell.getType()) {
            case '-':
                return new LineTile(cell);
            case 'c':
                return new CurveTile(cell);
            case 'T':
                return new BranchTile(cell);
            case 'H':
                return new HouseTile(cell);
            case 'P':
                return new SourceTile(cell);
            case '.':
                return new EmptyTile(cell);
        }

        return null;
    }
}
