package com.facebook.react.packagerconnection;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;

public final class SamplingProfilerPackagerMethod extends b {

    private static final class SamplingProfilerJniMethod {
        @DoNotStrip
        private final HybridData mHybridData;

        @DoNotStrip
        private static native HybridData initHybrid(long j);

        @DoNotStrip
        private native void poke(Responder responder);
    }

    static {
        SoLoader.a("packagerconnectionjnifb");
    }
}
