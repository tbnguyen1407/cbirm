package core;

import storage.ImageIndexHolder;

import java.util.Arrays;

public class ColorHistogram
{

    public static int[] GetYUVHist(int[][][] DCTMatrix)
    {

        //int yuv = 3; it is the first dimension of array DCTMatrix
        int width = DCTMatrix[0][0].length;//image width(horizontal)
        int height = DCTMatrix[0].length;//image height(vertical)
        int span = 8;//8 x 8 block

        //the 2 parameters below you can modify to scale the dimension of the histogram
        int binNum = 8;//number of bins in each color subspace
        int interval = 258000 / binNum;//the maximum and the minimum 129000 and -129000

        int blknum_h = width / span;
        int blknum_v = height / span;

        int[] hist = new int[binNum * binNum * binNum];
        for (int j = 0; j < blknum_h; j++)
        {
            for (int k = 0; k < blknum_v; k++)
            {
                int binY = DCTMatrix[0][k * span][j * span] / interval + 4;//calculate where the DC coefficient locates in Y subspace
                if (binY >= 8)
                {
                    binY = 7;
                }
                else if (binY <= -1)
                {
                    binY = 0;
                }
                int binU = DCTMatrix[1][k * span][j * span] / interval + 4;//calculate where the DC coefficient locates in U subspace
                if (binU >= 8)
                {
                    binU = 7;
                }
                else if (binU <= -1)
                {
                    binU = 0;
                }
                int binV = DCTMatrix[2][k * span][j * span] / interval + 4;//calculate where the DC coefficient locates in V subspace
                if (binV >= 8)
                {
                    binV = 7;
                }
                else if (binV <= -1)
                {
                    binV = 0;
                }

                hist[binY * binNum * binNum + binU * binNum + binV]++;
                //System.out.println(binY+" "+binU+" "+binV);
            }
        }
        return hist;
    }

    // histogram intersection
    public static double Intersection(int[] h1, int[] h2)
    {
        int norm1 = 0, norm2 = 0;
        for (int i = 0; i < h1.length; i++)
        {
            norm1 += h1[i];
            norm2 += h2[i];
        }

        double intersection = 0.0;

        double[] norm_h1 = new double[h1.length];
        double[] norm_h2 = new double[h1.length];

        //normalize each histogram
        for (int i = 0; i < h1.length; i++)
        {
            norm_h1[i] = (double) h1[i] / (double) norm1;
            norm_h2[i] = (double) h2[i] / (double) norm2;
        }

        for (int i = 0; i < h1.length; i++)
        {
            if (norm_h1[i] > norm_h2[i])
                intersection += norm_h2[i];
            else
                intersection += norm_h1[i];
        }
        return (1 - intersection);
    }

    // histogram matching
    public static double HistMatch(int[] h1, int[] h2)
    {
        int norm1 = 0, norm2 = 0;
        for (int i = 0; i < h1.length; i++)
        {
            norm1 += h1[i];
            norm2 += h2[i];
        }

        double[] norm_h1 = new double[h1.length];
        double[] norm_h2 = new double[h1.length];

        //normalize each histogram
        for (int i = 0; i < h1.length; i++)
        {

            norm_h1[i] = (double) h1[i] / (double) norm1;
            norm_h2[i] = (double) h2[i] / (double) norm2;
        }

        //calculate NSim
        double[] NSim = new double[h1.length];
        for (int i = 0; i < h1.length; i++)
        {
            if (norm_h1[i] == 0 && norm_h2[i] == 0)
            {
                NSim[i] = 0;
            }
            else
            {
                NSim[i] = norm_h1[i] * (1 - Math.abs(norm_h1[i] - norm_h2[i]) / Math.max(norm_h1[i], norm_h2[i]));
            }
        }
        //calculate Sim
        double sim = 0;
        for (int i = 0; i < h1.length; i++)
        {
            sim = sim + NSim[i];
            for (int j = 0; j < h1.length; j++)
            {
                if (i != j) //System.out.println(i+" "+j+" "+ColorSim(i,j)+" "+ColorSim(i,j)*NSim[j]);
                {
                    sim = sim + ColorSim(i, j) * NSim[j];
                }
            }
        }
        return sim;
    }

    //Calculate Color Similarity
    public static double ColorSim(int i, int j)
    {
        double colorSim = 0;
        int yi, ui, vi, yj, uj, vj;
        yi = (i / 64);
        ui = ((i % 64) / 8);
        vi = ((i % 64) % 8);

        yj = (j / 64);
        uj = ((j % 64) / 8);
        vj = ((j % 64) % 8);

        double nyi, nui, nvi, nyj, nuj, nvj;
        nyi = (double) yi / 8;
        nui = (double) ui / 8;
        nvi = (double) vi / 8;
        nyj = (double) yj / 8;
        nuj = (double) uj / 8;
        nvj = (double) vj / 8;

        double diff = Math.sqrt((double) Math.pow((nyi - nyj), 2) + Math.pow((nui - nuj), 2) + Math.pow((nvi - nvj), 2));
        String s = " ";

        double t = 0.130;
        if (diff > t)
        {
            colorSim = 0;
        }
        else
        {
            colorSim = 1 - diff / t;
        }

        return colorSim;
    }

    public static int[] loadImageIndex(String image)
    {
        String indexStr = ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.ColorHistogram, image);
        int[] indexIntArray;

        if (indexStr == null)
        {
            indexIntArray = createIndex(image);
            saveImageIndex(image, indexIntArray);
        }
        else
        {
            indexIntArray = stringToIndex(indexStr);
        }

        return indexIntArray;
    }

    public static void saveImageIndex(String image, int[] index)
    {
        ImageIndexHolder.getInstance().saveImageIndex(ImageIndexHolder.FeatureType.ColorHistogram, image, indexToString(index));
    }

    public static int[] createIndex(String image)
    {
        return ColorHistogram.GetYUVHist((new DCT(image)).DCTmatrix);
    }

    private static String indexToString(int[] index)
    {
        return Arrays.toString(index).replace(", ", " ").replace("[", "").replace("]", "");
    }

    private static int[] stringToIndex(String indexStr)
    {
        String[] indexStrArray = indexStr.split(" ");
        int[] indexIntArray = new int[indexStrArray.length];

        for (int i = 0; i < indexStrArray.length; i++)
            indexIntArray[i] = Integer.parseInt(indexStrArray[i]);

        return indexIntArray;
    }
}
