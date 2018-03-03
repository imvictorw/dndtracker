package classes;

public class Player {
	
	private String name;
	private int health = 0;
	private int level = 0;
	private PlayerType type;
	
	public Player(String name, PlayerType type) {
		this.name = name;
		this.type = type;
		
		switch(type) {
			case BARBARIAN:
				this.type = type;
				this.health = 12;
				break;
			case BARD:
				this.type = type;
				this.health = 8;
				break;
			case CLERIC:
				this.type = type;
				this.health = 8;
				break;
			case DRUID:
				this.type = type;
				this.health = 8;
				break;
			case FIGHTER:
				this.type = type;
				this.health = 10;
				break;
			case MONK:
				this.type = type;
				this.health = 8;
				break;
			case PALADIN:
				this.type = type;
				this.health = 10;
				break;
			case RANGER:
				this.type = type;
				this.health = 10;
				break;
			case ROGUE:
				this.type = type;
				this.health = 8;
				break;
			case SORCERER:
				this.type = type;
				this.health = 6;
				break;
			case WARLOCK:
				this.type = type;
				this.health = 8;
				break;
			case WIZARD:
				this.type = type;
				this.health = 6;
				break;
		}
	}
	
	public Player(String name, int health, int level, PlayerType type) {
		this.name = name;
		this.level = level;
		this.health = health;
		this.type = type;
		
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
