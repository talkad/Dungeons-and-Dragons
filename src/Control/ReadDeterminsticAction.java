package Control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadDeterminsticAction implements ActionReader {

	private List<String> actions;
	private int index;

	public ReadDeterminsticAction() {
		read();
		index = 0;
	}

	private void read() {
		actions = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("user_actions.txt"));
			String next;
			while ((next = reader.readLine()) != null) {
				actions.add(next);
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found ");
		} catch (IOException e) {
			System.out.println(e.getMessage() + "\n" + e.getStackTrace());
		}
	}

	@Override
	public String nextAction() {
		if (index < actions.size()) {
			String act = actions.get(index);
			index++;
			return act;
		}
		return "";
	}

}
