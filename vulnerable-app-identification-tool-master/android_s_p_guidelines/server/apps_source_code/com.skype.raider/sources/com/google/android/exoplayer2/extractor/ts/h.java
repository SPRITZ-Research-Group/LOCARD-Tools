package com.google.android.exoplayer2.extractor.ts;

import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.i;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.extractor.ts.t.d;
import java.util.Arrays;
import java.util.Collections;

public final class h implements g {
    private static final double[] c = new double[]{23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};
    private String a;
    private n b;
    private boolean d;
    private long e;
    private final boolean[] f = new boolean[4];
    private final a g = new a();
    private boolean h;
    private long i;
    private long j;
    private boolean k;
    private boolean l;
    private long m;
    private long n;

    private static final class a {
        public int a;
        public int b;
        public byte[] c = new byte[128];
        private boolean d;

        public final void a() {
            this.d = false;
            this.a = 0;
            this.b = 0;
        }

        public final boolean a(int startCodeValue, int bytesAlreadyPassed) {
            if (this.d) {
                if (this.b == 0 && startCodeValue == 181) {
                    this.b = this.a;
                } else {
                    this.a -= bytesAlreadyPassed;
                    this.d = false;
                    return true;
                }
            } else if (startCodeValue == 179) {
                this.d = true;
            }
            return false;
        }

        public final void a(byte[] newData, int offset, int limit) {
            if (this.d) {
                int readLength = limit - offset;
                if (this.c.length < this.a + readLength) {
                    this.c = Arrays.copyOf(this.c, (this.a + readLength) * 2);
                }
                System.arraycopy(newData, offset, this.c, this.a, readLength);
                this.a += readLength;
            }
        }
    }

    public final void a() {
        i.a(this.f);
        this.g.a();
        this.k = false;
        this.h = false;
        this.i = 0;
    }

    public final void a(com.google.android.exoplayer2.extractor.h extractorOutput, d idGenerator) {
        idGenerator.a();
        this.a = idGenerator.c();
        this.b = extractorOutput.a(idGenerator.b());
    }

    public final void a(long pesTimeUs, boolean dataAlignmentIndicator) {
        this.k = pesTimeUs != -9223372036854775807L;
        if (this.k) {
            this.j = pesTimeUs;
        }
    }

    public final void a(k data) {
        int offset = data.d();
        int limit = data.c();
        byte[] dataArray = data.a;
        this.i += (long) data.b();
        this.b.a(data, data.b());
        int searchOffset = offset;
        while (true) {
            int startCodeOffset = i.a(dataArray, searchOffset, limit, this.f);
            if (startCodeOffset != limit) {
                long j;
                int startCodeValue = data.a[startCodeOffset + 3] & 255;
                if (!this.d) {
                    int lengthToStartCode = startCodeOffset - offset;
                    if (lengthToStartCode > 0) {
                        this.g.a(dataArray, offset, startCodeOffset);
                    }
                    if (this.g.a(startCodeValue, lengthToStartCode < 0 ? -lengthToStartCode : 0)) {
                        a aVar = this.g;
                        String str = this.a;
                        Object copyOf = Arrays.copyOf(aVar.c, aVar.a);
                        int i = copyOf[5] & 255;
                        int i2 = (i >> 4) | ((copyOf[4] & 255) << 4);
                        i = ((i & 15) << 8) | (copyOf[6] & 255);
                        float f = 1.0f;
                        switch ((copyOf[7] & 240) >> 4) {
                            case 2:
                                f = ((float) (i * 4)) / ((float) (i2 * 3));
                                break;
                            case 3:
                                f = ((float) (i * 16)) / ((float) (i2 * 9));
                                break;
                            case 4:
                                f = ((float) (i * 121)) / ((float) (i2 * 100));
                                break;
                        }
                        Format a = Format.a(str, "video/mpeg2", i2, i, Collections.singletonList(copyOf), f);
                        j = 0;
                        i = (copyOf[7] & 15) - 1;
                        if (i >= 0 && i < c.length) {
                            double d = c[i];
                            i = aVar.b;
                            int i3 = (copyOf[i + 9] & 96) >> 5;
                            i = copyOf[i + 9] & 31;
                            if (i3 != i) {
                                d *= (((double) i3) + 1.0d) / ((double) (i + 1));
                            }
                            j = (long) (1000000.0d / d);
                        }
                        Pair<Format, Long> result = Pair.create(a, Long.valueOf(j));
                        this.b.a((Format) result.first);
                        this.e = ((Long) result.second).longValue();
                        this.d = true;
                    }
                }
                if (this.d && (startCodeValue == 184 || startCodeValue == 0)) {
                    int bytesWrittenPastStartCode = limit - startCodeOffset;
                    if (this.h) {
                        this.b.a(this.n, this.l ? 1 : 0, ((int) (this.i - this.m)) - bytesWrittenPastStartCode, bytesWrittenPastStartCode, null);
                        this.l = false;
                    }
                    if (startCodeValue == 184) {
                        this.h = false;
                        this.l = true;
                    } else {
                        if (this.k) {
                            j = this.j;
                        } else {
                            j = this.n + this.e;
                        }
                        this.n = j;
                        this.m = this.i - ((long) bytesWrittenPastStartCode);
                        this.k = false;
                        this.h = true;
                    }
                }
                offset = startCodeOffset;
                searchOffset = startCodeOffset + 3;
            } else if (!this.d) {
                this.g.a(dataArray, offset, limit);
                return;
            } else {
                return;
            }
        }
    }

    public final void b() {
    }
}
