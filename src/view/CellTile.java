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
        if (cell.mDirecions[0]) {
            // backGroundChange(cell);
            //Console.cursor(line * CELL_HEIGHT + cell.positionUp.line, col * CELL_WIDTH + cell.positionUp.col);
            print(0, SIDE / 2, '|');

            //Console.print('|');
        } else {
            //Console.cursor(line * CELL_HEIGHT + cell.positionUp.line, col * CELL_WIDTH + cell.positionUp.col);
            //Console.setBackground(Console.BLACK);
            print(0, SIDE / 2, ' ');
            //Console.print(' ');
        }
        if (cell.mDirecions[1]) {
            //Console.cursor(line * CELL_HEIGHT + cell.positionRight.line, col * CELL_WIDTH + cell.positionRight.col);
            backGroundChange(cell);
            print(SIDE / 2, SIDE, '-');
            //Console.print('-');
        } else {
            // Console.cursor(line * CELL_HEIGHT + cell.positionRight.line, col * CELL_WIDTH + cell.positionRight.col);
            Console.setBackground(Console.BLACK);
            print(SIDE / 2, SIDE, ' ');
            // Console.print(' ');
        }
        if (cell.mDirecions[2]) {
            // Console.cursor(line * CELL_HEIGHT + cell.positionDown.line, col * CELL_WIDTH + cell.positionDown.col);
            backGroundChange(cell);
            print(SIDE, SIDE / 2, '|');
            // Console.print('|');
        } else {
            //Console.cursor(line * CELL_HEIGHT + cell.positionDown.line, col * CELL_WIDTH + cell.positionDown.col);
            Console.setBackground(Console.BLACK);
            print(SIDE, SIDE / 2, ' ');
            //Console.print(' ');
        }
        if (cell.mDirecions[3]) {
            // Console.cursor(line * CELL_HEIGHT + cell.positionLeft.line, col * CELL_WIDTH + cell.positionLeft.col);
            backGroundChange(cell);
            print(0, SIDE / 2, '-');
            //  Console.print('-  ');
        } else {
            // Console.cursor(line * CELL_HEIGHT + cell.positionLeft.line, col * CELL_WIDTH + cell.positionLeft.col);
            Console.setBackground(Console.BLACK);
            print(0, SIDE / 2, ' ');
            //  Console.print(' ');
        }
        backGroundChangeCenter(cell);
        //Console.cursor(line * CELL_HEIGHT + cell.positionCenter.line, col * CELL_WIDTH + cell.positionCenter.col);
        //Console.print(cell.mCenter);
        print(SIDE / 2, SIDE / 2, cell.mCenter);

    }

    public void setViews(Plant model) {
        //TODO
    }

    public void repaintAll(Plant model) {
        model.refreshPower();
        Cell[][] newBoard = model.getBoard();
        for (int line = 0; line < model.getHeight(); line++) {
            for (int col = 0; col < model.getWidth(); col++) {
                drawCell(newBoard[line][col], line, col);
            }
        }
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
            Console.setBackground(Console.BLACK);
            Console.setForeground(Console.WHITE);
        }

    }

    private void drawCell(Cell piece, int line, int col) {

        if (piece.mDirecions[0]) {
            Console.cursor(line * CELL_HEIGHT + piece.positionUp.line, col * CELL_WIDTH + piece.positionUp.col);
            backGroundChange(piece);
            Console.print('|');
        } else {
            Console.cursor(line * CELL_HEIGHT + piece.positionUp.line, col * CELL_WIDTH + piece.positionUp.col);
            Console.setBackground(Console.BLACK);
            Console.print(' ');
        }
        if (piece.mDirecions[1]) {
            Console.cursor(line * CELL_HEIGHT + piece.positionRight.line, col * CELL_WIDTH + piece.positionRight.col);
            backGroundChange(piece);
            Console.print('-');
        } else {
            Console.cursor(line * CELL_HEIGHT + piece.positionRight.line, col * CELL_WIDTH + piece.positionRight.col);
            Console.setBackground(Console.BLACK);
            Console.print(' ');
        }
        if (piece.mDirecions[2]) {
            Console.cursor(line * CELL_HEIGHT + piece.positionDown.line, col * CELL_WIDTH + piece.positionDown.col);
            backGroundChange(piece);
            Console.print('|');
        } else {
            Console.cursor(line * CELL_HEIGHT + piece.positionDown.line, col * CELL_WIDTH + piece.positionDown.col);
            Console.setBackground(Console.BLACK);
            Console.print(' ');
        }
        if (piece.mDirecions[3]) {
            Console.cursor(line * CELL_HEIGHT + piece.positionLeft.line, col * CELL_WIDTH + piece.positionLeft.col);
            backGroundChange(piece);
            Console.print('-');
        } else {
            Console.cursor(line * CELL_HEIGHT + piece.positionLeft.line, col * CELL_WIDTH + piece.positionLeft.col);
            Console.setBackground(Console.BLACK);
            Console.print(' ');
        }
        backGroundChangeCenter(piece);
        Console.cursor(line * CELL_HEIGHT + piece.positionCenter.line, col * CELL_WIDTH + piece.positionCenter.col);
        Console.print(piece.mCenter);

    }

    public static Tile newInstance(Cell cell) {
        switch (cell.getType()) {
            case '.':
                return new BranchTile(cell);
            case 'c':
                return new CurveTile(cell);
            case 'T':
                return new BranchTile(cell);
            case 'H':
                return new HouseTile(cell);
            case 'P':
                return new SourceTile(cell);
            case ' ':
                return new EmptyTile(cell);
        }
        return null;
    }
}
