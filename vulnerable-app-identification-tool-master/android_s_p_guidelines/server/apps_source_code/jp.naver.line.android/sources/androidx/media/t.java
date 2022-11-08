package androidx.media;

import android.os.Build.VERSION;

public abstract class t {
    private final int a;
    private final int b;
    private int c;
    private u d;
    private Object e;

    public final int a() {
        return this.c;
    }

    public final int b() {
        return this.a;
    }

    public final int c() {
        return this.b;
    }

    public final void a(u uVar) {
        this.d = uVar;
    }

    public final Object d() {
        if (this.e == null && VERSION.SDK_INT >= 21) {
            this.e = new androidx.media.v.AnonymousClass1(this.a, this.b, this.c, new w(this) {
                final /* synthetic */ t a;

                {
                    this.a = r1;
                }
            });
        }
        return this.e;
    }
}
