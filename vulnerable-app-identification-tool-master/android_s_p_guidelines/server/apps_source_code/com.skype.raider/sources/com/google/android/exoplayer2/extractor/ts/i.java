package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.i.b;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.l;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.extractor.ts.t.d;
import com.skype.android.video.hw.utils.CodecUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class i implements g {
    private final r a;
    private final boolean b;
    private final boolean c;
    private final m d = new m(7);
    private final m e = new m(8);
    private final m f = new m(6);
    private long g;
    private final boolean[] h = new boolean[3];
    private String i;
    private n j;
    private a k;
    private boolean l;
    private long m;
    private final k n = new k();

    private static final class a {
        private final n a;
        private final boolean b;
        private final boolean c;
        private final SparseArray<b> d = new SparseArray();
        private final SparseArray<com.google.android.exoplayer2.d.i.a> e = new SparseArray();
        private final l f = new l(this.g, 0, 0);
        private byte[] g = new byte[128];
        private int h;
        private int i;
        private long j;
        private boolean k;
        private long l;
        private a m = new a();
        private a n = new a();
        private boolean o;
        private long p;
        private long q;
        private boolean r;

        private static final class a {
            private boolean a;
            private boolean b;
            private b c;
            private int d;
            private int e;
            private int f;
            private int g;
            private boolean h;
            private boolean i;
            private boolean j;
            private boolean k;
            private int l;
            private int m;
            private int n;
            private int o;
            private int p;

            private a() {
            }

            /* synthetic */ a(byte b) {
                this();
            }

            static /* synthetic */ boolean a(a x0, a x1) {
                if (x0.a) {
                    if (!x1.a || x0.f != x1.f || x0.g != x1.g || x0.h != x1.h) {
                        return true;
                    }
                    if (x0.i && x1.i && x0.j != x1.j) {
                        return true;
                    }
                    if (x0.d != x1.d && (x0.d == 0 || x1.d == 0)) {
                        return true;
                    }
                    if (x0.c.h == 0 && x1.c.h == 0 && (x0.m != x1.m || x0.n != x1.n)) {
                        return true;
                    }
                    if ((x0.c.h == 1 && x1.c.h == 1 && (x0.o != x1.o || x0.p != x1.p)) || x0.k != x1.k) {
                        return true;
                    }
                    if (x0.k && x1.k && x0.l != x1.l) {
                        return true;
                    }
                }
                return false;
            }

            public final void a() {
                this.b = false;
                this.a = false;
            }

            public final void a(int sliceType) {
                this.e = sliceType;
                this.b = true;
            }

            public final void a(b spsData, int nalRefIdc, int sliceType, int frameNum, int picParameterSetId, boolean fieldPicFlag, boolean bottomFieldFlagPresent, boolean bottomFieldFlag, boolean idrPicFlag, int idrPicId, int picOrderCntLsb, int deltaPicOrderCntBottom, int deltaPicOrderCnt0, int deltaPicOrderCnt1) {
                this.c = spsData;
                this.d = nalRefIdc;
                this.e = sliceType;
                this.f = frameNum;
                this.g = picParameterSetId;
                this.h = fieldPicFlag;
                this.i = bottomFieldFlagPresent;
                this.j = bottomFieldFlag;
                this.k = idrPicFlag;
                this.l = idrPicId;
                this.m = picOrderCntLsb;
                this.n = deltaPicOrderCntBottom;
                this.o = deltaPicOrderCnt0;
                this.p = deltaPicOrderCnt1;
                this.a = true;
                this.b = true;
            }

            public final boolean b() {
                return this.b && (this.e == 7 || this.e == 2);
            }
        }

        public a(n output, boolean allowNonIdrKeyframes, boolean detectAccessUnits) {
            this.a = output;
            this.b = allowNonIdrKeyframes;
            this.c = detectAccessUnits;
            b();
        }

        public final boolean a() {
            return this.c;
        }

        public final void a(b spsData) {
            this.d.append(spsData.a, spsData);
        }

        public final void a(com.google.android.exoplayer2.d.i.a ppsData) {
            this.e.append(ppsData.a, ppsData);
        }

        public final void b() {
            this.k = false;
            this.o = false;
            this.n.a();
        }

        public final void a(long position, int type, long pesTimeUs) {
            this.i = type;
            this.l = pesTimeUs;
            this.j = position;
            if (!(this.b && this.i == 1)) {
                if (!this.c) {
                    return;
                }
                if (!(this.i == 5 || this.i == 1 || this.i == 2)) {
                    return;
                }
            }
            a newSliceHeader = this.m;
            this.m = this.n;
            this.n = newSliceHeader;
            this.n.a();
            this.h = 0;
            this.k = true;
        }

        public final void a(byte[] data, int offset, int limit) {
            if (this.k) {
                int readLength = limit - offset;
                if (this.g.length < this.h + readLength) {
                    this.g = Arrays.copyOf(this.g, (this.h + readLength) * 2);
                }
                System.arraycopy(data, offset, this.g, this.h, readLength);
                this.h += readLength;
                this.f.a(this.g, 0, this.h);
                if (this.f.b(8)) {
                    this.f.a(1);
                    int nalRefIdc = this.f.c(2);
                    this.f.a(5);
                    if (this.f.b()) {
                        this.f.c();
                        if (this.f.b()) {
                            int sliceType = this.f.c();
                            if (!this.c) {
                                this.k = false;
                                this.n.a(sliceType);
                            } else if (this.f.b()) {
                                int picParameterSetId = this.f.c();
                                if (this.e.indexOfKey(picParameterSetId) < 0) {
                                    this.k = false;
                                    return;
                                }
                                com.google.android.exoplayer2.d.i.a ppsData = (com.google.android.exoplayer2.d.i.a) this.e.get(picParameterSetId);
                                b spsData = (b) this.d.get(ppsData.b);
                                if (spsData.e) {
                                    if (this.f.b(2)) {
                                        this.f.a(2);
                                    } else {
                                        return;
                                    }
                                }
                                if (this.f.b(spsData.g)) {
                                    boolean fieldPicFlag = false;
                                    boolean bottomFieldFlagPresent = false;
                                    boolean bottomFieldFlag = false;
                                    int frameNum = this.f.c(spsData.g);
                                    if (!spsData.f) {
                                        if (this.f.b(1)) {
                                            fieldPicFlag = this.f.a();
                                            if (fieldPicFlag) {
                                                if (this.f.b(1)) {
                                                    bottomFieldFlag = this.f.a();
                                                    bottomFieldFlagPresent = true;
                                                } else {
                                                    return;
                                                }
                                            }
                                        }
                                        return;
                                    }
                                    boolean idrPicFlag = this.i == 5;
                                    int idrPicId = 0;
                                    if (idrPicFlag) {
                                        if (this.f.b()) {
                                            idrPicId = this.f.c();
                                        } else {
                                            return;
                                        }
                                    }
                                    int picOrderCntLsb = 0;
                                    int deltaPicOrderCntBottom = 0;
                                    int deltaPicOrderCnt0 = 0;
                                    int deltaPicOrderCnt1 = 0;
                                    if (spsData.h == 0) {
                                        if (this.f.b(spsData.i)) {
                                            picOrderCntLsb = this.f.c(spsData.i);
                                            if (ppsData.c && !fieldPicFlag) {
                                                if (this.f.b()) {
                                                    deltaPicOrderCntBottom = this.f.d();
                                                } else {
                                                    return;
                                                }
                                            }
                                        }
                                        return;
                                    } else if (spsData.h == 1 && !spsData.j) {
                                        if (this.f.b()) {
                                            deltaPicOrderCnt0 = this.f.d();
                                            if (ppsData.c && !fieldPicFlag) {
                                                if (this.f.b()) {
                                                    deltaPicOrderCnt1 = this.f.d();
                                                } else {
                                                    return;
                                                }
                                            }
                                        }
                                        return;
                                    }
                                    this.n.a(spsData, nalRefIdc, sliceType, frameNum, picParameterSetId, fieldPicFlag, bottomFieldFlagPresent, bottomFieldFlag, idrPicFlag, idrPicId, picOrderCntLsb, deltaPicOrderCntBottom, deltaPicOrderCnt0, deltaPicOrderCnt1);
                                    this.k = false;
                                }
                            }
                        }
                    }
                }
            }
        }

        public final void a(long position, int offset) {
            int i = 0;
            if (this.i == 9 || (this.c && a.a(this.n, this.m))) {
                if (this.o) {
                    int i2;
                    int i3 = offset + ((int) (position - this.j));
                    if (this.r) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    this.a.a(this.q, i2, (int) (this.j - this.p), i3, null);
                }
                this.p = this.j;
                this.q = this.l;
                this.r = false;
                this.o = true;
            }
            boolean z = this.r;
            if (this.i == 5 || (this.b && this.i == 1 && this.n.b())) {
                i = 1;
            }
            this.r = z | i;
        }
    }

    public i(r seiReader, boolean allowNonIdrKeyframes, boolean detectAccessUnits) {
        this.a = seiReader;
        this.b = allowNonIdrKeyframes;
        this.c = detectAccessUnits;
    }

    public final void a() {
        com.google.android.exoplayer2.d.i.a(this.h);
        this.d.a();
        this.e.a();
        this.f.a();
        this.k.b();
        this.g = 0;
    }

    public final void a(h extractorOutput, d idGenerator) {
        idGenerator.a();
        this.i = idGenerator.c();
        this.j = extractorOutput.a(idGenerator.b());
        this.k = new a(this.j, this.b, this.c);
        this.a.a(extractorOutput, idGenerator);
    }

    public final void a(long pesTimeUs, boolean dataAlignmentIndicator) {
        this.m = pesTimeUs;
    }

    public final void a(k data) {
        int offset = data.d();
        int limit = data.c();
        byte[] dataArray = data.a;
        this.g += (long) data.b();
        this.j.a(data, data.b());
        while (true) {
            int nalUnitOffset = com.google.android.exoplayer2.d.i.a(dataArray, offset, limit, this.h);
            if (nalUnitOffset == limit) {
                a(dataArray, offset, limit);
                return;
            }
            int i;
            int nalUnitType = com.google.android.exoplayer2.d.i.b(dataArray, nalUnitOffset);
            int lengthToNalUnit = nalUnitOffset - offset;
            if (lengthToNalUnit > 0) {
                a(dataArray, offset, nalUnitOffset);
            }
            int bytesWrittenPastPosition = limit - nalUnitOffset;
            long absolutePosition = this.g - ((long) bytesWrittenPastPosition);
            if (lengthToNalUnit < 0) {
                i = -lengthToNalUnit;
            } else {
                i = 0;
            }
            long j = this.m;
            if (!this.l || this.k.a()) {
                this.d.b(i);
                this.e.b(i);
                if (this.l) {
                    if (this.d.b()) {
                        this.k.a(com.google.android.exoplayer2.d.i.a(this.d.a, 3, this.d.b));
                        this.d.a();
                    } else if (this.e.b()) {
                        this.k.a(com.google.android.exoplayer2.d.i.d(this.e.a, this.e.b));
                        this.e.a();
                    }
                } else if (this.d.b() && this.e.b()) {
                    List arrayList = new ArrayList();
                    arrayList.add(Arrays.copyOf(this.d.a, this.d.b));
                    arrayList.add(Arrays.copyOf(this.e.a, this.e.b));
                    b a = com.google.android.exoplayer2.d.i.a(this.d.a, 3, this.d.b);
                    com.google.android.exoplayer2.d.i.a d = com.google.android.exoplayer2.d.i.d(this.e.a, this.e.b);
                    this.j.a(Format.a(this.i, CodecUtils.MEDIA_TYPE, a.b, a.c, arrayList, a.d));
                    this.l = true;
                    this.k.a(a);
                    this.k.a(d);
                    this.d.a();
                    this.e.a();
                }
            }
            if (this.f.b(i)) {
                this.n.a(this.f.a, com.google.android.exoplayer2.d.i.a(this.f.a, this.f.b));
                this.n.c(4);
                this.a.a(j, this.n);
            }
            this.k.a(absolutePosition, bytesWrittenPastPosition);
            long j2 = this.m;
            if (!this.l || this.k.a()) {
                this.d.a(nalUnitType);
                this.e.a(nalUnitType);
            }
            this.f.a(nalUnitType);
            this.k.a(absolutePosition, nalUnitType, j2);
            offset = nalUnitOffset + 3;
        }
    }

    public final void b() {
    }

    private void a(byte[] dataArray, int offset, int limit) {
        if (!this.l || this.k.a()) {
            this.d.a(dataArray, offset, limit);
            this.e.a(dataArray, offset, limit);
        }
        this.f.a(dataArray, offset, limit);
        this.k.a(dataArray, offset, limit);
    }
}
