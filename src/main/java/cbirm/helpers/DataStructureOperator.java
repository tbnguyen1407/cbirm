package cbirm.helpers;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cbirm.core.DateTime;
import cbirm.core.Location;
import cbirm.core.SemanticTag;

public class DataStructureOperator {
    // region public methods

    public static Map<String, Double> normalizeHashMap(Map<String, Double> map) {
        Map<String, Double> tempMap = new HashMap<>();

        // Find largest value
        Iterator<String> iterator = map.keySet().iterator();
        double largestValue = 0;
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (map.get(key) > largestValue)
                largestValue = map.get(key);
        }

        double normalizedValue;
        iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            normalizedValue = (map.get(key) / largestValue);
            tempMap.put(key, normalizedValue);
        }

        return tempMap;
    }

    public static Object[][] ListTo2DArray(List<String> list) {
        Object[][] array = new Object[list.size()][1];
        for (int i = 0; i < list.size(); i++)
            array[i][0] = list.get(i);
        return array;
    }

    public static Object[][] ListToSearchResultArray(List<String> list) {
        Object[][] array = new Object[list.size()][5];

        for (int i = 0; i < list.size(); i++) {
            String filePath = list.get(i);

            array[i] = (new Object[] {
                    // ImageIndexHolder.getInstance().loadImageIcon(filePath),
                    ImageOperator.createImageIcon(filePath),
                    (new File(filePath)).getName(),
                    SemanticTag.indexToString(SemanticTag.loadImageIndex(filePath)),
                    Location.loadImageIndex(filePath),
                    DateTime.loadImageIndex(filePath)
            });
        }

        return array;
    }

    public Object[][] ListTo2DImageArray(List<String> list) {
        Object[][] array = new Object[(list.size() / 5) + 1][5];

        int j = 0;
        for (int i = 1; i <= list.size(); i++) {
            array[j][(i - 1) % 5] = ImageOperator.createImageIcon(list.get(i - 1));
            if ((i % 5) == 0)
                j++;
        }

        // Add dummy data if necessary to ensure row size
        if ((list.size() % 5) != 0) {
            for (int i = 0; i < (5 - list.size() % 5); i++)
                array[list.size() / 5][list.size() % 5 + i] = ImageOperator.createDummyImageIcon();
        }
        return array;
    }

    // endregion
}
