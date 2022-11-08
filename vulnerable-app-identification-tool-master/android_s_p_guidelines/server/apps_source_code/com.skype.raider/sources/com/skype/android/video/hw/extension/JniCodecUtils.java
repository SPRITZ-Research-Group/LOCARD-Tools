package com.skype.android.video.hw.extension;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public final class JniCodecUtils {
    public static native void fillInputFrameBuffer(long j, ByteBuffer byteBuffer, int i, boolean z);

    @Deprecated
    public static native void returnCapabilities(long j, String str, int[] iArr, int i, int[] iArr2, long j2, long j3, boolean z, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z2, int i9, boolean z3);

    public static native void returnCapabilitiesBuffer(long j, String str, int[] iArr, int i, int[] iArr2, Buffer buffer, long j2, boolean z, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z2, int i9, boolean z3);
}
