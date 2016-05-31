package core;

import java.io.IOException;
import java.util.HashMap;

public class ColorCoherenceVectorSearcher
{
	public String[] fileDatabase;

	public ColorCoherenceVectorSearcher(String[] filedb)
	{
		this.fileDatabase = filedb;
	}

	public HashMap queryImage(String imagePath)
	{
		if (fileDatabase.length == 0)
		{
			return null;
		}

		HashMap resultTable = new HashMap();

		int[][] queryIndex = ColorCoherenceVector.loadImageIndex(imagePath);

		for (int i = 0; i < fileDatabase.length; i++)
		{
			String filePath = fileDatabase[i];
			int[][] currentIndex = ColorCoherenceVector.loadImageIndex(filePath);
			double diff = ColorCoherenceVector.calculateDistance(queryIndex, currentIndex);
			resultTable.put(filePath, diff);
		}
		return resultTable;
	}
}
