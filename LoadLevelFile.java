import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Shafquat Hossain
 * This class reads the save file and loads it so that the user can continue
 * where they left off
 */

public class LoadLevelFile {
	public static void main(String[] args) {
		try {
			File myObj = new File(needs file.txt here);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
			}
			new Board(data);
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println ("An error occured. Unable to load file.");
			e.printStackTrace();
			}
	}
}
