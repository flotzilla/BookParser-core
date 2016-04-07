package org.home.utils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;

public class FileSize {

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

}
