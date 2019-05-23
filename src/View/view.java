package View;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Control.ActionReader;
import Control.Controller;
import Control.DeterministicNumber;
import Control.Mage;
import Control.Player;
import Control.RandomGenerator;
import Control.RandomNumber;
import Control.ReadAction;
import Control.ReadDeterminsticAction;
import Control.Rogue;
import Control.Warrior;

public class view {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		ActionReader action = null;
		RandomGenerator random = null;
		if (args.length == 1) {
			action = new ReadAction();
			random = new RandomNumber();
		} else if (args.length == 2) {
			action = new ReadDeterminsticAction();
			random = new DeterministicNumber();
		} else
			System.out.println("invalid CLI input");

		if (args.length > 0) {
			List<Player> plList = Arrays.asList(new Warrior("Jon Snow", 300, 300, 30, 4, 0, 0, 6),
					new Warrior("The Hound", 400, 400, 20, 6, 0, 0, 4),
					new Mage("Melisandre", 160, 160, 10, 1, 0, 0, 40, 300, 30, 5, 6),
					new Mage("Thoros Of Myr", 250, 250, 25, 3, 0, 0, 15, 150, 50, 3, 3),
					new Rogue("Arya Stark", 150, 150, 40, 2, 0, 0, 20), new Rogue("Bronn", 250, 250, 35, 3, 0, 0, 60));

			System.out.println("Select player:");
			int index = 1;
			for (Player pl : plList) {
				System.out.print(index + ". ");
				System.out.println(pl);
				index++;
			}

			Controller control = null;
			if (args.length == 1) {
				int selectedPlayer = in.nextInt();
				if (selectedPlayer <= 0 | selectedPlayer > plList.size())
					throw new IllegalArgumentException("The inserted index is out of range");
				else {
					System.out.println("You have selected:");
					Player player = plList.get(selectedPlayer - 1);
					System.out.println(player);
					control = new Controller(args[0], player);
				}
			} else if (args.length == 2 && args[1].equals("-D")) {
				System.out.println("You have selected:");
				String str=action.nextAction();
				//int num=Integer.parseInt(str);
				Player player = plList.get(5-1);
				System.out.println(player);
				control = new Controller(args[0], player);
			}

			UserInterface ui = new UserInterface(control, action, random);
			ui.startGame();
		}
	}
}
