package core;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.iptc.IptcDirectory;
import storage.ImageIndexHolder;

import java.io.File;

public class Location
{

	public static String createIndex(String filename)
	{
		File jpegFile = new File(filename);
        Metadata metadata = null;

        try
        {
            metadata = JpegMetadataReader.readMetadata(jpegFile);
        }
        catch (JpegProcessingException e)
        {
        }

        Directory iptcDirectory = metadata.getDirectory(IptcDirectory.class);
		String country = iptcDirectory.getString(IptcDirectory.TAG_COUNTRY_OR_PRIMARY_LOCATION);
		return (country == null) ? "Uncategoried" : country;
	}

	public static void saveImageIndex(String imagePath, String index)
	{
		ImageIndexHolder.getInstance().saveImageIndex(ImageIndexHolder.FeatureType.Location, imagePath, index);
	}

	public static String loadImageIndex(String imagePath)
	{
		String indexStr = ImageIndexHolder.getInstance().loadImageIndex(ImageIndexHolder.FeatureType.Location, imagePath);

		if (indexStr == null || indexStr == "")
		{
			indexStr = createIndex(imagePath);
			saveImageIndex(imagePath, indexStr);
		}
		return indexStr;
	}
}
