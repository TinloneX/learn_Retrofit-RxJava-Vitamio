package org.tinlone.demo.rxjavasample.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TimeFormatUtil {

    private TimeFormatUtil() {
    }

    private static SimpleDateFormat sdfHms = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);

    public static String hms(long mill) {
        return sdfHms.format(mill);
    }

}
