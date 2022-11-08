package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.util.a.b;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class ab {
    private static final ExecutorService a = new ThreadPoolExecutor(0, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new b("GAC_Transform"));

    public static ExecutorService a() {
        return a;
    }
}
