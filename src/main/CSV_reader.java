package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSV_reader {

	private static final String COMMA_DELIMITER = ",";

	/**
	 * return null if file not found
	 * @param path
	 * @return data
	 */
	public static List<List<String>> getData(String path) {

		List<List<String>> records = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(path), "UTF-8");) {
			while (scanner.hasNextLine()) {
				records.add(getRecordFromLine(scanner.nextLine()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		// fix issues that size of some lines is not match with the others
		records = fixIssues(records);

		return records;

	}

	private static List<String> getRecordFromLine(String line) {
		List<String> values = new ArrayList<String>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(COMMA_DELIMITER);
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}

		return values;
	}

	private static List<List<String>> fixIssues(List<List<String>> toFix) {
		int amountColumn = toFix.get(0).size();

		for (List<String> line : toFix) {
			if (line.size() < amountColumn) {
				line.add((amountColumn - 2), "");
			}
		}

		return toFix;
	}

}
