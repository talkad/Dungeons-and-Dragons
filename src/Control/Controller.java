package Control;

import java.util.*;

import Model.BoardModel;

public class Controller implements Observer {

	public static Player currentPlayer;
	public static List<Enemies> currentEnemies;
	public static char[][] board;
	public static String message;
	private int levelIndex;
	private String path; // path to the levels directory
	public static RandomGenerator random;

	public Controller(String path, Player pl, RandomGenerator random) {
		levelIndex = 1;
		board = BoardModel.read(path + "\\level " + levelIndex + ".txt");
		this.path = path;
		currentPlayer = pl;
		message = "";
		this.random=random;
		initializeEnemies();
	}

	private void initializeEnemies() {
		currentEnemies = new LinkedList<Enemies>();
		Monster m;
		Trap t;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				switch (board[i][j]) {
				case '@':
					currentPlayer.setPosition(j, i);
					currentPlayer.addObserver(this);
					break;
				case 's':
					m = new Monster("Lannister Solider", 80, 80, 8, 3, j, i, 's', 3, 25);
					currentEnemies.add(m);
					m.addObserver(this);
					break;
				case 'k':
					m = new Monster("Lannister Knight", 200, 200, 14, 8, j, i, 'k', 4, 50);
					currentEnemies.add(m);
					m.addObserver(this);
					break;
				case 'q':
					m = new Monster("Queen's Guard", 400, 400, 20, 15, j, i, 'q', 5, 100);
					currentEnemies.add(m);
					m.addObserver(this);
					break;
				case 'z':
					m = new Monster("Wright", 600, 600, 30, 15, j, i, 'z', 3, 100);
					currentEnemies.add(m);
					m.addObserver(this);
					break;
				case 'b':
					m = new Monster("Bear Wright", 1000, 1000, 75, 30, j, i, 'b', 4, 250);
					currentEnemies.add(m);
					m.addObserver(this);
					break;
				case 'g':
					m = new Monster("Giant Wright", 1500, 1500, 100, 40, j, i, 'g', 5, 500);
					currentEnemies.add(m);
					m.addObserver(this);
					break;
				case 'w':
					m = new Monster("White Walker", 2000, 2000, 150, 50, j, i, 'w', 6, 1000);
					currentEnemies.add(m);
					m.addObserver(this);
					break;
				case 'M':
					m = new Monster("The Mountain", 1000, 1000, 60, 25, j, i, 'M', 6, 500);
					currentEnemies.add(m);
					m.addObserver(this);
					break;
				case 'C':
					m = new Monster("Queen Cersei", 100, 100, 10, 10, j, i, 'C', 1, 1000);
					currentEnemies.add(m);
					m.addObserver(this);
					break;
				case 'K':
					m = new Monster("Night's King", 5000, 5000, 300, 150, j, i, 'K', 8, 5000);
					currentEnemies.add(m);
					m.addObserver(this);
					break;
				case 'B':
					t = new Trap("Bonus Trap", 1, 1, 1, 1, j, i, 'B', 5, 250, 6, 2);
					currentEnemies.add(t);
					t.addObserver(this);
					break;
				case 'Q':
					t = new Trap("Queen's Trap", 250, 250, 50, 10, j, i, 'Q', 100, 4, 10, 4);
					currentEnemies.add(t);
					t.addObserver(this);
					break;
				case 'D':
					t = new Trap("Death Trap", 500, 500, 100, 20, j, i, 'D', 250, 6, 10, 3);
					currentEnemies.add(t);
					t.addObserver(this);
					break;
				}
			}
		}
	}

	public static void combat(GameUnit attacker, GameUnit defender) {
		if (attacker.combat(defender)) { // the defender dies
			if (currentEnemies.contains(defender)) { // an enemy died
				currentPlayer.experience = currentPlayer.experience + ((Enemies) defender).experience;
				currentPlayer.levelUp();
				currentEnemies.remove(defender);
				board[defender.position.getY()][defender.position.getX()] = '.';
				message += defender.name + " died. " + attacker.name + " gained " + ((Enemies) defender).experience
						+ " experience!\n";
			} else { // the player died
				message += currentPlayer.name + " died.\n" + "You Lost\n";
				board[currentPlayer.position.getY()][currentPlayer.position.getX()] = 'X';
			}
		}
		currentPlayer.levelUp();
	}

	public void move(char ch) {
		message = "";
		currentPlayer.move(ch);
		for (Enemies en : currentEnemies)
			en.move(currentPlayer);
		if (isNextlevel())
			message += "You passed to the next level";
	}

	@Override
	public void update(Observable o, Object arg) {
		GameUnit u = (GameUnit) o;
		if (currentEnemies.contains(u)) {
			board[u.position.getY()][u.position.getX()] = ((Enemies) u).tile;
		} else {
			board[u.position.getY()][u.position.getX()] = '@';
		}
	}

	public boolean isGameOver() {
		if (currentPlayer.health.getCurrentHealth() <= 0)
			return true;
		return false;
	}

	public static GameUnit getGameUnitAt(int x, int y) // it must be an enemy or player
	{
		for (Enemies enemy : currentEnemies) {
			if (enemy.position.getX() == x && enemy.position.getY() == y)
				return enemy;
		}
		return currentPlayer;
	}

	public static boolean isDisapearedTrap(int x, int y) {
		for (Enemies enemy : currentEnemies) {
			if (enemy.position.getX() == x && enemy.position.getY() == y && board[y][x] == '.')
				return true;
		}
		return false;
	}

	private boolean isNextlevel() {
		if (currentEnemies.size() == 0) { // all enemies died
			levelIndex++;
			board = BoardModel.read(path + "\\level " + levelIndex + ".txt");
			initializeEnemies();
			return true;
		}
		return false;
	}

}
