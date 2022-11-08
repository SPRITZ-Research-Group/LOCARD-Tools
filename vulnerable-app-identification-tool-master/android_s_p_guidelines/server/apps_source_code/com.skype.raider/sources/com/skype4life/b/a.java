package com.skype4life.b;

import android.os.Process;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public final class a extends Formatter {
    public static final String a = System.getProperty("line.separator");
    private static final String b = String.format("%5d", new Object[]{Integer.valueOf(Process.myPid())});
    private final ThreadLocal<Calendar> c = new ThreadLocal<Calendar>(this) {
        final /* synthetic */ a a;

        {
            this.a = this$0;
        }

        public final /* synthetic */ Object initialValue() {
            return Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        }
    };
    private final ThreadLocal<String> d = new ThreadLocal<String>(this) {
        final /* synthetic */ a a;

        {
            this.a = this$0;
        }

        public final /* synthetic */ Object initialValue() {
            return String.format("%5d", new Object[]{Integer.valueOf(Process.myTid())});
        }
    };

    private static void a(StringBuilder builder, int value) {
        if (value < 10) {
            builder.append(0);
        }
        builder.append(value);
    }

    public final String format(LogRecord logRecord) {
        char c;
        StringBuilder result = new StringBuilder(512);
        StringBuilder stringBuilder = new StringBuilder(64);
        Calendar calendar = (Calendar) this.c.get();
        calendar.setTimeInMillis(logRecord.getMillis());
        a(stringBuilder, calendar.get(2) + 1);
        stringBuilder.append('-');
        a(stringBuilder, calendar.get(5));
        stringBuilder.append(' ');
        a(stringBuilder, calendar.get(11));
        stringBuilder.append(':');
        a(stringBuilder, calendar.get(12));
        stringBuilder.append(':');
        a(stringBuilder, calendar.get(13));
        stringBuilder.append('.');
        int i = calendar.get(14);
        if (i < 100) {
            stringBuilder.append(0);
        }
        a(stringBuilder, i);
        StringBuilder append = stringBuilder.append(' ').append(b).append(' ').append((String) this.d.get()).append(' ');
        i = logRecord.getLevel().intValue();
        if (i == Level.SEVERE.intValue()) {
            c = 'E';
        } else if (i == Level.WARNING.intValue()) {
            c = 'W';
        } else if (i == Level.CONFIG.intValue() || i == Level.INFO.intValue()) {
            c = 'I';
        } else if (i == Level.FINE.intValue()) {
            c = 'D';
        } else {
            c = 'V';
        }
        append.append(c).append(' ').append(logRecord.getLoggerName()).append(": ");
        String prefix = stringBuilder.toString();
        result.append(prefix).append(logRecord.getMessage()).append(a);
        Throwable throwable = logRecord.getThrown();
        if (throwable != null) {
            result.append(prefix).append(throwable.getMessage());
            result.append(a);
            for (StackTraceElement stackFrame : throwable.getStackTrace()) {
                result.append(prefix).append(9).append(stackFrame.getClassName()).append('.').append(stackFrame.getMethodName()).append('(').append(stackFrame.getFileName()).append(':').append(stackFrame.getLineNumber()).append(')').append(a);
            }
        }
        return result.toString();
    }
}
