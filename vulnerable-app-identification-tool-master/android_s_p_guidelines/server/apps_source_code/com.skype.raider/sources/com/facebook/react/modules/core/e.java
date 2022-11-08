package com.facebook.react.modules.core;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ap;
import java.util.ArrayDeque;

public final class e {
    private static e a;
    private final a b = a.a();
    private final b c = new b();
    private final ArrayDeque<com.facebook.react.modules.core.a.a>[] d = new ArrayDeque[a.values().length];
    private int e = 0;
    private boolean f = false;

    public enum a {
        PERF_MARKERS(0),
        DISPATCH_UI(1),
        NATIVE_ANIMATED_MODULE(2),
        TIMERS_EVENTS(3),
        IDLE_EVENT(4);
        
        private final int f;

        private a(int order) {
            this.f = order;
        }

        final int a() {
            return this.f;
        }
    }

    private class b extends com.facebook.react.modules.core.a.a {
        final /* synthetic */ e a;

        private b(e eVar) {
            this.a = eVar;
        }

        /* synthetic */ b(e x0, byte b) {
            this(x0);
        }

        public final void b(long frameTimeNanos) {
            synchronized (this.a) {
                this.a.f = false;
                for (int i = 0; i < this.a.d.length; i++) {
                    int initialLength = this.a.d[i].size();
                    for (int callback = 0; callback < initialLength; callback++) {
                        ((com.facebook.react.modules.core.a.a) this.a.d[i].removeFirst()).b(frameTimeNanos);
                        this.a.e = this.a.e - 1;
                    }
                }
                this.a.c();
            }
        }
    }

    public static void a() {
        if (a == null) {
            ap.b();
            a = new e();
        }
    }

    public static e b() {
        com.facebook.infer.annotation.a.a(a, "ReactChoreographer needs to be initialized.");
        return a;
    }

    private e() {
        for (int i = 0; i < this.d.length; i++) {
            this.d[i] = new ArrayDeque();
        }
    }

    public final synchronized void a(a type, com.facebook.react.modules.core.a.a frameCallback) {
        boolean z = true;
        synchronized (this) {
            this.d[type.a()].addLast(frameCallback);
            this.e++;
            if (this.e <= 0) {
                z = false;
            }
            com.facebook.infer.annotation.a.a(z);
            if (!this.f) {
                this.b.a(this.c);
                this.f = true;
            }
        }
    }

    public final synchronized void b(a type, com.facebook.react.modules.core.a.a frameCallback) {
        if (this.d[type.a()].removeFirstOccurrence(frameCallback)) {
            this.e--;
            c();
        } else {
            FLog.e("React", "Tried to remove non-existent frame callback");
        }
    }

    private void c() {
        com.facebook.infer.annotation.a.a(this.e >= 0);
        if (this.e == 0 && this.f) {
            this.b.b(this.c);
            this.f = false;
        }
    }
}
