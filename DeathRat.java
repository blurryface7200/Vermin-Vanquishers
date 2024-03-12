/**
 * @author abhim and mike
 * A death rat item, walks around the board 
 * killing any rat it touches until it kills 5
 * then it removes itself
 */

import java.util.Random;

public class DeathRat extends Item {

	private static final int ITEM_HEALTH = 5; //Works 5 times 
	private static final int TIMER = 10; //Remains stationary for first 10 200 milliseconds
	private int direction;
	private Random r;

	/**
	 * Creates a DeathRat object
	 * @param board being placed on
	 * @param tile being placed on
	 */
	public DeathRat(Board board, Tile tile) {
		setHealth(ITEM_HEALTH);
		setTile(tile);
		setBoard(board);
		setTime(TIMER);
		r = new Random();
		this.direction = r.nextInt(4);
	}

	/**
	 * A tick operation
	 * Allows it to move around the board 
	 */
	@Override
	public void tick() {
		if (this.getTime() != 0) {
			this.executeItem();
			this.setTime(getTime()-1);
		} else {
			this.executeItem(); // kill before moving
			if (direction == 4) {
				direction = 1;
			}
			switch (direction) {
				case 0:
					if (board.getTile(this.getTile().getY() + 1, this.getTile().getX()).getItem() == null
							&& board.getTile(this.getTile().getY() + 1, this.getTile().getX()).getType() != 'G') {
						Tile newTile = board.getTile(this.getTile().getY() + 1, this.getTile().getX());
						newTile.addItem(this);
						this.getTile().removeItem();
						this.setTile(newTile);
					} else {
						this.direction = r.nextInt(4);
						tick();
					}
					break;
				case 1:
					if (board.getTile(this.getTile().getY() - 1, this.getTile().getX()).getItem() == null
							&& board.getTile(this.getTile().getY() - 1, this.getTile().getX()).getType() != 'G') {
						Tile newTile = board.getTile(this.getTile().getY() - 1, this.getTile().getX());
						newTile.addItem(this);
						this.getTile().removeItem();
						this.setTile(newTile);
					} else {
						this.direction = r.nextInt(4);
						tick();
					}
					break;
				case 2:
					if (board.getTile(this.getTile().getY(), this.getTile().getX() + 1).getItem() == null
							&& board.getTile(this.getTile().getY(), this.getTile().getX() + 1).getType() != 'G') {
						Tile newTile = board.getTile(this.getTile().getY(), this.getTile().getX() + 1);
						newTile.addItem(this);
						this.getTile().removeItem();
						this.setTile(newTile);
					} else {
						this.direction = r.nextInt(4);
						tick();
					}
					break;
				case 3:
					if (board.getTile(this.getTile().getY(), this.getTile().getX() - 1).getItem() == null
							&& board.getTile(this.getTile().getY(), this.getTile().getX() - 1).getType() != 'G') {
						Tile newTile = board.getTile(this.getTile().getY(), this.getTile().getX() - 1);
						newTile.addItem(this);
						this.getTile().removeItem();
						this.setTile(newTile);
					} else {
						this.direction = r.nextInt(4);
						tick();
					}
					break;
				default:
					break;
			}
			this.executeItem(); // kill after moving
		}
	}

	/**
	 * Called when the item needs to execute
	 * Kills the rat on the tile it is on.
	 */
	@Override
	public void executeItem() {
		if (this.getTile().getRat() != null) {
			this.healthDecreaseOrRemove();
			this.kill();
		} 
		if (getHealth()==0) {
			this.remove();
		}
	}
}
