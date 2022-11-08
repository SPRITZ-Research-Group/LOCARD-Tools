package com.facebook.ads.internal.d;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class b {
    private static final String a = b.class.getSimpleName();
    private static final ExecutorService b = Executors.newSingleThreadExecutor();
    private static final ExecutorService c = Executors.newFixedThreadPool(5);
    private final Handler d = new Handler();
    private final c e;
    private final d f;
    private final List<Callable<Boolean>> g;

    private class a implements Callable<Boolean> {
        final /* synthetic */ b a;
        private final String b;
        private final int c;
        private final int d;

        public a(b bVar, String str, int i, int i2) {
            this.a = bVar;
            this.b = str;
            this.c = i;
            this.d = i2;
        }

        public final /* synthetic */ Object call() {
            return Boolean.valueOf(this.a.e.a(this.b, this.c, this.d) != null);
        }
    }

    private class b implements Callable<Boolean> {
        final /* synthetic */ b a;
        private final String b;

        public b(b bVar, String str) {
            this.a = bVar;
            this.b = str;
        }

        public final /* synthetic */ Object call() {
            return Boolean.valueOf(this.a.f.a(this.b));
        }
    }

    public b(Context context) {
        this.e = c.a(context);
        this.f = d.a(context);
        this.g = new ArrayList();
    }

    public final void a(@Nullable final a aVar) {
        final ArrayList arrayList = new ArrayList(this.g);
        b.execute(new Runnable(this) {
            final /* synthetic */ b c;

            public final void run() {
                List<Future> arrayList = new ArrayList(arrayList.size());
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList.add(b.c.submit((Callable) it.next()));
                }
                final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
                try {
                    for (Future future : arrayList) {
                        atomicBoolean.set(((Boolean) future.get()).booleanValue() & atomicBoolean.get());
                    }
                } catch (InterruptedException e) {
                    b.a;
                    atomicBoolean.set(false);
                    this.c.d.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public final void run() {
                            if (aVar == null) {
                                return;
                            }
                            if (atomicBoolean.get()) {
                                aVar.a();
                            } else {
                                aVar.b();
                            }
                        }
                    });
                } catch (ExecutionException e2) {
                    b.a;
                    atomicBoolean.set(false);
                    this.c.d.post(/* anonymous class already generated */);
                }
                this.c.d.post(/* anonymous class already generated */);
            }
        });
        this.g.clear();
    }

    public final void a(String str) {
        this.g.add(new b(this, str));
    }

    public final void a(String str, int i, int i2) {
        this.g.add(new a(this, str, i, i2));
    }

    public final String b(String str) {
        return this.f.b(str);
    }
}
