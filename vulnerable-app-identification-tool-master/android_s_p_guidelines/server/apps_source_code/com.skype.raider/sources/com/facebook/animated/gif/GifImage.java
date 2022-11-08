package com.facebook.animated.gif;

import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.h;
import com.facebook.imagepipeline.a.a.b;
import com.facebook.imagepipeline.a.a.b.a;
import com.facebook.imagepipeline.a.a.c;
import com.facebook.soloader.SoLoader;
import java.nio.ByteBuffer;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
public class GifImage implements c, com.facebook.imagepipeline.a.b.c {
    private static final int LOOP_COUNT_FOREVER = 0;
    private static final int LOOP_COUNT_MISSING = -1;
    private static volatile boolean sInitialized;
    @DoNotStrip
    private long mNativeContext;

    @DoNotStrip
    private static native GifImage nativeCreateFromDirectByteBuffer(ByteBuffer byteBuffer);

    @DoNotStrip
    private static native GifImage nativeCreateFromNativeMemory(long j, int i);

    @DoNotStrip
    private native void nativeDispose();

    @DoNotStrip
    private native void nativeFinalize();

    @DoNotStrip
    private native int nativeGetDuration();

    @DoNotStrip
    private native GifFrame nativeGetFrame(int i);

    @DoNotStrip
    private native int nativeGetFrameCount();

    @DoNotStrip
    private native int[] nativeGetFrameDurations();

    @DoNotStrip
    private native int nativeGetHeight();

    @DoNotStrip
    private native int nativeGetLoopCount();

    @DoNotStrip
    private native int nativeGetSizeInBytes();

    @DoNotStrip
    private native int nativeGetWidth();

    private static synchronized void ensure() {
        synchronized (GifImage.class) {
            if (!sInitialized) {
                sInitialized = true;
                SoLoader.a("gifimage");
            }
        }
    }

    public static GifImage create(byte[] source) {
        ensure();
        h.a((Object) source);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(source.length);
        byteBuffer.put(source);
        byteBuffer.rewind();
        return nativeCreateFromDirectByteBuffer(byteBuffer);
    }

    public static GifImage create(ByteBuffer byteBuffer) {
        ensure();
        byteBuffer.rewind();
        return nativeCreateFromDirectByteBuffer(byteBuffer);
    }

    public static GifImage create(long nativePtr, int sizeInBytes) {
        ensure();
        h.a(nativePtr != 0);
        return nativeCreateFromNativeMemory(nativePtr, sizeInBytes);
    }

    public c decode(long nativePtr, int sizeInBytes) {
        return create(nativePtr, sizeInBytes);
    }

    public c decode(ByteBuffer byteBuffer) {
        return create(byteBuffer);
    }

    @DoNotStrip
    GifImage(long nativeContext) {
        this.mNativeContext = nativeContext;
    }

    protected void finalize() {
        nativeFinalize();
    }

    public void dispose() {
        nativeDispose();
    }

    public int getWidth() {
        return nativeGetWidth();
    }

    public int getHeight() {
        return nativeGetHeight();
    }

    public int getFrameCount() {
        return nativeGetFrameCount();
    }

    public int getDuration() {
        return nativeGetDuration();
    }

    public int[] getFrameDurations() {
        return nativeGetFrameDurations();
    }

    public int getLoopCount() {
        int loopCount = nativeGetLoopCount();
        switch (loopCount) {
            case -1:
                return 1;
            case 0:
                return 0;
            default:
                return loopCount + 1;
        }
    }

    public GifFrame getFrame(int frameNumber) {
        return nativeGetFrame(frameNumber);
    }

    public boolean doesRenderSupportScaling() {
        return false;
    }

    public int getSizeInBytes() {
        return nativeGetSizeInBytes();
    }

    public b getFrameInfo(int frameNumber) {
        GifFrame frame = getFrame(frameNumber);
        try {
            b bVar = new b(frameNumber, frame.d(), frame.e(), frame.b(), frame.c(), a.BLEND_WITH_PREVIOUS, fromGifDisposalMethod(frame.f()));
            return bVar;
        } finally {
            frame.a();
        }
    }

    private static b.b fromGifDisposalMethod(int disposalMode) {
        if (disposalMode == 0) {
            return b.b.DISPOSE_DO_NOT;
        }
        if (disposalMode == 1) {
            return b.b.DISPOSE_DO_NOT;
        }
        if (disposalMode == 2) {
            return b.b.DISPOSE_TO_BACKGROUND;
        }
        if (disposalMode == 3) {
            return b.b.DISPOSE_TO_PREVIOUS;
        }
        return b.b.DISPOSE_DO_NOT;
    }
}
