package cbirm.core;

import java.util.HashMap;
import java.util.Map;

public class ColorCorrelogramSearcher {
	// region fields

	private String[] fileDatabase;

	// endregion

	// region constructors

	public ColorCorrelogramSearcher(String[] filedb) {
		this.fileDatabase = filedb;
	}

	// endregion

	// region public methods

	public Map<String, Double> queryImage(String imagePath) {
		if (fileDatabase.length == 0) {
			return null;
		}

		Map<String, Double> resultTable = new HashMap<>();

		double[][] queryIndex = ColorCorrelogram.loadImageIndex(imagePath);

		for (int i = 0; i < fileDatabase.length; i++) {
			String filePath = fileDatabase[i];
			double[][] currentIndex = ColorCorrelogram.loadImageIndex(filePath);
			double diff = (double) ColorCorrelogram.calculateDistance(queryIndex, currentIndex);
			resultTable.put(filePath, diff);
		}
		return resultTable;
	}

	// endregion
}
