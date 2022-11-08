package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.d.j;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.q;
import com.google.android.exoplayer2.extractor.f;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.i;
import com.google.android.exoplayer2.extractor.l;
import com.google.android.exoplayer2.extractor.ts.t.d;
import com.skype.Defines;
import java.io.IOException;

public final class o implements f {
    public static final i a = new i() {
        public final f[] a() {
            return new f[]{new o()};
        }
    };
    private final q b;
    private final SparseArray<a> c;
    private final k d;
    private boolean e;
    private boolean f;
    private boolean g;
    private h h;

    private static final class a {
        private final g a;
        private final q b;
        private final j c = new j(new byte[64]);
        private boolean d;
        private boolean e;
        private boolean f;
        private int g;
        private long h;

        public a(g pesPayloadReader, q timestampAdjuster) {
            this.a = pesPayloadReader;
            this.b = timestampAdjuster;
        }

        public final void a() {
            this.f = false;
            this.a.a();
        }

        public final void a(k data) {
            data.a(this.c.a, 0, 3);
            this.c.a(0);
            this.c.b(8);
            this.d = this.c.d();
            this.e = this.c.d();
            this.c.b(6);
            this.g = this.c.c(8);
            data.a(this.c.a, 0, this.g);
            this.c.a(0);
            this.h = 0;
            if (this.d) {
                this.c.b(4);
                long c = ((long) this.c.c(3)) << 30;
                this.c.b(1);
                c |= (long) (this.c.c(15) << 15);
                this.c.b(1);
                c |= (long) this.c.c(15);
                this.c.b(1);
                if (!this.f && this.e) {
                    this.c.b(4);
                    long c2 = ((long) this.c.c(3)) << 30;
                    this.c.b(1);
                    c2 |= (long) (this.c.c(15) << 15);
                    this.c.b(1);
                    c2 |= (long) this.c.c(15);
                    this.c.b(1);
                    this.b.a(c2);
                    this.f = true;
                }
                this.h = this.b.a(c);
            }
            this.a.a(this.h, true);
            this.a.a(data);
            this.a.b();
        }
    }

    public o() {
        this(new q(0));
    }

    private o(q timestampAdjuster) {
        this.b = timestampAdjuster;
        this.d = new k(4096);
        this.c = new SparseArray();
    }

    public final boolean a(g input) throws IOException, InterruptedException {
        byte[] scratch = new byte[14];
        input.c(scratch, 0, 14);
        if (442 != (((((scratch[0] & 255) << 24) | ((scratch[1] & 255) << 16)) | ((scratch[2] & 255) << 8)) | (scratch[3] & 255)) || (scratch[4] & 196) != 68 || (scratch[6] & 4) != 4 || (scratch[8] & 4) != 4 || (scratch[9] & 1) != 1 || (scratch[12] & 3) != 3) {
            return false;
        }
        input.c(scratch[13] & 7);
        input.c(scratch, 0, 3);
        if (1 == ((((scratch[0] & 255) << 16) | ((scratch[1] & 255) << 8)) | (scratch[2] & 255))) {
            return true;
        }
        return false;
    }

    public final void a(h output) {
        this.h = output;
        output.a(new com.google.android.exoplayer2.extractor.m.a(-9223372036854775807L));
    }

    public final void a(long position, long timeUs) {
        this.b.d();
        for (int i = 0; i < this.c.size(); i++) {
            ((a) this.c.valueAt(i)).a();
        }
    }

    public final int a(g input, l seekPosition) throws IOException, InterruptedException {
        if (!input.b(this.d.a, 0, 4, true)) {
            return -1;
        }
        this.d.c(0);
        int nextStartCode = this.d.n();
        if (nextStartCode == 441) {
            return -1;
        }
        if (nextStartCode == 442) {
            input.c(this.d.a, 0, 10);
            this.d.c(9);
            input.b((this.d.g() & 7) + 14);
            return 0;
        } else if (nextStartCode == 443) {
            input.c(this.d.a, 0, 2);
            this.d.c(0);
            input.b(this.d.h() + 6);
            return 0;
        } else if (((nextStartCode & -256) >> 8) != 1) {
            input.b(1);
            return 0;
        } else {
            int streamId = nextStartCode & 255;
            a payloadReader = (a) this.c.get(streamId);
            if (!this.e) {
                if (payloadReader == null) {
                    g elementaryStreamReader = null;
                    if (!this.f && streamId == 189) {
                        elementaryStreamReader = new b();
                        this.f = true;
                    } else if (!this.f && (streamId & 224) == 192) {
                        elementaryStreamReader = new l();
                        this.f = true;
                    } else if (!this.g && (streamId & 240) == 224) {
                        elementaryStreamReader = new h();
                        this.g = true;
                    }
                    if (elementaryStreamReader != null) {
                        elementaryStreamReader.a(this.h, new d(streamId, Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE));
                        payloadReader = new a(elementaryStreamReader, this.b);
                        this.c.put(streamId, payloadReader);
                    }
                }
                if ((this.f && this.g) || input.c() > 1048576) {
                    this.e = true;
                    this.h.b();
                }
            }
            input.c(this.d.a, 0, 2);
            this.d.c(0);
            int pesLength = this.d.h() + 6;
            if (payloadReader == null) {
                input.b(pesLength);
            } else {
                this.d.a(pesLength);
                input.b(this.d.a, 0, pesLength);
                this.d.c(6);
                payloadReader.a(this.d);
                this.d.b(this.d.e());
            }
            return 0;
        }
    }
}
