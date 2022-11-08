package com.google.android.gms.c;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class m<TResult> implements u<TResult> {
    private final Executor a;
    private final Object b = new Object();
    @GuardedBy("mLock")
    private b c;

    public m(@NonNull Executor executor, @NonNull b bVar) {
        this.a = executor;
        this.c = bVar;
    }

    public final void a(@NonNull g gVar) {
        if (gVar.c()) {
            synchronized (this.b) {
                if (this.c == null) {
                    return;
                }
                this.a.execute(new n(this));
            }
        }
    }
}
