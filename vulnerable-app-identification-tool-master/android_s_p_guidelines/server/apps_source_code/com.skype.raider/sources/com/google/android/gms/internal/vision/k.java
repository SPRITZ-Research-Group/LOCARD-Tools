package com.google.android.gms.internal.vision;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.a;
import javax.annotation.concurrent.GuardedBy;

public abstract class k<T> {
    private final Context a;
    private final Object b = new Object();
    private final String c;
    private boolean d = false;
    @GuardedBy("mLock")
    private T e;

    public k(Context context, String str) {
        this.a = context;
        this.c = str;
    }

    protected abstract T a(DynamiteModule dynamiteModule, Context context) throws RemoteException, a;

    public final boolean a() {
        return b() != null;
    }

    protected final T b() {
        T t;
        synchronized (this.b) {
            if (this.e != null) {
                t = this.e;
            } else {
                try {
                    this.e = a(DynamiteModule.a(this.a, DynamiteModule.e, "com.google.android.gms.vision.dynamite"), this.a);
                } catch (a e) {
                } catch (RemoteException e2) {
                }
                if (!this.d && this.e == null) {
                    this.d = true;
                }
                t = this.e;
            }
        }
        return t;
    }
}
