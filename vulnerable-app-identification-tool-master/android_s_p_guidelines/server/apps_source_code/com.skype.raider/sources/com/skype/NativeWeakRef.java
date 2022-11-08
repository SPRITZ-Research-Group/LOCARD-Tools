package com.skype;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public abstract class NativeWeakRef<T> extends WeakReference<T> {
    protected final int nativeObject;

    public abstract void destroyNativeObject();

    public NativeWeakRef(T referent, ReferenceQueue<T> queue, int nativeObject) {
        super(referent, queue);
        this.nativeObject = nativeObject;
    }
}
