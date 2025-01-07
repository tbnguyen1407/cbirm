package cbirm.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LocationSearcher {
	// region fields

	private String[] fileDatabase;

	// endregion

	// region constructors

	public LocationSearcher(String[] filedb) {
		this.fileDatabase = filedb;
	}

	// endregion

	// region public methods

	public List<String> queryImage(String location) {
		if (fileDatabase.length == 0) {
			return null;
		}

		List<String> resultTable = new ArrayList<>();

		for (int i = 0; i < fileDatabase.length; i++) {
			String filePath = fileDatabase[i];
			String currentIndex = Location.loadImageIndex(filePath);

			if (currentIndex != null && currentIndex.equalsIgnoreCase(location))
				resultTable.add(filePath);
		}

		return resultTable;
	}

	public List<String> getListOfLocations() {
		Set<String> set = new HashSet<>();
		for (int i = 0; i < fileDatabase.length; i++) {
			String filePath = fileDatabase[i];
			String currentIndex = Location.loadImageIndex(filePath);

			if (currentIndex != null)
				set.add(currentIndex);

		}
		return new ArrayList<>(set);
	}

	// endregion
}
