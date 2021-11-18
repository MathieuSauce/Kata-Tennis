package fr.fulstac.tennis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * A few junit test cases that were used to test the program during its developement.
 */

class TennisTest {

	private Match match = new Match("Mathieu", "Sauce");
	@Test
	@DisplayName("Player Names should be properly displayed")
	void testNames() {
		assertEquals(match.getMatchString().split("\n")[0].trim(), "Player 1 : Mathieu");
		assertEquals(match.getMatchString().split("\n")[1].trim(), "Player 2 : Sauce");
	}
	
	@Test
	@DisplayName("Score should be properly displayed")
	void testScores() {
		assertEquals(match.getMatchString().split("\n")[2].trim(), "Score : (0-0)");
		
		for(int i  = 0 ; i < 24; i++) {
			match.scores(1);
		}
		assertEquals(match.getMatchString().split("\n")[2].trim(), "Score : (6-0) (0-0)");
	}
	
	@Test
	@DisplayName("Sets should be able to go above 6 games won if opponent has won more than 5 games")
	void testEmptyGameStatus() {
		
		for(int i = 0; i < 20; i++) {
			match.scores(1);
		}
		
		for(int i = 0; i < 20; i++) {
			match.scores(2);
		}
		assertEquals(match.getMatchString().split("\n")[2].trim(),"Score : (5-5)");
		for(int i = 0; i < 4; i++) {
			match.scores(1);
		}
		assertEquals(match.getMatchString().split("\n")[2].trim(),"Score : (6-5)");
		
		for(int i = 0; i < 4; i++) {
			match.scores(1);
		}
		assertEquals(match.getMatchString().split("\n")[2].trim(),"Score : (7-5) (0-0)");
	}
	
	@Test
	@DisplayName("Game Status should properly display")
	void testGameStatusDisplay() {
		assertEquals(match.getMatchString().split("\n")[3].trim(),"Current game status : 0-0");
		for(int i = 0; i < 3; i++) {
			match.scores(1);
			match.scores(2);
		}
		assertEquals(match.getMatchString().split("\n")[3].trim(),"Current game status : Deuce");
		match.scores(1);
		assertEquals(match.getMatchString().split("\n")[3].trim(),"Current game status : Advantage");
		match.scores(2);
		assertEquals(match.getMatchString().split("\n")[3].trim(),"Current game status : Deuce");
	}
	
	@Test
	@DisplayName("Should be able to instanciate GUI")
	void testGUI() {
		assertNotNull(new TennisGUI());
	}
}
