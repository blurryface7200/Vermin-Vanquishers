import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * 
 * @author Tudor
 *
 */
public class HighScore {

	File highScoreFile = new File("C:\\Users\\tudor\\Desktop\\HighScore.txt");
	ArrayList<Integer> highScoreList = new ArrayList<>();
	ArrayList<Integer> topHighScoreList = new ArrayList<>();

	
//	File temp = new File("C:\\Users\\tudor\\Desktop\\test.txt");
//	File file3 = new File("C:\\Users\\tudor\\Desktop\\file3.txt");

	// HighScore hs = new HighScore();
	// Score temp = new Score();

	/**
	 * Creates a highscore class
	 */
	public HighScore() {

	}

	/**
	 * Writes a highscore to the file 
	 * @param score
	 */
	public void writeToFile(int score){
		try {
			FileWriter writer = new FileWriter(highScoreFile, true);
			PrintWriter printWriter = new PrintWriter(writer);
			printWriter.println(score);
			printWriter.close();
		} catch (IOException ex) {
			System.out.println("An error occured");
			ex.getStackTrace();
		}
	}

	/**
	 * Adds the HighScores to an arraylist from the file
	 * @param file being passed in
	 * @return an arraylist of HighScores
	 */
	public ArrayList populateArrayList(File file) {
		try {
			FileReader reader = new FileReader(highScoreFile);
			BufferedReader bReader = new BufferedReader(reader);
			String s = bReader.readLine();
			
			
			while(s != null) {
				int curLine = Integer.parseInt(s);
				highScoreList.add(curLine);
				//get next line
				s = bReader.readLine();
			}
			
		} catch(IOException ex) {
			System.out.println("An error occurred.");
		    ex.printStackTrace();
		}
		
		return highScoreList;
	}
	
	/**
	 * Sorts the arraylist in descending order
	 * @param list being passed in
	 * @return the list but sorted 
	 */
	public ArrayList sortInDescendingOrder(ArrayList list) {
		  
		Collections.sort(list, Collections.reverseOrder());
		return list;
		
	}
	
	/**
	 * Takes the top 10 scores of the list
	 * @param list being passed in
	 * @return the 10 highest scores from the list
	 */
	public ArrayList topHighScores(ArrayList list) {
		int noOfHighScores = 10;
		
		for(int i = 0; i < noOfHighScores; i ++) {
			topHighScoreList.add(highScoreList.get(i));
		}
		
		return topHighScoreList;
		
	} 
	
	/**
	 * Displays the highscores out
	 */
	public void displayHighScores() {
		
		topHighScores(sortInDescendingOrder(populateArrayList(highScoreFile)));
		
		for(int i = 0; i < topHighScoreList.size(); i ++) {
			System.out.println(i + 1 + ". " + topHighScoreList.get(i));
		}
		System.out.println("");
	}

}
