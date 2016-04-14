import org.home.entity.Book;
import org.home.entity.Fb2Book;
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

    public void init(){
        logger.debug("Prepare pdf test data");
        includedPathList = new ArrayList<>();
        excludedPathList = new ArrayList<>();

        includedPathList.add(Paths.get("/media/MegaHard/Book/Java_/spring-framework-reference.4.2.4.pdf"));
        //7 book files, 1 subdir, 6 pdf, 1 doc, 1 not appropriate file(is not a book at all)
        includedPathList.add(Paths.get("/media/MegaHard/Book/Java_/Java train/"));

        //1 skipped file
        excludedPathList.add(Paths.get("/media/MegaHard/Book/Java_/Java train/Effective.Java.2nd.Edition.May.2008.3000th.Release.pdf"));
    }

    public void fb2init(){
        logger.debug("Prepare test data");
        includedPathList = new ArrayList<>();
        excludedPathList = new ArrayList<>();

        includedPathList.add(Paths.get("/media/MegaHard/Book/___худ лит/Rend_Istochnik.204899.fb2"));
        includedPathList.add(Paths.get("/media/MegaHard/Book/___худ лит/Tri tovarischa.fb2"));
        includedPathList.add(Paths.get("/media/MegaHard/Book/___худ лит/кэррол_джим_дневники_баскетболиста_(basketball_diaries).fb2"));
    }

    @Test
    public void testSuccessFullScanResult(){
        String method_name = new Object() {}.getClass().getEnclosingMethod().getName();

        logger.debug("Start " + method_name + " test method");
        init();
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
    public void testSuccessFullScanF2b(){
        fb2init();

        String method_name = new Object() {}.getClass().getEnclosingMethod().getName();

        logger.debug("Start " + method_name + " test method");
        BookScanner bookScanner = new BookScanner(includedPathList, excludedPathList);
        ScanResults scanResults = bookScanner.scan();

        int foundfb2BooksCount = scanResults.getFoundfb2BooksCount();
        assertEquals(3, foundfb2BooksCount);

        for(Book b: scanResults.getBookList()){
            if(b instanceof Fb2Book){
                    Fb2Book f2book = (Fb2Book) b;
                    logger.debug("Book is " + f2book);
            }
        }

        Fb2Book fb2Book = new Fb2Book();
        logger.trace("book id is " + fb2Book.getId());

        Fb2Book fb2Book2 = new Fb2Book();
        logger.trace("book id is " + fb2Book2.getId());

        Book book = new Book();
        logger.trace("book id is " + book.getId());
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
