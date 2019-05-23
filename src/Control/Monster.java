package Control;

public class Monster extends Enemies {

	public Monster(String name, Integer healthPool, Integer currentHealth, Integer attackPoints, Integer defensePoints,
			Integer coordinate_X, Integer coordinate_Y, char tile, Integer range, Integer experience) {
		super(name, healthPool, currentHealth, attackPoints, defensePoints, coordinate_X, coordinate_Y, tile, range,
				experience);
	}

	public void move(Player pl) {
		if (position.dist(pl.position) < range) {
			int dx = this.position.getX() - pl.position.getX();
			int dy = this.position.getY() - pl.position.getY();
			if (Math.abs(dx) > Math.abs(dy)) {
				if (dx > 0)
					super.moveLeft();
				else
					super.moveRight();
			} else {
				if (dy > 0)
					super.moveUp();
				else
					super.moveDown();
			}
		} else {
			int random = (int) (Math.random() * 5);
			if (random == 1)
				super.moveLeft();
			else if (random == 2)
				super.moveRight();
			else if (random == 3)
				super.moveUp();
			else if (random == 4)
				super.moveDown();

		}
	}

}
