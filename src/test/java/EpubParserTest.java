import ch.qos.logback.classic.Logger;
import junit.framework.Assert;
import org.home.entity.Book;
import org.home.parsers.EpubParser;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bitybite on 4/15/16.
 */
public class EpubParserTest {
    private static final Logger logger =
            (Logger) LoggerFactory.getLogger(TestFB2Parser.class);
    private List<Path> pathList;
    private List<Book> bookList;

//    @Before
    public void init(){
        pathList = new ArrayList<>();
        bookList = new ArrayList<>();

        pathList.add(Paths.get("/media/MegaHard/Book/Java_/spring-framework-reference.epub"));
        pathList.add(Paths.get("/media/MegaHard/Book/Java_/Peter A. Pilgrim - Java EE 7 Developer Handbook - 2013/" +
                "Peter A. Pilgrim - Java EE 7 Developer Handbook - 2013.epub"));
        pathList.add(Paths.get("/media/MegaHard/Book/Java_/_FX/Hendrik Ebbers - Mastering JavaFX 8 Controls - 2014.epub"));
        pathList.add(Paths.get("/media/MegaHard/Book/Java_/Reese R.M., Reese J. L. - Java 7 New Features - 2012/" +
                "Reese R.M., Reese J. L. - Java 7 New Features - 2012.epub"));
        pathList.add(Paths.get("/media/MegaHard/Book/Java_/Mohamed Taman - JavaFX Essentials - 2015/" +
                "Mohamed Taman - JavaFX Essentials - 2015.epub"));
        pathList.add(Paths.get("/media/MegaHard/Book/Java_/Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft - Java 8 in Action, Lambdas Streams and Functional-Style Programming - 2014.epub"));
        pathList.add(Paths.get("/media/MegaHard/Book/Эшли Вэнс - " +
                "Илон Маск Tesla, SpaceX и дорога в будущее - 2015/" +
                "Эшли Вэнс - Илон Маск Tesla, SpaceX и дорога в будущее - 2015.epub"));


        bookList.add(new Book());
        bookList.add(new Book());
        bookList.add(new Book());
        bookList.add(new Book());
        bookList.add(new Book());
        bookList.add(new Book());
        bookList.add(new Book());
    }

    public void initBadFile(){
     pathList = new ArrayList<>();
        pathList.add(Paths.get("/media/MegaHard/Book/Java_/ss/bad.epub"));
        pathList.add(Paths.get("/media/MegaHard/Book/Java_/ss/bad2.epub"));

        bookList = new ArrayList<>();
        bookList.add(new Book());
        bookList.add(new Book());
    }

    @Test
    public void testParse() throws IOException {
        logger.debug("TestParse method started");
        init();
        EpubParser epubParser = new EpubParser();

        for (int i =0; i< pathList.size(); i++){
            epubParser.parse(pathList.get(i), bookList.get(i));
        }

        bookList.forEach(book->{
            logger.debug("Checking parse epub book title on null: " + book.getTitle());
            org.junit.Assert.assertNotNull(book.getTitle());
        });

        logger.debug("TestParse method finished");
    }

    @Test
    public void testDeflatedEpubFiles(){
        initBadFile();
        EpubParser epubParser = new EpubParser();
        try {
            for (int i =0; i< pathList.size(); i++){
                epubParser.parse(pathList.get(i), bookList.get(i));
            }
        } catch (IOException e) {
            logger.error("reading error" + e.getMessage());
        }
    }




}
