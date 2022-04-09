package BoardSolving;

import GameBoard.Board;

public abstract class SolvingStrategy {
    /**
     * Runs a solving strategy to try to solve the board.
     * @param  board board to try to solve
     * @return number of filled tiles
     */
    public abstract int executeSolveStrategy(Board board);
}
