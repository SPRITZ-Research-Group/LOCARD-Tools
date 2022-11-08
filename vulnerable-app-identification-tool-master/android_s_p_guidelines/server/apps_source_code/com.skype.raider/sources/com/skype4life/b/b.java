package com.skype4life.b;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class b implements com.facebook.common.logging.b {
    private final ThreadLocal<Map<String, Logger>> a = new ThreadLocal<Map<String, Logger>>(this) {
        final /* synthetic */ b a;

        {
            this.a = this$0;
        }

        protected final /* synthetic */ Object initialValue() {
            return new HashMap();
        }
    };
    private int b = 5;

    public final void a(int level) {
        this.b = level;
    }

    public final int b() {
        return this.b;
    }

    public final boolean b(int level) {
        return this.b <= level;
    }

    public final void a(String tag, String msg) {
        a(2, tag, msg);
    }

    public final void a(String tag, String msg, Throwable tr) {
        a(2, tag, msg, tr);
    }

    public final void b(String tag, String msg) {
        a(3, tag, msg);
    }

    public final void b(String tag, String msg, Throwable tr) {
        a(3, tag, msg, tr);
    }

    public final void c(String tag, String msg) {
        a(4, tag, msg);
    }

    public final void c(String tag, String msg, Throwable tr) {
        a(4, tag, msg, tr);
    }

    public final void d(String tag, String msg) {
        a(5, tag, msg);
    }

    public final void d(String tag, String msg, Throwable tr) {
        a(5, tag, msg, tr);
    }

    public final void e(String tag, String msg) {
        a(6, tag, msg);
    }

    public final void e(String tag, String msg, Throwable tr) {
        a(6, tag, msg, tr);
    }

    public final void f(String tag, String msg) {
        a(6, tag, msg);
    }

    public final void f(String tag, String msg, Throwable tr) {
        a(6, tag, msg, tr);
    }

    private void a(int priority, String tag, String msg) {
        a(tag).log(c(priority), msg);
        a(msg, null);
    }

    private void a(int priority, String tag, String msg, Throwable tr) {
        a(tag).log(c(priority), msg, tr);
        a(msg, tr);
    }

    private static void a(String msg, Throwable tr) {
        if (tr != null) {
            new StringBuilder().append(msg).append("\n").append(Log.getStackTraceString(tr));
        }
    }

    private Logger a(String tag) {
        Map<String, Logger> loggersMap = (Map) this.a.get();
        String normalizedTag = tag;
        if (tag == null) {
            normalizedTag = "ReactUnknown";
        }
        Logger logger = (Logger) loggersMap.get(normalizedTag);
        if (logger != null) {
            return logger;
        }
        logger = Logger.getLogger(normalizedTag);
        loggersMap.put(normalizedTag, logger);
        return logger;
    }

    private static Level c(int levelIndex) {
        switch (levelIndex) {
            case 2:
                return Level.FINEST;
            case 3:
                return Level.FINE;
            case 4:
                return Level.INFO;
            case 5:
                return Level.WARNING;
            case 6:
            case 7:
                return Level.SEVERE;
            default:
                return Level.FINEST;
        }
    }
}
