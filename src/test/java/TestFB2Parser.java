import ch.qos.logback.classic.Level;
import org.dom4j.DocumentException;
import org.home.entity.Book;
import org.home.entity.Fb2Book;
import org.home.parsers.FB2Parser;
import org.junit.Before;
import org.junit.Test;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestFB2Parser {
    private static final Logger logger =
            (Logger) LoggerFactory.getLogger(TestFB2Parser.class);
    private List<Path> fb2FilesList;
    private List<Book> bookList;

    @Before
    public void init(){
        logger.setLevel(Level.TRACE);

        Path remark = Paths.get("/media/MegaHard/Book/___худ лит/Tri tovarischa.fb2");
        Path rend = Paths.get("/media/MegaHard/Book/___худ лит/Rend_Istochnik.204899.fb2");
        Path carrol = Paths.get("/media/MegaHard/Book/___худ лит/кэррол_джим_дневники_баскетболиста_(basketball_diaries).fb2");

        fb2FilesList = new ArrayList<>();
        bookList = new ArrayList<>();

        fb2FilesList.add(remark);
        fb2FilesList.add(rend);
        fb2FilesList.add(carrol);

        bookList.add(new Fb2Book());
        bookList.add(new Fb2Book());
        bookList.add(new Fb2Book());
    }

//    @Test
//    public void testFb2(){
//        logger.debug(bookList.get(0).getClass().getName());
//        Fb2Book fb2Book = (Fb2Book) bookList.get(0);
//        fb2Book.setEncoding("sss");
//    }

    @Test
    public void testParser(){
        logger.info("Test testParser started");

        FB2Parser fb2Parser = new FB2Parser();

        for (int i = 0; i < 3; i++) {
            try {
                fb2Parser.startPArse(fb2FilesList.get(i), bookList.get(i));
            } catch (DocumentException | IOException e) {
                logger.error(e.getMessage());
            }
        }

        logger.debug("booklist size " + bookList.size());
        for(Book b: bookList){
//            logger.trace(b.toString());
            Fb2Book b1 = (Fb2Book) b;
            logger.trace(b1.toString());
        }


    }
}
