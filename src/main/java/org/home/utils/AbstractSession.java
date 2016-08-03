package org.home.utils;

/**
 * Created by bitybite on 4/23/16.
 */
public class AbstractSession {
    private Integer session_id;
    private String session_name;
    private Device device;

    public AbstractSession(Integer session_id, String session_name, Device device) {
        this.session_id = session_id;
        this.session_name = session_name;
        this.device = device;
    }

    @Override
    public String toString() {
        return "AbstractSession{" +
                "session_id=" + session_id +
                ", session_name='" + session_name + '\'' +
                ", device=" + device +
                '}';
    }

    public Integer getSession_id() {
        return session_id;
    }

    public void setSession_id(Integer session_id) {
        this.session_id = session_id;
    }

    public String getSession_name() {
        return session_name;
    }

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
