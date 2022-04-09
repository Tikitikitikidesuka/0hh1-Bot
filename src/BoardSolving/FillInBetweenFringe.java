package BoardSolving;

import GameBoard.Board;

public class FillInBetweenFringe extends SolvingStrategy {
    /**
     * If there are two tiles of the same color separated by an empty tile,
     * a tile of the opposite color is placed in the empty tile.
     * @param  board board to try to solve
     * @return number of filled tiles
     */
    @Override
    public int executeSolveStrategy(Board board) {
        return 0;
    }
}
