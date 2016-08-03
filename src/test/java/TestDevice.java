import ch.qos.logback.classic.Logger;
import org.home.utils.Device;
import org.junit.Test;
import org.slf4j.LoggerFactory;

public class TestDevice {
    public static final Logger logger =
            (ch.qos.logback.classic.Logger)  LoggerFactory.getLogger(Device.class);

    @Test
    public void testDevicee(){
        Device device = new Device();
        device.init();
        logger.debug(device.getHomeDir());
        logger.debug(device.getOsArch());
        logger.debug(device.getOsName());
        logger.debug(device.getOsVersion());
        logger.debug(device.getUserName());

        logger.debug(device.toString());
    }
}
