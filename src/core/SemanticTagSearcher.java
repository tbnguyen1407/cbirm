package core;

import java.util.ArrayList;
import java.util.HashMap;

public class SemanticTagSearcher
{
	public String[] fileDatabase;

	public SemanticTagSearcher(String[] filedb)
	{
		this.fileDatabase = filedb;
	}

    public HashMap queryImage(String tagString)
    {
        if (fileDatabase.length == 0)
            return null;

        String[] tags = tagString.split(" ");
        HashMap resultTable = new HashMap();

        for (int i = 0; i < fileDatabase.length; i++)
        {
            String filePath = fileDatabase[i];
            String[] currentIndex = SemanticTag.loadImageIndex(filePath);
            double diff = SemanticTag.calculateDistance(tags, currentIndex);
            resultTable.put(filePath, diff);
        }
        return resultTable;
    }

    public ArrayList getListOfTags()
	{
		ArrayList alist = new ArrayList();
		alist.add("animal");
        alist.add("family");
        alist.add("food");
        alist.add("friend");
		alist.add("landscape");
		alist.add("party");
		alist.add("pet");
		alist.add("recreation");
		alist.add("sport");
        alist.add("travel");
		alist.add("vehicle");
		return alist;
	}

    public ArrayList<String> queryExactTag(String queryTag)
    {
        if (fileDatabase.length == 0)
        {
            return null;
        }

        String querytag = queryTag.trim().toLowerCase();
        ArrayList<String> resultTable = new ArrayList<String>();

        for (int i = 0; i < fileDatabase.length; i++)
        {
            String filePath = fileDatabase[i];
            String currentIndex = SemanticTag.indexToString(SemanticTag.loadImageIndex(filePath)).toLowerCase();
            if (currentIndex.contains(querytag))
                resultTable.add(filePath);
        }

        return resultTable;
    }
}
