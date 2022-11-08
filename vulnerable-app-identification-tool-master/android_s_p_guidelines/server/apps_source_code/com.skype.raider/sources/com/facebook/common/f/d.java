package com.facebook.common.f;

import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import com.facebook.common.logging.FLog;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

@VisibleForTesting
public final class d<T> {
    @GuardedBy("itself")
    private static final Map<Object, Integer> a = new IdentityHashMap();
    @GuardedBy("this")
    private T b;
    @GuardedBy("this")
    private int c = 1;
    private final c<T> d;

    public static class a extends RuntimeException {
        public a() {
            super("Null shared reference");
        }
    }

    public d(T value, c<T> resourceReleaser) {
        this.b = h.a((Object) value);
        this.d = (c) h.a((Object) resourceReleaser);
        synchronized (a) {
            Integer num = (Integer) a.get(value);
            if (num == null) {
                a.put(value, Integer.valueOf(1));
            } else {
                a.put(value, Integer.valueOf(num.intValue() + 1));
            }
        }
    }

    public final synchronized T a() {
        return this.b;
    }

    private synchronized boolean d() {
        return this.c > 0;
    }

    public final synchronized void b() {
        f();
        this.c++;
    }

    public final void c() {
        if (e() == 0) {
            T deleted;
            synchronized (this) {
                deleted = this.b;
                this.b = null;
            }
            this.d.a(deleted);
            synchronized (a) {
                Integer num = (Integer) a.get(deleted);
                if (num == null) {
                    FLog.wtf("SharedReference", "No entry in sLiveObjects for value of type %s", deleted.getClass());
                } else if (num.intValue() == 1) {
                    a.remove(deleted);
                } else {
                    a.put(deleted, Integer.valueOf(num.intValue() - 1));
                }
            }
        }
    }

    private synchronized int e() {
        f();
        h.a(this.c > 0);
        this.c--;
        return this.c;
    }

    private void f() {
        Object obj;
        if (this == null || !d()) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj == null) {
            throw new a();
        }
    }
}
