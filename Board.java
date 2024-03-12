/**
 * @author mike
 * 
 */
public class Board {
    // the actual map of Tiles
    private Tile[][] tiles;
    // dimensions of the board for later use with JavaFX
    private int dimX;
    private int dimY;
    private char[][] map; // the grid of tiles with P as path, G as grass, T as tunnel

    /**
     * creates a board
     * @param m map or the grid of tiles with P as path, G as grass, T as tunnel
     */
    public Board(char[][] m) {
        this.map = m;
        dimY = m.length;
        dimX = m[1].length;
        generateTiles(m);
    }

    /**
     * method to translate char[][] into Tile[][]
     * @param m  map or the grid of tiles with P as path, G as grass, T as tunnel
     */
    public void generateTiles(char[][] m) {
        // method to translate char[][] into Tile[][]
        tiles = new Tile[dimY][dimX];
        // We changed dimX with dimY
        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < m[i].length; j++) {
                tiles[i][j] = new Tile(m[i][j], j, i);
            }
        }
    }

    /**
     * @param row index of the row (Y) starting from 0 to dimY-1
     * @param col index of the column (X) starting from 0 to dimX-1
     * @return the tile of the given indices 
     */
    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Method to find the current position of a rat
     * @param ratID ratID of the rat
     * @return the position array of the rat in the form of [y, x] 
     */
    public int[] findRatPos(int ratID) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].getRat()!= null && tiles[i][j].getRat().getRatID() == ratID) {
                    int[] x = new int[2];
                    x[0] = i;
                    x[1] = j;
                    return x;
                } else {
                }
            }
        }
        return null;
    }

   
    /**
     * @return the number of columns, X refers to it being the length on the X axis
     */
    public int getDimX() {
        return this.dimX;
    }

    /**
     * @return the number of rows, Y refers to it being the length on the Y axis
     */
    public int getDimY() {
        return this.dimY;
    }

}