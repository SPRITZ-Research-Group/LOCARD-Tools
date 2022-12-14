package com.facebook.jni;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;

@DoNotStrip
public class Countable {
    @DoNotStrip
    private long mInstance = 0;

    public native void dispose();

    static {
        SoLoader.a("fb");
    }

    protected void finalize() throws Throwable {
        dispose();
        super.finalize();
    }
}
