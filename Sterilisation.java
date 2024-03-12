import java.util.ArrayList;

/**
 * Sterilisation Item
 * Once placed, the sterilisation item will make all rats within a radius of 1 tile sterile. 
 * It will remain active for 3 seconds.
 * A sterile baby rat will develop into a sterile adult rat.
 * @author abhi
 *
 */
public class Sterilisation extends Item {

	private static final int TIME = 3;//in seconds
	private static final int RADIUS = 1;//No of tiles
	private ArrayList<Tile> range = new ArrayList<Tile>();
	//private int oneTenthSec = 10*TIME;
	
	/**
	 * @param board the board
	 * @param tile the original tile it is placed on
	 */
	public Sterilisation (Board board, Tile tile) {
		setTime(TIME);
		setRadius(RADIUS);
		setTile(tile);
		setBoard(board);
		expand();
	}

	/**
	 *Executes the item
	 */
	public void expand() {
			for(int i=-1; i<RADIUS+1; i++) {
				for(int j=-1; j<RADIUS+1; j++) {
					//System.out.println(board.getTile(tile.getY(), tile.getX()).getRat());
					Tile t = neighbouringTile(i, j);
					Item temp = new SterilisationExpand(this.board, t);
					t.addItem(temp);
					range.add(t);
					//sterilise(this.neighbouringTile(i, j).getRat());
				}
			}
	}
	
	/**
	 * Item is executed for every 'tick'
	 * 'ticked' every one-tenth seconds or 100 milliseconds
	 */
	@Override
	public void tick() {
		//expand();
		this.setTime(getTime()-1);
		if (getTime() == 0) {
			remove();
			for (Tile t : range) { // each tile in range has its explosion removed (gets cleared out)
				t.removeItem();
			}
		}
	}
	
	/**
	 * makes the rat sterile
	 * @param rat the rat present on the tile
	 */
	public static void sterilise(Rat rat){
		//make a method to sterilise in rat class or here
		System.out.println(rat.getGender());
		if(rat.getGender()==1) {
			//System.out.println(rat.getGender());
			rat.setGender(3);
			System.out.println(rat.getGender());
		}else if(rat.getGender()==2){
			//System.out.println(rat.getGender());
			rat.setGender(4);
			System.out.println(rat.getGender());
		}else {
			//do nothing
		}	
	}

	@Override
	public void executeItem() {
		// execution done in rat class
		
	}
	
	
	
}