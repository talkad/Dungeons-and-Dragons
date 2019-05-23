package View;

import Control.ActionReader;
import Control.Controller;
import Control.RandomGenerator;

public class UserInterface {
	private Controller control;
	private ActionReader action;
	private RandomGenerator random;

	public UserInterface(Controller control, ActionReader action, RandomGenerator random) {
		this.control = control;
		this.action = action;
		this.random = random;
	}

	// prints the borad and the game stats
	public void print() {
		System.out.println(Controller.message);
		for (int i = 0; i < Controller.board.length; i++) {
			for (int j = 0; j < Controller.board[i].length; j++) {
				System.out.print(Controller.board[i][j]);
			}
			System.out.println();
		}
		System.out.println();

		System.out.println(Controller.currentPlayer);
	}

	public void startGame() {
		try {
			char ch;
			System.out.println("Use w/s/a/d to move.\nUse e for special ability or q to pass.");
			print();
			while (!control.isGameOver()) {
				ch = action.nextAction().charAt(0);
				control.move(ch);
				print();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
	}
}
