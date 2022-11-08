package com.google.android.exoplayer2.a;

import com.google.android.exoplayer2.a.c.a;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

final class f implements c {
    private int b = -1;
    private int c = -1;
    private int[] d;
    private boolean e;
    private int[] f;
    private ByteBuffer g = a;
    private ByteBuffer h = a;
    private boolean i;

    public final void a(int[] outputChannels) {
        this.d = outputChannels;
    }

    public final boolean a(int sampleRateHz, int channelCount, int encoding) throws a {
        boolean outputChannelsChanged = !Arrays.equals(this.d, this.f);
        this.f = this.d;
        if (this.f == null) {
            this.e = false;
            return outputChannelsChanged;
        } else if (encoding != 2) {
            throw new a(sampleRateHz, channelCount, encoding);
        } else if (!outputChannelsChanged && this.c == sampleRateHz && this.b == channelCount) {
            return false;
        } else {
            boolean z;
            this.c = sampleRateHz;
            this.b = channelCount;
            if (channelCount != this.f.length) {
                z = true;
            } else {
                z = false;
            }
            this.e = z;
            int i = 0;
            while (i < this.f.length) {
                int channelIndex = this.f[i];
                if (channelIndex >= channelCount) {
                    throw new a(sampleRateHz, channelCount, encoding);
                }
                this.e = (channelIndex != i ? 1 : 0) | this.e;
                i++;
            }
            return true;
        }
    }

    public final boolean a() {
        return this.e;
    }

    public final int b() {
        return this.f == null ? this.b : this.f.length;
    }

    public final int c() {
        return 2;
    }

    public final void a(ByteBuffer inputBuffer) {
        int position = inputBuffer.position();
        int limit = inputBuffer.limit();
        int outputSize = (((limit - position) / (this.b * 2)) * this.f.length) * 2;
        if (this.g.capacity() < outputSize) {
            this.g = ByteBuffer.allocateDirect(outputSize).order(ByteOrder.nativeOrder());
        } else {
            this.g.clear();
        }
        while (position < limit) {
            for (int channelIndex : this.f) {
                this.g.putShort(inputBuffer.getShort((channelIndex * 2) + position));
            }
            position += this.b * 2;
        }
        inputBuffer.position(limit);
        this.g.flip();
        this.h = this.g;
    }

    public final void d() {
        this.i = true;
    }

    public final ByteBuffer e() {
        ByteBuffer outputBuffer = this.h;
        this.h = a;
        return outputBuffer;
    }

    public final boolean f() {
        return this.i && this.h == a;
    }

    public final void g() {
        this.h = a;
        this.i = false;
    }

    public final void h() {
        g();
        this.g = a;
        this.b = -1;
        this.c = -1;
        this.f = null;
        this.e = false;
    }
}
