import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter; // Import FileWriter class
import java.io.IOException; // Import IOException class for handling error

/**
 * @author shafa
 * This class takes all the current data of a game and saves it to 
 * a file so that the user can continue with it later
 */
public class SaveLevelFile {
	private Board board;
	private Tile[][]tiles;
	private Level level;
	private int dimX;
	private int dimY;
	private int x;
	private int y;
	private int updatingScore;
	private Profile p;
	public String filename;

	/**
	 * Constructor for the class
	 * @param board
	 * @param level
	 */
	public SaveLevelFile(Board board, Level level) {
		instantiateBoard(board, level);
	}
	
	
	/**
	 * Instantiates the board object to the file
	 * @param board
	 * @param level
	 */
	public void instantiateBoard(Board board, Level level){
		this.board = board;
		this.dimX = board.getDimX();
		this.dimY = board.getDimY();
		this.level = level;
	}

	/**
	 * Method to save all the data to a file 
	 * @throws IO exception if the file name is wrong
	 */
	public static void saveFile() {
		FileWriter filename;
		try {
			filename = new FileWriter("SaveLevel.txt");
			BufferedWriter filewrite = new BufferedWriter(filename);
			filewrite.write(getData());
			filewrite.write(getData());
			filewrite.write(getData());
			filewrite.write(p);
			filewrite.write(updatingScore);
			
			filewrite.close();
			
		}
		
		catch (IOException except) {
			except.printStackTrace();
		}
	}
		
	/**
	 * 	Method to gather data on number of items on board, number of rats and types of tiles
	 * @return String of data in the item arrays, rat arrays and map
	 */
	public static String getData() {
		Item [][] saveMapItem;
		for (int i=0; i<dimX; i++) {
			for (int j=0; j<dimY; j++) {
				if (board.getTile(i,j).getItem() != null) {
					saveMapItem[i][j] = board.getTile(i, j).getItem();
				}
				
			}
		}

		char [][] map = level.getMapLayout();
		
		Rat [][] saveMapRat;
		for (int i=0; i<dimX; i++) {
			for (int j=0; j<dimY; j++) {
				if (board.getTile(i,j).getRat() != null) {
					saveMapRat[i][j] = board.getTile(i,j).getRat();
					}
			}
		}
	}
	 
	/**
	 * Method to get the user profile
	 */
	public static Profile getProfile(String name) {
		this.p = Profile.getProfile(name);
		return p;
	}
	
	/**
	 * Returns the score of the level
	 * @return updatingScore 
	 */
	public static int getScore() {
		return updatingScore;
	}
		
	

}
