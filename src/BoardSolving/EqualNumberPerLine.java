package BoardSolving;

import GameBoard.Board;

public class EqualNumberPerLine extends SolvingStrategy {
    /**
     * If there is a line where there are size/2 tiles of one color,
     * the rest of the line is filled with the opposite color
     * @param  board board to try to solve
     * @return number of filled tiles
     */
    @Override
    public int executeSolveStrategy(Board board) {
        return 0;
    }
}
