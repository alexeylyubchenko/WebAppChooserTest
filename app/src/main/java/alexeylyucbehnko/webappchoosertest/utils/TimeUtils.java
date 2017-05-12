package alexeylyucbehnko.webappchoosertest.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Singleton;

/**
 * Created by ally on 11/05/17.
 */

@Singleton
public class TimeUtils {
    public static String getCurrentTimeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }
}
