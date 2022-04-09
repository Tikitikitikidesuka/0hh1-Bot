package BoardSolving;

import GameBoard.Board;

public class FillInBetweenTwo extends SolvingStrategy {
    /**
     * If there are two tiles of the same color separated by an empty tile,
     * a tile of the opposite color is placed in the empty tile.
     * @param  board board to try to solve
     * @return number of filled tiles
     */
    @Override
    public int executeSolveStrategy(Board board) {
        int filled = 0;

        filled += fillInBetweenTwoHorz(board);
        filled += fillInBetweenTwoVert(board);

        return filled;
    }

    private int fillInBetweenTwoHorz(Board board) {
        int filled = 0;

        int size = board.getSize();
        for(int y = 0; y < size; y++) {
            for(int x = 1; x < size - 1; x++) {
                if(
                        board.isTileEmpty(x, y) &&
                        !board.isTileEmpty(x - 1, y) &&
                        board.getTileType(x - 1, y) == board.getTileType(x + 1, y)
                ) {
                    board.setTileType(x, y, board.getTileType(x - 1, y));
                    board.invertTile(x, y);
                    filled++;
                }
            }
        }

        return filled;
    }

    private int fillInBetweenTwoVert(Board board) {
        int filled = 0;

        int size = board.getSize();
        for(int x = 0; x < size; x++) {
            for(int y = 1; y < size - 1; y++) {
                if(
                        board.isTileEmpty(x, y) &&
                                !board.isTileEmpty(x, y - 1) &&
                                board.getTileType(x, y - 1) == board.getTileType(x, y + 1)
                ) {
                    board.setTileType(x, y, board.getTileType(x, y - 1));
                    board.invertTile(x, y);
                    filled++;
                }
            }
        }

        return filled;
    }
}
