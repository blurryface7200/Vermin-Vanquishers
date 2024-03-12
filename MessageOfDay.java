import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author Steffan Jones 
 * Class that works statically to provide the message of the day
 *
 */
public class MessageOfDay {
	
	/**
	 * Gets the text from the website "http://cswebcat.swansea.ac.uk/puzzle"
	 * @return String of the body of the website
	 * @throws IOException incase the website entered is incorrect
	 * @throws InterruptedException if there is an interruption when attemping to get a response
	 */
	public static String getText() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://cswebcat.swansea.ac.uk/puzzle"))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	
	/**
	 * This method takes the string from website and applies a shift on the letters
	 * Every odd indexed letter is shifted forwards by its index
	 * Every even indexed letter is shifted backwards by its index
	 * @return a StringBUilder with the altered text after shifts have been applied
	 * @throws IOException if the website passed is wrong
	 * @throws InterruptedException if there is an interruption when attempting to join website
	 */
	public static StringBuilder solvePuzzle() throws IOException, InterruptedException {
		String puzzle = MessageOfDay.getText();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < puzzle.toCharArray().length; i++) {
			if (puzzle.charAt(i) != ' ') {
				if (i % 2 == 0) {
					int newPos = (puzzle.charAt(i) - (i + 1));
					if (newPos < 65) {
						int x = 64 - newPos;
						newPos = 90 - x;
					}
					char newChar = (char) newPos;
					result.append(newChar);
				}
				else {
					int newPos = (puzzle.charAt(i) + (i + 1));
					if (newPos > 90) {
						int x = newPos - 91;
						newPos = 65 + x;
					}
					char newChar = (char) newPos;
					result.append(newChar);
				}
			}
			else {
				result.append(' ');
			}
		}
		return result;
	}
	
	/**
	 * Appends "CS-230" and prepends the number of letters to the front of the string
	 * @return StringBuilder of the re-altered text
	 * @throws IOException if the website link is wrong
	 * @throws InterruptedException if there is an interruption when attempting to go to link
	 */
	public static StringBuilder appendToPuzzle() throws IOException, InterruptedException {
		StringBuilder s = MessageOfDay.solvePuzzle();
		s.append("CS-230");
		s.insert(0, s.length());
		return s;
	}
	
	/**
	 * Checks if the string is the correct solution by querying it with the website
	 * "http://cswebcat.swansea.ac.uk/message" by appending "?solution=" and the 
	 * altered text to the link
	 * @return the message of the day, or INCORRECT SOLUTION if solution is wrong
	 * @throws IOException if the website entered is wrong
	 * @throws InterruptedException if there is an interruption when attempting a connection
	 */
	public static String checkIfSolution() throws IOException, InterruptedException {
		StringBuilder s = MessageOfDay.appendToPuzzle();
		String ns = s.toString();
		String link = "http://cswebcat.swansea.ac.uk/message?solution=";
		String result = link.concat(ns);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(result))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
}
