package org.home.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Session {
    private static final Logger logger =
            LoggerFactory.getLogger(Session.class);
    private static int session_id = 1;
    private static Device device;

    public Session() {
        int session = -1;
        device = new Device();
        device.init();
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

    public static int getSession_id() {
        return session_id;
    }

    public static String getSessionName(){
        return "sess" + session_id + "__";
    }

    public static Device getDevice() {
        return device;
    }
}
