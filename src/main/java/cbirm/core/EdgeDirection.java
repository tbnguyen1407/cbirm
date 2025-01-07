package cbirm.core;

import java.util.Arrays;

import cbirm.storage.ImageIndexHolder;

public class EdgeDirection extends EdgeDirectionImplementation {
    // region constructors

    public EdgeDirection(String image) {
        super(image);
    }

    // endregion

    // region public methods

    public static int[] createIndex(String image) {
        EdgeDirection eh = new EdgeDirection(image);
        return eh.GlobalEdgeHistogram;
    }

    public static int[] loadImageIndex(String image) {
        String indexStr = ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.EdgeDirection,
                image);
        int[] indexIntArray;

        if (indexStr == null) { // not exists -> create and save index
            indexIntArray = createIndex(image);
            saveImageIndex(image, indexIntArray);
        } else {
            indexIntArray = stringToIndex(indexStr);
        }

        return indexIntArray;
    }

    public static void saveImageIndex(String image, int[] index) {
        ImageIndexHolder.getInstance().saveImageIndex(ImageIndexHolder.FeatureType.EdgeDirection, image,
                indexToString(index));
    }

    // endregion

    // region private methods

    private static String indexToString(int[] index) {
        return Arrays.toString(index).replace(", ", " ").replace("[", "").replace("]", "");
    }

    private static int[] stringToIndex(String indexStr) {
        String[] indexStrArray = indexStr.split(" ");
        int[] indexIntArray = new int[indexStrArray.length];

        for (int i = 0; i < indexStrArray.length; i++)
            indexIntArray[i] = Integer.parseInt(indexStrArray[i]);

        return indexIntArray;
    }

    // endregion
}
