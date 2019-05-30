package Control;

import java.util.Observable;

public abstract class GameUnit extends Observable {

	protected String name;
	protected Health health;
	protected Integer attackPoints;
	protected Integer defensePoints;
	protected Position position;

	public GameUnit(String name, Integer healthPool, Integer currentHealth, Integer attackPoints, Integer defensePoints,
			Integer x, Integer y) {
		this.name = name;
		health = new Health(healthPool, currentHealth);
		this.attackPoints = attackPoints;
		this.defensePoints = defensePoints;
		this.position = new Position(x, y);
	}

	public void setPosition(int x, int y) {
		this.position.setX(x);
		this.position.setY(y);
		setChanged();
		notifyObservers();
	}
	
	public Position getPosition() {
		return position;
	}
	
	public int getCurrentHealth() {
		return health.getCurrentHealth();
	}
	
	
	public String toString() {
		return name + "		Health: " + health.getCurrentHealth() + "		Attack damage: " + attackPoints
				+ "		Defense: " + defensePoints + "\n";
	}

	public void moveLeft() {
		GameUnit gu = Controller.getGameUnitAt(position.getX() - 1, position.getY());
		if (Controller.isDisapearedTrap(position.getX() - 1, position.getY())) { // returns true if there is a trap in																					// that location
			Controller.combat(this, gu);
		} else if (Controller.board[position.getY()][position.getX() - 1] == '.') {
			Controller.board[position.getY()][position.getX()] = '.';
			setPosition(position.getX() - 1, position.getY());
		} else if (Controller.board[position.getY()][position.getX() - 1] != '#') // it is a game unit
		{
			if (!Controller.currentEnemies.contains(gu) || !Controller.currentEnemies.contains(this))
				Controller.combat(this, gu);
		}
	}

	public void moveRight() {
		GameUnit gu = Controller.getGameUnitAt(position.getX() + 1, position.getY());
		if (Controller.isDisapearedTrap(position.getX() + 1, position.getY())) { // returns true if there is a trap in
																					// that location
			Controller.combat(this, gu);
		} else if (Controller.board[position.getY()][position.getX() + 1] == '.') {
			Controller.board[position.getY()][position.getX()] = '.';
			setPosition(position.getX() + 1, position.getY());
		} else if (Controller.board[position.getY()][position.getX() + 1] != '#') // it is a game unit
		{
			if (!Controller.currentEnemies.contains(gu) || !Controller.currentEnemies.contains(this))
				Controller.combat(this, gu);
		}
	}

	public void moveUp() {
		GameUnit gu = Controller.getGameUnitAt(position.getX(), position.getY() - 1);
		if (Controller.isDisapearedTrap(position.getX(), position.getY() - 1)) { // returns true if there is a trap in
																					// that location
			Controller.combat(this, gu);
		} else if (Controller.board[position.getY() - 1][position.getX()] == '.') {
			Controller.board[position.getY()][position.getX()] = '.';
			setPosition(position.getX(), position.getY() - 1);
		} else if (Controller.board[position.getY() - 1][position.getX()] != '#') // it is a game unit
		{
			if (!Controller.currentEnemies.contains(gu) || !Controller.currentEnemies.contains(this))
				Controller.combat(this, gu);
		}
	}

	public void moveDown() {
		GameUnit gu = Controller.getGameUnitAt(position.getX(), position.getY() + 1);
		if (Controller.isDisapearedTrap(position.getX(), position.getY() + 1)) { // returns true if there is a trap in
																					// that location
			Controller.combat(this, gu);
		} else if (Controller.board[position.getY() + 1][position.getX()] == '.') {
			Controller.board[position.getY()][position.getX()] = '.';
			setPosition(position.getX(), position.getY() + 1);
		} else if (Controller.board[position.getY() + 1][position.getX()] != '#') // it is a game unit
		{
			if (!Controller.currentEnemies.contains(gu) || !Controller.currentEnemies.contains(this))
				Controller.combat(this, gu);
		}
	}


	// Returns true if the defender dies. false otherwise
	public boolean combat(GameUnit defender) {
		int attackDamge = Controller.random.nextInt(this.attackPoints);
		int defense = Controller.random.nextInt(defender.defensePoints);

		Controller.message += this.name + " engaged in battle with " + defender.name + ":\n";
		Controller.message += this.toString();
		Controller.message += defender.toString();
		Controller.message += this.name + " rolled " + attackDamge + " attack points.\n";
		Controller.message += defender.name + " rolled " + defense + " defense points.\n";

		int damage = attackDamge - defense;
		if (damage > 0)
			defender.health.setCurrentHealth(defender.health.getCurrentHealth() - damage);
		else
			damage = 0;

		Controller.message += this.name + " hit " + defender.name + " for " + damage + " damage.\n";

		if (defender.health.getCurrentHealth() <= 0)
			return true;
		return false;

	}

}
