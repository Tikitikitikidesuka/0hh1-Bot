package BoardSolving;

import GameBoard.Board;
import GameBoard.TileType;

public class EqualNumberPerLine extends SolvingStrategy {
    /**
     * If there is a line where there are size/2 tiles of one color,
     * the rest of the line is filled with the opposite color
     * @param  board board to try to solve
     * @return number of filled tiles
     */
    @Override
    public int executeSolveStrategy(Board board) {
        int filled = 0;

        filled += equalNumberPerLineHorz(board);
        filled += equalNumberPerLineVert(board);

        return filled;
    }

    private int equalNumberPerLineHorz(Board board) {
        int filled = 0;

        int size = board.getSize();
        for(int y = 0; y < size; y++) {
            int colorA = 0, colorB = 0;
            for(int x = 0; x < size; x++) {
                switch(board.getTileType(x, y)) {
                    case COLOR_A -> colorA++;
                    case COLOR_B -> colorB++;
                }
            }

            if(colorA == size/2 || colorB == size/2) {
                TileType fillType;
                if(colorA == size/2)
                    fillType = TileType.COLOR_B;
                else
                    fillType = TileType.COLOR_A;

                for(int x = 0; x < size; x++) {
                    if(board.isTileEmpty(x, y))
                        board.setTileType(x, y, fillType);
                }
            }
        }

        return filled;
    }

    private int equalNumberPerLineVert(Board board) {
        int filled = 0;

        int size = board.getSize();
        for(int x = 0; x < size; x++) {
            int colorA = 0, colorB = 0;
            for(int y = 0; y < size; y++) {
                switch(board.getTileType(x, y)) {
                    case COLOR_A -> colorA++;
                    case COLOR_B -> colorB++;
                }
            }

            if(colorA == size/2 || colorB == size/2) {
                TileType fillType;
                if(colorA == size/2)
                    fillType = TileType.COLOR_B;
                else
                    fillType = TileType.COLOR_A;

                for(int y = 0; y < size; y++) {
                    if(board.isTileEmpty(x, y))
                        board.setTileType(x, y, fillType);
                }
            }
        }

        return filled;
    }
}
