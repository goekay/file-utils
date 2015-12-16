package com.goekay.fileutils;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 29.11.2015
 */
public final class Utils {
    private Utils() { }

    public static void print(String message) {
        System.out.println(message);
    }

    public static void warn(String message) {
        print("[Warn] " + message);
    }

    public static void inputError(Exception e) {
        print("[Error] Input parameters are invalid: " + e.getMessage());
    }

    public static String sep() {
        return System.lineSeparator();
    }
}
