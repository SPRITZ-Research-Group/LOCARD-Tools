package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public abstract class NativeArray {
    @DoNotStrip
    private HybridData mHybridData;

    public native String toString();

    static {
        ah.a();
    }

    protected NativeArray(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
