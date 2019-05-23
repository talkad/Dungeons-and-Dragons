package Control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DeterministicNumber implements RandomGenerator {

	private List<Integer> numbers;
	private int index;

	public DeterministicNumber() {
		numbers = new LinkedList<>();
		read();
		index = 0;
	}

	private void read() {
		numbers = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\eclipse-workspace\\DungeonsAndDragons\\deterministic\\random_numbers.txt"));
			String next;
			while ((next = reader.readLine()) != null) {
				numbers.add(Integer.parseInt(next));
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found ");
		} catch (IOException e) {
			System.out.println(e.getMessage() + "\n" + e.getStackTrace());
		}
	}

	@Override
	public int nextInt(int n) {
		if (index < numbers.size()) {
			int num = numbers.get(index);
			index++;
			return num;
		}
		return -1;
	}

}
