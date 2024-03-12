import java.util.ArrayList;

/**
 * Gas Item
 * Once placed, gas will slowly expand and occupy neighbouring 
 * tiles with a radius of 2 (top, bottom, left and right only)
 * if a rat stays in the gas for 1.5 second it dies
 * radius of 0 i.e., the original tile in first second
 * radius of 1 in 2nd second
 * radius of 2 in 3rd second
 * in 4th second it disappears
 * @author abhim
 *
 */
public class Gas extends Item {
	
	private static final int TIME = 4;//in seconds
	private static final int RADIUS = 2;//No of tiles //increases gradually from initial tile to radius of 2
	private ArrayList<Tile> range = new ArrayList<Tile>(); //The list of tiles that the gas is spread onto 
	
	/**
	 * Creates a gas object
	 * @param board the board
	 * @param tile the original tile the item is placed onto
	 */
	public Gas (Board board, Tile tile) {
		setTime(TIME);
		setRadius(RADIUS);
		setTile(tile);
		setBoard(board);
	}
	
	/**
	 * Item is executed for every 'tick' 
	 * 'ticked' every 1 seconds
	 * 
	 */
	@Override
	public void tick() {
		switch(this.getTime()) {
			case 4: 
				this.setTime(getTime()-1);
				break;
			case 3:
				Tile t = this.neighbouringTile(0, 1);
				if (t.getItem() == null) {
					Item temp = new GasExpand(board, t);
					t.addItem(temp);
					range.add(t);
				}
				t = this.neighbouringTile(0, -1);
				if (t.getItem() == null) {
					Item temp = new GasExpand(board, t);
					t.addItem(temp);
					range.add(t);
				}
				t = this.neighbouringTile(1, 0);
				if (t.getItem() == null) {
					Item temp = new GasExpand(board, t);
					t.addItem(temp);
					range.add(t);
				}
				t = this.neighbouringTile(-1, 0);
				if (t.getItem() == null) {
					Item temp = new GasExpand(board, t);
					t.addItem(temp);
					range.add(t);
				}
				this.setTime(getTime()-1);
				break;
			case 2:
				Tile t2 = this.neighbouringTile(0, 2);
				if (t2.getItem() == null) {
					Item temp = new GasExpand(board, t2);
					t2.addItem(temp);
					range.add(t2);
				}
				t2 = this.neighbouringTile(0, -2);
				if (t2.getItem() == null) {
					Item temp = new GasExpand(board, t2);
					t2.addItem(temp);
					range.add(t2);
				}
				t2 = this.neighbouringTile(2, 0);
				if (t2.getItem() == null) {
					Item temp = new GasExpand(board, t2);
					t2.addItem(temp);
					range.add(t2);
				}
				t2 = this.neighbouringTile(-2, 0);
				if (t2.getItem() == null) {
					Item temp = new GasExpand(board, t2);
					t2.addItem(temp);
					range.add(t2);
				}
				this.setTime(getTime()-1);
				break;
			case 1:
				this.setTime(getTime()-1);
				break;
			case 0:
				for (Tile x : range) {
					x.removeItem();
					this.getTile().removeItem();
				}
			default:
				break;
		}
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
