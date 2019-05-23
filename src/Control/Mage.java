package Control;

public class Mage extends Player {

	private Integer spellPower; // ability scale factor.
	private Integer manaPool; // holds the maximal value of resources.
	private Integer currentMana; // current amount of resources.
	private Integer cost; // ability cost.
	private Integer hitTimes; // maximal number of times the ability hit.
	private Integer range; // ability range.

	public Mage(String name, Integer healthPool, Integer currentHealth, Integer attackPoints, Integer defensePoints,
			Integer coordinate_X, Integer coordinate_Y, Integer spellPower, Integer manaPool, Integer cost,
			Integer hitTimes, Integer range) {
		super(name, healthPool, currentHealth, attackPoints, defensePoints, coordinate_X, coordinate_Y);
		this.spellPower = spellPower;
		this.manaPool = manaPool;
		this.currentMana = manaPool / 4;
		this.cost = cost;
		this.hitTimes = hitTimes;
		this.range = range;
	}

	@Override
	public void specialAbility() {
		levelUp();
		if (currentMana < cost)
			Controller.message += "Currentlly you can't use your special ability\n";
		else {
			currentMana = currentMana - cost;
			int hits = 0;
			Controller.message += this.name + " cast Blizzard.\n";
			for (Enemies enemy : Controller.currentEnemies) {
				if (hits < hitTimes && position.dist(enemy.position) <= range) {
					if (Math.random() < 0.5) { // select random enemy within range
						int defense = enemy.rollDefend();
						Controller.message += enemy.name + " rolled " + defense + " defense points.\n";
						int damage = spellPower - defense;
						if (damage < 0)
							damage = 0;
						enemy.health.setCurrentHealth(enemy.health.getCurrentHealth() - damage);
						Controller.message += this.name + " hit " + enemy.name + " for " + damage + " ability damage\n";
						hits++;
					}
				}
			}
		}
	}

	public void levelUp() {
		super.levelUp();
		if (experience >= 50 * level) {
			manaPool = manaPool + (25 * level);
			currentMana = Math.min(currentMana + (manaPool / 4), manaPool);
			spellPower = spellPower + (10 * level);
			Controller.message += "		+" + (25 * level) + " maximum mana, +" + (10 * level) + " spell power\n";
		}
	}

	@Override
	public void onGameTick() {
		currentMana = Math.min(manaPool, currentMana + 1);
	}

	public Integer getSpellPower() {
		return spellPower;
	}

	public void setSpellPower(Integer spellPower) {
		this.spellPower = spellPower;
	}

	public Integer getManaPool() {
		return manaPool;
	}

	public void setManaPool(Integer manaPool) {
		this.manaPool = manaPool;
	}

	public Integer getCurrentMana() {
		return currentMana;
	}

	public void setCurrentMana(Integer currentMana) {
		this.currentMana = currentMana;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getHitTimes() {
		return hitTimes;
	}

	public void setHitTimes(Integer hitTimes) {
		this.hitTimes = hitTimes;
	}

	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}

	public String toString() {
		return super.toString() + "SpellPower: " + spellPower + "		Mana: " + currentMana + "/" + manaPool + "\n";
	}

}
