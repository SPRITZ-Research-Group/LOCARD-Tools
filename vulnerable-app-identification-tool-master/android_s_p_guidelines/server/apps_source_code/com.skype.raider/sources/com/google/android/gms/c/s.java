package com.google.android.gms.c;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class s<TResult> implements u<TResult> {
    private final Executor a;
    private final Object b = new Object();
    @GuardedBy("mLock")
    private e<? super TResult> c;

    public s(@NonNull Executor executor, @NonNull e<? super TResult> eVar) {
        this.a = executor;
        this.c = eVar;
    }

    public final void a(@NonNull g<TResult> gVar) {
        if (gVar.b()) {
            synchronized (this.b) {
                if (this.c == null) {
                    return;
                }
                this.a.execute(new t(this, gVar));
            }
        }
    }
}
