package com.google.android.exoplayer2.a;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public interface c {
    public static final ByteBuffer a = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());

    public static final class a extends Exception {
        public a(int sampleRateHz, int channelCount, int encoding) {
            super("Unhandled format: " + sampleRateHz + " Hz, " + channelCount + " channels in encoding " + encoding);
        }
    }

    void a(ByteBuffer byteBuffer);

    boolean a();

    boolean a(int i, int i2, int i3) throws a;

    int b();

    int c();

    void d();

    ByteBuffer e();

    boolean f();

    void g();

    void h();
}
