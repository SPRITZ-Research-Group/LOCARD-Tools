package com.google.android.exoplayer2.extractor.b;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.e;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.m;
import com.skype.Defines;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class b extends h {
    private e a;
    private a b;

    private class a implements f, m {
        final /* synthetic */ b a;
        private long[] b;
        private long[] c;
        private long d = -1;
        private long e = -1;

        public a(b bVar) {
            this.a = bVar;
        }

        public final void b(long firstFrameOffset) {
            this.d = firstFrameOffset;
        }

        public final void a(k data) {
            data.d(1);
            int numberOfSeekPoints = data.k() / 18;
            this.b = new long[numberOfSeekPoints];
            this.c = new long[numberOfSeekPoints];
            for (int i = 0; i < numberOfSeekPoints; i++) {
                this.b[i] = data.p();
                this.c[i] = data.p();
                data.d(2);
            }
        }

        public final long a(g input) throws IOException, InterruptedException {
            if (this.e < 0) {
                return -1;
            }
            long result = -(this.e + 2);
            this.e = -1;
            return result;
        }

        public final long a_(long timeUs) {
            long granule = this.a.b(timeUs);
            this.e = this.b[s.a(this.b, granule, true)];
            return granule;
        }

        public final m a() {
            return this;
        }

        public final boolean i_() {
            return true;
        }

        public final long a(long timeUs) {
            return this.d + this.c[s.a(this.b, this.a.b(timeUs), true)];
        }

        public final long b() {
            e a = this.a.a;
            return (a.h * 1000000) / ((long) a.e);
        }
    }

    b() {
    }

    protected final void a(boolean headerData) {
        super.a(headerData);
        if (headerData) {
            this.a = null;
            this.b = null;
        }
    }

    private static boolean a(byte[] data) {
        return data[0] == (byte) -1;
    }

    protected final long a(k packet) {
        if (!a(packet.a)) {
            return -1;
        }
        int i = (packet.a[2] & 255) >> 4;
        switch (i) {
            case 1:
                i = 192;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                i = 576 << (i - 2);
                break;
            case 6:
            case 7:
                packet.d(4);
                packet.y();
                i = i == 6 ? packet.g() : packet.h();
                packet.c(0);
                i++;
                break;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                i = Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE << (i - 8);
                break;
            default:
                i = -1;
                break;
        }
        return (long) i;
    }

    protected final boolean a(k packet, long position, a setupData) throws IOException, InterruptedException {
        byte[] data = packet.a;
        if (this.a == null) {
            this.a = new e(data);
            byte[] metadata = Arrays.copyOfRange(data, 9, packet.c());
            metadata[4] = Byte.MIN_VALUE;
            List<byte[]> initializationData = Collections.singletonList(metadata);
            e eVar = this.a;
            setupData.a = Format.a(null, "audio/x-flac", -1, eVar.e * eVar.g, this.a.f, this.a.e, initializationData, null, null);
        } else if ((data[0] & 127) == 3) {
            this.b = new a(this);
            this.b.a(packet);
        } else if (a(data)) {
            if (this.b != null) {
                this.b.b(position);
                setupData.b = this.b;
            }
            return false;
        }
        return true;
    }
}
