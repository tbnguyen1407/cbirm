package core;

import helpers.DataStructureOperator;
import helpers.FileSystemOperator;
import storage.ImageIndexHolder;

import java.util.*;

public class ImageMatching
{
    private static final double THRESHOLD = 0.55;
    private static final double weightColorHistogram = 0.1;
    private static final double weightColorCoherenceVector = 0.2;
    private static final double weightColorCorrelogram = 0.2;
    private static final double weightEdgeDirection = 0.5;
    private static final double weightSemanticTag = 0.6;

    public static void testImageMatching()
    {
        String dataFolder = "D:\\Temporary\\images\\";
        String queryImage = dataFolder + "1_bankok.jpg";
        String queryImage2 = dataFolder + "1_DSC00654.JPG";
        String[] fileDatabase = FileSystemOperator.getListOfFiles(dataFolder);
        boolean[] featureVector = new boolean[]{true, true, true, true, false};
        String keywords = "sunset landscape catalonia barcelona airplane airport";

        System.out.println("TESTS START HERE\n");

        System.out.println("DEBUG: Intializing storage...\n");
        ImageIndexHolder.getInstance().initializeStorage();

        System.out.println("=== USING COLOR HISTOGRAM FEATURE ===\n");
        HashMap CHQueryResult = searchColorHistogram(queryImage, fileDatabase);

        System.out.println("=== USING COLORCOHERENCEVECTOR FEATURE ===\n");
        HashMap CCVQueryResult = searchColorCoherenceVector(queryImage, fileDatabase);

        System.out.println("=== USING COLORCORRELOGRAM FEATURE ===\n");
        HashMap CCQueryResult = searchColorCorrelogram(queryImage, fileDatabase);

        System.out.println("=== USING EDGE FEATURE ===\n");
        HashMap EFQueryResult = searchEdgeDirection(queryImage, fileDatabase);

        System.out.println("=== USING DATETIME FEATURE ===\n");
        ArrayList<String> DateTimeResult = searchDateTime("2009", fileDatabase);

        System.out.println("=== USING LOCATION FEATURE ===\n");
        ArrayList<String> LocationResult = searchLocation("Singapore", fileDatabase);

        System.out.println("=== USING SEMANTICTAGS FEATURE ===\n");
        HashMap SemanticTagResult = searchSemanticTag(keywords, fileDatabase);

/*
        System.out.println("=== USING FEATURE FUSION ===\n");
        searchFeatureFusion(queryImage, null, fileDatabase, featureVector);

        System.out.println("=== USING RELEVANT FEEDBACK ===\n");
        ArrayList<String> result = relevantFeedbackHandler(queryImage, queryImage2, keywords, fileDatabase, featureVector);
*/

        System.out.println("DEBUG: Finalizing storage...\n");
        ImageIndexHolder.getInstance().finalizeStorage();

        System.out.println("TESTS END HERE\n");
    }

    public static ArrayList<String> search(String queryImage, String keywords, String[] fileDatabase, boolean[] featureVector)
    {
        ArrayList<Map.Entry> resultSortedMapEntryList = searchFeatureFusion(queryImage, keywords, fileDatabase, featureVector);
        ArrayList<String> resultStringList = new ArrayList<String>();

        // Keep only values smaller than THRESHOLD
        for (int i = 0; i < resultSortedMapEntryList.size(); i++)
        {
            String curFilePath = resultSortedMapEntryList.get(i).getKey().toString();
            double curValue = Double.parseDouble(resultSortedMapEntryList.get(i).getValue().toString());
            if (curValue > THRESHOLD)
                break;
            resultStringList.add(curFilePath);
        }

        return resultStringList;
    }

    public static ArrayList<String> relevantFeedbackHandler(String queryImage, String feedbackImage, String keywords, String[] fileDatabase, boolean[] featureVector)
    {
        HashMap resultTable = new HashMap();
        ArrayList<String> resultStringList = new ArrayList<String>();

        ArrayList<Map.Entry> resultTableQuery = searchFeatureFusion(queryImage, keywords, fileDatabase, featureVector);
        ArrayList<Map.Entry> resultTableFeedback = searchFeatureFusion(feedbackImage, null, fileDatabase, new boolean[]{true, true, true, true, false});

        // Combine 2 results
        for (int i = 0; i < resultTableQuery.size(); i++)
        {
            String curFilePath = resultTableQuery.get(i).getKey().toString();
            double curQueryValue = (Double) resultTableQuery.get(i).getValue();
            double curFeedbackValue = (Double) resultTableFeedback.get(i).getValue();
            resultTable.put(curFilePath, (curQueryValue + curFeedbackValue) / 2);
        }

        resultTable = DataStructureOperator.normalizeHashMap(resultTable);

        // Sort the result
        ArrayList<Map.Entry> resultMapEntryList = new ArrayList<Map.Entry>(resultTable.entrySet());

        Collections.sort(resultMapEntryList, new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        // Keep only values smaller than THRESHOLD
        for (int i = 0; i < resultMapEntryList.size(); i++)
        {
            String curFilePath = resultMapEntryList.get(i).getKey().toString();
            double curValue = Double.parseDouble(resultMapEntryList.get(i).getValue().toString());
            if (curValue > THRESHOLD)
                break;
            resultStringList.add(curFilePath);
        }

        return resultStringList;
    }

    public static ArrayList<Map.Entry> searchFeatureFusion(String queryImage, String keywords, String[] fileDatabase, boolean[] featureVector)
    {
        HashMap resultColorHistogram = new HashMap();
        HashMap resultColorCoherenceVector = new HashMap();
        HashMap resultColorCorrelogram = new HashMap();
        HashMap resultEdgeDirection = new HashMap();
        HashMap resultSemanticTag = new HashMap();

        HashMap resultTable = new HashMap();

        // Populate corresponding hashmaps
        if (featureVector[0])
            resultColorHistogram = (new ColorHistogramSearcher(fileDatabase)).queryImage(queryImage);
        if (featureVector[1])
            resultColorCoherenceVector = (new ColorCoherenceVectorSearcher(fileDatabase)).queryImage(queryImage);
        if (featureVector[2])
            resultColorCorrelogram = (new ColorCorrelogramSearcher(fileDatabase)).queryImage(queryImage);
        if (featureVector[3])
            resultEdgeDirection = (new EdgeDirectionSearcher(fileDatabase)).queryImage(queryImage);
        if (featureVector[4])
            resultSemanticTag = new SemanticTagSearcher(fileDatabase).queryImage(keywords);

        for (String filePath : fileDatabase)
        {
            double combinedPoint = 0;
            if (featureVector[0])
                combinedPoint += (Double) resultColorHistogram.get(filePath) * weightColorHistogram;
            if (featureVector[1])
                combinedPoint += (Double) resultColorCoherenceVector.get(filePath) * weightColorCoherenceVector;
            if (featureVector[2])
                combinedPoint += (Double) resultColorCorrelogram.get(filePath) * weightColorCorrelogram;
            if (featureVector[3])
                combinedPoint += (Double) resultEdgeDirection.get(filePath) * weightEdgeDirection;
            if (featureVector[4])
                combinedPoint += (Double) resultSemanticTag.get(filePath) * weightSemanticTag;

            resultTable.put(filePath, combinedPoint);
        }

        resultTable = DataStructureOperator.normalizeHashMap(resultTable);

        // Sort the result
        ArrayList<Map.Entry> resultMapEntryList = new ArrayList<Map.Entry>(resultTable.entrySet());

        Collections.sort(resultMapEntryList, new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        return resultMapEntryList;
    }

    public static ArrayList<String> searchDateTime(String date, String[] fileDatabase)
    {
        DateTimeSearcher dateTimeSearcher = new DateTimeSearcher(fileDatabase);
        HashMap resultDateTime = dateTimeSearcher.queryImage(date);
        ArrayList<String> result = new ArrayList<String>(resultDateTime.keySet());
        return result;
    }

    public static ArrayList<String> searchLocation(String location, String[] fileDatabase)
    {
        LocationSearcher locationSearcher = new LocationSearcher(fileDatabase);
        return locationSearcher.queryImage(location);
    }

    public static ArrayList<String> searchExactSemanticTag(String queryTag, String[] fileDatabase)
    {
        SemanticTagSearcher semantictagSearcher = new SemanticTagSearcher(fileDatabase);
        return semantictagSearcher.queryExactTag(queryTag);
    }

    //
    // Private helpers
    //

    private static HashMap searchColorHistogram(String queryImage, String[] fileDatabase)
    {
        ColorHistogramSearcher colorhistogramSearcher = new ColorHistogramSearcher(fileDatabase);
        return DataStructureOperator.normalizeHashMap(colorhistogramSearcher.queryImage(queryImage));
    }

    private static HashMap searchColorCoherenceVector(String queryImage, String[] fileDatabase)
    {
        ColorCoherenceVectorSearcher colorcoherencevectorSearcher = new ColorCoherenceVectorSearcher(fileDatabase);
        return DataStructureOperator.normalizeHashMap(colorcoherencevectorSearcher.queryImage(queryImage));
    }

    private static HashMap searchColorCorrelogram(String queryImage, String[] fileDatabase)
    {
        ColorCorrelogramSearcher colorcorrelogramSearcher = new ColorCorrelogramSearcher(fileDatabase);
        return DataStructureOperator.normalizeHashMap(colorcorrelogramSearcher.queryImage(queryImage));
    }

    private static HashMap searchEdgeDirection(String queryImage, String[] fileDatabase)
    {
        EdgeDirectionSearcher edgedirectionSearcher = new EdgeDirectionSearcher(fileDatabase);
        return DataStructureOperator.normalizeHashMap(edgedirectionSearcher.queryImage(queryImage));
    }

    private static HashMap searchSemanticTag(String queryIndex, String[] fileDatabase)
    {
        SemanticTagSearcher semantictagSearcher = new SemanticTagSearcher(fileDatabase);
        return DataStructureOperator.normalizeHashMap(semantictagSearcher.queryImage(queryIndex));
    }

}
