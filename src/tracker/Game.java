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
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;

import org.json.JSONException;
import org.json.JSONObject;

import classes.*;
import java.util.Scanner;
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
	private String command;
	public String curString;
	public igui guiObj;
	public Scanner sc;
	HashMap<String, Integer> hashmap = new HashMap<String, Integer>();

	// used to determine what question to ask and to wait for a response in the
	// scanInput() method
	String lastCommand;
	int count = 0;
	boolean isCommand = true;
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
				update("Cannot have the same name with somebody in the encounter.\nIf same type of monster, add a 1. Eg. Zombie, Zombie1");
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
				update(attack + " was hit with " + amount + " damage");
				return true;
			} else {
				update("Monster is already dead");
			}
		} else if (attacked.checkClass() == 2) {
			if (currHealth > -10) {
				attacked.setHealth(currHealth - amount);
				update(attack + " was hit with " + amount + " damage");
				return true;
			} else {
				update("Player is already dead");
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
				update(heal + " healed " + amount);
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
				update(heal + " healed " + amount);
			}

			return true;
		}

		update("Error - Heal");
		return false;
	}

	public void help() {
		update("SHIT"); // lol
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
		update(arraySize);

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
	}

	public void alive() {

		Player character;
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
	public void next() {

		int ec = encounterCheck();

		// Removes all characters with less than 1 health
		if (ec == 1 || ec == 2) {

			ArrayList<Integer> toDelete = new ArrayList<>();
			// Add it to encounterArray to print out

			ArrayList<Player> tempArray = new ArrayList<Player>();
			// When removed on the currEncounterList, the remove() function removes the
			// memory area of that space
			// So you need to make a duplicate of the array so when it gets deleted the
			// memory area isnt deleted
			for (int i = 0; i < currEncounterList.size(); i++) {
				tempArray.add(currEncounterList.get(i));
			}

			encounterArray.add(tempArray);
			// Gets the index of what to delete
			for (int k = 0; k < currEncounterList.size(); k++) {
				Player character = currEncounterList.get(k);
				if (character.checkClass() == 1) { // Monster
					if (character.getHealth() <= 0) {
						// remove(character.getName());
						toDelete.add(k);
					}
				} else if (character.checkClass() == 2) { // Player
					if (character.getHealth() <= -10) {
						toDelete.add(k);
						// remove(character.getName());
					}
				}
			}

			int delsize = toDelete.size() - 1;
			// Delete out of the encounterArray
			for (int l = delsize; l >= 0; l--) {
				Player temp = currEncounterList.get(toDelete.get(l));
				remove(temp.getName());
			}

		} else {
			update("There are still monsters or players alive in the encounter");
			return;
		}

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
		}; // Formula to compare the two different set values for intiatives

		ArrayList<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(unsorted.entrySet());
		Collections.sort(listOfEntries, valueComparator); // sorted

		for (Entry<String, Integer> pair : listOfEntries) {
			for (int j = 0; j < currEncounterList.size(); j++) {
				// update(pair.getKey()+" "+currEncounterList.get(j).getName());
				if (currEncounterList.get(j).getName().equals(pair.getKey())) {
					sorted.add(currEncounterList.get(j));
				}
			}
		}

		currEncounterList = sorted;

	}

	// copy and pasted from above, with changes to make it compatible with the GUI
	// removed scanner and replaced with different input acceptance
	public void startGameGui() {
		update("Welcome to Dungeons And Dragons 5th Edition Battle Tracker\nCurrently it tracks health throughout every encounter");
		update("Begin adding monsters and players to the encounter to start the journey!\nLook at the help box on the right to get started!");
		update("******************************************************************************");
		update("When you are complete with adding all the players and monsters, use the setup command to get started");
		if (guiObj != null) {
			guiObj.updateLogText();
		}

	}

	public void scanInput(String inputString) {

		String temp;
		String temp2;
		String tempCommand;
		int temp3;
		int temp4;
		boolean checking = true;
		boolean masterLoop = true;

		// added players for testing
		// addPlayer("DOn", "BARD");
		// addPlayer("Lez", "BARD");
		// addMonster("Len", "UNDEAD", 10, 20);

		if (inputString != null) {

			if (isCommand) {

				String command1 = inputString;
				lastCommand = command1;
				switch (command1.toLowerCase()) {

				case "add player":

					update("Enter name for player");
					if (guiObj != null) {
						guiObj.updateLogText();
					}
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
					update("Enter name for player or monster to edit");
					isCommand = false;
					break;

				case "remove":
					update("Enter name for player or monster to remove");
					isCommand = false;
					break;

				case "attack":
					update("Enter name for player or monster to attack");
					isCommand = false;
					break;

				case "heal":
					update("Enter name for player or monster to heal");
					isCommand = false;
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
					update("Roll die for each character in the encounter.");
					if (currEncounterList.size() > 1) {
						if (currEncounterList.get(0).checkClass() == 1) {
							update("Dice Number? for " + currEncounterList.get(0).getName() + " the "
									+ currEncounterList.get(0).getMtype());
						} else {
							update("Dice Number? for " + currEncounterList.get(0).getName() + " the "
									+ currEncounterList.get(0).getType());
						}
						count = 1;
						isCommand = false;
					} else {
						update("Add at least two characters before setup.");
					}
					break;

				default:
					update("Please enter a valid command");
					break;
				}

			} else {
				switch (lastCommand.toLowerCase()) {

				case "add player":

					if (count < 1) {

						inputTemp = inputString;
						update("Enter player type");
						if (guiObj != null) {
							guiObj.updateLogText();
						}
						count++;
					}

					else {

						// check input for correct type
						if (addPlayer(inputTemp, inputString)) {

							addPlayer(inputTemp, inputString);
							update("Player " + inputTemp + " has been added.");
							if (guiObj != null) {
								guiObj.updateLogText();
							}
							resetVariables();
						} else {
							count = 1;
							isCommand = false;
						}
					}
					break;

				case "add player2":

					switch (count) {

					case 0:

						inputTemp = inputString.toString(); // name
						update("Enter player type");
						count++;
						break;

					case 1:

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
							update("Please enter a correct type\n" + "	BARBARIAN,\n" + "	BARD,\n" + "	CLERIC,\n"
									+ "	DRUID,\n" + "	FIGHTER,\n" + "	MONK,\n" + "	PALADIN,\n" + "	RANGER,\n"
									+ "	ROGUE,\n" + "	SORCERER,\n" + "	WARLOCK,\n" + "	WIZARD");
							count = 1;
						}
						break;

					case 2:
						try {
							intTemp = Integer.parseInt(inputString); // health
						} catch (NumberFormatException p) {
							update("Please enter a number");
						}

						if (intTemp != -1) {
							update("Enter player level");
							count++;
						}
						break;

					case 3:
						try {
							intTemp1 = Integer.parseInt(inputString); // level
						} catch (NumberFormatException p) {
							update("Please enter a number");
						}

						if (addPlayer2(inputTemp, inputTemp1, intTemp, intTemp1)) {
							addPlayer2(inputTemp, inputTemp1, intTemp, intTemp1);
							resetVariables();
						}

						break;
					}
					break;

				case "add monster":

					switch (count) {

					case 0:
						inputTemp = inputString.toString(); // name
						update("Enter monster type");
						count++;
						break;

					case 1:

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
							update("Please enter a correct type\n 	ABERRATION,\n" + "	BEAST,\n" + "	CELESTIAL,\n"
									+ "	CONSTRUCT,\n" + "	DEMON,\n" + "	DRAGON,\n" + "	ELEMENTAL,\n" + "	FEY,\n"
									+ "	FIEND,\n" + "	GIANT,\n" + "	HUMANOID,\n" + "	MONSTROSITY,\n" + "	OOZE,\n"
									+ "	PLANT,\n" + "	UNDEAD");
						}
						break;

					case 2:
						try {
							intTemp = Integer.parseInt(inputString); // health
						} catch (NumberFormatException p) {
							update("Please enter a number");
						}

						if (intTemp != -1) {
							update("Enter monster level");
							count++;
						}
						break;

					case 3:
						try {
							intTemp1 = Integer.parseInt(inputString); // level
						} catch (NumberFormatException p) {
							update("Please enter a number");
						}

						if (addMonster(inputTemp, inputTemp1, intTemp, intTemp1)) {
							addMonster(inputTemp, inputTemp1, intTemp, intTemp1);
							resetVariables();
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
										update("Please enter a valid number.");
									}
									if (intTemp != -1) {
										p.setLevel(intTemp);
										update("Level changed successfully!");
									}
									break;
								}
							}
							resetVariables();
							break;

						case 3:
							for (Player p : currEncounterList) {
								if (p.getName().equalsIgnoreCase(inputTemp)) {
									try {
										intTemp = Integer.parseInt(inputString);
									} catch (NumberFormatException e) {
										update("Please enter a valid number.");
									}
									if (intTemp != -1) {
										p.setHealth(intTemp);
										update("Health changed successfully!");
									}
									break;
								}
							}
							resetVariables();
							break;

						default:
							break;
						}
					}
					break;

				case "remove":

					boolean found1 = false;
					if (currEncounterList.size() > 0) {
						for (Player p : currEncounterList) {
							if (p.getName().equalsIgnoreCase(inputString)) {
								found1 = true;
								break;
							}
						}
						if (found1) {
							remove(inputString);
						}
					}
					break;

				case "attack":

					if (count < 1) {
						inputTemp = inputString;
						update("Enter the amount of damage done");
						count++;
					} else {
						try {
							intTemp = Integer.parseInt(inputString);
						} catch (NumberFormatException e) {
							update("Please enter a valid number.");
						}

						if (intTemp != -1) {
							attack(inputTemp, intTemp);
							resetVariables();
						}
					}
					break;

				case "heal":

					if (count < 1) {
						inputTemp = inputString;
						update("Enter the amount of healing done.");
						count++;
					} else {
						try {
							intTemp = Integer.parseInt(inputString);
						} catch (NumberFormatException e) {
							update("Please enter a valid number.");
						}

						if (intTemp != -1) {
							heal(inputTemp, intTemp);
							resetVariables();
						}
					}

					break;

				case "next":
					next();
					break;

				case "setup":
					HashMap<String, Integer> tempMap = new HashMap<String, Integer>();

					while (count <= currEncounterList.size() - 1) {
						try {
							intTemp = Integer.parseInt(inputString);
						} catch (NumberFormatException e) {
							update("Enter a number.");
							resetVariables();
							break;
						}

						if (intTemp != -1) {

							tempMap.put(currEncounterList.get(count - 1).getName(), intTemp);
							
							if (count < currEncounterList.size() - 1) {
								if (currEncounterList.get(count).checkClass() == 1) {
									update("Dice Number? for " + currEncounterList.get(count).getName() + " the "
											+ currEncounterList.get(count).getMtype());
								} else {
									update("Dice Number? for " + currEncounterList.get(count).getName() + " the "
											+ currEncounterList.get(count).getType());
								}
							}
							else {
								break;
							}
						}

					}
					sortEncounter(tempMap);
					resetVariables();
					break;

				case "stats":
					stats();
					break;

				case "help":
					help();
					// should bring up help window
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

	public void resetVariables() {
		inputTemp = "";
		inputTemp1 = "";
		inputTemp2 = "";
		inputTemp3 = "";
		intTemp = -1;
		intTemp = -1; // -1 is considered null for this project.
		isCommand = true;
		count = 0;
	}

	public void setLogString(ArrayList<String> stringlist) {
		logString = stringlist;
	}

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
