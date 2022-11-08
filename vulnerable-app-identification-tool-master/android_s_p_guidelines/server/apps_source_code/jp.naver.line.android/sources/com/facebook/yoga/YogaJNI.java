package com.facebook.yoga;

import com.facebook.soloader.SoLoader;

public class YogaJNI {
    public static int JNI_FAST_CALLS = 4;
    public static boolean useFastCall = false;

    private static native void jni_bindNativeMethods(boolean z);

    static boolean init() {
        if (!SoLoader.loadLibrary("yoga")) {
            return false;
        }
        jni_bindNativeMethods(useFastCall);
        return true;
    }
}
