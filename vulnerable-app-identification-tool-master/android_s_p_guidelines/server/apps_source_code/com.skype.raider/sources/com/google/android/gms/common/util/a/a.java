package com.google.android.gms.common.util.a;

import com.google.android.gms.common.internal.ab;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public final class a implements ThreadFactory {
    private final String a;
    private final int b;
    private final ThreadFactory c;

    public a(String str) {
        this(str, (byte) 0);
    }

    private a(String str, byte b) {
        this.c = Executors.defaultThreadFactory();
        this.a = (String) ab.a((Object) str, (Object) "Name must not be null");
        this.b = 0;
    }

    public final Thread newThread(Runnable runnable) {
        Thread newThread = this.c.newThread(new c(runnable, this.b));
        newThread.setName(this.a);
        return newThread;
    }
}
