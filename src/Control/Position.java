package Control;

public class Position {

	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public double dist(Position pos) {
		return Math.sqrt(Math.pow(x - pos.x, 2) + Math.pow(y - pos.y, 2));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
