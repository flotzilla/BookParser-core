package org.home.utils;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Device {
    public static final Logger logger =
            (ch.qos.logback.classic.Logger)  LoggerFactory.getLogger(Device.class);
    private static String homeDir;
    private static String userName;
    private static String osName;
    private static String osArch;
    private static String osVersion;

    static {
        homeDir = System.getenv().get("HOME");

        Properties properties = System.getProperties();
        userName = properties.getProperty("user.name");
        osName = properties.getProperty("os.name");
        osArch = properties.getProperty("os.arch");
        osVersion = properties.getProperty("os.version");
    }

    public static String getHomeDir() {
        return homeDir;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getOsName() {
        return osName;
    }

    public static String getOsArch() {
        return osArch;
    }

    public static String getOsVersion() {
        return osVersion;
    }
}
