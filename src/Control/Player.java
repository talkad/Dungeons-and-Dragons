package Control;

public abstract class Player extends GameUnit {

	protected Integer experience; // Increased by killing enemies.
	protected Integer level; // Increased by gaining experience.

	public Player(String name, Integer healthPool, Integer currentHealth, Integer attackPoints, Integer defensePoints,
			Integer coordinate_X, Integer coordinate_Y) {
		super(name, healthPool, currentHealth, attackPoints, defensePoints, coordinate_X, coordinate_Y);
		this.experience = 0;
		this.level = 1;
	}

	public void levelUp() {
		if (experience >= 50 * level) {
			experience = experience - (50 * level);
			level++;
			health.setHealthPool(health.getHealthPool() + (10 * level));
			int h=health.getHealthPool()-health.getCurrentHealth();
			health.setCurrentHealth(health.getHealthPool());
			attackPoints = attackPoints + (5 * level);
			defensePoints = defensePoints + (2 * level);
			Controller.message += "Level up: +" +health+ " Health, +" + (5 * level) + " Attack, +"
					+ (2 * level) + " Defense\n";
		}
	}

	public abstract void specialAbility();

	public abstract void onGameTick();

	public void move(char c) {
		onGameTick();
		switch (c) {
		case 'w':
			super.moveUp();
			break;
		case 'd':
			super.moveRight();
			break;
		case 's':
			super.moveDown();
			break;
		case 'a':
			super.moveLeft();
			break;
		case 'q':
			break;
		case 'e':
			this.specialAbility();
			break;
		}
	}

	public String toString() {
		return super.toString() + "		Level: " + level + "		Experience: " + experience + "/" + 50 * level
				+ "		";
	}
}
