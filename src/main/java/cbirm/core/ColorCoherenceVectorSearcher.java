package cbirm.core;

import java.util.HashMap;
import java.util.Map;

public class ColorCoherenceVectorSearcher {
	// region fields

	private String[] fileDatabase;

	// endregion

	// region constructors

	public ColorCoherenceVectorSearcher(String[] filedb) {
		this.fileDatabase = filedb;
	}

	// endregion

	// region public methods

	public Map<String, Double> queryImage(String imagePath) {
		if (fileDatabase.length == 0) {
			return null;
		}

		Map<String, Double> resultTable = new HashMap<>();

		int[][] queryIndex = ColorCoherenceVector.loadImageIndex(imagePath);

		for (int i = 0; i < fileDatabase.length; i++) {
			String filePath = fileDatabase[i];
			int[][] currentIndex = ColorCoherenceVector.loadImageIndex(filePath);
			double diff = ColorCoherenceVector.calculateDistance(queryIndex, currentIndex);
			resultTable.put(filePath, diff);
		}
		return resultTable;
	}

	// endregion
}
