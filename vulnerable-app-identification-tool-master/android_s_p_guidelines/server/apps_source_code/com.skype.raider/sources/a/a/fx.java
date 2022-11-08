package a.a;

import a.a.bi.a;
import android.content.Context;
import android.support.annotation.VisibleForTesting;
import com.appboy.c.b;
import com.appboy.f.c;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;

public class fx {
    private static final String c = c.a(fx.class);
    @VisibleForTesting
    AtomicBoolean a = new AtomicBoolean(false);
    @VisibleForTesting
    long b = 0;
    private final ap d;
    private final fp e;
    private final am f;
    private final Context g;
    private final cm h;
    private final cb i;
    private final ck j;
    private final en k;
    private final ac l;
    private final ad m;
    private final as n;
    private AtomicBoolean o = new AtomicBoolean(false);
    private n p;

    public fx(Context context, ap apVar, fp fpVar, ag agVar, cm cmVar, cb cbVar, ck ckVar, en enVar, ac acVar, ad adVar, as asVar) {
        this.d = apVar;
        this.e = fpVar;
        this.f = agVar;
        this.g = context;
        this.h = cmVar;
        this.i = cbVar;
        this.j = ckVar;
        this.k = enVar;
        this.l = acVar;
        this.m = adVar;
        this.n = asVar;
    }

    @VisibleForTesting
    protected final void a() {
        if (this.a.compareAndSet(true, false)) {
            this.k.a(new eg());
        }
    }

    @VisibleForTesting
    protected final void b() {
        if (this.o.compareAndSet(true, false) && this.p.a() != null) {
            this.k.a(new ei(this.p.a(), this.p.b()));
            this.p = null;
        }
    }

    @VisibleForTesting
    final void c() {
        if (this.b + 5 < co.a()) {
            this.a.set(true);
            c.b(c, "Requesting trigger refresh.");
            this.f.a(new a().b());
            this.b = co.a();
        }
    }

    public final void a(fy fyVar) {
        fyVar.a(new b<c>(this) {
            final /* synthetic */ fx a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void trigger(Object obj) {
                bq a = ((c) obj).a();
                bi e = a.e();
                if (e != null) {
                    if (e.d()) {
                        this.a.a();
                        this.a.b();
                    }
                    if (e.c()) {
                        this.a.j.a(true);
                    }
                }
                bg c = a.c();
                if (c != null) {
                    this.a.i.b(c, false);
                }
                bj d = a.d();
                if (d != null) {
                    this.a.h.b(d, false);
                }
                au f = a.f();
                if (f != null) {
                    for (av a2 : f.a()) {
                        this.a.e.a(a2);
                    }
                }
            }
        }, c.class);
        fyVar.a(new b<j>(this) {
            final /* synthetic */ fx a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void trigger(Object obj) {
                c.b(fx.c, "Session start event for new session received.");
                this.a.f.a(be.j());
                this.a.d.b();
                this.a.d.c();
                this.a.c();
                com.appboy.b.a(this.a.g, false);
                this.a.h.d();
            }
        }, j.class);
        fyVar.a(new b<k>(this) {
            final /* synthetic */ fx a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void trigger(Object obj) {
                fx.a(this.a, (k) obj);
                com.appboy.a.a(this.a.g).e();
            }
        }, k.class);
        fyVar.a(new b<n>(this) {
            final /* synthetic */ fx a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void trigger(Object obj) {
                n nVar = (n) obj;
                this.a.o.set(true);
                this.a.p = nVar;
                c.d(fx.c, "Requesting trigger update due to trigger-eligible push click event");
                this.a.f.a(new a().b());
            }
        }, n.class);
        fyVar.a(new b<i>(this) {
            final /* synthetic */ fx a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void trigger(Object obj) {
                i iVar = (i) obj;
                this.a.d.a(iVar.a());
                this.a.m.a(iVar.a());
                this.a.n.a(iVar.a());
            }
        }, i.class);
        fyVar.a(new b<Throwable>(this) {
            final /* synthetic */ Semaphore a = null;
            final /* synthetic */ fx b;

            {
                this.b = r2;
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final /* synthetic */ void trigger(Object obj) {
                try {
                    this.b.f.a((Throwable) obj);
                    if (this.a != null) {
                        this.a.release();
                    }
                } catch (Throwable e) {
                    c.d(fx.c, "Failed to log error.", e);
                    if (this.a != null) {
                        this.a.release();
                    }
                } catch (Throwable th) {
                    if (this.a != null) {
                        this.a.release();
                    }
                }
            }
        }, Throwable.class);
        fyVar.a(new b<q>(this) {
            final /* synthetic */ fx a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void trigger(Object obj) {
                try {
                    this.a.f.a((q) obj);
                } catch (Throwable e) {
                    c.d(fx.c, "Failed to log the database exception.", e);
                }
            }
        }, q.class);
        fyVar.a(new b<p>(this) {
            final /* synthetic */ fx a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void trigger(Object obj) {
                this.a.k.a(((p) obj).a());
                this.a.a();
                this.a.b();
            }
        }, p.class);
        fyVar.a(new b<g>(this) {
            final /* synthetic */ fx a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void trigger(Object obj) {
                this.a.c();
            }
        }, g.class);
        fyVar.a(new b<d>(this) {
            final /* synthetic */ fx a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void trigger(Object obj) {
                bq a = ((d) obj).a();
                bi e = a.e();
                if (e != null && e.c()) {
                    this.a.j.a(false);
                }
                bg c = a.c();
                if (c != null) {
                    this.a.i.b(c, true);
                }
                bj d = a.d();
                if (d != null) {
                    this.a.h.b(d, true);
                }
                au f = a.f();
                if (f != null) {
                    for (av b : f.a()) {
                        this.a.l.b(b);
                    }
                }
            }
        }, d.class);
        fyVar.a(new b<f>(this) {
            final /* synthetic */ fx a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void trigger(Object obj) {
                this.a.m.a(((f) obj).a());
            }
        }, f.class);
        fyVar.a(new b<o>(this) {
            final /* synthetic */ fx a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void trigger(Object obj) {
                this.a.k.a(((o) obj).a());
            }
        }, o.class);
    }

    static /* synthetic */ void a(fx fxVar, k kVar) {
        try {
            az a = kVar.a();
            av a2 = be.a(a.f());
            a2.a(a.a());
            fxVar.f.a(a2);
        } catch (JSONException e) {
            c.f(c, "Could not create session end event.");
        }
    }
}
