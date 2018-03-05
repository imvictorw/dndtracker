package tracker;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import classes.*;

/* Commands
 * Attack(name,health)
 * Heal(name, health)
 * help()
 * next() //next encounter
 * 
 * Add(type, name, health, level)
 * Remove(type, name)
 * Edit(type, name, stat to edit)
 * 
 * stats()
 * 
 * ###MAKE SURE NO DUPLICATE NAMES BEING MADE###
 */

public class Game{
	
	private ArrayList<Player> currEncounterList = new ArrayList<Player>();
	private Player[] order;
	Scanner sc = new Scanner(System.in);
	
	public boolean add(String name, String type) {
		
		boolean check = false;
		
		//Checks the type giving to the types allowed
		for(PlayerType typeTemp:  PlayerType.values()) {
			if(type.equals(typeTemp.toString())) {
				check = true;
				break;
			}
		}
		//If type was not found, then user input's type is invalid
		if(check == false) {
			System.out.println("Please enter a correct type");
			return false;
		}
		
		Player temp = new Player(name, PlayerType.valueOf(type));
		currEncounterList.add(temp);
		
		return true;
		
	}
	
	public boolean add2(String name, String type, int health, int level) {
		
		boolean check = false;
		
		//Checks the type giving to the types allowed
		for(PlayerType typeTemp:  PlayerType.values()) {
			if(type.equals(typeTemp.toString())) {
				check = true;
				break;
			}
		}
		//If type was not found, then user input's type is invalid
		if(check == false) {
			System.out.println("Please enter a correct type");
			return false;
		}
		
		Player temp = new Player(name, PlayerType.valueOf(type), health, level);
		currEncounterList.add(temp);
		
		return true;
		
	}
	
	public boolean heal(Player healer, Player healed, int amount) {
		if(healed.getHealth() < 0) {
			System.out.println("Character is dead");
			return false;
		}
		
		if(healer.getType() == PlayerType.CLERIC) {
			if(healed.getMaxHealth() < healed.getHealth() + amount) {
				healed.setHealth(healed.getMaxHealth());
			}else {
				healed.setHealth(healed.getHealth() + amount);	
			}	
			return true;
		}
		
		return false;
	}

	public void remove(Player del) {
		
		if(currEncounterList != null && currEncounterList.contains(del)) {
			currEncounterList.remove(del);
		}else {
			System.out.println("Error in removing player");
		}
		

	}
	
	
	/**
	 * Sort through encounter list to see if there are no more monsters left or no more humans left
	 * Delete all of the players with < 0 health
	 * Recreate encounterlist
	 * add new players + monsters
	 */
	public void next() {
		
	}
	public ArrayList<Player> setup(ArrayList<Player> elist) {
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		String tempname;
		int tempint;
		
		System.out.println("Enter each number which was rolled for each of these characters."
				+ " The order will be automatically redone.");
		
		//For each index of the encounter list, add their initiative and then reorder the encounterlist 
		for(int i = 0; i < elist.size(); i++) {
			tempname = elist.get(i).getName();
			System.out.println("Dice Number? for "+ tempname  + " the " + elist.get(i).getType());
			tempint = sc.nextInt();
			temp.put(tempname, tempint);
		}
		
		return sortEncounter(temp);
		
	}
	
	/*TODO
	 * Sort the list from the initiatives
	 * https://stackoverflow.com/questions/8119366/sorting-hashmap-by-values
	 */
	
	public ArrayList<Player> sortEncounter(HashMap<String, Integer> unsorted){
		ArrayList<Player> sorted = new ArrayList<Player>();
		
		for(Entry<String, Integer> pair : unsorted.entrySet()) {
			
		}
		
		return sorted;
		
	}

	public void startGame() {
	}

}
