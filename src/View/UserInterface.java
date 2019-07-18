package View;

import Control.ActionReader;
import Control.Controller;
import Control.RandomGenerator;

public class UserInterface {
	private Controller control;
	private ActionReader action;

	public UserInterface(Controller control, ActionReader action) {
		this.control = control;
		this.action = action;
	}

	// prints the borad and the game stats
	public void print() {
		if (Controller.board != null && (Controller.board.length>0 && Controller.board[0].length>0)) {
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
		else {
			System.out.println("You Won!");
		}
	}

	public void startGame() {
		try {
			char ch;
			System.out.println("Use w/s/a/d to move.\nUse e for special ability or q to pass.");
			print();
			String str = action.nextAction();
			while (!control.isGameOver() && str.length() != 0) {
				ch = str.charAt(0);
				control.move(ch);
				print();
				str = action.nextAction();
			}
		} catch (Exception e) { //there are not exist more levels
			System.out.println("You Won!");
		}
	}
}
