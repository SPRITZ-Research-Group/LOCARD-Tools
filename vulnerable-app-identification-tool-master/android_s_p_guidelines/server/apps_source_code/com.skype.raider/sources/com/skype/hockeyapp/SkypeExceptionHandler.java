package com.skype.hockeyapp;

import com.facebook.common.logging.FLog;
import java.lang.Thread.UncaughtExceptionHandler;

public class SkypeExceptionHandler implements UncaughtExceptionHandler {
    private final UncaughtExceptionHandler a;
    private final CrashFormatter b;

    public SkypeExceptionHandler(CrashFormatter crashFormatter, UncaughtExceptionHandler defaultExceptionHandler) {
        this.b = crashFormatter;
        this.a = defaultExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable ex) {
        FLog.e("AndroidRuntime", "unhandled crash: ", ex);
        this.b.a(ex);
        this.a.uncaughtException(thread, ex);
    }
}
