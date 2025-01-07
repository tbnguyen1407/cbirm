package cbirm.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cbirm.helpers.DataStructureOperator;
import cbirm.helpers.FileSystemOperator;
import cbirm.storage.ImageIndexHolder;

public class ImageMatching {
    // region fields

    private static final double THRESHOLD = 0.55;
    private static final double weightColorHistogram = 0.1;
    private static final double weightColorCoherenceVector = 0.2;
    private static final double weightColorCorrelogram = 0.2;
    private static final double weightEdgeDirection = 0.5;
    private static final double weightSemanticTag = 0.6;

    // endregion

    // region public methods

    public static void testImageMatching() {
        String dataFolder = "D:\\Temporary\\images\\";
        String queryImage = dataFolder + "1_bankok.jpg";
        String queryImage2 = dataFolder + "1_DSC00654.JPG";
        String[] fileDatabase = FileSystemOperator.getListOfFiles(dataFolder);
        boolean[] featureVector = new boolean[] { true, true, true, true, false };
        String keywords = "sunset landscape catalonia barcelona airplane airport";

        System.out.println("TESTS STARTED");

        System.out.println("DEBUG: Intializing storage...");
        ImageIndexHolder.getInstance().initializeStorage();

        System.out.println("=== USING COLOR HISTOGRAM FEATURE ===");
        Map<String, Double> CHQueryResult = searchColorHistogram(queryImage, fileDatabase);
        System.out.println(CHQueryResult);

        System.out.println("=== USING COLORCOHERENCEVECTOR FEATURE ===");
        Map<String, Double> CCVQueryResult = searchColorCoherenceVector(queryImage, fileDatabase);
        System.out.println(CCVQueryResult);

        System.out.println("=== USING COLORCORRELOGRAM FEATURE ===");
        Map<String, Double> CCQueryResult = searchColorCorrelogram(queryImage, fileDatabase);
        System.out.println(CCQueryResult);

        System.out.println("=== USING EDGE FEATURE ===");
        Map<String, Double> EFQueryResult = searchEdgeDirection(queryImage, fileDatabase);
        System.out.println(EFQueryResult);

        System.out.println("=== USING DATETIME FEATURE ===");
        List<String> DateTimeResult = searchDateTime("2009", fileDatabase);
        System.out.println(DateTimeResult);

        System.out.println("=== USING LOCATION FEATURE ===");
        List<String> LocationResult = searchLocation("Singapore", fileDatabase);
        System.out.println(LocationResult);

        System.out.println("=== USING SEMANTICTAGS FEATURE ===");
        Map<String, Double> SemanticTagResult = searchSemanticTag(keywords, fileDatabase);
        System.out.println(SemanticTagResult);

        System.out.println("=== USING FEATURE FUSION ===");
        List<Map.Entry<String, Double>> featureFusionResult = searchFeatureFusion(queryImage, null, fileDatabase,
                featureVector);
        System.out.println(featureFusionResult);

        System.out.println("=== USING RELEVANT FEEDBACK ===");
        List<String> relevantFeedbackResult = relevantFeedbackHandler(queryImage, queryImage2, keywords, fileDatabase,
                featureVector);
        System.out.println(relevantFeedbackResult);

        System.out.println("DEBUG: Finalizing storage...");
        ImageIndexHolder.getInstance().finalizeStorage();

        System.out.println("TESTS ENDED");
    }

    public static List<String> search(String queryImage, String keywords, String[] fileDatabase,
            boolean[] featureVector) {
        List<Map.Entry<String, Double>> sortedResultList = searchFeatureFusion(queryImage, keywords, fileDatabase,
                featureVector);
        List<String> resultStringList = new ArrayList<>();

        // Keep only values smaller than THRESHOLD
        for (int i = 0; i < sortedResultList.size(); i++) {
            if (sortedResultList.get(i).getValue() > THRESHOLD) {
                break;
            }

            resultStringList.add(sortedResultList.get(i).getKey());
        }

        return resultStringList;
    }

    public static List<String> relevantFeedbackHandler(String queryImage, String feedbackImage, String keywords,
            String[] fileDatabase, boolean[] featureVector) {
        Map<String, Double> resultTable = new HashMap<>();
        List<String> resultStringList = new ArrayList<>();

        List<Map.Entry<String, Double>> resultTableQuery = searchFeatureFusion(queryImage, keywords, fileDatabase,
                featureVector);
        List<Map.Entry<String, Double>> resultTableFeedback = searchFeatureFusion(feedbackImage, null, fileDatabase,
                new boolean[] { true, true, true, true, false });

        // Combine 2 results
        for (int i = 0; i < resultTableQuery.size(); i++) {
            String curFilePath = resultTableQuery.get(i).getKey().toString();
            double curQueryValue = resultTableQuery.get(i).getValue();
            double curFeedbackValue = resultTableFeedback.get(i).getValue();
            resultTable.put(curFilePath, (curQueryValue + curFeedbackValue) / 2);
        }

        resultTable = DataStructureOperator.normalizeHashMap(resultTable);

        // Sort the result
        List<Map.Entry<String, Double>> sortedResultList = resultTable
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());

        // Keep only values smaller than THRESHOLD
        for (int i = 0; i < sortedResultList.size(); i++) {
            String curFilePath = sortedResultList.get(i).getKey().toString();
            double curValue = Double.parseDouble(sortedResultList.get(i).getValue().toString());
            if (curValue > THRESHOLD)
                break;
            resultStringList.add(curFilePath);
        }

        return resultStringList;
    }

    public static List<Map.Entry<String, Double>> searchFeatureFusion(String queryImage, String keywords,
            String[] fileDatabase,
            boolean[] featureVector) {
        Map<String, Double> mResultColorHistogram = new HashMap<>();
        Map<String, Double> mResultColorCoherenceVector = new HashMap<>();
        Map<String, Double> mResultColorCorrelogram = new HashMap<>();
        Map<String, Double> mResultEdgeDirection = new HashMap<>();
        Map<String, Double> mResultSemanticTag = new HashMap<>();

        Map<String, Double> mResultCombined = new HashMap<>();

        // Populate corresponding map
        if (featureVector[0])
            mResultColorHistogram = (new ColorHistogramSearcher(fileDatabase)).queryImage(queryImage);
        if (featureVector[1])
            mResultColorCoherenceVector = (new ColorCoherenceVectorSearcher(fileDatabase)).queryImage(queryImage);
        if (featureVector[2])
            mResultColorCorrelogram = (new ColorCorrelogramSearcher(fileDatabase)).queryImage(queryImage);
        if (featureVector[3])
            mResultEdgeDirection = (new EdgeDirectionSearcher(fileDatabase)).queryImage(queryImage);
        if (featureVector[4])
            mResultSemanticTag = new SemanticTagSearcher(fileDatabase).queryImage(keywords);

        for (String filePath : fileDatabase) {
            double combinedPoint = 0;
            if (featureVector[0])
                combinedPoint += mResultColorHistogram.get(filePath) * weightColorHistogram;
            if (featureVector[1])
                combinedPoint += mResultColorCoherenceVector.get(filePath) * weightColorCoherenceVector;
            if (featureVector[2])
                combinedPoint += mResultColorCorrelogram.get(filePath) * weightColorCorrelogram;
            if (featureVector[3])
                combinedPoint += mResultEdgeDirection.get(filePath) * weightEdgeDirection;
            if (featureVector[4])
                combinedPoint += mResultSemanticTag.get(filePath) * weightSemanticTag;

            mResultCombined.put(filePath, combinedPoint);
        }

        mResultCombined = DataStructureOperator.normalizeHashMap(mResultCombined);

        // Sort the result
        List<Map.Entry<String, Double>> sortedResultCombined = mResultCombined
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        return sortedResultCombined;
    }

    public static List<String> searchDateTime(String date, String[] fileDatabase) {
        var searcher = new DateTimeSearcher(fileDatabase);
        Map<String, String> resultDateTime = searcher.queryImage(date);
        List<String> result = new ArrayList<>(resultDateTime.keySet());
        return result;
    }

    public static List<String> searchLocation(String location, String[] fileDatabase) {
        var searcher = new LocationSearcher(fileDatabase);
        List<String> result = searcher.queryImage(location);
        return result;
    }

    public static List<String> searchExactSemanticTag(String queryTag, String[] fileDatabase) {
        var searcher = new SemanticTagSearcher(fileDatabase);
        List<String> result = searcher.queryExactTag(queryTag);
        return result;
    }

    // endregion

    // region private methods

    private static Map<String, Double> searchColorHistogram(String queryImage, String[] fileDatabase) {
        var searcher = new ColorHistogramSearcher(fileDatabase);
        Map<String, Double> result = searcher.queryImage(queryImage);
        Map<String, Double> normalizedResult = DataStructureOperator.normalizeHashMap(result);
        return normalizedResult;
    }

    private static Map<String, Double> searchColorCoherenceVector(String queryImage, String[] fileDatabase) {
        var searcher = new ColorCoherenceVectorSearcher(fileDatabase);
        Map<String, Double> result = searcher.queryImage(queryImage);
        Map<String, Double> normalizedResult = DataStructureOperator.normalizeHashMap(result);
        return normalizedResult;
    }

    private static Map<String, Double> searchColorCorrelogram(String queryImage, String[] fileDatabase) {
        var searcher = new ColorCorrelogramSearcher(fileDatabase);
        Map<String, Double> result = searcher.queryImage(queryImage);
        Map<String, Double> normalizedResult = DataStructureOperator.normalizeHashMap(result);
        return normalizedResult;
    }

    private static Map<String, Double> searchEdgeDirection(String queryImage, String[] fileDatabase) {
        var searcher = new EdgeDirectionSearcher(fileDatabase);
        Map<String, Double> result = searcher.queryImage(queryImage);
        Map<String, Double> normalizedResult = DataStructureOperator.normalizeHashMap(result);
        return normalizedResult;
    }

    private static Map<String, Double> searchSemanticTag(String queryTagString, String[] fileDatabase) {
        var searcher = new SemanticTagSearcher(fileDatabase);
        Map<String, Double> result = searcher.queryImage(queryTagString);
        Map<String, Double> normalizedResult = DataStructureOperator.normalizeHashMap(result);
        return normalizedResult;
    }

    // endregion
}
