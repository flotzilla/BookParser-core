package org.home.utils;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Device {
    public static final Logger logger =
            (ch.qos.logback.classic.Logger)  LoggerFactory.getLogger(Device.class);
    private String homeDir;
    private String userName;
    private String osName;
    private String osArch;
    private String osVersion;

    public void init(){
        homeDir = System.getenv().get("HOME");

        Properties properties = System.getProperties();
        userName = properties.getProperty("user.name");
        osName = properties.getProperty("os.name");
        osArch = properties.getProperty("os.arch");
        osVersion = properties.getProperty("os.version");
    }

    @Override
    public String toString() {
        return "Device{" +
                "homeDir='" + homeDir + '\'' +
                ", userName='" + userName + '\'' +
                ", osName='" + osName + '\'' +
                ", osArch='" + osArch + '\'' +
                ", osVersion='" + osVersion + '\'' +
                '}';
    }

    public String getHomeDir() {
        return homeDir;
    }

    public String getUserName() {
        return userName;
    }

    public String getOsName() {
        return osName;
    }

    public String getOsArch() {
        return osArch;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public Device setHomeDir(String homeDir) {
        this.homeDir = homeDir;
        return this;
    }

    public Device setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Device setOsName(String osName) {
        this.osName = osName;
        return this;
    }

    public Device setOsArch(String osArch) {
        this.osArch = osArch;
        return this;
    }

    public Device setOsVersion(String osVersion) {
        this.osVersion = osVersion;
        return this;
    }
}
