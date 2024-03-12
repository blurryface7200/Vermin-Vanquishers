import java.util.Random;
import java.util.TimerTask;
/**
 * 
 * @author nish, steff
 *  the baby rat class inherits the move and mate mechanics from the rat superclass
 *  its has a unique method age which ages the rat as the game progesses so that it can eventually become an adult and mate
 *  its gender is randomly assigned on creation 
 */

public class BabyRat extends Rat {

	/**
	 * Constructs a BabyRat, used when creating a babyrat from 
	 * birth
	 */
	public BabyRat () {
		ratID = count++;
		count = count++;
		gender = Random(0,2);
		this.age = 0;
		
	}
	
	/**
	 * Constructs a BabyRat
	 * Used in the readLevelFile for spawn locations
	 * @param gender being passed by file
	 */
	public BabyRat(int gender) {
		this.gender = gender;
		this.age = 0;
	}
	
	/**
	 * For generating a random integer to determine the rats
	 * gender
	 * @param i lower bound
	 * @param j upper bound
	 * @return random int between lower and upper bound
	 */
	private int Random(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Increments the baby rats age until a certain count is hit
	 * When the count is hit it checks the BabyRats gender
	 * and creates a female or male rat correspondingly 
	 */
	public void age() {
		this.age += 1;
		if (age % 90 == 0) {
			if (gender == 1) {
				AdultMaleRat m = new AdultMaleRat();
				int[] pos = board.findRatPos(this.getRatID());
				int ypos = pos[0];
				int xpos = pos[1];
				Tile tile = board.getTile(ypos, xpos);
				tile.removeBabyRat();
				tile.addRat(m);
			} else if (gender == 2) {
				AdultFemaleRat f = new AdultFemaleRat();
				int[] pos = board.findRatPos(this.getRatID());
				int ypos = pos[0];
				int xpos = pos[1];
				Tile tile = board.getTile(ypos,xpos);
				tile.removeBabyRat();
				tile.addRat(f);
			}
			age = 0;
		}
	}
}
