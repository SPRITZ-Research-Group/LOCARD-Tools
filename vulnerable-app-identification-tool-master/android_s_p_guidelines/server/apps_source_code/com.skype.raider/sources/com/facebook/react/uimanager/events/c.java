package com.facebook.react.uimanager.events;

import android.util.LongSparseArray;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ap;
import com.facebook.react.bridge.v;
import com.facebook.react.modules.core.e;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

public final class c implements v {
    private static final Comparator<b> a = new Comparator<b>() {
        public final /* synthetic */ int compare(Object obj, Object obj2) {
            b bVar = (b) obj;
            b bVar2 = (b) obj2;
            if (bVar == null && bVar2 == null) {
                return 0;
            }
            if (bVar == null) {
                return -1;
            }
            if (bVar2 != null) {
                long e = bVar.e() - bVar2.e();
                if (e == 0) {
                    return 0;
                }
                if (e < 0) {
                    return -1;
                }
            }
            return 1;
        }
    };
    private final Object b = new Object();
    private final Object c = new Object();
    private final ag d;
    private final LongSparseArray<Integer> e = new LongSparseArray();
    private final Map<String, Short> f = new HashMap();
    private final a g = new a();
    private final ArrayList<b> h = new ArrayList();
    private final ArrayList<d> i = new ArrayList();
    private final b j = new b();
    private final AtomicInteger k = new AtomicInteger();
    private b[] l = new b[16];
    private int m = 0;
    @Nullable
    private volatile RCTEventEmitter n;
    private short o = (short) 0;
    private volatile boolean p = false;

    private class a implements Runnable {
        final /* synthetic */ c a;

        private a(c cVar) {
            this.a = cVar;
        }

        /* synthetic */ a(c x0, byte b) {
            this(x0);
        }

        public final void run() {
            com.facebook.systrace.a.a("DispatchEventsRunnable");
            try {
                this.a.k.getAndIncrement();
                this.a.p = false;
                com.facebook.infer.annotation.a.a(this.a.n);
                synchronized (this.a.c) {
                    if (this.a.m > 1) {
                        Arrays.sort(this.a.l, 0, this.a.m, c.a);
                    }
                    for (int eventIdx = 0; eventIdx < this.a.m; eventIdx++) {
                        b event = this.a.l[eventIdx];
                        if (event != null) {
                            event.a();
                            event.a(this.a.n);
                            event.h();
                        }
                    }
                    c.l(this.a);
                    this.a.e.clear();
                }
            } finally {
                com.facebook.systrace.a.a();
            }
        }
    }

    private class b extends com.facebook.react.modules.core.a.a {
        final /* synthetic */ c a;
        private volatile boolean b;
        private boolean c;

        private b(c cVar) {
            this.a = cVar;
            this.b = false;
            this.c = false;
        }

        /* synthetic */ b(c x0, byte b) {
            this(x0);
        }

        public final void b(long frameTimeNanos) {
            ap.b();
            if (this.c) {
                this.b = false;
            } else {
                f();
            }
            com.facebook.systrace.a.a("ScheduleDispatchFrameCallback");
            try {
                c.b(this.a);
                if (this.a.m > 0 && !this.a.p) {
                    this.a.p = true;
                    this.a.k.get();
                    this.a.d.d(this.a.g);
                }
                com.facebook.systrace.a.a();
            } catch (Throwable th) {
                com.facebook.systrace.a.a();
            }
        }

        public final void c() {
            this.c = true;
        }

        public final void d() {
            if (!this.b) {
                this.b = true;
                f();
            }
        }

        private void f() {
            e.b().a(com.facebook.react.modules.core.e.a.TIMERS_EVENTS, this.a.j);
        }

        public final void e() {
            if (!this.b) {
                if (this.a.d.f()) {
                    d();
                } else {
                    this.a.d.a(new Runnable(this) {
                        final /* synthetic */ b a;

                        {
                            this.a = this$1;
                        }

                        public final void run() {
                            this.a.d();
                        }
                    });
                }
            }
        }
    }

    static /* synthetic */ void l(c x0) {
        Arrays.fill(x0.l, 0, x0.m, null);
        x0.m = 0;
    }

    public c(ag reactContext) {
        this.d = reactContext;
        this.d.a((v) this);
    }

    public final void a(b event) {
        com.facebook.infer.annotation.a.a(event.g(), "Dispatched event hasn't been initialized");
        Iterator it = this.i.iterator();
        while (it.hasNext()) {
            ((d) it.next()).a(event);
        }
        synchronized (this.b) {
            this.h.add(event);
            event.a();
        }
        if (this.n != null) {
            this.j.e();
        }
    }

    public final void a(d listener) {
        this.i.add(listener);
    }

    public final void onHostResume() {
        if (this.n == null) {
            this.n = (RCTEventEmitter) this.d.a(RCTEventEmitter.class);
        }
        this.j.e();
    }

    public final void onHostPause() {
        c();
    }

    public final void onHostDestroy() {
        c();
    }

    public final void a() {
        ap.a(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.c();
            }
        });
    }

    private void c() {
        ap.b();
        this.j.c();
    }

    private void b(b event) {
        if (this.m == this.l.length) {
            this.l = (b[]) Arrays.copyOf(this.l, this.l.length * 2);
        }
        b[] bVarArr = this.l;
        int i = this.m;
        this.m = i + 1;
        bVarArr[i] = event;
    }

    static /* synthetic */ void b(c x0) {
        synchronized (x0.b) {
            synchronized (x0.c) {
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 < x0.h.size()) {
                        b bVar = (b) x0.h.get(i2);
                        if (bVar.b()) {
                            int shortValue;
                            b bVar2;
                            int d = bVar.d();
                            String a = bVar.a();
                            short f = bVar.f();
                            Short sh = (Short) x0.f.get(a);
                            if (sh != null) {
                                shortValue = sh.shortValue();
                            } else {
                                shortValue = x0.o;
                                x0.o = (short) (shortValue + 1);
                                x0.f.put(a, Short.valueOf(shortValue));
                            }
                            long j = ((((long) f) & 65535) << 48) | (((long) d) | ((((long) shortValue) & 65535) << 32));
                            Integer num = (Integer) x0.e.get(j);
                            if (num == null) {
                                x0.e.put(j, Integer.valueOf(x0.m));
                                bVar2 = bVar;
                                bVar = null;
                            } else {
                                b bVar3 = x0.l[num.intValue()];
                                b a2 = bVar.a(bVar3);
                                if (a2 != bVar3) {
                                    x0.e.put(j, Integer.valueOf(x0.m));
                                    x0.l[num.intValue()] = null;
                                    bVar = bVar3;
                                    bVar2 = a2;
                                } else {
                                    bVar2 = null;
                                }
                            }
                            if (bVar2 != null) {
                                x0.b(bVar2);
                            }
                            if (bVar != null) {
                                bVar.h();
                            }
                        } else {
                            x0.b(bVar);
                        }
                        i = i2 + 1;
                    }
                }
            }
            x0.h.clear();
        }
    }
}
