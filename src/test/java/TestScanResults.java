import org.home.entity.Book;
import org.home.entity.Fb2Book;
import org.home.exceptions.BadInputListDataException;
import org.home.scanner.BookScanner;
import org.home.scanner.ScanResults;
import org.home.utils.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestScanResults {
    private static final Logger logger =
            LoggerFactory.getLogger(UtilsTest.class);

    private List<Path> includedPathList;
    private List<Path> excludedPathList;

    public void init(){
        logger.debug("Prepare pdf test data");
        FileUtils.createTempoDirectory();
        includedPathList = new ArrayList<>();
        excludedPathList = new ArrayList<>();

        includedPathList.add(Paths.get("testDir/"));

        //1 skipped file
        excludedPathList.add(Paths.get("testDir/Backbone.js Essentials.pdf"));
    }

    public void fb2init(){
        logger.debug("Prepare test data");
        includedPathList = new ArrayList<>();
        excludedPathList = new ArrayList<>();

        includedPathList.add(Paths.get("testDir/Tolstoy Leo. Anna Karenina.fb2"));
        includedPathList.add(Paths.get("testDir/Достоевский Федор. Том 9. Братья Карамазовы.fb2"));
    }

    @Test
    public void testSuccessFullScanResult(){
        String method_name = new Object() {}.getClass().getEnclosingMethod().getName();

        logger.debug("Start " + method_name + " test method");
        init();
        BookScanner bookScanner = new BookScanner(includedPathList, excludedPathList);
        ScanResults scanResults = null;
        try {
            scanResults = bookScanner.scan();

            int foundDocBooksCount = scanResults.getFoundDocBooksCount();
            logger.debug("Asserting doc file count. value " + scanResults.getFoundDocBooksCount());
            assertEquals(0, foundDocBooksCount);

            logger.debug("Booklist array count " + scanResults.getBookList().size());
            int foundPdfBooksCount = scanResults.getFoundPdfBooksCount();
            logger.debug("Asserting pdf file count, value " + scanResults.getFoundPdfBooksCount());
            assertEquals(2, foundPdfBooksCount);

            logger.debug("Asserting not appropriate files count. value " + scanResults.getBadFilesPathList().size());
            assertEquals(0, scanResults.getBadFilesPathList().size());

            logger.debug("Asserting skipped files size counter. value " + scanResults.getIgnoredPathFileList().size());
            assertEquals(1, scanResults.getIgnoredPathFileList().size());


        } catch (BadInputListDataException e) {
            logger.error(e.getMessage());
        }finally {
            logger.debug("End of  " + method_name + " test method");
        }
    }

    @Test
    public void testSuccessFullScanF2b(){
        fb2init();

        String method_name = new Object() {}.getClass().getEnclosingMethod().getName();

        logger.debug("Start " + method_name + " test method");
        BookScanner bookScanner = new BookScanner(includedPathList, excludedPathList);
        ScanResults scanResults = null;
        try {
            scanResults = bookScanner.scan();

            int foundfb2BooksCount = scanResults.getFoundfb2BooksCount();
            assertEquals(2, foundfb2BooksCount);

            scanResults.getBookList()
                    .stream()
                    .filter(b -> b instanceof Fb2Book)
                    .forEach(b -> {
                        Fb2Book f2book = (Fb2Book) b;
                        logger.debug("Book is " + f2book);
                    });

            Fb2Book fb2Book = new Fb2Book();
            logger.trace("book id is " + fb2Book.getId());

            Fb2Book fb2Book2 = new Fb2Book();
            logger.trace("book id is " + fb2Book2.getId());

            Book book = new Book();
            logger.trace("book id is " + book.getId());

        } catch (BadInputListDataException e) {
            logger.error(e.getMessage());
        }finally {
            logger.debug("End of  " + method_name + " test method");
        }
    }

    @Test(expected = BadInputListDataException.class)
    public void testOnEmptyInputLists() throws BadInputListDataException {
        String method_name = new Object() {}.getClass().getEnclosingMethod().getName();
        logger.debug("Start " + method_name + " test method");
        BookScanner bookScanner = new BookScanner(includedPathList, excludedPathList);

        bookScanner.scan();
    }

    @Test
    public void testOnEmptyIgnoreList(){
        String method_name = new Object() {}.getClass().getEnclosingMethod().getName();

        logger.debug("Start " + method_name + " test method");
        init();
        BookScanner bookScanner = new BookScanner(includedPathList, null);
        BookScanner emptyExlBookScan = new BookScanner(includedPathList, Collections.emptyList());
        try {
            ScanResults scanResults = bookScanner.scan();
            assertEquals(7, scanResults.getBookList().size());
            logger.debug("Scanned " + scanResults.getBookList().size() + " books");

            ScanResults scan = emptyExlBookScan.scan();
            assertEquals(7, scan.getBookList().size());
            logger.debug("Scanned " + scan.getBookList().size() + " books");

        } catch (BadInputListDataException e) {
            logger.debug(e.getMessage());
        }finally {
            logger.debug("End oexcludedPathListf  " + method_name + " test method");
        }
    }

    @Test
    public void testOnNotExistedFiles() throws BadInputListDataException {
        FileUtils.createTempoDirectory();
        includedPathList = new ArrayList<>();
        excludedPathList = new ArrayList<>();

        includedPathList.add(Paths.get("testDi2222222r/"));

        BookScanner bookScanner = new BookScanner(includedPathList, excludedPathList);

        bookScanner.scan();
    }

    @Test
    public void testOnEmptyFiles(){

    }
}
