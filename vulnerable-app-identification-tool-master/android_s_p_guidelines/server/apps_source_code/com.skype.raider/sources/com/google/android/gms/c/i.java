package com.google.android.gms.c;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

public final class i {
    public static final Executor a = new a();
    static final Executor b = new w();

    private static final class a implements Executor {
        private final Handler a = new Handler(Looper.getMainLooper());

        public final void execute(@NonNull Runnable runnable) {
            this.a.post(runnable);
        }
    }
}
