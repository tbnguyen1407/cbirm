package cbirm.core;

import java.io.File;

import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;

import cbirm.storage.ImageIndexHolder;

public class DateTime {
    // region public methods

    public static String createIndex(String imagePath) {
        TiffField dateField = null;

        File file = new File(imagePath);
        JpegImageMetadata metadata = null;

        try {
            metadata = (JpegImageMetadata) Imaging.getMetadata(file);
            dateField = metadata.findExifValue(ExifTagConstants.EXIF_TAG_DATE_TIME_ORIGINAL);
        } catch (Exception e) {
        }

        return (dateField == null) ? "-" : dateField.getValueDescription().replace("'", "");
    }

    public static String loadImageIndex(String image) {
        String indexStr = ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.DateTime, image);

        if (indexStr == null) {
            indexStr = createIndex(image);
            saveImageIndex(image, indexStr);
        }

        return indexStr;
    }

    public static void saveImageIndex(String image, String index) {
        ImageIndexHolder.getInstance().saveImageIndex(ImageIndexHolder.FeatureType.DateTime, image, index);
    }

    // endregion
}
