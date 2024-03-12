import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a profile object that the user can create
 * @author Steffan Jones
 * @version 1.0
 */
public class Profile {
	
    private static ArrayList<Profile> profilesList = new ArrayList<Profile>();
	
	private String name;
	private Boolean[] lvlsUnlocked;
	
	/**
	 * Create a profile with name and how many levels it has unlocked.
	 * Used when reading/loading profiles from the file 
	 * Adds these profiles to a static array list of profiles
	 * @param name of profile
	 * @param lvlsUnlocked of the profile
	 */
	public Profile (String name, Boolean[] lvlsUnlocked) {
		setName(name);
		setLevels(lvlsUnlocked);
		profilesList.add(this);
	}
	
	/**
	 * Creates a profile with their name
	 * Used when creating a profile as levels unlocked will automatically be assigned
	 * @param name of profile
	 * @throws IOException can be thrown if the profile already exists
	 */
	public Profile (String name) throws IOException {
		if (!isCurrentProfile(name)) {
			writeProfile(name);
			setName(name);
			profilesList.add(this);
		}
	}
	
	/**
	 * sets name of the profile
	 * @param name of the profile
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the name of the profile
	 * @return name of the profile
	 */
	public String getName() {
		return name;
	}
	
	public Boolean[] getLvlsUnlocked() {
		return lvlsUnlocked;
	}
	/**
	 * Sets the levels unlocked by profile
	 * @param lvlsUnlocked array of bool values 
	 * true is unlocked level at index, false if not
	 */
	public void setLevels(Boolean[] lvlsUnlocked) {
		this.lvlsUnlocked = lvlsUnlocked;
	}
	
	/**
	 * Checks if the level is unlocked by the profile
	 * @param lvlsUnlocked by the profile
	 * @param level object being searched for
	 * @return true if the profile has unlocked the level
	 * 		   false if not
	 */
	public Boolean isLevelUnlocked(Boolean[] lvlsUnlocked, int level) {
		if (lvlsUnlocked[level]) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Checks if the profile being created by the user is already a current profile
	 * By searching for it in a file called ProfilesList which holds all profile 
	 * names and their levels unlocked
	 * @param name of profile being searched for
	 * @return true if the profile already exists, false if not
	 * @throws IOException if the ProfilesList file does not exist
	 */
	public static boolean isCurrentProfile(String name) throws IOException {
		try (FileReader fr = new FileReader("C:\\Users\\abhim\\Desktop\\CSE 2021-22\\RATSGN12\\ProfilesList.txt");
				BufferedReader br = new BufferedReader(fr)) {
			String line = br.readLine();
			while (line != null) {
				if (line.contains(name)) {
					return true;
				}
				line = br.readLine();
			}
			return false;
		}
	}
	
	/**
	 * Reads all the profiles from the file and creates objects from them
	 * @throws Exception if file does not exist
	 */
	public static void readProfiles() throws Exception {
		try (FileReader fr = new FileReader("C:\\Users\\abhim\\Desktop\\CSE 2021-22\\RATSGN12\\ProfilesList.txt");) {
			Scanner in = new Scanner(fr);
			while (in.hasNextLine()) {
				Scanner line = new Scanner(in.nextLine()).useDelimiter(",");
				String name = line.next();
				Boolean[] lvlsUnlocked = new Boolean[4];
				lvlsUnlocked[0] = convertStringToBool(line.next());
				lvlsUnlocked[1] = convertStringToBool(line.next());
				lvlsUnlocked[2] = convertStringToBool(line.next());
				lvlsUnlocked[3] = convertStringToBool(line.next());
				new Profile(name, lvlsUnlocked);
			}
			in.close();
		}
	}
	
	/**
	 * Used to check the values in the file and converts them to bool
	 * @param in string being passed in
	 * @return true if the string = true, false if not
	 */
	public static boolean convertStringToBool(String in) {
		if (in.equals("true")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Gets the list of profiles 
	 * @return array list of profiles
	 */
	public static ArrayList<Profile> getProfilesList() {
		return profilesList;
	}
	
	/**
	 * Writes the profile to the profiles file if it is a new profile 
	 * @param name of the profile
	 * @throws IOException if the file does not exist
	 */
	public static void writeProfile(String name) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("C:\\Users\\abhim\\Desktop\\CSE 2021-22\\RATSGN12\\ProfilesList.txt", true));
		out.write(name);
		out.write(",true,false,false,false");
		out.write("\n");
		out.close();
	}
	
	/**
	 * gets the profile object
	 * @param name of the profile being searched
	 * @return the profile
	 */
	public static Profile getProfile(String name) {
		for (int i = 0; i < profilesList.size(); i++) {
			if (profilesList.get(i).getName().equals(name)) {
				return profilesList.get(i);
			}
			else {
				
			}
		}
		return null;
	}
}
