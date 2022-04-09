package GameBoard;

public class Board {
    private Tile[][] matrix;


    /**
     * Constructor for class Board.
     * @param size size of the board's side.
     */
    public Board(int size) {
        matrix = new Tile[size][size];
        for(int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                matrix[y][x] = new Tile();
            }
        }
    }

    /**
     * Returns the size of the board's side.
     * @return size of the board's side.
     */
    public int getSize() {
        return matrix.length;
    }

    /**
     * Returns a string representation of the board's state.
     * @return string representation of the board's state.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for(Tile[] row : matrix) {
            for(Tile tile  : row) {
                output.append(tile.toString());
            }
            output.append('\n');
        }
        return output.toString();
    }
}
