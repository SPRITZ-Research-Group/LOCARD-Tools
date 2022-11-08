package com.applovin.impl.sdk;

import java.lang.Thread.UncaughtExceptionHandler;

class fg implements UncaughtExceptionHandler {
    final /* synthetic */ ff a;

    fg(ff ffVar) {
        this.a = ffVar;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        this.a.a.c.e("TaskManager", "Caught unhandled exception", th);
    }
}
