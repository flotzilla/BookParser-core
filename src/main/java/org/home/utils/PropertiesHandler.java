package org.home.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PropertiesHandler {
    private static final String propFile = "config.properties";
    private static final Logger logger =
            LoggerFactory.getLogger(PropertiesHandler.class);

    private static List<String> fileExtensions;
    private static Properties properties = null;

    public static void saveProperties(){
        OutputStream outputStream = null;
        try{
            outputStream= new FileOutputStream("src" + File.separator + "main"
                    + File.separator + "resources" + File.separator + propFile);
            properties.store(outputStream, null);
            logger.debug("saved");
        } catch (FileNotFoundException e) {
            logger.error("Properties file not found");
        } catch (IOException e) {
            logger.error("Cannot save properties file content");
        }finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    logger.error("Cannot close properties stream");
                }
            }
        }
    }

    public static void saveProperty(String key, String value){
        initProperties();
        properties.setProperty(key, value);
        saveProperties();
    }

    public static void readProperties(){
        InputStream inputStream = null;
        try {

            inputStream = ClassLoader.getSystemResourceAsStream(propFile);
            properties.load(inputStream);
            fileExtensions = Arrays.asList(properties.getProperty("extension").split(","));
        } catch (FileNotFoundException e) {
            logger.error("Properties file not found");
        } catch (IOException e) {
            logger.error("Cannot read properties file content");
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("Cannot close properties stream");
                }
            }
        }
    }

    public static String getProperty(String key){
        initProperties();
        return properties.getProperty(key);
    }

    public static List<String> getFileExtensions(){
        initProperties();
        return fileExtensions;
    }

    private static void initProperties(){
        if (properties == null){
            properties = new Properties();
        }

        if(properties.isEmpty()){
            readProperties();
        }
    }
}
