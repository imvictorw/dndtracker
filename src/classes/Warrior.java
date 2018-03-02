package classes;

public class Warrior {

	public String name;

	// Stats
	private int currHealth;
	private int maxHealth;
	private int level;

	// Attributes
	/*
	 * // private int armor; // private int speed; private int str; private int dex;
	 * private int con; private int intell; //int private int wis; private int cha;
	 */
	
	public Warrior(String name) {
		this.name = name;
		this.maxHealth = 10;
		this.currHealth = 10;
		this.level = 1;
	}

	public Warrior(String name1, int maxhealth1, int level1) {
		this.name = name1;
		this.maxHealth = maxhealth1;
		this.currHealth = maxHealth;
		// this.armor = armor;
		// this.speed = 25;
		this.level = level1;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrHealth() {
		return currHealth;
	}

	public void setCurrHealth(int currHealth) {
		this.currHealth = currHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
