package Control;

public class Warrior extends Player {

	private Integer cooldown; // Represents the number of game ticks required to pass before the warrior can
								// cast the ability again.
	private Integer remaining; // Represents the number of ticks remained until the warrior can re-cast its
								// special ability.

	public Warrior(String name, Integer healthPool, Integer currentHealth, Integer attackPoints, Integer defensePoints,
			Integer coordinate_X, Integer coordinate_Y, Integer cooldown) {
		super(name, healthPool, currentHealth, attackPoints, defensePoints, coordinate_X, coordinate_Y);
		this.cooldown = cooldown;
		remaining = 0;
	}

	public void levelUp() {
		super.levelUp();
		if (experience >= 50 * level) {
			remaining = 0;
			health.setHealthPool(health.getHealthPool() + (5 * level));
			defensePoints = defensePoints + level;
		}
	}

	@Override
	public void onGameTick() {
		if (remaining > 0)
			remaining--;
	}

	@Override
	public void specialAbility() {
		levelUp();
		if (remaining > 0)
			Controller.message += "Currentlly you can't use your special ability\n";
		else {
			remaining = cooldown;
			health.setCurrentHealth(health.getCurrentHealth() + (2 * defensePoints));
			Controller.message += this.name + " cast Heal, healing for " + (2 * defensePoints) + "\n";
		}
	}

	public Integer getCooldown() {
		return cooldown;
	}

	public void setCooldown(Integer cooldown) {
		this.cooldown = cooldown;
	}

	public Integer getRemaining() {
		return remaining;
	}

	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
	}

	public String toString() {
		return super.toString() + "Ability cooldown: " + cooldown + "		Remaining: " + remaining + "\n";
	}

}
