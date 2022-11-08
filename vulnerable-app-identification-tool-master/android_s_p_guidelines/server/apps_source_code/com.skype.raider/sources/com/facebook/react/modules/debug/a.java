package com.facebook.react.modules.debug;

import com.facebook.react.bridge.ac;
import com.facebook.react.common.d;

public final class a implements ac, com.facebook.react.uimanager.debug.a {
    private final d a = d.a();
    private final d b = d.a();
    private final d c = d.a();
    private final d d = d.a();
    private volatile boolean e = true;

    public final synchronized void a() {
        this.a.a(System.nanoTime());
    }

    public final synchronized void b() {
        this.b.a(System.nanoTime());
    }

    public final synchronized void c() {
        this.c.a(System.nanoTime());
    }

    public final synchronized void d() {
        this.d.a(System.nanoTime());
    }

    public final synchronized boolean a(long frameStartTimeNanos, long frameEndTimeNanos) {
        boolean hitFrame;
        boolean finishedUiUpdate = a(this.d, frameStartTimeNanos, frameEndTimeNanos);
        long b = b(this.a, frameStartTimeNanos, frameEndTimeNanos);
        long b2 = b(this.b, frameStartTimeNanos, frameEndTimeNanos);
        boolean didEndFrameIdle = (b == -1 && b2 == -1) ? this.e : b > b2;
        if (finishedUiUpdate) {
            hitFrame = true;
        } else {
            if (didEndFrameIdle) {
                if (!a(this.c, frameStartTimeNanos, frameEndTimeNanos)) {
                    hitFrame = true;
                }
            }
            hitFrame = false;
        }
        a(this.a, frameEndTimeNanos);
        a(this.b, frameEndTimeNanos);
        a(this.c, frameEndTimeNanos);
        a(this.d, frameEndTimeNanos);
        this.e = didEndFrameIdle;
        return hitFrame;
    }

    private static boolean a(d eventArray, long startTime, long endTime) {
        for (int i = 0; i < eventArray.b(); i++) {
            long time = eventArray.a(i);
            if (time >= startTime && time < endTime) {
                return true;
            }
        }
        return false;
    }

    private static long b(d eventArray, long startTime, long endTime) {
        long lastEvent = -1;
        for (int i = 0; i < eventArray.b(); i++) {
            long time = eventArray.a(i);
            if (time >= startTime && time < endTime) {
                lastEvent = time;
            } else if (time >= endTime) {
                break;
            }
        }
        return lastEvent;
    }

    private static void a(d eventArray, long endTime) {
        int i;
        int size = eventArray.b();
        int indicesToRemove = 0;
        for (i = 0; i < size; i++) {
            if (eventArray.a(i) < endTime) {
                indicesToRemove++;
            }
        }
        if (indicesToRemove > 0) {
            for (i = 0; i < size - indicesToRemove; i++) {
                eventArray.a(i, eventArray.a(i + indicesToRemove));
            }
            eventArray.b(indicesToRemove);
        }
    }
}
