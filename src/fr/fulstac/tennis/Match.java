package fr.fulstac.tennis;
/*
 * This class handles a Match. 
 * It calls methods from the other classes in order to generate whatever is needed for the game to work
 * 
 * getMatchString() generates the full string for the match, calling the set, games, score and player methods to do so
 * 
 * scores() handles a player scoring and a game or a set ending. It also pushes completed sets to the score class
 */
public class Match {
	private Player player1;
	private Player player2;
	private Score score;
	private Game currentGame;
	private Set currentSet;
	
	
	public Match(String player1Name, String player2Name) {
		this.player1 = new Player(player1Name);
		this.player2 = new Player(player2Name);
		this.score = new Score();
		this.currentGame = new Game();
		this.currentSet = new Set();
	}
	
	public String getMatchString() {
		String matchString = "";
		
		matchString += "Player 1 : " + player1.getName() + "\n";
		matchString += "Player 2 : " + player2.getName() + "\n";
		matchString += "Score : " + score + currentSet + "\n" ;
		matchString += "Current game status : " + currentGame;
		
		return matchString;
	}
	
	
	public void scores(int player) {
		
		currentGame.scores(player);
		if(currentGame.isDone()) {
			currentGame = new Game();
			currentSet.increasePlayerGames(player);
			if(currentSet.isDone()) {
				score.addSet(currentSet);
				currentSet = new Set();
			}
		}
	}
	
}
