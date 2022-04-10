package BoardIO;

import GameBoard.Board;

import java.awt.*;
import java.awt.event.InputEvent;

public class BoardClickOutput {
    /**
     * Clicks on the corresponding points of the viewport to set the game board to the board passed.
     * @param initialBoard board containing the state of the beginning of the game
     * @param board board containing the desired state to output
     * @param boardOrigin point of the viewport where the top left corner of the game board is
     * @param tileDistance distance between two tiles on the screen
     * @param robot awt robot which will move the mouse
     * @param clickDelay delay between clicks of the bot
     */
    public static void clickOutputBoard(
            Board initialBoard, Board board,
            Point boardOrigin, int tileDistance,
            Robot robot, int clickDelay,
            int xScreenOffset
    ) {
        int size = board.getSize();
        for(int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                if(initialBoard.isTileEmpty(x, y)) {
                    moveMouseToTile(x, y, boardOrigin, tileDistance, robot, xScreenOffset);
                    switch(board.getTileType(x, y)) {
                        case COLOR_A -> rightClick(robot, clickDelay);
                        case COLOR_B -> leftClick(robot, clickDelay);
                    }
                }
            }
        }
    }

    /**
     * Overload of the clickOutputBoard method which sets the clickDelay to zero by default.
     * @param initialBoard board containing the initial state of the game
     * @param board board containing the desired state to output
     * @param boardOrigin point of the viewport where the top left corner of the game board is
     * @param tileDistance distance between two tiles on the screen
     * @param robot awt robot which will move the mouse
     */
    public static void clickOutputBoard(
            Board initialBoard, Board board,
            Point boardOrigin, int tileDistance,
            Robot robot
    ) {
        clickOutputBoard(
                initialBoard, board,
                boardOrigin, tileDistance,
                robot, 0,
                0
        );
    }

    private static void moveMouseToTile(int x, int y, Point boardOrigin, int tileDistance, Robot robot, int xOffset) {
        Point coords = getTileScreenCoords(x, y, boardOrigin, tileDistance, xOffset);
        robot.mouseMove(
                coords.x,
                coords.y
        );
    }

    private static Point getTileScreenCoords(int x, int y, Point boardOrigin, int tileDistance, int xOffset) {
        return new Point(
                xOffset + boardOrigin.x + tileDistance/2 + x*tileDistance,
                boardOrigin.y + tileDistance/2 + y*tileDistance
        );
    }

    private static void leftClick(Robot robot, int clickDelay) {
        robot.delay(clickDelay);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private static void rightClick(Robot robot, int clickDelay) {
        robot.delay(clickDelay);
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }
}
