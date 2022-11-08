package com.google.android.gms.b;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.k;

public abstract class f<T> {
    private final String a;
    private T b;

    public static class a extends Exception {
        public a(String str) {
            super(str);
        }

        public a(String str, Throwable th) {
            super(str, th);
        }
    }

    protected f(String str) {
        this.a = str;
    }

    protected final T a(Context context) throws a {
        if (this.b == null) {
            ab.a((Object) context);
            Context e = k.e(context);
            if (e == null) {
                throw new a("Could not get remote context.");
            }
            try {
                this.b = a((IBinder) e.getClassLoader().loadClass(this.a).newInstance());
            } catch (Throwable e2) {
                throw new a("Could not load creator class.", e2);
            } catch (Throwable e22) {
                throw new a("Could not instantiate creator.", e22);
            } catch (Throwable e222) {
                throw new a("Could not access creator.", e222);
            }
        }
        return this.b;
    }

    protected abstract T a(IBinder iBinder);
}
