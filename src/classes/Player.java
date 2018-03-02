package classes;

public abstract class Player {
	
	private String name;
	private int health = 0;
	private int level = 0;
	private PlayerType type;
	
	public Player(String name, PlayerType type) {
		this.name = name;
		this.type = type;
	}
	
	public Player(String name, int health, int level, PlayerType type) {
		this.name = name;
		this.health = health;
		this.level = level;
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
