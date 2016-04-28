package org.home.utils;

import org.home.entity.Book;
import org.home.entity.Fb2Book;
import org.home.scanner.ScanResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private static final Logger logger =
            LoggerFactory.getLogger(DB.class);
    private static String DB_NAME = "jdbc:sqlite:";
    private final int maxBatchSize = 1000;
    private Connection connection = null;

    static {
        String sqlite_db_name = PropertiesHandler.getProperty("sqlite_db_name");
        if (sqlite_db_name != null) {
            DB_NAME += sqlite_db_name;
        } else {
            DB_NAME += "db.sqlite";
        }
    }

    public static String getDbName() {
        return DB_NAME;
    }

    public Connection connect() throws SQLException {
        connection = DriverManager.getConnection(DB_NAME);
        return connection;
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Cannot close connection");
                logger.error(e.getMessage());
                throw e;
            }
        }
    }

    public int getBookSize() throws SQLException {
        int bookSize;
        connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT COUNT(*) AS rowcount from \"Book\"");
        if (resultSet.next()) {
            bookSize = resultSet.getInt("rowcount");
        } else {
            bookSize = 0;
        }
        statement.close();
        disconnect();
        return bookSize;
    }

    public int getEmptyBookSize() throws SQLException {
        int bookSize;
        connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT COUNT(*) AS rowcount from \"Empty_book\"");
        if (resultSet.next()) {
            bookSize = resultSet.getInt("rowcount");
        } else {
            bookSize = 0;
        }
        statement.close();
        disconnect();
        return bookSize;
    }

    public int getUndefinedBookSize() throws SQLException {
        int bookSize;
        connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT COUNT(*) AS rowcount from \"Undefined_book\"");
        if (resultSet.next()) {
            bookSize = resultSet.getInt("rowcount");
        } else {
            bookSize = 0;
        }
        statement.close();
        disconnect();
        return bookSize;
    }

    public AbstractSession getLastSession() throws SQLException {
        AbstractSession session = null;

        connect();
        String sql = "SELECT * from Session ORDER BY record_id DESC LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        logger.debug("Size of query " + resultSet.getFetchSize());
        if (resultSet.next()) {
            Device device = new Device()
                    .setHomeDir(resultSet.getString("home_dir"))
                    .setOsArch(resultSet.getString("os_arch"))
                    .setOsName(resultSet.getString("os_name"))
                    .setOsVersion(resultSet.getString("os_version"))
                    .setUserName(resultSet.getString("user_name"));
            session = new AbstractSession(resultSet.getInt("id"),
                    resultSet.getString("session_name"), device);
        }
        statement.close();
        disconnect();

        return session;
    }

    public void saveSession() throws SQLException {
        Device device = org.home.utils.Session.getDevice();
        connect();

        String sql = "INSERT INTO \"Session\" " +
                "(session_name, id, home_dir, user_name, os_name, os_version, os_arch, temp_directory) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, Session.getSessionName());
        statement.setInt(2, Session.getSession_id());
        statement.setString(3, device.getHomeDir());
        statement.setString(4, device.getUserName());
        statement.setString(5, device.getOsName());
        statement.setString(6, device.getOsVersion());
        statement.setString(7, device.getOsArch());
        statement.setString(8, PropertiesHandler.getProperty("temp_directory"));
        statement.executeUpdate();
        statement.close();
        disconnect();
    }

    public void saveScanResults(ScanResults scanResult, String session_name) throws SQLException {
        connect();
        String sql = "INSERT INTO \"ScanResult\" " +
                "(scan_id, scan_time, parse_time, glob_time, pdf_books_count, fb2_books_count, epub_books_count, " +
                "djvu_books_count, txt_books_count, doc_books_count, cbr_books_count, book_found_count, " +
                "empty_book_count, undefined_book_count, scanned_path_count, bad_files_count, ignored_path_count) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setBigDecimal(1, BigDecimal.valueOf(scanResult.getScan_id()));
        st.setString(2, scanResult.getScanTime().toString());
        st.setString(3, scanResult.getParseTime().toString());
        st.setString(4, scanResult.getGlobalTime().toString());
        st.setInt(5, scanResult.getFoundPdfBooksCount());
        st.setInt(6, scanResult.getFoundfb2BooksCount());
        st.setInt(7, scanResult.getFoundEpubBooksCount());
        st.setInt(8, scanResult.getFoundDjvuBooksCount());
        st.setInt(9, scanResult.getFoundTxtBooksCount());
        st.setInt(10, scanResult.getFoundDocBooksCount());
        st.setInt(11, scanResult.getFoundcbrBooksCount());
        st.setInt(12, scanResult.getBookList().size());
        st.setInt(13, scanResult.getEmptyBookList().size());
        st.setInt(14, scanResult.getUndefinedBookList().size());
        st.setInt(15, scanResult.getScannedPathList().size());
        st.setInt(16, scanResult.getBadFilesPathList().size());
        st.setInt(17, scanResult.getIgnoredPathFileList().size());
        st.execute();

        String session_scan_sql = "INSERT INTO \"Session_scan\" " +
                "(session_name, scan_id) VALUES(?, ?)";
        st = connection.prepareStatement(session_scan_sql);
        st.setString(1, session_name);
        st.setLong(2, scanResult.getScan_id());
        st.execute();

        if (scanResult.getBookList().size() > 0) {
            saveBooks(scanResult);
            saveScanBooksId(scanResult);
        }

        if (scanResult.getEmptyBookList().size() > 0) {
            saveEmptyBooks(scanResult);
            saveScanEmptyBooksId(scanResult);
        }

        if (scanResult.getUndefinedBookList().size() > 0) {
            saveUndefinedBooks(scanResult);
            saveUndefinedBooksId(scanResult);
        }

        if(scanResult.getScannedPathList().size() > 0){
            saveScanPathList(scanResult);
            saveScanPathListIds(scanResult);
        }

        if(scanResult.getBadFilesPathList().size() > 0){
            saveBadFilesPathList(scanResult);
            saveBadFilesPathListIds(scanResult);
        }

        if(scanResult.getIgnoredPathFileList().size() > 0){
            saveIgnoredPathList(scanResult);
            saveIgnoredPathListIds(scanResult);
        }

        disconnect();
    }

    private void saveBooks(ScanResults scanResults) throws SQLException {
        String sql = "INSERT into \"Book\" " +
                "(scan_id, id, file_name, location_path, ext, file_size, title, author, number_of_pages, subject, " +
                "description, creation_date, modification_date, creator, producer, keywords, lang, version," +
                " have_cover, preview_image_file, isbn, publisher_name, publisher_book_name, publisher_city, " +
                "publish_year, is_deleted, encoding, annotaion, genre, translator, src_lang, src_genre, src_author, " +
                "src_title, src_annotation, src_keywords, src_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        prepareBookStatement(ps, scanResults.getBookList());
        ps.executeBatch();
    }

    public ScanResults getScanResult(long scan_id) throws SQLException {
        ScanResults scanResults = new ScanResults();

        String sql = "SELECT * from \"ScanResult\" WHERE " +
                "scan_id = ?";
        connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, scan_id);
        ResultSet resultSet = statement.executeQuery();

        logger.trace("Fetch size: " + resultSet.getFetchSize());
            while(resultSet.next()){
                scanResults.setScan_id(scan_id);
                scanResults.setFoundPdfBooksCount(resultSet.getInt("pdf_books_count"));
                scanResults.setFoundfb2BooksCount(resultSet.getInt("fb2_books_count"));
                scanResults.setFoundEpubBooksCount(resultSet.getInt("epub_books)count"));
                scanResults.setFoundDjvuBooksCount(resultSet.getInt("djvu_books_count"));
                scanResults.setFoundTxtBooksCount(resultSet.getInt("txt_books_count"));
                scanResults.setFoundDocBooksCount(resultSet.getInt("doc_books_count"));
                scanResults.setFoundcbrBooksCount(resultSet.getInt("cbr_books_count"));
            }
        connection.close();
        disconnect();
        return scanResults;
    }

    private void saveScanBooksId(ScanResults scanResults) throws SQLException {
        String scanBookSql = "INSERT INTO \"Scan_Book\" " +
                "(scan_id, book_scan_id)  VALUES(?, ?)";
        PreparedStatement st = connection.prepareStatement(scanBookSql);
        int counter = 0;
        for (Book b : scanResults.getBookList()) {
            st.setBigDecimal(1, BigDecimal.valueOf(scanResults.getScan_id()));
            st.setString(2, b.getScanId());
            st.addBatch();
            if (++counter % maxBatchSize == 0) {
                st.executeBatch();
            }
        }
        st.executeBatch();
        st.close();
    }

    private void saveEmptyBooks(ScanResults scanResults) throws SQLException {
        String sql = "INSERT into \"Empty_book\" " +
                "(scan_id, id, file_name, location_path, ext, file_size, title, author, number_of_pages, subject, " +
                "description, creation_date, modification_date, creator, producer, keywords, lang, version," +
                " have_cover, preview_image_file, isbn, publisher_name, publisher_book_name, publisher_city, " +
                "publish_year, is_deleted, encoding, annotaion, genre, translator, src_lang, src_genre, src_author, " +
                "src_title, src_annotation, src_keywords, src_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        prepareBookStatement(ps, scanResults.getEmptyBookList());
        ps.executeBatch();
    }

    private void saveScanEmptyBooksId(ScanResults scanResults) throws SQLException {
        String scanBookSql = "INSERT INTO \"Scan_Empty_Book\" " +
                "(scan_id, book_scan_id)  VALUES(?, ?)";
        PreparedStatement st = connection.prepareStatement(scanBookSql);
        int counter = 0;
        for (Book b : scanResults.getEmptyBookList()) {
            st.setBigDecimal(1, BigDecimal.valueOf(scanResults.getScan_id()));
            st.setString(2, b.getScanId());
            st.addBatch();
            if (++counter % maxBatchSize == 0) {
                st.executeBatch();
            }
        }
        st.executeBatch();
        st.close();
    }

    private void saveUndefinedBooks(ScanResults scanResults) throws SQLException {
        String sql = "INSERT into \"Undefined_book\" " +
                "(scan_id, id, file_name, location_path, ext, file_size, title, author, number_of_pages, subject, " +
                "description, creation_date, modification_date, creator, producer, keywords, lang, version," +
                " have_cover, preview_image_file, isbn, publisher_name, publisher_book_name, publisher_city, " +
                "publish_year, is_deleted, encoding, annotaion, genre, translator, src_lang, src_genre, src_author, " +
                "src_title, src_annotation, src_keywords, src_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        prepareBookStatement(ps, scanResults.getUndefinedBookList());
        ps.executeBatch();
    }

    private void saveUndefinedBooksId(ScanResults scanResults) throws SQLException {
        String scanBookSql = "INSERT INTO \"Scan_Undefined_Book\" " +
                "(scan_id, book_scan_id)  VALUES(?, ?)";
        PreparedStatement st = connection.prepareStatement(scanBookSql);
        int counter = 0;
        for (Book b : scanResults.getUndefinedBookList()) {
            st.setBigDecimal(1, BigDecimal.valueOf(scanResults.getScan_id()));
            st.setString(2, b.getScanId());
            st.addBatch();
            if (++counter % maxBatchSize == 0) {
                st.executeBatch();
            }
        }
        st.executeBatch();
        st.close();
    }

    private void prepareBookStatement(PreparedStatement ps, List<Book> bookList) throws SQLException {
        int counter = 0;
        for (Book b : bookList) {
            ps.setString(1, b.getScanId());
            ps.setInt(2, b.getId());
            ps.setString(3, b.getFileName());
            ps.setString(4, b.getLocationPath().toAbsolutePath().toString());
            ps.setString(5, b.getExtension());
            ps.setString(6, b.getSize());
            ps.setString(7, b.getTitle());
            ps.setString(8, b.getAuthor());
            ps.setInt(9, b.getNumberOfPages());
            ps.setString(10, b.getSubject());
            ps.setString(11, b.getDescription());
            ps.setString(12, b.getCreationDate());
            ps.setString(13, b.getModifDate());
            ps.setString(14, b.getCreator());
            ps.setString(15, b.getProducer());
            ps.setString(16, b.getKeywords());
            ps.setString(17, b.getLanguage());
            ps.setString(18, b.getVersion());
            ps.setBoolean(19, b.isHaveCover());
            ps.setString(20, b.getPreviewImageFile());
            ps.setString(21, b.getIsbn());
            ps.setString(22, b.getPublisherName());
            ps.setString(23, b.getPublisherBookName());
            ps.setString(24, b.getPublisherCity());
            ps.setString(25, b.getPublishYear());
            ps.setBoolean(26, b.is_deleted());

            if (b.getClass().equals(Fb2Book.class)) {
                logger.debug("Saving fb2 book");
                Fb2Book fb2book = (Fb2Book) b;
                ps.setString(27, fb2book.getEncoding());
                ps.setString(28, fb2book.getAnnotation());
                ps.setString(29, fb2book.getGenre());
                ps.setString(30, fb2book.getTranslator());
                ps.setString(31, fb2book.getSrcLang());
                ps.setString(32, fb2book.getSrcGenre());
                ps.setString(33, fb2book.getSrcAuthor());
                ps.setString(34, fb2book.getSrcTitle());
                ps.setString(35, fb2book.getSrcAnnotation());
                ps.setString(36, fb2book.getSrcKeywords());
                ps.setString(37, fb2book.getSrcDate());
            } else {
                logger.trace("Saving empty pdf book");
                ps.setString(27, "none");
                ps.setString(28, "none");
                ps.setString(29, "none");
                ps.setString(30, "none");
                ps.setString(31, "none");
                ps.setString(32, "none");
                ps.setString(33, "none");
                ps.setString(34, "none");
                ps.setString(35, "none");
                ps.setString(36, "none");
                ps.setString(37, "none");
            }
            ps.addBatch();
            if (++counter % maxBatchSize == 0) {
                ps.executeBatch();
            }
        }
    }

    private void saveScanPathList(ScanResults scanResults) throws SQLException {
     String sql = "Insert INTO  \"Scanned_path_list\" " +
             "(id, file_path) VALUES (?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        int counter = 0;
        for(Path p :scanResults.getScannedPathList()){
            statement.setString(1, scanResults.getScan_id() + "__f" + ++counter);
            statement.setString(2, p.toAbsolutePath().toString());
            statement.addBatch();

            if(counter % maxBatchSize ==0){
                statement.executeBatch();
            }
        }

        statement.executeBatch();
    }

    private void saveScanPathListIds(ScanResults scanResults) throws SQLException {
        String sql = "Insert INTO  \"Scan_Scanned_path_list\" " +
                "(scan_id, file_path_id)  VALUES (?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        int counter = 0;
        for(Path p :scanResults.getScannedPathList()){
            statement.setBigDecimal(1, BigDecimal.valueOf(scanResults.getScan_id()));
            statement.setString(2, scanResults.getScan_id() + "__f" + ++counter);
            statement.addBatch();

            if(counter % maxBatchSize ==0){
                statement.executeBatch();
            }
        }

        statement.executeBatch();
    }

    private void saveIgnoredPathList(ScanResults scanResults) throws SQLException {
        String sql = "Insert INTO  \"ignored_path_list\" " +
                "(id, file_path) VALUES (?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        int counter = 0;
        for(Path p :scanResults.getIgnoredPathFileList()){
            statement.setString(1, scanResults.getScan_id() + "__f" + ++counter);
            statement.setString(2, p.toAbsolutePath().toString());
            statement.addBatch();

            if(counter % maxBatchSize ==0){
                statement.executeBatch();
            }
        }

        statement.executeBatch();
    }

    private void saveIgnoredPathListIds(ScanResults scanResults) throws SQLException {
        String sql = "Insert INTO  \"Scan_Ignored_path_list\" " +
                "(scan_id, file_path_id)  VALUES (?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        int counter = 0;
        for(Path p :scanResults.getIgnoredPathFileList()){
            statement.setBigDecimal(1, BigDecimal.valueOf(scanResults.getScan_id()));
            statement.setString(2, scanResults.getScan_id() + "__f" + ++counter);
            statement.addBatch();

            if(counter % maxBatchSize ==0){
                statement.executeBatch();
            }
        }

        statement.executeBatch();
    }

    private void saveBadFilesPathList(ScanResults scanResults) throws SQLException {
        String sql = "Insert INTO  \"Bad_files_path_list\" " +
                "(id, file_path) VALUES (?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        int counter = 0;
        for(Path p :scanResults.getBadFilesPathList()){
            statement.setString(1, scanResults.getScan_id() + "__f" + ++counter);
            statement.setString(2, p.toAbsolutePath().toString());
            statement.addBatch();

            if(counter % maxBatchSize ==0){
                statement.executeBatch();
            }
        }

        statement.executeBatch();
    }

    private void saveBadFilesPathListIds(ScanResults scanResults) throws SQLException {
        String sql = "Insert INTO  \"Scan_Bad_files_path_list\" " +
                "(scan_id, file_path_id)  VALUES (?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        int counter = 0;
        for(Path p :scanResults.getBadFilesPathList()){
            statement.setBigDecimal(1, BigDecimal.valueOf(scanResults.getScan_id()));
            statement.setString(2, scanResults.getScan_id() + "__f" + ++counter);
            statement.addBatch();

            if(counter % maxBatchSize ==0){
                statement.executeBatch();
            }
        }

        statement.executeBatch();
    }

    private List<Book> getScanBooksList(long scanId) throws SQLException {
        List<Book> books = new ArrayList<>();
//
//        String sql = "SELECT From ";
//        PreparedStatement statement = connection.prepareStatement(sql);
        return books;
    }

    private void getScanEmptyBooksList(long scanId){}

    private void getScanUndefinedBooksList(long scanId){}

    private void getScanPathList(long scanId){}

    private void getScanBadFilesList(long scanId){}

    private void getScanIgnoredFilesList(long scanId){}


}
