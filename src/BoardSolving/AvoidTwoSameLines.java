package BoardSolving;

import GameBoard.Board;

import java.util.ArrayList;
import java.util.List;

public class AvoidTwoSameLines extends SolvingStrategy {
    /**
     * If there is a line with exactly two empty tiles and another line which is the same except for the two empty tiles,
     * the two empty tiles are filled such that the two lines end up being different but the number of tiles rules is still abided.
     * @param  board board to try to solve
     * @return number of filled tiles
     */
    @Override
    public int executeSolveStrategy(Board board) {
        int filled = 0;

        filled += this.avoidTwoSameLinesHorz(board);
        filled += this.avoidTwoSameLinesVert(board);

        return filled;
    }

    private int avoidTwoSameLinesHorz(Board board) {
        int filled = 0;

        int size = board.getSize();
        List<Integer> fullLines = new ArrayList<>();
        List<Integer> semiLines = new ArrayList<>();
        for(int y = 0; y < size; y++) {
            int emptyTiles = 0;
            for(int x = 0; x < size; x++) {
                if(board.isTileEmpty(x, y))
                    emptyTiles++;
            }
            switch(emptyTiles) {
                case 0 -> fullLines.add(y);
                case 2 -> semiLines.add(y);
            }

            for(int semiLine : semiLines) {
                for(int fullLine : fullLines) {
                    if(this.differentTilesHorz(semiLine, fullLine, board) == 2) {
                        this.setLinesHorz(semiLine, fullLine, board);
                        filled += 2;
                    }
                }
            }
        }

        return filled;
    }

    private int avoidTwoSameLinesVert(Board board) {
        int filled = 0;

        int size = board.getSize();
        List<Integer> fullLines = new ArrayList<>();
        List<Integer> semiLines = new ArrayList<>();
        for(int x = 0; x < size; x++) {
            int emptyTiles = 0;
            for(int y = 0; y < size; y++) {
                if(board.isTileEmpty(x, y))
                    emptyTiles++;
            }
            switch(emptyTiles) {
                case 0 -> fullLines.add(x);
                case 2 -> semiLines.add(x);
            }

            for(int semiLine : semiLines) {
                for(int fullLine : fullLines) {
                    if(this.differentTilesVert(semiLine, fullLine, board) == 2) {
                        this.setLinesVert(semiLine, fullLine, board);
                        filled += 2;
                    }
                }
            }
        }

        return filled;
    }

    private int differentTilesHorz(int semiLine, int fullLine, Board board) {
        int differentTiles = 0;

        int size = board.getSize();
        for(int x = 0; x < size; x++) {
            if(board.getTileType(x, semiLine) != board.getTileType(x, fullLine)) {
                differentTiles++;
            }
        }

        return differentTiles;
    }

    private int differentTilesVert(int semiLine, int fullLine, Board board) {
        int differentTiles = 0;

        int size = board.getSize();
        for(int y = 0; y < size; y++) {
            if(board.getTileType(semiLine, y) != board.getTileType(fullLine, y)) {
                differentTiles++;
            }
        }

        return differentTiles;
    }

    private void setLinesHorz(int semiLine, int fullLine, Board board) {
        int size = board.getSize();
        for(int x = 0; x < size; x++) {
            if(board.getTileType(x, semiLine) != board.getTileType(x, fullLine)) {
                board.setTileType(x, semiLine, board.getTileType(x, fullLine));
                board.invertTile(x, semiLine);
            }
        }
    }

    private void setLinesVert(int semiLine, int fullLine, Board board) {
        int size = board.getSize();
        for(int y = 0; y < size; y++) {
            if(board.getTileType(semiLine, y) != board.getTileType(fullLine, y)) {
                board.setTileType(semiLine, y, board.getTileType(fullLine, y));
                board.invertTile(semiLine, y);
            }
        }
    }
}
