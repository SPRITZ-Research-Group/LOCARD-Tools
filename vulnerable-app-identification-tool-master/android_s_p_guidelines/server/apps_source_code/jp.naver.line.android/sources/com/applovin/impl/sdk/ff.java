package com.applovin.impl.sdk;

import java.util.concurrent.ThreadFactory;

class ff implements ThreadFactory {
    final /* synthetic */ fd a;
    private final String b;

    public ff(fd fdVar, String str) {
        this.a = fdVar;
        this.b = str;
    }

    public Thread newThread(Runnable runnable) {
        StringBuilder stringBuilder = new StringBuilder("AppLovinSdk:");
        stringBuilder.append(this.b);
        stringBuilder.append(":");
        stringBuilder.append(gd.a(this.a.b.getSdkKey()));
        Thread thread = new Thread(runnable, stringBuilder.toString());
        thread.setDaemon(true);
        thread.setPriority(10);
        thread.setUncaughtExceptionHandler(new fg(this));
        return thread;
    }
}
