package org.tinlone.demo.rxjavasample.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TimeFormatUtil {

    private static SimpleDateFormat sdfHms = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);

    private TimeFormatUtil() {
    }

    public static String hms(long mill) {
        return sdfHms.format(mill);
    }

}
