package com.facebook.drawee.a;

import android.os.Handler;
import android.os.Looper;
import com.facebook.common.internal.h;
import java.util.HashSet;
import java.util.Set;

public final class a {
    private static a a = null;
    private final Set<a> b = new HashSet();
    private final Handler c = new Handler(Looper.getMainLooper());
    private final Runnable d = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = this$0;
        }

        public final void run() {
            a.c();
            for (a d : this.a.b) {
                d.d();
            }
            this.a.b.clear();
        }
    };

    public interface a {
        void d();
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    public final void a(a releasable) {
        c();
        if (this.b.add(releasable) && this.b.size() == 1) {
            this.c.post(this.d);
        }
    }

    public final void b(a releasable) {
        c();
        this.b.remove(releasable);
    }

    private static void c() {
        h.b(Looper.getMainLooper().getThread() == Thread.currentThread());
    }
}
