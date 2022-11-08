package androidx.lifecycle;

import android.os.Handler;

public final class ah {
    private final p a;
    private final Handler b = new Handler();
    private ai c;

    public ah(o oVar) {
        this.a = new p(oVar);
    }

    private void a(j jVar) {
        if (this.c != null) {
            this.c.run();
        }
        this.c = new ai(this.a, jVar);
        this.b.postAtFrontOfQueue(this.c);
    }

    public final void a() {
        a(j.ON_CREATE);
    }

    public final void b() {
        a(j.ON_START);
    }

    public final void c() {
        a(j.ON_START);
    }

    public final void d() {
        a(j.ON_STOP);
        a(j.ON_DESTROY);
    }

    public final i e() {
        return this.a;
    }
}
