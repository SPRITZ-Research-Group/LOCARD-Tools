package com.facebook.internal;

import android.content.Intent;
import java.util.UUID;

public final class a {
    private static a a;
    private UUID b;
    private Intent c;
    private int d;

    public a(int i) {
        this(i, UUID.randomUUID());
    }

    private a(int i, UUID uuid) {
        this.b = uuid;
        this.d = i;
    }

    public final Intent a() {
        return this.c;
    }

    public final UUID b() {
        return this.b;
    }

    public final int c() {
        return this.d;
    }

    public final void a(Intent intent) {
        this.c = intent;
    }

    public final boolean d() {
        return a(this);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized a a(UUID uuid, int i) {
        synchronized (a.class) {
            a aVar = a;
            if (aVar != null && aVar.b.equals(uuid) && aVar.d == i) {
                a(null);
                return aVar;
            }
        }
    }

    private static synchronized boolean a(a aVar) {
        boolean z;
        synchronized (a.class) {
            a aVar2 = a;
            a = aVar;
            if (aVar2 != null) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }
}
