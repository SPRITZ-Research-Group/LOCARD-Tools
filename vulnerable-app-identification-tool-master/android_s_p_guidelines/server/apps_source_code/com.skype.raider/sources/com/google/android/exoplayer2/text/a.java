package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.e;
import com.google.android.exoplayer2.decoder.f;
import java.nio.ByteBuffer;

public abstract class a extends f<g, h, e> implements d {
    private final String a;

    protected abstract c a(byte[] bArr, int i, boolean z) throws e;

    protected a(String name) {
        super(new g[2], new h[2]);
        this.a = name;
        e();
    }

    public final void a(long timeUs) {
    }

    protected final void a(h buffer) {
        super.a((e) buffer);
    }

    private e a(g inputBuffer, h outputBuffer, boolean reset) {
        try {
            ByteBuffer inputData = inputBuffer.b;
            h hVar = outputBuffer;
            hVar.a(inputBuffer.c, a(inputData.array(), inputData.limit(), reset), inputBuffer.d);
            outputBuffer.e();
            return null;
        } catch (e e) {
            return e;
        }
    }

    protected final /* synthetic */ e g() {
        return new b(this);
    }

    protected final /* synthetic */ DecoderInputBuffer f() {
        return new g();
    }
}
