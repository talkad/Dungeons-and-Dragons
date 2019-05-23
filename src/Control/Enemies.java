
package Control;

public abstract class Enemies extends GameUnit {

	protected char tile;
	protected Integer range;
	protected Integer experience;

	public Enemies(String name, Integer healthPool, Integer currentHealth, Integer attackPoints, Integer defensePoints,
			Integer coordinate_X, Integer coordinate_Y, char tile, Integer range, Integer experience) {
		super(name, healthPool, currentHealth, attackPoints, defensePoints, coordinate_X, coordinate_Y);
		this.tile = tile;
		this.range = range;
		this.experience = experience;
	}

	public abstract void move(Player pl);

}
