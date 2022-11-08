package com.skype.smsmanager.nativesms;

import com.skype.smsmanager.nativesms.models.LoggerStrategy;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import junit.framework.Assert;

public class SmsMmsLogger {
    private static CopyOnWriteArrayList<LoggerStrategy> a = new CopyOnWriteArrayList();

    private SmsMmsLogger() {
    }

    public static void a(LoggerStrategy logger) {
        Assert.assertNotNull("Logger cannot be null", logger);
        a.add(logger);
    }

    public static void a(String tag, String msg) {
        Iterator it = a.iterator();
        while (it.hasNext()) {
            ((LoggerStrategy) it.next()).a("[SmsRelayNative] " + tag, msg);
        }
    }

    public static void b(String tag, String msg) {
        Iterator it = a.iterator();
        while (it.hasNext()) {
            ((LoggerStrategy) it.next()).b("[SmsRelayNative] " + tag, msg);
        }
    }

    public static void c(String tag, String msg) {
        Iterator it = a.iterator();
        while (it.hasNext()) {
            ((LoggerStrategy) it.next()).c("[SmsRelayNative] " + tag, msg);
        }
    }

    public static void a(String tag, String msg, Throwable tr) {
        Iterator it = a.iterator();
        while (it.hasNext()) {
            ((LoggerStrategy) it.next()).a("[SmsRelayNative] " + tag, msg, tr);
        }
    }
}
