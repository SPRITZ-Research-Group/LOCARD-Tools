package com.google.android.gms.c;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class q<TResult> implements u<TResult> {
    private final Executor a;
    private final Object b = new Object();
    @GuardedBy("mLock")
    private d c;

    public q(@NonNull Executor executor, @NonNull d dVar) {
        this.a = executor;
        this.c = dVar;
    }

    public final void a(@NonNull g<TResult> gVar) {
        if (!gVar.b() && !gVar.c()) {
            synchronized (this.b) {
                if (this.c == null) {
                    return;
                }
                this.a.execute(new r(this, gVar));
            }
        }
    }
}
