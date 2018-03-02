package classes;

public class Cleric {

	public String name;

	// Stats
	private int currHealth;
	private int maxHealth;
	private int level;
	
	public Cleric(String name) {
		this.name = name;
		this.maxHealth = 8;
		this.currHealth = 8;
		this.level = 1;
	}

	public Cleric(String name1, int maxhealth1, int level1) {
		this.name = name1;
		this.maxHealth = maxhealth1;
		this.currHealth = maxHealth;
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
