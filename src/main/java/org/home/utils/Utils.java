package org.home.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by bitybite on 5/2/16.
 */
public class Utils {

    public static StringWriter printStackTrace(Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw;
    }
}
