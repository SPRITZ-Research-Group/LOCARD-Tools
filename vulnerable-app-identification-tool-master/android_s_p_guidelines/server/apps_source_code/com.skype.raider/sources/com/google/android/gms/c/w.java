package com.google.android.gms.c;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class w implements Executor {
    w() {
    }

    public final void execute(@NonNull Runnable runnable) {
        runnable.run();
    }
}
