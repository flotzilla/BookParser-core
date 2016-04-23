package org.home.utils;

import org.home.jooq.Tables;
import org.home.jooq.tables.*;
import org.home.jooq.tables.Session;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static final Logger logger =
            LoggerFactory.getLogger(DB.class);
    private static String DB_NAME = "jdbc:sqlite:";
    private Connection connection = null;

    static {
        String sqlite_db_name = PropertiesHandler.getProperty("sqlite_db_name");
        if(sqlite_db_name != null){
            DB_NAME += sqlite_db_name;
        }else{
            DB_NAME += "db.sqlite";
        }
    }

    public DB(){}

    private Connection connect() throws SQLException {
        connection = DriverManager.getConnection(DB_NAME);
        return connection;
    }

    private void disconnect() throws SQLException {
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Cannot close connection");
                logger.error(e.getMessage());
                throw e;
            }
        }
    }

    private Result<Record> fetchSqlRsult(String sql) throws SQLException {
        connect();
        Result<Record> fetch = DSL.using(connection, SQLDialect.SQLITE)
                .fetch(sql);
        disconnect();
        return fetch;
    }

    private Record fetchSingle(String sql) throws SQLException {
        connect();
        Record record = DSL.using(connection, SQLDialect.SQLITE)
                .fetchOne(sql);
        disconnect();
        return record;
    }

    public static String getDbName() {
        return DB_NAME;
    }

    public Connection getConnection() {
        return connection;
    }

    public AbstractSession getLastSession() throws SQLException {
        AbstractSession session = null;
        String sql = "SELECT * from Session ORDER BY record_id DESC LIMIT 1";
        Record record = fetchSingle(sql);
        if(record != null){
            try {
                Device device = new Device()
                        .setHomeDir((String) record.getValue("home_dir"))
                        .setOsArch((String) record.getValue("os_arch"))
                        .setOsName((String) record.getValue("os_name"))
                        .setOsVersion((String) record.getValue("os_version"))
                        .setUserName((String) record.getValue("user_name"));
                session = new AbstractSession((Integer) record.getValue("id"),
                        (String) record.getValue("session_name"),
                        device);
            }catch (ClassCastException cce){
                logger.error(cce.getMessage());
            }

        }

        return session;
    }

    public void saveSession() throws SQLException {
        Device device = new Device();
        connect();
        DSLContext create = DSL.using(connection, SQLDialect.SQLITE);
        create.insertInto(Session.SESSION,
                Session.SESSION.SESSION_NAME,
                Session.SESSION.ID,
                Session.SESSION.HOME_DIR,
                Session.SESSION.USER_NAME,
                Session.SESSION.OS_NAME,
                Session.SESSION.OS_VERSION,
                Session.SESSION.OS_ARCH,
                Session.SESSION.TEMP_DIRECTORY)
                .values(org.home.utils.Session.getSessionName(),
                        org.home.utils.Session.getSession_id(),
                        device.getHomeDir(),
                        device.getUserName(),
                        device.getOsName(),
                        device.getOsVersion(),
                        device.getOsArch(),
                        PropertiesHandler.getProperty("temp_directory"))
                .execute();
        disconnect();
    }
}
