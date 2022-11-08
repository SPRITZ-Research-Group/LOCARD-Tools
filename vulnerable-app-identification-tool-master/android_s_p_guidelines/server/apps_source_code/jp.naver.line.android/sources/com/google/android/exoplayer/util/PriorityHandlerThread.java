package com.google.android.exoplayer.util;

import android.os.HandlerThread;
import android.os.Process;

public final class PriorityHandlerThread extends HandlerThread {
    private final int priority;

    public PriorityHandlerThread(String str, int i) {
        super(str);
        this.priority = i;
    }

    public final void run() {
        Process.setThreadPriority(this.priority);
        super.run();
    }
}
