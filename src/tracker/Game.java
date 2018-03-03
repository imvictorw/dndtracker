package tracker;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	public boolean setup(ArrayList<Player> elist) {
		HashMap<String, Integer> temp = new HashMap(elist.size());
		System.out.println("Enter each number which was rolled for each of these characters."
				+ " The order will be automatically redone");
		
		//Need to sort this hashmap?
		for(int i = 0; i < elist.size(); i++) {
			System.out.println("Dice Number? for "+ elist.get(i).getName() + " the " + elist.get(i).getType());
			int tempint = sc.nextInt();
			temp.put(elist.get(i).getName(), tempint);
		}
		
	}

	public void startGame() {
	}

}
