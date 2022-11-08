package a.a;

import com.appboy.f.c;
import java.util.concurrent.ThreadFactory;

public class fj implements fp {
    private static final String a = c.a(fj.class);
    private final com.appboy.a.a b;
    private final bs c;
    private final fn d;
    private final Object e = new Object();
    private volatile boolean f = false;
    private volatile Thread g;
    private volatile boolean h = true;
    private bt i;
    private boolean j = false;

    class a implements Runnable {
        final /* synthetic */ fj a;

        private a(fj fjVar) {
            this.a = fjVar;
        }

        /* synthetic */ a(fj fjVar, byte b) {
            this(fjVar);
        }

        public final void run() {
            while (this.a.h) {
                try {
                    fj.a(this.a, this.a.d.b());
                } catch (InterruptedException e) {
                    c.b(fj.a, "Automatic thread interrupted! [" + e.getMessage() + "]");
                }
            }
        }
    }

    public fj(com.appboy.a.a aVar, b bVar, bs bsVar, fn fnVar, ThreadFactory threadFactory, boolean z) {
        this.b = aVar;
        this.c = bsVar;
        this.d = fnVar;
        this.g = threadFactory.newThread(new a());
        this.i = new bt(bVar);
        this.j = z;
    }

    public final void a(av avVar) {
        this.d.a(avVar);
    }

    public final void a(bq bqVar) {
        this.d.a(bqVar);
    }

    public final void b(av avVar) {
        this.d.b(avVar);
    }

    public final void a(bc bcVar) {
        this.d.a(bcVar);
    }

    public final void a(fy fyVar) {
        synchronized (this.e) {
            this.h = false;
            this.g.interrupt();
            this.g = null;
        }
        if (!this.d.a()) {
            this.d.a(new bm(this.b.a()));
        }
        br c = this.d.c();
        if (c != null) {
            if (c.h() || this.j) {
                this.i.b(c);
            } else {
                this.c.b(c);
            }
        }
        fyVar.a();
    }

    public final void a() {
        synchronized (this.e) {
            if (this.f) {
                c.b(a, "Automatic request execution start was previously requested, continuing without action.");
                return;
            }
            if (this.g != null) {
                this.g.start();
            }
            this.f = true;
        }
    }

    static /* synthetic */ void a(fj fjVar, bq bqVar) {
        if (bqVar.h() || fjVar.j) {
            fjVar.i.a(bqVar);
        } else {
            fjVar.c.a(bqVar);
        }
    }
}
