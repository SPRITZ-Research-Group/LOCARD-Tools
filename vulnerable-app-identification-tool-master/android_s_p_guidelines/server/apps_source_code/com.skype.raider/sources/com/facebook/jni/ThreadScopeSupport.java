package com.facebook.jni;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;

@DoNotStrip
public class ThreadScopeSupport {
    private static native void runStdFunctionImpl(long j);

    static {
        SoLoader.a("fb");
    }

    @DoNotStrip
    private static void runStdFunction(long ptr) {
        runStdFunctionImpl(ptr);
    }
}
