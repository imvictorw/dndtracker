package tracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
 * ###TODO - Monsters??? What to do - ####
 * ### WIll break if player name is called Zombie, and the monsters will be called zombie?##
 * ###Maybe add a multiple of the same monster add function??###
 */

public class Game {

	private ArrayList<ArrayList<Player>> encounterArray = new ArrayList<>(); // To save every encounter
	private ArrayList<Player> currEncounterList = new ArrayList<Player>(); // For every current encounter
	Scanner sc = new Scanner(System.in);

	public boolean addPlayer(String name, String type) {

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
			System.out.println("Please enter a correct type\n" + "	BARBARIAN,\n" + "	BARD,\n" + "	CLERIC,\n"
					+ "	DRUID,\n" + "	FIGHTER,\n" + "	MONK,\n" + "	PALADIN,\n" + "	RANGER,\n" + "	ROGUE,\n"
					+ "	SORCERER,\n" + "	WARLOCK,\n" + "	WIZARD");
		}

		Player temp = new Player(name, PlayerType.valueOf(type));
		currEncounterList.add(temp);

		return true;

	}

	public boolean addPlayer2(String name, String type, int health, int level) {

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
			System.out.println("Please enter a correct type\n" + "	BARBARIAN,\n" + "	BARD,\n" + "	CLERIC,\n"
					+ "	DRUID,\n" + "	FIGHTER,\n" + "	MONK,\n" + "	PALADIN,\n" + "	RANGER,\n" + "	ROGUE,\n"
					+ "	SORCERER,\n" + "	WARLOCK,\n" + "	WIZARD");
			return false;
		}

		Player temp = new Player(name, PlayerType.valueOf(type), health, level);
		currEncounterList.add(temp);

		return true;

	}

	public boolean addMonster(String name, String type, int health, int level) {
		boolean check = false;

		// Checks the type giving to the types allowed
		for (MonsterType typeTemp : MonsterType.values()) {
			if (type.equals(typeTemp.toString())) {
				check = true;
				break;
			}
		}

		for (Player checkName : currEncounterList) {
			if (checkName.getName().equals(name)) {
				System.out.println("Cannot have the same name with somebody in the encounter. If same type of monster, add a 1. Eg. Zombie, Zombie1");
				return false;
			}
		}

		// If type was not found, then user input's type is invalid
		if (check == false) {
			System.out.println("Please enter a correct type\n 	ABERRATION,\n" + "	BEAST,\n" + "	CELESTIAL,\n"
					+ "	CONSTRUCT,\n" + "	DEMON,\n" + "	DRAGON,\n" + "	ELEMENTAL,\n" + "	FEY,\n" + "	FIEND,\n"
					+ "	GIANT,\n" + "	HUMANOID,\n" + "	MONSTROSITY,\n" + "	OOZE,\n" + "	PLANT,\n"
					+ "	UNDEAD");
			return false;
		}

		Player temp = new Player(name, MonsterType.valueOf(type), health, level);
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

					} else if (choice == 3) {
						break;
					} else {
						System.out.println("Enter a correct number choice");
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

	public ArrayList<Player> setup() {
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		String tempname;
		int tempint;

		System.out.println("Enter each number which was rolled for each of these characters."
				+ " The order will be automatically redone.");

		// For each index of the encounter list, add their initiative and then reorder
		// the encounterlist
		for (int i = 0; i < currEncounterList.size(); i++) {
			tempname = currEncounterList.get(i).getName();
			System.out.println("Dice Number? for " + tempname + " the " + currEncounterList.get(i).getType());
			tempint = sc.nextInt();
			temp.put(tempname, tempint);
		}

		return sortEncounter(temp);

	}

	/*
	 * TODO Sort the list from the initiatives
	 * https://stackoverflow.com/questions/8119366/sorting-hashmap-by-values
	 */
	//

	public ArrayList<Player> sortEncounter(HashMap<String, Integer> unsorted) {

		Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2) {
				int v1 = (int) obj1.getValue();
				int v2 = (int) obj2.getValue();

				if (v1 > v2) {
					return 1;
				} else if (v1 < v2) {
					return -1;
				}

				return 0;
			}
		};

		ArrayList<Player> sorted = new ArrayList<Player>();

		ArrayList<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(unsorted.entrySet());
		Collections.sort(listOfEntries, valueComparator); // sorted

		for (int i = 0; i < currEncounterList.size(); i++) {
			for (Entry<String, Integer> pair : listOfEntries) {
				if (currEncounterList.get(i).getName().equals(pair.getKey())) {
					sorted.add(currEncounterList.get(i));
				}
			}
		}

		return sorted;

	}

	public void startGame() {
	}

}
