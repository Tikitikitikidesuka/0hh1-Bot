package BoardIO;

import GameBoard.Board;
import GameBoard.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class ImageToBoard {
    /**
     * Wrapper for a board and its info,
     * specifically the screen origin of the board and the screen distance between contiguous tiles
     */
    public static class BoardAndInfo {
        public Board board;
        public Point boardScreenOrigin;
        public int boardScreenTileDistance;

        public BoardAndInfo(Board board, Point origin, int distance) {
            this.board = board;
            this.boardScreenOrigin = origin;
            this.boardScreenTileDistance = distance;
        }
    }
    /**
     * Generates a BoardAndInfo object from an image of the game's board.
     * @param image image of the game's board
     * @return BoardAndInfo object containing a board with the same state as the game's board
     */
    public static BoardAndInfo bufferedImageToBoard(BufferedImage image) {
        Point origin = getBoardPixelOrigin(image);
        int distance = getTilePixelDistance(image, origin);
        int size = getBoardSize(image, origin, distance);

        Board board = new Board(size);

        for(int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                board.setTileType(x, y, getImageTileType(x, y, origin, distance, image));
            }
        }

        return new BoardAndInfo(board, origin, distance);
    }

    private static TileType getImageTileType(int x, int y, Point origin, int distance, BufferedImage image) {
        TileType type = TileType.EMPTY;

        Point position = new Point(x*distance + origin.x, y*distance + origin.y);

        int pixelColorCode = image.getRGB(position.x, position.y);
        if(isValidTileColor(pixelColorCode))
            type = colorTileMap.get(pixelColorCode);

        return type;
    }

    private static Point getBoardPixelOrigin(BufferedImage image) {
        Point origin = null;

        for(int y = image.getHeight() - 1; y >= 0; y--) {
            for(int x = image.getWidth() - 1; x >= 0; x--) {
                int pixelColor = image.getRGB(x, y);
                if(isValidTileColor(pixelColor))
                    origin = new Point(x, y);
            }
        }

        return origin;
    }

    private static int getTilePixelDistance(BufferedImage image, Point origin) {
        boolean spacePassed = false;
        boolean secondTileFound = false;

        int x = origin.x;
        while(++x < image.getWidth() && !secondTileFound) {
            int pixelColor = image.getRGB(x, origin.y);
            if(spacePassed) {
                if(isValidTileColor(pixelColor)) {
                    secondTileFound = true;
                }
            } else {
                if(isBackgroundColor(pixelColor)) {
                    spacePassed = true;
                }
            }
        }

        return x - origin.x;
    }

    private static int getBoardSize(BufferedImage image, Point origin, int distance) {
        int size = 1;
        int x = origin.x + distance;
        while(x < image.getWidth() && !isBackgroundColor(image.getRGB(x, origin.y))) {
            x += distance;
            size++;
        }
        return size;
    }


    private static final int COLOR_BG    = new Color(34, 34, 34).getRGB();
    private static final int COLOR_A1    = new Color(48, 167, 194).getRGB();
    private static final int COLOR_A2    = new Color(53, 184, 213).getRGB();
    private static final int COLOR_B1    = new Color(213, 83, 54).getRGB();
    private static final int COLOR_B2    = new Color(194, 75, 49).getRGB();
    private static final int COLOR_EMPTY = new Color(42, 42, 42).getRGB();

    private static final Map<Integer, TileType> colorTileMap;
    static {
        Map<Integer, TileType> colorTileMapT = new TreeMap<>();
        colorTileMapT.put(COLOR_A1, TileType.COLOR_A);
        colorTileMapT.put(COLOR_A2, TileType.COLOR_A);
        colorTileMapT.put(COLOR_B1, TileType.COLOR_B);
        colorTileMapT.put(COLOR_B2, TileType.COLOR_B);
        colorTileMapT.put(COLOR_EMPTY, TileType.EMPTY);
        colorTileMap = Collections.unmodifiableMap(colorTileMapT);
    }

    private static final int[] validTileColors =
            {COLOR_A1, COLOR_A2, COLOR_B1, COLOR_B2, COLOR_EMPTY};

    private static final int[] backgroundColors =
            {COLOR_BG};

    private static boolean isValidTileColor(int codeRGB) {
        return isValidColor(codeRGB, validTileColors);
    }

    private static boolean isBackgroundColor(int codeRGB) {
        return isValidColor(codeRGB, backgroundColors);
    }

    private static boolean isValidColor(int codeRGB, int[] validColors) {
        for(int validColor : validColors) {
            if(codeRGB == validColor)
                return true;
        }
        return false;
    }
}
