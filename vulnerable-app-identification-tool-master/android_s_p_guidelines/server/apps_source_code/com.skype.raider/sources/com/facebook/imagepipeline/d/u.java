package com.facebook.imagepipeline.d;

import com.facebook.cache.a.c;
import com.facebook.common.f.a;
import com.facebook.common.internal.h;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.image.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

public class u {
    private static final Class<?> a = u.class;
    @GuardedBy("this")
    private Map<c, e> b = new HashMap();

    private u() {
    }

    public static u a() {
        return new u();
    }

    public final synchronized void a(c key, e encodedImage) {
        h.a((Object) key);
        h.a(e.e(encodedImage));
        e.d((e) this.b.put(key, e.a(encodedImage)));
        c();
    }

    public final void b() {
        List<e> old;
        synchronized (this) {
            old = new ArrayList(this.b.values());
            this.b.clear();
        }
        for (int i = 0; i < old.size(); i++) {
            e encodedImage = (e) old.get(i);
            if (encodedImage != null) {
                encodedImage.close();
            }
        }
    }

    public final boolean a(c key) {
        e encodedImage;
        h.a((Object) key);
        synchronized (this) {
            encodedImage = (e) this.b.remove(key);
        }
        if (encodedImage == null) {
            return false;
        }
        try {
            boolean a = encodedImage.a();
            return a;
        } finally {
            encodedImage.close();
        }
    }

    public final synchronized boolean b(c key, e encodedImage) {
        boolean z = false;
        synchronized (this) {
            h.a((Object) key);
            h.a((Object) encodedImage);
            h.a(e.e(encodedImage));
            e oldValue = (e) this.b.get(key);
            if (oldValue != null) {
                a<com.facebook.common.e.h> oldRef = oldValue.b();
                a<com.facebook.common.e.h> ref = encodedImage.b();
                if (!(oldRef == null || ref == null)) {
                    try {
                        if (oldRef.a() == ref.a()) {
                            this.b.remove(key);
                            c();
                            z = true;
                        }
                    } finally {
                        a.c(ref);
                        a.c(oldRef);
                        e.d(oldValue);
                    }
                }
                a.c(ref);
                a.c(oldRef);
                e.d(oldValue);
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized e b(c key) {
        e eVar;
        Throwable th;
        h.a((Object) key);
        e storedEncodedImage = (e) this.b.get(key);
        if (storedEncodedImage != null) {
            synchronized (storedEncodedImage) {
                e storedEncodedImage2;
                try {
                    if (e.e(storedEncodedImage)) {
                        storedEncodedImage2 = e.a(storedEncodedImage);
                    } else {
                        this.b.remove(key);
                        FLog.w(a, "Found closed reference %d for key %s (%d)", Integer.valueOf(System.identityHashCode(storedEncodedImage)), key.a(), Integer.valueOf(System.identityHashCode(key)));
                        eVar = null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
        eVar = storedEncodedImage;
        return eVar;
        throw th;
    }

    public final synchronized boolean c(c key) {
        boolean z = false;
        synchronized (this) {
            h.a((Object) key);
            if (this.b.containsKey(key)) {
                e storedEncodedImage = (e) this.b.get(key);
                synchronized (storedEncodedImage) {
                    if (e.e(storedEncodedImage)) {
                        z = true;
                    } else {
                        this.b.remove(key);
                        FLog.w(a, "Found closed reference %d for key %s (%d)", Integer.valueOf(System.identityHashCode(storedEncodedImage)), key.a(), Integer.valueOf(System.identityHashCode(key)));
                    }
                }
            }
        }
        return z;
    }

    private synchronized void c() {
        FLog.v(a, "Count = %d", Integer.valueOf(this.b.size()));
    }
}
