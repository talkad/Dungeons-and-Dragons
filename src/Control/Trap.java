package Control;

import java.util.*;

public class Trap extends Enemies {
	private Integer relocationTime;
	private Integer ticksCount;
	private Integer visibilityTime;

	public Trap(String name, Integer healthPool, Integer currentHealth, Integer attackPoints, Integer defensePoints,
			Integer coordinate_X, Integer coordinate_Y, char tile, Integer range, Integer experience,
			Integer relocationTime, Integer visibilityTime) {
		super(name, healthPool, currentHealth, attackPoints, defensePoints, coordinate_X, coordinate_Y, tile, range,
				experience);
		this.relocationTime = relocationTime;
		this.ticksCount = 0;
		this.visibilityTime = visibilityTime;
	}

	public Integer getTicksCount() {
		return this.ticksCount;
	}

	public Integer getVisibilityTime() {
		return this.visibilityTime;
	}

	public Integer getRelocationTime() {
		return this.relocationTime;
	}

	@Override
	public void move(Player player) {
		if (ticksCount == relocationTime) {
			ticksCount = 0;
			List<Position> positions = new LinkedList<>();
			Position pos;
			for (int i = position.getY() - range; i <= position.getY() + range; i++) {
				for (int j = position.getX() - range; j <= position.getX() + range; j++) {
					if (i > 0 && i < Controller.board.length - 1 && j > 0 && j < Controller.board[i].length - 1) { // check
																													// if
																													// legal
																													// indexes
						if (Controller.board[i][j] == '.') {
							pos = new Position(j, i);
							if (this.position.dist(pos) < range)
								positions.add(pos);
						}
					}
				}
			}

			if (positions.size() > 0) {
				int index = (int) (Math.random() * (positions.size()));
				pos = positions.get(index);
				Controller.board[this.position.getY()][this.position.getX()] = '.';
				this.setPosition(pos.getX(), pos.getY());
			}
		} else {
			ticksCount++;
			if (this.position.dist(player.position) < 2)
				this.combat(player);
		}

		if (ticksCount < visibilityTime)
			Controller.board[this.position.getY()][this.position.getX()] = tile;
		else
			Controller.board[this.position.getY()][this.position.getX()] = '.';

	}
}
