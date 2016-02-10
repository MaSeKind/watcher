package com.github.masekind.watcher;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Jan-Louis Crafford
 *         Created on 2015/08/13.
 */
public class DiskLogger implements Loggable {

    private static final String[] LOG_TAGS = {"N/A", "N/A",
            "V", "D", "I", "W", "E", "A"};

    private static final String DATE_PATTERN = "MMM d hh:mm:ss";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.US);

    private Context context;

    public DiskLogger(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void println(int priority, String tag, String prefix, String msg, Throwable tr) {
        try {
            File file = new File(context.getExternalCacheDir(), "watcher.log");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            String date = dateFormat.format(new Date(System.currentTimeMillis()));

            StringBuilder builder = new StringBuilder();
            builder.append(date)
                    .append(" ")
                    .append(prefix)
                    .append(" [")
                    .append(LOG_TAGS[priority])
                    .append("] ")
                    .append(msg);

            writer.append(builder.toString());
            writer.newLine();

            if (tr != null) {
                builder = new StringBuilder();
                builder.append(date)
                        .append(" ")
                        .append(prefix)
                        .append(" [")
                        .append(LOG_TAGS[priority])
                        .append("] ")
                        .append(tr.toString());

                writer.append(builder.toString());
                writer.newLine();

                if (tr.getStackTrace() != null) {
                    StackTraceElement[] stackTraceElements = tr.getStackTrace();

                    int count = stackTraceElements.length > 5 ? 5 : stackTraceElements.length;
                    for (int i = 0; i < count; i++) {
                        builder = new StringBuilder();
                        builder.append(date)
                                .append(" ")
                                .append(prefix)
                                .append(" [")
                                .append(LOG_TAGS[priority])
                                .append("] ")
                                .append("\tat ")
                                .append(stackTraceElements[i].toString());

                        writer.append(builder.toString());
                        writer.newLine();
                    }
                }
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
