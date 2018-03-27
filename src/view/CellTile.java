package view;


import model.Cell;
import console.tile.Tile;


public abstract class CellTile extends Tile {
    public static final int SIDE = 3;
    protected Cell cell;

    protected CellTile(Cell cell) {
        this.cell=cell;
        setSize(SIDE, SIDE);
    }

    @Override
    public void paint() {
        //TODO:
    }

    //TODO:
}
