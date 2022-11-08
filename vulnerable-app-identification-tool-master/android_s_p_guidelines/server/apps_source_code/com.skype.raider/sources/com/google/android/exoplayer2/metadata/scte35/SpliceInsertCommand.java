package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SpliceInsertCommand extends SpliceCommand {
    public static final Creator<SpliceInsertCommand> CREATOR = new Creator<SpliceInsertCommand>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new SpliceInsertCommand[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new SpliceInsertCommand(parcel, (byte) 0);
        }
    };
    public final long a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final long f;
    public final long g;
    public final List<a> h;
    public final boolean i;
    public final long j;
    public final int k;
    public final int l;
    public final int m;

    public static final class a {
        public final int a;
        public final long b;
        public final long c;

        /* synthetic */ a(int x0, long x1, long x2, byte b) {
            this(x0, x1, x2);
        }

        private a(int componentTag, long componentSplicePts, long componentSplicePlaybackPositionUs) {
            this.a = componentTag;
            this.b = componentSplicePts;
            this.c = componentSplicePlaybackPositionUs;
        }

        public static a a(Parcel in) {
            return new a(in.readInt(), in.readLong(), in.readLong());
        }
    }

    /* synthetic */ SpliceInsertCommand(Parcel x0, byte b) {
        this(x0);
    }

    private SpliceInsertCommand(long spliceEventId, boolean spliceEventCancelIndicator, boolean outOfNetworkIndicator, boolean programSpliceFlag, boolean spliceImmediateFlag, long programSplicePts, long programSplicePlaybackPositionUs, List<a> componentSpliceList, boolean autoReturn, long breakDuration, int uniqueProgramId, int availNum, int availsExpected) {
        this.a = spliceEventId;
        this.b = spliceEventCancelIndicator;
        this.c = outOfNetworkIndicator;
        this.d = programSpliceFlag;
        this.e = spliceImmediateFlag;
        this.f = programSplicePts;
        this.g = programSplicePlaybackPositionUs;
        this.h = Collections.unmodifiableList(componentSpliceList);
        this.i = autoReturn;
        this.j = breakDuration;
        this.k = uniqueProgramId;
        this.l = availNum;
        this.m = availsExpected;
    }

    private SpliceInsertCommand(Parcel in) {
        boolean z;
        boolean z2 = true;
        this.a = in.readLong();
        if (in.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        this.b = z;
        if (in.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        this.c = z;
        if (in.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        this.d = z;
        if (in.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        this.e = z;
        this.f = in.readLong();
        this.g = in.readLong();
        int componentSpliceListSize = in.readInt();
        List<a> componentSpliceList = new ArrayList(componentSpliceListSize);
        for (int i = 0; i < componentSpliceListSize; i++) {
            componentSpliceList.add(a.a(in));
        }
        this.h = Collections.unmodifiableList(componentSpliceList);
        if (in.readByte() != (byte) 1) {
            z2 = false;
        }
        this.i = z2;
        this.j = in.readLong();
        this.k = in.readInt();
        this.l = in.readInt();
        this.m = in.readInt();
    }

    static com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand a(com.google.android.exoplayer2.d.k r35, long r36, com.google.android.exoplayer2.d.q r38) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r18_1 'componentSplices' java.util.List<com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand$a>) in PHI: PHI: (r18_2 'componentSplices' java.util.List<com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand$a>) = (r18_0 'componentSplices' java.util.List<com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand$a>), (r18_1 'componentSplices' java.util.List<com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand$a>) binds: {(r18_0 'componentSplices' java.util.List<com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand$a>)=B:20:0x004b, (r18_1 'componentSplices' java.util.List<com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand$a>)=B:42:0x0090}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:222)
	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r32 = r35.l();
        r2 = r35.g();
        r2 = r2 & 128;
        if (r2 == 0) goto L_0x0085;
    L_0x000c:
        r10 = 1;
    L_0x000d:
        r11 = 0;
        r12 = 0;
        r13 = 0;
        r14 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r18 = java.util.Collections.emptyList();
        r22 = 0;
        r23 = 0;
        r24 = 0;
        r19 = 0;
        r20 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        if (r10 != 0) goto L_0x00be;
    L_0x0028:
        r27 = r35.g();
        r0 = r27;
        r2 = r0 & 128;
        if (r2 == 0) goto L_0x0087;
    L_0x0032:
        r11 = 1;
    L_0x0033:
        r2 = r27 & 64;
        if (r2 == 0) goto L_0x0089;
    L_0x0037:
        r12 = 1;
    L_0x0038:
        r2 = r27 & 32;
        if (r2 == 0) goto L_0x008b;
    L_0x003c:
        r26 = 1;
    L_0x003e:
        r2 = r27 & 16;
        if (r2 == 0) goto L_0x008e;
    L_0x0042:
        r13 = 1;
    L_0x0043:
        if (r12 == 0) goto L_0x004b;
    L_0x0045:
        if (r13 != 0) goto L_0x004b;
    L_0x0047:
        r14 = com.google.android.exoplayer2.metadata.scte35.TimeSignalCommand.a(r35, r36);
    L_0x004b:
        if (r12 != 0) goto L_0x0090;
    L_0x004d:
        r25 = r35.g();
        r18 = new java.util.ArrayList;
        r0 = r18;
        r1 = r25;
        r0.<init>(r1);
        r30 = 0;
    L_0x005c:
        r0 = r30;
        r1 = r25;
        if (r0 >= r1) goto L_0x0090;
    L_0x0062:
        r3 = r35.g();
        r4 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        if (r13 != 0) goto L_0x0071;
    L_0x006d:
        r4 = com.google.android.exoplayer2.metadata.scte35.TimeSignalCommand.a(r35, r36);
    L_0x0071:
        r2 = new com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand$a;
        r0 = r38;
        r6 = r0.a(r4);
        r8 = 0;
        r2.<init>(r3, r4, r6, r8);
        r0 = r18;
        r0.add(r2);
        r30 = r30 + 1;
        goto L_0x005c;
    L_0x0085:
        r10 = 0;
        goto L_0x000d;
    L_0x0087:
        r11 = 0;
        goto L_0x0033;
    L_0x0089:
        r12 = 0;
        goto L_0x0038;
    L_0x008b:
        r26 = 0;
        goto L_0x003e;
    L_0x008e:
        r13 = 0;
        goto L_0x0043;
    L_0x0090:
        if (r26 == 0) goto L_0x00b2;
    L_0x0092:
        r2 = r35.g();
        r0 = (long) r2;
        r28 = r0;
        r6 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r6 = r6 & r28;
        r8 = 0;
        r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r2 == 0) goto L_0x00cc;
    L_0x00a3:
        r19 = 1;
    L_0x00a5:
        r6 = 1;
        r6 = r6 & r28;
        r2 = 32;
        r6 = r6 << r2;
        r8 = r35.l();
        r20 = r6 | r8;
    L_0x00b2:
        r22 = r35.h();
        r23 = r35.g();
        r24 = r35.g();
    L_0x00be:
        r7 = new com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand;
        r0 = r38;
        r16 = r0.a(r14);
        r8 = r32;
        r7.<init>(r8, r10, r11, r12, r13, r14, r16, r18, r19, r20, r22, r23, r24);
        return r7;
    L_0x00cc:
        r19 = 0;
        goto L_0x00a5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand.a(com.google.android.exoplayer2.d.k, long, com.google.android.exoplayer2.d.q):com.google.android.exoplayer2.metadata.scte35.SpliceInsertCommand");
    }

    public final void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2 = 1;
        dest.writeLong(this.a);
        if (this.b) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        if (this.c) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        if (this.d) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        if (this.e) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        dest.writeLong(this.f);
        dest.writeLong(this.g);
        int componentSpliceListSize = this.h.size();
        dest.writeInt(componentSpliceListSize);
        for (int i3 = 0; i3 < componentSpliceListSize; i3++) {
            a aVar = (a) this.h.get(i3);
            dest.writeInt(aVar.a);
            dest.writeLong(aVar.b);
            dest.writeLong(aVar.c);
        }
        if (!this.i) {
            i2 = 0;
        }
        dest.writeByte((byte) i2);
        dest.writeLong(this.j);
        dest.writeInt(this.k);
        dest.writeInt(this.l);
        dest.writeInt(this.m);
    }
}
