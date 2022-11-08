package com.google.android.gms.internal.b;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class k extends WeakReference<Throwable> {
    private final int a;

    public k(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        if (th == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.a = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        k kVar = (k) obj;
        return this.a == kVar.a && get() == kVar.get();
    }

    public final int hashCode() {
        return this.a;
    }
}
