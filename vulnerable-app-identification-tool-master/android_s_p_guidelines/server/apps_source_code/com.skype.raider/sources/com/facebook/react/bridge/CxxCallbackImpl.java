package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class CxxCallbackImpl implements d {
    @DoNotStrip
    private final HybridData mHybridData;

    private native void nativeInvoke(NativeArray nativeArray);

    @DoNotStrip
    private CxxCallbackImpl(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public void invoke(Object... args) {
        nativeInvoke(b.a(args));
    }
}
