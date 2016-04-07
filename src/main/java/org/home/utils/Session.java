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
import java.util.Set;

public class Session {
    private static final Logger logger =
            LoggerFactory.getLogger(Session.class);
    private static int session_id = 1;

    public Session() {
        int session = -1;
        try{
             session = Integer.parseInt(PropertiesHandler.getProperty("session"));
        }catch (NumberFormatException nfe){
            logger.error("Cannot parse session property. " + nfe.getMessage());
        }finally {
            if(session != -1){
                session_id = ++session;
                PropertiesHandler.saveProperty("session", String.valueOf(session_id));
            }
            logger.debug("Started session with number " + session);
        }
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

    public static int getSession_id() {
        return session_id;
    }

    public static String getSessionName(){
        return "sess" + session_id + "__";
    }



}
