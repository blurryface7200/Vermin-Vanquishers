import java.util.ArrayList;

/**
 * Bomb Item
 * Once placed, a bomb will count down from 4 in 1 second increments.
 * Once the bomb reaches zero a powerful explosion will extend
 * vertically and horizontally (not diagonally) until it reaches grass in all
 * directions (the explosion passes through tunnels).
 * The grass prevents the explosion from continuing in a given direction.
 * The explosion will kill any rats on these tiles and will also destroy any
 * other items currently placed on these tiles.
 * 
 * @author abhi, Mike
 */
public class Bomb extends Item {

	private static final int TIME = 5;// in seconds
	ArrayList<Tile> range = new ArrayList<Tile>(); // The list of tiles that the bomb is expanded onto
	
	/**
	 * Creates a bomb object
	 * @param board being placed on
	 * @param tile being placed on
	 */
	public Bomb(Board board, Tile tile) {
		setBoard(board);
		setTile(tile);
		setTime(TIME);
	}

	/**
	 * Abstract method called from the superclass item
	 * Executes its behaviour
	 */
	@Override
	public void executeItem() {
		findRange();
		for (Tile t : range) { // each tile in range gets an explosion object
			Item exp = new Explosion(board, t);
			t.addItem(exp);
			t.getItem().executeItem();
		}
		this.kill();
	}

	/**
	 * Abstract method called from the superclass item
	 * Executes a tick operation to allow it to count down
	 */
	@Override
	public void tick() {
		if (this.getTime() == 1) {
			this.executeItem();
			this.setTime(this.getTime() - 1);
		} else if (this.getTime() == 0) {
			for (Tile t : range) { // each tile in range has its explosion removed (gets cleared out)
				t.removeItem();
			}
			this.getTile().removeItem(); // Finally, bomb removes itself from the game
		} else {
			this.setTime(this.getTime() - 1);
		}
	}

	/**
	 * Finds the range of the bomb
	 * Goes through each path tile till it hits a grass tile
	 */
	public void findRange() {
		Tile tempTile = this.tile;
		while (board.getTile(tempTile.getY(), tempTile.getX() + 1).getType() != 'G') {
			// Go up recursively until you hit grass, put all path/tunnel tiles into range
			tempTile = board.getTile(tempTile.getY(), tempTile.getX() + 1);
			range.add(tempTile);
		}
		tempTile = this.tile;
		while (board.getTile(tempTile.getY(), tempTile.getX() - 1).getType() != 'G') {
			// Go down recursively until you hit grass, put all path/tunnel tiles into range
			tempTile = board.getTile(tempTile.getY(), tempTile.getX() - 1);
			range.add(tempTile);
		}
		tempTile = this.tile;
		while (board.getTile(tempTile.getY() + 1, tempTile.getX()).getType() != 'G') {
			// Go right recursively until you hit grass, put all path/tunnel tiles into
			// range
			tempTile = board.getTile(tempTile.getY() + 1, tempTile.getX());
			range.add(tempTile);
		}
		tempTile = this.tile;
		while (board.getTile(tempTile.getY() - 1, tempTile.getX()).getType() != 'G') {
			// Go left recursively until you hit grass, put all path/tunnel tiles into range
			tempTile = board.getTile(tempTile.getY() - 1, tempTile.getX());
			range.add(tempTile);
		}
		tempTile = this.tile;
	}

}