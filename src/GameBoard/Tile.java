package GameBoard;

public class Tile {
    private TileType type;


    /**
     * Constructor for class Board. Sets type to TileType.EMTPY.
     */
    public Tile() {
        this.type = TileType.EMPTY;
    }

    /**
     * Constructor for class Board.
     * @param type type to set
     */
    public Tile(TileType type) {
        this.type = type;
    }


    /**
     * Returns the tile's type.
     * @return type of the tile
     */
    public TileType getType() {
        return this.type;
    }

    /**
     * Sets the tile's type.
     * @param type type to set
     */
    public void setType(TileType type) {
        this.type = type;
    }

    /**
     * Inverts the tile type.
     * If the tile was of type COLOR_B it becomes of type COLOR_A and vice-versa.
     */
    public void invert() {
        switch (this.type) {
            case COLOR_A -> this.type = TileType.COLOR_B;
            case COLOR_B -> this.type = TileType.COLOR_A;
        }
    }

    /**
     * Returns a string representation of the tiles's type.
     * @return string representation of the tile's type
     */
    @Override
    public String toString() {
        String output = "_";
        switch (this.type) {
            case COLOR_A -> output = "X";
            case COLOR_B -> output = "0";
        }
        return output;
    }
}
