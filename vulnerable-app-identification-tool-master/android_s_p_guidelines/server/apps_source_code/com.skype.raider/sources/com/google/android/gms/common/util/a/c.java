package com.google.android.gms.common.util.a;

import android.os.Process;

final class c implements Runnable {
    private final Runnable a;
    private final int b;

    public c(Runnable runnable, int i) {
        this.a = runnable;
        this.b = i;
    }

    public final void run() {
        Process.setThreadPriority(this.b);
        this.a.run();
    }
}
