package cbirm.core;

import java.util.Arrays;

import cbirm.storage.ImageIndexHolder;

public class ColorCoherenceVector {
    // region public methods

    public static int[][] createIndex(String image) {
        int[][][] dctMatrix = (new DCT(image)).getMatrix();

        int value, value1 = 0, value2 = 0, value3 = 0, value4 = 0, value5 = 0, value6 = 0, value7 = 0, value8 = 0;
        int similar = 0;

        int width = dctMatrix[0][0].length;// image width(horizontal)
        int height = dctMatrix[0].length;// image height(vertical)
        int span = 8;// 8 x 8 block

        // the 2 parameters below you can modify to scale the dimension of the histogram
        int binNum = 8;// number of bins in each color subspace
        int interval = 258000 / binNum;// the maximum and the minimum 129000 and -129000

        int blknum_h = width / span;
        int blknum_v = height / span;

        int a = 0;
        int[][] adjacenttable = new int[blknum_h][blknum_v];
        int[][] comparetable = new int[2][512];

        for (int j = 0; j < blknum_h; j++) {
            for (int k = 0; k < blknum_v; k++) {
                int binY = dctMatrix[0][k * span][j * span] / interval + 4;// calculate where the DC coefficient locates
                                                                           // in Y subspace
                int binU = dctMatrix[1][k * span][j * span] / interval + 4;// calculate where the DC coefficient locates
                                                                           // in U subspace
                int binV = dctMatrix[2][k * span][j * span] / interval + 4;// calculate where the DC coefficient locates
                                                                           // in V subspace
                adjacenttable[j][k] = binY + binU + binV;
            }
        }

        for (int i = 0; i < blknum_h; i++) {
            for (int j = 0; j < blknum_v; j++) {

                value = adjacenttable[i][j];

                if (i - 1 >= 0 && j - 1 >= 0) // top left
                    value1 = adjacenttable[i - 1][j - 1];
                if (i - 1 >= 0) // top middle
                    value2 = adjacenttable[i - 1][j];
                if (i - 1 >= 0 && j + 1 <= blknum_v - 1) // top right
                    value3 = adjacenttable[i - 1][j + 1];
                if (j - 1 >= 0) // middle left
                    value4 = adjacenttable[i][j - 1];
                if (j + 1 <= blknum_v - 1) // middle right
                    value5 = adjacenttable[i][j + 1];
                if (i + 1 <= blknum_h - 1 && j - 1 >= 0) // bottom left
                    value6 = adjacenttable[i + 1][j - 1];
                if (i + 1 <= blknum_h - 1) // bottom middle
                    value7 = adjacenttable[i + 1][j];
                if (i + 1 <= blknum_h - 1 && j + 1 <= blknum_v - 1)
                    value8 = adjacenttable[i + 1][j + 1];

                if (value1 != 0 && value == value1)
                    similar++;
                if (value2 != 0 && value == value2)
                    similar++;
                if (value3 != 0 && value == value3)
                    similar++;
                if (value4 != 0 && value == value4)
                    similar++;
                if (value5 != 0 && value == value5)
                    similar++;
                if (value6 != 0 && value == value6)
                    similar++;
                if (value7 != 0 && value == value7)
                    similar++;
                if (value8 != 0 && value == value8)
                    similar++;

                if (similar >= 2) {
                    comparetable[0][value]++;
                    similar = 0;
                } else {
                    comparetable[1][value]++;
                    similar = 0;
                }
            }
        }
        return comparetable;
    }

    public static double calculateDistance(int[][] h1, int[][] h2) {
        int totalDifferentCluster = 0;
        int totalPixelsCluster = 0;
        int totalDifferentIsolated = 0;
        int totalPixelsIsolated = 0;
        double distance;

        for (int i = 0; i < 512; i++) {
            int firstValue = h1[0][i];
            int secondValue = h2[0][i];
            totalDifferentCluster = totalDifferentCluster + Math.abs(firstValue - secondValue);
            totalPixelsCluster = totalPixelsCluster + firstValue + secondValue;
        }

        for (int j = 0; j < 512; j++) {
            int firstValue = h1[1][j];
            int secondValue = h2[1][j];
            totalDifferentIsolated = totalDifferentIsolated + Math.abs(firstValue - secondValue);
            totalPixelsIsolated = totalPixelsIsolated + firstValue + secondValue;
        }

        if (totalPixelsCluster == 0 || totalPixelsIsolated == 0) {
            distance = 0.0;
        } else {
            distance = 0.8 * ((double) totalDifferentCluster / (double) totalPixelsCluster) +
                    0.2 * ((double) totalDifferentIsolated / (double) totalPixelsIsolated);
        }
        return distance;
    }

    public static int[][] loadImageIndex(String image) {
        String indexStr = ImageIndexHolder.getInstance()
                .loadImageIndex(ImageIndexHolder.FeatureType.ColorCoherenceVector, image);
        int[][] indexIntArray = null;

        if (indexStr == null) { // not exists -> create and save index
            indexIntArray = createIndex(image);
            saveImageIndex(image, indexIntArray);
        } else {
            indexIntArray = stringToIndex(indexStr);
        }

        return indexIntArray;
    }

    public static void saveImageIndex(String image, int[][] index) {
        ImageIndexHolder.getInstance().saveImageIndex(ImageIndexHolder.FeatureType.ColorCoherenceVector, image,
                indexToString(index));
    }

    // endregion

    // region private methods

    private static String indexToString(int[][] index) {
        StringBuilder sb = new StringBuilder();

        sb.append(Arrays.toString(index[0]).replace(", ", " ").replace("[", "").replace("]", "")
                + "-"
                + Arrays.toString(index[1]).replace(", ", " ").replace("[", "").replace("]", ""));

        return sb.toString();
    }

    private static int[][] stringToIndex(String indexStr) {
        String[] indexStrArray = indexStr.split("-");
        int[][] indexIntArray = new int[2][512];

        for (int i = 0; i < indexStrArray.length; i++) {
            String[] subStrArray = indexStrArray[i].split(" ");
            for (int j = 0; j < subStrArray.length; j++)
                indexIntArray[i][j] = Integer.parseInt(subStrArray[j]);
        }
        return indexIntArray;
    }

    // endregion
}
