package Control;

import java.util.Scanner;

public class ReadAction implements ActionReader {
	public static Scanner reader = new Scanner(System.in);

	@Override
	public String nextAction() {
		return reader.next().charAt(0) + "";

	}

}
