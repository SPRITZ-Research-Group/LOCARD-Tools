package androidx.core.app;

import android.app.PendingIntent;
import android.os.Bundle;

public final class x {
    final Bundle a;
    boolean b;
    public int c;
    public CharSequence d;
    public PendingIntent e;
    private final an[] f;
    private final an[] g;
    private boolean h;
    private final int i;

    public x(int i, CharSequence charSequence, PendingIntent pendingIntent) {
        this(i, charSequence, pendingIntent, new Bundle(), null, null, true, 0, true);
    }

    x(int i, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, an[] anVarArr, an[] anVarArr2, boolean z, int i2, boolean z2) {
        this.b = true;
        this.c = i;
        this.d = aa.e(charSequence);
        this.e = pendingIntent;
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.a = bundle;
        this.f = anVarArr;
        this.g = anVarArr2;
        this.h = z;
        this.i = i2;
        this.b = z2;
    }

    public final boolean a() {
        return this.h;
    }

    public final an[] b() {
        return this.f;
    }

    public final int c() {
        return this.i;
    }

    public final an[] d() {
        return this.g;
    }
}
