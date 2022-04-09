import BoardSolving.*;
import GameBoard.Board;

public class Bot {
    private Board board;
    private Board initialBoard;

    private static final SolvingStrategy[] SOLVING_STRATEGIES = {
            new FillTwoInARowFringe(),
            new FillInBetweenFringe(),
            new EqualNumberPerLine(),
            new AvoidTwoSameLines()
    };


    /**
     * Fills the board with a valid solution.
     */
    public void solveBoard() {
        int filled;
        do {
            filled = 0;
            for(SolvingStrategy solvingStrategy : SOLVING_STRATEGIES) {
                filled += solvingStrategy.executeSolveStrategy(board);
            }
        } while(filled > 0);
    }

    /**
     * Prints a string representation of the board's state.
     */
    public void printBoard() {
        System.out.println("Game board: " + this.board);
    }

    /**
     * Prints a string representation of the initial board's state.
     */
    public void printInitialBoard() {
        System.out.println("Inital game board: " + this.initialBoard);
    }
}
