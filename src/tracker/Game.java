/**
 * 
 * @author Victor
 * 
 *`	BUGS	
 * save doesnt show properly. 
 * 
 */

package tracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.Scanner;
//import java.io.BufferedReader;
import java.io.File;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
/*import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;*/

import org.json.JSONException;
import org.json.JSONObject;

import classes.*;
//import java.util.Scanner;
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
	public ArrayList<Player> currEncounterList = new ArrayList<Player>(); // For every current encounter
	public ArrayList<String> logString = new ArrayList<String>();
	public String curString;
	public igui guiObj;
	public Scanner sc;
	HashMap<String, Integer> hashmap = new HashMap<String, Integer>();

	// used to determine what question to ask and to wait for a response in the
	// scanInput() method
	String lastCommand;
	int count = 0;
	boolean isCommand = true;
	boolean ranSetup;
	String inputTemp;
	String inputTemp1;
	String inputTemp2;
	String inputTemp3;

	int intTemp;
	int intTemp1;

	/** ADDING, EDIT & REMOVE PLAYERS & MONSTERS **/

	// input is sanitized in GUI processes, this method assumes both inputs are
	// valid. They always should be.
	public void addPlayerGUI(String name, String type) {
		Player temp = new Player(name, PlayerType.valueOf(type));
		update("Welcome " + name + " the " + type);
		currEncounterList.add(temp);
	}

	// gui sanitizes input
	public void addPlayerGUI2(String name, String type, int health, int level) {
		Player temp = new Player(name, PlayerType.valueOf(type), health, level);
		update("Welcome " + name + " the " + type);
		currEncounterList.add(temp);
	}

	public void addMonsterGUI(String name, String type, int health, int level) {
		Player temp = new Player(name, MonsterType.valueOf(type), health, level);
		update("Welcome " + type + " " + name);
		currEncounterList.add(temp);
	}

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
			if (checkName.getName().equalsIgnoreCase(name)) {
				update("Cannot have the same name with somebody in the encounter");
				return false;
			}
		}

		// If type was not found, then user input's type is invalid
		if (check == false) {
			update("Please enter a correct type\n" + "	BARBARIAN,\n" + "	BARD,\n" + "	CLERIC,\n" + "	DRUID,\n"
					+ "	FIGHTER,\n" + "	MONK,\n" + "	PALADIN,\n" + "	RANGER,\n" + "	ROGUE,\n" + "	SORCERER,\n"
					+ "	WARLOCK,\n" + "	WIZARD");
			return false;
		}

		Player temp = new Player(name, PlayerType.valueOf(type));
		update("Welcome " + name + " the " + type);
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
				// update("Cannot have the same name with somebody in the encounter");
				return false;
			}
		}

		// If type was not found, then user input's type is invalid
		if (check == false) {
			update("Please enter a correct type\n" + "	BARBARIAN,\n" + "	BARD,\n" + "	CLERIC,\n" + "	DRUID,\n"
					+ "	FIGHTER,\n" + "	MONK,\n" + "	PALADIN,\n" + "	RANGER,\n" + "	ROGUE,\n" + "	SORCERER,\n"
					+ "	WARLOCK,\n" + "	WIZARD");
			return false;
		}

		Player temp = new Player(name, PlayerType.valueOf(type), health, level);
		update("Welcome " + name + " the " + type);
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
				update("Cannot have the same name with somebody in the encounter.\nIf same type of monster, add a \nnumber. Eg. Zombie, Zombie1");
				return false;
			}
		}

		// If type was not found, then user input's type is invalid
		if (check == false) {
			update("Please enter a correct type\n 	ABERRATION,\n" + "	BEAST,\n" + "	CELESTIAL,\n"
					+ "	CONSTRUCT,\n" + "	DEMON,\n" + "	DRAGON,\n" + "	ELEMENTAL,\n" + "	FEY,\n" + "	FIEND,\n"
					+ "	GIANT,\n" + "	HUMANOID,\n" + "	MONSTROSITY,\n" + "	OOZE,\n" + "	PLANT,\n"
					+ "	UNDEAD");
			return false;
		}

		Player temp = new Player(name, MonsterType.valueOf(type), health, level);
		update("Welcome " + type + " " + name);
		currEncounterList.add(temp);

		return true;
	}

	public void edit(String name) {
		for (Player checkName : currEncounterList) {
			if (checkName.getName().toLowerCase().equals(name.toLowerCase())) {
				update("Please enter the number on what you want to edit with " + name + "\n"
						+ "1. Name\n2. Level+MaxHealth\nPress 3 or enter to quit");

				while (sc.hasNextInt()) {
					int choice = sc.nextInt();
					/** Need to add checks **/
					if (choice == 1) { // Change health
						update("Please enter the new name for the character to be");
						sc.nextLine();
						String changeName = sc.nextLine();
						checkName.setName(changeName);

						update("Change in name is successful");

					} else if (choice == 2) { // Change Level
						update("Please enter the new level for the character to be");
						int newLevel = sc.nextInt();

						update("Please enter the new max health for the character to be");
						int newMaxHealth = sc.nextInt();

						checkName.setMaxHealth(newMaxHealth);
						checkName.setLevel(newLevel);

						update("Change in level is successful");

					} else if (choice == 3) {
						break;
					} else {
						update("Enter a correct number choice");
					}
				}
			}
		}
	}

	public void edit(String name, int num) {

		for (Player checkName : currEncounterList) {
			if (checkName.getName().toLowerCase().equals(name.toLowerCase())) {

				if (num == 1) { // Change health
					update("Please enter the new name for the character to be");
					sc.nextLine();
					String changeName = sc.nextLine();
					checkName.setName(changeName);

					update("Change in name is successful");

				} else if (num == 2) { // Change Level
					update("Please enter the new level for the character to be");
					int newLevel = sc.nextInt();

					update("Please enter the new max health for the character to be");
					int newMaxHealth = sc.nextInt();

					checkName.setMaxHealth(newMaxHealth);
					checkName.setLevel(newLevel);

					update("Change in level is successful");

				} else if (num == 3) {
					break;
				} else {
					update("Enter a correct number choice");
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
		if (delPlayer == null) {
			update("Invalid name");
			return;
		}

		if (currEncounterList != null && currEncounterList.contains(delPlayer)) {
			currEncounterList.remove(delPlayer);
			update("Successfully removed " + del);
		} else {
			update("Error in removing player");
		}

	}

	/** MOVES INCLUDING ATTACK & HEAL CHARACTER **/

	// Can customize wording depending on character / monster type
	public boolean attack(String attack, int amount) {

		Player attacked = find(attack);
		if (attacked == null) {
			update("Invalid name");
			return false;
		}

		int currHealth = attacked.getHealth();

		if (attacked.checkClass() == 1) {
			if (currHealth > 0) {
				attacked.setHealth(currHealth - amount);
				update(attack + " was hit with " + amount + " damage!");
				return true;
			} else {
				update("Monster is already dead");
			}
		} else if (attacked.checkClass() == 2) {
			if (currHealth > -10) {
				attacked.setHealth(currHealth - amount);
				update(attack + " was hit with " + amount + " damage!");
				return true;
			} else {
				update("Player is already dead.");
			}
		}

		return false;
	}

	// Heal can be done by potion?
	public boolean heal(String heal, int amount) {
		// Check player exists

		Player healed = find(heal);

		if (healed == null) {
			update("Invalid name");
			return false;
		}

		if (healed.checkClass() == 2) {
			if (healed.getHealth() <= -10) {
				update("Character is dead");
				return false;
			}
			if (healed.getMaxHealth() < healed.getHealth() + amount) {
				healed.setHealth(healed.getMaxHealth());
				update(heal + " is now full health " + "(" + healed.getMaxHealth() + ")");
			} else {
				healed.setHealth(healed.getHealth() + amount);
				update(heal + " was healed for " + amount + " hp.");
			}

			return true;
		} else if (healed.checkClass() == 1) {
			if (healed.getHealth() < 0) {
				update("Character is dead");
				return false;
			}
			if (healed.getMaxHealth() < healed.getHealth() + amount) {
				healed.setHealth(healed.getMaxHealth());
				update(heal + " is now full health " + "(" + healed.getMaxHealth() + ")");
			} else {
				healed.setHealth(healed.getHealth() + amount);
				update(heal + " was healed for " + amount + " hp.");
			}

			return true;
		}

		update("Error - Heal");
		return false;
	}

	public void help() {
		JFrame newFrame = new JFrame();
		JOptionPane.showMessageDialog(newFrame, "Prerequesite: Must add a minimum of two characters, run setup.\r\n"
				+ "\r\n" + "setup: Used to enter in inititives for each active character and monster.\r\n"
				+ "next: Used when all characters have taken turn and party is ready for next round.\r\n"
				+ "save: Save the game in its current state including character initiatives and health. Will only work if 'next' was called at least 1 time.\r\n"
				+ "load: Loads prior saved encounter data.\r\n"
				+ "add player: Used when introducing new player characters into encounter.\r\n"
				+ "add player2: When introducing a character that has a higher level than 1.\r\n"
				+ "add monster: Used to add new nonplayer enemy into encounter.\r\n"
				+ "remove: Used to remove Player or Monster character from encounter.\r\n"
				+ "edit: Used to edit Player or Monster characters current level.\r\n"
				+ "heal: Used to heal Player and Monster characters current health.\r\n"
				+ "attack: Used to inflict select number of damage on any charcater in encounter.\r\n"
				+ "alive: Used to view current Player and Nonplayer characters in the current encounter.\r\n"
				+ "stats: Shows statistics of the encounter by round.\r\n");
	}

	/**
	 * Vertical - Rounds // Horizontal - Players // Separate each encounter
	 * 
	 * // Encounter 1 // // Encounter 2
	 * 
	 */

	public void stats() {
		int counter = 1;

		String arraySize = "" + encounterArray.size();

		if (encounterArray.size() > 0) {
			// update(arraySize);
			for (ArrayList<Player> list : encounterArray) {

				Player character;
				update("Round " + counter);
				update("Order" + "\t" + "Name" + "\t" + "Type" + "\t" + "Health");

				for (int i = 0; i < list.size(); i++) {
					character = list.get(i);
					int j = i + 1;
					if (character.checkClass() == 1) {
						update(j + "	" + character.getName() + "		" + character.getMtype() + "		"
								+ character.getHealth());
					} else {
						update(j + "	" + character.getName() + "		" + character.getType() + "		"
								+ character.getHealth());
					}

				}
				counter++;
			}
		} else {
			update("The encounter is still on the first round!");
		}
	}

	public void alive() {

		Player character;
		update("\n");
		update("Order" + "\t\t" + "Name" + "\t\t" + "Type" + "\t\t" + "Health(Max)");

		for (int i = 0; i < currEncounterList.size(); i++) {
			character = currEncounterList.get(i);
			int j = i + 1;
			if (character.checkClass() == 1) {
				update(j + "\t\t\t" + character.getName() + "\t\t\t" + character.getMtype() + "\t\t"
						+ character.getHealth() + "(" + character.getMaxHealth() + ")");
			} else {
				update(j + "\t\t\t" + character.getName() + "\t\t\t" + character.getType() + "\t\t"
						+ character.getHealth() + "(" + character.getMaxHealth() + ")");
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
	 * Gets the String file JSON encoded saved Converts it back to the array form
	 * and adds them all back to the encounter array Adds the current round to the
	 * currEncounterList
	 * 
	 * @param savefile
	 */

	public void decode(String savefile) {

		boolean ptypeCheck = false;

		try {
			JSONObject jsonObject = new JSONObject(savefile);
			for (int i = 1; i < jsonObject.names().length() + 1; i++) {
				JSONObject newJSON = jsonObject.getJSONObject(Integer.toString(i));
				ArrayList<Player> loadArray = new ArrayList<>();
				for (int j = 0; j < newJSON.names().length(); j++) {
					ptypeCheck = false;
					JSONObject playerObject = newJSON.getJSONObject(Integer.toString(j));
					String name = playerObject.getString("Name");
					String type = playerObject.getJSONObject("Attributes").getString("Type");
					int health = playerObject.getJSONObject("Attributes").getInt("Health");
					int level = playerObject.getJSONObject("Attributes").getInt("Level");

					for (PlayerType typeTemp : PlayerType.values()) { // Checks if player or monster
						if (type.equals(typeTemp.toString())) {
							ptypeCheck = true;
							break;
						}
					}
					Player temp = null;
					if (ptypeCheck == true) {
						temp = new Player(name, PlayerType.valueOf(type), health, level);
					} else {
						temp = new Player(name, MonsterType.valueOf(type), health, level);
					}

					loadArray.add(temp);

				}
				encounterArray.add(loadArray);

				// Adding the current round to the current encounter list
				if (i == jsonObject.names().length()) {
					currEncounterList = loadArray;
				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String encode() throws JSONException {

		LinkedHashMap<String, Object> mapOrdered = new LinkedHashMap<>();

		int counter = 1;

		for (ArrayList<Player> list : encounterArray) {

			JSONObject round = new JSONObject();
			JSONObject characterJSON = new JSONObject();
			Player character;

			for (int i = 0; i < list.size(); i++) {
				character = list.get(i);
				if (character.checkClass() == 1) { // Monster
					JSONObject attr = new JSONObject();
					attr.put("Type", character.getMtype());
					attr.put("Health", character.getHealth());
					attr.put("Level", character.getLevel());

					round.put("Attributes", attr);
					round.put("Name", character.getName());
					characterJSON.put(Integer.toString(i), round);
				} else { // Human
					JSONObject attr = new JSONObject();
					attr.put("Type", character.getType());
					attr.put("Health", character.getHealth());
					attr.put("Level", character.getLevel());

					round.put("Attributes", attr);
					round.put("Name", character.getName());
					characterJSON.put(Integer.toString(i), round);
				}
				round = new JSONObject();
			}
			mapOrdered.put(Integer.toString(counter), characterJSON);
			counter++;
		}
		String result = mapOrdered.toString();
		update(result);
		return result;
	}

	/**
	 * Order Determined by index of list ROUND Name Attributes Type Health HashMap
	 * Linked List ("ROUND", "Array")
	 */
	public void save() {
		try {
			String result = encode();

			// Write to file
			FileWriter fout = new FileWriter("savefile.txt");
			fout.write(result);
			fout.close();
			update("Successfully saved current file to savefile.txt");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			update("Error in saving");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			update("File failed to save");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			update("I/O Error");
			e.printStackTrace();
		}
	}

	public void load() {
		try {
			// Read from file
			File file = new File("savefile.txt");
			Scanner input = new Scanner(file);

			while (input.hasNextLine()) {
				String line = input.nextLine();
				decode(line);
				update("Successfully loaded previous save file");
			}

			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Sort through encounter list to see if there are no more monsters left or no
	 * more humans left Delete all of the players with < 0 health Recreate
	 * encounterlist add new players + monsters
	 * 
	 * @throws JSONException
	 */
	/*
	 * public void next() {
	 * 
	 * int ec = encounterCheck();
	 * 
	 * // Removes all characters with less than 1 health if (ec == 1 || ec == 2) {
	 * 
	 * ArrayList<Integer> toDelete = new ArrayList<>(); // Add it to encounterArray
	 * to print out
	 * 
	 * ArrayList<Player> tempArray = new ArrayList<Player>(); // When removed on the
	 * currEncounterList, the remove() function removes the // memory area of that
	 * space // So you need to make a duplicate of the array so when it gets deleted
	 * the // memory area isnt deleted for(Player p : currEncounterList) {
	 * tempArray.add(new Player(p)); }
	 * 
	 * encounterArray.add(tempArray); // Gets the index of what to delete for (int k
	 * = 0; k < currEncounterList.size(); k++) { Player character =
	 * currEncounterList.get(k); if (character.checkClass() == 1) { // Monster if
	 * (character.getHealth() <= 0) { // remove(character.getName());
	 * toDelete.add(k); } } else if (character.checkClass() == 2) { // Player if
	 * (character.getHealth() <= -10) { toDelete.add(k); //
	 * remove(character.getName()); } } }
	 * 
	 * int delsize = toDelete.size() - 1; // Delete out of the encounterArray for
	 * (int l = delsize; l >= 0; l--) { Player temp =
	 * currEncounterList.get(toDelete.get(l)); remove(temp.getName()); }
	 * 
	 * } else {
	 * update("There are still monsters or players alive in the encounter"); return;
	 * }
	 * 
	 * }
	 */
	public void next() {
		ArrayList<Player> tempArray = new ArrayList<Player>();
		for (Player p : currEncounterList) {
			tempArray.add(new Player(p));
		}

		encounterArray.add(tempArray);
	}

	public void setup() {
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		String tempname;
		int tempint;

		// update("Enter each number which was rolled for each of these characters."
		// + " The order will be automatically redone.");

		// For each index of the encounter list, add their initiative and then reorder
		// the encounterlist
		for (int i = 0; i < currEncounterList.size(); i++) {
			tempname = currEncounterList.get(i).getName();
			if (currEncounterList.get(i).checkClass() == 1) {
				update("Dice Number? for " + tempname + " the " + currEncounterList.get(i).getMtype());
			} else {
				update("Dice Number? for " + tempname + " the " + currEncounterList.get(i).getType());
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
		}; // Formula to compare the two different set values for initiatives

		ArrayList<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(unsorted.entrySet());
		Collections.sort(listOfEntries, valueComparator); // sorted

		for (Entry<String, Integer> pair : listOfEntries) {
			for (int j = 0; j < currEncounterList.size(); j++) {
				// update(pair.getKey()+" "+currEncounterList.get(j).getName());
				if (currEncounterList.get(j).getName().equalsIgnoreCase(pair.getKey())) {
					sorted.add(currEncounterList.get(j));
				}
			}
		}

		currEncounterList = sorted;

	}

	// copy and pasted from above, with changes to make it compatible with the GUI
	// removed scanner and replaced with different input acceptance
	public void startGameGui() {
		update("*****************************************\n"
				+ "Welcome to Dungeons And Dragons 5th Edition\nBattle Tracker!\nCurrently it tracks health throughout every encounter.\n"
				+ "Begin adding monsters and players to the\nencounter to start the journey!\nLook at the help box on the right, or type\n"
				+ "help in the textbox below to get started!\n" + "*****************************************\n"
				+ "When you are complete with adding all the players\nand monsters, use the setup command to get started.");
		// added players for testing
		// addPlayer("DOn", "BARD");
		// addPlayer("Lez", "BARD");
		// addMonster("Len", "UNDEAD", 10, 20);
		// addMonster("Evil", "FEY", 10, 1);
		if (guiObj != null) {
			guiObj.updateLogText();
		}
	}

	// Directs user input to correct cases
	public void scanInput(String inputString) {

		// Checks that user input is not null
		if (inputString != null) {

			// checks if the input is pre or post command.
			if (isCommand) {

				// sets the current input command to command1 to save previous commands
				String command1 = inputString;
				lastCommand = command1;

				// checks input from list of commands, displays initial prompts then sets
				// isCommand to false if more input is needed
				switch (command1.toLowerCase()) {

				case "add player":

					update("Enter name for player");
					// ensures guiObj is not null
					if (guiObj != null) {
						guiObj.updateLogText();
					}
					// sets isCommand as false to send next scanInput
					isCommand = false;

					break;

				case "add player2":
					update("Enter name for player");
					if (guiObj != null) {
						guiObj.updateLogText();
					}
					isCommand = false;

					break;

				case "add monster":
					update("Enter name for monster");
					if (guiObj != null) {
						guiObj.updateLogText();
					}
					isCommand = false;
					break;

				case "edit":
					// edit can only be done if there are players in the encounter
					if (currEncounterList.size() > 0) {
						update("Enter name for player or monster to edit");
						isCommand = false;
					} else {
						update("Please add more characters to the encounter.");
						resetVariables();
					}
					break;

				case "remove":
					if (currEncounterList.size() > 0) {
						update("Enter name for player or monster to remove");
						isCommand = false;
					} else {
						update("Please add more characters to the encounter.");
						resetVariables();
					}
					break;

				case "attack":
					if (currEncounterList.size() > 0) {
						update("Enter name for player or monster to attack");
						isCommand = false;
					} else {
						update("Please add more characters to the encounter.");
						resetVariables();
					}
					break;

				case "heal":
					if (currEncounterList.size() > 0) {
						update("Enter name for player or monster to heal");
						isCommand = false;
					} else {
						update("Please add more characters to the encounter.");
					}
					break;

				case "next":
					if (currEncounterList.size() > 1) {
						next();
					} else {
						update("Please add at least two characters before moving\non to the next round.");
						resetVariables();
					}
					break;

				case "stats":
					stats();
					break;

				case "help":
					help();
					break;

				case "save":
					if (currEncounterList.size() > 1) {
						save();
					} else {
						update("Please add at least two characters before saving.");
						resetVariables();
					}
					break;

				case "load":
					load();
					break;

				case "alive":
					if (currEncounterList.size() > 0) {
						alive();
					} else {
						update("There are currently no players in the encounter!");
						resetVariables();
					}
					break;

				case "setup":

					// checks if setup has been ran previously
					if (!ranSetup) {
						// Tells user to roll for initiatives.
						update("Roll die for each character in the encounter.");

						// If there are 2 or more players, move on
						if (currEncounterList.size() > 1) {

							// Checks if character is a monster or not. Format: [message] [name] "the"
							// [type]
							if (currEncounterList.get(0).checkClass() == 1) {
								update("Dice Number? for " + currEncounterList.get(0).getName() + " the "
										+ currEncounterList.get(0).getMtype() + ".");
							} else {
								update("Dice Number? for " + currEncounterList.get(0).getName() + " the "
										+ currEncounterList.get(0).getType() + ".");
							}

							// increments count for lower loop
							count++;
							isCommand = false;

							// Explain error
						} else {
							update("Add at least two characters before setup.");
						}
					} else {
						update("Setup has already been completed.");
					}
					break;

				default:
					// This message indicates that the input was not one of the accepted commands
					update("Please enter a valid command");
					break;
				}

			} else {
				// Checks what the last command entered was
				switch (lastCommand.toLowerCase()) {

				case "add player":

					if (count < 1) {

						boolean isInt = false;
						int newInt = -1;
						boolean nameCheck = false;

						try {
							newInt = Integer.parseInt(inputString);
							// if no exception is thrown, inputString is an integer
							isInt = true;
						} catch (NumberFormatException e) {
							// If it throws this exception, inputString is not a number

							// sets latest requested input (name), as temporary value

							for (Player p : currEncounterList) {
								if (p.getName().equalsIgnoreCase(inputString)) {
									count = 0;
									update("Cannot have same name as someone else in the encounter.");
									isInt = false;
									nameCheck = true;
									break;
								}
							}

							if (!nameCheck) {
								inputTemp = inputString;
								// asks for next input (type)
								update("Enter player type");
								count++;
								if (guiObj != null) {
									guiObj.updateLogText();
								}
							}
						}
						if (isInt) {
							// prompts for retry if inputString is an int
							update("Character names can only be made from letters.");
							update("Enter player name.");
							count = 0;
						}
					}

					// if count is greater than 0, name was input
					else {

						// checks if method was successful, uses temporary variable from first half of
						// if statement and current input to add character.

						int newInt = -1;
						boolean isInt = false;
						try {
							// tries to set newInt as input
							newInt = Integer.parseInt(inputString);

							// isInt is only true if the above line works
							isInt = true;

						} catch (NumberFormatException e) {
							// if this exception is thrown, input is not a number
							if (addPlayer(inputTemp, inputString)) {

								// display message indicating who was added
								update("Player " + inputTemp + " has been added.");
								if (guiObj != null) {
									guiObj.updateLogText();
								}
								// Successful end of logic, reset variables
								resetVariables();
							} else {
								// if player couldn't be added, the loop is set back one step to allow user to
								// try again
								count = 1;
								isCommand = false;
							}
						}

						// if exception wasn't thrown, retry for type
						if (isInt) {
							update("Invalid input, please enter only letters.");
							count = 1;
							isCommand = false;
							update("Enter player type.");
						}

					}
					break;

				case "add player2":

					switch (count) {

					case 0:

						boolean isInt = false;
						boolean nameCheck = false;

						try {
							int newint = Integer.parseInt(inputString);
							isInt = true;
						} catch (NumberFormatException e) {
							// if exception is thrown, inputString is not a number
							// sets current input (name) to temp variable
							for (Player p : currEncounterList) {
								if (p.getName().equalsIgnoreCase(inputString)) {
									nameCheck = true;
									update("Cannot have same name as someone else in the encounter.");
									count = 0;
									isInt = false;
									break;
								}

							}
							if (!nameCheck) {
								inputTemp = inputString.toString();
								update("Enter player type.");
								count++;
							}
						}

						if (isInt) {
							update("Character names can only be made from letters.");
							update("Enter player name.");
							count = 0;
						}

						break;

					case 1:

						boolean isInt1 = false;

						try {
							int newint = Integer.parseInt(inputString);
							isInt1 = true;
						} catch (NumberFormatException e) {
							// inputString is not a number
							boolean flag = false;
							for (PlayerType typeTemp : PlayerType.values()) {
								if (inputString.toUpperCase().equals(typeTemp.toString())) {
									flag = true;
									// taking the last command and looping
								}
							}

							if (flag) {
								inputTemp1 = inputString.toString(); // type
								update("Enter player health");
								count++;
							} else {
								update("Please enter a correct type\n" + "	BARBARIAN,\n" + "	BARD,\n"
										+ "	CLERIC,\n" + "	DRUID,\n" + "	FIGHTER,\n" + "	MONK,\n" + "	PALADIN,\n"
										+ "	RANGER,\n" + "	ROGUE,\n" + "	SORCERER,\n" + "	WARLOCK,\n"
										+ "	WIZARD");
								count = 1;
							}
						}
						if (isInt1) {
							count = 1;
							update("Types can only include letters.");
							update("Enter player type.");
						}
						break;

					case 2:

						boolean isInt2 = false;

						try {
							intTemp = Integer.parseInt(inputString); // health
							isInt2 = true;
						} catch (NumberFormatException p) {
							update("Please enter only positive numbers.");
							count = 2;
						}

						if (isInt2) {
							if (intTemp > 0) {
								// successful end of case
								update("Enter player level");
								count++;
							} else {
								update("Please enter only positive numbers.");
								count = 2;
							}
						}
						break;

					case 3:
						boolean isInt3 = false;
						try {
							intTemp1 = Integer.parseInt(inputString); // level
							isInt3 = true;
						} catch (NumberFormatException p) {
							update("Please enter only positive numbers.");
							count = 3;
						}

						if (isInt3) {
							if (addPlayer2(inputTemp, inputTemp1, intTemp, intTemp1)) {
								// addPlayer2(inputTemp, inputTemp1, intTemp, intTemp1);
								resetVariables();
							}
						} else {
							count = 3;
						}

						break;
					}
					break;

				case "add monster":

					switch (count) {

					case 0:

						boolean isInt = false;
						boolean nameCheck = false;

						try {
							int newint = Integer.parseInt(inputString);
							isInt = true;
						} catch (NumberFormatException e) {
							for (Player p : currEncounterList) {
								if (p.getName().equalsIgnoreCase(inputString)) {
									nameCheck = true;
									count = 0;
									update("Cannot have same name as someone else in the encounter.");
									isInt = false;
									break;
								}
							}
						}

						if (!nameCheck) {
							inputTemp = inputString.toString(); // name
							update("Enter monster type");
							count++;
						}

						if (isInt) {
							update("Monster names can only be letters.");
							count = 0;
						}

						break;

					case 1:

						boolean isInt1 = false;
						try {
							int newint = Integer.parseInt(inputString);
							isInt1 = true;
						} catch (NumberFormatException e) {
							boolean check2 = false;
							for (MonsterType typeTemp : MonsterType.values()) {
								if (inputString.toUpperCase().equals(typeTemp.toString())) {
									check2 = true;
								}
							}
							if (check2) {
								inputTemp1 = inputString.toString(); // type
								update("Enter monster health");
								count++;
							} else {
								update("Please enter a correct type\n 	ABERRATION,\n" + "	BEAST,\n"
										+ "	CELESTIAL,\n" + "	CONSTRUCT,\n" + "	DEMON,\n" + "	DRAGON,\n"
										+ "	ELEMENTAL,\n" + "	FEY,\n" + "	FIEND,\n" + "	GIANT,\n" + "	HUMANOID,\n"
										+ "	MONSTROSITY,\n" + "	OOZE,\n" + "	PLANT,\n" + "	UNDEAD");
							}
						}
						if (isInt1) {
							update("Monster types can only be letters.");
							count = 1;
						}
						break;

					case 2:

						boolean isInt2 = false;
						try {
							intTemp = Integer.parseInt(inputString); // health
							isInt2 = true;
						} catch (NumberFormatException p) {
							update("Please enter only positive numbers.");
							count = 2;
						}

						if (isInt2 && intTemp > 0) {
							if (intTemp > 0) {
								// successful entry
								update("Enter monster level");
								count++;
							}
						} else if (intTemp <= 0) {
							update("Please enter only positive numbers.");
							count = 2;
						}
						break;

					case 3:

						boolean isInt3 = false;

						try {
							intTemp1 = Integer.parseInt(inputString); // level
							isInt3 = true;
						} catch (NumberFormatException p) {
							update("Please enter only positive numbers 1-20.");
						}

						if (isInt3 && intTemp1 > 0 && intTemp1 < 21) {
							// successful entry
							if (addMonster(inputTemp, inputTemp1, intTemp, intTemp1)) {
								// addMonster(inputTemp, inputTemp1, intTemp, intTemp1);
								resetVariables();
							}
						} else if (intTemp <= 0 || intTemp >= 21) {
							count = 3;
							update("Please enter only positive numbers 1-20.");
						}

						break;
					}

					break;

				case "edit":

					if (count == 0) {
						boolean found = false;
						if (currEncounterList.size() > 0) {
							for (Player p : currEncounterList) {
								if (p.getName().equalsIgnoreCase(inputString)) {
									found = true;
									inputTemp = inputString;
									break;
								}
							}
							if (found) {
								update("Please enter a number to edit" + "\n"
										+ "1. Name\n2. Level\n3. Health\nPress 4 to quit");
								count++;
							}
						}
					} else if (count == 1) {
						try {
							intTemp1 = Integer.parseInt(inputString);
						} catch (NumberFormatException p) {
							update("Please enter a number");
						}
						if (intTemp1 != -1) {

							switch (intTemp1) {

							case 1:
								update("Enter new name.");
								count++;
								break;

							case 2:
								update("Enter new Level.");
								count++;
								break;

							case 3:
								update("Enter new Health.");
								count++;
								break;

							case 4:
								resetVariables();
								break;

							default:
								update("Error: Bad number");

							}
						}
					} else if (count == 2) {

						switch (intTemp1) {

						case 1:
							for (Player p : currEncounterList) {
								if (p.getName().equalsIgnoreCase(inputTemp)) {
									p.setName(inputString);
									update("Name changed successfully!");
									break;
								}
							}
							resetVariables();
							break;

						case 2:
							for (Player p : currEncounterList) {
								if (p.getName().equalsIgnoreCase(inputTemp)) {
									try {
										intTemp = Integer.parseInt(inputString);
									} catch (NumberFormatException e) {
										// update("Please enter a valid number.");
									}
									if (intTemp > 0 && intTemp < 21) {
										p.setLevel(intTemp);
										update("Level changed successfully!");
										resetVariables();
									} else {
										update("Level must be a number between 1 and 20!");
									}
									break;
								}
							}
							break;

						case 3:
							for (Player p : currEncounterList) {
								if (p.getName().equalsIgnoreCase(inputTemp)) {
									try {
										intTemp = Integer.parseInt(inputString);
									} catch (NumberFormatException e) {
										update("Please enter a valid number.");
									}
									if (intTemp > 0) {
										p.setHealth(intTemp);
										update("Health changed successfully!");
										resetVariables();
									}
									break;
								}
							}
							break;

						default:
							break;
						}
					}
					break;

				case "remove":

					if (inputString.equalsIgnoreCase("exit")) {
						// player no longer wants to remove character
						resetVariables();
					} else {
						boolean found1 = false;
						if (currEncounterList.size() > 0) {
							for (Player p : currEncounterList) {
								if (p.getName().equalsIgnoreCase(inputString)) {
									found1 = true;
									break;
								}
							}
							if (found1) {
								// successful end of logic
								remove(inputString);
								resetVariables();
							} else {
								update("Player not found. Type 'exit' to leave the remove dialog.");
							}
						}
					}
					break;

				case "attack":

					if (count < 1) {

						boolean isInt = false;
						try {
							int newint = Integer.parseInt(inputString);
							isInt = true;
						} catch (NumberFormatException e) {
							// if exception is thrown, inputString is not a number

							// checks if player is in encounter
							boolean inEnc = false;
							for (Player p : currEncounterList) {
								if (p.getName().equalsIgnoreCase(inputString)) {
									// if player is found, go to next step
									inputTemp = inputString;
									update("Enter the amount of damage done.");
									count++;
									inEnc = true;
								}

							}
							if (!inEnc) {
								update("Character must be in the encounter.");
								count = 0;
							}
						}
						if (isInt) {
							count = 0;
							update("Enter a valid name.");
						}

					} else {
						try {
							intTemp = Integer.parseInt(inputString);
						} catch (NumberFormatException e) {
							// update("Please enter a valid number.");
							count = 1;
						}

						if (intTemp > 0) {
							// successful end of logic
							attack(inputTemp, intTemp);
							resetVariables();
						} else {
							update("Please enter only positive numbers.");
							count = 1;
						}
					}
					break;

				case "heal":

					if (count < 1) {

						boolean isInt = false;
						try {
							int newint = Integer.parseInt(inputString);
							isInt = true;
						} catch (NumberFormatException e) {
							// if exception is thrown, input is not an integer

							for (Player p : currEncounterList) {
								if (p.getName().equalsIgnoreCase(inputString)) {
									inputTemp = inputString;
									update("Enter the amount of healing done.");
									count++;
								} else {
									update("Player must be in the encounter.");
									count = 0;
								}
							}
						}
						if (isInt) {
							update("Enter a valid name.");
							count = 0;
						}

					} else {

						try {
							intTemp = Integer.parseInt(inputString);
						} catch (NumberFormatException e) {
							update("Please a valid number.");
							count = 1;
						}

						if (intTemp > 0) {
							// successful end of logic
							heal(inputTemp, intTemp);
							resetVariables();
						} else {
							update("Please enter only positive numbers.");
						}
					}

					break;

				case "setup":

					boolean finalLoop = false;

					try {
						intTemp = Integer.parseInt(inputString);
					} catch (NumberFormatException e) {
						update("Enter a number.");
						resetVariables();
						break;
					}

					if (intTemp > 0) {

						if (count < currEncounterList.size()) {

							hashmap.put(currEncounterList.get(count - 1).getName(), intTemp);

							if (currEncounterList.get(count).checkClass() == 1) {
								update("Dice Number? for " + currEncounterList.get(count).getName() + " the "
										+ currEncounterList.get(count).getMtype());
								count++;
							} else {
								update("Dice Number? for " + currEncounterList.get(count).getName() + " the "
										+ currEncounterList.get(count).getType());
								count++;
							}

						} else if (count == currEncounterList.size()) {
							hashmap.put(currEncounterList.get(count - 1).getName(), intTemp);
							finalLoop = true;
						}

						else {
							break;
						}

					} else {
						// do nothing
					}

					if (finalLoop) {
						sortEncounter(hashmap);
						update("Characters successfully sorted by initiative!");
						hashmap = new HashMap<String, Integer>();
						resetVariables();
						ranSetup = true;
						break;
					}
					break;

				default:
					update("Please enter a valid command");
					break;
				}
			}
			if (guiObj != null) {
				guiObj.updateLogText();
			}
		} else {
			// Do nothing if no input
		}
	}

	public ArrayList<Player> getCurrEncounterList() {
		return currEncounterList;
	}

	// This method sets curString as the given string to allow it to be passed to
	// the GUI object
	public void setCurString(String string) {
		curString = string;
	}

	// Returns curString
	public String getCurString() {
		return curString;
	}

	// sets all class level variables used in scanInput() to their default values
	public void resetVariables() {
		inputTemp = "";
		inputTemp1 = "";
		inputTemp2 = "";
		inputTemp3 = "";
		intTemp = -1;
		intTemp1 = -1; // -1 is considered null for this project.
		isCommand = true;
		count = 0;
	}

	// sets logString
	public void setLogString(ArrayList<String> stringlist) {
		logString = stringlist;
	}

	// returns logString
	public ArrayList<String> getLogString() {
		return logString;
	}

	// adds a string to the logString ArrayList and tells gui to update logtext with
	// newest version of logString
	public void update(String addString) {
		logString.add(addString);
		// update gui
	}

}
