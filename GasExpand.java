/**
 * A dummy class for Gas expansion
 * @author abhim
 *
 */
public class GasExpand extends Item{
	 
	/**
	 * Creates a GasExpand object
	 * @param board the board
	 * @param tile the tile the gas expands into 
	 */
	public GasExpand(Board board, Tile tile) {
	        this.board = board;
	        this.tile = tile;
	        //executeItem();
	    }
	
	/**
	 *A dummy method
	 *"execution" of the item is done in Rat class itself
	 */
	@Override
	public void executeItem() {
		kill();
	}
	

}
