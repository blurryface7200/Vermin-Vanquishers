import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Steffan Jones
 * This is a class that reads the files in a folder that holds the format of how the levels will be made
 * It then creates a level object for each of these files on the folder 
 */

public class ReadLevelFile {
	
	/**
	 * Takes in a string of a folder path and for each file in folder
	 * creates a level object with it
	 * @param fileName - folder path
	 */
	public static void readFiles(String fileName) throws FileNotFoundException {
		File folder = new File(fileName);
		for (final File fileEntry : folder.listFiles()) {
			try {
				Scanner in = new Scanner(fileEntry);
				Level.addLevel(createLevel(in));
			}
			catch (FileNotFoundException e) {
				throw new FileNotFoundException();
			}
		}
	}
	
	/**
	 * Takes in a file (with the use of a scanner) and creates
	 * a level object from the files contents
	 * @param in - the file being read
	 * @return a level object using the data in the file
	 */
	private static Level createLevel(Scanner in) {
		int cols = in.nextInt();
		in.nextLine();
		int rows = in.nextInt();
		
		char[][] mapLayout = new char [rows][cols];
		for (int i = 0; i < rows; i++) {
			in.nextLine();
			for (int j = 0; j < cols; j++) {
				mapLayout[i][j] = in.next().charAt(0);
			}
		}
		BabyRat[][] spawnSpots = new BabyRat[rows][cols];
		// Goes through each line of baby rat and creates one with its spawnpoint
		// until it hits the "//" which will break the loop as 
		// it has finished creating baby rat spawn points
		while ((in.hasNextLine()) && !((in.nextLine().equals("//")))) {
			int gender = in.nextInt();
			int xSpawn = in.nextInt();
			int ySpawn = in.nextInt();
			spawnSpots[ySpawn][xSpawn] = new BabyRat(gender);
			in.nextLine();
		}

		int lossCondition = in.nextInt();
		in.nextLine();
		int endTimer = in.nextInt();
		in.nextLine();
		int[] itemSpawnRates = new int[8];
		
		for (int i = 0; i < 8; i++) {
			itemSpawnRates[i] = in.nextInt();
		}
		
		Level level = new Level(cols, rows, mapLayout, spawnSpots, lossCondition, endTimer, itemSpawnRates);
		return level;
	}	
}
