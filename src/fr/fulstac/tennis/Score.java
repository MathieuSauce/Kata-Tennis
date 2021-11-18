package fr.fulstac.tennis;

import java.util.ArrayList;

/*
 * This class is used to keep track of the score of completed sets in a List of sets.
 * toString is used to generate the score string that will be displayed on screen. Here we use a stringBuilder because it's better suited for loops than the usual concatenation
 */

public class Score {
	private ArrayList<Set> setList;
	
	public Score() {
		setList = new ArrayList<Set>();
	}
	
	public void addSet(Set set) {
		this.setList.add(set);
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		setList.forEach((set) -> sb.append(set.toString()));
		return sb.toString();
	}
}
