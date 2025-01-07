package cbirm.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SemanticTagSearcher {
    // region fields

    private String[] fileDatabase;

    // endregion

    // region constructors

    public SemanticTagSearcher(String[] filedb) {
        this.fileDatabase = filedb;
    }

    // endregion

    // region public methods

    public Map<String, Double> queryImage(String tagString) {
        if (fileDatabase.length == 0)
            return null;

        String[] tags = tagString.split(" ");
        Map<String, Double> resultTable = new HashMap<>();

        for (int i = 0; i < fileDatabase.length; i++) {
            String filePath = fileDatabase[i];
            String[] currentIndex = SemanticTag.loadImageIndex(filePath);
            double diff = SemanticTag.calculateDistance(tags, currentIndex);
            resultTable.put(filePath, diff);
        }
        return resultTable;
    }

    public List<String> getListOfTags() {
        return Arrays.asList(
                "animal",
                "family",
                "food",
                "friend",
                "landscape",
                "party",
                "pet",
                "recreation",
                "sport",
                "travel",
                "vehicle");
    }

    public ArrayList<String> queryExactTag(String queryTag) {
        if (fileDatabase.length == 0) {
            return null;
        }

        String querytag = queryTag.trim().toLowerCase();
        ArrayList<String> resultTable = new ArrayList<String>();

        for (int i = 0; i < fileDatabase.length; i++) {
            String filePath = fileDatabase[i];
            String currentIndex = SemanticTag.indexToString(SemanticTag.loadImageIndex(filePath)).toLowerCase();
            if (currentIndex.contains(querytag))
                resultTable.add(filePath);
        }

        return resultTable;
    }

    // endregion
}
