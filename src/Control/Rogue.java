package Control;

public class Rogue extends Player {

	private Integer cost;
	private Integer currentEnergy;

	public Rogue(String name, Integer healthPool, Integer currentHealth, Integer attackPoints, Integer defensePoints,
			Integer coordinate_X, Integer coordinate_Y, Integer cost) {
		super(name, healthPool, currentHealth, attackPoints, defensePoints, coordinate_X, coordinate_Y);
		this.cost = cost;
		currentEnergy = 100;
	}

	public void levelUp() {
		super.levelUp();
		if (experience >= 50 * level) {
			currentEnergy = 100;
			attackPoints = attackPoints + (3 * level);
		}
	}

	@Override
	public void specialAbility() {
		levelUp();
		if (currentEnergy < cost)
			Controller.message += "Currentlly you can't use your special ability\\n";
		else {
			currentEnergy = currentEnergy - cost;
			Controller.message += this.name + " cast Fan of Knives.\n";
			for (Enemies enemy : Controller.currentEnemies) {
				if (position.dist(enemy.position) < 2) {
					int defense = Controller.random.nextInt(enemy.defensePoints);
					Controller.message += enemy.name + " rolled " + defense + " defense points.\n";
					int damage = attackPoints - defense;
					if (damage < 0)
						damage = 0;
					enemy.health.setCurrentHealth(enemy.health.getCurrentHealth() - damage);
					Controller.message += this.name + " hit " + enemy.name + " for " + damage + " ability damage\n";
				}
			}
		}
	}

	@Override
	public void onGameTick() {
		currentEnergy = Math.min(currentEnergy + 10, 100);
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getCurrentEnergy() {
		return currentEnergy;
	}

	public void setCurrentEnergy(Integer currentEnergy) {
		this.currentEnergy = currentEnergy;
	}

	public String toString() {
		return super.toString() + "Energy: " + currentEnergy + "/100" + "\n";
	}

}
