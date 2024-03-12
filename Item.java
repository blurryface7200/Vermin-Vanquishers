
/**
 * @author abhi
 *
 */
public abstract class Item {

	private int health; //health the health of the Item
	private int time; // (in seconds) time the item lasts once it is placed
	private int radius; //the radius over which the item is spread
	protected Tile tile;
	protected Board board;
	
	/**
	 * method that is overrided by sub class items
	 */
	public void tick() {
		
	}
	
	/**
	 * @return the radius
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * @param radius the radius to set
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * Sets the health of the Item
	 * @param health the value of health
	 */
	public void setHealth (int health) {
		this.health = health;
	}
	
	/**
	 * Decrement by one in the health of the Item one once it is used 
	 * Removes the item once it reaches 0 (from 1)
	 */
	public void healthDecreaseOrRemove() {
		if (this.getHealth()==1) {
			this.remove();
			health--;
		}
		
		health--;
		//System.out.println(getHealth());
		
	}
	
	/**
	 * Removes the item from the tile
	 * 
	 */
	public void remove() {
		getTile().removeItem();
	}
	

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Execution of the Item
	 * @param board
	 */
	public abstract void executeItem ();
	

	/**
	 * method to kill
	 * 
	 */
	public void kill() {
		getTile().removeRat();
	}


	/**
	 * @return the tile
	 */
	public Tile getTile() {
		return tile;
	}

	/**
	 * @param tile the tile to set
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	
	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public Tile neighbouringTile(int x, int y) {
		if ((tile.getY()+y)>(board.getDimY()-1) || (tile.getX()+x)>(board.getDimX()-1)) {
			return board.getTile(tile.getY(), tile.getX());
		} else {
			return board.getTile(tile.getY()+y, tile.getX()+x);
		}
	}
}
