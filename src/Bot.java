import BoardIO.ImageToBoard;
import BoardSolving.*;
import GameBoard.Board;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bot {
    private final Board board;
    private final Board initialBoard;

    private static final SolvingStrategy[] SOLVING_STRATEGIES = {
            new FillTwoInARowFringe(),
            new FillInBetweenFringe(),
            new EqualNumberPerLine(),
            new AvoidTwoSameLines()
    };


    public static void main(String[] args) {
        Bot bot = new Bot();
    }

    public Bot() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.out.println("ERROR: Failed to create AWT Robot");
            e.printStackTrace();
            System.exit(1);
        }

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        Rectangle rectangle = new Rectangle(1280, 0, dimension.width, dimension.height);
        BufferedImage image = robot.createScreenCapture(rectangle);

        this.board = ImageToBoard.bufferedImageToBoard(image);
        this.initialBoard = new Board(this.board);

        this.solveBoard();
        this.printBoard();
    }

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
            System.out.println(board);
        } while(filled > 0);
    }

    /**
     * Prints a string representation of the board's state.
     */
    public void printBoard() {
        System.out.println("Game board:\n" + this.board);
    }

    /**
     * Prints a string representation of the initial board's state.
     */
    public void printInitialBoard() {
        System.out.println("Inital game board:\n" + this.initialBoard);
    }
}
