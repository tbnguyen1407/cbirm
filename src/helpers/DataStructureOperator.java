package helpers;

import core.DateTime;
import core.Location;
import core.SemanticTag;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DataStructureOperator
{
    public static HashMap normalizeHashMap(HashMap map)
    {
        HashMap tempMap = new HashMap();

        // Find largest value
        Iterator iterator = map.keySet().iterator();
        double largestValue = 0;
        while (iterator.hasNext())
        {
            Object key = iterator.next();
            if ((Double) map.get(key) > largestValue)
                largestValue = (Double) map.get(key);
        }


        double normalizedValue;
        iterator = map.keySet().iterator();
        while (iterator.hasNext())
        {
            Object key = iterator.next();
            normalizedValue = ((Double) map.get(key) / largestValue);
            tempMap.put(key, normalizedValue);
        }

        return tempMap;
    }

    public static Object[][] ArrayListStringToArray2DString(ArrayList<String> arrayList)
    {
        Object[][] resultObjectArray = new Object[arrayList.size()][1];
        for (int i = 0; i < arrayList.size(); i++)
            resultObjectArray[i][0] = arrayList.get(i);
        return resultObjectArray;
    }

    public static Object[][] ArrayListStringToArraySearchResult(ArrayList<String> arrayList)
    {
        Object[][] data = new Object[arrayList.size()][5];

        for (int i = 0; i < arrayList.size(); i++)
        {
            String filePath = arrayList.get(i);

            data[i] = (new Object[]
                    {
                            //ImageIndexHolder.getInstance().loadImageIcon(filePath),
                            ImageOperator.createImageIcon(filePath),
                            (new File(filePath)).getName(),
                            SemanticTag.indexToString(SemanticTag.loadImageIndex(filePath)),
                            Location.loadImageIndex(filePath),
                            DateTime.loadImageIndex(filePath)
                    });
        }

        return data;
    }

    public Object[][] ArrayListStringTo2DObjectArrayImage(ArrayList<String> arrayList)
    {
        Object[][] data = new Object[(arrayList.size() / 5) + 1][5];

        int j = 0;
        for (int i = 1; i <= arrayList.size(); i++)
        {
            data[j][(i - 1) % 5] = ImageOperator.createImageIcon(arrayList.get(i - 1));
            if ((i % 5) == 0)
                j++;
        }

        // Add dummy data if necessary to ensure row size
        if ((arrayList.size() % 5) != 0)
        {
            for (int i = 0; i < (5 - arrayList.size() % 5); i++)
                data[arrayList.size() / 5][arrayList.size() % 5 + i] = getClass().getResource("/resource/blank.jpg");
        }
        return data;
    }

}
