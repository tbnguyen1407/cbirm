package cbirm.core;

import java.io.FileInputStream;

class DCT implements JPEGDecoder.DCTArray {
    // region fields

    private int[][][] matrix;

    // endregion

    // region constructors

    public DCT(String file) {
        JPEGDecoder decoder = new JPEGDecoder();

        try {
            FileInputStream fis = new FileInputStream(file);
            decoder.decode(fis, this);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // endregion

    // region public methods

    public int[][][] getMatrix() {
        return this.matrix;
    }

    public void setSize(int width, int height) {
        matrix = new int[3][height][width];
    }

    public void setPixel(int x, int y, int component, int value) {
        matrix[component][y][x] = value;
    }

    // endregion
}
