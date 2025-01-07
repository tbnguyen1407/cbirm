package cbirm.core;

import java.util.HashMap;
import java.util.Map;

public class ColorHistogramSearcher {
	// region fields

	private String[] fileDatabase;

	// endregion

	// region constructors

	public ColorHistogramSearcher(String[] filedb) {
		this.fileDatabase = filedb;
	}

	// endregion

	// region public methods

	public Map<String, Double> queryImage(String imagePath) {
		if (fileDatabase.length == 0) {
			return null;
		}

		Map<String, Double> resultTable = new HashMap<>();

		int[] queryIndex = ColorHistogram.loadImageIndex(imagePath);

		for (int i = 0; i < fileDatabase.length; i++) {
			String filePath = fileDatabase[i];
			int[] hist2 = ColorHistogram.loadImageIndex(filePath);
			double intersection = ColorHistogram.Intersection(queryIndex, hist2);
			resultTable.put(filePath, intersection);
		}

		return resultTable;
	}

	// endregion
}
