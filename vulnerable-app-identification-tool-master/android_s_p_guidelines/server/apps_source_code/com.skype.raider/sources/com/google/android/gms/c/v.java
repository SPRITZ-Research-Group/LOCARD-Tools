package com.google.android.gms.c;

import android.support.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;

final class v<TResult> {
    private final Object a = new Object();
    @GuardedBy("mLock")
    private Queue<u<TResult>> b;
    @GuardedBy("mLock")
    private boolean c;

    v() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(@NonNull g<TResult> gVar) {
        synchronized (this.a) {
            if (this.b == null || this.c) {
            } else {
                this.c = true;
            }
        }
    }

    public final void a(@NonNull u<TResult> uVar) {
        synchronized (this.a) {
            if (this.b == null) {
                this.b = new ArrayDeque();
            }
            this.b.add(uVar);
        }
    }
}
