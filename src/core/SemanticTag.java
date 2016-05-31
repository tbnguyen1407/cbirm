package core;

import storage.ImageIndexHolder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SemanticTag
{
    private static HashMap similarTags = new HashMap();


    public static String[] createIndex(String imagePath)
    {
        ArrayList tmp = new ArrayList();
        String tagFile = imagePath.substring(0, imagePath.length() - 3) + "txt";
        FileInputStream fstream = null;
        try
        {
            fstream = new FileInputStream(tagFile);

            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            while ((strLine = br.readLine()) != null)
            {
                strLine = strLine.toLowerCase().trim();
                tmp.add(strLine);
            }
            in.close();
        }
        catch (Exception e)
        {
        }

        String[] tags = (String[]) tmp.toArray(new String[0]);

        return tags;
    }

    public static void saveImageIndex(String imagePath, String[] index)
    {
        ImageIndexHolder.getInstance().saveImageIndex(ImageIndexHolder.FeatureType.SemanticTag, imagePath, indexToString(index));
    }

    public static String indexToString(String[] index)
    {
        StringBuilder indexStr = new StringBuilder();//index.toString().replace(", ", " ").replace("[", "").replace("]", "");
        for (int i = 0; i < index.length; i++)
        {
            indexStr.append(index[i] + " ");
        }
        //System.out.println(indexStr.toString().trim());
        return indexStr.toString().trim();
    }

    public static String[] loadImageIndex(String imagePath)
    {
        String indexStr = ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.SemanticTag, imagePath);
        String[] tags = stringToIndex(indexStr);

        if (tags == null || tags.length == 0)
        {

            tags = createIndex(imagePath);
            saveImageIndex(imagePath, tags);
        }
        return tags;
    }

    private static String[] stringToIndex(String indexStr)
    {
        String[] index = null;
        if (indexStr != null)
            index = indexStr.split(" ");
        return index;
    }

    public static double calculateDistance(String[] firstIndex, String[] secondIndex)
    {

        double score = 0;
        // exact match
        if (indexToString(firstIndex).equalsIgnoreCase(indexToString(secondIndex)))
            score = 0;

            // calculate score for each word and combine the scores
        else
        {
            int count = firstIndex.length;
            double hitCount = 0;
            for (int j = 0; j < firstIndex.length; j++)
            {
                String s = firstIndex[j];
                double max = 0;
                for (int i = 0; i < secondIndex.length; i++)
                {
                    String aTag = (String) secondIndex[i];
                    if (s.equalsIgnoreCase(aTag))
                    {
                        if (max < 1) max = 1;
                        break;
                    }
                    else if (s.contains(aTag) || aTag.contains(s))
                    {
                        if (max<0.6) max=0.6;
                    }
                    else
                    {
                        if (toArrayList(getSimilarTagsString(s)).contains(aTag) || toArrayList(getSimilarTagsString(aTag)).contains(s))
                        {
                            if (max<0.3) max=0.3;
                        }
                    }
                }
                hitCount+=max;

            }

            hitCount = hitCount * 0.9;
            score = 1 - hitCount / count;
        }
        return score;
    }

    public static String getSimilarTagsString(String query)
    {
        String similarTags = (String) ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.SimilarTags, "people");
        if (similarTags.contains(query)) return similarTags;

        similarTags = (String) ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.SimilarTags, "animal");
        if (similarTags.contains(query)) return similarTags;

        similarTags = (String) ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.SimilarTags, "food");
        if (similarTags.contains(query)) return similarTags;

        similarTags = (String) ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.SimilarTags, "vehicle");
        if (similarTags.contains(query)) return similarTags;

        similarTags = (String) ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.SimilarTags, "landscape");
        if (similarTags.contains(query)) return similarTags;

        similarTags = (String) ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.SimilarTags, "party");
        if (similarTags.contains(query)) return similarTags;

        similarTags = (String) ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.SimilarTags, "recreation");
        if (similarTags.contains(query)) return similarTags;

        similarTags = (String) ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.SimilarTags, "plant");
        if (similarTags.contains(query)) return similarTags;

        similarTags = (String) ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.SimilarTags, "sport");
        if (similarTags.contains(query)) return similarTags;

        return "";
    }

    public static ArrayList toArrayList(String tags)
    {
        ArrayList alist = new ArrayList();
        if (tags != null)
        {
            StringTokenizer st = new StringTokenizer(tags);
            while (st.hasMoreTokens())
            {
                String s = st.nextToken();
                alist.add(s);
            }
        }
        return alist;
    }

    {
    }
}
