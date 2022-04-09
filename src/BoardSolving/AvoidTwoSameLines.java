package BoardSolving;

import GameBoard.Board;

public class AvoidTwoSameLines extends SolvingStrategy {
    /**
     * If there is a line with exactly two empty tiles and another line which is the same except for the two empty tiles,
     * the two empty tiles are filled such that the two lines end up being different but the number of tiles rules is still abided.
     * @param  board board to try to solve
     * @return number of filled tiles
     */
    @Override
    public int executeSolveStrategy(Board board) {
        return 0;
    }
}
