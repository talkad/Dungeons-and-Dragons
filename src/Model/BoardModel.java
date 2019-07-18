package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Control.Controller;

public class BoardModel {

	public static char[][] read(String path) {
		List<String> lines = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String next;
			while ((next = reader.readLine()) != null) {
				lines.add(next);
			}
			char[][] board = new char[lines.size()][lines.get(0).length()];
			String row = "";
			for (int i = 0; i < lines.size(); i++) {
				row = lines.get(i);
				for (int j = 0; j < lines.get(0).length(); j++) {
					board[i][j] = row.charAt(j);
				}
			}
			return board;
		} catch (FileNotFoundException e) {
			Controller.message+="You Won!";
		} catch (IOException e) {
			Controller.message+="You Won!";
		}
		return null;
	}
}
