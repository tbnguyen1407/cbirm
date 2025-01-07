package cbirm.helpers;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;

public class FileSystemOperator {
    // region public methods

    public static boolean checkAndCreateFile(File file) throws IOException {
        boolean fileExists = file.exists();
        if (!fileExists) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        return fileExists;
    }

    public static String[] getListOfFiles(String folderPath) {
        File rootFolder = new File(folderPath);
        File[] listOfFiles = rootFolder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return file.isDirectory() || name.toLowerCase().endsWith(".jpg");
            }
        });

        ArrayList<String> fileDatabaseArray = new ArrayList<String>();

        for (int i = 0; i < listOfFiles.length; i++)
            if (listOfFiles[i].isFile())
                fileDatabaseArray.add(listOfFiles[i].getPath());
        return fileDatabaseArray.toArray(new String[fileDatabaseArray.size()]);
    }

    public static FileFilter createChooserFileFilter(String extension) {
        return new javax.swing.filechooser.FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith("." + extension);
            }

            public String getDescription() {
                return extension + " files";
            }
        };
    }

    // endregion
}
