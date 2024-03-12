/**
 * 
 * @author nish
 * the female rat inherits the majority of its methods from rat 
 * it has a unique method pregnancy timer which releases the baby rat after a set time 
 */
public class AdultFemaleRat extends Rat {

	boolean pregnant;
	int pregnantTimer;
	
	/**
	 * Constructs an AdultFemaleRat object
	 */
	public AdultFemaleRat() {
		gender = 2;
		ratID = count ++;
		count = count++;
		age = 18;
		pregnant = false;
		pregnantTimer = 0;
	}
	
	/**
	 * Checks if this rat is pregnant
	 * @return bool pregnant
	 */
	public boolean isPregnant() {
		return this.pregnant;
	}
	
	/**
	 * Makes this rat pregnant
	 * @param pregnant
	 */
	@Override
	public void makePregnant(boolean pregnant) {
		this.pregnant = pregnant;
	}
	
	/**
	 * Increments the timer on the female rat to give birth
	 */
	public void pregnantAge() {
		pregnantTimer += 1;
		if (pregnantTimer % 40 == 0) {
			BabyRat b = new BabyRat();
			int[] lastPos = board.findRatPos(this.ratID);
			this.move();
			board.getTile(lastPos[0], lastPos[1]).addRat(b);
		}
	}
	
}
