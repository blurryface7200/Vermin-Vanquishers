/**
 * 
 * @author nish
 * rat superclass contains the methods which are used for every rat
 * this is move and mate
 * move contains the logic needed to make the rat move 
 * mate allows a male and female rat to produce a baby rat
 */
public abstract class Rat {
	protected int age;
	protected int gender;
	protected boolean alive;
	protected double speed;
	protected int direction;
	protected int ratID;
	protected int health;
	protected Board board;
	protected int gasHealth; // for use in gas item
	protected static int count = 0;
	
	/**
	 * constructor for rat
	 */
	public Rat() {
		alive = true;
		direction = 1;
		ratID = count++;
		count = count ++;
		health = 100;
		gasHealth = 0; // for use in gas item
	} 

	
	/**
	 * Move mechanic for rat, checks the next tile and compares it to a number
	 * of situations such as if it needs to impregnate/be impregnated
	 * If tile is grass and if tile holds certain items 
	 * @param coordToCheckY 
	 * @param coordToCheckX
	 * @param currentPosY
	 * @param currentPosX
	 */
	public void moveMech(int coordToCheckY, int coordToCheckX, int currentPosY, int currentPosX) {
		
		Item i = getTile(currentPosY, currentPosX).getItem(); //Returns current tile
		if (getTile(coordToCheckY, coordToCheckX).getRat() != null){ //checking to see if there is a rat on the next tile 
			if (getTile(coordToCheckY, coordToCheckX).getRat().getGender() + this.gender == 3) { //checking to see if the rats are opposite genders so that they can mate		
				if (direction <= 2) {
					direction += 2;
				} else {
					direction -=2;
				}
				if (this.gender == 2) {
					this.makePregnant(true);
				} else if (getTile(coordToCheckY, coordToCheckX).getRat().getGender() == 2){
					getTile(coordToCheckY, coordToCheckX).getRat().makePregnant(true);
				}
			} else {
				if (direction <= 2) {
					direction += 2;
				} else {
					direction -= 2;
				}
			}
		}  else if (getTile(coordToCheckY, coordToCheckX).getType() == 'G') {//checks to see if the bext tile is grass
			direction = direction + 1; //changes direction since cant move on grass
			if(direction >= 5) {
				direction = 1;
			}
			//execution of stopsign
		} else if (getTile(coordToCheckY, coordToCheckX).getItem() instanceof StopSign) {
			getTile(coordToCheckY, coordToCheckX).getItem().executeItem();
			direction = direction + 1; //changes direction since cant move on grass
			if(direction == 5) {
				direction = 1;
			}
		} else if (i instanceof Poison) {
			i.executeItem();
			direction = 0; //its gonna stop moving
		} else if (i instanceof SexChange) {
			i.executeItem();
			//direction = 0; 
		} else if (i instanceof Gas || i instanceof GasExpand) {
			if (gasHealth == 3) {
				i.executeItem();
				direction = 0; 
			} else {
				gasHealth++;
			}
		} else if (i instanceof Sterilisation || i instanceof SterilisationExpand) {
			Sterilisation.sterilise(this);
			gasHealth = 0;
			getTile(currentPosY, currentPosX).removeRat(); //if not occupied removes rat from current tile
			getTile(coordToCheckY, coordToCheckX).addRat(this); //adds rat to the next tile
		
		}
		else { //checking to see if the next tile is free to move too 
			this.gasHealth = 0;
			getTile(currentPosY, currentPosX).removeRat(); //if not occupied removes rat from current tile
			getTile(coordToCheckY, coordToCheckX).addRat(this); //adds rat to the next tile
			//getTile(coordToCheckY, coordToCheckX).getItem(); // checks to see if there is an item
			}
	}
	
	/**
	 * Abstract method
	 * Makes pregnant rat on tile
	 * @param b true or false
	 */
	public void makePregnant(boolean b) {
		// TODO Auto-generated method stub	
	}

	/**
	 * Checks the rats current position and direction
	 * With this calls the moveMech method as to
	 * where to go
	 */
	public void move() {
		int[] currentPos = board.findRatPos(ratID); //getting the current co-ords for the rat
		int currentPosY = currentPos[0]; //saving the y pos as a variable 
		int currentPosX = currentPos[1]; //saving the x pos as a variable
		
		switch (direction) {
		case 1:
			moveMech(currentPosY - 1, currentPosX, currentPosY,  currentPosX);
			break;
		case 2:	
			moveMech(currentPosY, currentPosX + 1, currentPosY,  currentPosX);
			break;
			
		case 3:
			moveMech(currentPosY + 1, currentPosX, currentPosY,  currentPosX);
			break;
		case 4:
			moveMech(currentPosY, currentPosX - 1, currentPosY,  currentPosX);
			break;
		}
	}
	
	
	/**
	 * Gets the gender
	 * @return gender
	 */
	public int getGender() {
		return this.gender;
	} 
	
	/**
	 * gets the ratID
	 * @return ratID
	 */
	public int getRatID() {
		return ratID;
	}
	
	/**
	 * Gets the age
	 * @return age
	 */
	public int getAge () {
		return age;
	}
	
	/**
	 * Sets the age
	 * @param age
	 */
	public void setAge (int age) {
		this.age = age;
	}
	
	/**
	 * Gets the tile at
	 * @param y co ordinate
	 * @param x co ordinate
	 * @return tile 
	 */
	public Tile getTile(int y, int x) {
		
		return board.getTile(y, x);
	}
	
	/**
	 * Initiliases the board that the rat is on
	 * @param board
	 */
	public void initiliaseBoard(Board board) {
		this.board = board;
	}
	
	/**
	 * Sets the gender
	 * @param gender
	 */
	public void setGender (int gender) {
		this.gender = gender;
	}
	
	/**
	 * gets the health
	 * @return health
	 */
	public int getHealth () {
		return health;
	}
	
	/**
	 * Sets the health
	 * @param health
	 */
	public void setHealth (int health) {
		health = this.health;
	}
	
	/**
	 * Sets the ratID
	 * @param ratID
	 */
	public void setRatID(int ratID) {
		this.ratID = ratID;
	}
	
	/**
	 * Self kills the rat
	 */
	public void kill() {
		int[] pos = board.findRatPos(ratID);
		board.getTile(pos[0], pos[1]).removeRat();
	}
}


