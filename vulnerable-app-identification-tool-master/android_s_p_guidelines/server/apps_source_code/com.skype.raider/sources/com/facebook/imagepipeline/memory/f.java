package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import com.facebook.common.logging.FLog;
import java.util.LinkedList;
import java.util.Queue;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@VisibleForTesting
@NotThreadSafe
class f<V> {
    public final int a;
    public final int b;
    final Queue c;
    private final boolean d;
    private int e;

    public f(int itemSize, int maxLength, int inUseLength, boolean fixBucketsReinitialization) {
        boolean z;
        boolean z2 = true;
        h.b(itemSize > 0);
        if (maxLength >= 0) {
            z = true;
        } else {
            z = false;
        }
        h.b(z);
        if (inUseLength < 0) {
            z2 = false;
        }
        h.b(z2);
        this.a = itemSize;
        this.b = maxLength;
        this.c = new LinkedList();
        this.e = inUseLength;
        this.d = fixBucketsReinitialization;
    }

    public final boolean a() {
        return this.e + b() > this.b;
    }

    final int b() {
        return this.c.size();
    }

    @Deprecated
    @Nullable
    public final V c() {
        V value = d();
        if (value != null) {
            this.e++;
        }
        return value;
    }

    @Nullable
    public V d() {
        return this.c.poll();
    }

    public final void e() {
        this.e++;
    }

    public final void a(V value) {
        boolean z = true;
        h.a((Object) value);
        if (this.d) {
            if (this.e <= 0) {
                z = false;
            }
            h.b(z);
            this.e--;
            b(value);
        } else if (this.e > 0) {
            this.e--;
            b(value);
        } else {
            FLog.e("BUCKET", "Tried to release value %s from an empty bucket!", value);
        }
    }

    void b(V value) {
        this.c.add(value);
    }

    public final void f() {
        h.b(this.e > 0);
        this.e--;
    }

    public final int g() {
        return this.e;
    }
}
