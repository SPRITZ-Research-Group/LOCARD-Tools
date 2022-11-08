package com.facebook.jni;

import com.facebook.jni.a.a;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;

@DoNotStrip
public class HybridData {
    @DoNotStrip
    private Destructor mDestructor = new Destructor(this);

    public static class Destructor extends a {
        @DoNotStrip
        private long mNativePointer;

        static native void deleteNative(long j);

        Destructor(Object referent) {
            super(referent);
        }

        final void a() {
            deleteNative(this.mNativePointer);
            this.mNativePointer = 0;
        }
    }

    static {
        SoLoader.a("fb");
    }

    public synchronized void resetNative() {
        this.mDestructor.a();
    }

    public boolean isValid() {
        return this.mDestructor.mNativePointer != 0;
    }
}
