package org.home.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.DecimalFormat;
import java.util.Set;

public class FileUtils {
    private static final Logger logger =
            LoggerFactory.getLogger(FileUtils.class);

    //file size in megabytes
    public static String calculateFileSize(Path file) throws SecurityException, IOException {
        double size = Files.size(file);
        if(size != 0){
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String formated = decimalFormat.format(size / 1024 / 1024);
            return String.valueOf(formated) + " Mb";
        }else{
            return "0";
        }
    }

    public static String[] getFileExtension(Path file){
        String fileName = file.getFileName().toString();
        String[] fileNameArray = new String[2];

        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0){
            fileNameArray[1] = fileName.substring(fileName.lastIndexOf(".") + 1);
            fileNameArray[0] = fileName.substring(0, fileName.indexOf(fileNameArray[1]) - 1 );
        }else{
            fileNameArray[0] = fileName;
            fileNameArray[1] =  "undefined";
        }
        return fileNameArray;
    }


    /**
     * @param fileName that consist dash
     * @return array first item - author name, second book name
     */
    public static String[] parseFileNameBydashSeparator(String fileName){
        String[] fileNameArray = new String[2];
        if(fileName.contains("-") && fileName.indexOf("-") != 0){
            fileNameArray[0] = fileName.substring(fileName.indexOf("-") + 1); //author
            fileNameArray[1] = fileName.substring(0, fileName.indexOf(fileNameArray[0]) - 1 ); //book name
        }else{
            fileNameArray[0] = "undefined";
            fileNameArray[1] =  "undefined";
        }

        return fileNameArray;
    }

    public static void createTempoDirectory(){
        Path temp_directory = Paths.get(PropertiesHandler.getProperty("temp_directory"));
        logger.debug("Temp dir" + temp_directory.toString());
        if(!Files.exists(temp_directory)){
            Set<PosixFilePermission> perms =
                    PosixFilePermissions.fromString("rwxr-x---");
            FileAttribute<Set<PosixFilePermission>> attr =
                    PosixFilePermissions.asFileAttribute(perms);
            try {
                Files.createDirectory(temp_directory, attr);
            } catch (IOException e) {
                logger.error("Cannot create temp directory at " + temp_directory.toString());
            }
        }
    }

}
