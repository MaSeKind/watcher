package com.github.masekind.watcher;

/**
 * Basic interface for making a loggable class.
 *
 * @author Jan-Louis Crafford
 *         Created on 2015/08/13.
 */
public interface Loggable {

    /**
     * Prints the provided data to the logging system
     *
     * @param priority Log level; Verbose, Error, Debug, etc.
     * @param tag      Tag to display for log.
     * @param prefix   Tag msg prefix, usually the class & method name plus line number call was made from
     * @param msg      Actual message to be printed.
     * @param tr       Optional exception to print out.
     */
    void println(int priority, String tag, String prefix, String msg, Throwable tr);

}
