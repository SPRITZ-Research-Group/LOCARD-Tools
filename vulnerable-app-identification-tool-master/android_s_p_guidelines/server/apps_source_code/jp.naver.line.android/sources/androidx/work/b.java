package androidx.work;

import android.os.Build.VERSION;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class b {
    private final Executor a;
    private final ah b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;

    b(c cVar) {
        if (cVar.a == null) {
            this.a = Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)));
        } else {
            this.a = cVar.a;
        }
        if (cVar.b == null) {
            this.b = ah.a();
        } else {
            this.b = cVar.b;
        }
        this.c = cVar.c;
        this.d = cVar.d;
        this.e = cVar.e;
        this.f = cVar.f;
    }

    public final Executor a() {
        return this.a;
    }

    public final ah b() {
        return this.b;
    }

    public final int c() {
        return this.c;
    }

    public final int d() {
        return this.d;
    }

    public final int e() {
        return this.e;
    }

    public final int f() {
        if (VERSION.SDK_INT == 23) {
            return this.f / 2;
        }
        return this.f;
    }
}
