package com.google.android.gms.common.api.internal;

import android.support.annotation.Nullable;
import android.support.v4.util.a;
import com.google.android.gms.c.h;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.c;
import java.util.Map;
import java.util.Set;

public final class aq {
    private final a<ap<?>, ConnectionResult> a;
    private final a<ap<?>, String> b;
    private final h<Map<ap<?>, String>> c;
    private int d;
    private boolean e;

    public final Set<ap<?>> a() {
        return this.a.keySet();
    }

    public final void a(ap<?> apVar, ConnectionResult connectionResult, @Nullable String str) {
        this.a.put(apVar, connectionResult);
        this.b.put(apVar, str);
        this.d--;
        if (!connectionResult.b()) {
            this.e = true;
        }
        if (this.d != 0) {
            return;
        }
        if (this.e) {
            this.c.a(new c(this.a));
            return;
        }
        this.c.a(this.b);
    }
}
