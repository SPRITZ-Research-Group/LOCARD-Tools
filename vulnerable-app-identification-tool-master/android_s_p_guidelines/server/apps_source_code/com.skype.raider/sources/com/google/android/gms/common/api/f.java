package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.TimeUnit;

@KeepForSdk
public abstract class f<R extends i> {

    @KeepForSdk
    public interface a {
        @KeepForSdk
        void a(Status status);
    }

    @NonNull
    public abstract R a(@NonNull TimeUnit timeUnit);

    @KeepForSdk
    public void a(@NonNull a aVar) {
        throw new UnsupportedOperationException();
    }

    public abstract void a(@NonNull j<? super R> jVar);

    public abstract boolean a();
}
