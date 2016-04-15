import org.home.utils.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import static org.junit.Assert.assertEquals;

public class UtilsTest {
    private static final Logger logger =
            LoggerFactory.getLogger(UtilsTest.class);

    @Test
    public void testCalculoateSize(){
        //5.3 mb
        Path file = Paths.get("/media/MegaHard/Book/Java_/spring-framework-reference.4.2.4.pdf");
        try {
            String size = FileUtils.calculateFileSize(file);
            logger.debug("Method File size is " + size);

            double size1 = Files.size(file);
            logger.debug("Calculated fs " + size1);
            double l = size1 / 1024 / 1024;

            logger.debug("Calculated fs in mb " + l);
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String format = decimalFormat.format(l) + " Mb";
            logger.debug("Formatted size " + format);
            assertEquals(format, size);

        } catch (IOException e) {
           logger.error(e.getMessage());
        }
    }
}
