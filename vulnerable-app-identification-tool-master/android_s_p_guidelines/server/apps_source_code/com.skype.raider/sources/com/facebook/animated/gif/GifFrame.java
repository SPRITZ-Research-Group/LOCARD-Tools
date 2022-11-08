package com.facebook.animated.gif;

import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.imagepipeline.a.a.d;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class GifFrame implements d {
    @DoNotStrip
    private long mNativeContext;

    @DoNotStrip
    private native void nativeDispose();

    @DoNotStrip
    private native void nativeFinalize();

    @DoNotStrip
    private native int nativeGetDisposalMode();

    @DoNotStrip
    private native int nativeGetDurationMs();

    @DoNotStrip
    private native int nativeGetHeight();

    @DoNotStrip
    private native int nativeGetTransparentPixelColor();

    @DoNotStrip
    private native int nativeGetWidth();

    @DoNotStrip
    private native int nativeGetXOffset();

    @DoNotStrip
    private native int nativeGetYOffset();

    @DoNotStrip
    private native boolean nativeHasTransparency();

    @DoNotStrip
    private native void nativeRenderFrame(int i, int i2, Bitmap bitmap);

    @DoNotStrip
    GifFrame(long nativeContext) {
        this.mNativeContext = nativeContext;
    }

    protected void finalize() {
        nativeFinalize();
    }

    public final void a() {
        nativeDispose();
    }

    public final void a(int width, int height, Bitmap bitmap) {
        nativeRenderFrame(width, height, bitmap);
    }

    public final int b() {
        return nativeGetWidth();
    }

    public final int c() {
        return nativeGetHeight();
    }

    public final int d() {
        return nativeGetXOffset();
    }

    public final int e() {
        return nativeGetYOffset();
    }

    public final int f() {
        return nativeGetDisposalMode();
    }
}
