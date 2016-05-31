package core;

import java.io.FileInputStream;

class DCT implements JPEGDecoder.DCTArray
{
    public int[][][] DCTmatrix;
    String file;
    JPEGDecoder j = null;
    int width, height;

    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;

        DCTmatrix = new int[3][height][width];
    }

    public void setPixel(int x, int y, int component, int value)
    {
        DCTmatrix[component][y][x] = value;
    }

    public DCT(String s)
    {
        file = s;
        j = new JPEGDecoder();
        FileInputStream in = null;

        try
        {
            in = new FileInputStream(file);
            j.decode(in, this);
            in.close();
        }
        catch (Exception e)
        {
        }
    }
}

