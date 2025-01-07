package cbirm.core;

import java.util.HashMap;
import java.util.Map;

public class EdgeDirectionSearcher {
	// region fields

	private String[] fileDatabase;

	// endregion

	// region constructors

	public EdgeDirectionSearcher(String[] filedb) {
		this.fileDatabase = filedb;
	}

	// endregion

	// region public methods

	public Map<String, Double> queryImage(String imagePath) {
		if (fileDatabase.length == 0) {
			return null;
		}

		Map<String, Double> resultTable = new HashMap<>();

		int[] queryIndex = EdgeDirection.loadImageIndex(imagePath);

		for (int i = 0; i < fileDatabase.length; i++) {
			String filePath = fileDatabase[i];
			int[] currentIndex = EdgeDirection.loadImageIndex(filePath);
			double diff = (double) EdgeDirection.calculateDistance(queryIndex, currentIndex);
			resultTable.put(filePath, diff);
		}
		return resultTable;
	}

	// endregion
}
