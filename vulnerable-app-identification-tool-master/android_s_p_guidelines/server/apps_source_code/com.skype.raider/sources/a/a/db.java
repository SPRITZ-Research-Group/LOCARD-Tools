package a.a;

import android.app.AlarmManager;
import android.content.Context;
import com.appboy.a.a;
import com.appboy.c.b;
import com.appboy.f.c;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class db {
    private static final String a = c.a(db.class);
    private final cm b;
    private final cb c;
    private final fy d;
    private final fj e;
    private final ag f;
    private final cf g;
    private final fx h;
    private final ThreadPoolExecutor i;
    private final a j;
    private final fm k;
    private final ai l;
    private final ap m;
    private final er n;
    private final ck o;
    private final ad p;
    private final ac q;

    public db(Context context, fh fhVar, a aVar, b bVar, ab abVar, aq aqVar, boolean z, boolean z2, as asVar) {
        bz a;
        String a2 = fhVar.a();
        String bdVar = aVar.b().toString();
        cj cjVar = new cj(context);
        ThreadFactory sVar = new s();
        this.i = new ThreadPoolExecutor(cz.a(), cz.b(), cz.c(), TimeUnit.SECONDS, cz.d(), sVar);
        this.d = new fy(this.i, cjVar);
        this.o = new ck(context, bdVar);
        if (a2.equals("")) {
            this.b = new cm(context, aqVar, this.o, cjVar);
            this.c = new cb(context);
            a = bz.a(context, null, bdVar);
        } else {
            this.b = new cm(context, a2, bdVar, aqVar, this.o, cjVar);
            this.c = new cb(context, a2, bdVar);
            a = bz.a(context, a2, bdVar);
        }
        an ajVar = new aj(context, aVar, a2, abVar, this.c);
        this.j = new a();
        fn fnVar = new fn(this.b, ajVar, aVar);
        ch ceVar = new ce(new cl(context, a2, bdVar), this.d);
        u uVar = new u(sVar);
        sVar.a(new t(this.d));
        cg cdVar = new cd(new ca(new ci(a), uVar), this.d);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        this.l = new ai(context, ceVar, this.d, alarmManager, this.o, aVar.l(), aVar.z());
        this.q = new ac(cdVar);
        this.g = new cf(context, a2);
        bs bvVar = new bv(this.j, new da(new fb(new bb())), this.d, bVar, this.i, this.g, this.o);
        this.k = new fm(context, this.d, new fk(), alarmManager, new fl(context), a2);
        fm fmVar = this.k;
        fy fyVar = this.d;
        fyVar.a(new b<l>(fmVar) {
            final /* synthetic */ fm a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void trigger(Object obj) {
                this.a.h = fw.a;
                this.a.b();
            }
        }, l.class);
        fyVar.a(new b<m>(fmVar) {
            final /* synthetic */ fm a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void trigger(Object obj) {
                this.a.h = fw.b;
                this.a.b();
            }
        }, m.class);
        this.k.a(z2);
        this.e = new fj(aVar, this.d, bvVar, fnVar, sVar, z);
        an anVar = ajVar;
        a aVar2 = aVar;
        this.f = new ag(this.l, this.e, this.d, anVar, aVar2, this.o, this.q, a2, z2, new ah(context, this.d, this.o), cjVar);
        this.n = new er(context, this.f, this.i, bVar, aVar, a2, bdVar);
        this.p = new ad(context, bdVar, this.f, aVar, this.o);
        if (!z) {
            ((bv) bvVar).a(this.f);
        }
        this.g.a(this.f);
        this.m = new af(context, this.f, aVar, this.o);
        this.h = new fx(context, this.m, this.e, this.f, this.b, this.c, this.o, this.n, this.q, this.p, asVar);
    }

    public final ck a() {
        return this.o;
    }

    public final fm b() {
        return this.k;
    }

    public final fx c() {
        return this.h;
    }

    public final ag d() {
        return this.f;
    }

    public final fj e() {
        return this.e;
    }

    public final fy f() {
        return this.d;
    }

    public final cm g() {
        return this.b;
    }

    public final ThreadPoolExecutor h() {
        return this.i;
    }

    public final cf i() {
        return this.g;
    }

    public final ap j() {
        return this.m;
    }

    public final ac k() {
        return this.q;
    }

    public final er l() {
        return this.n;
    }

    public final ad m() {
        return this.p;
    }

    public final void n() {
        this.i.execute(new Runnable(this) {
            final /* synthetic */ db a;

            {
                this.a = r1;
            }

            public final void run() {
                try {
                    synchronized (this.a.b) {
                        if (this.a.b.c()) {
                            c.d(db.a, "User cache was locked, waiting.");
                            try {
                                this.a.b.wait();
                                c.b(db.a, "User cache notified.");
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                    this.a.e.a(this.a.d);
                } catch (Throwable e2) {
                    c.c(db.a, "Exception while shutting down dispatch manager. Continuing.", e2);
                }
                try {
                    this.a.k.a();
                } catch (Throwable e22) {
                    c.c(db.a, "Exception while un-registering data refresh broadcast receivers. Continuing.", e22);
                }
            }
        });
    }
}
