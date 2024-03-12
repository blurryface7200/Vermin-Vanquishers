/**
 * Poison Item
 * Once placed, stays on one tile, and kills the rat that runs into it.
 * Item is removed after using it once
 * @author abhim
 *
 */
public class Poison extends Item {
	
	private static final int ITEM_HEALTH = 1; //as the Item is removed after using it once
	
	/**
	 * @param board the board
	 * @param tile the tile the item is placed on
	 */
	public Poison (Board board, Tile tile) {
		setHealth(ITEM_HEALTH);
		setTile(tile);
		setBoard(board);
	}


	/**
	 *Executes the Item
	 */
	@Override
	public void executeItem() {
		kill();
		healthDecreaseOrRemove();
	}
	
}