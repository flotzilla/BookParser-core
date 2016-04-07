import org.home.scanner.BookScanner;
import org.home.scanner.ScanResults;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestScanResults {
    private static final Logger logger =
            LoggerFactory.getLogger(UtilsTest.class);

    private List<Path> includedPathList;
    private List<Path> excludedPathList;

    @Before
    public void init(){
        logger.debug("Prepare test data");
        includedPathList = new ArrayList<>();
        excludedPathList = new ArrayList<>();

        includedPathList.add(Paths.get("/media/MegaHard/Book/Java_/spring-framework-reference.4.2.4.pdf"));
        //7 book files, 1 subdir, 6 pdf, 1 doc, 1 not appropriate file(is not a book at all)
        includedPathList.add(Paths.get("/media/MegaHard/Book/Java_/Java train/"));

        //1 skipped file
        excludedPathList.add(Paths.get("/media/MegaHard/Book/Java_/Java train/Effective.Java.2nd.Edition.May.2008.3000th.Release.pdf"));
    }

    @Test
    public void testSuccessFullScanResult(){
        String method_name = new Object() {}.getClass().getEnclosingMethod().getName();

        logger.debug("Start " + method_name + " test method");
        BookScanner bookScanner = new BookScanner(includedPathList, excludedPathList);
        ScanResults scanResults = bookScanner.scan();

        int foundDocBooksCount = scanResults.getFoundDocBooksCount();
        logger.debug("Asserting doc file count. value " + scanResults.getFoundDocBooksCount());
        assertEquals(1, foundDocBooksCount);

        logger.debug("Booklist array count " + scanResults.getBookList().size());
        int foundPdfBooksCount = scanResults.getFoundPdfBooksCount();
        logger.debug("Asserting pdf file count, value " + scanResults.getFoundPdfBooksCount());
        assertEquals(6, foundPdfBooksCount);

        logger.debug("Asserting not appropriate files count. value " + scanResults.getBadFilesPathList().size());
        assertEquals(0, scanResults.getBadFilesPathList().size());

        logger.debug("Asserting skipped files size counter. value " + scanResults.getIgnoredPathFileList().size());
        assertEquals(2, scanResults.getIgnoredPathFileList().size());

        logger.debug("End of  " + method_name + " test method");

    }

    @Test
    public void testOnEmptyInputLists(){

    }

    @Test
    public void testOnEmptyIgnoreList(){

    }

    @Test
    public void testOnNotExistedFiles(){

    }

    @Test
    public void testOnEmptyFiles(){

    }
}
