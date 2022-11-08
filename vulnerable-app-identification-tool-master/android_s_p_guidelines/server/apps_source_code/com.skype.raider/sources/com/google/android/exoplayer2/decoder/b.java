package com.google.android.exoplayer2.decoder;

import android.annotation.TargetApi;
import android.media.MediaCodec.CryptoInfo;
import android.media.MediaCodec.CryptoInfo.Pattern;
import com.google.android.exoplayer2.d.s;

public final class b {
    public byte[] a;
    public byte[] b;
    public int c;
    public int[] d;
    public int[] e;
    public int f;
    public int g;
    public int h;
    private final CryptoInfo i;
    private final a j;

    @TargetApi(24)
    private static final class a {
        private final CryptoInfo a;
        private final Pattern b;

        /* synthetic */ a(CryptoInfo x0, byte b) {
            this(x0);
        }

        private a(CryptoInfo frameworkCryptoInfo) {
            this.a = frameworkCryptoInfo;
            this.b = new Pattern(0, 0);
        }

        static /* synthetic */ void a(a x0, int x1, int x2) {
            x0.b.set(x1, x2);
            x0.a.setPattern(x0.b);
        }
    }

    public b() {
        CryptoInfo cryptoInfo;
        a aVar = null;
        if (s.a >= 16) {
            cryptoInfo = new CryptoInfo();
        } else {
            cryptoInfo = null;
        }
        this.i = cryptoInfo;
        if (s.a >= 24) {
            aVar = new a(this.i, (byte) 0);
        }
        this.j = aVar;
    }

    public final void a(int numSubSamples, int[] numBytesOfClearData, int[] numBytesOfEncryptedData, byte[] key, byte[] iv) {
        this.f = numSubSamples;
        this.d = numBytesOfClearData;
        this.e = numBytesOfEncryptedData;
        this.b = key;
        this.a = iv;
        this.c = 1;
        this.g = 0;
        this.h = 0;
        if (s.a >= 16) {
            this.i.numSubSamples = this.f;
            this.i.numBytesOfClearData = this.d;
            this.i.numBytesOfEncryptedData = this.e;
            this.i.key = this.b;
            this.i.iv = this.a;
            this.i.mode = this.c;
            if (s.a >= 24) {
                a.a(this.j, this.g, this.h);
            }
        }
    }

    @TargetApi(16)
    public final CryptoInfo a() {
        return this.i;
    }
}
