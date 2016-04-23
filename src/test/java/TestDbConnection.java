import org.home.utils.AbstractSession;
import org.home.utils.DB;
import org.home.utils.Device;
import org.home.utils.Session;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import static java.util.stream.Collectors.groupingBy;

public class TestDbConnection {
    private static final Logger logger =
            LoggerFactory.getLogger(TestDbConnection.class);
    private String db_name = "jdbc:sqlite:db.sqlite";

//    @Test
    public void testConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(db_name);
            logger.debug(connection.getSchema());
            logger.debug(connection.getCatalog());
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("INSERT into \"Session\" VALUES('sess_name1', " +
                    "1," +
                    " 'home'," +
                    "'user', " +
                    "'os', " +
                    "'os_version', " +
                    "'os_arch', " +
                    "'tempo') ");

            statement.executeUpdate("INSERT into \"Session\" VALUES('sess_name2', " +
                    "2," +
                    " 'home'," +
                    "'user', " +
                    "'os', " +
                    "'os_version', " +
                    "'os_arch', " +
                    "'tempo') ");


            ResultSet resultSet = statement.  executeQuery("SELECT * FROM \"Session\"");
            while (resultSet.next()){
                logger.debug(String.valueOf(resultSet.getInt("id")));
                logger.debug(resultSet.getString("os_arch"));
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Cannot close connection");
                    logger.error(e.getMessage());
                }
            }
        }
    }

//    @Test
    public void testQool(){
        Connection connection= null;
        try{
            connection = DriverManager.getConnection(db_name);

            String select_sql = "Select * FROM Session";

            Result<Record> fetch = DSL.using(connection, SQLDialect.SQLITE)
                    .fetch(select_sql);

            if(fetch.size() > 0 ){
                fetch.stream()
                        .forEach(record -> {
                            logger.debug(String.valueOf(record.getValue("session_name")));
                            Object id = record.getValue("id");
                            if(id.getClass().equals(Integer.class)){
                                logger.debug("integer type");
                            }else{
                                logger.debug("not integer");
                            }

                            if(id instanceof Integer){
                                logger.debug("integer too");
                            }

                        });
            }
        } catch (SQLException e) {
            logger.debug("Cannot get connection to file " + e.getMessage());
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Cannot close connection");
                    logger.error(e.getMessage());
                }
            }
        }
    }

    @Test
    public void TestDBGetLastSession(){
        DB db = new DB();
        try {
            AbstractSession lastSession = db.getLastSession();
            logger.debug("Last session name is " + lastSession);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    public void TestInsertSession(){
        try {
            Session session = new Session();
            new DB().saveSession();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
