package core;

import java.util.ArrayList;
import java.util.TreeSet;

public class LocationSearcher
{
	public String[] fileDatabase;

	public LocationSearcher(String[] filedb)
	{
		this.fileDatabase = filedb;
	}

	public ArrayList<String> queryImage(String location)
	{
		if (fileDatabase.length == 0)
		{
			return null;
		}

		ArrayList<String> resultTable = new ArrayList();

		for (int i = 0; i < fileDatabase.length; i++)
		{
			String filePath = fileDatabase[i];
			String currentIndex = Location.loadImageIndex(filePath);

			if (currentIndex != null && currentIndex.equalsIgnoreCase(location))
				resultTable.add(filePath);
		}

		return resultTable;
	}

	public ArrayList<String> getListOfLocations()
	{
		TreeSet set = new TreeSet();
		for (int i = 0; i < fileDatabase.length; i++)
		{
			String filePath = fileDatabase[i];
			String currentIndex = Location.loadImageIndex(filePath);

			if (currentIndex != null)
				set.add(currentIndex);

		}
		ArrayList<String> alist = new ArrayList<String>(set);
		return alist;
	}
}
