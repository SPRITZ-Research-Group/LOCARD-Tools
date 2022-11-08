package androidx.lifecycle;

import androidx.lifecycle.LiveData$androidx.lifecycle.u;
import defpackage.ba;
import defpackage.be;
import java.util.Iterator;
import java.util.Map.Entry;

public abstract class LiveData<T> {
    static final Object b = new Object();
    final Object a = new Object();
    int c = 0;
    volatile Object d = b;
    private be<ac<? super T>, u> e = new be();
    private volatile Object f = b;
    private int g = -1;
    private boolean h;
    private boolean i;
    private final Runnable j = new Runnable(this) {
        final /* synthetic */ LiveData a;

        {
            this.a = r1;
        }

        public final void run() {
            Object obj;
            synchronized (this.a.a) {
                obj = this.a.d;
                this.a.d = LiveData.b;
            }
            this.a.b(obj);
        }
    };

    class LifecycleBoundObserver extends u implements h {
        final o a;
        final /* synthetic */ LiveData b;

        LifecycleBoundObserver(LiveData liveData, o oVar, ac<? super T> acVar) {
            this.b = liveData;
            super(liveData, acVar);
            this.a = oVar;
        }

        final boolean a() {
            return this.a.getLifecycle().a().a(k.STARTED);
        }

        public final void a(o oVar, j jVar) {
            if (this.a.getLifecycle().a() == k.DESTROYED) {
                this.b.b(this.c);
            } else {
                a(a());
            }
        }

        final boolean a(o oVar) {
            return this.a == oVar;
        }

        final void b() {
            this.a.getLifecycle().b(this);
        }
    }

    protected void c() {
    }

    protected void d() {
    }

    private void b(u uVar) {
        if (!uVar.d) {
            return;
        }
        if (!uVar.a()) {
            uVar.a(false);
        } else if (uVar.e < this.g) {
            uVar.e = this.g;
            uVar.c.onChanged(this.f);
        }
    }

    final void a(u uVar) {
        if (this.h) {
            this.i = true;
            return;
        }
        this.h = true;
        do {
            this.i = false;
            u uVar2;
            if (uVar2 == null) {
                Iterator c = this.e.c();
                while (c.hasNext()) {
                    b((u) ((Entry) c.next()).getValue());
                    if (this.i) {
                        break;
                    }
                }
            }
            b(uVar2);
            uVar2 = null;
        } while (this.i);
        this.h = false;
    }

    public void a(o oVar, ac<? super T> acVar) {
        a("observe");
        if (oVar.getLifecycle().a() != k.DESTROYED) {
            n lifecycleBoundObserver = new LifecycleBoundObserver(this, oVar, acVar);
            u uVar = (u) this.e.a(acVar, lifecycleBoundObserver);
            if (uVar != null && !uVar.a(oVar)) {
                throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            } else if (uVar == null) {
                oVar.getLifecycle().a(lifecycleBoundObserver);
            }
        }
    }

    public final void a(ac<? super T> acVar) {
        a("observeForever");
        t tVar = new t(this, acVar);
        u uVar = (u) this.e.a(acVar, tVar);
        if (uVar != null && (uVar instanceof LifecycleBoundObserver)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        } else if (uVar == null) {
            tVar.a(true);
        }
    }

    public void b(ac<? super T> acVar) {
        a("removeObserver");
        u uVar = (u) this.e.b(acVar);
        if (uVar != null) {
            uVar.b();
            uVar.a(false);
        }
    }

    public final void a(o oVar) {
        a("removeObservers");
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (((u) entry.getValue()).a(oVar)) {
                b((ac) entry.getKey());
            }
        }
    }

    protected void a(T t) {
        Object obj;
        synchronized (this.a) {
            obj = this.d == b ? 1 : null;
            this.d = t;
        }
        if (obj != null) {
            ba.a().b(this.j);
        }
    }

    protected void b(T t) {
        a("setValue");
        this.g++;
        this.f = t;
        a(null);
    }

    public final T a() {
        T t = this.f;
        return t != b ? t : null;
    }

    final int b() {
        return this.g;
    }

    public final boolean e() {
        return this.c > 0;
    }

    private static void a(String str) {
        if (!ba.a().c()) {
            StringBuilder stringBuilder = new StringBuilder("Cannot invoke ");
            stringBuilder.append(str);
            stringBuilder.append(" on a background thread");
            throw new IllegalStateException(stringBuilder.toString());
        }
    }
}
