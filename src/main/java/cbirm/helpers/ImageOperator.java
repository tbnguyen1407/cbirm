package cbirm.helpers;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageOperator {
    // region public methods

    public static ImageIcon createImageIcon(String imgPath) {
        Image img = (new ImageIcon(imgPath)).getImage().getScaledInstance(173, 173, Image.SCALE_FAST);
        ImageIcon icon = new ImageIcon(img);
        return icon;
    }

    public static ImageIcon createDummyImageIcon() {
        Image img = (new ImageIcon("img/blank.jpg")).getImage().getScaledInstance(173, 173, Image.SCALE_FAST);
        ImageIcon icon = new ImageIcon(img);
        return icon;
    }

    // endregion
}
