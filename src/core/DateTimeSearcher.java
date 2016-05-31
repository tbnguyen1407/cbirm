package core;

import java.util.*;

public class DateTimeSearcher
{
	public String[] fileDatabase;

	public DateTimeSearcher(String[] filedb)
	{
		this.fileDatabase = filedb;
	}

	String[] comparison;
	int yearCompareFirst, monthCompareFirst, dayCompareFirst, hourCompareFirst, minuteCompareFirst, secondCompareFirst;
	int yearCompareSecond = 0, monthCompareSecond = 0, dayCompareSecond = 0, hourCompareSecond = 0, minuteCompareSecond = 0, secondCompareSecond = 0;
	int yearCurrent, monthCurrent, dayCurrent, hourCurrent, minuteCurrent, secondCurrent;

	public HashMap sortTime(boolean asc)
	{

		//HashMap resultTable = new HashMap();
		List<String> comparison = new ArrayList<String>();
		List<String> filePathList = new ArrayList<String>();

		int numberOfImages = fileDatabase.length;

		// Newest -> Oldest
		for (int i = 0; i < numberOfImages; i++)
		{
			String imagePath = fileDatabase[i];

			String actualTime = DateTime.loadImageIndex(imagePath);

			if (actualTime != "-")
			{
				yearCurrent = Integer.parseInt(actualTime.substring(0, 4));
				monthCurrent = Integer.parseInt(actualTime.substring(5, 7));
				dayCurrent = Integer.parseInt(actualTime.substring(8, 10));
				hourCurrent = Integer.parseInt(actualTime.substring(11, 13));
				minuteCurrent = Integer.parseInt(actualTime.substring(14, 16));
				secondCurrent = Integer.parseInt(actualTime.substring(17, 19));

			}

			if (comparison.isEmpty())
			{   //Check whether there are elements inside already
				comparison.add(actualTime);
				filePathList.add(imagePath);
			}
			else
			{									   //Elements inside
				int size = comparison.size();
				int index = 0;

				while (index < size)
				{
					String compareFirst = comparison.get(index);
					if (compareFirst != "-")
					{
						yearCompareFirst = Integer.parseInt(compareFirst.substring(1, 5));
						monthCompareFirst = Integer.parseInt(compareFirst.substring(6, 8));
						dayCompareFirst = Integer.parseInt(compareFirst.substring(9, 11));
						hourCompareFirst = Integer.parseInt(compareFirst.substring(12, 14));
						minuteCompareFirst = Integer.parseInt(compareFirst.substring(15, 17));
						secondCompareFirst = Integer.parseInt(compareFirst.substring(18, 20));


					}
					if (index + 1 != size)
					{
						String compareSecond = comparison.get(index + 1);
						if (compareSecond != "-")
						{
							yearCompareSecond = Integer.parseInt(compareSecond.substring(1, 5));
							monthCompareSecond = Integer.parseInt(compareSecond.substring(6, 8));
							dayCompareSecond = Integer.parseInt(compareSecond.substring(9, 11));
							hourCompareSecond = Integer.parseInt(compareSecond.substring(12, 14));
							minuteCompareSecond = Integer.parseInt(compareSecond.substring(15, 17));
							secondCompareSecond = Integer.parseInt(compareSecond.substring(18, 20));

						}
					}

					Calendar current = Calendar.getInstance();
					current.set(yearCurrent, monthCurrent, dayCurrent, hourCurrent, minuteCurrent, secondCurrent);
					Calendar first = Calendar.getInstance();
					first.set(yearCompareFirst, monthCompareFirst, dayCompareFirst, hourCompareFirst, minuteCompareFirst, secondCompareFirst);
					Calendar second = Calendar.getInstance();
					second.set(yearCompareSecond, monthCompareSecond, dayCompareSecond, hourCompareSecond, minuteCompareSecond, secondCompareSecond);

					if (current.compareTo(first) < 0)
					{
						comparison.add(index, actualTime);
						filePathList.add(index, imagePath);
						index = 0;

						break;
					}
					else if (current.compareTo(second) < 0)
					{
						comparison.add(index + 1, actualTime);
						filePathList.add(index + 1, imagePath);
						index = 0;

						break;
					}
					else
					{
						if (index + 1 == size)
						{
							comparison.add(actualTime);
							filePathList.add(imagePath);
							index = 0;
							break;

						}
					}
					index++;
				}
			}
		}

		HashMap resultTable = new HashMap();

		return resultTable;
	}

	public HashMap queryImage(String time)
	{
		if (fileDatabase.length == 0)
		{
			return null;
		}

		HashMap resultTable = new HashMap();

		for (int i = 0; i < fileDatabase.length; i++)
		{
			String filePath = fileDatabase[i];
			String currentIndex = DateTime.loadImageIndex(filePath);

			if (currentIndex.startsWith(time))
				resultTable.put(filePath, currentIndex);
		}
		return resultTable;

	}

	private static HashMap sort(HashMap map, final boolean isAsc)
	{
		LinkedList list = new LinkedList(map.entrySet());
		System.out.println("-->" + list.size());
		Collections.sort(list, new Comparator()
		{
			public int compare(Object o1, Object o2)
			{
				if (isAsc)
					return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
				else
					return -1 * ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		HashMap result = new LinkedHashMap();

		for (Iterator it = list.iterator(); it.hasNext();)
		{
			Map.Entry entry = (Map.Entry) it.next();
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}

	public ArrayList<String> getListOfDateTime()
	{

		ArrayList<String> comparison = new ArrayList<String>();

		int numberOfImages = fileDatabase.length;

		int counter = 0;

		// Newest -> Oldest
		for (int i = 0; i < numberOfImages; i++)
		{
			String imagePath = fileDatabase[i];

			String actualTime = DateTime.loadImageIndex(imagePath);
			if (actualTime.length() > 8)
			{
				String year = actualTime.substring(0, 4);
				//String month = actualTime.substring(5, 7);
				//String middle = "-";
				String time = year; // + middle + month;

				if (comparison.isEmpty())
				{   //Check whether there are elements inside already
					comparison.add(time);
				}
				else
				{									   //Elements inside
					for (int j = 0; j < comparison.size(); j++)
					{
						String compare = comparison.get(j);
						if (compare.compareTo(time) == 0)
						{
							counter++;
						}
					}
					if (counter == 0)
					{
						comparison.add(time);
					}
				}

				counter = 0;

			}
		}

		Collections.sort(comparison);

		return comparison;
	}
}



