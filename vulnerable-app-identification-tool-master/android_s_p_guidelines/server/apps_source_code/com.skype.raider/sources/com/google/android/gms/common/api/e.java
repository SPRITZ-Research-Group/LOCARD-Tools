package com.google.android.gms.common.api;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public abstract class e {
    @GuardedBy("sAllClients")
    private static final Set<e> a = Collections.newSetFromMap(new WeakHashMap());

    public interface a {
        void a();

        void b();
    }

    public interface b {
        void a(@NonNull ConnectionResult connectionResult);
    }

    @KeepForSdk
    public Looper a() {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public <A extends com.google.android.gms.common.api.a.b, T extends com.google.android.gms.common.api.internal.c.a<? extends i, A>> T a(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    public void b() {
        throw new UnsupportedOperationException();
    }

    public void c() {
        throw new UnsupportedOperationException();
    }
}
