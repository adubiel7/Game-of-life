package GameOfLife;

import app.Cell;

public class CellGoL extends Cell {
    public static class State {
        public static byte DEAD = 0;
        public static byte ALIVE = 1;
    }

    public CellGoL() {
        state = State.DEAD;
    }
}

