import java.util.ArrayList;
/**This class represents a Level object, the level holds the data for the size and layout of the map,
 * along with win/loss conditions, baby rat spawn locations and item spawn rates for that level
 * @author Steffan Jones
 * @version 1.0
**/

public class Level {
	public static ArrayList<Level> levels = new ArrayList<Level>();
	
	private int width;  //x
	private int height; //y
	private char mapLayout[][];
	private BabyRat spawnSpots[][];
	private int lossCondition;
	private int endTimer;
	private int itemSpawnRates[];
	//EACH INDEX IN THIS ARRAY ^^ RELATES TO A DIFFERENT ITEM SPAWN RATE AS SUCH:
	//0:BOMBS 1:GAS 2:STERILISATION 3:POISON 4:MALE SEX CHANGE
	//5:FEMALE SEX CHANGE 6:STOP SIGN 7:DEATH RATS
	//private HighScore[] levelHighScore = new HighScore[10];
	
	/**
	 * Creates a level object
	 * @param width of the map (x) as int
	 * @param height of the map (y) as int
	 * @param mapLayout as 2d array of chars, each coord will have a char type that defines if the tile is grass,
	 *  path, tile or baby rat spawn location
	 * @param spawnSpots as 2d array of BabyRat objects, has co-ords for BabyRat objects with their predefined gender
	 * @param lossCondition of the level, losing number of rats 
	 * @param endTimer, time player expected the finish level in
	 * @param itemSpawnRates array of item spawn rates, each index relates to different item, as discussed above
	 */

	public Level(int width, int height, char[][] mapLayout, 
			 BabyRat[][] spawnSpots, int lossCondition, int endTimer, int[] itemSpawnRates) {
		setWidth(width);
		setHeight(height);
		setMapLayout(mapLayout);
		setSpawnSpots(spawnSpots);
		setLossCondition(lossCondition);
		setEndTimer(endTimer);
		setSpawnRates(itemSpawnRates);
	}
	
	/**
	 * Set the width of the level map
	 * @param width number
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Set the height of the level map
	 * @param height number
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Set the layout of the map from the level
	 * @param mapLayout 2d array of char
	 */
	public void setMapLayout(char mapLayout[][]) {
		this.mapLayout = mapLayout;
	}
	
	/**
	 * Set the spawn points of the baby rats
	 * @param spawnSpots 2d array of BabyRat objects
	 */
	public void setSpawnSpots(BabyRat spawnSpots[][]) {
		this.spawnSpots = spawnSpots;
	}
	
	/**
	 * Sets the loss condition of the level
	 * @param lossCondition the population of rats that causes the 
	 * player to lose the level
	 */
	public void setLossCondition(int lossCondition) {
		this.lossCondition = lossCondition;
	}
	
	/**
	 * Sets the end timer of the level
	 * @param endTimer the time (in seconds) the player must 
	 * beat the level in
	 */
	public void setEndTimer(int endTimer) {
		this.endTimer = endTimer;
	}
	
	//public void setLevelHighScore(HighScore[] levelHighScore) {
	//	this.levelHighScore = levelHighScore;
	//}
	
	/**
	 * Sets the spawn rates of each item in the inventory
	 * @param itemSpawnRates array of int, each index goes to 
	 * a specific item
	 */
	public void setSpawnRates(int[] itemSpawnRates) {
		this.itemSpawnRates = itemSpawnRates;
	}
	
	/**
	 * gets the specific spawn rate for an item at index i
	 * @param i the index of which item's spawn rate to get
	 * @return the spawn rate of the item in int
	 */
	public int getSpawnRate(int i) {
		return itemSpawnRates[i];
	}
	
	/**
	 * gets the width
	 * @return the width
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * gets the height
	 * @return the height
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * gets the layout of the map
	 * @return 2d char array of the map
	 */
	public char[][] getMapLayout() {
		return this.mapLayout;
	}
	
	/**
	 * gets the loss condition of the level
	 * @return the number of rats needed to lose
	 */
	public int getLossCondition() {
		return this.lossCondition;
	}
	
	/**
	 * gets the end timer of the level
	 * @return the time needed to pass that player is expected to win the level in
	 */
	public int getEndTimer() {
		return this.endTimer;
	}

	/**
	 * gets the spawn points of the baby rats on the map
	 * @return 2d array of baby rats
	 */
	public BabyRat[][] getSpawnSpots() {
		return this.spawnSpots;
	}
	//Delete, if Tudors highscore class doesn't work
	//public HighScore[] getLevelHighScores() {
		//return this.levelHighScore[];
	//}
	
//	
//	public void addHighScore(HighScore highscore, Profile profile) {
//		
//	}
//	
	/**
	 * Adds a level to the static arraylist of levels
	 * @param level being added
	 */
	public static void addLevel(Level level) {
		levels.add(level);
	}
	
	/**
	 * Gets the level at index i
	 * @param i int index
	 * @return the level at i in the arraylist
	 */
	public static Level getLevel(int i) {
		return levels.get(i);
	}
	
	/**
	 * Generates a board class with the mapLayout
	 * @param level being used
	 * @return new board object
	 */
	public static Board generateBoard(Level level) {
		Board b = new Board(level.getMapLayout());
		return b;
	}
}