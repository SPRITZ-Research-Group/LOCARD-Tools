package com.facebook.imagepipeline.d;

import android.graphics.Bitmap;
import android.os.SystemClock;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.i;
import com.facebook.common.internal.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class h<K, V> implements com.facebook.common.e.c, p<K, V> {
    @VisibleForTesting
    static final long a = TimeUnit.MINUTES.toMillis(5);
    @GuardedBy("this")
    @VisibleForTesting
    final g<K, b<K, V>> b;
    @GuardedBy("this")
    @VisibleForTesting
    final g<K, b<K, V>> c;
    @GuardedBy("this")
    @VisibleForTesting
    final Map<Bitmap, Object> d = new WeakHashMap();
    @GuardedBy("this")
    protected q e;
    private final v<V> f;
    private final a g;
    private final j<q> h;
    @GuardedBy("this")
    private long i;

    public interface c<K> {
        void a(K k, boolean z);
    }

    public interface a {
        double a(com.facebook.common.e.b bVar);
    }

    @VisibleForTesting
    static class b<K, V> {
        public final K a;
        public final com.facebook.common.f.a<V> b;
        public int c = 0;
        public boolean d = false;
        @Nullable
        public final c<K> e;

        private b(K key, com.facebook.common.f.a<V> valueRef, @Nullable c<K> observer) {
            this.a = com.facebook.common.internal.h.a((Object) key);
            this.b = (com.facebook.common.f.a) com.facebook.common.internal.h.a(com.facebook.common.f.a.b(valueRef));
            this.e = observer;
        }

        @VisibleForTesting
        static <K, V> b<K, V> a(K key, com.facebook.common.f.a<V> valueRef, @Nullable c<K> observer) {
            return new b(key, valueRef, observer);
        }
    }

    public h(v<V> valueDescriptor, a cacheTrimStrategy, j<q> memoryCacheParamsSupplier) {
        this.f = valueDescriptor;
        this.b = new g(a((v) valueDescriptor));
        this.c = new g(a((v) valueDescriptor));
        this.g = cacheTrimStrategy;
        this.h = memoryCacheParamsSupplier;
        this.e = (q) this.h.a();
        this.i = SystemClock.uptimeMillis();
    }

    private v<b<K, V>> a(final v<V> evictableValueDescriptor) {
        return new v<b<K, V>>(this) {
            final /* synthetic */ h b;

            public final /* bridge */ /* synthetic */ int a(Object obj) {
                return evictableValueDescriptor.a(((b) obj).b.a());
            }
        };
    }

    public final com.facebook.common.f.a<V> a(K key, com.facebook.common.f.a<V> valueRef) {
        return a(key, valueRef, null);
    }

    public final com.facebook.common.f.a<V> a(K key, com.facebook.common.f.a<V> valueRef, c<K> observer) {
        b oldExclusive;
        com.facebook.common.internal.h.a((Object) key);
        com.facebook.common.internal.h.a((Object) valueRef);
        a();
        com.facebook.common.f.a<V> oldRefToClose = null;
        com.facebook.common.f.a<V> clientRef = null;
        synchronized (this) {
            oldExclusive = (b) this.b.c(key);
            b oldEntry = (b) this.c.c(key);
            if (oldEntry != null) {
                d(oldEntry);
                oldRefToClose = g(oldEntry);
            }
            if (d(valueRef.a())) {
                b newEntry = b.a(key, valueRef, observer);
                this.c.a(key, newEntry);
                clientRef = a(newEntry);
            }
        }
        com.facebook.common.f.a.c(oldRefToClose);
        c(oldExclusive);
        b();
        return clientRef;
    }

    private synchronized boolean d(V value) {
        boolean z;
        int newValueSize = this.f.a(value);
        z = newValueSize <= this.e.e && c() <= this.e.b - 1 && d() <= this.e.a - newValueSize;
        return z;
    }

    @Nullable
    public final com.facebook.common.f.a<V> a(K key) {
        b oldExclusive;
        com.facebook.common.internal.h.a((Object) key);
        com.facebook.common.f.a<V> clientRef = null;
        synchronized (this) {
            oldExclusive = (b) this.b.c(key);
            b entry = (b) this.c.b((Object) key);
            if (entry != null) {
                clientRef = a(entry);
            }
        }
        c(oldExclusive);
        a();
        b();
        return clientRef;
    }

    private synchronized com.facebook.common.f.a<V> a(final b<K, V> entry) {
        e(entry);
        return com.facebook.common.f.a.a(entry.b.a(), new com.facebook.common.f.c<V>(this) {
            final /* synthetic */ h b;

            public final void a(V v) {
                h.a(this.b, entry);
            }
        });
    }

    private synchronized boolean b(b<K, V> entry) {
        boolean z;
        if (entry.d || entry.c != 0) {
            z = false;
        } else {
            this.b.a(entry.a, entry);
            z = true;
        }
        return z;
    }

    @Nullable
    public final com.facebook.common.f.a<V> b(K key) {
        b oldExclusive;
        com.facebook.common.internal.h.a((Object) key);
        com.facebook.common.f.a<V> clientRef = null;
        boolean removed = false;
        synchronized (this) {
            oldExclusive = (b) this.b.c(key);
            if (oldExclusive != null) {
                Object entry = (b) this.c.c(key);
                com.facebook.common.internal.h.a(entry);
                com.facebook.common.internal.h.b(entry.c == 0);
                clientRef = entry.b;
                removed = true;
            }
        }
        if (removed) {
            c(oldExclusive);
        }
        return clientRef;
    }

    public final int a(i<K> predicate) {
        ArrayList oldExclusives;
        ArrayList oldEntries;
        synchronized (this) {
            oldExclusives = this.b.b((i) predicate);
            oldEntries = this.c.b((i) predicate);
            c(oldEntries);
        }
        a(oldEntries);
        b(oldExclusives);
        a();
        b();
        return oldEntries.size();
    }

    public final synchronized boolean b(i<K> predicate) {
        return !this.c.a((i) predicate).isEmpty();
    }

    public final synchronized boolean c(K key) {
        return this.c.a((Object) key);
    }

    public final void a(com.facebook.common.e.b trimType) {
        ArrayList oldEntries;
        double trimRatio = this.g.a(trimType);
        synchronized (this) {
            oldEntries = a(Integer.MAX_VALUE, Math.max(0, ((int) (((double) this.c.b()) * (1.0d - trimRatio))) - d()));
            c(oldEntries);
        }
        a(oldEntries);
        b(oldEntries);
        a();
        b();
    }

    private synchronized void a() {
        if (this.i + a <= SystemClock.uptimeMillis()) {
            this.i = SystemClock.uptimeMillis();
            this.e = (q) this.h.a();
        }
    }

    private void b() {
        ArrayList oldEntries;
        synchronized (this) {
            oldEntries = a(Math.min(this.e.d, this.e.b - c()), Math.min(this.e.c, this.e.a - d()));
            c(oldEntries);
        }
        a(oldEntries);
        b(oldEntries);
    }

    @Nullable
    private synchronized ArrayList<b<K, V>> a(int count, int size) {
        ArrayList<b<K, V>> oldEntries;
        count = Math.max(count, 0);
        size = Math.max(size, 0);
        if (this.b.a() > count || this.b.b() > size) {
            oldEntries = new ArrayList();
            while (true) {
                if (this.b.a() <= count && this.b.b() <= size) {
                    break;
                }
                K key = this.b.c();
                this.b.c(key);
                oldEntries.add(this.c.c(key));
            }
        } else {
            oldEntries = null;
        }
        return oldEntries;
    }

    private void a(@Nullable ArrayList<b<K, V>> oldEntries) {
        if (oldEntries != null) {
            Iterator it = oldEntries.iterator();
            while (it.hasNext()) {
                com.facebook.common.f.a.c(g((b) it.next()));
            }
        }
    }

    private static void b(@Nullable ArrayList<b<K, V>> entries) {
        if (entries != null) {
            Iterator it = entries.iterator();
            while (it.hasNext()) {
                c((b) it.next());
            }
        }
    }

    private static <K, V> void c(@Nullable b<K, V> entry) {
        if (entry != null && entry.e != null) {
            entry.e.a(entry.a, false);
        }
    }

    private synchronized void c(@Nullable ArrayList<b<K, V>> oldEntries) {
        if (oldEntries != null) {
            Iterator it = oldEntries.iterator();
            while (it.hasNext()) {
                d((b) it.next());
            }
        }
    }

    private synchronized void d(b<K, V> entry) {
        boolean z = true;
        synchronized (this) {
            com.facebook.common.internal.h.a((Object) entry);
            if (entry.d) {
                z = false;
            }
            com.facebook.common.internal.h.b(z);
            entry.d = true;
        }
    }

    private synchronized void e(b<K, V> entry) {
        com.facebook.common.internal.h.a((Object) entry);
        com.facebook.common.internal.h.b(!entry.d);
        entry.c++;
    }

    private synchronized void f(b<K, V> entry) {
        com.facebook.common.internal.h.a((Object) entry);
        com.facebook.common.internal.h.b(entry.c > 0);
        entry.c--;
    }

    @Nullable
    private synchronized com.facebook.common.f.a<V> g(b<K, V> entry) {
        com.facebook.common.f.a<V> aVar;
        com.facebook.common.internal.h.a((Object) entry);
        aVar = (entry.d && entry.c == 0) ? entry.b : null;
        return aVar;
    }

    private synchronized int c() {
        return this.c.a() - this.b.a();
    }

    private synchronized int d() {
        return this.c.b() - this.b.b();
    }

    static /* synthetic */ void a(h x0, b x1) {
        boolean b;
        com.facebook.common.f.a g;
        com.facebook.common.internal.h.a((Object) x1);
        synchronized (x0) {
            x0.f(x1);
            b = x0.b(x1);
            g = x0.g(x1);
        }
        com.facebook.common.f.a.c(g);
        if (!b) {
            x1 = null;
        }
        if (!(x1 == null || x1.e == null)) {
            x1.e.a(x1.a, true);
        }
        x0.a();
        x0.b();
    }
}
