import org.home.entity.Book;
import org.home.entity.Fb2Book;
import org.home.scanner.ScanResults;
import org.home.utils.AbstractSession;
import org.home.utils.DB;
import org.home.utils.Session;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestDbConnection {
    private static final Logger logger =
            LoggerFactory.getLogger(TestDbConnection.class);
    private String db_name = "jdbc:sqlite:db.sqlite";
    private Session session;
    private List<Book> books;

    @Before
    public void initSession() {
        session = new Session();
    }

    public void initListOfbooks(ScanResults scanResults){
        books = new ArrayList<>(10);
        for(int i = 0; i< 10; i++){
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
                books.add(book);
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
                books.add(book);
                logger.debug("Fb2 book instance");
            }

        }
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

    @Test
    public void testSaveScanResults(){
        ScanResults scanResults = new ScanResults(
                new Timestamp(new Date().getTime()).getTime());
        scanResults.setFoundPdfBooksCount(5);
        scanResults.setFoundfb2BooksCount(5);
        initListOfbooks(scanResults);
        scanResults.setBookList(books);

        DB db = new DB();
        try {
            db.saveSession();
            db.saveScanResults(scanResults, Session.getSessionName());
        } catch (SQLException e) {
            logger.error(e.getMessage() + e.getErrorCode());
            e.printStackTrace();
        }
    }

    @Test
    public void testBookScanId(){

    }
}
