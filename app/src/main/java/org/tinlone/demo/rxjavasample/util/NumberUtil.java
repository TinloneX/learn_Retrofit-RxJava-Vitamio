package org.tinlone.demo.rxjavasample.util;

import android.support.annotation.Size;

public class NumberUtil {

    private static StringBuilder h(int number) {
        StringBuilder builder = new StringBuilder(
                Integer.toHexString(number & 0xff));
        while (builder.length() < 2) {
            builder.append("0");
        }
        return builder;
    }

    public static String hex(int number) {
        return h(number).toString().toUpperCase();
    }

    public static String hex(int... numbers) {
        return hex("", numbers);
    }

    public static String hex(String start, int... numbers) {
        StringBuilder builder = new StringBuilder(start);
        for (int number : numbers) {
            builder.append(hex(number));
        }
        return builder.toString().toUpperCase();
    }

    public static String color(@Size(min = 3, max = 4) int... numbers) {
        return hex("#", numbers);
    }
}
