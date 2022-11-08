package com.google.android.gms.internal.measurement;

public final class ax {
    private final int a;
    private final boolean b;
    private final boolean c;
    private final /* synthetic */ av d;

    ax(av avVar, int i, boolean z, boolean z2) {
        this.d = avVar;
        this.a = i;
        this.b = z;
        this.c = z2;
    }

    public final void a(String str) {
        this.d.a(this.a, this.b, this.c, str, null, null, null);
    }

    public final void a(String str, Object obj) {
        this.d.a(this.a, this.b, this.c, str, obj, null, null);
    }

    public final void a(String str, Object obj, Object obj2) {
        this.d.a(this.a, this.b, this.c, str, obj, obj2, null);
    }

    public final void a(String str, Object obj, Object obj2, Object obj3) {
        this.d.a(this.a, this.b, this.c, str, obj, obj2, obj3);
    }
}
