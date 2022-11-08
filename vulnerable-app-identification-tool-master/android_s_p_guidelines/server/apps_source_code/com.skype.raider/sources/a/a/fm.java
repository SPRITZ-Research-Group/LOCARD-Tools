package a.a;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.annotation.VisibleForTesting;
import com.appboy.f.c;
import com.appboy.receivers.AppboyActionReceiver;

public class fm {
    private static final String a = c.a(fm.class);
    private final Context b;
    private final fo c;
    private final AlarmManager d;
    private final fl e;
    private final BroadcastReceiver f;
    private final PendingIntent g;
    private fw h;
    private long i;
    private boolean j;
    private volatile boolean k = false;

    public fm(Context context, final b bVar, fo foVar, AlarmManager alarmManager, fl flVar, String str) {
        this.b = context;
        this.c = foVar;
        this.d = alarmManager;
        this.e = flVar;
        this.h = fw.NO_SESSION;
        this.i = -1;
        this.g = PendingIntent.getBroadcast(this.b, str.hashCode(), new Intent("com.appboy.action.receiver.DATA_SYNC").setClass(context, AppboyActionReceiver.class), 134217728);
        this.f = new BroadcastReceiver(this) {
            final /* synthetic */ fm b;

            public final void onReceive(Context context, Intent intent) {
                try {
                    this.b.c.a(intent, (ConnectivityManager) context.getSystemService("connectivity"));
                    this.b.b();
                } catch (Throwable e) {
                    c.d(fm.a, "Failed to process connectivity event.", e);
                    fm.a(bVar, e);
                }
            }
        };
        c.b(a, "Registered broadcast filters");
    }

    public final synchronized void a(boolean z) {
        this.j = z;
        b();
        if (z) {
            a();
        } else {
            d();
        }
    }

    private synchronized boolean d() {
        boolean z = true;
        synchronized (this) {
            if (this.k) {
                c.b(a, "The data sync policy is already running. Ignoring request.");
                z = false;
            } else {
                c.b(a, "Data sync started");
                this.b.registerReceiver(this.f, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                a(3000);
                this.k = true;
            }
        }
        return z;
    }

    public final synchronized boolean a() {
        boolean z = false;
        synchronized (this) {
            if (this.k) {
                c.b(a, "Data sync stopped");
                e();
                this.b.unregisterReceiver(this.f);
                this.k = false;
                z = true;
            } else {
                c.b(a, "The data sync policy is not running. Ignoring request.");
            }
        }
        return z;
    }

    @VisibleForTesting
    protected final void b() {
        long j = this.i;
        if (this.h != fw.NO_SESSION && !this.j) {
            switch (this.c.a()) {
                case NONE:
                    this.i = -1;
                    break;
                case TWO_G:
                    this.i = this.e.a();
                    break;
                case FOUR_G:
                case WIFI:
                    this.i = this.e.c();
                    break;
                default:
                    this.i = this.e.b();
                    break;
            }
        }
        this.i = -1;
        if (j != this.i) {
            a(this.i);
            c.b(a, "Dispatch state has changed from " + j + " to " + this.i + ".");
        }
    }

    private void a(long j) {
        if (this.d == null) {
            c.b(a, "Alarm manager was null. Ignoring request to reset it.");
        } else if (this.i <= 0) {
            c.b(a, "Cancelling alarm because delay value was not positive.");
            e();
        } else {
            this.d.setInexactRepeating(1, co.c() + j, this.i, this.g);
        }
    }

    private void e() {
        if (this.g != null) {
            this.d.cancel(this.g);
        }
    }

    static /* synthetic */ void a(b bVar, Throwable th) {
        try {
            bVar.a(th, Throwable.class);
        } catch (Throwable e) {
            c.d(a, "Failed to log throwable.", e);
        }
    }
}
