/**
 * Stop Sign or "No Entry Signs" 
 * An item that can be placed on the map on a path cell by the user, 
 * this stops rats from passing through this cell until it has been hit 5 times by a rat/s
 * after 5 hits from any rat, it will stop ‘existing’
 * @author abhim
 *
 */
public class StopSign extends Item {

	private static final int ITEM_HEALTH = 5; //as the Item is removed after using it 5 times
	
	/**
	 * Creates a StopSign Item
	 * @param health the health of the Stop Sign Item i.e. "5"
	 * as it after 5 hits from any rat, it will stop ‘existing’ 
	 */
	public StopSign(Board board, Tile tile) {
		setHealth(ITEM_HEALTH);
		this.board = board;
		this.tile = tile;
	}

	@Override
	public void executeItem() {
		//"execution" of the item is done in Rat class itself
		healthDecreaseOrRemove();
	}
	

	

	
}
