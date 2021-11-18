package fr.fulstac.tennis;

import java.util.HashMap;

/*
 * Class that handles games
 * We use two hashmaps in order to store both the scores of each player and if one of them has the advantage.
 * deuce and done booleans are use to determine if the game is done or if the status is deuce.
 * 
 * toString generates the string that will be displayed. 
 * 
 * scores method is used to handle the player passed as an argument scoring. It follows these rules :
 * 		- The player's score follows this sequence : 0,15,30,40.
 * 		- If both player are at 40, it's deuce.
 * 		- If a player scores while deuce, he gets the advantage.
 * 		- If a player has the advantage and scores, or if he's the only one at 40, he will win this game.
 * 
 */

public class Game {

	
	private HashMap<Integer, Integer> scores;
	private HashMap<Integer, Boolean> advantages;
	private boolean deuce;
	private boolean done;
	
	public Game() {
		scores = new HashMap<Integer, Integer>();
		scores.put(1, 0);
		scores.put(2, 0);
		deuce = done = false;
		advantages = new HashMap<Integer, Boolean>();
		advantages.put(1, false);
		advantages.put(2, false);
	}
	
	public String toString() {
		if(done) {
			System.err.println("Trying to acces done game status");
			return "Done";
		}
		
		if(deuce) {
			return "Deuce";
		}
		if(advantages.values().contains(true)) {
			return "Advantage";
		}
		return scores.get(1) + "-" + scores.get(2);
	}
	
	
	public void scores(int playerNumber) {
		int score = scores.get(playerNumber);
		if(score < 30) {
			scores.put(playerNumber, score + 15);
			return;
		}
		
		if(score == 30) {
			if(scores.values().contains(40)) {
				deuce = true;
			}
			scores.put(playerNumber, 40);
			return;
		}
		
		if(deuce) {
			advantages.put(playerNumber, true);
			deuce = false;
			return ;
		}
		int otherPlayerNumber = playerNumber == 1 ? 2 : 1; 
		if(score == 40) {
			
			if(advantages.get(playerNumber)) {
				done = true;
				return;
			}
			
			if(advantages.get(otherPlayerNumber)) {
				advantages.put(otherPlayerNumber, false);
				deuce = true;
			}
			
			if(scores.get(otherPlayerNumber) < 40) {
				done = true;
			}
		}
	}
	
	public boolean isDone() {
		return done;
	}
	
}
