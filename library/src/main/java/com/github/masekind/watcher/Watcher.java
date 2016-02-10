package com.github.masekind.watcher;

import android.util.Log;

/**
 * "Quis custodiet ipsos custodes"
 *
 * @author Jan-Louis Crafford
 *         Created on 2015/08/13.
 */
public class Watcher {

    private static final String TAG = "=======Watcher=======";

    public static final int NONE = -1;
    public static final int VERBOSE = Log.VERBOSE;
    public static final int DEBUG = Log.DEBUG;
    public static final int INFO = Log.INFO;
    public static final int WARN = Log.WARN;
    public static final int ERROR = Log.ERROR;
    public static final int ASSERT = Log.ASSERT;

    private static NativeLogger sNativeLogger = new NativeLogger();

    // Loggable to be used to print logs.
    private static Loggable sLogger;

    /**
     * Returns the next Loggable object
     */
    public static Loggable getLogger() {
        return sLogger;
    }

    /**
     * Sets the next Loggable to be used
     */
    public static void setLogger(Loggable logger) {
        sLogger = logger;
    }

    private static String getPrefix() {
        StackTraceElement element = new Throwable().getStackTrace()[3];
        //String className = element.getClassName();
        String methodName = element.getMethodName();
        String fileName = element.getFileName().replace(".java", "");
        int lineNumber = element.getLineNumber();

        return fileName
                + "."
                + methodName
                + ":"
                + lineNumber;
    }

    /**
     * Prints the provided logging info to the Loggable object
     *
     * @param priority Log level.
     * @param msg      Actual msg to be logged.
     * @param tr       Optional exception data to be logged.
     */
    private static void println(int priority, String msg, Throwable tr) {
        String prefix = getPrefix();

        sNativeLogger.println(priority, TAG, prefix, msg, tr);

        if (sLogger != null) {
            sLogger.println(priority, TAG, prefix, msg, tr);
        }
    }

    /**
     * Prints a VERBOSE logging message.
     */
    public static void v(String msg, Throwable tr) {
        println(VERBOSE, msg, tr);
    }

    public static void v(String msg) {
        println(VERBOSE, msg, null);
    }

    /**
     * Prints a DEBUG logging message.
     */
    public static void d(String msg, Throwable tr) {
        println(DEBUG, msg, tr);
    }

    public static void d(String msg) {
        println(DEBUG, msg, null);
    }

    /**
     * Prints a INFO logging message.
     */
    public static void i(String msg, Throwable tr) {
        println(INFO, msg, tr);
    }

    public static void i(String msg) {
        println(INFO, msg, null);
    }

    /**
     * Prints a WARN logging message.
     */
    public static void w(String msg, Throwable tr) {
        println(WARN, msg, tr);
    }

    public static void w(String msg) {
        println(WARN, msg, null);
    }

    public static void w(Throwable tr) {
        println(WARN, null, tr);
    }

    /**
     * Prints a ERROR logging message.
     */
    public static void e(String msg, Throwable tr) {
        println(ERROR, msg, tr);
    }

    public static void e(String msg) {
        println(ERROR, msg, null);
    }

    public static void e(Throwable tr) {
        println(ERROR, null, tr);
    }
}
