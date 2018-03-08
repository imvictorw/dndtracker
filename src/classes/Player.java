package classes;
/**
 * 
 * @author Victor
 * 
 * TODO:
 * Still have to add specific levels up & add max health
 *
 */
public class Player {
	
	private String name;
	private int health = 0;
	private int maxHealth = 0;
	private int level = 0;
	private PlayerType type;
	private MonsterType mtype;
	
	//Players
	public Player(String name, PlayerType type) {
		this.name = name;
		
		switch(type) {
			case BARBARIAN:
				this.type = type;
				this.health = 12;
				this.maxHealth = 12;
				break;
			case BARD:
				this.type = type;
				this.health = 8;
				this.maxHealth = 8;
				break;
			case CLERIC:
				this.type = type;
				this.health = 8;
				this.maxHealth = 8;
				break;
			case DRUID:
				this.type = type;
				this.health = 8;
				this.maxHealth = 8;
				break;
			case FIGHTER:
				this.type = type;
				this.health = 10;
				this.maxHealth = 10;
				break;
			case MONK:
				this.type = type;
				this.health = 8;
				this.maxHealth = 8;
				break;
			case PALADIN:
				this.type = type;
				this.health = 10;
				this.maxHealth = 10;
				break;
			case RANGER:
				this.type = type;
				this.health = 10;
				this.maxHealth = 10;
				break;
			case ROGUE:
				this.type = type;
				this.health = 8;
				this.maxHealth = 8;
				break;
			case SORCERER:
				this.type = type;
				this.health = 6;
				this.maxHealth = 6;
				break;
			case WARLOCK:
				this.type = type;
				this.health = 8;
				this.maxHealth = 8;
				break;
			case WIZARD:
				this.type = type;
				this.health = 6;
				this.maxHealth = 8;
				break;
		}
	}
	
	public Player(String name, PlayerType type, int health, int level) {
		this.name = name;
		this.level = level;
		this.health = health;
		this.maxHealth = health;
		this.type = type;
		
	}
	
	//Monsters
	public Player(String name, MonsterType mtype, int health, int level) {
		this.name = name;
		this.level = level;
		this.health = health;
		this.maxHealth = health;
		this.mtype = mtype;
	}
	
	
	public MonsterType getMtype() {
		return mtype;
	}

	public void setMtype(MonsterType mtype) {
		this.mtype = mtype;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public PlayerType getType() {
		return type;
	}

	public void setType(PlayerType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	

}
