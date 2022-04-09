package BoardSolving;

import GameBoard.Board;

public class FillTwoInARowFringe extends SolvingStrategy {
    /**
     * If there are two contiguous tiles of the same color, a tile of the opposite color is placed
     * such that it isn't possible to have three adjacent tiles of the same color in a row or column.
     * @param  board board to try to solve
     * @return number of filled tiles
     */
    @Override
    public int executeSolveStrategy(Board board) {
        return 0;
    }
}
