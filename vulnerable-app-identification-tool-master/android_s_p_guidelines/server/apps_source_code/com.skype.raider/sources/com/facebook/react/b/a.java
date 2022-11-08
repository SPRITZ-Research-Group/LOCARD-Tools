package com.facebook.react.b;

import android.os.Handler;
import android.util.SparseArray;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.ap;
import java.lang.ref.WeakReference;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

public final class a {
    private static final WeakHashMap<ai, a> a = new WeakHashMap();
    private final WeakReference<ai> b;
    private final Set<b> c = new CopyOnWriteArraySet();
    private final AtomicInteger d = new AtomicInteger(0);
    private final Handler e = new Handler();
    private final Set<Integer> f = new CopyOnWriteArraySet();
    private final SparseArray<Runnable> g = new SparseArray();

    public static a a(ai context) {
        a helper = (a) a.get(context);
        if (helper != null) {
            return helper;
        }
        helper = new a(context);
        a.put(context, helper);
        return helper;
    }

    private a(ai reactContext) {
        this.b = new WeakReference(reactContext);
    }

    public final void a(b listener) {
        this.c.add(listener);
    }

    public final void b(b listener) {
        this.c.remove(listener);
    }

    public final boolean a() {
        return this.f.size() > 0;
    }

    public final synchronized void a(final int taskId) {
        com.facebook.infer.annotation.a.a(this.f.remove(Integer.valueOf(taskId)), "Tried to finish non-existent task with id " + taskId + ".");
        Runnable timeout = (Runnable) this.g.get(taskId);
        if (timeout != null) {
            this.e.removeCallbacks(timeout);
            this.g.remove(taskId);
        }
        ap.a(new Runnable(this) {
            final /* synthetic */ a b;

            public final void run() {
                for (b onHeadlessJsTaskFinish : this.b.c) {
                    onHeadlessJsTaskFinish.onHeadlessJsTaskFinish(taskId);
                }
            }
        });
    }

    public final synchronized boolean b(int taskId) {
        return this.f.contains(Integer.valueOf(taskId));
    }
}
