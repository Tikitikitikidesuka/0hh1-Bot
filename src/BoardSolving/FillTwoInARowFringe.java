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
        int filled = 0;

        filled += fillTwoInARowFringeHorz(board);

        return filled;
    }

    public int fillTwoInARowFringeHorz(Board board) {
        int filled = 0;

        int size = board.getSize();
        for(int y = 0; y < size; y++) {
            boolean doubleFound = false;
            for(int x = 0; x < size - 1; x++) {
                if(doubleFound) {
                    if(board.isTileEmpty(x + 1, y)) {
                        board.setTileType(x + 1, y, board.getTileType(x, y));
                        board.invertTile(x + 1, y);
                        filled++;
                    }
                    if(x - 2 > 0 && board.isTileEmpty(x - 2, y)) {
                        board.setTileType(x - 2, y, board.getTileType(x, y));
                        board.invertTile(x - 2, y);
                        filled++;
                    }
                    doubleFound = false;
                }
                else {
                    doubleFound =
                            !board.isTileEmpty(x, y) &&
                            board.getTileType(x, y) == board.getTileType(x + 1, y);
                }
            }
        }

        return filled;
    }
}
