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
 * 		Console, 1.health,2.level
 * 
 * stats()
 * 
 * ###MAKE SURE NO DUPLICATE NAMES BEING MADE###
 */

public class Game {

	private ArrayList<Player> currEncounterList = new ArrayList<Player>();
	private Player[] order;
	Scanner sc = new Scanner(System.in);

	public boolean add(String name, String type) {

		boolean check = false;

		// Checks the type giving to the types allowed
		for (PlayerType typeTemp : PlayerType.values()) {
			if (type.equals(typeTemp.toString())) {
				check = true;
				break;
			}
		}

		for (Player checkName : currEncounterList) {
			if (checkName.getName().equals(name)) {
				System.out.println("Cannot have the same name with somebody in the encounter");
				return false;
			}
		}

		// If type was not found, then user input's type is invalid
		if (check == false) {
			System.out.println("Please enter a correct type");
			return false;
		}

		Player temp = new Player(name, PlayerType.valueOf(type));
		currEncounterList.add(temp);

		return true;

	}

	public boolean add2(String name, String type, int health, int level) {

		boolean check = false;

		// Checks the type giving to the types allowed
		for (PlayerType typeTemp : PlayerType.values()) {
			if (type.equals(typeTemp.toString())) {
				check = true;
				break;
			}
		}

		for (Player checkName : currEncounterList) {
			if (checkName.getName().equals(name)) {
				System.out.println("Cannot have the same name with somebody in the encounter");
				return false;
			}
		}

		// If type was not found, then user input's type is invalid
		if (check == false) {
			System.out.println("Please enter a correct type");
			return false;
		}

		Player temp = new Player(name, PlayerType.valueOf(type), health, level);
		currEncounterList.add(temp);

		return true;

	}

	public void remove(Player del) {

		if (currEncounterList != null && currEncounterList.contains(del)) {
			currEncounterList.remove(del);
		} else {
			System.out.println("Error in removing player");
		}

	}

	public void edit(String name) {
		for (Player checkName : currEncounterList) {
			if (checkName.getName().equals(name)) {
				System.out.println("Please enter the number on what you want to edit with " + name + "\n"
						+ "1. Name\n2. Level+MaxHealth\nPress 3 or enter to quit");

				
				while (sc.hasNextInt()) {
					int choice = sc.nextInt();
					/** Need to add checks **/
					if (choice == 1) { // Change health
						System.out.println("Please enter the new name for the character to be");
						String changeName = sc.nextLine();
						checkName.setName(changeName);

						System.out.println("Change in name is successful");

					} else if (choice == 2) { // Change Level
						System.out.println("Please enter the new level for the character to be");
						int newLevel = sc.nextInt();

						System.out.println("Please enter the new max health for the character to be");
						int newMaxHealth = sc.nextInt();

						checkName.setMaxHealth(newMaxHealth);
						checkName.setLevel(newLevel);

						System.out.println("Change in level is successful");

					}else if (choice == 3) {
						break;
					}else {
						System.out.println("Enter a currect number choice.");
					}
				}
			}
		}
	}

	public boolean attack(Player attacked, int amount) {
		for (Player checkName : currEncounterList) {
			if (checkName == attacked) {
				int currHealth = attacked.getHealth();
				if (currHealth > 0) {
					attacked.setHealth(currHealth - amount);
					return true;
				} else {
					System.out.println("Player is already dead");
				}

			}
		}
		return false;
	}

	// Heal can be done by potion?
	public boolean heal(Player healed, int amount) {
		if (healed.getHealth() < 0) {
			System.out.println("Character is dead");
			return false;
		}
		if (healed.getMaxHealth() < healed.getHealth() + amount) {
			healed.setHealth(healed.getMaxHealth());
		} else {
			healed.setHealth(healed.getHealth() + amount);
		}

		return true;
	}

	/**
	 * Sort through encounter list to see if there are no more monsters left or no
	 * more humans left Delete all of the players with < 0 health Recreate
	 * encounterlist add new players + monsters
	 */
	public void next() {
		boolean hCheck = false;
		boolean mCheck = false;

	}

	public ArrayList<Player> setup(ArrayList<Player> elist) {
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		String tempname;
		int tempint;

		System.out.println("Enter each number which was rolled for each of these characters."
				+ " The order will be automatically redone.");

		// For each index of the encounter list, add their initiative and then reorder
		// the encounterlist
		for (int i = 0; i < elist.size(); i++) {
			tempname = elist.get(i).getName();
			System.out.println("Dice Number? for " + tempname + " the " + elist.get(i).getType());
			tempint = sc.nextInt();
			temp.put(tempname, tempint);
		}

		return sortEncounter(temp);

	}

	/*
	 * TODO Sort the list from the initiatives
	 * https://stackoverflow.com/questions/8119366/sorting-hashmap-by-values
	 */

	public ArrayList<Player> sortEncounter(HashMap<String, Integer> unsorted) {
		ArrayList<Player> sorted = new ArrayList<Player>();

		for (Entry<String, Integer> pair : unsorted.entrySet()) {

		}

		return sorted;

	}

	public void startGame() {
	}

}
