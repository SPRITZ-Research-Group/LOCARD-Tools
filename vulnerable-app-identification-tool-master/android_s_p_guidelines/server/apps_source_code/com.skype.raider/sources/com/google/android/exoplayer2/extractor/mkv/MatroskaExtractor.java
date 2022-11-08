package com.google.android.exoplayer2.extractor.mkv;

import android.util.SparseArray;
import com.adjust.sdk.Constants;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmInitData.SchemeData;
import com.google.android.exoplayer2.extractor.f;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.i;
import com.google.android.exoplayer2.extractor.l;
import com.google.android.exoplayer2.extractor.m;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.skype.Defines;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public final class MatroskaExtractor implements f {
    public static final i a = new i() {
        public final f[] a() {
            return new f[]{new MatroskaExtractor()};
        }
    };
    private static final byte[] b = new byte[]{(byte) 49, (byte) 10, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 44, (byte) 48, (byte) 48, (byte) 48, (byte) 32, (byte) 45, (byte) 45, (byte) 62, (byte) 32, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 44, (byte) 48, (byte) 48, (byte) 48, (byte) 10};
    private static final byte[] c = new byte[]{(byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
    private static final UUID d = new UUID(72057594037932032L, -9223371306706625679L);
    private long A;
    private boolean B;
    private long C;
    private long D;
    private long E;
    private com.google.android.exoplayer2.d.f F;
    private com.google.android.exoplayer2.d.f G;
    private boolean H;
    private int I;
    private long J;
    private long K;
    private int L;
    private int M;
    private int[] N;
    private int O;
    private int P;
    private int Q;
    private int R;
    private boolean S;
    private boolean T;
    private boolean U;
    private boolean V;
    private byte W;
    private int X;
    private int Y;
    private int Z;
    private boolean aa;
    private boolean ab;
    private h ac;
    private final b e;
    private final e f;
    private final SparseArray<b> g;
    private final boolean h;
    private final k i;
    private final k j;
    private final k k;
    private final k l;
    private final k m;
    private final k n;
    private final k o;
    private final k p;
    private final k q;
    private ByteBuffer r;
    private long s;
    private long t;
    private long u;
    private long v;
    private long w;
    private b x;
    private boolean y;
    private int z;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    private final class a implements c {
        final /* synthetic */ MatroskaExtractor a;

        private a(MatroskaExtractor matroskaExtractor) {
            this.a = matroskaExtractor;
        }

        /* synthetic */ a(MatroskaExtractor x0, byte b) {
            this(x0);
        }

        public final int a(int id) {
            return MatroskaExtractor.a(id);
        }

        public final boolean b(int id) {
            return MatroskaExtractor.b(id);
        }

        public final void a(int id, long contentPosition, long contentSize) throws com.google.android.exoplayer2.k {
            this.a.a(id, contentPosition, contentSize);
        }

        public final void c(int id) throws com.google.android.exoplayer2.k {
            this.a.c(id);
        }

        public final void a(int id, long value) throws com.google.android.exoplayer2.k {
            this.a.a(id, value);
        }

        public final void a(int id, double value) throws com.google.android.exoplayer2.k {
            this.a.a(id, value);
        }

        public final void a(int id, String value) throws com.google.android.exoplayer2.k {
            this.a.a(id, value);
        }

        public final void a(int id, int contentsSize, g input) throws IOException, InterruptedException {
            this.a.a(id, contentsSize, input);
        }
    }

    private static final class b {
        public float A;
        public float B;
        public float C;
        public float D;
        public float E;
        public float F;
        public int G;
        public int H;
        public int I;
        public long J;
        public long K;
        public boolean L;
        public boolean M;
        public n N;
        public int O;
        private String P;
        public String a;
        public int b;
        public int c;
        public int d;
        public boolean e;
        public byte[] f;
        public byte[] g;
        public byte[] h;
        public DrmInitData i;
        public int j;
        public int k;
        public int l;
        public int m;
        public int n;
        public byte[] o;
        public int p;
        public boolean q;
        public int r;
        public int s;
        public int t;
        public int u;
        public int v;
        public float w;
        public float x;
        public float y;
        public float z;

        private b() {
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = 0;
            this.o = null;
            this.p = -1;
            this.q = false;
            this.r = -1;
            this.s = -1;
            this.t = -1;
            this.u = Constants.ONE_SECOND;
            this.v = 200;
            this.w = -1.0f;
            this.x = -1.0f;
            this.y = -1.0f;
            this.z = -1.0f;
            this.A = -1.0f;
            this.B = -1.0f;
            this.C = -1.0f;
            this.D = -1.0f;
            this.E = -1.0f;
            this.F = -1.0f;
            this.G = 1;
            this.H = -1;
            this.I = Defines.SKYLIB_MESSAGE_MAX_BODY_SIZE;
            this.J = 0;
            this.K = 0;
            this.M = true;
            this.P = "eng";
        }

        /* synthetic */ b(byte b) {
            this();
        }

        public final void a(com.google.android.exoplayer2.extractor.h r29, int r30) throws com.google.android.exoplayer2.k {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r22_1 'colorInfo' com.google.android.exoplayer2.video.ColorInfo) in PHI: PHI: (r22_2 'colorInfo' com.google.android.exoplayer2.video.ColorInfo) = (r22_0 'colorInfo' com.google.android.exoplayer2.video.ColorInfo), (r22_1 'colorInfo' com.google.android.exoplayer2.video.ColorInfo) binds: {(r22_0 'colorInfo' com.google.android.exoplayer2.video.ColorInfo)=B:157:0x03aa, (r22_1 'colorInfo' com.google.android.exoplayer2.video.ColorInfo)=B:179:0x0412}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:222)
	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r28 = this;
            r5 = -1;
            r8 = -1;
            r9 = 0;
            r0 = r28;
            r4 = r0.a;
            r2 = -1;
            r6 = r4.hashCode();
            switch(r6) {
                case -2095576542: goto L_0x004c;
                case -2095575984: goto L_0x0038;
                case -1985379776: goto L_0x010f;
                case -1784763192: goto L_0x00d3;
                case -1730367663: goto L_0x0080;
                case -1482641358: goto L_0x00a3;
                case -1482641357: goto L_0x00af;
                case -1373388978: goto L_0x006a;
                case -933872740: goto L_0x014b;
                case -538363189: goto L_0x0042;
                case -538363109: goto L_0x0056;
                case -425012669: goto L_0x0133;
                case -356037306: goto L_0x00f7;
                case 62923557: goto L_0x0097;
                case 62923603: goto L_0x00bb;
                case 62927045: goto L_0x00df;
                case 82338133: goto L_0x001a;
                case 82338134: goto L_0x0024;
                case 99146302: goto L_0x013f;
                case 444813526: goto L_0x0075;
                case 542569478: goto L_0x00eb;
                case 725957860: goto L_0x011b;
                case 855502857: goto L_0x0060;
                case 1422270023: goto L_0x0127;
                case 1809237540: goto L_0x002e;
                case 1950749482: goto L_0x00c7;
                case 1950789798: goto L_0x0103;
                case 1951062397: goto L_0x008b;
                default: goto L_0x000f;
            };
        L_0x000f:
            switch(r2) {
                case 0: goto L_0x0157;
                case 1: goto L_0x01a2;
                case 2: goto L_0x01a5;
                case 3: goto L_0x01a8;
                case 4: goto L_0x01a8;
                case 5: goto L_0x01a8;
                case 6: goto L_0x01bb;
                case 7: goto L_0x01d7;
                case 8: goto L_0x01f4;
                case 9: goto L_0x020b;
                case 10: goto L_0x020f;
                case 11: goto L_0x021d;
                case 12: goto L_0x026a;
                case 13: goto L_0x0276;
                case 14: goto L_0x027c;
                case 15: goto L_0x0282;
                case 16: goto L_0x0286;
                case 17: goto L_0x028a;
                case 18: goto L_0x028e;
                case 19: goto L_0x028e;
                case 20: goto L_0x0292;
                case 21: goto L_0x0296;
                case 22: goto L_0x02a2;
                case 23: goto L_0x02de;
                case 24: goto L_0x0307;
                case 25: goto L_0x030b;
                case 26: goto L_0x0317;
                case 27: goto L_0x031b;
                default: goto L_0x0012;
            };
        L_0x0012:
            r2 = new com.google.android.exoplayer2.k;
            r4 = "Unrecognized codec identifier.";
            r2.<init>(r4);
            throw r2;
        L_0x001a:
            r6 = "V_VP8";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x0022:
            r2 = 0;
            goto L_0x000f;
        L_0x0024:
            r6 = "V_VP9";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x002c:
            r2 = 1;
            goto L_0x000f;
        L_0x002e:
            r6 = "V_MPEG2";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x0036:
            r2 = 2;
            goto L_0x000f;
        L_0x0038:
            r6 = "V_MPEG4/ISO/SP";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x0040:
            r2 = 3;
            goto L_0x000f;
        L_0x0042:
            r6 = "V_MPEG4/ISO/ASP";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x004a:
            r2 = 4;
            goto L_0x000f;
        L_0x004c:
            r6 = "V_MPEG4/ISO/AP";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x0054:
            r2 = 5;
            goto L_0x000f;
        L_0x0056:
            r6 = "V_MPEG4/ISO/AVC";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x005e:
            r2 = 6;
            goto L_0x000f;
        L_0x0060:
            r6 = "V_MPEGH/ISO/HEVC";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x0068:
            r2 = 7;
            goto L_0x000f;
        L_0x006a:
            r6 = "V_MS/VFW/FOURCC";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x0072:
            r2 = 8;
            goto L_0x000f;
        L_0x0075:
            r6 = "V_THEORA";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x007d:
            r2 = 9;
            goto L_0x000f;
        L_0x0080:
            r6 = "A_VORBIS";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x0088:
            r2 = 10;
            goto L_0x000f;
        L_0x008b:
            r6 = "A_OPUS";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x0093:
            r2 = 11;
            goto L_0x000f;
        L_0x0097:
            r6 = "A_AAC";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x009f:
            r2 = 12;
            goto L_0x000f;
        L_0x00a3:
            r6 = "A_MPEG/L2";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x00ab:
            r2 = 13;
            goto L_0x000f;
        L_0x00af:
            r6 = "A_MPEG/L3";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x00b7:
            r2 = 14;
            goto L_0x000f;
        L_0x00bb:
            r6 = "A_AC3";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x00c3:
            r2 = 15;
            goto L_0x000f;
        L_0x00c7:
            r6 = "A_EAC3";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x00cf:
            r2 = 16;
            goto L_0x000f;
        L_0x00d3:
            r6 = "A_TRUEHD";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x00db:
            r2 = 17;
            goto L_0x000f;
        L_0x00df:
            r6 = "A_DTS";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x00e7:
            r2 = 18;
            goto L_0x000f;
        L_0x00eb:
            r6 = "A_DTS/EXPRESS";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x00f3:
            r2 = 19;
            goto L_0x000f;
        L_0x00f7:
            r6 = "A_DTS/LOSSLESS";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x00ff:
            r2 = 20;
            goto L_0x000f;
        L_0x0103:
            r6 = "A_FLAC";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x010b:
            r2 = 21;
            goto L_0x000f;
        L_0x010f:
            r6 = "A_MS/ACM";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x0117:
            r2 = 22;
            goto L_0x000f;
        L_0x011b:
            r6 = "A_PCM/INT/LIT";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x0123:
            r2 = 23;
            goto L_0x000f;
        L_0x0127:
            r6 = "S_TEXT/UTF8";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x012f:
            r2 = 24;
            goto L_0x000f;
        L_0x0133:
            r6 = "S_VOBSUB";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x013b:
            r2 = 25;
            goto L_0x000f;
        L_0x013f:
            r6 = "S_HDMV/PGS";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x0147:
            r2 = 26;
            goto L_0x000f;
        L_0x014b:
            r6 = "S_DVBSUB";
            r4 = r4.equals(r6);
            if (r4 == 0) goto L_0x000f;
        L_0x0153:
            r2 = 27;
            goto L_0x000f;
        L_0x0157:
            r3 = "video/x-vnd.on2.vp8";
        L_0x0159:
            r0 = r28;
            r2 = r0.M;
            if (r2 == 0) goto L_0x034e;
        L_0x015f:
            r2 = 1;
        L_0x0160:
            r4 = r2 | 0;
            r0 = r28;
            r2 = r0.L;
            if (r2 == 0) goto L_0x0351;
        L_0x0168:
            r2 = 2;
        L_0x0169:
            r11 = r4 | r2;
            r2 = com.google.android.exoplayer2.d.h.a(r3);
            if (r2 == 0) goto L_0x0354;
        L_0x0171:
            r2 = java.lang.Integer.toString(r30);
            r4 = -1;
            r0 = r28;
            r6 = r0.G;
            r0 = r28;
            r7 = r0.I;
            r0 = r28;
            r10 = r0.i;
            r0 = r28;
            r12 = r0.P;
            r25 = com.google.android.exoplayer2.Format.a(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
        L_0x018a:
            r0 = r28;
            r2 = r0.b;
            r0 = r29;
            r2 = r0.a(r2);
            r0 = r28;
            r0.N = r2;
            r0 = r28;
            r2 = r0.N;
            r0 = r25;
            r2.a(r0);
            return;
        L_0x01a2:
            r3 = "video/x-vnd.on2.vp9";
            goto L_0x0159;
        L_0x01a5:
            r3 = "video/mpeg2";
            goto L_0x0159;
        L_0x01a8:
            r3 = "video/mp4v-es";
            r0 = r28;
            r2 = r0.h;
            if (r2 != 0) goto L_0x01b2;
        L_0x01b0:
            r9 = 0;
        L_0x01b1:
            goto L_0x0159;
        L_0x01b2:
            r0 = r28;
            r2 = r0.h;
            r9 = java.util.Collections.singletonList(r2);
            goto L_0x01b1;
        L_0x01bb:
            r3 = "video/avc";
            r2 = new com.google.android.exoplayer2.d.k;
            r0 = r28;
            r4 = r0.h;
            r2.<init>(r4);
            r24 = com.google.android.exoplayer2.video.a.a(r2);
            r0 = r24;
            r9 = r0.a;
            r0 = r24;
            r2 = r0.b;
            r0 = r28;
            r0.O = r2;
            goto L_0x0159;
        L_0x01d7:
            r3 = "video/hevc";
            r2 = new com.google.android.exoplayer2.d.k;
            r0 = r28;
            r4 = r0.h;
            r2.<init>(r4);
            r27 = com.google.android.exoplayer2.video.b.a(r2);
            r0 = r27;
            r9 = r0.a;
            r0 = r27;
            r2 = r0.b;
            r0 = r28;
            r0.O = r2;
            goto L_0x0159;
        L_0x01f4:
            r2 = new com.google.android.exoplayer2.d.k;
            r0 = r28;
            r4 = r0.h;
            r2.<init>(r4);
            r9 = a(r2);
            if (r9 == 0) goto L_0x0207;
        L_0x0203:
            r3 = "video/wvc1";
            goto L_0x0159;
        L_0x0207:
            r3 = "video/x-unknown";
            goto L_0x0159;
        L_0x020b:
            r3 = "video/x-unknown";
            goto L_0x0159;
        L_0x020f:
            r3 = "audio/vorbis";
            r5 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
            r0 = r28;
            r2 = r0.h;
            r9 = a(r2);
            goto L_0x0159;
        L_0x021d:
            r3 = "audio/opus";
            r5 = 5760; // 0x1680 float:8.071E-42 double:2.846E-320;
            r9 = new java.util.ArrayList;
            r2 = 3;
            r9.<init>(r2);
            r0 = r28;
            r2 = r0.h;
            r9.add(r2);
            r2 = 8;
            r2 = java.nio.ByteBuffer.allocate(r2);
            r4 = java.nio.ByteOrder.nativeOrder();
            r2 = r2.order(r4);
            r0 = r28;
            r6 = r0.J;
            r2 = r2.putLong(r6);
            r2 = r2.array();
            r9.add(r2);
            r2 = 8;
            r2 = java.nio.ByteBuffer.allocate(r2);
            r4 = java.nio.ByteOrder.nativeOrder();
            r2 = r2.order(r4);
            r0 = r28;
            r6 = r0.K;
            r2 = r2.putLong(r6);
            r2 = r2.array();
            r9.add(r2);
            goto L_0x0159;
        L_0x026a:
            r3 = "audio/mp4a-latm";
            r0 = r28;
            r2 = r0.h;
            r9 = java.util.Collections.singletonList(r2);
            goto L_0x0159;
        L_0x0276:
            r3 = "audio/mpeg-L2";
            r5 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
            goto L_0x0159;
        L_0x027c:
            r3 = "audio/mpeg";
            r5 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
            goto L_0x0159;
        L_0x0282:
            r3 = "audio/ac3";
            goto L_0x0159;
        L_0x0286:
            r3 = "audio/eac3";
            goto L_0x0159;
        L_0x028a:
            r3 = "audio/true-hd";
            goto L_0x0159;
        L_0x028e:
            r3 = "audio/vnd.dts";
            goto L_0x0159;
        L_0x0292:
            r3 = "audio/vnd.dts.hd";
            goto L_0x0159;
        L_0x0296:
            r3 = "audio/x-flac";
            r0 = r28;
            r2 = r0.h;
            r9 = java.util.Collections.singletonList(r2);
            goto L_0x0159;
        L_0x02a2:
            r3 = "audio/raw";
            r2 = new com.google.android.exoplayer2.d.k;
            r0 = r28;
            r4 = r0.h;
            r2.<init>(r4);
            r2 = b(r2);
            if (r2 == 0) goto L_0x02da;
        L_0x02b3:
            r0 = r28;
            r2 = r0.H;
            r8 = com.google.android.exoplayer2.d.s.b(r2);
            if (r8 != 0) goto L_0x0159;
        L_0x02bd:
            r8 = -1;
            r3 = "audio/x-unknown";
            r2 = new java.lang.StringBuilder;
            r4 = "Unsupported PCM bit depth: ";
            r2.<init>(r4);
            r0 = r28;
            r4 = r0.H;
            r2 = r2.append(r4);
            r4 = ". Setting mimeType to ";
            r2 = r2.append(r4);
            r2.append(r3);
            goto L_0x0159;
        L_0x02da:
            r3 = "audio/x-unknown";
            goto L_0x0159;
        L_0x02de:
            r3 = "audio/raw";
            r0 = r28;
            r2 = r0.H;
            r8 = com.google.android.exoplayer2.d.s.b(r2);
            if (r8 != 0) goto L_0x0159;
        L_0x02ea:
            r8 = -1;
            r3 = "audio/x-unknown";
            r2 = new java.lang.StringBuilder;
            r4 = "Unsupported PCM bit depth: ";
            r2.<init>(r4);
            r0 = r28;
            r4 = r0.H;
            r2 = r2.append(r4);
            r4 = ". Setting mimeType to ";
            r2 = r2.append(r4);
            r2.append(r3);
            goto L_0x0159;
        L_0x0307:
            r3 = "application/x-subrip";
            goto L_0x0159;
        L_0x030b:
            r3 = "application/vobsub";
            r0 = r28;
            r2 = r0.h;
            r9 = java.util.Collections.singletonList(r2);
            goto L_0x0159;
        L_0x0317:
            r3 = "application/pgs";
            goto L_0x0159;
        L_0x031b:
            r3 = "application/dvbsubs";
            r2 = 4;
            r2 = new byte[r2];
            r4 = 0;
            r0 = r28;
            r6 = r0.h;
            r7 = 0;
            r6 = r6[r7];
            r2[r4] = r6;
            r4 = 1;
            r0 = r28;
            r6 = r0.h;
            r7 = 1;
            r6 = r6[r7];
            r2[r4] = r6;
            r4 = 2;
            r0 = r28;
            r6 = r0.h;
            r7 = 2;
            r6 = r6[r7];
            r2[r4] = r6;
            r4 = 3;
            r0 = r28;
            r6 = r0.h;
            r7 = 3;
            r6 = r6[r7];
            r2[r4] = r6;
            r9 = java.util.Collections.singletonList(r2);
            goto L_0x0159;
        L_0x034e:
            r2 = 0;
            goto L_0x0160;
        L_0x0351:
            r2 = 0;
            goto L_0x0169;
        L_0x0354:
            r2 = com.google.android.exoplayer2.d.h.b(r3);
            if (r2 == 0) goto L_0x0517;
        L_0x035a:
            r0 = r28;
            r2 = r0.n;
            if (r2 != 0) goto L_0x037e;
        L_0x0360:
            r0 = r28;
            r2 = r0.l;
            r4 = -1;
            if (r2 != r4) goto L_0x0453;
        L_0x0367:
            r0 = r28;
            r2 = r0.j;
        L_0x036b:
            r0 = r28;
            r0.l = r2;
            r0 = r28;
            r2 = r0.m;
            r4 = -1;
            if (r2 != r4) goto L_0x0459;
        L_0x0376:
            r0 = r28;
            r2 = r0.k;
        L_0x037a:
            r0 = r28;
            r0.m = r2;
        L_0x037e:
            r19 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r0 = r28;
            r2 = r0.l;
            r4 = -1;
            if (r2 == r4) goto L_0x03a4;
        L_0x0387:
            r0 = r28;
            r2 = r0.m;
            r4 = -1;
            if (r2 == r4) goto L_0x03a4;
        L_0x038e:
            r0 = r28;
            r2 = r0.k;
            r0 = r28;
            r4 = r0.l;
            r2 = r2 * r4;
            r2 = (float) r2;
            r0 = r28;
            r4 = r0.j;
            r0 = r28;
            r6 = r0.m;
            r4 = r4 * r6;
            r4 = (float) r4;
            r19 = r2 / r4;
        L_0x03a4:
            r22 = 0;
            r0 = r28;
            r2 = r0.q;
            if (r2 == 0) goto L_0x0427;
        L_0x03ac:
            r0 = r28;
            r2 = r0.w;
            r4 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 == 0) goto L_0x0410;
        L_0x03b6:
            r0 = r28;
            r2 = r0.x;
            r4 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 == 0) goto L_0x0410;
        L_0x03c0:
            r0 = r28;
            r2 = r0.y;
            r4 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 == 0) goto L_0x0410;
        L_0x03ca:
            r0 = r28;
            r2 = r0.z;
            r4 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 == 0) goto L_0x0410;
        L_0x03d4:
            r0 = r28;
            r2 = r0.A;
            r4 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 == 0) goto L_0x0410;
        L_0x03de:
            r0 = r28;
            r2 = r0.B;
            r4 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 == 0) goto L_0x0410;
        L_0x03e8:
            r0 = r28;
            r2 = r0.C;
            r4 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 == 0) goto L_0x0410;
        L_0x03f2:
            r0 = r28;
            r2 = r0.D;
            r4 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 == 0) goto L_0x0410;
        L_0x03fc:
            r0 = r28;
            r2 = r0.E;
            r4 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 == 0) goto L_0x0410;
        L_0x0406:
            r0 = r28;
            r2 = r0.F;
            r4 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 != 0) goto L_0x045f;
        L_0x0410:
            r26 = 0;
        L_0x0412:
            r22 = new com.google.android.exoplayer2.video.ColorInfo;
            r0 = r28;
            r2 = r0.r;
            r0 = r28;
            r4 = r0.t;
            r0 = r28;
            r6 = r0.s;
            r0 = r22;
            r1 = r26;
            r0.<init>(r2, r4, r6, r1);
        L_0x0427:
            r12 = java.lang.Integer.toString(r30);
            r0 = r28;
            r15 = r0.j;
            r0 = r28;
            r0 = r0.k;
            r16 = r0;
            r18 = -1;
            r0 = r28;
            r0 = r0.o;
            r20 = r0;
            r0 = r28;
            r0 = r0.p;
            r21 = r0;
            r0 = r28;
            r0 = r0.i;
            r23 = r0;
            r13 = r3;
            r14 = r5;
            r17 = r9;
            r25 = com.google.android.exoplayer2.Format.a(r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23);
            goto L_0x018a;
        L_0x0453:
            r0 = r28;
            r2 = r0.l;
            goto L_0x036b;
        L_0x0459:
            r0 = r28;
            r2 = r0.m;
            goto L_0x037a;
        L_0x045f:
            r2 = 25;
            r0 = new byte[r2];
            r26 = r0;
            r2 = java.nio.ByteBuffer.wrap(r26);
            r4 = 0;
            r2.put(r4);
            r0 = r28;
            r4 = r0.w;
            r6 = 1195593728; // 0x47435000 float:50000.0 double:5.907017874E-315;
            r4 = r4 * r6;
            r6 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
            r4 = r4 + r6;
            r4 = (int) r4;
            r4 = (short) r4;
            r2.putShort(r4);
            r0 = r28;
            r4 = r0.x;
            r6 = 1195593728; // 0x47435000 float:50000.0 double:5.907017874E-315;
            r4 = r4 * r6;
            r6 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
            r4 = r4 + r6;
            r4 = (int) r4;
            r4 = (short) r4;
            r2.putShort(r4);
            r0 = r28;
            r4 = r0.y;
            r6 = 1195593728; // 0x47435000 float:50000.0 double:5.907017874E-315;
            r4 = r4 * r6;
            r6 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
            r4 = r4 + r6;
            r4 = (int) r4;
            r4 = (short) r4;
            r2.putShort(r4);
            r0 = r28;
            r4 = r0.z;
            r6 = 1195593728; // 0x47435000 float:50000.0 double:5.907017874E-315;
            r4 = r4 * r6;
            r6 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
            r4 = r4 + r6;
            r4 = (int) r4;
            r4 = (short) r4;
            r2.putShort(r4);
            r0 = r28;
            r4 = r0.A;
            r6 = 1195593728; // 0x47435000 float:50000.0 double:5.907017874E-315;
            r4 = r4 * r6;
            r6 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
            r4 = r4 + r6;
            r4 = (int) r4;
            r4 = (short) r4;
            r2.putShort(r4);
            r0 = r28;
            r4 = r0.B;
            r6 = 1195593728; // 0x47435000 float:50000.0 double:5.907017874E-315;
            r4 = r4 * r6;
            r6 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
            r4 = r4 + r6;
            r4 = (int) r4;
            r4 = (short) r4;
            r2.putShort(r4);
            r0 = r28;
            r4 = r0.C;
            r6 = 1195593728; // 0x47435000 float:50000.0 double:5.907017874E-315;
            r4 = r4 * r6;
            r6 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
            r4 = r4 + r6;
            r4 = (int) r4;
            r4 = (short) r4;
            r2.putShort(r4);
            r0 = r28;
            r4 = r0.D;
            r6 = 1195593728; // 0x47435000 float:50000.0 double:5.907017874E-315;
            r4 = r4 * r6;
            r6 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
            r4 = r4 + r6;
            r4 = (int) r4;
            r4 = (short) r4;
            r2.putShort(r4);
            r0 = r28;
            r4 = r0.E;
            r6 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
            r4 = r4 + r6;
            r4 = (int) r4;
            r4 = (short) r4;
            r2.putShort(r4);
            r0 = r28;
            r4 = r0.F;
            r6 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
            r4 = r4 + r6;
            r4 = (int) r4;
            r4 = (short) r4;
            r2.putShort(r4);
            r0 = r28;
            r4 = r0.u;
            r4 = (short) r4;
            r2.putShort(r4);
            r0 = r28;
            r4 = r0.v;
            r4 = (short) r4;
            r2.putShort(r4);
            goto L_0x0412;
        L_0x0517:
            r2 = "application/x-subrip";
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0531;
        L_0x051f:
            r2 = java.lang.Integer.toString(r30);
            r0 = r28;
            r4 = r0.P;
            r0 = r28;
            r6 = r0.i;
            r25 = com.google.android.exoplayer2.Format.a(r2, r3, r11, r4, r6);
            goto L_0x018a;
        L_0x0531:
            r2 = "application/vobsub";
            r2 = r2.equals(r3);
            if (r2 != 0) goto L_0x0549;
        L_0x0539:
            r2 = "application/pgs";
            r2 = r2.equals(r3);
            if (r2 != 0) goto L_0x0549;
        L_0x0541:
            r2 = "application/dvbsubs";
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x055b;
        L_0x0549:
            r2 = java.lang.Integer.toString(r30);
            r0 = r28;
            r4 = r0.P;
            r0 = r28;
            r6 = r0.i;
            r25 = com.google.android.exoplayer2.Format.a(r2, r3, r9, r4, r6);
            goto L_0x018a;
        L_0x055b:
            r2 = new com.google.android.exoplayer2.k;
            r4 = "Unexpected MIME type.";
            r2.<init>(r4);
            throw r2;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.b.a(com.google.android.exoplayer2.extractor.h, int):void");
        }

        private static List<byte[]> a(k buffer) throws com.google.android.exoplayer2.k {
            try {
                buffer.d(16);
                if (buffer.m() != 826496599) {
                    return null;
                }
                int startOffset = buffer.d() + 20;
                byte[] bufferData = buffer.a;
                int offset = startOffset;
                while (offset < bufferData.length - 4) {
                    if (bufferData[offset] == (byte) 0 && bufferData[offset + 1] == (byte) 0 && bufferData[offset + 2] == (byte) 1 && bufferData[offset + 3] == (byte) 15) {
                        return Collections.singletonList(Arrays.copyOfRange(bufferData, offset, bufferData.length));
                    }
                    offset++;
                }
                throw new com.google.android.exoplayer2.k("Failed to find FourCC VC1 initialization data");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new com.google.android.exoplayer2.k("Error parsing FourCC VC1 codec private");
            }
        }

        private static List<byte[]> a(byte[] codecPrivate) throws com.google.android.exoplayer2.k {
            try {
                if (codecPrivate[0] != (byte) 2) {
                    throw new com.google.android.exoplayer2.k("Error parsing vorbis codec private");
                }
                int offset = 1;
                int vorbisInfoLength = 0;
                while (true) {
                    int offset2 = offset;
                    if (codecPrivate[offset2] == (byte) -1) {
                        vorbisInfoLength += 255;
                        offset = offset2 + 1;
                    } else {
                        offset = offset2 + 1;
                        vorbisInfoLength += codecPrivate[offset2];
                        int vorbisSkipLength = 0;
                        while (true) {
                            offset2 = offset;
                            if (codecPrivate[offset2] != (byte) -1) {
                                break;
                            }
                            vorbisSkipLength += 255;
                            offset = offset2 + 1;
                        }
                        offset = offset2 + 1;
                        vorbisSkipLength += codecPrivate[offset2];
                        if (codecPrivate[offset] != (byte) 1) {
                            throw new com.google.android.exoplayer2.k("Error parsing vorbis codec private");
                        }
                        byte[] vorbisInfo = new byte[vorbisInfoLength];
                        System.arraycopy(codecPrivate, offset, vorbisInfo, 0, vorbisInfoLength);
                        offset += vorbisInfoLength;
                        if (codecPrivate[offset] != (byte) 3) {
                            throw new com.google.android.exoplayer2.k("Error parsing vorbis codec private");
                        }
                        offset += vorbisSkipLength;
                        if (codecPrivate[offset] != (byte) 5) {
                            throw new com.google.android.exoplayer2.k("Error parsing vorbis codec private");
                        }
                        byte[] vorbisBooks = new byte[(codecPrivate.length - offset)];
                        System.arraycopy(codecPrivate, offset, vorbisBooks, 0, codecPrivate.length - offset);
                        List<byte[]> initializationData = new ArrayList(2);
                        initializationData.add(vorbisInfo);
                        initializationData.add(vorbisBooks);
                        return initializationData;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new com.google.android.exoplayer2.k("Error parsing vorbis codec private");
            }
        }

        private static boolean b(k buffer) throws com.google.android.exoplayer2.k {
            try {
                int formatTag = buffer.i();
                if (formatTag == 1) {
                    return true;
                }
                if (formatTag != 65534) {
                    return false;
                }
                buffer.c(24);
                if (buffer.p() == MatroskaExtractor.d.getMostSignificantBits() && buffer.p() == MatroskaExtractor.d.getLeastSignificantBits()) {
                    return true;
                }
                return false;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new com.google.android.exoplayer2.k("Error parsing MS/ACM codec private");
            }
        }
    }

    public MatroskaExtractor() {
        this(0);
    }

    public MatroskaExtractor(int flags) {
        this(new a(), flags);
    }

    private MatroskaExtractor(b reader, int flags) {
        boolean z = false;
        this.t = -1;
        this.u = -9223372036854775807L;
        this.v = -9223372036854775807L;
        this.w = -9223372036854775807L;
        this.C = -1;
        this.D = -1;
        this.E = -9223372036854775807L;
        this.e = reader;
        this.e.a(new a());
        if ((flags & 1) == 0) {
            z = true;
        }
        this.h = z;
        this.f = new e();
        this.g = new SparseArray();
        this.k = new k(4);
        this.l = new k(ByteBuffer.allocate(4).putInt(-1).array());
        this.m = new k(4);
        this.i = new k(com.google.android.exoplayer2.d.i.a);
        this.j = new k(4);
        this.n = new k();
        this.o = new k();
        this.p = new k(8);
        this.q = new k();
    }

    public final boolean a(g input) throws IOException, InterruptedException {
        return new d().a(input);
    }

    public final void a(h output) {
        this.ac = output;
    }

    public final void a(long position, long timeUs) {
        this.E = -9223372036854775807L;
        this.I = 0;
        this.e.a();
        this.f.a();
        b();
    }

    public final int a(g input, l seekPosition) throws IOException, InterruptedException {
        this.aa = false;
        boolean continueReading = true;
        while (continueReading && !this.aa) {
            continueReading = this.e.a(input);
            if (continueReading) {
                int i;
                long c = input.c();
                if (this.B) {
                    this.D = c;
                    seekPosition.a = this.C;
                    this.B = false;
                    i = 1;
                } else if (!this.y || this.D == -1) {
                    boolean i2 = false;
                } else {
                    seekPosition.a = this.D;
                    this.D = -1;
                    i2 = 1;
                }
                if (i2 != 0) {
                    return 1;
                }
            }
        }
        return continueReading ? 0 : -1;
    }

    static int a(int id) {
        switch (id) {
            case 131:
            case 136:
            case 155:
            case 159:
            case 176:
            case 179:
            case 186:
            case 215:
            case 231:
            case 241:
            case 251:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21432:
            case 21680:
            case 21682:
            case 21690:
            case 21930:
            case 21945:
            case 21946:
            case 21947:
            case 21948:
            case 21949:
            case 22186:
            case 22203:
            case 25188:
            case 2352003:
            case 2807729:
                return 2;
            case 134:
            case 17026:
            case 2274716:
                return 3;
            case 160:
            case 174:
            case 183:
            case 187:
            case 224:
            case 225:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 21936:
            case 21968:
            case 25152:
            case 28032:
            case 30320:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317:
                return 1;
            case 161:
            case 163:
            case 16981:
            case 18402:
            case 21419:
            case 25506:
            case 30322:
                return 4;
            case 181:
            case 17545:
            case 21969:
            case 21970:
            case 21971:
            case 21972:
            case 21973:
            case 21974:
            case 21975:
            case 21976:
            case 21977:
            case 21978:
                return 5;
            default:
                return 0;
        }
    }

    static boolean b(int id) {
        return id == 357149030 || id == 524531317 || id == 475249515 || id == 374648427;
    }

    final void a(int id, long contentPosition, long contentSize) throws com.google.android.exoplayer2.k {
        switch (id) {
            case 160:
                this.ab = false;
                return;
            case 174:
                this.x = new b();
                return;
            case 187:
                this.H = false;
                return;
            case 19899:
                this.z = -1;
                this.A = -1;
                return;
            case 20533:
                this.x.e = true;
                return;
            case 21968:
                this.x.q = true;
                return;
            case 408125543:
                if (this.t == -1 || this.t == contentPosition) {
                    this.t = contentPosition;
                    this.s = contentSize;
                    return;
                }
                throw new com.google.android.exoplayer2.k("Multiple Segment elements not supported");
            case 475249515:
                this.F = new com.google.android.exoplayer2.d.f();
                this.G = new com.google.android.exoplayer2.d.f();
                return;
            case 524531317:
                if (!this.y) {
                    if (!this.h || this.C == -1) {
                        this.ac.a(new com.google.android.exoplayer2.extractor.m.a(this.w));
                        this.y = true;
                        return;
                    }
                    this.B = true;
                    return;
                }
                return;
            default:
                return;
        }
    }

    final void c(int id) throws com.google.android.exoplayer2.k {
        switch (id) {
            case 160:
                if (this.I == 2) {
                    if (!this.ab) {
                        this.Q |= 1;
                    }
                    a((b) this.g.get(this.O), this.J);
                    this.I = 0;
                    return;
                }
                return;
            case 174:
                Object obj;
                String str = this.x.a;
                if ("V_VP8".equals(str) || "V_VP9".equals(str) || "V_MPEG2".equals(str) || "V_MPEG4/ISO/SP".equals(str) || "V_MPEG4/ISO/ASP".equals(str) || "V_MPEG4/ISO/AP".equals(str) || "V_MPEG4/ISO/AVC".equals(str) || "V_MPEGH/ISO/HEVC".equals(str) || "V_MS/VFW/FOURCC".equals(str) || "V_THEORA".equals(str) || "A_OPUS".equals(str) || "A_VORBIS".equals(str) || "A_AAC".equals(str) || "A_MPEG/L2".equals(str) || "A_MPEG/L3".equals(str) || "A_AC3".equals(str) || "A_EAC3".equals(str) || "A_TRUEHD".equals(str) || "A_DTS".equals(str) || "A_DTS/EXPRESS".equals(str) || "A_DTS/LOSSLESS".equals(str) || "A_FLAC".equals(str) || "A_MS/ACM".equals(str) || "A_PCM/INT/LIT".equals(str) || "S_TEXT/UTF8".equals(str) || "S_VOBSUB".equals(str) || "S_HDMV/PGS".equals(str) || "S_DVBSUB".equals(str)) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    this.x.a(this.ac, this.x.b);
                    this.g.put(this.x.b, this.x);
                }
                this.x = null;
                return;
            case 19899:
                if (this.z == -1 || this.A == -1) {
                    throw new com.google.android.exoplayer2.k("Mandatory element SeekID or SeekPosition not found");
                } else if (this.z == 475249515) {
                    this.C = this.A;
                    return;
                } else {
                    return;
                }
            case 25152:
                if (!this.x.e) {
                    return;
                }
                if (this.x.g == null) {
                    throw new com.google.android.exoplayer2.k("Encrypted Track found but ContentEncKeyID was not found");
                }
                this.x.i = new DrmInitData(new SchemeData(C.b, "video/webm", this.x.g));
                return;
            case 28032:
                if (this.x.e && this.x.f != null) {
                    throw new com.google.android.exoplayer2.k("Combining encryption and compression is not supported");
                }
                return;
            case 357149030:
                if (this.u == -9223372036854775807L) {
                    this.u = 1000000;
                }
                if (this.v != -9223372036854775807L) {
                    this.w = a(this.v);
                    return;
                }
                return;
            case 374648427:
                if (this.g.size() == 0) {
                    throw new com.google.android.exoplayer2.k("No valid tracks were found");
                }
                this.ac.b();
                return;
            case 475249515:
                if (!this.y) {
                    m aVar;
                    h hVar = this.ac;
                    if (this.t == -1 || this.w == -9223372036854775807L || this.F == null || this.F.a() == 0 || this.G == null || this.G.a() != this.F.a()) {
                        this.F = null;
                        this.G = null;
                        aVar = new com.google.android.exoplayer2.extractor.m.a(this.w);
                    } else {
                        int i;
                        int a = this.F.a();
                        int[] iArr = new int[a];
                        long[] jArr = new long[a];
                        long[] jArr2 = new long[a];
                        long[] jArr3 = new long[a];
                        for (i = 0; i < a; i++) {
                            jArr3[i] = this.F.a(i);
                            jArr[i] = this.t + this.G.a(i);
                        }
                        for (i = 0; i < a - 1; i++) {
                            iArr[i] = (int) (jArr[i + 1] - jArr[i]);
                            jArr2[i] = jArr3[i + 1] - jArr3[i];
                        }
                        iArr[a - 1] = (int) ((this.t + this.s) - jArr[a - 1]);
                        jArr2[a - 1] = this.w - jArr3[a - 1];
                        this.F = null;
                        this.G = null;
                        aVar = new com.google.android.exoplayer2.extractor.a(iArr, jArr, jArr2, jArr3);
                    }
                    hVar.a(aVar);
                    this.y = true;
                    return;
                }
                return;
            default:
                return;
        }
    }

    final void a(int id, long value) throws com.google.android.exoplayer2.k {
        boolean z = true;
        b bVar;
        switch (id) {
            case 131:
                this.x.c = (int) value;
                return;
            case 136:
                bVar = this.x;
                if (value != 1) {
                    z = false;
                }
                bVar.L = z;
                return;
            case 155:
                this.K = a(value);
                return;
            case 159:
                this.x.G = (int) value;
                return;
            case 176:
                this.x.j = (int) value;
                return;
            case 179:
                this.F.a(a(value));
                return;
            case 186:
                this.x.k = (int) value;
                return;
            case 215:
                this.x.b = (int) value;
                return;
            case 231:
                this.E = a(value);
                return;
            case 241:
                if (!this.H) {
                    this.G.a(value);
                    this.H = true;
                    return;
                }
                return;
            case 251:
                this.ab = true;
                return;
            case 16980:
                if (value != 3) {
                    throw new com.google.android.exoplayer2.k("ContentCompAlgo " + value + " not supported");
                }
                return;
            case 17029:
                if (value < 1 || value > 2) {
                    throw new com.google.android.exoplayer2.k("DocTypeReadVersion " + value + " not supported");
                }
                return;
            case 17143:
                if (value != 1) {
                    throw new com.google.android.exoplayer2.k("EBMLReadVersion " + value + " not supported");
                }
                return;
            case 18401:
                if (value != 5) {
                    throw new com.google.android.exoplayer2.k("ContentEncAlgo " + value + " not supported");
                }
                return;
            case 18408:
                if (value != 1) {
                    throw new com.google.android.exoplayer2.k("AESSettingsCipherMode " + value + " not supported");
                }
                return;
            case 20529:
                if (value != 0) {
                    throw new com.google.android.exoplayer2.k("ContentEncodingOrder " + value + " not supported");
                }
                return;
            case 20530:
                if (value != 1) {
                    throw new com.google.android.exoplayer2.k("ContentEncodingScope " + value + " not supported");
                }
                return;
            case 21420:
                this.A = this.t + value;
                return;
            case 21432:
                switch ((int) value) {
                    case 0:
                        this.x.p = 0;
                        return;
                    case 1:
                        this.x.p = 2;
                        return;
                    case 3:
                        this.x.p = 1;
                        return;
                    case 15:
                        this.x.p = 3;
                        return;
                    default:
                        return;
                }
            case 21680:
                this.x.l = (int) value;
                return;
            case 21682:
                this.x.n = (int) value;
                return;
            case 21690:
                this.x.m = (int) value;
                return;
            case 21930:
                bVar = this.x;
                if (value != 1) {
                    z = false;
                }
                bVar.M = z;
                return;
            case 21945:
                switch ((int) value) {
                    case 1:
                        this.x.t = 2;
                        return;
                    case 2:
                        this.x.t = 1;
                        return;
                    default:
                        return;
                }
            case 21946:
                switch ((int) value) {
                    case 1:
                    case 6:
                    case 7:
                        this.x.s = 3;
                        return;
                    case 16:
                        this.x.s = 6;
                        return;
                    case 18:
                        this.x.s = 7;
                        return;
                    default:
                        return;
                }
            case 21947:
                this.x.q = true;
                switch ((int) value) {
                    case 1:
                        this.x.r = 1;
                        return;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        this.x.r = 2;
                        return;
                    case 9:
                        this.x.r = 6;
                        return;
                    default:
                        return;
                }
            case 21948:
                this.x.u = (int) value;
                return;
            case 21949:
                this.x.v = (int) value;
                return;
            case 22186:
                this.x.J = value;
                return;
            case 22203:
                this.x.K = value;
                return;
            case 25188:
                this.x.H = (int) value;
                return;
            case 2352003:
                this.x.d = (int) value;
                return;
            case 2807729:
                this.u = value;
                return;
            default:
                return;
        }
    }

    final void a(int id, double value) {
        switch (id) {
            case 181:
                this.x.I = (int) value;
                return;
            case 17545:
                this.v = (long) value;
                return;
            case 21969:
                this.x.w = (float) value;
                return;
            case 21970:
                this.x.x = (float) value;
                return;
            case 21971:
                this.x.y = (float) value;
                return;
            case 21972:
                this.x.z = (float) value;
                return;
            case 21973:
                this.x.A = (float) value;
                return;
            case 21974:
                this.x.B = (float) value;
                return;
            case 21975:
                this.x.C = (float) value;
                return;
            case 21976:
                this.x.D = (float) value;
                return;
            case 21977:
                this.x.E = (float) value;
                return;
            case 21978:
                this.x.F = (float) value;
                return;
            default:
                return;
        }
    }

    final void a(int id, String value) throws com.google.android.exoplayer2.k {
        switch (id) {
            case 134:
                this.x.a = value;
                return;
            case 17026:
                if (!"webm".equals(value) && !"matroska".equals(value)) {
                    throw new com.google.android.exoplayer2.k("DocType " + value + " not supported");
                }
                return;
            case 2274716:
                this.x.P = value;
                return;
            default:
                return;
        }
    }

    final void a(int id, int contentSize, g input) throws IOException, InterruptedException {
        switch (id) {
            case 161:
            case 163:
                if (this.I == 0) {
                    this.O = (int) this.f.a(input, false, true, 8);
                    this.P = this.f.b();
                    this.K = -9223372036854775807L;
                    this.I = 1;
                    this.k.a();
                }
                b track = (b) this.g.get(this.O);
                if (track == null) {
                    input.b(contentSize - this.P);
                    this.I = 0;
                    return;
                }
                if (this.I == 1) {
                    int i;
                    int i2;
                    a(input, 3);
                    int lacing = (this.k.a[2] & 6) >> 1;
                    if (lacing == 0) {
                        this.M = 1;
                        this.N = a(this.N, 1);
                        this.N[0] = (contentSize - this.P) - 3;
                    } else if (id != 163) {
                        throw new com.google.android.exoplayer2.k("Lacing only supported in SimpleBlocks.");
                    } else {
                        a(input, 4);
                        this.M = (this.k.a[3] & 255) + 1;
                        this.N = a(this.N, this.M);
                        int totalSamplesSize;
                        int headerSize;
                        int sampleIndex;
                        int[] iArr;
                        if (lacing == 2) {
                            Arrays.fill(this.N, 0, this.M, ((contentSize - this.P) - 4) / this.M);
                        } else if (lacing == 1) {
                            totalSamplesSize = 0;
                            headerSize = 4;
                            for (sampleIndex = 0; sampleIndex < this.M - 1; sampleIndex++) {
                                this.N[sampleIndex] = 0;
                                int byteValue;
                                do {
                                    headerSize++;
                                    a(input, headerSize);
                                    byteValue = this.k.a[headerSize - 1] & 255;
                                    iArr = this.N;
                                    iArr[sampleIndex] = iArr[sampleIndex] + byteValue;
                                } while (byteValue == 255);
                                totalSamplesSize += this.N[sampleIndex];
                            }
                            this.N[this.M - 1] = ((contentSize - this.P) - headerSize) - totalSamplesSize;
                        } else if (lacing == 3) {
                            totalSamplesSize = 0;
                            headerSize = 4;
                            sampleIndex = 0;
                            while (sampleIndex < this.M - 1) {
                                this.N[sampleIndex] = 0;
                                headerSize++;
                                a(input, headerSize);
                                if (this.k.a[headerSize - 1] == (byte) 0) {
                                    throw new com.google.android.exoplayer2.k("No valid varint length mask found");
                                }
                                long readValue = 0;
                                int i3 = 0;
                                while (i3 < 8) {
                                    int lengthMask = 1 << (7 - i3);
                                    if ((this.k.a[headerSize - 1] & lengthMask) != 0) {
                                        int readPosition = headerSize - 1;
                                        headerSize += i3;
                                        a(input, headerSize);
                                        readValue = (long) ((this.k.a[readPosition] & 255) & (lengthMask ^ -1));
                                        for (int readPosition2 = readPosition + 1; readPosition2 < headerSize; readPosition2++) {
                                            readValue = (readValue << 8) | ((long) (this.k.a[readPosition2] & 255));
                                        }
                                        if (sampleIndex > 0) {
                                            readValue -= (1 << ((i3 * 7) + 6)) - 1;
                                        }
                                        if (readValue >= -2147483648L || readValue > 2147483647L) {
                                            throw new com.google.android.exoplayer2.k("EBML lacing sample size out of range.");
                                        }
                                        int intReadValue = (int) readValue;
                                        iArr = this.N;
                                        if (sampleIndex != 0) {
                                            intReadValue += this.N[sampleIndex - 1];
                                        }
                                        iArr[sampleIndex] = intReadValue;
                                        totalSamplesSize += this.N[sampleIndex];
                                        sampleIndex++;
                                    } else {
                                        i3++;
                                    }
                                }
                                if (readValue >= -2147483648L) {
                                    break;
                                }
                                throw new com.google.android.exoplayer2.k("EBML lacing sample size out of range.");
                            }
                            this.N[this.M - 1] = ((contentSize - this.P) - headerSize) - totalSamplesSize;
                        } else {
                            throw new com.google.android.exoplayer2.k("Unexpected lacing value: " + lacing);
                        }
                    }
                    this.J = this.E + a((long) ((this.k.a[0] << 8) | (this.k.a[1] & 255)));
                    boolean isInvisible = (this.k.a[2] & 8) == 8;
                    boolean isKeyframe = track.c == 2 || (id == 163 && (this.k.a[2] & 128) == 128);
                    if (isKeyframe) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if (isInvisible) {
                        i2 = Integer.MIN_VALUE;
                    } else {
                        i2 = 0;
                    }
                    this.Q = i2 | i;
                    this.I = 2;
                    this.L = 0;
                }
                if (id == 163) {
                    while (this.L < this.M) {
                        a(input, track, this.N[this.L]);
                        a(track, this.J + ((long) ((this.L * track.d) / Constants.ONE_SECOND)));
                        this.L++;
                    }
                    this.I = 0;
                    return;
                }
                a(input, track, this.N[0]);
                return;
            case 16981:
                this.x.f = new byte[contentSize];
                input.b(this.x.f, 0, contentSize);
                return;
            case 18402:
                this.x.g = new byte[contentSize];
                input.b(this.x.g, 0, contentSize);
                return;
            case 21419:
                Arrays.fill(this.m.a, (byte) 0);
                input.b(this.m.a, 4 - contentSize, contentSize);
                this.m.c(0);
                this.z = (int) this.m.l();
                return;
            case 25506:
                this.x.h = new byte[contentSize];
                input.b(this.x.h, 0, contentSize);
                return;
            case 30322:
                this.x.o = new byte[contentSize];
                input.b(this.x.o, 0, contentSize);
                return;
            default:
                throw new com.google.android.exoplayer2.k("Unexpected id: " + id);
        }
    }

    private void a(b track, long timeUs) {
        if ("S_TEXT/UTF8".equals(track.a)) {
            Object obj;
            Object obj2 = this.o.a;
            long j = this.K;
            if (j == -9223372036854775807L) {
                obj = c;
            } else {
                j -= ((long) ((int) (j / 3600000000L))) * 3600000000L;
                j -= (long) (60000000 * ((int) (j / 60000000)));
                int i = (int) ((j - ((long) (1000000 * ((int) (j / 1000000))))) / 1000);
                obj = s.c(String.format(Locale.US, "%02d:%02d:%02d,%03d", new Object[]{Integer.valueOf(r0), Integer.valueOf(r4), Integer.valueOf(r5), Integer.valueOf(i)}));
            }
            System.arraycopy(obj, 0, obj2, 19, 12);
            track.N.a(this.o, this.o.c());
            this.Z += this.o.c();
        }
        track.N.a(timeUs, this.Q, this.Z, 0, track.g);
        this.aa = true;
        b();
    }

    private void b() {
        this.R = 0;
        this.Z = 0;
        this.Y = 0;
        this.S = false;
        this.T = false;
        this.V = false;
        this.X = 0;
        this.W = (byte) 0;
        this.U = false;
        this.n.a();
    }

    private void a(g input, int requiredLength) throws IOException, InterruptedException {
        if (this.k.c() < requiredLength) {
            if (this.k.e() < requiredLength) {
                this.k.a(Arrays.copyOf(this.k.a, Math.max(this.k.a.length * 2, requiredLength)), this.k.c());
            }
            input.b(this.k.a, this.k.c(), requiredLength - this.k.c());
            this.k.b(requiredLength);
        }
    }

    private void a(g input, b track, int size) throws IOException, InterruptedException {
        if ("S_TEXT/UTF8".equals(track.a)) {
            int sizeWithPrefix = b.length + size;
            if (this.o.e() < sizeWithPrefix) {
                this.o.a = Arrays.copyOf(b, sizeWithPrefix + size);
            }
            input.b(this.o.a, b.length, size);
            this.o.c(0);
            this.o.b(sizeWithPrefix);
            return;
        }
        n output = track.N;
        if (!this.S) {
            if (track.e) {
                this.Q &= -1073741825;
                if (!this.T) {
                    input.b(this.k.a, 0, 1);
                    this.R++;
                    if ((this.k.a[0] & 128) == 128) {
                        throw new com.google.android.exoplayer2.k("Extension bit is set in signal byte");
                    }
                    this.W = this.k.a[0];
                    this.T = true;
                }
                if (((this.W & 1) == 1 ? 1 : null) != null) {
                    boolean hasSubsampleEncryption = (this.W & 2) == 2;
                    this.Q |= ErrorDialogData.SUPPRESSED;
                    if (!this.U) {
                        input.b(this.p.a, 0, 8);
                        this.R += 8;
                        this.U = true;
                        this.k.a[0] = (byte) ((hasSubsampleEncryption ? 128 : 0) | 8);
                        this.k.c(0);
                        output.a(this.k, 1);
                        this.Z++;
                        this.p.c(0);
                        output.a(this.p, 8);
                        this.Z += 8;
                    }
                    if (hasSubsampleEncryption) {
                        if (!this.V) {
                            input.b(this.k.a, 0, 1);
                            this.R++;
                            this.k.c(0);
                            this.X = this.k.g();
                            this.V = true;
                        }
                        int samplePartitionDataSize = this.X * 4;
                        this.k.a(samplePartitionDataSize);
                        input.b(this.k.a, 0, samplePartitionDataSize);
                        this.R += samplePartitionDataSize;
                        short subsampleCount = (short) ((this.X / 2) + 1);
                        int subsampleDataSize = (subsampleCount * 6) + 2;
                        if (this.r == null || this.r.capacity() < subsampleDataSize) {
                            this.r = ByteBuffer.allocate(subsampleDataSize);
                        }
                        this.r.position(0);
                        this.r.putShort(subsampleCount);
                        int partitionOffset = 0;
                        for (int i = 0; i < this.X; i++) {
                            int previousPartitionOffset = partitionOffset;
                            partitionOffset = this.k.t();
                            if (i % 2 == 0) {
                                this.r.putShort((short) (partitionOffset - previousPartitionOffset));
                            } else {
                                this.r.putInt(partitionOffset - previousPartitionOffset);
                            }
                        }
                        int finalPartitionSize = (size - this.R) - partitionOffset;
                        if (this.X % 2 == 1) {
                            this.r.putInt(finalPartitionSize);
                        } else {
                            this.r.putShort((short) finalPartitionSize);
                            this.r.putInt(0);
                        }
                        this.q.a(this.r.array(), subsampleDataSize);
                        output.a(this.q, subsampleDataSize);
                        this.Z += subsampleDataSize;
                    }
                }
            } else if (track.f != null) {
                this.n.a(track.f, track.f.length);
            }
            this.S = true;
        }
        size += this.n.c();
        if ("V_MPEG4/ISO/AVC".equals(track.a) || "V_MPEGH/ISO/HEVC".equals(track.a)) {
            byte[] nalLengthData = this.j.a;
            nalLengthData[0] = (byte) 0;
            nalLengthData[1] = (byte) 0;
            nalLengthData[2] = (byte) 0;
            int nalUnitLengthFieldLength = track.O;
            int nalUnitLengthFieldLengthDiff = 4 - track.O;
            while (this.R < size) {
                if (this.Y == 0) {
                    int min = Math.min(nalUnitLengthFieldLength, this.n.b());
                    input.b(nalLengthData, nalUnitLengthFieldLengthDiff + min, nalUnitLengthFieldLength - min);
                    if (min > 0) {
                        this.n.a(nalLengthData, nalUnitLengthFieldLengthDiff, min);
                    }
                    this.R += nalUnitLengthFieldLength;
                    this.j.c(0);
                    this.Y = this.j.t();
                    this.i.c(0);
                    output.a(this.i, 4);
                    this.Z += 4;
                } else {
                    this.Y -= a(input, output, this.Y);
                }
            }
        } else {
            while (this.R < size) {
                a(input, output, size - this.R);
            }
        }
        if ("A_VORBIS".equals(track.a)) {
            this.l.c(0);
            output.a(this.l, 4);
            this.Z += 4;
        }
    }

    private int a(g input, n output, int length) throws IOException, InterruptedException {
        int bytesRead;
        int strippedBytesLeft = this.n.b();
        if (strippedBytesLeft > 0) {
            bytesRead = Math.min(length, strippedBytesLeft);
            output.a(this.n, bytesRead);
        } else {
            bytesRead = output.a(input, length, false);
        }
        this.R += bytesRead;
        this.Z += bytesRead;
        return bytesRead;
    }

    private long a(long unscaledTimecode) throws com.google.android.exoplayer2.k {
        if (this.u == -9223372036854775807L) {
            throw new com.google.android.exoplayer2.k("Can't scale timecode prior to timecodeScale being set.");
        }
        return s.a(unscaledTimecode, this.u, 1000);
    }

    private static int[] a(int[] array, int length) {
        if (array == null) {
            return new int[length];
        }
        return array.length < length ? new int[Math.max(array.length * 2, length)] : array;
    }
}
