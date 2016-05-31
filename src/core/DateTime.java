package core;

import org.apache.sanselan.Sanselan;
import org.apache.sanselan.formats.jpeg.JpegImageMetadata;
import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;
import storage.ImageIndexHolder;

import java.io.File;

public class DateTime
{

    public static String createIndex(String imagePath)
    {
        TiffField dateField = null;

        File file = new File(imagePath);
        JpegImageMetadata metadata = null;

        try
        {
            metadata = (JpegImageMetadata) Sanselan.getMetadata(file);
            dateField = metadata.findEXIFValue(TiffConstants.EXIF_TAG_DATE_TIME_ORIGINAL);
        }
        catch (Exception e)
        {
        }

        return (dateField == null) ? "-" : dateField.getValueDescription().replace("'", "");
    }

    public static String loadImageIndex(String image)
    {
        String indexStr = ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.DateTime, image);

        if (indexStr == null)
        {
            indexStr = createIndex(image);
            saveImageIndex(image, indexStr);
        }

        return indexStr;
    }

    public static void saveImageIndex(String image, String index)
    {
        ImageIndexHolder.getInstance().saveImageIndex(ImageIndexHolder.FeatureType.DateTime, image, index);
    }
}
