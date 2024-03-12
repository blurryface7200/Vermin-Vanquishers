import java.util.ArrayList;

public class ProfileTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Profile.readProfiles();
		ArrayList<Profile> profiles = Profile.getProfilesList();
		Boolean[] lvlsUnlocked = profiles.get(0).getLvlsUnlocked();
		System.out.println(profiles.get(0).getName());
		Profile p = Profile.getProfile("Steff");
		lvlsUnlocked = p.getLvlsUnlocked();
	}

}
