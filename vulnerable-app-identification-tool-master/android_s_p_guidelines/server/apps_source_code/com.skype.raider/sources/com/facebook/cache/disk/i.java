package com.facebook.cache.disk;

import com.facebook.cache.a.b.a;
import com.facebook.cache.a.c;
import com.facebook.infer.annotation.ReturnsOwnership;
import java.io.IOException;

public final class i {
    private static final Object a = new Object();
    private static i b;
    private static int c;
    private c d;
    private String e;
    private long f;
    private long g;
    private long h;
    private IOException i;
    private a j;
    private i k;

    @ReturnsOwnership
    public static i a() {
        synchronized (a) {
            if (b != null) {
                i eventToReuse = b;
                b = eventToReuse.k;
                eventToReuse.k = null;
                c--;
                return eventToReuse;
            }
            return new i();
        }
    }

    private i() {
    }

    public final i a(c cacheKey) {
        this.d = cacheKey;
        return this;
    }

    public final i a(String resourceId) {
        this.e = resourceId;
        return this;
    }

    public final i a(long itemSize) {
        this.f = itemSize;
        return this;
    }

    public final i b(long cacheSize) {
        this.h = cacheSize;
        return this;
    }

    public final i c(long cacheLimit) {
        this.g = cacheLimit;
        return this;
    }

    public final i a(IOException exception) {
        this.i = exception;
        return this;
    }

    public final i a(a evictionReason) {
        this.j = evictionReason;
        return this;
    }

    public final void b() {
        synchronized (a) {
            if (c < 5) {
                this.d = null;
                this.e = null;
                this.f = 0;
                this.g = 0;
                this.h = 0;
                this.i = null;
                this.j = null;
                c++;
                if (b != null) {
                    this.k = b;
                }
                b = this;
            }
        }
    }
}
