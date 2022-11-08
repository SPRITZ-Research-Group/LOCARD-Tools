package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.d.j;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.q;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.extractor.f;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.i;
import com.google.android.exoplayer2.extractor.l;
import com.google.android.exoplayer2.extractor.ts.t.c;
import com.google.android.exoplayer2.extractor.ts.t.d;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class TsExtractor implements f {
    public static final i a = new i() {
        public final f[] a() {
            return new f[]{new TsExtractor()};
        }
    };
    private static final long b = ((long) s.e("AC-3"));
    private static final long c = ((long) s.e("EAC3"));
    private static final long d = ((long) s.e("HEVC"));
    private final int e;
    private final List<q> f;
    private final k g;
    private final j h;
    private final SparseIntArray i;
    private final c j;
    private final SparseArray<t> k;
    private final SparseBooleanArray l;
    private h m;
    private int n;
    private boolean o;
    private t p;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    private class a implements p {
        final /* synthetic */ TsExtractor a;
        private final j b = new j(new byte[4]);

        public a(TsExtractor tsExtractor) {
            this.a = tsExtractor;
        }

        public final void a(q timestampAdjuster, h extractorOutput, d idGenerator) {
        }

        public final void a(k sectionData) {
            if (sectionData.g() == 0) {
                sectionData.d(7);
                int programCount = sectionData.b() / 4;
                for (int i = 0; i < programCount; i++) {
                    sectionData.a(this.b, 4);
                    int programNumber = this.b.c(16);
                    this.b.b(3);
                    if (programNumber == 0) {
                        this.b.b(13);
                    } else {
                        int pid = this.b.c(13);
                        this.a.k.put(pid, new q(new b(this.a, pid)));
                        this.a.n = this.a.n + 1;
                    }
                }
                if (this.a.e != 2) {
                    this.a.k.remove(0);
                }
            }
        }
    }

    private class b implements p {
        final /* synthetic */ TsExtractor a;
        private final j b = new j(new byte[5]);
        private final SparseArray<t> c = new SparseArray();
        private final SparseIntArray d = new SparseIntArray();
        private final int e;

        public b(TsExtractor tsExtractor, int pid) {
            this.a = tsExtractor;
            this.e = pid;
        }

        public final void a(q timestampAdjuster, h extractorOutput, d idGenerator) {
        }

        public final void a(k sectionData) {
            if (sectionData.g() == 2) {
                q timestampAdjuster;
                int trackId;
                t reader;
                if (this.a.e == 1 || this.a.e == 2 || this.a.n == 1) {
                    timestampAdjuster = (q) this.a.f.get(0);
                } else {
                    timestampAdjuster = new q(((q) this.a.f.get(0)).a());
                    this.a.f.add(timestampAdjuster);
                }
                sectionData.d(2);
                int programNumber = sectionData.h();
                sectionData.d(5);
                sectionData.a(this.b, 2);
                this.b.b(4);
                sectionData.d(this.b.c(12));
                if (this.a.e == 2 && this.a.p == null) {
                    this.a.p = this.a.j.a(21, new com.google.android.exoplayer2.extractor.ts.t.b(21, null, null, new byte[0]));
                    this.a.p.a(timestampAdjuster, this.a.m, new d(programNumber, 21, 8192));
                }
                this.c.clear();
                this.d.clear();
                int remainingEntriesLength = sectionData.b();
                while (remainingEntriesLength > 0) {
                    sectionData.a(this.b, 5);
                    int streamType = this.b.c(8);
                    this.b.b(3);
                    int elementaryPid = this.b.c(13);
                    this.b.b(4);
                    int esInfoLength = this.b.c(12);
                    int d = sectionData.d();
                    int i = d + esInfoLength;
                    int i2 = -1;
                    String str = null;
                    ArrayList arrayList = null;
                    while (sectionData.d() < i) {
                        int g = sectionData.g();
                        int g2 = sectionData.g() + sectionData.d();
                        if (g == 5) {
                            long l = sectionData.l();
                            if (l == TsExtractor.b) {
                                i2 = 129;
                            } else if (l == TsExtractor.c) {
                                i2 = 135;
                            } else if (l == TsExtractor.d) {
                                i2 = 36;
                            }
                        } else if (g == 106) {
                            i2 = 129;
                        } else if (g == 122) {
                            i2 = 135;
                        } else if (g == 123) {
                            i2 = 138;
                        } else if (g == 10) {
                            str = sectionData.e(3).trim();
                        } else if (g == 89) {
                            i2 = 89;
                            arrayList = new ArrayList();
                            while (sectionData.d() < g2) {
                                String trim = sectionData.e(3).trim();
                                int g3 = sectionData.g();
                                byte[] bArr = new byte[4];
                                sectionData.a(bArr, 0, 4);
                                arrayList.add(new com.google.android.exoplayer2.extractor.ts.t.a(trim, g3, bArr));
                            }
                        }
                        sectionData.d(g2 - sectionData.d());
                    }
                    sectionData.c(i);
                    com.google.android.exoplayer2.extractor.ts.t.b esInfo = new com.google.android.exoplayer2.extractor.ts.t.b(i2, str, arrayList, Arrays.copyOfRange(sectionData.a, d, i));
                    if (streamType == 6) {
                        streamType = esInfo.a;
                    }
                    remainingEntriesLength -= esInfoLength + 5;
                    if (this.a.e == 2) {
                        trackId = streamType;
                    } else {
                        trackId = elementaryPid;
                    }
                    if (!this.a.l.get(trackId)) {
                        if (this.a.e == 2 && streamType == 21) {
                            reader = this.a.p;
                        } else {
                            reader = this.a.j.a(streamType, esInfo);
                        }
                        if (this.a.e != 2 || elementaryPid < this.d.get(trackId, 8192)) {
                            this.d.put(trackId, elementaryPid);
                            this.c.put(trackId, reader);
                        }
                    }
                }
                int trackIdCount = this.d.size();
                for (int i3 = 0; i3 < trackIdCount; i3++) {
                    trackId = this.d.keyAt(i3);
                    this.a.l.put(trackId, true);
                    reader = (t) this.c.valueAt(i3);
                    if (reader != null) {
                        if (reader != this.a.p) {
                            reader.a(timestampAdjuster, this.a.m, new d(programNumber, trackId, 8192));
                        }
                        this.a.k.put(this.d.valueAt(i3), reader);
                    }
                }
                if (this.a.e != 2) {
                    this.a.k.remove(this.e);
                    this.a.n = this.a.e == 1 ? 0 : this.a.n - 1;
                    if (this.a.n == 0) {
                        this.a.m.b();
                        this.a.o = true;
                    }
                } else if (!this.a.o) {
                    this.a.m.b();
                    this.a.n = 0;
                    this.a.o = true;
                }
            }
        }
    }

    public TsExtractor() {
        this((byte) 0);
    }

    private TsExtractor(byte b) {
        this(1, 0);
    }

    public TsExtractor(int mode, int defaultTsPayloadReaderFlags) {
        this(mode, new q(0), new DefaultTsPayloadReaderFactory(defaultTsPayloadReaderFlags));
    }

    private TsExtractor(int mode, q timestampAdjuster, c payloadReaderFactory) {
        this.j = (c) com.google.android.exoplayer2.d.a.a((Object) payloadReaderFactory);
        this.e = mode;
        if (mode == 1 || mode == 2) {
            this.f = Collections.singletonList(timestampAdjuster);
        } else {
            this.f = new ArrayList();
            this.f.add(timestampAdjuster);
        }
        this.g = new k(940);
        this.h = new j(new byte[3]);
        this.l = new SparseBooleanArray();
        this.k = new SparseArray();
        this.i = new SparseIntArray();
        d();
    }

    public final boolean a(g input) throws IOException, InterruptedException {
        byte[] buffer = this.g.a;
        input.c(buffer, 0, 940);
        int j = 0;
        while (j < 188) {
            int i = 0;
            while (i != 5) {
                if (buffer[(i * 188) + j] == (byte) 71) {
                    i++;
                } else {
                    j++;
                }
            }
            input.b(j);
            return true;
        }
        return false;
    }

    public final void a(h output) {
        this.m = output;
        output.a(new com.google.android.exoplayer2.extractor.m.a(-9223372036854775807L));
    }

    public final void a(long position, long timeUs) {
        int timestampAdjustersCount = this.f.size();
        for (int i = 0; i < timestampAdjustersCount; i++) {
            ((q) this.f.get(i)).d();
        }
        this.g.a();
        this.i.clear();
        d();
    }

    public final int a(g input, l seekPosition) throws IOException, InterruptedException {
        int limit;
        byte[] data = this.g.a;
        if (940 - this.g.d() < 188) {
            int bytesLeft = this.g.b();
            if (bytesLeft > 0) {
                System.arraycopy(data, this.g.d(), data, 0, bytesLeft);
            }
            this.g.a(data, bytesLeft);
        }
        while (this.g.b() < 188) {
            limit = this.g.c();
            int read = input.a(data, limit, 940 - limit);
            if (read == -1) {
                return -1;
            }
            this.g.b(limit + read);
        }
        limit = this.g.c();
        int position = this.g.d();
        while (position < limit && data[position] != (byte) 71) {
            position++;
        }
        this.g.c(position);
        int endOfPacket = position + 188;
        if (endOfPacket > limit) {
            return 0;
        }
        this.g.d(1);
        this.g.a(this.h, 3);
        if (this.h.d()) {
            this.g.c(endOfPacket);
            return 0;
        }
        boolean payloadUnitStartIndicator = this.h.d();
        this.h.b(1);
        int pid = this.h.c(13);
        this.h.b(2);
        boolean adaptationFieldExists = this.h.d();
        boolean payloadExists = this.h.d();
        boolean discontinuityFound = false;
        int continuityCounter = this.h.c(4);
        if (this.e != 2) {
            int previousCounter = this.i.get(pid, continuityCounter - 1);
            this.i.put(pid, continuityCounter);
            if (previousCounter == continuityCounter) {
                if (payloadExists) {
                    this.g.c(endOfPacket);
                    return 0;
                }
            } else if (continuityCounter != (previousCounter + 1) % 16) {
                discontinuityFound = true;
            }
        }
        if (adaptationFieldExists) {
            this.g.d(this.g.g());
        }
        if (payloadExists) {
            t payloadReader = (t) this.k.get(pid);
            if (payloadReader != null) {
                if (discontinuityFound) {
                    payloadReader.a();
                }
                this.g.b(endOfPacket);
                payloadReader.a(this.g, payloadUnitStartIndicator);
                com.google.android.exoplayer2.d.a.b(this.g.d() <= endOfPacket);
                this.g.b(limit);
            }
        }
        this.g.c(endOfPacket);
        return 0;
    }

    private void d() {
        this.l.clear();
        this.k.clear();
        SparseArray<t> initialPayloadReaders = this.j.a();
        int initialPayloadReadersSize = initialPayloadReaders.size();
        for (int i = 0; i < initialPayloadReadersSize; i++) {
            this.k.put(initialPayloadReaders.keyAt(i), initialPayloadReaders.valueAt(i));
        }
        this.k.put(0, new q(new a(this)));
        this.p = null;
    }
}
