package a.a;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.appboy.a.a;
import com.appboy.e.b.b;
import com.appboy.f.c;
import com.appboy.f.i;
import com.appboy.f.j;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

public class ag implements am {
    private static final String a = c.a(ag.class);
    private AtomicInteger b = new AtomicInteger(0);
    private AtomicInteger c = new AtomicInteger(0);
    private volatile String d = "";
    private final Object e = new Object();
    private final Object f = new Object();
    private final ai g;
    private final ah h;
    private final fp i;
    private final b j;
    private final an k;
    private final a l;
    private final ck m;
    private final ac n;
    private final String o;
    private final cj p;
    private boolean q = false;
    private Class<? extends Activity> r = null;

    public ag(ai aiVar, fp fpVar, b bVar, an anVar, a aVar, ck ckVar, ac acVar, String str, boolean z, ah ahVar, cj cjVar) {
        this.g = aiVar;
        this.i = fpVar;
        this.j = bVar;
        this.k = anVar;
        this.l = aVar;
        this.q = z;
        this.o = str;
        this.m = ckVar;
        this.n = acVar;
        this.h = ahVar;
        this.p = cjVar;
    }

    public final az a() {
        if (this.p.a()) {
            c.f(a, "SDK is disabled. Returning null session.");
            return null;
        }
        az a = this.g.a();
        c.d(a, "Completed the openSession call. Starting or continuing session " + a.a());
        return a;
    }

    public final az a(@NonNull Activity activity) {
        if (this.p.a()) {
            c.f(a, "SDK is disabled. Returning null session.");
            return null;
        }
        az a = a();
        this.r = activity.getClass();
        this.h.a();
        c.a(a, "Opened session with activity: " + activity.getLocalClassName());
        return a;
    }

    public final az b(@NonNull Activity activity) {
        if (this.p.a()) {
            c.f(a, "SDK is disabled. Returning null session.");
            return null;
        } else if (this.r != null && !activity.getClass().equals(this.r)) {
            return null;
        } else {
            this.h.b();
            c.a(a, "Closed session with activity: " + activity.getLocalClassName());
            return this.g.b();
        }
    }

    public final void b() {
        if (this.p.a()) {
            c.f(a, "SDK is disabled. Not force closing session.");
            return;
        }
        this.r = null;
        this.g.e();
    }

    public final boolean a(av avVar) {
        Object obj = null;
        if (this.p.a()) {
            c.f(a, "SDK is disabled. Not logging event: " + avVar);
            return false;
        }
        synchronized (this.e) {
            if (avVar == null) {
                c.g(a, "Appboy manager received null event.");
                throw new NullPointerException();
            }
            if (this.g.d() || this.g.c() == null) {
                c.b(a, "Not adding session id to event: " + cv.a((JSONObject) avVar.h()));
            } else {
                avVar.a(this.g.c());
                obj = 1;
            }
            if (i.b(this.o)) {
                c.b(a, "Not adding user id to event: " + cv.a((JSONObject) avVar.h()));
            } else {
                avVar.a(this.o);
            }
            if (fs.b(avVar.b())) {
                c.b(a, "Publishing an internal push body clicked event for any awaiting triggers.");
                JSONObject c = avVar.c();
                if (c != null) {
                    String optString = c.optString("cid", null);
                    if (avVar.b().equals(fs.PUSH_NOTIFICATION_TRACKING)) {
                        this.j.a(new n(optString, avVar), n.class);
                    }
                } else {
                    c.f(a, "Event json was null. Not publishing push clicked trigger event.");
                }
            }
            this.n.a(avVar);
            if (fs.a(avVar.b()) && obj == null) {
                c.b(a, "Adding push click to dispatcher pending list");
                this.i.b(avVar);
            } else {
                this.i.a(avVar);
            }
            if (avVar.b().equals(fs.SESSION_START)) {
                this.i.a(avVar.f());
            }
            if (obj == null) {
                c();
            }
        }
        return true;
    }

    public final void a(Throwable th) {
        try {
            if (b(th)) {
                c.f(a, "Not logging duplicate error.");
            } else {
                a(be.a(th, this.g.c()));
            }
        } catch (Throwable e) {
            c.d(a, "Failed to create error event from " + th + ".", e);
        } catch (Throwable e2) {
            c.d(a, "Failed to log error.", e2);
        }
    }

    public final void a(q qVar) {
        try {
            if (b((Throwable) qVar)) {
                c.f(a, "Not logging duplicate database exception.");
            } else {
                a(be.a(qVar, this.g.c()));
            }
        } catch (Throwable e) {
            c.d(a, "Failed to create database exception event from " + qVar + ".", e);
        } catch (Throwable e2) {
            c.d(a, "Failed to log error.", e2);
        }
    }

    public final void a(String str, String str2, boolean z) {
        if (str == null || !j.a(str)) {
            throw new IllegalArgumentException("Reply to email address is invalid");
        } else if (i.c(str2)) {
            throw new IllegalArgumentException("Feedback message cannot be null or blank");
        } else {
            a(new bn(this.l.a(), new b(str2, str, z, this.k.a(), this.o)));
        }
    }

    public final void c() {
        a(new bi.a());
    }

    public final void a(bi.a aVar) {
        if (aVar == null) {
            c.b(a, "Cannot request data sync with null respond with object");
            return;
        }
        if (this.m != null && this.m.p()) {
            aVar.a(new bh(this.m.k()));
        }
        aVar.a(this.o);
        bi c = aVar.c();
        if (c.c() && (c.d() || c.e())) {
            this.m.a(false);
        }
        a(new bm(this.l.a(), c));
    }

    public final void a(aw awVar) {
        c.b(a, "Posting geofence request for location.");
        a(new bo(this.l.a(), awVar));
    }

    public final void b(av avVar) {
        c.b(a, "Posting geofence report for geofence event.");
        a(new bp(this.l.a(), avVar));
    }

    public final void a(de deVar, ed edVar) {
        a(new bw(this.l.a(), deVar, edVar, this, this.o));
    }

    public final void a(ed edVar) {
        this.j.a(new o(edVar), o.class);
    }

    public final void a(List<String> list, long j) {
        a(new bx(this.l.a(), list, j, this.o));
    }

    public final void a(boolean z) {
        this.q = z;
    }

    public final String d() {
        return this.o;
    }

    private boolean b(Throwable th) {
        boolean z = false;
        synchronized (this.f) {
            this.b.getAndIncrement();
            if (!this.d.equals(th.getMessage()) || this.c.get() <= 3 || this.b.get() >= 100) {
                if (this.d.equals(th.getMessage())) {
                    this.c.getAndIncrement();
                } else {
                    this.c.set(0);
                }
                if (this.b.get() >= 100) {
                    this.b.set(0);
                }
                this.d = th.getMessage();
            } else {
                z = true;
            }
        }
        return z;
    }

    @VisibleForTesting
    private void a(bq bqVar) {
        if (this.p.a()) {
            c.f(a, "SDK is disabled. Not adding request to dispatch.");
        } else {
            this.i.a(bqVar);
        }
    }
}
