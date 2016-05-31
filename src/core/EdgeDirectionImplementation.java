package core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EdgeDirectionImplementation
{
    public static final int BIN_COUNT = 80;
    private int[] bins = new int[BIN_COUNT];
    private int threshold = 11;
    private double width;
    private double height;
    private int num_block = 1100;

    private static final int NoEdge = 0;
    private static final int VerticalEdge = 1;
    private static final int HorizontalEdge = 2;
    private static final int NonDirectionalEdge = 3;
    private static final int Diagonal45Edge = 4;
    private static final int Diagonal135Edge = 5;

    // Grey level matrix converting from RGB.
    private double[][] grey_level;

    /**
     * The bins have to be quantized with help of this quantization table.
     * The first row is used for the vertical bins,
     * the second for the horizontal bins,
     * the third for the 45 degree bins,
     * the fourth for the 135 degree and the last for the non-directional edges.
     */

    private static double[][] QuantTable = {
            {0.010867, 0.057915, 0.099526, 0.144849, 0.195573, 0.260504, 0.358031, 0.530128}, // VerticalEdge
            {0.012266, 0.069934, 0.125879, 0.182307, 0.243396, 0.314563, 0.411728, 0.564319}, // HorizontalEdge
            {0.004193, 0.025852, 0.046860, 0.068519, 0.093286, 0.123490, 0.161505, 0.228960}, // Diagonal45Edge
            {0.004174, 0.025924, 0.046232, 0.067163, 0.089655, 0.115391, 0.151904, 0.217745}, // Diagonal135Edge
            {0.006778, 0.051667, 0.108650, 0.166257, 0.224226, 0.285691, 0.356375, 0.450972}
    };

    // Local edge histogram of the bins before quantization
    private double LocalEdgeHistogram[] = new double[BIN_COUNT];
    private int blockSize = -1;
    private BufferedImage image;
    // Global edge histogram of the whole image
    public int[] GlobalEdgeHistogram;

    public EdgeDirectionImplementation(String imagePath)
    {
        Image img = null;

        try
        {
            img = ImageIO.read(new File(imagePath));
        }
        catch (IOException e)
        {
        }

        this.image = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.drawImage(img, null, null);

        width = image.getWidth();
        height = image.getHeight();

        extractFeature();
        GlobalEdgeHistogram = setEdgeHistogram();
    }

    public EdgeDirectionImplementation()
    {
    }

    // combine edge information of sub-images and construct the local edge histogram (80 bins)

    public void extractFeature()
    {
        makeGreyLevel();
        int sub_local_index = 0;
        int EdgeTypeOfBlock = 0;
        int[] count_local = new int[16];

        for (int i = 0; i < 16; i++)
            count_local[i] = 0;

        for (int j = 0; j <= height - getblockSize(); j += getblockSize())
            for (int i = 0; i <= width - getblockSize(); i += getblockSize())
            {
                sub_local_index = (int) ((i << 2) / width) + ((int) ((j << 2) / height) << 2);
                count_local[sub_local_index]++;

                EdgeTypeOfBlock = getEdgeFeature(i, j);

                switch (EdgeTypeOfBlock)
                {
                    case EdgeDirectionImplementation.NoEdge:
                        break;
                    case EdgeDirectionImplementation.VerticalEdge:
                        LocalEdgeHistogram[sub_local_index * 5]++;
                        break;
                    case EdgeDirectionImplementation.HorizontalEdge:
                        LocalEdgeHistogram[sub_local_index * 5 + 1]++;
                        break;
                    case EdgeDirectionImplementation.Diagonal45Edge:
                        LocalEdgeHistogram[sub_local_index * 5 + 2]++;
                        break;
                    case EdgeDirectionImplementation.Diagonal135Edge:
                        LocalEdgeHistogram[sub_local_index * 5 + 3]++;
                        break;
                    case EdgeDirectionImplementation.NonDirectionalEdge:
                        LocalEdgeHistogram[sub_local_index * 5 + 4]++;
                        break;
                }

            }
        for (int k = 0; k < BIN_COUNT; k++)
        {
            LocalEdgeHistogram[k] /= count_local[(int) k / 5];
        }
    }

    // Construct 5 quantized edge histograms for 5 edge types

    public int[] setEdgeHistogram()
    {
        int Edge_HistogramElement[] = new int[BIN_COUNT];
        double iQuantValue = 0;
        double value[] = LocalEdgeHistogram;

        for (int i = 0; i < BIN_COUNT; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                bins[i] = j;
                if (j < 7)
                    iQuantValue = (EdgeDirectionImplementation.QuantTable[i % 5][j] + EdgeDirectionImplementation.QuantTable[i % 5][j + 1]) / 2.0;
                else
                    iQuantValue = 1.0;
                if (value[i] <= iQuantValue)
                    break;
            }
        }
        return bins;
    }

    // Distance between 2 edge histograms of 2 images
    // return distance [0..480]

    public static float calculateDistance(int[] edgeHistogramA, int[] edgeHistogramB)
    {
        if (edgeHistogramA == null) System.err.println("Input edgeHistogram a is null!");
        if (edgeHistogramB == null) System.err.println("Input edgeHistogram b is null!");

        double result = 0f;

        for (int i = 0; i < edgeHistogramA.length; i++)
            result += Math.abs((float) EdgeDirectionImplementation.QuantTable[i % 5][edgeHistogramA[i]] - (float) EdgeDirectionImplementation.QuantTable[i % 5][edgeHistogramB[i]]);
        for (int i = 0; i <= 4; i++)
            result += 5f * Math.abs((float) edgeHistogramA[i] - (float) edgeHistogramB[i]);
        for (int i = 5; i < BIN_COUNT; i++)
            result += Math.abs((float) edgeHistogramA[i] - (float) edgeHistogramB[i]);
        return (float) result;
    }

    // Get size of size of the image block

    private int getblockSize()
    {
        if (blockSize < 0)
        {
            double a = (int) (Math.sqrt((width * height) / num_block));
            blockSize = (int) (Math.floor((a / 2)) * 2);
            if (blockSize == 0)
                blockSize = 2;
        }
        return blockSize;
    }

    // Get luminance value from RGB

    public void makeGreyLevel()
    {
        grey_level = new double[(int) width][(int) height];
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                grey_level[x][y] = getYfromRGB(image.getRGB(x, y));
            }
        }

    }

    // Each image block is divided into 4 sub-blocks
    // i,j: indices of sub-block

    // 1st sub-block's average luminance

    private double getFirstBlockDC(int i, int j)
    {
        double average_brightness = 0;
        if (grey_level[i][j] != 0)
        {

            for (int m = 0; m <= (getblockSize() >> 1) - 1; m++)
            {
                for (int n = 0; n <= (getblockSize() >> 1) - 1; n++)
                {
                    average_brightness = average_brightness + grey_level[i + m][j + n];
                }
            }
        }
        else
        {
            System.err.println("Grey level not initialized.");
        }
        double bs = getblockSize() * getblockSize();
        double div = 4 / bs;
        average_brightness = average_brightness * div;
        return average_brightness;
    }

    // 2nd sub-block's average luminance

    private double getSecondBlockDC(int i, int j)
    {
        double average_brightness = 0;
        if (grey_level[i][j] != 0)

            for (int m = getblockSize() / 2; m <= getblockSize() - 1; m++)
            {
                for (int n = 0; n <= (getblockSize() >> 1) - 1; n++)
                {
                    average_brightness += grey_level[i + m][j + n];


                }
            }
        else
        {
            System.err.println("Grey level not initialized.");
        }
        double bs = getblockSize() * getblockSize();
        double div = 4 / bs;
        average_brightness = average_brightness * div;
        return average_brightness;
    }

    // 3rd sub-block's average luminance

    private double getThirdBlockDC(int i, int j)
    {
        double average_brightness = 0;
        if (grey_level[i][j] != 0)
        {

            for (int m = 0; m <= (getblockSize() >> 1) - 1; m++)
            {
                for (int n = (int) (getblockSize() >> 1); n <= getblockSize() - 1; n++)
                {
                    average_brightness += grey_level[i + m][j + n];
                }
            }
        }
        else
        {
            System.err.println("Grey level not initialized.");
        }
        double bs = getblockSize() * getblockSize();
        double div = 4 / bs;
        average_brightness = average_brightness * div;
        return average_brightness;
    }

    // 4th sub-block's average luminance

    private double getFourthBlockDC(int i, int j)
    {
        double average_brightness = 0;

        for (int m = (int) (getblockSize() >> 1); m <= getblockSize() - 1; m++)
        {
            for (int n = (int) (getblockSize() >> 1); n <= getblockSize() - 1; n++)
            {
                average_brightness += grey_level[i + m][j + n];
            }
        }
        double bs = getblockSize() * getblockSize();
        double div = 4 / bs;
        average_brightness = average_brightness * div;
        return average_brightness;
    }

    // Maximum value of the 4 sub-blocks' DC going through filter coeff is compared with the threshold
    // i,j: indices of image-block
    // return index where maximum edge is found

    private int getEdgeFeature(int i, int j)
    {
        double average[] = {getFirstBlockDC(i, j), getSecondBlockDC(i, j),
                getThirdBlockDC(i, j), getFourthBlockDC(i, j)};
        double th = this.threshold;
        double edge_filter[][] = {
                {1.0, -1.0, 1.0, -1.0},
                {1.0, 1.0, -1.0, -1.0},
                {Math.sqrt(2), 0.0, 0.0, -Math.sqrt(2)},
                {0.0, Math.sqrt(2), -Math.sqrt(2), 0.0},
                {2.0, -2.0, -2.0, 2.0}
        };
        double[] strengths = new double[5];
        int e_index;

        for (int e = 0; e < 5; e++)
        {
            for (int k = 0; k < 4; k++)
            {
                strengths[e] += average[k] * edge_filter[e][k];
            }
            strengths[e] = Math.abs(strengths[e]);
        }
        double e_max = strengths[0];

        e_index = EdgeDirectionImplementation.VerticalEdge;
        if (strengths[1] > e_max)
        {
            e_max = strengths[1];
            e_index = EdgeDirectionImplementation.HorizontalEdge;
        }
        if (strengths[2] > e_max)
        {
            e_max = strengths[2];
            e_index = EdgeDirectionImplementation.Diagonal45Edge;
        }
        if (strengths[3] > e_max)
        {
            e_max = strengths[3];
            e_index = EdgeDirectionImplementation.Diagonal135Edge;
        }
        if (strengths[4] > e_max)
        {
            e_max = strengths[4];
            e_index = EdgeDirectionImplementation.NonDirectionalEdge;
        }
        if (e_max < th)
        {
            e_index = EdgeDirectionImplementation.NoEdge;
        }

        return (e_index);
    }

    private static int getYfromRGB(int rgb)
    {
        int b = rgb & 255;
        int g = rgb >> 8 & 255;
        int r = rgb >> 16 & 255;
        double yy = (0.299 * r + 0.587 * g + 0.114 * b) / 256.0;
        return (int) (219.0 * yy + 16.5);
    }
}
