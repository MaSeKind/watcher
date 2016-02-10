package com.github.masekind.watcher;

import android.util.Log;

/**
 * Outputs logging messages to the native Android {@link Log} utility
 *
 * @author Jan-Louis Crafford
 *         Created on 2015/08/13.
 */
public class NativeLogger implements Loggable {

    private String mCustomTag;

    public NativeLogger() {
    }

    public NativeLogger(String tag) {
        mCustomTag = tag;
    }

    @Override
    public void println(int priority, String tag, String prefix, String msg, Throwable tr) {
        // There actually are log methods that don't take a msg parameter.  For now,
        // if that's the case, just convert null to the empty string and move on.
        String useMsg = msg;
        if (useMsg == null) {
            useMsg = "";
        }

        // If an exception was provided, convert that exception to a usable string and attach
        // it to the end of the msg method.
        if (tr != null) {
            useMsg += "\n" + Log.getStackTraceString(tr);
        }

        if (mCustomTag != null) {
            tag = mCustomTag;
        }

        // This is functionally identical to Log.x(tag, useMsg);
        // For instance, if priority were Log.VERBOSE, this would be the same as Log.v(tag, useMsg)
        Log.println(priority, tag, useMsg);
    }
}
