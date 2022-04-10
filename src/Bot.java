import BoardIO.BoardClickOutput;
import BoardIO.ImageToBoard;
import BoardSolving.*;
import GameBoard.Board;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bot {
    private Board board;
    private Board initialBoard;

    private static final SolvingStrategy[] SOLVING_STRATEGIES = {
            new FillTwoInARowFringe(),
            new FillInBetweenTwo(),
            new EqualNumberPerLine(),
            new AvoidTwoSameLines()
    };


    public static void main(String[] args) {
        Bot bot = new Bot();
        bot.run();
    }

    /**
     * Runs the bot.
     */
    private void run() {
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

        ImageToBoard.BoardAndInfo boardAndInfo = ImageToBoard.bufferedImageToBoard(image);
        this.board = boardAndInfo.board;
        this.initialBoard = new Board(this.board);
        Point boardScreenOrigin = boardAndInfo.boardScreenOrigin;
        int boardScreenTileDistance = boardAndInfo.boardScreenTileDistance;

        this.solveBoard();

        BoardClickOutput.clickOutputBoard(
                this.initialBoard, this.board,
                boardScreenOrigin, boardScreenTileDistance,
                robot
        );
    }

    /**
     * Fills the board with a valid solution.
     */
    private void solveBoard() {
        int filled;
        do {
            filled = 0;
            for(SolvingStrategy solvingStrategy : SOLVING_STRATEGIES) {
                filled += solvingStrategy.executeSolveStrategy(this.board);
            }
        } while(filled > 0);
    }

    /**
     * Prints a string representation of the board's state.
     */
    private void printBoard() {
        System.out.println("Game board:\n" + this.board);
    }

    /**
     * Prints a string representation of the initial board's state.
     */
    private void printInitialBoard() {
        System.out.println("Inital game board:\n" + this.initialBoard);
    }
}
