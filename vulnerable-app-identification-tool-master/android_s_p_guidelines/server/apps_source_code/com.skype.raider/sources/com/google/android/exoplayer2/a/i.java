package com.google.android.exoplayer2.a;

import com.google.android.exoplayer2.a.c.a;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class i implements c {
    private int b = -1;
    private int c = -1;
    private int d = 0;
    private ByteBuffer e = a;
    private ByteBuffer f = a;
    private boolean g;

    public final boolean a(int sampleRateHz, int channelCount, int encoding) throws a {
        if (encoding != 3 && encoding != 2 && encoding != Integer.MIN_VALUE && encoding != ErrorDialogData.SUPPRESSED) {
            throw new a(sampleRateHz, channelCount, encoding);
        } else if (this.b == sampleRateHz && this.c == channelCount && this.d == encoding) {
            return false;
        } else {
            this.b = sampleRateHz;
            this.c = channelCount;
            this.d = encoding;
            if (encoding == 2) {
                this.e = a;
            }
            return true;
        }
    }

    public final boolean a() {
        return (this.d == 0 || this.d == 2) ? false : true;
    }

    public final int b() {
        return this.c;
    }

    public final int c() {
        return 2;
    }

    public final void a(ByteBuffer inputBuffer) {
        int resampledSize;
        int position = inputBuffer.position();
        int limit = inputBuffer.limit();
        int size = limit - position;
        switch (this.d) {
            case Integer.MIN_VALUE:
                resampledSize = (size / 3) * 2;
                break;
            case 3:
                resampledSize = size * 2;
                break;
            case ErrorDialogData.SUPPRESSED /*1073741824*/:
                resampledSize = size / 2;
                break;
            default:
                throw new IllegalStateException();
        }
        if (this.e.capacity() < resampledSize) {
            this.e = ByteBuffer.allocateDirect(resampledSize).order(ByteOrder.nativeOrder());
        } else {
            this.e.clear();
        }
        int i;
        switch (this.d) {
            case Integer.MIN_VALUE:
                for (i = position; i < limit; i += 3) {
                    this.e.put(inputBuffer.get(i + 1));
                    this.e.put(inputBuffer.get(i + 2));
                }
                break;
            case 3:
                for (i = position; i < limit; i++) {
                    this.e.put((byte) 0);
                    this.e.put((byte) ((inputBuffer.get(i) & 255) - 128));
                }
                break;
            case ErrorDialogData.SUPPRESSED /*1073741824*/:
                for (i = position; i < limit; i += 4) {
                    this.e.put(inputBuffer.get(i + 2));
                    this.e.put(inputBuffer.get(i + 3));
                }
                break;
            default:
                throw new IllegalStateException();
        }
        inputBuffer.position(inputBuffer.limit());
        this.e.flip();
        this.f = this.e;
    }

    public final void d() {
        this.g = true;
    }

    public final ByteBuffer e() {
        ByteBuffer outputBuffer = this.f;
        this.f = a;
        return outputBuffer;
    }

    public final boolean f() {
        return this.g && this.f == a;
    }

    public final void g() {
        this.f = a;
        this.g = false;
    }

    public final void h() {
        g();
        this.e = a;
        this.b = -1;
        this.c = -1;
        this.d = 0;
    }
}
