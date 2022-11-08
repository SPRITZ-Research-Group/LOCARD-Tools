package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import java.util.Arrays;

public abstract class m {
    private static final Object a = new Object();
    private static m b;

    protected static final class a {
        private final String a;
        private final String b;
        private final ComponentName c = null;
        private final int d;

        public a(String str, String str2, int i) {
            this.a = ab.a(str);
            this.b = ab.a(str2);
            this.d = i;
        }

        public final String a() {
            return this.b;
        }

        public final ComponentName b() {
            return this.c;
        }

        public final int c() {
            return this.d;
        }

        public final Intent d() {
            return this.a != null ? new Intent(this.a).setPackage(this.b) : new Intent().setComponent(this.c);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return z.a(this.a, aVar.a) && z.a(this.b, aVar.b) && z.a(this.c, aVar.c) && this.d == aVar.d;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.a, this.b, this.c, Integer.valueOf(this.d)});
        }

        public final String toString() {
            return this.a == null ? this.c.flattenToString() : this.a;
        }
    }

    public static m a(Context context) {
        synchronized (a) {
            if (b == null) {
                b = new ao(context.getApplicationContext());
            }
        }
        return b;
    }

    protected abstract boolean a(a aVar, ServiceConnection serviceConnection);

    public final boolean a(String str, String str2, int i, ServiceConnection serviceConnection) {
        return a(new a(str, str2, i), serviceConnection);
    }

    protected abstract void b(a aVar, ServiceConnection serviceConnection);

    public final void b(String str, String str2, int i, ServiceConnection serviceConnection) {
        b(new a(str, str2, i), serviceConnection);
    }
}
