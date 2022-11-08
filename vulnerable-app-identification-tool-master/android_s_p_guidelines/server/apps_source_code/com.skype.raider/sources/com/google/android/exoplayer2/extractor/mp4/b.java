package com.google.android.exoplayer2.extractor.mp4;

import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.skype.android.video.hw.utils.CodecUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class b {
    private static final int a = s.e("vide");
    private static final int b = s.e("soun");
    private static final int c = s.e("text");
    private static final int d = s.e("sbtl");
    private static final int e = s.e("subt");
    private static final int f = s.e("clcp");
    private static final int g = s.e("cenc");
    private static final int h = s.e("meta");

    private static final class a {
        public final int a;
        public int b;
        public int c;
        public long d;
        private final boolean e;
        private final k f;
        private final k g;
        private int h;
        private int i;

        public a(k stsc, k chunkOffsets, boolean chunkOffsetsAreLongs) {
            boolean z = true;
            this.g = stsc;
            this.f = chunkOffsets;
            this.e = chunkOffsetsAreLongs;
            chunkOffsets.c(12);
            this.a = chunkOffsets.t();
            stsc.c(12);
            this.i = stsc.t();
            if (stsc.n() != 1) {
                z = false;
            }
            com.google.android.exoplayer2.d.a.b(z, "first_chunk must be 1");
            this.b = -1;
        }

        public final boolean a() {
            int i = this.b + 1;
            this.b = i;
            if (i == this.a) {
                return false;
            }
            long v;
            if (this.e) {
                v = this.f.v();
            } else {
                v = this.f.l();
            }
            this.d = v;
            if (this.b == this.h) {
                this.c = this.g.t();
                this.g.d(4);
                i = this.i - 1;
                this.i = i;
                this.h = i > 0 ? this.g.t() - 1 : -1;
            }
            return true;
        }
    }

    private interface b {
        int a();

        int b();

        boolean c();
    }

    private static final class c {
        public final i[] a;
        public Format b;
        public int c;
        public int d = 0;

        public c(int numberOfEntries) {
            this.a = new i[numberOfEntries];
        }
    }

    static final class d implements b {
        private final int a = this.c.t();
        private final int b = this.c.t();
        private final k c;

        public d(b stszAtom) {
            this.c = stszAtom.aQ;
            this.c.c(12);
        }

        public final int a() {
            return this.b;
        }

        public final int b() {
            return this.a == 0 ? this.c.t() : this.a;
        }

        public final boolean c() {
            return this.a != 0;
        }
    }

    static final class e implements b {
        private final k a;
        private final int b = this.a.t();
        private final int c = (this.a.t() & 255);
        private int d;
        private int e;

        public e(b stz2Atom) {
            this.a = stz2Atom.aQ;
            this.a.c(12);
        }

        public final int a() {
            return this.b;
        }

        public final int b() {
            if (this.c == 8) {
                return this.a.g();
            }
            if (this.c == 16) {
                return this.a.h();
            }
            int i = this.d;
            this.d = i + 1;
            if (i % 2 != 0) {
                return this.e & 15;
            }
            this.e = this.a.g();
            return (this.e & 240) >> 4;
        }

        public final boolean c() {
            return false;
        }
    }

    private static final class f {
        private final int a;
        private final long b;
        private final int c;

        public f(int id, long duration, int rotationDegrees) {
            this.a = id;
            this.b = duration;
            this.c = rotationDegrees;
        }
    }

    public static Track a(a trak, b mvhd, long duration, DrmInitData drmInitData, boolean isQuickTime) throws com.google.android.exoplayer2.k {
        int trackType;
        a mdia = trak.e(a.E);
        k kVar = mdia.d(a.S).aQ;
        kVar.c(16);
        int n = kVar.n();
        if (n == b) {
            trackType = 1;
        } else if (n == a) {
            trackType = 2;
        } else if (n == c || n == d || n == e || n == f) {
            trackType = 3;
        } else if (n == h) {
            trackType = 4;
        } else {
            trackType = -1;
        }
        if (trackType == -1) {
            return null;
        }
        int i;
        long j;
        long durationUs;
        k kVar2 = trak.d(a.O).aQ;
        kVar2.c(8);
        int a = a.a(kVar2.n());
        kVar2.d(a == 0 ? 8 : 16);
        int n2 = kVar2.n();
        kVar2.d(4);
        Object obj = 1;
        int d = kVar2.d();
        n = a == 0 ? 4 : 8;
        for (i = 0; i < n; i++) {
            if (kVar2.a[d + i] != (byte) -1) {
                obj = null;
                break;
            }
        }
        if (obj != null) {
            kVar2.d(n);
            j = -9223372036854775807L;
        } else {
            j = a == 0 ? kVar2.l() : kVar2.v();
            if (j == 0) {
                j = -9223372036854775807L;
            }
        }
        kVar2.d(16);
        i = kVar2.n();
        a = kVar2.n();
        kVar2.d(4);
        d = kVar2.n();
        int n3 = kVar2.n();
        if (i == 0 && a == 65536 && d == -65536 && n3 == 0) {
            i = 90;
        } else if (i == 0 && a == -65536 && d == 65536 && n3 == 0) {
            i = 270;
        } else if (i == -65536 && a == 0 && d == 0 && n3 == -65536) {
            i = 180;
        } else {
            i = 0;
        }
        f fVar = new f(n2, j, i);
        if (duration == -9223372036854775807L) {
            duration = fVar.b;
        }
        k kVar3 = mvhd.aQ;
        kVar3.c(8);
        kVar3.d(a.a(kVar3.n()) == 0 ? 8 : 16);
        long movieTimescale = kVar3.l();
        if (duration == -9223372036854775807L) {
            durationUs = -9223372036854775807L;
        } else {
            durationUs = s.a(duration, 1000000, movieTimescale);
        }
        a stbl = mdia.e(a.F).e(a.G);
        kVar3 = mdia.d(a.R).aQ;
        kVar3.c(8);
        i = a.a(kVar3.n());
        kVar3.d(i == 0 ? 8 : 16);
        long l = kVar3.l();
        kVar3.d(i == 0 ? 4 : 8);
        n = kVar3.h();
        Pair<Long, String> mdhdData = Pair.create(Long.valueOf(l), ((char) (((n >> 10) & 31) + 96)) + ((char) (((n >> 5) & 31) + 96)) + ((char) ((n & 31) + 96)));
        c stsdData = a(stbl.d(a.T).aQ, fVar.a, fVar.c, (String) mdhdData.second, drmInitData, isQuickTime);
        Pair<long[], long[]> edtsData = a(trak.e(a.P));
        if (stsdData.b == null) {
            return null;
        }
        return new Track(fVar.a, trackType, ((Long) mdhdData.first).longValue(), movieTimescale, durationUs, stsdData.b, stsdData.d, stsdData.a, stsdData.c, (long[]) edtsData.first, (long[]) edtsData.second);
    }

    public static com.google.android.exoplayer2.extractor.mp4.k a(com.google.android.exoplayer2.extractor.mp4.Track r88, com.google.android.exoplayer2.extractor.mp4.a.a r89, com.google.android.exoplayer2.extractor.j r90) throws com.google.android.exoplayer2.k {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r73_0 'sampleSizeBox' com.google.android.exoplayer2.extractor.mp4.b$b) in PHI: PHI: (r73_1 'sampleSizeBox' com.google.android.exoplayer2.extractor.mp4.b$b) = (r73_0 'sampleSizeBox' com.google.android.exoplayer2.extractor.mp4.b$b), (r73_2 'sampleSizeBox' com.google.android.exoplayer2.extractor.mp4.b$b) binds: {(r73_0 'sampleSizeBox' com.google.android.exoplayer2.extractor.mp4.b$b)=B:2:0x000a, (r73_2 'sampleSizeBox' com.google.android.exoplayer2.extractor.mp4.b$b)=B:10:0x003e}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:222)
	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r4 = com.google.android.exoplayer2.extractor.mp4.a.aq;
        r0 = r89;
        r78 = r0.d(r4);
        if (r78 == 0) goto L_0x002c;
    L_0x000a:
        r73 = new com.google.android.exoplayer2.extractor.mp4.b$d;
        r0 = r73;
        r1 = r78;
        r0.<init>(r1);
    L_0x0013:
        r71 = r73.a();
        if (r71 != 0) goto L_0x0048;
    L_0x0019:
        r4 = new com.google.android.exoplayer2.extractor.mp4.k;
        r12 = 0;
        r5 = new long[r12];
        r12 = 0;
        r6 = new int[r12];
        r7 = 0;
        r12 = 0;
        r8 = new long[r12];
        r12 = 0;
        r9 = new int[r12];
        r4.<init>(r5, r6, r7, r8, r9);
    L_0x002b:
        return r4;
    L_0x002c:
        r4 = com.google.android.exoplayer2.extractor.mp4.a.ar;
        r0 = r89;
        r80 = r0.d(r4);
        if (r80 != 0) goto L_0x003e;
    L_0x0036:
        r4 = new com.google.android.exoplayer2.k;
        r12 = "Track has no sample table size information";
        r4.<init>(r12);
        throw r4;
    L_0x003e:
        r73 = new com.google.android.exoplayer2.extractor.mp4.b$e;
        r0 = r73;
        r1 = r80;
        r0.<init>(r1);
        goto L_0x0013;
    L_0x0048:
        r20 = 0;
        r4 = com.google.android.exoplayer2.extractor.mp4.a.as;
        r0 = r89;
        r21 = r0.d(r4);
        if (r21 != 0) goto L_0x005e;
    L_0x0054:
        r20 = 1;
        r4 = com.google.android.exoplayer2.extractor.mp4.a.at;
        r0 = r89;
        r21 = r0.d(r4);
    L_0x005e:
        r0 = r21;
        r0 = r0.aQ;
        r19 = r0;
        r4 = com.google.android.exoplayer2.extractor.mp4.a.ap;
        r0 = r89;
        r4 = r0.d(r4);
        r0 = r4.aQ;
        r75 = r0;
        r4 = com.google.android.exoplayer2.extractor.mp4.a.am;
        r0 = r89;
        r4 = r0.d(r4);
        r0 = r4.aQ;
        r79 = r0;
        r4 = com.google.android.exoplayer2.extractor.mp4.a.an;
        r0 = r89;
        r77 = r0.d(r4);
        if (r77 == 0) goto L_0x013d;
    L_0x0086:
        r0 = r77;
        r0 = r0.aQ;
        r76 = r0;
    L_0x008c:
        r4 = com.google.android.exoplayer2.extractor.mp4.a.ao;
        r0 = r89;
        r27 = r0.d(r4);
        if (r27 == 0) goto L_0x0141;
    L_0x0096:
        r0 = r27;
        r0 = r0.aQ;
        r26 = r0;
    L_0x009c:
        r18 = new com.google.android.exoplayer2.extractor.mp4.b$a;
        r0 = r18;
        r1 = r75;
        r2 = r19;
        r3 = r20;
        r0.<init>(r1, r2, r3);
        r4 = 12;
        r0 = r79;
        r0.c(r4);
        r4 = r79.t();
        r69 = r4 + -1;
        r65 = r79.t();
        r81 = r79.t();
        r66 = 0;
        r70 = 0;
        r84 = 0;
        if (r26 == 0) goto L_0x00d1;
    L_0x00c6:
        r4 = 12;
        r0 = r26;
        r0.c(r4);
        r70 = r26.t();
    L_0x00d1:
        r54 = -1;
        r68 = 0;
        if (r76 == 0) goto L_0x00ea;
    L_0x00d7:
        r4 = 12;
        r0 = r76;
        r0.c(r4);
        r68 = r76.t();
        if (r68 <= 0) goto L_0x0145;
    L_0x00e4:
        r4 = r76.t();
        r54 = r4 + -1;
    L_0x00ea:
        r4 = r73.c();
        if (r4 == 0) goto L_0x0148;
    L_0x00f0:
        r4 = "audio/raw";
        r0 = r88;
        r12 = r0.f;
        r12 = r12.f;
        r4 = r4.equals(r12);
        if (r4 == 0) goto L_0x0148;
    L_0x00fe:
        if (r69 != 0) goto L_0x0148;
    L_0x0100:
        if (r70 != 0) goto L_0x0148;
    L_0x0102:
        if (r68 != 0) goto L_0x0148;
    L_0x0104:
        r47 = 1;
    L_0x0106:
        r7 = 0;
        r86 = 0;
        if (r47 != 0) goto L_0x0229;
    L_0x010b:
        r0 = r71;
        r5 = new long[r0];
        r0 = r71;
        r6 = new int[r0];
        r0 = r71;
        r8 = new long[r0];
        r0 = r71;
        r9 = new int[r0];
        r56 = 0;
        r67 = 0;
        r46 = 0;
    L_0x0121:
        r0 = r46;
        r1 = r71;
        if (r0 >= r1) goto L_0x01ad;
    L_0x0127:
        if (r67 != 0) goto L_0x014b;
    L_0x0129:
        r4 = r18.a();
        com.google.android.exoplayer2.d.a.b(r4);
        r0 = r18;
        r0 = r0.d;
        r56 = r0;
        r0 = r18;
        r0 = r0.c;
        r67 = r0;
        goto L_0x0127;
    L_0x013d:
        r76 = 0;
        goto L_0x008c;
    L_0x0141:
        r26 = 0;
        goto L_0x009c;
    L_0x0145:
        r76 = 0;
        goto L_0x00ea;
    L_0x0148:
        r47 = 0;
        goto L_0x0106;
    L_0x014b:
        if (r26 == 0) goto L_0x015e;
    L_0x014d:
        if (r66 != 0) goto L_0x015c;
    L_0x014f:
        if (r70 <= 0) goto L_0x015c;
    L_0x0151:
        r66 = r26.t();
        r84 = r26.n();
        r70 = r70 + -1;
        goto L_0x014d;
    L_0x015c:
        r66 = r66 + -1;
    L_0x015e:
        r5[r46] = r56;
        r4 = r73.b();
        r6[r46] = r4;
        r4 = r6[r46];
        if (r4 <= r7) goto L_0x016c;
    L_0x016a:
        r7 = r6[r46];
    L_0x016c:
        r0 = r84;
        r12 = (long) r0;
        r12 = r12 + r86;
        r8[r46] = r12;
        if (r76 != 0) goto L_0x01ab;
    L_0x0175:
        r4 = 1;
    L_0x0176:
        r9[r46] = r4;
        r0 = r46;
        r1 = r54;
        if (r0 != r1) goto L_0x018b;
    L_0x017e:
        r4 = 1;
        r9[r46] = r4;
        r68 = r68 + -1;
        if (r68 <= 0) goto L_0x018b;
    L_0x0185:
        r4 = r76.t();
        r54 = r4 + -1;
    L_0x018b:
        r0 = r81;
        r12 = (long) r0;
        r86 = r86 + r12;
        r65 = r65 + -1;
        if (r65 != 0) goto L_0x01a0;
    L_0x0194:
        if (r69 <= 0) goto L_0x01a0;
    L_0x0196:
        r65 = r79.t();
        r81 = r79.t();
        r69 = r69 + -1;
    L_0x01a0:
        r4 = r6[r46];
        r12 = (long) r4;
        r56 = r56 + r12;
        r67 = r67 + -1;
        r46 = r46 + 1;
        goto L_0x0121;
    L_0x01ab:
        r4 = 0;
        goto L_0x0176;
    L_0x01ad:
        if (r66 != 0) goto L_0x01c5;
    L_0x01af:
        r4 = 1;
    L_0x01b0:
        com.google.android.exoplayer2.d.a.a(r4);
    L_0x01b3:
        if (r70 <= 0) goto L_0x01c9;
    L_0x01b5:
        r4 = r26.t();
        if (r4 != 0) goto L_0x01c7;
    L_0x01bb:
        r4 = 1;
    L_0x01bc:
        com.google.android.exoplayer2.d.a.a(r4);
        r26.n();
        r70 = r70 + -1;
        goto L_0x01b3;
    L_0x01c5:
        r4 = 0;
        goto L_0x01b0;
    L_0x01c7:
        r4 = 0;
        goto L_0x01bc;
    L_0x01c9:
        if (r68 != 0) goto L_0x01d1;
    L_0x01cb:
        if (r65 != 0) goto L_0x01d1;
    L_0x01cd:
        if (r67 != 0) goto L_0x01d1;
    L_0x01cf:
        if (r69 == 0) goto L_0x020f;
    L_0x01d1:
        r4 = new java.lang.StringBuilder;
        r12 = "Inconsistent stbl box for track ";
        r4.<init>(r12);
        r0 = r88;
        r12 = r0.a;
        r4 = r4.append(r12);
        r12 = ": remainingSynchronizationSamples ";
        r4 = r4.append(r12);
        r0 = r68;
        r4 = r4.append(r0);
        r12 = ", remainingSamplesAtTimestampDelta ";
        r4 = r4.append(r12);
        r0 = r65;
        r4 = r4.append(r0);
        r12 = ", remainingSamplesInChunk ";
        r4 = r4.append(r12);
        r0 = r67;
        r4 = r4.append(r0);
        r12 = ", remainingTimestampDeltaChanges ";
        r4 = r4.append(r12);
        r0 = r69;
        r4.append(r0);
    L_0x020f:
        r0 = r88;
        r4 = r0.i;
        if (r4 == 0) goto L_0x021b;
    L_0x0215:
        r4 = r90.a();
        if (r4 == 0) goto L_0x0278;
    L_0x021b:
        r0 = r88;
        r12 = r0.c;
        com.google.android.exoplayer2.d.s.a(r8, r12);
        r4 = new com.google.android.exoplayer2.extractor.mp4.k;
        r4.<init>(r5, r6, r7, r8, r9);
        goto L_0x002b;
    L_0x0229:
        r0 = r18;
        r4 = r0.a;
        r0 = new long[r4];
        r22 = r0;
        r0 = r18;
        r4 = r0.a;
        r0 = new int[r4];
        r23 = r0;
    L_0x0239:
        r4 = r18.a();
        if (r4 == 0) goto L_0x0254;
    L_0x023f:
        r0 = r18;
        r4 = r0.b;
        r0 = r18;
        r12 = r0.d;
        r22[r4] = r12;
        r0 = r18;
        r4 = r0.b;
        r0 = r18;
        r12 = r0.c;
        r23[r4] = r12;
        goto L_0x0239;
    L_0x0254:
        r4 = r73.b();
        r0 = r81;
        r12 = (long) r0;
        r0 = r22;
        r1 = r23;
        r64 = com.google.android.exoplayer2.extractor.mp4.d.a(r4, r0, r1, r12);
        r0 = r64;
        r5 = r0.a;
        r0 = r64;
        r6 = r0.b;
        r0 = r64;
        r7 = r0.c;
        r0 = r64;
        r8 = r0.d;
        r0 = r64;
        r9 = r0.e;
        goto L_0x020f;
    L_0x0278:
        r0 = r88;
        r4 = r0.i;
        r4 = r4.length;
        r12 = 1;
        if (r4 != r12) goto L_0x0321;
    L_0x0280:
        r0 = r88;
        r4 = r0.b;
        r12 = 1;
        if (r4 != r12) goto L_0x0321;
    L_0x0287:
        r4 = r8.length;
        r12 = 2;
        if (r4 < r12) goto L_0x0321;
    L_0x028b:
        r0 = r88;
        r4 = r0.j;
        r12 = 0;
        r30 = r4[r12];
        r0 = r88;
        r4 = r0.i;
        r12 = 0;
        r10 = r4[r12];
        r0 = r88;
        r12 = r0.c;
        r0 = r88;
        r14 = r0.d;
        r12 = com.google.android.exoplayer2.d.s.a(r10, r12, r14);
        r28 = r30 + r12;
        r50 = r86;
        r4 = 0;
        r12 = r8[r4];
        r4 = (r12 > r30 ? 1 : (r12 == r30 ? 0 : -1));
        if (r4 > 0) goto L_0x0321;
    L_0x02b0:
        r4 = 1;
        r12 = r8[r4];
        r4 = (r30 > r12 ? 1 : (r30 == r12 ? 0 : -1));
        if (r4 >= 0) goto L_0x0321;
    L_0x02b7:
        r4 = r8.length;
        r4 = r4 + -1;
        r12 = r8[r4];
        r4 = (r12 > r28 ? 1 : (r12 == r28 ? 0 : -1));
        if (r4 >= 0) goto L_0x0321;
    L_0x02c0:
        r4 = (r28 > r50 ? 1 : (r28 == r50 ? 0 : -1));
        if (r4 > 0) goto L_0x0321;
    L_0x02c4:
        r58 = r50 - r28;
        r4 = 0;
        r12 = r8[r4];
        r10 = r30 - r12;
        r0 = r88;
        r4 = r0.f;
        r4 = r4.s;
        r12 = (long) r4;
        r0 = r88;
        r14 = r0.c;
        r38 = com.google.android.exoplayer2.d.s.a(r10, r12, r14);
        r0 = r88;
        r4 = r0.f;
        r4 = r4.s;
        r12 = (long) r4;
        r0 = r88;
        r14 = r0.c;
        r10 = r58;
        r40 = com.google.android.exoplayer2.d.s.a(r10, r12, r14);
        r12 = 0;
        r4 = (r38 > r12 ? 1 : (r38 == r12 ? 0 : -1));
        if (r4 != 0) goto L_0x02f7;
    L_0x02f1:
        r12 = 0;
        r4 = (r40 > r12 ? 1 : (r40 == r12 ? 0 : -1));
        if (r4 == 0) goto L_0x0321;
    L_0x02f7:
        r12 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r4 = (r38 > r12 ? 1 : (r38 == r12 ? 0 : -1));
        if (r4 > 0) goto L_0x0321;
    L_0x02fe:
        r12 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r4 = (r40 > r12 ? 1 : (r40 == r12 ? 0 : -1));
        if (r4 > 0) goto L_0x0321;
    L_0x0305:
        r0 = r38;
        r4 = (int) r0;
        r0 = r90;
        r0.b = r4;
        r0 = r40;
        r4 = (int) r0;
        r0 = r90;
        r0.c = r4;
        r0 = r88;
        r12 = r0.c;
        com.google.android.exoplayer2.d.s.a(r8, r12);
        r4 = new com.google.android.exoplayer2.extractor.mp4.k;
        r4.<init>(r5, r6, r7, r8, r9);
        goto L_0x002b;
    L_0x0321:
        r0 = r88;
        r4 = r0.i;
        r4 = r4.length;
        r12 = 1;
        if (r4 != r12) goto L_0x035f;
    L_0x0329:
        r0 = r88;
        r4 = r0.i;
        r12 = 0;
        r12 = r4[r12];
        r14 = 0;
        r4 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
        if (r4 != 0) goto L_0x035f;
    L_0x0336:
        r46 = 0;
    L_0x0338:
        r4 = r8.length;
        r0 = r46;
        if (r0 >= r4) goto L_0x0358;
    L_0x033d:
        r12 = r8[r46];
        r0 = r88;
        r4 = r0.j;
        r14 = 0;
        r14 = r4[r14];
        r10 = r12 - r14;
        r12 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r0 = r88;
        r14 = r0.c;
        r12 = com.google.android.exoplayer2.d.s.a(r10, r12, r14);
        r8[r46] = r12;
        r46 = r46 + 1;
        goto L_0x0338;
    L_0x0358:
        r4 = new com.google.android.exoplayer2.extractor.mp4.k;
        r4.<init>(r5, r6, r7, r8, r9);
        goto L_0x002b;
    L_0x035f:
        r0 = r88;
        r4 = r0.b;
        r12 = 1;
        if (r4 != r12) goto L_0x03ba;
    L_0x0366:
        r55 = 1;
    L_0x0368:
        r35 = 0;
        r49 = 0;
        r24 = 0;
        r46 = 0;
    L_0x0370:
        r0 = r88;
        r4 = r0.i;
        r4 = r4.length;
        r0 = r46;
        if (r0 >= r4) goto L_0x03bf;
    L_0x0379:
        r0 = r88;
        r4 = r0.j;
        r52 = r4[r46];
        r12 = -1;
        r4 = (r52 > r12 ? 1 : (r52 == r12 ? 0 : -1));
        if (r4 == 0) goto L_0x03b7;
    L_0x0385:
        r0 = r88;
        r4 = r0.i;
        r10 = r4[r46];
        r0 = r88;
        r12 = r0.c;
        r0 = r88;
        r14 = r0.d;
        r10 = com.google.android.exoplayer2.d.s.a(r10, r12, r14);
        r4 = 1;
        r12 = 1;
        r0 = r52;
        r74 = com.google.android.exoplayer2.d.s.a(r8, r0, r4, r12);
        r12 = r52 + r10;
        r4 = 0;
        r0 = r55;
        r42 = com.google.android.exoplayer2.d.s.a(r8, r12, r0, r4);
        r4 = r42 - r74;
        r35 = r35 + r4;
        r0 = r49;
        r1 = r74;
        if (r0 == r1) goto L_0x03bd;
    L_0x03b2:
        r4 = 1;
    L_0x03b3:
        r24 = r24 | r4;
        r49 = r42;
    L_0x03b7:
        r46 = r46 + 1;
        goto L_0x0370;
    L_0x03ba:
        r55 = 0;
        goto L_0x0368;
    L_0x03bd:
        r4 = 0;
        goto L_0x03b3;
    L_0x03bf:
        r0 = r35;
        r1 = r71;
        if (r0 == r1) goto L_0x048a;
    L_0x03c5:
        r4 = 1;
    L_0x03c6:
        r24 = r24 | r4;
        if (r24 == 0) goto L_0x048d;
    L_0x03ca:
        r0 = r35;
        r0 = new long[r0];
        r34 = r0;
    L_0x03d0:
        if (r24 == 0) goto L_0x0491;
    L_0x03d2:
        r0 = r35;
        r0 = new int[r0];
        r36 = r0;
    L_0x03d8:
        if (r24 == 0) goto L_0x0495;
    L_0x03da:
        r33 = 0;
    L_0x03dc:
        if (r24 == 0) goto L_0x0499;
    L_0x03de:
        r0 = r35;
        r0 = new int[r0];
        r32 = r0;
    L_0x03e4:
        r0 = r35;
        r0 = new long[r0];
        r37 = r0;
        r60 = 0;
        r72 = 0;
        r46 = 0;
    L_0x03f0:
        r0 = r88;
        r4 = r0.i;
        r4 = r4.length;
        r0 = r46;
        if (r0 >= r4) goto L_0x04a3;
    L_0x03f9:
        r0 = r88;
        r4 = r0.j;
        r52 = r4[r46];
        r0 = r88;
        r4 = r0.i;
        r10 = r4[r46];
        r12 = -1;
        r4 = (r52 > r12 ? 1 : (r52 == r12 ? 0 : -1));
        if (r4 == 0) goto L_0x049d;
    L_0x040b:
        r0 = r88;
        r12 = r0.c;
        r0 = r88;
        r14 = r0.d;
        r12 = com.google.android.exoplayer2.d.s.a(r10, r12, r14);
        r44 = r52 + r12;
        r4 = 1;
        r12 = 1;
        r0 = r52;
        r74 = com.google.android.exoplayer2.d.s.a(r8, r0, r4, r12);
        r4 = 0;
        r0 = r44;
        r2 = r55;
        r42 = com.google.android.exoplayer2.d.s.a(r8, r0, r2, r4);
        if (r24 == 0) goto L_0x044f;
    L_0x042c:
        r25 = r42 - r74;
        r0 = r74;
        r1 = r34;
        r2 = r72;
        r3 = r25;
        java.lang.System.arraycopy(r5, r0, r1, r2, r3);
        r0 = r74;
        r1 = r36;
        r2 = r72;
        r3 = r25;
        java.lang.System.arraycopy(r6, r0, r1, r2, r3);
        r0 = r74;
        r1 = r32;
        r2 = r72;
        r3 = r25;
        java.lang.System.arraycopy(r9, r0, r1, r2, r3);
    L_0x044f:
        r48 = r74;
    L_0x0451:
        r0 = r48;
        r1 = r42;
        if (r0 >= r1) goto L_0x049d;
    L_0x0457:
        r14 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r0 = r88;
        r0 = r0.d;
        r16 = r0;
        r12 = r60;
        r62 = com.google.android.exoplayer2.d.s.a(r12, r14, r16);
        r12 = r8[r48];
        r12 = r12 - r52;
        r14 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r0 = r88;
        r0 = r0.c;
        r16 = r0;
        r82 = com.google.android.exoplayer2.d.s.a(r12, r14, r16);
        r12 = r62 + r82;
        r37[r72] = r12;
        if (r24 == 0) goto L_0x0485;
    L_0x047d:
        r4 = r36[r72];
        r0 = r33;
        if (r4 <= r0) goto L_0x0485;
    L_0x0483:
        r33 = r6[r48];
    L_0x0485:
        r72 = r72 + 1;
        r48 = r48 + 1;
        goto L_0x0451;
    L_0x048a:
        r4 = 0;
        goto L_0x03c6;
    L_0x048d:
        r34 = r5;
        goto L_0x03d0;
    L_0x0491:
        r36 = r6;
        goto L_0x03d8;
    L_0x0495:
        r33 = r7;
        goto L_0x03dc;
    L_0x0499:
        r32 = r9;
        goto L_0x03e4;
    L_0x049d:
        r60 = r60 + r10;
        r46 = r46 + 1;
        goto L_0x03f0;
    L_0x04a3:
        r43 = 0;
        r46 = 0;
    L_0x04a7:
        r0 = r32;
        r4 = r0.length;
        r0 = r46;
        if (r0 >= r4) goto L_0x04be;
    L_0x04ae:
        if (r43 != 0) goto L_0x04be;
    L_0x04b0:
        r4 = r32[r46];
        r4 = r4 & 1;
        if (r4 == 0) goto L_0x04bc;
    L_0x04b6:
        r4 = 1;
    L_0x04b7:
        r43 = r43 | r4;
        r46 = r46 + 1;
        goto L_0x04a7;
    L_0x04bc:
        r4 = 0;
        goto L_0x04b7;
    L_0x04be:
        if (r43 != 0) goto L_0x04c8;
    L_0x04c0:
        r4 = new com.google.android.exoplayer2.k;
        r12 = "The edited sample sequence does not contain a sync sample.";
        r4.<init>(r12);
        throw r4;
    L_0x04c8:
        r12 = new com.google.android.exoplayer2.extractor.mp4.k;
        r13 = r34;
        r14 = r36;
        r15 = r33;
        r16 = r37;
        r17 = r32;
        r12.<init>(r13, r14, r15, r16, r17);
        r4 = r12;
        goto L_0x002b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.b.a(com.google.android.exoplayer2.extractor.mp4.Track, com.google.android.exoplayer2.extractor.mp4.a$a, com.google.android.exoplayer2.extractor.j):com.google.android.exoplayer2.extractor.mp4.k");
    }

    public static Metadata a(b udtaAtom, boolean isQuickTime) {
        if (isQuickTime) {
            return null;
        }
        k udtaData = udtaAtom.aQ;
        udtaData.c(8);
        while (udtaData.b() >= 8) {
            int atomPosition = udtaData.d();
            int atomSize = udtaData.n();
            if (udtaData.n() == a.aB) {
                udtaData.c(atomPosition);
                int i = atomPosition + atomSize;
                udtaData.d(12);
                while (udtaData.d() < i) {
                    int d = udtaData.d();
                    int n = udtaData.n();
                    if (udtaData.n() == a.aC) {
                        udtaData.c(d);
                        i = d + n;
                        udtaData.d(8);
                        List arrayList = new ArrayList();
                        while (udtaData.d() < i) {
                            Entry a = e.a(udtaData);
                            if (a != null) {
                                arrayList.add(a);
                            }
                        }
                        if (arrayList.isEmpty()) {
                            return null;
                        }
                        return new Metadata(arrayList);
                    }
                    udtaData.d(n - 8);
                }
                return null;
            }
            udtaData.d(atomSize - 8);
        }
        return null;
    }

    private static c a(k stsd, int trackId, int rotationDegrees, String language, DrmInitData drmInitData, boolean isQuickTime) throws com.google.android.exoplayer2.k {
        stsd.c(12);
        int numberOfEntries = stsd.n();
        c cVar = new c(numberOfEntries);
        for (int i = 0; i < numberOfEntries; i++) {
            int childStartPosition = stsd.d();
            int childAtomSize = stsd.n();
            com.google.android.exoplayer2.d.a.a(childAtomSize > 0, (Object) "childAtomSize should be positive");
            int childAtomType = stsd.n();
            int h;
            int h2;
            int i2;
            int d;
            String str;
            int i3;
            int d2;
            int n;
            String str2;
            Object obj;
            if (childAtomType == a.b || childAtomType == a.c || childAtomType == a.Z || childAtomType == a.al || childAtomType == a.d || childAtomType == a.e || childAtomType == a.f || childAtomType == a.aK || childAtomType == a.aL) {
                stsd.c((childStartPosition + 8) + 8);
                stsd.d(16);
                h = stsd.h();
                h2 = stsd.h();
                i2 = 0;
                float f = 1.0f;
                stsd.d(50);
                d = stsd.d();
                if (childAtomType == a.Z) {
                    childAtomType = a(stsd, childStartPosition, childAtomSize, cVar, i);
                    stsd.c(d);
                }
                List list = null;
                str = null;
                byte[] bArr = null;
                int i4 = -1;
                while (true) {
                    i3 = d;
                    if (i3 - childStartPosition < childAtomSize) {
                        stsd.c(i3);
                        d2 = stsd.d();
                        n = stsd.n();
                        if (n != 0 || stsd.d() - childStartPosition != childAtomSize) {
                            com.google.android.exoplayer2.d.a.a(n > 0, (Object) "childAtomSize should be positive");
                            d = stsd.n();
                            if (d == a.H) {
                                com.google.android.exoplayer2.d.a.b(str == null);
                                str = CodecUtils.MEDIA_TYPE;
                                stsd.c(d2 + 8);
                                com.google.android.exoplayer2.video.a a = com.google.android.exoplayer2.video.a.a(stsd);
                                list = a.a;
                                cVar.c = a.b;
                                if (i2 == 0) {
                                    f = a.e;
                                }
                                d = i2;
                            } else if (d == a.I) {
                                com.google.android.exoplayer2.d.a.b(str == null);
                                str = "video/hevc";
                                stsd.c(d2 + 8);
                                com.google.android.exoplayer2.video.b a2 = com.google.android.exoplayer2.video.b.a(stsd);
                                list = a2.a;
                                cVar.c = a2.b;
                                d = i2;
                            } else if (d == a.aM) {
                                com.google.android.exoplayer2.d.a.b(str == null);
                                str = childAtomType == a.aK ? "video/x-vnd.on2.vp8" : "video/x-vnd.on2.vp9";
                                d = i2;
                            } else if (d == a.g) {
                                com.google.android.exoplayer2.d.a.b(str == null);
                                str = "video/3gpp";
                                d = i2;
                            } else if (d == a.J) {
                                com.google.android.exoplayer2.d.a.b(str == null);
                                Pair a3 = a(stsd, d2);
                                str2 = (String) a3.first;
                                list = Collections.singletonList(a3.second);
                                str = str2;
                                d = i2;
                            } else if (d == a.ai) {
                                stsd.c(d2 + 8);
                                f = ((float) stsd.t()) / ((float) stsd.t());
                                d = 1;
                            } else if (d == a.aI) {
                                d = d2 + 8;
                                while (d - d2 < n) {
                                    stsd.c(d);
                                    int n2 = stsd.n();
                                    if (stsd.n() == a.aJ) {
                                        bArr = Arrays.copyOfRange(stsd.a, d, n2 + d);
                                        d = i2;
                                    } else {
                                        d += n2;
                                    }
                                }
                                bArr = null;
                                d = i2;
                            } else {
                                if (d == a.aH) {
                                    d = stsd.g();
                                    stsd.d(3);
                                    if (d == 0) {
                                        switch (stsd.g()) {
                                            case 0:
                                                i4 = 0;
                                                d = i2;
                                                break;
                                            case 1:
                                                i4 = 1;
                                                d = i2;
                                                break;
                                            case 2:
                                                i4 = 2;
                                                d = i2;
                                                break;
                                            case 3:
                                                i4 = 3;
                                                d = i2;
                                                break;
                                        }
                                    }
                                }
                                d = i2;
                            }
                            i2 = i3 + n;
                            i3 = i2;
                        }
                    }
                }
                if (str != null) {
                    cVar.b = Format.a(Integer.toString(trackId), str, -1, h, h2, list, rotationDegrees, f, bArr, i4, null, drmInitData);
                }
            } else if (childAtomType == a.i || childAtomType == a.aa || childAtomType == a.n || childAtomType == a.p || childAtomType == a.r || childAtomType == a.u || childAtomType == a.s || childAtomType == a.t || childAtomType == a.ay || childAtomType == a.az || childAtomType == a.l || childAtomType == a.m || childAtomType == a.j || childAtomType == a.aO) {
                int h3;
                stsd.c((childStartPosition + 8) + 8);
                if (isQuickTime) {
                    d = stsd.h();
                    stsd.d(6);
                    i2 = d;
                } else {
                    stsd.d(8);
                    i2 = 0;
                }
                if (i2 == 0 || i2 == 1) {
                    h3 = stsd.h();
                    stsd.d(6);
                    d = stsd.r();
                    if (i2 == 1) {
                        stsd.d(16);
                    }
                } else if (i2 == 2) {
                    stsd.d(16);
                    d = (int) Math.round(Double.longBitsToDouble(stsd.p()));
                    h3 = stsd.t();
                    stsd.d(20);
                }
                d2 = stsd.d();
                if (childAtomType == a.aa) {
                    childAtomType = a(stsd, childStartPosition, childAtomSize, cVar, i);
                    stsd.c(d2);
                }
                String str3 = null;
                if (childAtomType == a.n) {
                    str3 = "audio/ac3";
                } else if (childAtomType == a.p) {
                    str3 = "audio/eac3";
                } else if (childAtomType == a.r) {
                    str3 = "audio/vnd.dts";
                } else if (childAtomType == a.s || childAtomType == a.t) {
                    str3 = "audio/vnd.dts.hd";
                } else if (childAtomType == a.u) {
                    str3 = "audio/vnd.dts.hd;profile=lbr";
                } else if (childAtomType == a.ay) {
                    str3 = "audio/3gpp";
                } else if (childAtomType == a.az) {
                    str3 = "audio/amr-wb";
                } else if (childAtomType == a.l || childAtomType == a.m) {
                    str3 = "audio/raw";
                } else if (childAtomType == a.j) {
                    str3 = "audio/mpeg";
                } else if (childAtomType == a.aO) {
                    str3 = "audio/alac";
                }
                Object obj2 = null;
                int i5 = d;
                h2 = h3;
                str = str3;
                while (d2 - childStartPosition < childAtomSize) {
                    stsd.c(d2);
                    n = stsd.n();
                    com.google.android.exoplayer2.d.a.a(n > 0, (Object) "childAtomSize should be positive");
                    d = stsd.n();
                    if (d == a.J || (isQuickTime && d == a.k)) {
                        if (d == a.J) {
                            d = d2;
                        } else {
                            i2 = stsd.d();
                            while (i2 - d2 < n) {
                                stsd.c(i2);
                                h = stsd.n();
                                com.google.android.exoplayer2.d.a.a(h > 0, (Object) "childAtomSize should be positive");
                                if (stsd.n() == a.J) {
                                    d = i2;
                                } else {
                                    i2 += h;
                                }
                            }
                            d = -1;
                        }
                        if (d != -1) {
                            Pair a4 = a(stsd, d);
                            str = (String) a4.first;
                            obj = (byte[]) a4.second;
                            if ("audio/mp4a-latm".equals(str)) {
                                Pair a5 = com.google.android.exoplayer2.d.b.a((byte[]) obj);
                                i5 = ((Integer) a5.first).intValue();
                                h2 = ((Integer) a5.second).intValue();
                            }
                        } else {
                            obj = obj2;
                        }
                        obj2 = obj;
                    } else if (d == a.o) {
                        stsd.c(d2 + 8);
                        cVar.b = com.google.android.exoplayer2.a.a.a(stsd, Integer.toString(trackId), language, drmInitData);
                    } else if (d == a.q) {
                        stsd.c(d2 + 8);
                        cVar.b = com.google.android.exoplayer2.a.a.b(stsd, Integer.toString(trackId), language, drmInitData);
                    } else if (d == a.v) {
                        cVar.b = Format.a(Integer.toString(trackId), str, -1, -1, h2, i5, null, drmInitData, language);
                    } else if (d == a.aO) {
                        obj2 = new byte[n];
                        stsd.c(d2);
                        stsd.a(obj2, 0, n);
                    }
                    d2 += n;
                }
                if (cVar.b == null && str != null) {
                    List list2;
                    i3 = "audio/raw".equals(str) ? 2 : -1;
                    str2 = Integer.toString(trackId);
                    if (obj2 == null) {
                        list2 = null;
                    } else {
                        list2 = Collections.singletonList(obj2);
                    }
                    cVar.b = Format.a(str2, str, -1, -1, h2, i5, i3, list2, drmInitData, 0, language);
                }
            } else if (childAtomType == a.aj || childAtomType == a.au || childAtomType == a.av || childAtomType == a.aw || childAtomType == a.ax) {
                stsd.c((childStartPosition + 8) + 8);
                List list3 = null;
                long j = Long.MAX_VALUE;
                if (childAtomType == a.aj) {
                    str = "application/ttml+xml";
                } else if (childAtomType == a.au) {
                    str = "application/x-quicktime-tx3g";
                    d = (childAtomSize - 8) - 8;
                    obj = new byte[d];
                    stsd.a(obj, 0, d);
                    list3 = Collections.singletonList(obj);
                } else if (childAtomType == a.av) {
                    str = "application/x-mp4-vtt";
                } else if (childAtomType == a.aw) {
                    str = "application/ttml+xml";
                    j = 0;
                } else if (childAtomType == a.ax) {
                    str = "application/x-mp4-cea-608";
                    cVar.d = 1;
                } else {
                    throw new IllegalStateException();
                }
                cVar.b = Format.a(Integer.toString(trackId), str, 0, language, -1, drmInitData, j, list3);
            } else if (childAtomType == a.aN) {
                cVar.b = Format.a(Integer.toString(trackId), "application/x-camera-motion", drmInitData);
            }
            stsd.c(childStartPosition + childAtomSize);
        }
        return cVar;
    }

    private static Pair<long[], long[]> a(a edtsAtom) {
        if (edtsAtom != null) {
            b elst = edtsAtom.d(a.Q);
            if (elst != null) {
                k elstData = elst.aQ;
                elstData.c(8);
                int version = a.a(elstData.n());
                int entryCount = elstData.t();
                long[] editListDurations = new long[entryCount];
                long[] editListMediaTimes = new long[entryCount];
                for (int i = 0; i < entryCount; i++) {
                    editListDurations[i] = version == 1 ? elstData.v() : elstData.l();
                    editListMediaTimes[i] = version == 1 ? elstData.p() : (long) elstData.n();
                    if (elstData.j() != (short) 1) {
                        throw new IllegalArgumentException("Unsupported media rate.");
                    }
                    elstData.d(2);
                }
                return Pair.create(editListDurations, editListMediaTimes);
            }
        }
        return Pair.create(null, null);
    }

    private static Pair<String, byte[]> a(k parent, int position) {
        String mimeType;
        parent.c((position + 8) + 4);
        parent.d(1);
        a(parent);
        parent.d(2);
        int flags = parent.g();
        if ((flags & 128) != 0) {
            parent.d(2);
        }
        if ((flags & 64) != 0) {
            parent.d(parent.h());
        }
        if ((flags & 32) != 0) {
            parent.d(2);
        }
        parent.d(1);
        a(parent);
        switch (parent.g()) {
            case 32:
                mimeType = "video/mp4v-es";
                break;
            case 33:
                mimeType = CodecUtils.MEDIA_TYPE;
                break;
            case 35:
                mimeType = "video/hevc";
                break;
            case 64:
            case 102:
            case 103:
            case 104:
                mimeType = "audio/mp4a-latm";
                break;
            case 107:
                return Pair.create("audio/mpeg", null);
            case 165:
                mimeType = "audio/ac3";
                break;
            case 166:
                mimeType = "audio/eac3";
                break;
            case 169:
            case 172:
                return Pair.create("audio/vnd.dts", null);
            case 170:
            case 171:
                return Pair.create("audio/vnd.dts.hd", null);
            default:
                mimeType = null;
                break;
        }
        parent.d(12);
        parent.d(1);
        int initializationDataSize = a(parent);
        byte[] initializationData = new byte[initializationDataSize];
        parent.a(initializationData, 0, initializationDataSize);
        return Pair.create(mimeType, initializationData);
    }

    private static int a(k parent, int position, int size, c out, int entryIndex) {
        int childPosition = parent.d();
        while (childPosition - position < size) {
            parent.c(childPosition);
            int childAtomSize = parent.n();
            com.google.android.exoplayer2.d.a.a(childAtomSize > 0, (Object) "childAtomSize should be positive");
            if (parent.n() == a.V) {
                Pair<Integer, i> result;
                int i = childPosition + 8;
                Object obj = null;
                Object obj2 = null;
                Object obj3 = null;
                while (true) {
                    int i2 = i;
                    if (i2 - childPosition >= childAtomSize) {
                        break;
                    }
                    parent.c(i2);
                    int n = parent.n();
                    i = parent.n();
                    if (i == a.ab) {
                        obj3 = Integer.valueOf(parent.n());
                    } else if (i == a.W) {
                        parent.d(4);
                        obj = parent.n() == g ? 1 : null;
                    } else if (i == a.X) {
                        int i3 = i2 + 8;
                        while (i3 - i2 < n) {
                            parent.c(i3);
                            i = parent.n();
                            if (parent.n() == a.Y) {
                                parent.d(6);
                                boolean z = parent.g() == 1;
                                int g = parent.g();
                                byte[] bArr = new byte[16];
                                parent.a(bArr, 0, 16);
                                i obj22 = new i(z, g, bArr);
                            } else {
                                i3 += i;
                            }
                        }
                        obj22 = null;
                    }
                    i = i2 + n;
                }
                if (obj != null) {
                    com.google.android.exoplayer2.d.a.a(obj3 != null, (Object) "frma atom is mandatory");
                    com.google.android.exoplayer2.d.a.a(obj22 != null, (Object) "schi->tenc atom is mandatory");
                    result = Pair.create(obj3, obj22);
                } else {
                    result = null;
                }
                if (result != null) {
                    out.a[entryIndex] = (i) result.second;
                    return ((Integer) result.first).intValue();
                }
            }
            childPosition += childAtomSize;
        }
        return 0;
    }

    private static int a(k data) {
        int currentByte = data.g();
        int size = currentByte & 127;
        while ((currentByte & 128) == 128) {
            currentByte = data.g();
            size = (size << 7) | (currentByte & 127);
        }
        return size;
    }
}
