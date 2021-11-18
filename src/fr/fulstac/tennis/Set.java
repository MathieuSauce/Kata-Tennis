package fr.fulstac.tennis;

import java.util.HashMap;

/*
 * This class handles Sets. We use an hashmap to save the amount of games won by each player during the set
 * 
 * increasePlayerGames adds a game to the player passed as an argument
 * 
 * isDone checks if any of the players meets the following condition :
 * 		- Player has won at least 6 games
 * 		- Player has won at least 2 more game than their opponent
 * 
 * toString method is used to generate the string that will be displayed on screen
 */

public class Set {
	
	private HashMap<Integer, Integer> playerGames;
	
	public Set() {
		playerGames = new HashMap<Integer, Integer>();
		playerGames.put(1, 0);
		playerGames.put(2, 0);
	}
	
	public void increasePlayerGames(int player) {
		playerGames.put(player, playerGames.get(player) + 1);
	}
	
	public boolean isDone() {
		if( (playerGames.get(1) >= 6 && playerGames.get(1) - playerGames.get(2) >= 2) || (playerGames.get(2) >= 6 && playerGames.get(2) - playerGames.get(1) >= 2) ) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "(" + playerGames.get(1) + "-" + playerGames.get(2) + ") ";
	}
}
