package core;

import java.util.HashMap;

public class ColorCorrelogramSearcher
{
	public String[] fileDatabase;

	public ColorCorrelogramSearcher(String[] filedb)
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

		double[][] queryIndex = ColorCorrelogram.loadImageIndex(imagePath);

		for (int i = 0; i < fileDatabase.length; i++)
		{
			String filePath = fileDatabase[i];
			double[][] currentIndex = ColorCorrelogram.loadImageIndex(filePath);
			double diff = (double) ColorCorrelogram.calculateDistance(queryIndex, currentIndex);
			resultTable.put(filePath, diff);
		}
		return resultTable;

	}

}
