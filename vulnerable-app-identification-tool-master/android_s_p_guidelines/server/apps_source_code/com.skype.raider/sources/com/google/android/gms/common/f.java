package com.google.android.gms.common;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.b.b;
import com.google.android.gms.b.d;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.s;
import com.google.android.gms.common.internal.v;
import com.google.android.gms.dynamite.DynamiteModule;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
final class f {
    private static volatile v a;
    private static final Object b = new Object();
    private static Context c;

    static abstract class a extends com.google.android.gms.common.internal.s.a {
        private int a;

        protected a(byte[] bArr) {
            ab.b(bArr.length == 25);
            this.a = Arrays.hashCode(bArr);
        }

        protected static byte[] a(String str) {
            try {
                return str.getBytes("ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }

        abstract byte[] a();

        public final b b() {
            return d.a(a());
        }

        public final int c() {
            return hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof s)) {
                return false;
            }
            try {
                s sVar = (s) obj;
                if (sVar.c() != hashCode()) {
                    return false;
                }
                b b = sVar.b();
                if (b == null) {
                    return false;
                }
                return Arrays.equals(a(), (byte[]) d.a(b));
            } catch (RemoteException e) {
                return false;
            }
        }

        public int hashCode() {
            return this.a;
        }
    }

    static synchronized void a(Context context) {
        synchronized (f.class) {
            if (c == null && context != null) {
                c = context.getApplicationContext();
            }
        }
    }

    static u a(String str, a aVar, boolean z) {
        boolean z2 = true;
        try {
            if (a == null) {
                ab.a(c);
                synchronized (b) {
                    if (a == null) {
                        a = com.google.android.gms.common.internal.v.a.a(DynamiteModule.a(c, DynamiteModule.d, "com.google.android.gms.googlecertificates").a("com.google.android.gms.common.GoogleCertificatesImpl"));
                    }
                }
            }
            ab.a(c);
            try {
                if (a.a(new GoogleCertificatesQuery(str, aVar, z), d.a(c.getPackageManager()))) {
                    return u.a();
                }
                if (z || !a(str, aVar, true).a) {
                    z2 = false;
                }
                return u.a(str, aVar, z, z2);
            } catch (Throwable e) {
                return u.a("module call", e);
            }
        } catch (Throwable e2) {
            return u.a("module init", e2);
        }
    }
}
