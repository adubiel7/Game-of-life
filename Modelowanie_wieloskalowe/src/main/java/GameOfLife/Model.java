package GameOfLife;

import app.Cell;
import static GameOfLife.CellGoL.*;
import java.util.Random;

public class Model {
    private Cell[][] grid;
    private int gridHeight, gridWidth;

    public Model(int height, int wight) {
        gridHeight = height;
        gridWidth = wight;
        grid = new CellGoL[gridHeight][gridWidth];

        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                grid[i][j] = new CellGoL();
            }
        }
    }

    private byte checkNeighbours(Cell[][] frame, int height, int width) {
        byte result;

        int iG, i, iD, jL, j, jR;
        i = height;
        j = width;

        if (height == 0) iG = gridHeight - 1;
        else iG = height - 1;

        if (height == gridHeight - 1) iD = 0;
        else iD = height + 1;

        if (width == 0) jL = gridWidth - 1;
        else jL = width - 1;

        if (width == gridWidth - 1) jR = 0;
        else jR = width + 1;

        int aliveNeighbours = 0;
        if (frame[iG][jL].getState() == State.ALIVE) aliveNeighbours++;
        if (frame[iG][j].getState() == State.ALIVE) aliveNeighbours++;
        if (frame[iG][jR].getState() == State.ALIVE) aliveNeighbours++;

        if (frame[i][jL].getState() == State.ALIVE) aliveNeighbours++;//
        if (frame[i][jR].getState() == State.ALIVE) aliveNeighbours++;

        if (frame[iD][jL].getState() == State.ALIVE) aliveNeighbours++;
        if (frame[iD][j].getState() == State.ALIVE) aliveNeighbours++;
        if (frame[iD][jR].getState() == State.ALIVE) aliveNeighbours++;

        if (frame[height][width].getState() == State.DEAD && aliveNeighbours == 3)
            result = State.ALIVE;
        else if (frame[height][width].getState() == State.ALIVE && (aliveNeighbours == 3 || aliveNeighbours == 2))
            result = State.ALIVE;
        else
            result = State.DEAD;

        return result;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    private Cell[][] getTmp(){
        Cell[][] tmp = new CellGoL[gridHeight][gridWidth];
        for (int i = 0; i < gridHeight; i++)
            for (int j = 0; j < gridWidth; j++)
                tmp[i][j] = new CellGoL();

        return tmp;
    }

    public Cell[][] getResult(Cell[][] frame) {
        Cell[][] tmp = getTmp();

        for (int i = 0; i < gridHeight; i++)
            for (int j = 0; j < gridWidth; j++)
                tmp[i][j].setState(checkNeighbours(frame, i, j));

        for (int i = 0; i < gridHeight; i++)
            System.arraycopy(tmp[i], 0, grid[i], 0, gridWidth);

        return getGrid();
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public void clearGrid(){
        for (int i = 0; i < gridHeight; i++)
            for (int j = 0; j < gridWidth; j++)
                grid[i][j].setState(State.DEAD);
    }

    public void fillRandomly(int amount){
        Random rand = new Random();
        for (int i = 0; i < amount; i++)
            grid[rand.nextInt(gridHeight)][rand.nextInt(gridWidth)].setState(State.ALIVE);
    }
}