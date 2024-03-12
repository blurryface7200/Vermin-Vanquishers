/**
 * 
 * @author Mike
 * Used in the bomb class to allow for it to touch other tiles
 */
public class Explosion extends Item {

	/**
	 * Creates an explosion object
	 * @param board being placed on
	 * @param tile being placed on
	 */
    public Explosion(Board board, Tile tile) {
        this.board = board;
        this.tile = tile;
        executeItem();
    }

    /**
     * Uses abstract method from item class to execute its 
     * behaviour, so it kills the rat on the tile it is on.
     */
    @Override
    public void executeItem() {
        kill();
    }
}