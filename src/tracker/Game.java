/**
 * 
 * @author Victor
 * 
 *`	BUGS	
 * 
 */

package tracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;



import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
 * 		- Console, 1.health,2.level
 * 
 * stats() - Prints out the encounters
 * 
 * save()
 * load()
 * 
 *Save function+Reload - So can restart when program is turned off 
 *Get the arrays from previous encounters
 *Input stream on the file to get back the data ~~~JSON~~~
 *Cannot run setup again unless next function has been called easy boolean
 *have to run setup before encounter starts ~~~
 *
 *Check inputs ints etc
 */

public class Game {

	private ArrayList<ArrayList<Player>> encounterArray = new ArrayList<>(); // To save every encounter
	private ArrayList<Player> currEncounterList = new ArrayList<Player>(); // For every current encounter
	Scanner sc = new Scanner(System.in);
	private String command;


	/** ADDING, EDIT & REMOVE PLAYERS & MONSTERS **/

	public boolean addPlayer(String name, String type) {

		boolean check = false;
		type = type.toUpperCase();

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

		Player temp = new Player(name, PlayerType.valueOf(type));
		System.out.println("Welcome " + name + " the " + type);
		currEncounterList.add(temp);

		return true;

	}

	public boolean addPlayer2(String name, String type, int health, int level) {

		boolean check = false;
		type = type.toUpperCase();

		// Checks the type giving to the types allowed
		for (PlayerType typeTemp : PlayerType.values()) {
			if (type.toUpperCase().equals(typeTemp.toString())) {
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
		System.out.println("Welcome " + name + " the " + type);
		currEncounterList.add(temp);

		return true;

	}

	public boolean addMonster(String name, String type, int health, int level) {
		boolean check = false;
		type = type.toUpperCase();

		// Checks the type giving to the types allowed
		for (MonsterType typeTemp : MonsterType.values()) {
			if (type.equals(typeTemp.toString())) {
				check = true;
				break;
			}
		}

		for (Player checkName : currEncounterList) {
			if (checkName.getName().equals(name)) {
				System.out.println(
						"Cannot have the same name with somebody in the encounter. If same type of monster, add a 1. Eg. Zombie, Zombie1");
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
		System.out.println("Welcome " + type + " " + name);
		currEncounterList.add(temp);

		return true;
	}

	public void edit(String name) {
		for (Player checkName : currEncounterList) {
			if (checkName.getName().toLowerCase().equals(name.toLowerCase())) {
				System.out.println("Please enter the number on what you want to edit with " + name + "\n"
						+ "1. Name\n2. Level+MaxHealth\nPress 3 or enter to quit");

				while (sc.hasNextInt()) {
					int choice = sc.nextInt();
					/** Need to add checks **/
					if (choice == 1) { // Change health
						System.out.println("Please enter the new name for the character to be");
						sc.nextLine();
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

	public Player find(String name) {
		for (int i = 0; i < currEncounterList.size(); i++) {
			if (currEncounterList.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
				return currEncounterList.get(i);
			}
		}
		return null;
	}

	public void remove(String del) {

		Player delPlayer = find(del);
		if(delPlayer == null) {
			System.out.println("Invalid name");
			return;
		}

		if (currEncounterList != null && currEncounterList.contains(delPlayer)) {
			currEncounterList.remove(delPlayer);
			System.out.println("Successfully removed " + del);
		} else {
			System.out.println("Error in removing player");
		}

	}

	/** MOVES INCLUDING ATTACK & HEAL CHARACTER **/

	//Can customize wording depending on character / monster type
	public boolean attack(String attack, int amount) {

		Player attacked = find(attack);
		if(attacked == null) {
			System.out.println("Invalid name");
			return false;
		}

		int currHealth = attacked.getHealth();

		if (attacked.checkClass() == 1) {
			if (currHealth > 0) {
				attacked.setHealth(currHealth - amount);
				System.out.println(attack + " was hit with " + amount + " damage");
				return true;
			} else {
				System.out.println("Monster is already dead");
			}
		} else if (attacked.checkClass() == 2) {
			if (currHealth > -10) {
				attacked.setHealth(currHealth - amount);
				System.out.println(attack + " was hit with " + amount + " damage");
				return true;
			} else {
				System.out.println("Player is already dead");
			}
		}

		return false;
	}

	// Heal can be done by potion?
	public boolean heal(String heal, int amount) {
		// Check player exists

		Player healed = find(heal);
		
		if(healed == null) {
			System.out.println("Invalid name");
			return false;
		}
		
		if (healed.checkClass() == 2) {
			if (healed.getHealth() <= -10) {
				System.out.println("Character is dead");
				return false;
			}
			if (healed.getMaxHealth() < healed.getHealth() + amount) {
				healed.setHealth(healed.getMaxHealth());
				System.out.println(heal + " is now full health " + "(" +healed.getMaxHealth()+")");
			} else {
				healed.setHealth(healed.getHealth() + amount);
				System.out.println(heal + " healed "+ amount);
			}

			return true;
		} else if (healed.checkClass() == 1) {
			if (healed.getHealth() < 0) {
				System.out.println("Character is dead");
				return false;
			}
			if (healed.getMaxHealth() < healed.getHealth() + amount) {
				healed.setHealth(healed.getMaxHealth());
				System.out.println(heal + " is now full health " + "(" +healed.getMaxHealth()+")");
			} else {
				healed.setHealth(healed.getHealth() + amount);
				System.out.println(heal + " healed "+ amount);
			}

			return true;
		}

		System.out.println("Error - Heal");
		return false;
	}

	public void help() {
		System.out.println("SHIT");
	}

	/** Vertical - Rounds
	// Horizontal - Players
	// Separate each encounter

	// Encounter 1
	//
	// Encounter 2
	 * 
	 */

	public void stats() {
		int counter = 1;
		for(ArrayList<Player> list : encounterArray) {

			Player character;
			System.out.println("Round "+counter);
			System.out.println("Order	Name		Type		Health");
			
			for(int i = 0; i < list.size(); i++) {
				character = list.get(i);
				int j = i + 1;
				if(character.checkClass() == 1) {
					System.out.println(j+"	"+character.getName() +"		"+character.getMtype()+"		"+ character.getHealth());
				}else {
					System.out.println(j+"	"+character.getName() +"		"+character.getType()+"		"+ character.getHealth());
				}
				
			}
			counter++;
		}
	}

	public void alive() {
		
		Player character;
		System.out.println("Order	Name		Type		Health");
		
		for(int i = 0; i < currEncounterList.size(); i++) {
			character = currEncounterList.get(i);
			int j = i + 1;
			if(character.checkClass() == 1) {
				System.out.println(j+"	"+character.getName() +"		"+character.getMtype()+"		"+ character.getHealth());
			}else {
				System.out.println(j+"	"+character.getName() +"		"+character.getType()+"		"+ character.getHealth());
			}
			
		}
	}
	/**
	 * Monster Alive - 1 Player Alive - 2
	 * 
	 * Both Alive = 3 Both Dead = 0
	 * 
	 * @return
	 */
	public int encounterCheck() {

		int result = 0;
		// Checks if there are either only Monsters or only Players are left
		for (int i = 0; i < currEncounterList.size(); i++) {
			if (currEncounterList.get(i).checkClass() == 1 && currEncounterList.get(i).getHealth() > 0) { // Monster
				result++;
				break;
			}
		}

		for (int j = 0; j < currEncounterList.size(); j++) {
			if (currEncounterList.get(j).checkClass() == 2 && currEncounterList.get(j).getHealth() > -10) { // Player
				result += 2;
				break;
			}
		}

		return result;
	}

	/**
	 * Sort through encounter list to see if there are no more monsters left or no
	 * more humans left Delete all of the players with < 0 health Recreate
	 * encounterlist add new players + monsters
	 * @throws JSONException 
	 */
	
	
	public LinkedHashMap encode() throws JSONException {
		
		LinkedHashMap<String, Object> mapOrdered = new LinkedHashMap<>();
		
		int counter = 1;
		for(ArrayList<Player> list : encounterArray) {

			Player character;
			JSONObject round = new JSONObject();
			
			for(int i = 0; i < list.size(); i++) {
				character = list.get(i);
				if(character.checkClass() == 1) { //Monster
					JSONObject attr = new JSONObject();
					attr.put("Type", character.getMtype());
					attr.put("Health", character.getHealth());
					
					round.put("Attributes", attr);
					round.put("Name", character.getName());
				}else { // Human
					JSONObject attr = new JSONObject();
					attr.put("Type", character.getType());
					attr.put("Health", character.getHealth());
					
					round.put("Attributes", attr);
					round.put("Name", character.getName());
				}
				
			}
			mapOrdered.put(Integer.toString(counter), round);
			
			counter++;
		}
		System.out.println(mapOrdered);
		return mapOrdered;
	}
	/**
	 * Order Determined by index of list
	 * ROUND
	 * 	Name
	 * 	Attributes
	 * 		Type
	 * 		Health
	 * HashMap Linked List ("ROUND", "Array")
	 */
	public void save() {
		try {
			encode();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in saving");
			e.printStackTrace();
		}
	}
	public void load() {
		
	}
	public void next() {

		int ec = encounterCheck();

		// Removes all characters with less than 1 health
		if (ec == 1 || ec == 2) {
			
			ArrayList<Integer> toDelete = new ArrayList<>();
			// Add it to encounterArray to print out
			
			ArrayList<Player> tempArray = new ArrayList<Player>(); 
			//When removed on the currEncounterList, the remove() function removes the memory area of that space
			//So you need to make a duplicate of the array so when it gets deleted the memory area isnt deleted
			for(int i = 0;i < currEncounterList.size(); i++) {
				tempArray.add(currEncounterList.get(i));
			}
			
			encounterArray.add(tempArray);
			//Gets the index of what to delete
			for (int k = 0; k < currEncounterList.size(); k++) {
				Player character = currEncounterList.get(k);
				if (character.checkClass() == 1) { // Monster
					if (character.getHealth() <= 0) {
						//remove(character.getName());
						toDelete.add(k);
					}
				} else if (character.checkClass() == 2) { // Player
					if (character.getHealth() <= -10) {
						toDelete.add(k);
						//remove(character.getName());
					}
				}
			}
			
			int delsize = toDelete.size() - 1;
			//Delete out of the encounterArray
			for(int l = delsize; l >= 0; l--) {
				Player temp = currEncounterList.get(toDelete.get(l));
				remove(temp.getName());
			}
			
			
		} else {
			System.out.println("There are still monsters or players alive in the encounter");
			return;
		}

	}

	public void setup() {
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		String tempname;
		int tempint;

		System.out.println("Enter each number which was rolled for each of these characters."
				+ " The order will be automatically redone.");

		// For each index of the encounter list, add their initiative and then reorder
		// the encounterlist
		for (int i = 0; i < currEncounterList.size(); i++) {
			tempname = currEncounterList.get(i).getName();
			if(currEncounterList.get(i).checkClass() == 1) {
				System.out.println("Dice Number? for " + tempname + " the " + currEncounterList.get(i).getMtype());
			}else {
				System.out.println("Dice Number? for " + tempname + " the " + currEncounterList.get(i).getType());
			}
			
			tempint = sc.nextInt();
			temp.put(tempname, tempint);
		}

		sortEncounter(temp);

	}
	public void sortEncounter(HashMap<String, Integer> unsorted) {
		
		ArrayList<Player> sorted = new ArrayList<Player>();

		Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2) {
				int v1 = (int) obj1.getValue();
				int v2 = (int) obj2.getValue();
				if (v1 > v2) {
					return -1;
				} else if (v1 < v2) {
					return 1;
				}

				return 0;
			}
		}; // Formula to compare the two different set values for intiatives

		ArrayList<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(unsorted.entrySet());
		Collections.sort(listOfEntries, valueComparator); // sorted

			for (Entry<String, Integer> pair : listOfEntries) {
				for(int j = 0; j < currEncounterList.size();j++) {
//					System.out.println(pair.getKey()+"			"+currEncounterList.get(j).getName());
					if (currEncounterList.get(j).getName().equals(pair.getKey())) {
						sorted.add(currEncounterList.get(j));
					}
				}
		}
		

		currEncounterList = sorted;

	}

	/* Req - One Monster and One Player */
	public void startGame() {
		System.out.println(
				"Welcome to Dungeons And Dragons 5th Edition Battle Tracker\nCurrently it tracks health throughout every encounter");
		System.out.println(
				"Begin adding monsters and players to the encounter to start the journey!\nLook at the help box on the right to get started!");
		System.out.println("******************************************************************************");
		System.out.println("When you are complete with adding all the players and monsters, use the setup command to get started");

		String temp;
		String temp2;
		int temp3;
		int temp4;
		addPlayer("DOn", "BARD");
		addPlayer("Lez", "BARD");
		addMonster("a", "UNDEAD", -10, 20);
		addMonster("b", "UNDEAD", -10, 20);

		while (true) {
			command = sc.nextLine();

			switch (command.toLowerCase()) {

			case "add player":
				System.out.println("Enter name for player");
				temp = sc.nextLine();
				System.out.println("Enter player type");
				temp2 = sc.nextLine();
				addPlayer(temp, temp2);
				break;

			case "add player2":
				System.out.println("Enter name for player");
				temp = sc.nextLine();
				System.out.println("Enter player health");
				temp3 = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter player level");
				temp4 = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter player type");
				temp2 = sc.nextLine();
				addPlayer2(temp, temp2, temp3, temp4);
				break;
			case "add monster":
				System.out.println("Enter name for monster");
				temp = sc.nextLine();
				System.out.println("Enter monster health");
				temp3 = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter monster level");
				temp4 = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter monster type");
				temp2 = sc.nextLine();
				addMonster(temp, temp2, temp3, temp4);
				break;
			case "edit":
				System.out.println("Enter name for player or monster to edit");
				temp = sc.nextLine();
				edit(temp);
				break;
			case "remove":
				System.out.println("Enter name for player or monster to edit");
				temp = sc.nextLine();
				remove(temp);
				break;
			case "attack":
				System.out.println("Enter name for player or monster to attack");
				temp = sc.nextLine();
				System.out.println("Enter the amount of damage done");
				temp3 = sc.nextInt();
				attack(temp,temp3);
				break;
			case "heal":
				System.out.println("Enter name for player or monster to heal");
				temp = sc.nextLine();
				System.out.println("Enter the amount of healing done");
				temp3 = sc.nextInt();
				heal(temp,temp3);
				break;
			case "next":
				next();
				break;
			case "stats":
				stats();
				break;
			case "help":
				help();
				break;
			case "save":
				save();
				break;
			case "load":
				load();
				break;
			case "alive":
				alive();
				break;
			case "setup":
				setup();
				break;
			default:
//				System.out.println("Please enter a valid command");
				break;
			}
			

		}
	}


}
