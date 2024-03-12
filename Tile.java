/**
 * @author mike
 * Tile class, this can hold a rat and item and makes up the board
 */
public class Tile {
    private enum Type {
        PATH, GRASS, TUNNEL
    }

    private Type type;
    private Rat ratHere = null;
    private Item itemHere = null;
    private int xCoord;
    private int yCoord;
    public static int scoreCounter;

    /**
     * Creates a tile object
     * @param t the type (GRASS, PATH OR TUNNEL)
     * @param x co ordinate of it
     * @param y co ordinate of it
     */
    public Tile(char t, int x, int y) {
        switch (t) {
            case 'P':
                this.type = Type.PATH;
                break;
            case 'G':
                this.type = Type.GRASS;
                break;
            case 'T':
                this.type = Type.TUNNEL;
                break;
            default:
                this.type = Type.PATH;
                break;
        }
        this.xCoord = x;
        this.yCoord = y;
    }

    /**
     * Adds a rat to the tile
     * @param r rat being added
     */
    public void addRat(Rat r) {
        this.ratHere = r;

    }

    /**
     * Returns the rat on the tile
     * @return the rat on tile
     */
    public Rat getRat() {
        return this.ratHere;
    }

    /**
     * Removes the rat from the tile and adds 1 to score
     * @return the score 
     */
    public int removeRat() {
        this.ratHere = null;
      scoreCounter = scoreCounter ++;
      return scoreCounter;
    }
    
    /**
     * removes the rat without adding one to score used for ageing babyrats
     */
    public void removeBabyRat() {
    	this.ratHere = null;
    	
    }
    

    /**
     * Adds an item to the tile
     */
    public void addItem(Item i) {
        this.itemHere = i;

    }

    /**
     * Gets the current position of the item on the tile
     * @return item on tile
     */
    public Item getItem() {
        return this.itemHere;
    }

    /**
     * Removes the item from the tile
     */
    public void removeItem() {
        this.itemHere = null;

    }

    /**
     * Sets type as the letter t
     * @param t type of tile being set to
     */
    public void setType(char t) {
        switch (t) {
            case 'P':
                this.type = Type.PATH;
                break;
            case 'G':
                this.type = Type.GRASS;
                break;
            case 'T':
                this.type = Type.TUNNEL;
                break;
            default:
                this.type = Type.PATH;
        }
    }

    /**
     * Returns the type of the tile with a letter, whether its path, grass or tunnel
     * @returns char of what the tile is
     */
    public char getType() {
        switch (type) {
            case PATH:
                return 'P';
            case GRASS:
                return 'G';
            case TUNNEL:
                return 'T';
            default:
                return 'C';
        }
    }

    /**
     * Gets the X co ordinate of the tile
     * @return X co ordinate
     */
    public int getX() {
        return this.xCoord;
    }

    /**
     * Gets the Y co ordinate of the tile
     * @return Y co ordinate
     */
    public int getY() {
        return this.yCoord;
    }
}