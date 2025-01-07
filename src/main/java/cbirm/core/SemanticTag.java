package cbirm.core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import cbirm.storage.ImageIndexHolder;

public class SemanticTag {
    // region public methods

    public static String[] createIndex(String imagePath) {
        List<String> tmp = new ArrayList<>();
        String tagFile = imagePath.substring(0, imagePath.length() - 3) + "txt";
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream(tagFile);

            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                strLine = strLine.toLowerCase().trim();
                tmp.add(strLine);
            }
            in.close();
        } catch (Exception e) {
        }

        String[] tags = (String[]) tmp.toArray(new String[0]);

        return tags;
    }

    public static void saveImageIndex(String imagePath, String[] index) {
        ImageIndexHolder.getInstance().saveImageIndex(ImageIndexHolder.FeatureType.SemanticTag, imagePath,
                indexToString(index));
    }

    public static String indexToString(String[] index) {
        StringBuilder indexStr = new StringBuilder();// index.toString().replace(", ", " ").replace("[",
                                                     // "").replace("]", "");
        for (int i = 0; i < index.length; i++) {
            indexStr.append(index[i] + " ");
        }
        // System.out.println(indexStr.toString().trim());
        return indexStr.toString().trim();
    }

    public static String[] loadImageIndex(String imagePath) {
        String indexStr = ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.SemanticTag,
                imagePath);
        String[] tags = stringToIndex(indexStr);

        if (tags == null || tags.length == 0) {

            tags = createIndex(imagePath);
            saveImageIndex(imagePath, tags);
        }
        return tags;
    }

    public static double calculateDistance(String[] firstIndex, String[] secondIndex) {

        double score = 0;
        // exact match
        if (indexToString(firstIndex).equalsIgnoreCase(indexToString(secondIndex)))
            score = 0;

        // calculate score for each word and combine the scores
        else {
            int count = firstIndex.length;
            double hitCount = 0;
            for (int j = 0; j < firstIndex.length; j++) {
                String s = firstIndex[j];
                double max = 0;
                for (int i = 0; i < secondIndex.length; i++) {
                    String aTag = (String) secondIndex[i];
                    if (s.equalsIgnoreCase(aTag)) {
                        if (max < 1)
                            max = 1;
                        break;
                    } else if (s.contains(aTag) || aTag.contains(s)) {
                        if (max < 0.6)
                            max = 0.6;
                    } else {
                        if (toList(getSimilarTagsString(s)).contains(aTag)
                                || toList(getSimilarTagsString(aTag)).contains(s)) {
                            if (max < 0.3)
                                max = 0.3;
                        }
                    }
                }
                hitCount += max;

            }

            hitCount = hitCount * 0.9;
            score = 1 - hitCount / count;
        }
        return score;
    }

    // endregion

    // region private methods

    private static String getSimilarTagsString(String query) {
        List<String> tagCategories = Arrays.asList(
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
        for (String tagCategory : tagCategories) {
            String similarTags = ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.SimilarTags,
                    tagCategory);
            if (similarTags.contains(query)) {
                return similarTags;
            }
        }

        return "";
    }

    private static String[] stringToIndex(String indexStr) {
        String[] index = null;
        if (indexStr != null)
            index = indexStr.split(" ");
        return index;
    }

    private static List<String> toList(String tags) {
        List<String> alist = new ArrayList<>();
        if (tags != null) {
            StringTokenizer st = new StringTokenizer(tags);
            while (st.hasMoreTokens()) {
                String s = st.nextToken();
                alist.add(s);
            }
        }
        return alist;
    }

    // endregion
}
