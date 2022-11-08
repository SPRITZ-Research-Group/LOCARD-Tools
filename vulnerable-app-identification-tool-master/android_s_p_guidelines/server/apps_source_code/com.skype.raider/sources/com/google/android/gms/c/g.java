package com.google.android.gms.c;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.concurrent.Executor;

public abstract class g<TResult> {
    @NonNull
    public g<TResult> a(@NonNull c<TResult> cVar) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    @NonNull
    public abstract g<TResult> a(@NonNull d dVar);

    @NonNull
    public abstract g<TResult> a(@NonNull e<? super TResult> eVar);

    @NonNull
    public <TContinuationResult> g<TContinuationResult> a(@NonNull Executor executor, @NonNull a<TResult, TContinuationResult> aVar) {
        throw new UnsupportedOperationException("continueWith is not implemented");
    }

    @NonNull
    public g<TResult> a(@NonNull Executor executor, @NonNull b bVar) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented");
    }

    @NonNull
    public g<TResult> a(@NonNull Executor executor, @NonNull c<TResult> cVar) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    @NonNull
    public abstract g<TResult> a(@NonNull Executor executor, @NonNull d dVar);

    @NonNull
    public abstract g<TResult> a(@NonNull Executor executor, @NonNull e<? super TResult> eVar);

    public abstract <X extends Throwable> TResult a(@NonNull Class<X> cls) throws Throwable;

    public abstract boolean a();

    public abstract boolean b();

    public abstract boolean c();

    public abstract TResult d();

    @Nullable
    public abstract Exception e();
}
