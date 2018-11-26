package org.tinlone.demo.rxjavasample.util;

public class Timer {

    private static long timer = 0L;

    public static String start() {
        timer = System.currentTimeMillis();
        return TimeFormatUtil.hms(timer);
    }

    public static String end() {
        long millis = System.currentTimeMillis();
        long time = millis - timer;
        String result = TimeFormatUtil.hms(millis);
        result += "<br/>执行总用时:" + TimeFormatUtil.hms(time) + "  " + time + "ms";
        timer = millis;
        return result;
    }

}
