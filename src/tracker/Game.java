package tracker;

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
	
	public boolean add2(String type, String name, int health, int level) {
		if(type.toLowerCase() != "wizard" || type.toLowerCase() != "cleric" || type.toLowerCase() != "ranger" || type.toLowerCase() != "rogue" || type.toLowerCase() != "warrior") {
			System.out.println("Enter a correct type. \n Cleric, Ranger, Rogue, Warrior, Wizard");
			return false;
		}
		
		if(health < 1) {
			//somecase
		}
		
		if(level > 20) {
			//somecase
		}
		
		switch(type.toLowerCase()) {
			case "cleric": 
				classes.Cleric temp = new Cleric(name, health, level);
				break;
			case "ranger": 
				classes.Ranger temp1 = new Ranger(name, health, level);
				break;
			case "rogue": 
				classes.Rogue temp2 = new Rogue(name, health, level);
				break;
			case "warrior": 
				classes.Warrior temp3 = new Warrior(name, health, level);
				break;
			case "wizard": 
				classes.Wizard temp4 = new Wizard(name, health, level);
				break;
				
		}
		
		return true;
		
	}

	public void startGame() {

	}

}
