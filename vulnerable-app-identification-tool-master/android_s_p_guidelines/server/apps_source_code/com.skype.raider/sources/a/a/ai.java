package a.a;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.VisibleForTesting;
import com.appboy.a;
import com.appboy.f.c;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ai {
    @VisibleForTesting
    static final long a = TimeUnit.SECONDS.toMillis(10);
    private static final String b = c.a(ai.class);
    private static final long c = TimeUnit.SECONDS.toMillis(10);
    private final Object d = new Object();
    private final ch e;
    private final b f;
    private final Context g;
    private final AlarmManager h;
    private final int i;
    private final String j;
    private final ck k;
    private volatile az l;
    private final Handler m;
    private final Runnable n;
    private final boolean o;

    public ai(final Context context, ch chVar, b bVar, AlarmManager alarmManager, ck ckVar, int i, boolean z) {
        this.e = chVar;
        this.f = bVar;
        this.g = context;
        this.h = alarmManager;
        this.i = i;
        this.k = ckVar;
        this.m = new Handler(Looper.getMainLooper());
        this.n = new Runnable(this) {
            final /* synthetic */ ai b;

            public final void run() {
                c.b(ai.b, "Requesting data flush on internal session close flush timer.");
                a.a(context).e();
            }
        };
        this.o = z;
        BroadcastReceiver anonymousClass2 = new BroadcastReceiver(this) {
            final /* synthetic */ ai a;

            {
                this.a = r1;
            }

            public final void onReceive(Context context, Intent intent) {
                synchronized (this.a.d) {
                    try {
                        this.a.h();
                    } catch (Exception e) {
                        try {
                            this.a.f.a(e, Throwable.class);
                        } catch (Throwable e2) {
                            c.d(ai.b, "Failed to log throwable.", e2);
                        }
                    }
                }
            }
        };
        this.j = context.getPackageName() + ".intent.APPBOY_SESSION_SHOULD_SEAL";
        context.registerReceiver(anonymousClass2, new IntentFilter(this.j));
    }

    public final az a() {
        az azVar;
        synchronized (this.d) {
            if (g()) {
                this.e.a(this.l);
            }
            i();
            Intent intent = new Intent(this.j);
            intent.putExtra("session_id", this.l.toString());
            this.h.cancel(PendingIntent.getBroadcast(this.g, 0, intent, ErrorDialogData.SUPPRESSED));
            this.f.a(l.a, l.class);
            azVar = this.l;
        }
        return azVar;
    }

    public final az b() {
        az azVar;
        synchronized (this.d) {
            g();
            this.l.a(Double.valueOf(co.b()));
            this.e.a(this.l);
            i();
            this.m.postDelayed(this.n, c);
            az azVar2 = this.l;
            int i = this.i;
            boolean z = this.o;
            long toMillis = TimeUnit.SECONDS.toMillis((long) i);
            if (z) {
                long toMillis2 = TimeUnit.SECONDS.toMillis((long) azVar2.b());
                toMillis = Math.max(a, (toMillis + toMillis2) - co.c());
            }
            c.b(b, "Creating a session seal alarm with a delay of " + toMillis + " ms");
            Intent intent = new Intent(this.j);
            intent.putExtra("session_id", this.l.toString());
            this.h.set(1, toMillis + co.c(), PendingIntent.getBroadcast(this.g, 0, intent, ErrorDialogData.SUPPRESSED));
            this.f.a(m.a, m.class);
            azVar = this.l;
        }
        return azVar;
    }

    public final bc c() {
        bc bcVar;
        synchronized (this.d) {
            h();
            if (this.l == null) {
                bcVar = null;
            } else {
                bcVar = this.l.a();
            }
        }
        return bcVar;
    }

    public final boolean d() {
        boolean z;
        synchronized (this.d) {
            z = this.l != null && this.l.d();
        }
        return z;
    }

    public final void e() {
        synchronized (this.d) {
            if (this.l != null) {
                this.l.e();
                this.e.a(this.l);
                this.f.a(new k(this.l), k.class);
            }
        }
    }

    private boolean g() {
        boolean z = true;
        synchronized (this.d) {
            h();
            if (this.l == null || this.l.d()) {
                az azVar = this.l;
                az azVar2 = new az(new bc(UUID.randomUUID()), co.b());
                this.k.a(true);
                this.f.a(j.a, j.class);
                c.d(b, "New session created with ID: " + azVar2.a());
                this.l = azVar2;
                if (azVar != null && azVar.d()) {
                    c.b(b, "Clearing completely dispatched sealed session " + azVar.a());
                    this.e.b(azVar);
                }
            } else if (this.l.c() != null) {
                this.l.a(null);
            } else {
                z = false;
            }
        }
        return z;
    }

    private void h() {
        Object obj = 1;
        synchronized (this.d) {
            if (this.l == null) {
                this.l = this.e.a();
                if (this.l != null) {
                    c.b(b, "Restored session from offline storage: " + this.l.a().toString());
                }
            }
            if (!(this.l == null || this.l.c() == null || this.l.d())) {
                az azVar = this.l;
                int i = this.i;
                boolean z = this.o;
                long c = co.c();
                long toMillis = TimeUnit.SECONDS.toMillis((long) i);
                long toMillis2 = TimeUnit.SECONDS.toMillis(azVar.c().longValue());
                long toMillis3 = TimeUnit.SECONDS.toMillis((long) azVar.b());
                if (z) {
                    if ((toMillis3 + toMillis) + a > c) {
                        obj = null;
                    }
                } else if (toMillis2 + toMillis > c) {
                    obj = null;
                }
                if (obj != null) {
                    c.d(b, "Session [" + this.l.a() + "] being sealed because its end time is over the grace period.");
                    e();
                    this.e.b(this.l);
                    this.l = null;
                }
            }
        }
    }

    @VisibleForTesting
    private void i() {
        this.m.removeCallbacks(this.n);
    }
}
