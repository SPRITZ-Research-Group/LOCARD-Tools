package com.google.firebase.iid;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.c.h;

abstract class c<T> {
    final int a;
    final h<T> b = new h();
    final int c;
    final Bundle d;

    c(int i, int i2, Bundle bundle) {
        this.a = i;
        this.c = i2;
        this.d = bundle;
    }

    abstract void a(Bundle bundle);

    final void a(d dVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(dVar);
            new StringBuilder((String.valueOf(valueOf).length() + 14) + String.valueOf(valueOf2).length()).append("Failing ").append(valueOf).append(" with ").append(valueOf2);
        }
        this.b.a((Exception) dVar);
    }

    final void a(T t) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(t);
            new StringBuilder((String.valueOf(valueOf).length() + 16) + String.valueOf(valueOf2).length()).append("Finishing ").append(valueOf).append(" with ").append(valueOf2);
        }
        this.b.a((Object) t);
    }

    abstract boolean a();

    public String toString() {
        int i = this.c;
        int i2 = this.a;
        return "Request { what=" + i + " id=" + i2 + " oneWay=" + a() + "}";
    }
}
