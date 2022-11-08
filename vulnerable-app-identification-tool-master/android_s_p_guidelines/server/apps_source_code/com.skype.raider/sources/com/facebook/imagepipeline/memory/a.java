package com.facebook.imagepipeline.memory;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.facebook.common.e.d;
import com.facebook.common.e.f;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import com.facebook.common.internal.l;
import com.facebook.common.logging.FLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

public abstract class a<V> implements f<V> {
    final d a;
    final ad b;
    @VisibleForTesting
    final SparseArray<f<V>> c;
    @VisibleForTesting
    final Set<V> d;
    @GuardedBy("this")
    @VisibleForTesting
    final a e;
    @GuardedBy("this")
    @VisibleForTesting
    final a f;
    private final Class<?> g = getClass();
    private boolean h;
    private final ae i;

    @VisibleForTesting
    @NotThreadSafe
    static class a {
        int a;
        int b;

        a() {
        }

        public final void a(int numBytes) {
            this.a++;
            this.b += numBytes;
        }

        public final void b(int numBytes) {
            if (this.b < numBytes || this.a <= 0) {
                FLog.wtf("com.facebook.imagepipeline.memory.BasePool.Counter", "Unexpected decrement of %d. Current numBytes = %d, count = %d", Integer.valueOf(numBytes), Integer.valueOf(this.b), Integer.valueOf(this.a));
                return;
            }
            this.a--;
            this.b -= numBytes;
        }
    }

    public static class b extends RuntimeException {
        public b(Object size) {
            super("Invalid size: " + size.toString());
        }
    }

    public static class c extends RuntimeException {
        public c(int hardCap, int usedBytes, int freeBytes, int allocSize) {
            super("Pool hard cap violation? Hard cap = " + hardCap + " Used size = " + usedBytes + " Free size = " + freeBytes + " Request size = " + allocSize);
        }
    }

    protected abstract V b(int i);

    @VisibleForTesting
    protected abstract void b(V v);

    protected abstract int c(int i);

    protected abstract int c(V v);

    protected abstract int d(int i);

    public a(d memoryTrimmableRegistry, ad poolParams, ae poolStatsTracker) {
        this.a = (d) h.a((Object) memoryTrimmableRegistry);
        this.b = (ad) h.a((Object) poolParams);
        this.i = (ae) h.a((Object) poolStatsTracker);
        this.c = new SparseArray();
        if (this.b.f) {
            c();
        } else {
            a(new SparseIntArray(0));
        }
        this.d = Collections.newSetFromMap(new IdentityHashMap());
        this.f = new a();
        this.e = new a();
    }

    protected final void a() {
        this.a.a(this);
    }

    @Nullable
    protected synchronized V a(f<V> bucket) {
        return bucket.c();
    }

    public final V a(int size) {
        V value;
        b();
        int bucketedSize = c(size);
        synchronized (this) {
            int sizeInBytes;
            f bucket = h(bucketedSize);
            if (bucket != null) {
                value = a(bucket);
                if (value != null) {
                    h.b(this.d.add(value));
                    bucketedSize = c((Object) value);
                    sizeInBytes = d(bucketedSize);
                    this.e.a(sizeInBytes);
                    this.f.b(sizeInBytes);
                    f();
                    if (FLog.isLoggable(2)) {
                        FLog.v(this.g, "get (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSize));
                    }
                }
            }
            sizeInBytes = d(bucketedSize);
            if (i(sizeInBytes)) {
                this.e.a(sizeInBytes);
                if (bucket != null) {
                    bucket.e();
                }
                value = null;
                try {
                    value = b(bucketedSize);
                } catch (Throwable e) {
                    synchronized (this) {
                        this.e.b(sizeInBytes);
                        f<V> bucket2 = h(bucketedSize);
                        if (bucket2 != null) {
                            bucket2.f();
                        }
                        l.a(e);
                    }
                    return value;
                }
                synchronized (this) {
                    h.b(this.d.add(value));
                    d();
                    f();
                    if (FLog.isLoggable(2)) {
                        FLog.v(this.g, "get (alloc) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSize));
                    }
                }
            } else {
                throw new c(this.b.a, this.e.b, this.f.b, sizeInBytes);
            }
        }
        return value;
    }

    public final void a(V value) {
        h.a((Object) value);
        int bucketedSize = c((Object) value);
        int sizeInBytes = d(bucketedSize);
        synchronized (this) {
            f<V> bucket = g(bucketedSize);
            if (!this.d.remove(value)) {
                FLog.e(this.g, "release (free, value unrecognized) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSize));
                b((Object) value);
            } else if (bucket == null || bucket.a() || e() || !d((Object) value)) {
                if (bucket != null) {
                    bucket.f();
                }
                if (FLog.isLoggable(2)) {
                    FLog.v(this.g, "release (free) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSize));
                }
                b((Object) value);
                this.e.b(sizeInBytes);
            } else {
                bucket.a(value);
                this.f.a(sizeInBytes);
                this.e.b(sizeInBytes);
                if (FLog.isLoggable(2)) {
                    FLog.v(this.g, "release (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSize));
                }
            }
            f();
        }
    }

    public final void a(com.facebook.common.e.b memoryTrimType) {
        List arrayList;
        f fVar;
        int i = 0;
        synchronized (this) {
            int i2;
            if (this.b.f) {
                arrayList = new ArrayList(this.c.size());
                int size = this.c.size();
                for (i2 = 0; i2 < size; i2++) {
                    fVar = (f) this.c.valueAt(i2);
                    int i3 = fVar.a;
                    int i4 = fVar.b;
                    int g = fVar.g();
                    if (fVar.b() > 0) {
                        arrayList.add(fVar);
                    }
                    this.c.setValueAt(i2, new f(d(i3), i4, g, this.b.f));
                }
            } else {
                arrayList = new ArrayList(this.c.size());
                SparseIntArray sparseIntArray = new SparseIntArray();
                for (i2 = 0; i2 < this.c.size(); i2++) {
                    fVar = (f) this.c.valueAt(i2);
                    if (fVar.b() > 0) {
                        arrayList.add(fVar);
                    }
                    sparseIntArray.put(this.c.keyAt(i2), fVar.g());
                }
                a(sparseIntArray);
            }
            a aVar = this.f;
            aVar.a = 0;
            aVar.b = 0;
            f();
        }
        while (i < arrayList.size()) {
            fVar = (f) arrayList.get(i);
            while (true) {
                Object d = fVar.d();
                if (d == null) {
                    break;
                }
                b(d);
            }
            i++;
        }
    }

    protected boolean d(V value) {
        h.a((Object) value);
        return true;
    }

    private synchronized void b() {
        boolean z = !e() || this.f.b == 0;
        h.b(z);
    }

    private synchronized void a(SparseIntArray inUseCounts) {
        h.a((Object) inUseCounts);
        this.c.clear();
        SparseIntArray bucketSizes = this.b.c;
        if (bucketSizes != null) {
            for (int i = 0; i < bucketSizes.size(); i++) {
                int bucketSize = bucketSizes.keyAt(i);
                this.c.put(bucketSize, new f(d(bucketSize), bucketSizes.valueAt(i), inUseCounts.get(bucketSize, 0), this.b.f));
            }
            this.h = false;
        } else {
            this.h = true;
        }
    }

    private synchronized void c() {
        synchronized (this) {
            SparseIntArray bucketSizes = this.b.c;
            if (bucketSizes != null) {
                this.c.clear();
                for (int i = 0; i < bucketSizes.size(); i++) {
                    int keyAt = bucketSizes.keyAt(i);
                    this.c.put(keyAt, new f(d(keyAt), bucketSizes.valueAt(i), 0, this.b.f));
                }
                this.h = false;
            } else {
                this.h = true;
            }
        }
    }

    @VisibleForTesting
    private synchronized void d() {
        if (e()) {
            f(this.b.b);
        }
    }

    @VisibleForTesting
    private synchronized void f(int targetSize) {
        int bytesToFree = Math.min((this.e.b + this.f.b) - targetSize, this.f.b);
        if (bytesToFree > 0) {
            if (FLog.isLoggable(2)) {
                FLog.v(this.g, "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d", Integer.valueOf(targetSize), Integer.valueOf(this.e.b + this.f.b), Integer.valueOf(bytesToFree));
            }
            f();
            for (int i = 0; i < this.c.size() && bytesToFree > 0; i++) {
                f<V> bucket = (f) this.c.valueAt(i);
                while (bytesToFree > 0) {
                    Object value = bucket.d();
                    if (value == null) {
                        break;
                    }
                    b(value);
                    bytesToFree -= bucket.a;
                    this.f.b(bucket.a);
                }
            }
            f();
            if (FLog.isLoggable(2)) {
                FLog.v(this.g, "trimToSize: TargetSize = %d; Final Size = %d", Integer.valueOf(targetSize), Integer.valueOf(this.e.b + this.f.b));
            }
        }
    }

    private synchronized f<V> g(int bucketedSize) {
        return (f) this.c.get(bucketedSize);
    }

    @VisibleForTesting
    private synchronized f<V> h(int bucketedSize) {
        f<V> newBucket;
        f<V> bucket = (f) this.c.get(bucketedSize);
        if (bucket == null && this.h) {
            if (FLog.isLoggable(2)) {
                FLog.v(this.g, "creating new bucket %s", Integer.valueOf(bucketedSize));
            }
            newBucket = e(bucketedSize);
            this.c.put(bucketedSize, newBucket);
        } else {
            newBucket = bucket;
        }
        return newBucket;
    }

    f<V> e(int bucketedSize) {
        return new f(d(bucketedSize), Integer.MAX_VALUE, 0, this.b.f);
    }

    @VisibleForTesting
    private synchronized boolean e() {
        return this.e.b + this.f.b > this.b.b;
    }

    @VisibleForTesting
    private synchronized boolean i(int sizeInBytes) {
        boolean z = false;
        synchronized (this) {
            int hardCap = this.b.a;
            if (sizeInBytes <= hardCap - this.e.b) {
                int softCap = this.b.b;
                if (sizeInBytes > softCap - (this.e.b + this.f.b)) {
                    f(softCap - sizeInBytes);
                }
                if (sizeInBytes <= hardCap - (this.e.b + this.f.b)) {
                    z = true;
                }
            }
        }
        return z;
    }

    @SuppressLint({"InvalidAccessToGuardedField"})
    private void f() {
        if (FLog.isLoggable(2)) {
            FLog.v(this.g, "Used = (%d, %d); Free = (%d, %d)", Integer.valueOf(this.e.a), Integer.valueOf(this.e.b), Integer.valueOf(this.f.a), Integer.valueOf(this.f.b));
        }
    }
}
