import org.home.entity.Book;
import org.home.entity.Fb2Book;
import org.home.scanner.ScanResults;
import org.home.utils.AbstractSession;
import org.home.utils.DB;
import org.home.utils.Session;
import org.home.utils.Utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ch.qos.logback.classic.Logger;

public class TestDbConnection {
    private static final Logger logger =
            (Logger) LoggerFactory.getLogger(TestDbConnection.class);
    private String db_name = "jdbc:sqlite:db.sqlite";
    private Session session;
    private List<Book> undefinedBookList;
    private List<Book> emptyBookList;
    private List<Book> bookList;
    private List<Path> scannedPathList;
    private List<Path> ignoredPathList;
    private List<Path> badPathList;

    @Before
    public void initSession() {
        session = new Session();
    }

    public void initListOfBooks(ScanResults scanResults, int size){
        bookList = new ArrayList<>();
        for(int i = 0; i< size; i++){
            if(i % 2 == 0){
                Book book = new Book();
                book.setExtension("pdf");
                logger.debug("Book instance");
                book.setFileName("file_name" + i);
                book.setScanId(scanResults.getScan_id());
                book.setLocationPath(Paths.get("/some/file/path" + i));
                book.setIs_deleted(false);
                book.setNumberOfPages(30);
                book.setSize("1Mb");
                bookList.add(book);
            }else{
                Fb2Book book = new Fb2Book();
                book.setExtension("fb2");
                book.setEncoding("urf-8");
                book.setFileName("file_name" + i);
                book.setScanId(scanResults.getScan_id());
                book.setLocationPath(Paths.get("/some/file/path" + i));
                book.setIs_deleted(false);
                book.setNumberOfPages(30);
                book.setSize("1Mb");
                bookList.add(book);
                logger.debug("Fb2 book instance");
            }

        }
    }

    public void initUndefinedBooks(ScanResults scanResults, int size){
        undefinedBookList = new ArrayList<>();
        for(int i = 0; i< size; i++){
            if(i % 2 == 0){
                Book book = new Book();
                book.setExtension("pdf");
                logger.debug("Book instance");
                book.setFileName("undefined_book" + i);
                book.setScanId(scanResults.getScan_id());
                book.setLocationPath(Paths.get("/some/file/path" + i));
                book.setIs_deleted(false);
                book.setNumberOfPages(30);
                book.setSize("1Mb");
                undefinedBookList.add(book);
            }else{
                Fb2Book book = new Fb2Book();
                book.setExtension("fb2");
                book.setEncoding("urf-8");
                book.setFileName("undefined_book" + i);
                book.setScanId(scanResults.getScan_id());
                book.setLocationPath(Paths.get("/some/file/path" + i));
                book.setIs_deleted(false);
                book.setNumberOfPages(30);
                book.setSize("1Mb");
                undefinedBookList.add(book);
                logger.debug("Fb2 book instance");
            }

        }
    }

    public void initEmptyBooks(ScanResults scanResults, int size){
        emptyBookList = new ArrayList<>();
        for(int i = 0; i< size; i++){
            if(i % 2 == 0){
                Book book = new Book();
                book.setExtension("pdf");
                logger.debug("Book instance");
                book.setFileName("empty book" + i);
                book.setScanId(scanResults.getScan_id());
                book.setLocationPath(Paths.get("/some/file/path" + i));
                book.setIs_deleted(false);
                book.setNumberOfPages(30);
                book.setSize("1Mb");
                emptyBookList.add(book);
            }else{
                Fb2Book book = new Fb2Book();
                book.setExtension("fb2");
                book.setEncoding("urf-8");
                book.setFileName("empty book" + i);
                book.setScanId(scanResults.getScan_id());
                book.setLocationPath(Paths.get("/some/file/path" + i));
                book.setIs_deleted(false);
                book.setNumberOfPages(30);
                book.setSize("1Mb");
                emptyBookList.add(book);
                logger.debug("Fb2 book instance");
            }

        }
    }

    public void initScannedFilesList(ScanResults scanResults, int size){
        scannedPathList = new ArrayList<>();
        for(int i=0; i< size; i++){
            scannedPathList.add(Paths.get("file" + i));
        }
        scanResults.setScannedPathList(scannedPathList);
    }

    public void initIgnoredFilesList(ScanResults scanResults, int size){
        ignoredPathList= new ArrayList<>();
        for(int i=0; i< size; i++){
            ignoredPathList.add(Paths.get("file" + i));
        }
        scanResults.setIgnoredPathFileList(ignoredPathList);
    }

    public void initBadFilesList(ScanResults scanResults, int size){
        badPathList= new ArrayList<>();
        for(int i=0; i< size; i++){
            badPathList.add(Paths.get("file" + i));
        }
        scanResults.setBadFilesPathList(badPathList);
    }


    //    @Test
    public void TestDBGetLastSession() {
        DB db = new DB();
        try {
            AbstractSession lastSession = db.getLastSession();
            logger.debug("Last session name is " + lastSession);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    //    @Test
    public void TestInsertSession() {
        try {
            new DB().saveSession();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    //    @Test
    public void testScanSaving() {
        long time = new Timestamp(new Date().getTime()).getTime();
        logger.debug("Time is " + String.valueOf(time));
    }

//    @Test
    public void testSaveScanResults(){
        ScanResults scanResults = new ScanResults(
                new Timestamp(new Date().getTime()).getTime());
        scanResults.setFoundPdfBooksCount(5);
        scanResults.setFoundfb2BooksCount(5);
        initListOfBooks(scanResults, 10);
        initEmptyBooks(scanResults,5);
        initUndefinedBooks(scanResults, 5);
        scanResults.setBookList(bookList);
        scanResults.setUndefinedBookList(undefinedBookList);
        scanResults.setEmptyBookList(emptyBookList);

        DB db = new DB();
        try {
            int booksSizeBefore = db.getBookSize();
            int emptyBooksSizeBefore = db.getEmptyBookSize();
            int undefinedBooksSizeBefore = db.getUndefinedBookSize();

            db.saveSession();
            db.saveScanResults(scanResults, Session.getSessionName());

            int booksSizeAfter = db.getBookSize();
            int emptyBooksSizeAfter = db.getEmptyBookSize();
            int undefinedBooksSizeAfter= db.getUndefinedBookSize();

            Assert.assertEquals(booksSizeBefore + 10, booksSizeAfter);
            Assert.assertEquals(emptyBooksSizeBefore + 5, emptyBooksSizeAfter);
            Assert.assertEquals(undefinedBooksSizeBefore + 5, undefinedBooksSizeAfter);

        } catch (SQLException e) {
            logger.error(e.getMessage() + e.getErrorCode());
            e.printStackTrace();
        }
    }

//    @Test
    public void testSaveScanResultsPath(){
        ScanResults scanResults = new ScanResults(
                new Timestamp(new Date().getTime()).getTime());
        initScannedFilesList(scanResults,10);
        initBadFilesList(scanResults,5);
        initIgnoredFilesList(scanResults,2);

        logger.debug("Session name" + Session.getSessionName());
        logger.debug("Scan id" + scanResults.getScan_id());

        DB db = new DB();
        try {
            db.saveSession();
            db.saveScanResults(scanResults, Session.getSessionName());
        } catch (SQLException e) {
            logger.error(Utils.printStackTrace(e).toString());
        }
    }

//    @Test
    public void testGetScanResults(){
        DB db = new DB();
        try {
            ScanResults scanResult = db.getScanResult(Long.valueOf("1461731856555"));
            logger.debug(scanResult.toString());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

//    @Test
    public void testGetScanBookList() {
        DB db = new DB();
        List<Book> scanBooksList = null;
        try {
            scanBooksList = db.getScanBooksList(Long.valueOf("1461738825600"), true);
            logger.debug("Books size" + scanBooksList.size());
            scanBooksList.forEach(book -> logger.debug(book.toString()));
        } catch (SQLException e) {
            logger.error(Utils.printStackTrace(e).toString());
        }

    }

    @Test
    public void testGetUndefinedBooksList() {
        DB db = new DB();
        List<Book> scanUndefinedBooksList = null;
        try {
            scanUndefinedBooksList = db.getScanUndefinedBooksList(Long.valueOf("1461738825600"), true);
            logger.debug("Undefined Books size " + scanUndefinedBooksList.size());
            scanUndefinedBooksList.forEach(book -> logger.debug(book.toString()));
        } catch (SQLException e) {
            logger.error(Utils.printStackTrace(e).toString());
        }

    }

    @Test
    public void testGeEmptyBooksList(){
        DB db = new DB();
        try {
            List<Book> scanEmptyBooksList = db.getScanEmptyBooksList(Long.valueOf("1461738825600"), true);
            logger.debug("Empty Books Size " + scanEmptyBooksList.size());
            scanEmptyBooksList.forEach(book -> logger.debug(book.toString()));
        } catch (SQLException e) {
            logger.error(Utils.printStackTrace(e).toString());
        }
    }
}
