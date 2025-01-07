package cbirm.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateTimeSearcher {
	// region fields

	private String[] fileDatabase;

	// endregion

	// region constructors

	public DateTimeSearcher(String[] filedb) {
		this.fileDatabase = filedb;
	}

	// endregion

	// region public methods

	public Map<String, String> queryImage(String time) {
		if (fileDatabase.length == 0) {
			return null;
		}

		Map<String, String> resultTable = new HashMap<>();

		for (int i = 0; i < fileDatabase.length; i++) {
			String filePath = fileDatabase[i];
			String currentIndex = DateTime.loadImageIndex(filePath);

			if (currentIndex.startsWith(time))
				resultTable.put(filePath, currentIndex);
		}
		return resultTable;

	}

	public List<String> getListOfDateTime() {

		List<String> comparison = new ArrayList<>();

		int numberOfImages = fileDatabase.length;

		int counter = 0;

		// Newest -> Oldest
		for (int i = 0; i < numberOfImages; i++) {
			String imagePath = fileDatabase[i];

			String actualTime = DateTime.loadImageIndex(imagePath);
			if (actualTime.length() > 8) {
				String year = actualTime.substring(0, 4);
				// String month = actualTime.substring(5, 7);
				// String middle = "-";
				String time = year; // + middle + month;

				if (comparison.isEmpty()) { // Check whether there are elements inside already
					comparison.add(time);
				} else { // Elements inside
					for (int j = 0; j < comparison.size(); j++) {
						String compare = comparison.get(j);
						if (compare.compareTo(time) == 0) {
							counter++;
						}
					}
					if (counter == 0) {
						comparison.add(time);
					}
				}

				counter = 0;

			}
		}

		Collections.sort(comparison);

		return comparison;
	}

	// endregion
}
