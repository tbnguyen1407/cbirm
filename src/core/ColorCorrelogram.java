package core;

import storage.ImageIndexHolder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ColorCorrelogram
{
    private static int maxDistance = 3;
    public static double[][] correlogram;
    private static int numBins = 256;
    private static double quantH_f = 8d;
    private static double quantS_f = 4d;
    private static double quantV_f = 2d;

    private static double quantH = 360d / quantH_f;
    private static double quantV = 256d / quantV_f;
    private static double quantS = 256d / quantS_f;

    public ColorCorrelogram(int maxDistance)
    {
        this.maxDistance = maxDistance;
        initialize();
    }

    private void initialize()
    {
        if (numBins < 33)
        {
            quantH_f = 8d;
            quantS_f = 2d;
            quantV_f = 2d;
            numBins = 32;
        }
        else if (numBins < 65)
        {
            quantH_f = 8d;
            quantS_f = 4d;
            quantV_f = 2d;
            numBins = 64;
        }
        else if (numBins < 129)
        {
            quantH_f = 8d;
            quantS_f = 4d;
            quantV_f = 4d;
            numBins = 128;
        }
        else
        {
            quantH_f = 16d;
            quantS_f = 4d;
            quantV_f = 4d;
            numBins = 256;
        }
        quantH = 360d / quantH_f;
        quantS = 256d / quantS_f;
        quantV = 256d / quantV_f;
    }

    public static double[][] createIndex(String imagePath)
    {
        try
        {
            Image img = ImageIO.read(new File(imagePath));
            BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bimage.createGraphics();
            g.drawImage(img, null, null);
            Raster r = bimage.getRaster();

            int[] histogram = new int[numBins];
            for (int i = 0; i < histogram.length; i++)
                histogram[i] = 0;
            int[][] quantPixels = new int[r.getWidth()][r.getHeight()];
            // quantize colors for each pixel (done in HSV color space):
            int[] pixel = new int[3];
            int[] hsv = new int[3];
            for (int x = 0; x < r.getWidth(); x++)
            {
                for (int y = 0; y < r.getHeight(); y++)
                {
                    hsv = convertRgbToHsv(r.getPixel(x, y, pixel));
                    quantPixels[x][y] = quantize(hsv);
                    histogram[quantPixels[x][y]]++;
                }
            }

            correlogram = new double[numBins][maxDistance];
            for (int i1 = 0; i1 < correlogram.length; i1++)
                for (int j = 0; j < correlogram[i1].length; j++)
                    correlogram[i1][j] = 0;

            int[] tmpCorrelogram = new int[maxDistance];
            for (int x = 0; x < r.getWidth(); x++)
            {
                for (int y = 0; y < r.getHeight(); y++)
                {
                    int color = quantPixels[x][y];
                    getNumPixelsInNeighbourhood(x, y, quantPixels, tmpCorrelogram);
                    for (int i = 0; i < maxDistance; i++)
                    {
                        correlogram[color][i] += tmpCorrelogram[i];
                    }
                }
            }
            // normalization
            double[] max = new double[maxDistance];
            for (int i = 0; i < max.length; i++)
                max[i] = 0;
            for (int c = 0; c < numBins; c++)
                for (int i = 0; i < maxDistance; i++)
                    max[i] = Math.max(correlogram[c][i], max[i]);
            for (int c = 0; c < numBins; c++)
                for (int i = 0; i < maxDistance; i++)
                    correlogram[c][i] = correlogram[c][i] / max[i];
        }
        catch (IOException e)
        {
            System.out.println("IOException!");
        }

        return correlogram;
    }

    public static double[][] loadImageIndex(String image)
    {
        String indexStr = ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.ColorCorrelogram, image);
        double[][] indexdoubleArray = null;

        if (indexStr == null)
        { // not exists -> create and save index
            indexdoubleArray = createIndex(image);
            saveImageIndex(image, indexdoubleArray);
        }
        else
        {
            indexdoubleArray = stringToIndex(indexStr);
        }
        return indexdoubleArray;
    }

    public static void saveImageIndex(String image, double[][] index)
    {
        ImageIndexHolder.getInstance().saveImageIndex(ImageIndexHolder.FeatureType.ColorCorrelogram, image, indexToString(index));
    }

    private static String indexToString(double[][] index)
    {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < index.length; i++)
        {
            sb.append(Arrays.toString(index[i]).replace(", ", " ").replace("[", "").replace("]", "") + "|");
        }
        int lastIndex = sb.lastIndexOf("|");

        return sb.toString().substring(0, lastIndex);
    }

    private static double[][] stringToIndex(String indexStr)
    {
        String[] indexStrArray = indexStr.split("\\|");

        double[][] indexdoubleArray = new double[numBins][maxDistance];

        for (int i = 0; i < indexStrArray.length; i++)
        {
            String[] subStrArray = indexStrArray[i].split(" ");
            for (int j = 0; j < subStrArray.length; j++)
                indexdoubleArray[i][j] = Double.parseDouble(subStrArray[j]);
        }
        return indexdoubleArray;
    }

    private static void getNumPixelsInNeighbourhood(int x, int y, int[][] quantPixels, int[] correlogramm)
    {
        // distance 1 -> set 0
        for (int i = 0; i < correlogramm.length; i++)
        {
            correlogramm[i] = 0;
        }
        for (int d = 1; d <= maxDistance; d++)
        {
            if (d > 1) correlogramm[d - 1] += correlogramm[d - 2];
            int color = quantPixels[x][y];

            for (int i = -d; i <= d; i++)
            {
                if (isInPicture(x + i, y + d, quantPixels.length, quantPixels[0].length))
                    if (color == quantPixels[x + i][y + d]) correlogramm[d - 1]++;
                if (isInPicture(x + i, y - d, quantPixels.length, quantPixels[0].length))
                    if (color == quantPixels[x + i][y - d]) correlogramm[d - 1]++;
            }
            for (int i = -d + 1; i <= d - 1; i++)
            {
                if (isInPicture(x + d, y + i, quantPixels.length, quantPixels[0].length))
                    if (color == quantPixels[x + d][y + i]) correlogramm[d - 1]++;
                if (isInPicture(x - d, y + i, quantPixels.length, quantPixels[0].length))
                    if (color == quantPixels[x - d][y + i]) correlogramm[d - 1]++;
            }
        }
    }

    private static boolean isInPicture(int x, int y, int maxX, int maxY)
    {
        boolean inPicture = (!(x < 0 || y < 0) && !(y >= maxY || x >= maxX));
        return inPicture;
    }

    // Quantize a pixel
    private static int quantize(int[] pixel)
    {
        return (int) ((int) (pixel[0] / quantH) * (quantV_f) * (quantS_f)
                + (int) (pixel[1] / quantS) * (quantV_f)
                + (int) (pixel[2] / quantV));
    }

    private static int[] convertRgbToHsv(int[] rgb)
    {
        int[] hsv = new int[3];
        int R = rgb[0];
        int G = rgb[1];
        int B = rgb[2];
        int max, min;
        double hue = 0d;

        max = Math.max(R, G);     //calculation of max(R,G,B)
        max = Math.max(max, B);

        min = Math.min(R, G);     //calculation of min(R,G,B)
        min = Math.min(min, B);

        if (max == 0)
            hsv[1] = 0;
        else
            hsv[1] = (int) (((max - min) / (double) max) * 255d); // Saturation in [0,255]

        if (max == min)
            hue = 0;     // (max - min) = 0
        else
        {
            double maxMinusMin = (double) (max - min);
            if (R == max)
                hue = ((G - B) / maxMinusMin);
            else if (G == max)
                hue = (2 + (B - R) / maxMinusMin);
            else if (B == max)
                hue = (4 + (R - G) / maxMinusMin);
            hue *= 60d;
            if (hue < 0d)
                hue += 360d;
        }
        hsv[0] = (int) (hue); // hue in [0,359]
        hsv[2] = max; // value in [0,255]
        return hsv;
    }

    public static double calculateDistance(double[][] h1, double[][] h2)
    {
        double result = 0.0;

        for (int i = 0; i < h1.length; i++)
        {
            for (int j = 0; j < h1[i].length; j++)
            {
                double v = Math.abs(h1[i][j] - h2[i][j]);
                result += v;
            }

        }
        return result;
    }
}
