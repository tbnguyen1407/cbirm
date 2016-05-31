package helpers;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;

public class FileSystemOperator
{
    public static boolean checkAndCreateFile(File file) throws IOException
    {
        boolean fileExists = file.exists();
        if (!fileExists)
            file.createNewFile();
        return fileExists;
    }

    public static String[] getListOfFiles(String folderPath)
    {
        File rootFolder = new File(folderPath);
        File[] listOfFiles = rootFolder.listFiles(createIOFileFilter("jpg"));

        ArrayList<String> fileDatabaseArray = new ArrayList<String>();

        for (int i = 0; i < listOfFiles.length; i++)
            if (listOfFiles[i].isFile())
                fileDatabaseArray.add(listOfFiles[i].getPath());
        return fileDatabaseArray.toArray(new String[fileDatabaseArray.size()]);
    }

    public static javax.swing.filechooser.FileFilter createChooserFileFilter(final String extension)
    {
        return new javax.swing.filechooser.FileFilter()
        {
            public boolean accept(File file)
            {
                return file.isDirectory() || file.getName().toLowerCase().endsWith("." + extension);
            }

            public String getDescription()
            {
                return extension + " files";
            }
        };
    }

    private static java.io.FileFilter createIOFileFilter(final String extension)
    {
        return new FileFilter()
        {
            public boolean accept(File file)
            {
                return file.isDirectory() || file.getName().toLowerCase().endsWith("." + extension);
            }

            public String getDescription()
            {
                return extension + " files";
            }
        };
    }

}
