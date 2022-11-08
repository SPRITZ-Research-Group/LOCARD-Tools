package com.google.android.gms.c;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class o<TResult> implements u<TResult> {
    private final Executor a;
    private final Object b = new Object();
    @GuardedBy("mLock")
    private c<TResult> c;

    public o(@NonNull Executor executor, @NonNull c<TResult> cVar) {
        this.a = executor;
        this.c = cVar;
    }

    public final void a(@NonNull g<TResult> gVar) {
        synchronized (this.b) {
            if (this.c == null) {
                return;
            }
            this.a.execute(new p(this, gVar));
        }
    }
}
