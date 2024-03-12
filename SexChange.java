/**
 * SexChange Item
 * An item that can be placed on the map on a path cell by the user.
 * This turns the sex of the rat into a specific sex (Male/Female) once it runs
 * into it
 * and the item is used up and removed from the path
 * (if the initial sex of the rat is same as the one it is supposed to turn
 * into,
 * the item is still used up and removed).
 * The item will remain on the path until a rat runs into it.
 * 
 * @author abhi
 *
 */
public class SexChange extends Item {

	private static final int ITEM_HEALTH = 1; // as the Item is removed after using it once
	private int sexChangeInto; // Male = 1 or Female = 2

	/**
	 * method to change the sex of the rat
	 * 
	 * @param board         the board
	 * @param tile          the tile it is placed on
	 * @param sexChangeInto the sex of the rat that the item will turn it into (1
	 *                      for Male & 2 for Female)
	 */
	public SexChange(Board board, Tile tile, int sexChangeInto) {
		setHealth(ITEM_HEALTH);
		this.sexChangeInto = sexChangeInto;
		this.board = board;
		this.tile = tile;
		// java fx : if sexChangeInto == male create the "male sign" image else "female
		// sign" image

	}

	/**
	 * Changes the sex of the Rat into the specified sex.
	 * If the sex of the rat as same as as the one it is supposed to turn into,
	 * the item is still used up and removed
	 * 
	 * @param rat the rat present on the tile
	 */
	public void sexChange(Rat rat) {
		if (sexChangeInto == 1) {
			AdultMaleRat newRat = new AdultMaleRat();
			switch (rat.getGender()) {
				case 1:
					break;
				case 2:
					if (rat instanceof AdultFemaleRat) {
						tile.removeRat();
						tile.addRat(newRat);
						System.out.println("CHANGED INTO MALE");
					}
					else {
						//rat.setGender(1);
					}
					break;
				case 3:
					newRat.setGender(3);
					break;
				case 4:
					tile.removeRat();
					tile.addRat(newRat);
					newRat.setGender(3);
					break;
				default:
					break;
			}
		} else {
			AdultFemaleRat newRat = new AdultFemaleRat();
			switch (rat.getGender()) {
				case 1:
					if (rat instanceof AdultMaleRat) {
						tile.removeRat();
						tile.addRat(newRat);
					}
					else {
						//rat.setGender(2);
					}
					break;
				case 2:
					break;
				case 3:
					this.kill();
					tile.addRat(newRat);
					newRat.setGender(4);
					break;
				case 4:
					newRat.setGender(4);
					break;
				default:
					break;
			}
			
		}
	}

	/**
	 * Execution of the Item
	 */
	public void executeItem() {
		if(this.getTile().getRat() != null) {
			System.out.println("ITS EXECUTING!!");
			System.out.println(tile.getRat().getGender());
			sexChange(tile.getRat());
			System.out.println(tile.getRat().getGender());
			healthDecreaseOrRemove();
			//System.out.println("ITS EXECUTING!!");
		}
	}
	
//	public void tick() {
//		executeItem();
//		//System.out.println("ITS TICKING!!");
//	}
}
