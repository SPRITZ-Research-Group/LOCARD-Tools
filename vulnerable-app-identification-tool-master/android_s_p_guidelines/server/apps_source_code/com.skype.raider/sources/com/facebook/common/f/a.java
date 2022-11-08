package com.facebook.common.f;

import com.facebook.common.internal.b;
import com.facebook.common.internal.h;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.PropagatesNullable;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class a<T> implements Closeable, Cloneable {
    private static Class<a> a = a.class;
    private static final c<Closeable> d = new c<Closeable>() {
        public final /* bridge */ /* synthetic */ void a(Object obj) {
            try {
                b.a((Closeable) obj);
            } catch (IOException e) {
            }
        }
    };
    @GuardedBy("this")
    private boolean b = false;
    private final d<T> c;

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return b();
    }

    private a(d<T> sharedReference) {
        this.c = (d) h.a((Object) sharedReference);
        sharedReference.b();
    }

    private a(T t, c<T> resourceReleaser) {
        this.c = new d(t, resourceReleaser);
    }

    public static <T extends Closeable> a<T> a(@PropagatesNullable T t) {
        if (t == null) {
            return null;
        }
        return new a(t, d);
    }

    public static <T> a<T> a(@PropagatesNullable T t, c<T> resourceReleaser) {
        if (t == null) {
            return null;
        }
        return new a(t, resourceReleaser);
    }

    public final synchronized T a() {
        h.b(!this.b);
        return this.c.a();
    }

    public final synchronized a<T> b() {
        h.b(d());
        return new a(this.c);
    }

    @Nullable
    public final synchronized a<T> c() {
        a<T> b;
        if (d()) {
            b = b();
        } else {
            b = null;
        }
        return b;
    }

    public final synchronized boolean d() {
        return !this.b;
    }

    public final int e() {
        return d() ? System.identityHashCode(this.c.a()) : 0;
    }

    public final void close() {
        synchronized (this) {
            if (this.b) {
                return;
            }
            this.b = true;
            this.c.c();
        }
    }

    public static boolean a(@Nullable a<?> ref) {
        return ref != null && ref.d();
    }

    @Nullable
    public static <T> a<T> b(@Nullable a<T> ref) {
        return ref != null ? ref.c() : null;
    }

    public static <T> List<a<T>> a(@PropagatesNullable Collection<a<T>> refs) {
        if (refs == null) {
            return null;
        }
        List<a<T>> ret = new ArrayList(refs.size());
        for (a<T> ref : refs) {
            ret.add(b(ref));
        }
        return ret;
    }

    public static void c(@Nullable a<?> ref) {
        if (ref != null) {
            ref.close();
        }
    }

    public static void a(@Nullable Iterable<? extends a<?>> references) {
        if (references != null) {
            for (a c : references) {
                c(c);
            }
        }
    }

    protected final void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (this.b) {
                } else {
                    FLog.w(a, "Finalized without closing: %x %x (type = %s)", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(System.identityHashCode(this.c)), this.c.a().getClass().getName());
                    close();
                    super.finalize();
                }
            }
        } finally {
            super.finalize();
        }
    }
}
