package helpers;

import javax.swing.*;
import java.awt.*;

public class ImageOperator
{

    public static ImageIcon createImageIcon(String imgPath)
    {
        Image img = (new ImageIcon(imgPath)).getImage().getScaledInstance(173, 173, Image.SCALE_FAST);
        ImageIcon icon = new ImageIcon(img);
        //img.flush();
        //img = null;
        //System.gc();
        return icon;//new ImageIcon((new ImageIcon(imgPath)).getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
    }

}
