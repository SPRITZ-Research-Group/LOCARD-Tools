package com.facebook.imagepipeline.a.c;

import android.net.Uri;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.g;
import com.facebook.imagepipeline.d.h;
import java.util.Iterator;
import java.util.LinkedHashSet;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class c {
    private final com.facebook.cache.a.c a;
    private final h<com.facebook.cache.a.c, com.facebook.imagepipeline.image.c> b;
    private final com.facebook.imagepipeline.d.h.c<com.facebook.cache.a.c> c = new com.facebook.imagepipeline.d.h.c<com.facebook.cache.a.c>(this) {
        final /* synthetic */ c a;

        {
            this.a = this$0;
        }

        public final /* bridge */ /* synthetic */ void a(Object obj, boolean z) {
            this.a.a((com.facebook.cache.a.c) obj, z);
        }
    };
    @GuardedBy("this")
    private final LinkedHashSet<com.facebook.cache.a.c> d = new LinkedHashSet();

    @VisibleForTesting
    static class a implements com.facebook.cache.a.c {
        private final com.facebook.cache.a.c a;
        private final int b;

        public a(com.facebook.cache.a.c imageCacheKey, int frameIndex) {
            this.a = imageCacheKey;
            this.b = frameIndex;
        }

        public final String toString() {
            return g.a(this).a("imageCacheKey", this.a).a("frameIndex", this.b).toString();
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof a)) {
                return false;
            }
            a that = (a) o;
            if (this.b == that.b && this.a.equals(that.a)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return (this.a.hashCode() * 1013) + this.b;
        }

        public final boolean a(Uri uri) {
            return this.a.a(uri);
        }

        public final String a() {
            return null;
        }
    }

    public c(com.facebook.cache.a.c imageCacheKey, h<com.facebook.cache.a.c, com.facebook.imagepipeline.image.c> backingCache) {
        this.a = imageCacheKey;
        this.b = backingCache;
    }

    public final synchronized void a(com.facebook.cache.a.c key, boolean isReusable) {
        if (isReusable) {
            this.d.add(key);
        } else {
            this.d.remove(key);
        }
    }

    @Nullable
    public final com.facebook.common.f.a<com.facebook.imagepipeline.image.c> a(int frameIndex, com.facebook.common.f.a<com.facebook.imagepipeline.image.c> imageRef) {
        return this.b.a(c(frameIndex), imageRef, this.c);
    }

    @Nullable
    public final com.facebook.common.f.a<com.facebook.imagepipeline.image.c> a(int frameIndex) {
        return this.b.a(c(frameIndex));
    }

    public final boolean b(int frameIndex) {
        return this.b.c(c(frameIndex));
    }

    @Nullable
    public final com.facebook.common.f.a<com.facebook.imagepipeline.image.c> a() {
        com.facebook.common.f.a<com.facebook.imagepipeline.image.c> imageRef;
        do {
            Object key = b();
            if (key == null) {
                return null;
            }
            imageRef = this.b.b(key);
        } while (imageRef == null);
        return imageRef;
    }

    @Nullable
    private synchronized com.facebook.cache.a.c b() {
        com.facebook.cache.a.c cacheKey;
        cacheKey = null;
        Iterator<com.facebook.cache.a.c> iterator = this.d.iterator();
        if (iterator.hasNext()) {
            cacheKey = (com.facebook.cache.a.c) iterator.next();
            iterator.remove();
        }
        return cacheKey;
    }

    private a c(int frameIndex) {
        return new a(this.a, frameIndex);
    }
}
