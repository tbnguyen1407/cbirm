package core;

import java.util.HashMap;

public class ColorHistogramSearcher
{

	public String[] fileDatabase;

	public ColorHistogramSearcher(String[] filedb)
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

		int[] queryIndex = ColorHistogram.loadImageIndex(imagePath);

		for (int i = 0; i < fileDatabase.length; i++)
		{
			String filePath = fileDatabase[i];
			int[] hist2 = ColorHistogram.loadImageIndex(filePath);
			double intersection = ColorHistogram.Intersection(queryIndex, hist2);
			resultTable.put(filePath, intersection);
		}

		return resultTable;
	}
}
