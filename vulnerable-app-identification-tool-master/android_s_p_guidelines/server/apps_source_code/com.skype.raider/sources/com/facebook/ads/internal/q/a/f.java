package com.facebook.ads.internal.q.a;

import android.os.Handler;
import android.support.annotation.VisibleForTesting;

public final class f {
    private final Handler a;
    private final a b;
    private int c;
    private boolean d;

    public interface a {
        void a();

        void a(int i);
    }

    public f(int i, a aVar) {
        this(i, aVar, new Handler());
    }

    @VisibleForTesting
    private f(int i, a aVar, Handler handler) {
        this.d = false;
        this.c = i;
        this.b = aVar;
        this.a = handler;
    }

    public final boolean a() {
        if (this.c <= 0 || this.d) {
            return false;
        }
        this.d = true;
        this.b.a(this.c);
        this.a.postDelayed(new Runnable(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public final void run() {
                if (this.a.c()) {
                    f.a(this.a);
                    this.a.a.postDelayed(this, 1000);
                }
            }
        }, 1000);
        return true;
    }

    public final boolean b() {
        if (!this.d) {
            return false;
        }
        this.d = false;
        return true;
    }

    public final boolean c() {
        return this.d;
    }

    public final boolean d() {
        return this.c <= 0;
    }

    static /* synthetic */ void a(f fVar) {
        fVar.c--;
        fVar.b.a(fVar.c);
        if (fVar.c == 0) {
            fVar.b.a();
            fVar.d = false;
        }
    }
}
