package com.google.android.gms.c;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class k<TResult, TContinuationResult> implements u<TResult> {
    private final Executor a;
    private final a<TResult, TContinuationResult> b;
    private final x<TContinuationResult> c;

    public k(@NonNull Executor executor, @NonNull a<TResult, TContinuationResult> aVar, @NonNull x<TContinuationResult> xVar) {
        this.a = executor;
        this.b = aVar;
        this.c = xVar;
    }

    public final void a(@NonNull g<TResult> gVar) {
        this.a.execute(new l(this, gVar));
    }
}
