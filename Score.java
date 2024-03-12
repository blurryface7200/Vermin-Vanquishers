/**
 * 
 * @author Tudor
 *
 */
public class Score {
	private int finalScore;
	private int updatingScore;
	
	/**
	 * Creates a score object
	 * @param score
	 */
	public Score(int score){
		updatingScore = score;
	}
	
	/**
	 * Sets the score 
	 * @param score
	 */
	public void setScore(int score){		
		updatingScore = score;
	}
	
	/**
	 * Gets the score
	 * @return score
	 */
	public int getScore(){		
		return updatingScore;
	}
	
	/**
	 * Gets the final score
	 * @return finalScore
	 */
	public int getFinalScore() {
		finalScore = updatingScore + 100;
		return finalScore;
	}
	
	/**
	 * Increases the score
	 * @return updatingScore
	 */
	public int increaseScore(){
		updatingScore += 10;
		return updatingScore;
	}

}
