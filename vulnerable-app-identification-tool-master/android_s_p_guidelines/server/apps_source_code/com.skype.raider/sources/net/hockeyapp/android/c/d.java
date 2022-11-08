package net.hockeyapp.android.c;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import net.hockeyapp.android.c.a.b;
import net.hockeyapp.android.c.a.c;
import net.hockeyapp.android.c.a.k;
import net.hockeyapp.android.c.a.l;
import net.hockeyapp.android.f.e;
import net.hockeyapp.android.g;

public final class d {
    private static boolean a = true;
    private static final AtomicInteger b = new AtomicInteger(0);
    private static final AtomicLong c = new AtomicLong(f());
    private static final Integer d = Integer.valueOf(20000);
    private static final Object e = new Object();
    private static volatile d f;
    private static WeakReference<Application> g;
    private static f h;
    private static a i;
    private static g j;
    private volatile boolean k;
    private a l;

    private class a implements ActivityLifecycleCallbacks {
        final /* synthetic */ d a;
        private final long b;
        private Timer c;
        private TimerTask d;

        private a(d dVar) {
            this.a = dVar;
            this.b = 2000;
        }

        /* synthetic */ a(d x0, byte b) {
            this(x0);
        }

        public final void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        public final void onActivityStarted(Activity activity) {
        }

        public final void onActivityResumed(Activity activity) {
            d.a(this.a);
            if (this.d != null) {
                this.d.cancel();
                this.d = null;
            }
            if (this.c != null) {
                this.c.cancel();
                this.c = null;
            }
        }

        public final void onActivityPaused(Activity activity) {
            d.c.set(d.f());
            this.c = new Timer();
            this.d = new TimerTask(this) {
                final /* synthetic */ a a;

                {
                    this.a = this$1;
                }

                public final void run() {
                    e.b("HA-MetricsManager");
                    d.i.a();
                }
            };
            this.c.schedule(this.d, 2000);
        }

        public final void onActivityStopped(Activity activity) {
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        public final void onActivityDestroyed(Activity activity) {
        }
    }

    private d(Context context, g telemetryContext) {
        j = telemetryContext;
        f sender = new f();
        h = sender;
        e persistence = new e(context, sender);
        h.a(persistence);
        i = new a(j, persistence);
        net.hockeyapp.android.f.a.a(new AsyncTask<Void, Object, Object>(persistence) {
            final /* synthetic */ e a;

            {
                this.a = this$0;
            }

            protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                if (this.a.a()) {
                    f c = this.a.c();
                    if (c != null) {
                        c.a();
                    }
                }
                return null;
            }
        });
    }

    @Deprecated
    private static boolean e() {
        if (f == null) {
            e.f("HA-MetricsManager");
            return false;
        } else if (!a || f.k) {
            return false;
        } else {
            return true;
        }
    }

    private static long f() {
        return new Date().getTime();
    }

    @SuppressLint({"StaticFieldLeak"})
    private void g() {
        final String sessionId = UUID.randomUUID().toString();
        try {
            net.hockeyapp.android.f.a.a(new AsyncTask<Void, Void, Void>(this) {
                final /* synthetic */ d b;

                protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                    d.j.a(sessionId);
                    d.a(k.START);
                    return null;
                }
            });
        } catch (RejectedExecutionException e) {
            e.f();
        }
    }

    public static void a(Application application, String appIdentifier) {
        if (f == null) {
            synchronized (e) {
                d dVar = f;
                if (dVar == null) {
                    net.hockeyapp.android.a.a(application.getApplicationContext());
                    dVar = new d(application.getApplicationContext(), new g(application.getApplicationContext(), appIdentifier));
                    g = new WeakReference(application);
                }
                dVar.k = false;
                f = dVar;
                if (!dVar.k) {
                    Boolean valueOf = Boolean.valueOf(false);
                    if (f == null || !a) {
                        e.d("HA-MetricsManager");
                    } else {
                        synchronized (e) {
                            f.k = valueOf.booleanValue();
                            if (!valueOf.booleanValue()) {
                                Application application2;
                                d dVar2 = f;
                                if (dVar2.l == null) {
                                    dVar2.l = new a(dVar2, (byte) 0);
                                }
                                if (g != null) {
                                    application2 = (Application) g.get();
                                } else {
                                    application2 = null;
                                }
                                if (application2 != null) {
                                    application2.registerActivityLifecycleCallbacks(dVar2.l);
                                }
                            }
                        }
                    }
                }
            }
            g.a(new net.hockeyapp.android.g.a() {
            });
        }
    }

    static /* synthetic */ void a(k x1) {
        net.hockeyapp.android.c.a.e lVar = new l();
        lVar.a(x1);
        b cVar = new c();
        cVar.a(lVar);
        cVar.a(lVar.b());
        cVar.b = lVar.a();
        i.a(cVar);
    }

    static /* synthetic */ void a(d x0) {
        if (b.getAndIncrement() != 0) {
            long f = f();
            long andSet = c.getAndSet(f());
            Object obj = f - andSet >= ((long) d.intValue()) ? 1 : null;
            new StringBuilder("Checking if we have to renew a session, time difference is: ").append(f - andSet);
            e.b("HA-MetricsManager");
            if (obj != null && e()) {
                e.b("HA-MetricsManager");
                x0.g();
            }
        } else if (e()) {
            e.b("HA-MetricsManager");
            x0.g();
        } else {
            e.b("HA-MetricsManager");
        }
    }
}
