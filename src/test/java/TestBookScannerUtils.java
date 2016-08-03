import org.home.scanner.BookScanner;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestBookScannerUtils {
    private static final Logger logger =
            LoggerFactory.getLogger(TestBookScannerUtils.class);

    public Class[] pathClass;
    public Class[] pathListClass;
    public Class bookScannercls;
    public Object bookScannerobj;

    @Before
    public void init() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        pathClass = new Class[1];
        pathClass[0] = Path.class;

        pathListClass = new Class[2];
        pathListClass[0] = List.class;
        pathListClass[1] = Path.class;

        bookScannercls = Class.forName("org.home.scanner.BookScanner");
        bookScannerobj = bookScannercls.newInstance();
    }

    @Test
    public void TestGetFileExtension(){
        Method getFileExtension = null;
        try {
            init();
            getFileExtension = BookScanner.class.getDeclaredMethod("getFileExtension", pathClass);
            getFileExtension.setAccessible(true);
            String[] result = (String[]) getFileExtension.invoke(bookScannerobj, Paths.get("/media/MegaHard/Book/Java_/spring-framework-reference.4.2.4.pdf"));
            assertEquals(2, result.length);
            assertEquals("pdf", result[1]);
            assertEquals("spring-framework-reference.4.2.4", result[0]);

            Path strangeFile = Paths.get("/sssrerer");
            String[] strangeResult = (String[]) getFileExtension.invoke(bookScannerobj, strangeFile);
            assertEquals("sssrerer", strangeResult[0]);
            assertEquals("undefined", strangeResult[1]);

        } catch (NoSuchMethodException | ClassNotFoundException |
                IllegalAccessException | InstantiationException | InvocationTargetException e) {
            logger.debug("Too hard");
            e.printStackTrace();
        }
    }

    @Test
    public void TestIsContains() throws InvocationTargetException {
        Method isContains = null;
        try{
            init();
            isContains = BookScanner.class.getDeclaredMethod("isContains", pathListClass);
            isContains.setAccessible(true);

            Path file = Paths.get("/media/MegaHard/Book/Java_/spring-framework-reference.4.2.4.pdf");
            Path notExistedFile = Paths.get("/sss.txt");

            List<Path> pathList = new ArrayList<>();
            pathList.add(file);

            boolean invokeTrue = (boolean) isContains.invoke(bookScannerobj, pathList, file);
            boolean invokeFalse = (boolean) isContains.invoke(bookScannerobj, pathList, notExistedFile);

            assertEquals(true, invokeTrue);
            assertEquals(false, invokeFalse);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
