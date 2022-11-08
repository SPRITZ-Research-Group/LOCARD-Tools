package androidx.constraintlayout.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.android.gms.common.api.Api.BaseClientBuilder;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.co;
import defpackage.dc;
import defpackage.de;
import defpackage.dg;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstraintLayout extends ViewGroup {
    SparseArray<View> a = new SparseArray();
    de b = new de();
    int c = -1;
    int d = -1;
    int e = 0;
    int f = 0;
    private ArrayList<ConstraintHelper> g = new ArrayList(4);
    private final ArrayList<dc> h = new ArrayList(100);
    private int i = 0;
    private int j = 0;
    private int k = BaseClientBuilder.API_PRIORITY_OTHER;
    private int l = BaseClientBuilder.API_PRIORITY_OTHER;
    private boolean m = true;
    private int n = 7;
    private b o = null;
    private int p = -1;
    private HashMap<String, Integer> q = new HashMap();
    private int r = -1;
    private int s = -1;
    private co t;

    public class LayoutParams extends MarginLayoutParams {
        public float A = 0.5f;
        public String B = null;
        float C = BitmapDescriptorFactory.HUE_RED;
        int D = 1;
        public float E = -1.0f;
        public float F = -1.0f;
        public int G = 0;
        public int H = 0;
        public int I = 0;
        public int J = 0;
        public int K = 0;
        public int L = 0;
        public int M = 0;
        public int N = 0;
        public float O = 1.0f;
        public float P = 1.0f;
        public int Q = -1;
        public int R = -1;
        public int S = -1;
        public boolean T = false;
        public boolean U = false;
        boolean V = true;
        boolean W = true;
        boolean X = false;
        boolean Y = false;
        boolean Z = false;
        public int a = -1;
        boolean aa = false;
        int ab = -1;
        int ac = -1;
        int ad = -1;
        int ae = -1;
        int af = -1;
        int ag = -1;
        float ah = 0.5f;
        int ai;
        int aj;
        float ak;
        dc al = new dc();
        public boolean am = false;
        public int b = -1;
        public float c = -1.0f;
        public int d = -1;
        public int e = -1;
        public int f = -1;
        public int g = -1;
        public int h = -1;
        public int i = -1;
        public int j = -1;
        public int k = -1;
        public int l = -1;
        public int m = -1;
        public int n = 0;
        public float o = BitmapDescriptorFactory.HUE_RED;
        public int p = -1;
        public int q = -1;
        public int r = -1;
        public int s = -1;
        public int t = -1;
        public int u = -1;
        public int v = -1;
        public int w = -1;
        public int x = -1;
        public int y = -1;
        public float z = 0.5f;

        public LayoutParams(android.content.Context r10, android.util.AttributeSet r11) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.<init>(android.content.Context, android.util.AttributeSet):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
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
            r9 = this;
            r9.<init>(r10, r11);
            r0 = -1;
            r9.a = r0;
            r9.b = r0;
            r1 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r9.c = r1;
            r9.d = r0;
            r9.e = r0;
            r9.f = r0;
            r9.g = r0;
            r9.h = r0;
            r9.i = r0;
            r9.j = r0;
            r9.k = r0;
            r9.l = r0;
            r9.m = r0;
            r2 = 0;
            r9.n = r2;
            r3 = 0;
            r9.o = r3;
            r9.p = r0;
            r9.q = r0;
            r9.r = r0;
            r9.s = r0;
            r9.t = r0;
            r9.u = r0;
            r9.v = r0;
            r9.w = r0;
            r9.x = r0;
            r9.y = r0;
            r4 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
            r9.z = r4;
            r9.A = r4;
            r5 = 0;
            r9.B = r5;
            r9.C = r3;
            r5 = 1;
            r9.D = r5;
            r9.E = r1;
            r9.F = r1;
            r9.G = r2;
            r9.H = r2;
            r9.I = r2;
            r9.J = r2;
            r9.K = r2;
            r9.L = r2;
            r9.M = r2;
            r9.N = r2;
            r1 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
            r9.O = r1;
            r9.P = r1;
            r9.Q = r0;
            r9.R = r0;
            r9.S = r0;
            r9.T = r2;
            r9.U = r2;
            r9.V = r5;
            r9.W = r5;
            r9.X = r2;
            r9.Y = r2;
            r9.Z = r2;
            r9.aa = r2;
            r9.ab = r0;
            r9.ac = r0;
            r9.ad = r0;
            r9.ae = r0;
            r9.af = r0;
            r9.ag = r0;
            r9.ah = r4;
            r1 = new dc;
            r1.<init>();
            r9.al = r1;
            r9.am = r2;
            r1 = androidx.constraintlayout.widget.f.ConstraintLayout_Layout;
            r10 = r10.obtainStyledAttributes(r11, r1);
            r11 = r10.getIndexCount();
            r1 = 0;
        L_0x009a:
            if (r1 >= r11) goto L_0x03ec;
        L_0x009c:
            r4 = r10.getIndex(r1);
            r6 = androidx.constraintlayout.widget.a.a;
            r6 = r6.get(r4);
            r7 = -2;
            switch(r6) {
                case 0: goto L_0x03e8;
                case 1: goto L_0x03e0;
                case 2: goto L_0x03cd;
                case 3: goto L_0x03c4;
                case 4: goto L_0x03ab;
                case 5: goto L_0x03a2;
                case 6: goto L_0x0399;
                case 7: goto L_0x0390;
                case 8: goto L_0x037d;
                case 9: goto L_0x036a;
                case 10: goto L_0x0356;
                case 11: goto L_0x0342;
                case 12: goto L_0x032e;
                case 13: goto L_0x031a;
                case 14: goto L_0x0306;
                case 15: goto L_0x02f2;
                case 16: goto L_0x02de;
                case 17: goto L_0x02ca;
                case 18: goto L_0x02b6;
                case 19: goto L_0x02a2;
                case 20: goto L_0x028e;
                case 21: goto L_0x0284;
                case 22: goto L_0x027a;
                case 23: goto L_0x0270;
                case 24: goto L_0x0266;
                case 25: goto L_0x025c;
                case 26: goto L_0x0252;
                case 27: goto L_0x0248;
                case 28: goto L_0x023e;
                case 29: goto L_0x0234;
                case 30: goto L_0x022a;
                case 31: goto L_0x0217;
                case 32: goto L_0x0204;
                case 33: goto L_0x01ed;
                case 34: goto L_0x01d6;
                case 35: goto L_0x01c8;
                case 36: goto L_0x01b1;
                case 37: goto L_0x019a;
                case 38: goto L_0x018c;
                default: goto L_0x00aa;
            };
        L_0x00aa:
            switch(r6) {
                case 44: goto L_0x00e7;
                case 45: goto L_0x00dd;
                case 46: goto L_0x00d3;
                case 47: goto L_0x00cb;
                case 48: goto L_0x00c3;
                case 49: goto L_0x00b9;
                case 50: goto L_0x00af;
                default: goto L_0x00ad;
            };
        L_0x00ad:
            goto L_0x03e8;
        L_0x00af:
            r6 = r9.R;
            r4 = r10.getDimensionPixelOffset(r4, r6);
            r9.R = r4;
            goto L_0x03e8;
        L_0x00b9:
            r6 = r9.Q;
            r4 = r10.getDimensionPixelOffset(r4, r6);
            r9.Q = r4;
            goto L_0x03e8;
        L_0x00c3:
            r4 = r10.getInt(r4, r2);
            r9.H = r4;
            goto L_0x03e8;
        L_0x00cb:
            r4 = r10.getInt(r4, r2);
            r9.G = r4;
            goto L_0x03e8;
        L_0x00d3:
            r6 = r9.F;
            r4 = r10.getFloat(r4, r6);
            r9.F = r4;
            goto L_0x03e8;
        L_0x00dd:
            r6 = r9.E;
            r4 = r10.getFloat(r4, r6);
            r9.E = r4;
            goto L_0x03e8;
        L_0x00e7:
            r4 = r10.getString(r4);
            r9.B = r4;
            r4 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
            r9.C = r4;
            r9.D = r0;
            r4 = r9.B;
            if (r4 == 0) goto L_0x03e8;
        L_0x00f7:
            r4 = r9.B;
            r4 = r4.length();
            r6 = r9.B;
            r7 = 44;
            r6 = r6.indexOf(r7);
            if (r6 <= 0) goto L_0x0129;
        L_0x0107:
            r7 = r4 + -1;
            if (r6 >= r7) goto L_0x0129;
        L_0x010b:
            r7 = r9.B;
            r7 = r7.substring(r2, r6);
            r8 = "W";
            r8 = r7.equalsIgnoreCase(r8);
            if (r8 == 0) goto L_0x011c;
        L_0x0119:
            r9.D = r2;
            goto L_0x0126;
        L_0x011c:
            r8 = "H";
            r7 = r7.equalsIgnoreCase(r8);
            if (r7 == 0) goto L_0x0126;
        L_0x0124:
            r9.D = r5;
        L_0x0126:
            r6 = r6 + 1;
            goto L_0x012a;
        L_0x0129:
            r6 = 0;
        L_0x012a:
            r7 = r9.B;
            r8 = 58;
            r7 = r7.indexOf(r8);
            if (r7 < 0) goto L_0x0178;
        L_0x0134:
            r4 = r4 + -1;
            if (r7 >= r4) goto L_0x0178;
        L_0x0138:
            r4 = r9.B;
            r4 = r4.substring(r6, r7);
            r6 = r9.B;
            r7 = r7 + 1;
            r6 = r6.substring(r7);
            r7 = r4.length();
            if (r7 <= 0) goto L_0x03e8;
        L_0x014c:
            r7 = r6.length();
            if (r7 <= 0) goto L_0x03e8;
        L_0x0152:
            r4 = java.lang.Float.parseFloat(r4);	 Catch:{ NumberFormatException -> 0x03e8 }
            r6 = java.lang.Float.parseFloat(r6);	 Catch:{ NumberFormatException -> 0x03e8 }
            r7 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1));	 Catch:{ NumberFormatException -> 0x03e8 }
            if (r7 <= 0) goto L_0x03e8;	 Catch:{ NumberFormatException -> 0x03e8 }
        L_0x015e:
            r7 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1));	 Catch:{ NumberFormatException -> 0x03e8 }
            if (r7 <= 0) goto L_0x03e8;	 Catch:{ NumberFormatException -> 0x03e8 }
        L_0x0162:
            r7 = r9.D;	 Catch:{ NumberFormatException -> 0x03e8 }
            if (r7 != r5) goto L_0x016f;	 Catch:{ NumberFormatException -> 0x03e8 }
        L_0x0166:
            r6 = r6 / r4;	 Catch:{ NumberFormatException -> 0x03e8 }
            r4 = java.lang.Math.abs(r6);	 Catch:{ NumberFormatException -> 0x03e8 }
            r9.C = r4;	 Catch:{ NumberFormatException -> 0x03e8 }
            goto L_0x03e8;	 Catch:{ NumberFormatException -> 0x03e8 }
        L_0x016f:
            r4 = r4 / r6;	 Catch:{ NumberFormatException -> 0x03e8 }
            r4 = java.lang.Math.abs(r4);	 Catch:{ NumberFormatException -> 0x03e8 }
            r9.C = r4;	 Catch:{ NumberFormatException -> 0x03e8 }
            goto L_0x03e8;
        L_0x0178:
            r4 = r9.B;
            r4 = r4.substring(r6);
            r6 = r4.length();
            if (r6 <= 0) goto L_0x03e8;
        L_0x0184:
            r4 = java.lang.Float.parseFloat(r4);	 Catch:{ NumberFormatException -> 0x03e8 }
            r9.C = r4;	 Catch:{ NumberFormatException -> 0x03e8 }
            goto L_0x03e8;
        L_0x018c:
            r6 = r9.P;
            r4 = r10.getFloat(r4, r6);
            r4 = java.lang.Math.max(r3, r4);
            r9.P = r4;
            goto L_0x03e8;
        L_0x019a:
            r6 = r9.N;	 Catch:{ Exception -> 0x01a4 }
            r6 = r10.getDimensionPixelSize(r4, r6);	 Catch:{ Exception -> 0x01a4 }
            r9.N = r6;	 Catch:{ Exception -> 0x01a4 }
            goto L_0x03e8;
            r6 = r9.N;
            r4 = r10.getInt(r4, r6);
            if (r4 != r7) goto L_0x03e8;
        L_0x01ad:
            r9.N = r7;
            goto L_0x03e8;
        L_0x01b1:
            r6 = r9.L;	 Catch:{ Exception -> 0x01bb }
            r6 = r10.getDimensionPixelSize(r4, r6);	 Catch:{ Exception -> 0x01bb }
            r9.L = r6;	 Catch:{ Exception -> 0x01bb }
            goto L_0x03e8;
            r6 = r9.L;
            r4 = r10.getInt(r4, r6);
            if (r4 != r7) goto L_0x03e8;
        L_0x01c4:
            r9.L = r7;
            goto L_0x03e8;
        L_0x01c8:
            r6 = r9.O;
            r4 = r10.getFloat(r4, r6);
            r4 = java.lang.Math.max(r3, r4);
            r9.O = r4;
            goto L_0x03e8;
        L_0x01d6:
            r6 = r9.M;	 Catch:{ Exception -> 0x01e0 }
            r6 = r10.getDimensionPixelSize(r4, r6);	 Catch:{ Exception -> 0x01e0 }
            r9.M = r6;	 Catch:{ Exception -> 0x01e0 }
            goto L_0x03e8;
            r6 = r9.M;
            r4 = r10.getInt(r4, r6);
            if (r4 != r7) goto L_0x03e8;
        L_0x01e9:
            r9.M = r7;
            goto L_0x03e8;
        L_0x01ed:
            r6 = r9.K;	 Catch:{ Exception -> 0x01f7 }
            r6 = r10.getDimensionPixelSize(r4, r6);	 Catch:{ Exception -> 0x01f7 }
            r9.K = r6;	 Catch:{ Exception -> 0x01f7 }
            goto L_0x03e8;
            r6 = r9.K;
            r4 = r10.getInt(r4, r6);
            if (r4 != r7) goto L_0x03e8;
        L_0x0200:
            r9.K = r7;
            goto L_0x03e8;
        L_0x0204:
            r4 = r10.getInt(r4, r2);
            r9.J = r4;
            r4 = r9.J;
            if (r4 != r5) goto L_0x03e8;
        L_0x020e:
            r4 = "ConstraintLayout";
            r6 = "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.";
            android.util.Log.e(r4, r6);
            goto L_0x03e8;
        L_0x0217:
            r4 = r10.getInt(r4, r2);
            r9.I = r4;
            r4 = r9.I;
            if (r4 != r5) goto L_0x03e8;
        L_0x0221:
            r4 = "ConstraintLayout";
            r6 = "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.";
            android.util.Log.e(r4, r6);
            goto L_0x03e8;
        L_0x022a:
            r6 = r9.A;
            r4 = r10.getFloat(r4, r6);
            r9.A = r4;
            goto L_0x03e8;
        L_0x0234:
            r6 = r9.z;
            r4 = r10.getFloat(r4, r6);
            r9.z = r4;
            goto L_0x03e8;
        L_0x023e:
            r6 = r9.U;
            r4 = r10.getBoolean(r4, r6);
            r9.U = r4;
            goto L_0x03e8;
        L_0x0248:
            r6 = r9.T;
            r4 = r10.getBoolean(r4, r6);
            r9.T = r4;
            goto L_0x03e8;
        L_0x0252:
            r6 = r9.y;
            r4 = r10.getDimensionPixelSize(r4, r6);
            r9.y = r4;
            goto L_0x03e8;
        L_0x025c:
            r6 = r9.x;
            r4 = r10.getDimensionPixelSize(r4, r6);
            r9.x = r4;
            goto L_0x03e8;
        L_0x0266:
            r6 = r9.w;
            r4 = r10.getDimensionPixelSize(r4, r6);
            r9.w = r4;
            goto L_0x03e8;
        L_0x0270:
            r6 = r9.v;
            r4 = r10.getDimensionPixelSize(r4, r6);
            r9.v = r4;
            goto L_0x03e8;
        L_0x027a:
            r6 = r9.u;
            r4 = r10.getDimensionPixelSize(r4, r6);
            r9.u = r4;
            goto L_0x03e8;
        L_0x0284:
            r6 = r9.t;
            r4 = r10.getDimensionPixelSize(r4, r6);
            r9.t = r4;
            goto L_0x03e8;
        L_0x028e:
            r6 = r9.s;
            r6 = r10.getResourceId(r4, r6);
            r9.s = r6;
            r6 = r9.s;
            if (r6 != r0) goto L_0x03e8;
        L_0x029a:
            r4 = r10.getInt(r4, r0);
            r9.s = r4;
            goto L_0x03e8;
        L_0x02a2:
            r6 = r9.r;
            r6 = r10.getResourceId(r4, r6);
            r9.r = r6;
            r6 = r9.r;
            if (r6 != r0) goto L_0x03e8;
        L_0x02ae:
            r4 = r10.getInt(r4, r0);
            r9.r = r4;
            goto L_0x03e8;
        L_0x02b6:
            r6 = r9.q;
            r6 = r10.getResourceId(r4, r6);
            r9.q = r6;
            r6 = r9.q;
            if (r6 != r0) goto L_0x03e8;
        L_0x02c2:
            r4 = r10.getInt(r4, r0);
            r9.q = r4;
            goto L_0x03e8;
        L_0x02ca:
            r6 = r9.p;
            r6 = r10.getResourceId(r4, r6);
            r9.p = r6;
            r6 = r9.p;
            if (r6 != r0) goto L_0x03e8;
        L_0x02d6:
            r4 = r10.getInt(r4, r0);
            r9.p = r4;
            goto L_0x03e8;
        L_0x02de:
            r6 = r9.l;
            r6 = r10.getResourceId(r4, r6);
            r9.l = r6;
            r6 = r9.l;
            if (r6 != r0) goto L_0x03e8;
        L_0x02ea:
            r4 = r10.getInt(r4, r0);
            r9.l = r4;
            goto L_0x03e8;
        L_0x02f2:
            r6 = r9.k;
            r6 = r10.getResourceId(r4, r6);
            r9.k = r6;
            r6 = r9.k;
            if (r6 != r0) goto L_0x03e8;
        L_0x02fe:
            r4 = r10.getInt(r4, r0);
            r9.k = r4;
            goto L_0x03e8;
        L_0x0306:
            r6 = r9.j;
            r6 = r10.getResourceId(r4, r6);
            r9.j = r6;
            r6 = r9.j;
            if (r6 != r0) goto L_0x03e8;
        L_0x0312:
            r4 = r10.getInt(r4, r0);
            r9.j = r4;
            goto L_0x03e8;
        L_0x031a:
            r6 = r9.i;
            r6 = r10.getResourceId(r4, r6);
            r9.i = r6;
            r6 = r9.i;
            if (r6 != r0) goto L_0x03e8;
        L_0x0326:
            r4 = r10.getInt(r4, r0);
            r9.i = r4;
            goto L_0x03e8;
        L_0x032e:
            r6 = r9.h;
            r6 = r10.getResourceId(r4, r6);
            r9.h = r6;
            r6 = r9.h;
            if (r6 != r0) goto L_0x03e8;
        L_0x033a:
            r4 = r10.getInt(r4, r0);
            r9.h = r4;
            goto L_0x03e8;
        L_0x0342:
            r6 = r9.g;
            r6 = r10.getResourceId(r4, r6);
            r9.g = r6;
            r6 = r9.g;
            if (r6 != r0) goto L_0x03e8;
        L_0x034e:
            r4 = r10.getInt(r4, r0);
            r9.g = r4;
            goto L_0x03e8;
        L_0x0356:
            r6 = r9.f;
            r6 = r10.getResourceId(r4, r6);
            r9.f = r6;
            r6 = r9.f;
            if (r6 != r0) goto L_0x03e8;
        L_0x0362:
            r4 = r10.getInt(r4, r0);
            r9.f = r4;
            goto L_0x03e8;
        L_0x036a:
            r6 = r9.e;
            r6 = r10.getResourceId(r4, r6);
            r9.e = r6;
            r6 = r9.e;
            if (r6 != r0) goto L_0x03e8;
        L_0x0376:
            r4 = r10.getInt(r4, r0);
            r9.e = r4;
            goto L_0x03e8;
        L_0x037d:
            r6 = r9.d;
            r6 = r10.getResourceId(r4, r6);
            r9.d = r6;
            r6 = r9.d;
            if (r6 != r0) goto L_0x03e8;
        L_0x0389:
            r4 = r10.getInt(r4, r0);
            r9.d = r4;
            goto L_0x03e8;
        L_0x0390:
            r6 = r9.c;
            r4 = r10.getFloat(r4, r6);
            r9.c = r4;
            goto L_0x03e8;
        L_0x0399:
            r6 = r9.b;
            r4 = r10.getDimensionPixelOffset(r4, r6);
            r9.b = r4;
            goto L_0x03e8;
        L_0x03a2:
            r6 = r9.a;
            r4 = r10.getDimensionPixelOffset(r4, r6);
            r9.a = r4;
            goto L_0x03e8;
        L_0x03ab:
            r6 = r9.o;
            r4 = r10.getFloat(r4, r6);
            r6 = 1135869952; // 0x43b40000 float:360.0 double:5.611943214E-315;
            r4 = r4 % r6;
            r9.o = r4;
            r4 = r9.o;
            r4 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1));
            if (r4 >= 0) goto L_0x03e8;
        L_0x03bc:
            r4 = r9.o;
            r4 = r6 - r4;
            r4 = r4 % r6;
            r9.o = r4;
            goto L_0x03e8;
        L_0x03c4:
            r6 = r9.n;
            r4 = r10.getDimensionPixelSize(r4, r6);
            r9.n = r4;
            goto L_0x03e8;
        L_0x03cd:
            r6 = r9.m;
            r6 = r10.getResourceId(r4, r6);
            r9.m = r6;
            r6 = r9.m;
            if (r6 != r0) goto L_0x03e8;
        L_0x03d9:
            r4 = r10.getInt(r4, r0);
            r9.m = r4;
            goto L_0x03e8;
        L_0x03e0:
            r6 = r9.S;
            r4 = r10.getInt(r4, r6);
            r9.S = r4;
        L_0x03e8:
            r1 = r1 + 1;
            goto L_0x009a;
        L_0x03ec:
            r10.recycle();
            r9.a();
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.<init>(android.content.Context, android.util.AttributeSet):void");
        }

        public final void a() {
            this.Y = false;
            this.V = true;
            this.W = true;
            if (this.width == -2 && this.T) {
                this.V = false;
                this.I = 1;
            }
            if (this.height == -2 && this.U) {
                this.W = false;
                this.J = 1;
            }
            if (this.width == 0 || this.width == -1) {
                this.V = false;
                if (this.width == 0 && this.I == 1) {
                    this.width = -2;
                    this.T = true;
                }
            }
            if (this.height == 0 || this.height == -1) {
                this.W = false;
                if (this.height == 0 && this.J == 1) {
                    this.height = -2;
                    this.U = true;
                }
            }
            if (this.c != -1.0f || this.a != -1 || this.b != -1) {
                this.Y = true;
                this.V = true;
                this.W = true;
                if (!(this.al instanceof dg)) {
                    this.al = new dg();
                }
                ((dg) this.al).a(this.S);
            }
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        @TargetApi(17)
        public void resolveLayoutDirection(int i) {
            int i2 = this.leftMargin;
            int i3 = this.rightMargin;
            super.resolveLayoutDirection(i);
            this.ad = -1;
            this.ae = -1;
            this.ab = -1;
            this.ac = -1;
            this.af = -1;
            this.ag = -1;
            this.af = this.t;
            this.ag = this.v;
            this.ah = this.z;
            this.ai = this.a;
            this.aj = this.b;
            this.ak = this.c;
            Object obj = null;
            if ((1 == getLayoutDirection() ? 1 : null) != null) {
                if (this.p != -1) {
                    this.ad = this.p;
                } else {
                    if (this.q != -1) {
                        this.ae = this.q;
                    }
                    if (this.r != -1) {
                        this.ac = this.r;
                        obj = 1;
                    }
                    if (this.s != -1) {
                        this.ab = this.s;
                        obj = 1;
                    }
                    if (this.x != -1) {
                        this.ag = this.x;
                    }
                    if (this.y != -1) {
                        this.af = this.y;
                    }
                    if (obj != null) {
                        this.ah = 1.0f - this.z;
                    }
                    if (this.Y && this.S == 1) {
                        if (this.c != -1.0f) {
                            this.ak = 1.0f - this.c;
                            this.ai = -1;
                            this.aj = -1;
                        } else if (this.a != -1) {
                            this.aj = this.a;
                            this.ai = -1;
                            this.ak = -1.0f;
                        } else if (this.b != -1) {
                            this.ai = this.b;
                            this.aj = -1;
                            this.ak = -1.0f;
                        }
                    }
                }
                obj = 1;
                if (this.r != -1) {
                    this.ac = this.r;
                    obj = 1;
                }
                if (this.s != -1) {
                    this.ab = this.s;
                    obj = 1;
                }
                if (this.x != -1) {
                    this.ag = this.x;
                }
                if (this.y != -1) {
                    this.af = this.y;
                }
                if (obj != null) {
                    this.ah = 1.0f - this.z;
                }
                if (this.c != -1.0f) {
                    this.ak = 1.0f - this.c;
                    this.ai = -1;
                    this.aj = -1;
                } else if (this.a != -1) {
                    this.aj = this.a;
                    this.ai = -1;
                    this.ak = -1.0f;
                } else if (this.b != -1) {
                    this.ai = this.b;
                    this.aj = -1;
                    this.ak = -1.0f;
                }
            } else {
                if (this.p != -1) {
                    this.ac = this.p;
                }
                if (this.q != -1) {
                    this.ab = this.q;
                }
                if (this.r != -1) {
                    this.ad = this.r;
                }
                if (this.s != -1) {
                    this.ae = this.s;
                }
                if (this.x != -1) {
                    this.af = this.x;
                }
                if (this.y != -1) {
                    this.ag = this.y;
                }
            }
            if (this.r == -1 && this.s == -1 && this.q == -1 && this.p == -1) {
                if (this.f != -1) {
                    this.ad = this.f;
                    if (this.rightMargin <= 0 && i3 > 0) {
                        this.rightMargin = i3;
                    }
                } else if (this.g != -1) {
                    this.ae = this.g;
                    if (this.rightMargin <= 0 && i3 > 0) {
                        this.rightMargin = i3;
                    }
                }
                if (this.d != -1) {
                    this.ab = this.d;
                    if (this.leftMargin <= 0 && i2 > 0) {
                        this.leftMargin = i2;
                    }
                } else if (this.e != -1) {
                    this.ac = this.e;
                    if (this.leftMargin <= 0 && i2 > 0) {
                        this.leftMargin = i2;
                    }
                }
            }
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return a();
    }

    public void setDesignInformation(int i, Object obj, Object obj2) {
        if (i == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.q == null) {
                this.q = new HashMap();
            }
            obj = (String) obj;
            i = obj.indexOf("/");
            if (i != -1) {
                obj = obj.substring(i + 1);
            }
            this.q.put(obj, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    public final Object a(Object obj) {
        if (obj instanceof String) {
            String str = (String) obj;
            if (this.q != null && this.q.containsKey(str)) {
                return this.q.get(str);
            }
        }
        return null;
    }

    public ConstraintLayout(Context context) {
        super(context);
        a(null);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    public void setId(int i) {
        this.a.remove(getId());
        super.setId(i);
        this.a.put(getId(), this);
    }

    private void a(android.util.AttributeSet r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.constraintlayout.widget.ConstraintLayout.a(android.util.AttributeSet):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
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
        r7 = this;
        r0 = r7.b;
        r0.a(r7);
        r0 = r7.a;
        r1 = r7.getId();
        r0.put(r1, r7);
        r0 = 0;
        r7.o = r0;
        if (r8 == 0) goto L_0x008d;
    L_0x0013:
        r1 = r7.getContext();
        r2 = androidx.constraintlayout.widget.f.ConstraintLayout_Layout;
        r8 = r1.obtainStyledAttributes(r8, r2);
        r1 = r8.getIndexCount();
        r2 = 0;
        r3 = 0;
    L_0x0023:
        if (r3 >= r1) goto L_0x008a;
    L_0x0025:
        r4 = r8.getIndex(r3);
        r5 = androidx.constraintlayout.widget.f.ConstraintLayout_Layout_android_minWidth;
        if (r4 != r5) goto L_0x0036;
    L_0x002d:
        r5 = r7.i;
        r4 = r8.getDimensionPixelOffset(r4, r5);
        r7.i = r4;
        goto L_0x0087;
    L_0x0036:
        r5 = androidx.constraintlayout.widget.f.ConstraintLayout_Layout_android_minHeight;
        if (r4 != r5) goto L_0x0043;
    L_0x003a:
        r5 = r7.j;
        r4 = r8.getDimensionPixelOffset(r4, r5);
        r7.j = r4;
        goto L_0x0087;
    L_0x0043:
        r5 = androidx.constraintlayout.widget.f.ConstraintLayout_Layout_android_maxWidth;
        if (r4 != r5) goto L_0x0050;
    L_0x0047:
        r5 = r7.k;
        r4 = r8.getDimensionPixelOffset(r4, r5);
        r7.k = r4;
        goto L_0x0087;
    L_0x0050:
        r5 = androidx.constraintlayout.widget.f.ConstraintLayout_Layout_android_maxHeight;
        if (r4 != r5) goto L_0x005d;
    L_0x0054:
        r5 = r7.l;
        r4 = r8.getDimensionPixelOffset(r4, r5);
        r7.l = r4;
        goto L_0x0087;
    L_0x005d:
        r5 = androidx.constraintlayout.widget.f.ConstraintLayout_Layout_layout_optimizationLevel;
        if (r4 != r5) goto L_0x006a;
    L_0x0061:
        r5 = r7.n;
        r4 = r8.getInt(r4, r5);
        r7.n = r4;
        goto L_0x0087;
    L_0x006a:
        r5 = androidx.constraintlayout.widget.f.ConstraintLayout_Layout_constraintSet;
        if (r4 != r5) goto L_0x0087;
    L_0x006e:
        r4 = r8.getResourceId(r4, r2);
        r5 = new androidx.constraintlayout.widget.b;	 Catch:{ NotFoundException -> 0x0083 }
        r5.<init>();	 Catch:{ NotFoundException -> 0x0083 }
        r7.o = r5;	 Catch:{ NotFoundException -> 0x0083 }
        r5 = r7.o;	 Catch:{ NotFoundException -> 0x0083 }
        r6 = r7.getContext();	 Catch:{ NotFoundException -> 0x0083 }
        r5.a(r6, r4);	 Catch:{ NotFoundException -> 0x0083 }
        goto L_0x0085;
    L_0x0083:
        r7.o = r0;
    L_0x0085:
        r7.p = r4;
    L_0x0087:
        r3 = r3 + 1;
        goto L_0x0023;
    L_0x008a:
        r8.recycle();
    L_0x008d:
        r8 = r7.b;
        r0 = r7.n;
        r8.a(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.a(android.util.AttributeSet):void");
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (VERSION.SDK_INT < 14) {
            onViewAdded(view);
        }
    }

    public void removeView(View view) {
        super.removeView(view);
        if (VERSION.SDK_INT < 14) {
            onViewRemoved(view);
        }
    }

    public void onViewAdded(View view) {
        if (VERSION.SDK_INT >= 14) {
            super.onViewAdded(view);
        }
        dc a = a(view);
        if ((view instanceof Guideline) && !(a instanceof dg)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.al = new dg();
            layoutParams.Y = true;
            ((dg) layoutParams.al).a(layoutParams.S);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.d();
            ((LayoutParams) view.getLayoutParams()).Z = true;
            if (!this.g.contains(constraintHelper)) {
                this.g.add(constraintHelper);
            }
        }
        this.a.put(view.getId(), view);
        this.m = true;
    }

    public void onViewRemoved(View view) {
        if (VERSION.SDK_INT >= 14) {
            super.onViewRemoved(view);
        }
        this.a.remove(view.getId());
        dc a = a(view);
        this.b.b(a);
        this.g.remove(view);
        this.h.remove(a);
        this.m = true;
    }

    public void setMinWidth(int i) {
        if (i != this.i) {
            this.i = i;
            requestLayout();
        }
    }

    public void setMinHeight(int i) {
        if (i != this.j) {
            this.j = i;
            requestLayout();
        }
    }

    public void setMaxWidth(int i) {
        if (i != this.k) {
            this.k = i;
            requestLayout();
        }
    }

    public void setMaxHeight(int i) {
        if (i != this.l) {
            this.l = i;
            requestLayout();
        }
    }

    private final dc b(int i) {
        if (i == 0) {
            return this.b;
        }
        View view = (View) this.a.get(i);
        if (view == null) {
            view = findViewById(i);
            if (!(view == null || view == this || view.getParent() != this)) {
                onViewAdded(view);
            }
        }
        if (view == this) {
            return this.b;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).al;
    }

    public final dc a(View view) {
        if (view == this) {
            return this.b;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).al;
    }

    protected void onMeasure(int r52, int r53) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.constraintlayout.widget.ConstraintLayout.onMeasure(int, int):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
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
        r51 = this;
        r0 = r51;
        r1 = r52;
        r2 = r53;
        java.lang.System.currentTimeMillis();
        r3 = android.view.View.MeasureSpec.getMode(r52);
        r4 = android.view.View.MeasureSpec.getSize(r52);
        r5 = android.view.View.MeasureSpec.getMode(r53);
        r6 = android.view.View.MeasureSpec.getSize(r53);
        r7 = r51.getPaddingLeft();
        r8 = r51.getPaddingTop();
        r9 = r0.b;
        r9.g(r7);
        r9 = r0.b;
        r9.h(r8);
        r9 = r0.b;
        r10 = r0.k;
        r9.c(r10);
        r9 = r0.b;
        r10 = r0.l;
        r9.d(r10);
        r9 = android.os.Build.VERSION.SDK_INT;
        r10 = 17;
        r11 = 0;
        r12 = 1;
        if (r9 < r10) goto L_0x004f;
    L_0x0041:
        r9 = r0.b;
        r13 = r51.getLayoutDirection();
        if (r13 != r12) goto L_0x004b;
    L_0x0049:
        r13 = 1;
        goto L_0x004c;
    L_0x004b:
        r13 = 0;
    L_0x004c:
        r9.a(r13);
    L_0x004f:
        r9 = android.view.View.MeasureSpec.getMode(r52);
        r13 = android.view.View.MeasureSpec.getSize(r52);
        r14 = android.view.View.MeasureSpec.getMode(r53);
        r15 = android.view.View.MeasureSpec.getSize(r53);
        r16 = r51.getPaddingTop();
        r17 = r51.getPaddingBottom();
        r16 = r16 + r17;
        r17 = r51.getPaddingLeft();
        r18 = r51.getPaddingRight();
        r17 = r17 + r18;
        r18 = defpackage.dd.FIXED;
        r19 = defpackage.dd.FIXED;
        r51.getLayoutParams();
        r12 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r10 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r9 == r12) goto L_0x0095;
    L_0x0080:
        if (r9 == 0) goto L_0x0092;
    L_0x0082:
        if (r9 == r10) goto L_0x0088;
    L_0x0084:
        r9 = r18;
        r13 = 0;
        goto L_0x0099;
    L_0x0088:
        r9 = r0.k;
        r9 = java.lang.Math.min(r9, r13);
        r9 = r9 - r17;
        r13 = r9;
        goto L_0x0097;
    L_0x0092:
        r18 = defpackage.dd.WRAP_CONTENT;
        goto L_0x0084;
    L_0x0095:
        r18 = defpackage.dd.WRAP_CONTENT;
    L_0x0097:
        r9 = r18;
    L_0x0099:
        if (r14 == r12) goto L_0x00b0;
    L_0x009b:
        if (r14 == 0) goto L_0x00ad;
    L_0x009d:
        if (r14 == r10) goto L_0x00a3;
    L_0x009f:
        r14 = r19;
        r15 = 0;
        goto L_0x00b4;
    L_0x00a3:
        r14 = r0.l;
        r14 = java.lang.Math.min(r14, r15);
        r14 = r14 - r16;
        r15 = r14;
        goto L_0x00b2;
    L_0x00ad:
        r19 = defpackage.dd.WRAP_CONTENT;
        goto L_0x009f;
    L_0x00b0:
        r19 = defpackage.dd.WRAP_CONTENT;
    L_0x00b2:
        r14 = r19;
    L_0x00b4:
        r10 = r0.b;
        r10.k(r11);
        r10 = r0.b;
        r10.l(r11);
        r10 = r0.b;
        r10.a(r9);
        r9 = r0.b;
        r9.i(r13);
        r9 = r0.b;
        r9.b(r14);
        r9 = r0.b;
        r9.j(r15);
        r9 = r0.b;
        r10 = r0.i;
        r13 = r51.getPaddingLeft();
        r10 = r10 - r13;
        r13 = r51.getPaddingRight();
        r10 = r10 - r13;
        r9.k(r10);
        r9 = r0.b;
        r10 = r0.j;
        r13 = r51.getPaddingTop();
        r10 = r10 - r13;
        r13 = r51.getPaddingBottom();
        r10 = r10 - r13;
        r9.l(r10);
        r9 = r0.b;
        r9 = r9.p();
        r10 = r0.b;
        r10 = r10.r();
        r13 = r0.m;
        if (r13 == 0) goto L_0x05cf;
    L_0x0104:
        r0.m = r11;
        r13 = r51.getChildCount();
        r12 = 0;
    L_0x010b:
        if (r12 >= r13) goto L_0x011c;
    L_0x010d:
        r16 = r0.getChildAt(r12);
        r16 = r16.isLayoutRequested();
        if (r16 == 0) goto L_0x0119;
    L_0x0117:
        r12 = 1;
        goto L_0x011d;
    L_0x0119:
        r12 = r12 + 1;
        goto L_0x010b;
    L_0x011c:
        r12 = 0;
    L_0x011d:
        if (r12 == 0) goto L_0x05c1;
    L_0x011f:
        r12 = r0.h;
        r12.clear();
        r12 = r51.isInEditMode();
        r13 = r51.getChildCount();
        if (r12 == 0) goto L_0x0172;
    L_0x012e:
        r14 = 0;
    L_0x012f:
        if (r14 >= r13) goto L_0x0172;
    L_0x0131:
        r16 = r0.getChildAt(r14);
        r15 = r51.getResources();	 Catch:{ NotFoundException -> 0x016a }
        r11 = r16.getId();	 Catch:{ NotFoundException -> 0x016a }
        r11 = r15.getResourceName(r11);	 Catch:{ NotFoundException -> 0x016a }
        r15 = r16.getId();	 Catch:{ NotFoundException -> 0x016a }
        r15 = java.lang.Integer.valueOf(r15);	 Catch:{ NotFoundException -> 0x016a }
        r20 = r7;
        r7 = 0;
        r0.setDesignInformation(r7, r11, r15);	 Catch:{ NotFoundException -> 0x016c }
        r7 = 47;	 Catch:{ NotFoundException -> 0x016c }
        r7 = r11.indexOf(r7);	 Catch:{ NotFoundException -> 0x016c }
        r15 = -1;	 Catch:{ NotFoundException -> 0x016c }
        if (r7 == r15) goto L_0x015e;	 Catch:{ NotFoundException -> 0x016c }
    L_0x0158:
        r7 = r7 + 1;	 Catch:{ NotFoundException -> 0x016c }
        r11 = r11.substring(r7);	 Catch:{ NotFoundException -> 0x016c }
    L_0x015e:
        r7 = r16.getId();	 Catch:{ NotFoundException -> 0x016c }
        r7 = r0.b(r7);	 Catch:{ NotFoundException -> 0x016c }
        r7.a(r11);	 Catch:{ NotFoundException -> 0x016c }
        goto L_0x016c;
    L_0x016a:
        r20 = r7;
    L_0x016c:
        r14 = r14 + 1;
        r7 = r20;
        r11 = 0;
        goto L_0x012f;
    L_0x0172:
        r20 = r7;
        r7 = 0;
    L_0x0175:
        if (r7 >= r13) goto L_0x0187;
    L_0x0177:
        r11 = r0.getChildAt(r7);
        r11 = r0.a(r11);
        if (r11 == 0) goto L_0x0184;
    L_0x0181:
        r11.g();
    L_0x0184:
        r7 = r7 + 1;
        goto L_0x0175;
    L_0x0187:
        r7 = r0.p;
        r11 = -1;
        if (r7 == r11) goto L_0x01b8;
    L_0x018c:
        r7 = 0;
    L_0x018d:
        if (r7 >= r13) goto L_0x01b8;
    L_0x018f:
        r11 = r0.getChildAt(r7);
        r14 = r11.getId();
        r15 = r0.p;
        if (r14 != r15) goto L_0x01b5;
    L_0x019b:
        r14 = r11 instanceof androidx.constraintlayout.widget.Constraints;
        if (r14 == 0) goto L_0x01b5;
    L_0x019f:
        r11 = (androidx.constraintlayout.widget.Constraints) r11;
        r14 = r11.a;
        if (r14 != 0) goto L_0x01ac;
    L_0x01a5:
        r14 = new androidx.constraintlayout.widget.b;
        r14.<init>();
        r11.a = r14;
    L_0x01ac:
        r14 = r11.a;
        r14.a(r11);
        r11 = r11.a;
        r0.o = r11;
    L_0x01b5:
        r7 = r7 + 1;
        goto L_0x018d;
    L_0x01b8:
        r7 = r0.o;
        if (r7 == 0) goto L_0x01c1;
    L_0x01bc:
        r7 = r0.o;
        r7.c(r0);
    L_0x01c1:
        r7 = r0.b;
        r7.N();
        r7 = r0.g;
        r7 = r7.size();
        if (r7 <= 0) goto L_0x01df;
    L_0x01ce:
        r11 = 0;
    L_0x01cf:
        if (r11 >= r7) goto L_0x01df;
    L_0x01d1:
        r14 = r0.g;
        r14 = r14.get(r11);
        r14 = (androidx.constraintlayout.widget.ConstraintHelper) r14;
        r14.a(r0);
        r11 = r11 + 1;
        goto L_0x01cf;
    L_0x01df:
        r7 = 0;
    L_0x01e0:
        if (r7 >= r13) goto L_0x01f2;
    L_0x01e2:
        r11 = r0.getChildAt(r7);
        r14 = r11 instanceof androidx.constraintlayout.widget.Placeholder;
        if (r14 == 0) goto L_0x01ef;
    L_0x01ea:
        r11 = (androidx.constraintlayout.widget.Placeholder) r11;
        r11.a(r0);
    L_0x01ef:
        r7 = r7 + 1;
        goto L_0x01e0;
    L_0x01f2:
        r7 = 0;
    L_0x01f3:
        if (r7 >= r13) goto L_0x05b8;
    L_0x01f5:
        r11 = r0.getChildAt(r7);
        r15 = r0.a(r11);
        if (r15 == 0) goto L_0x059c;
    L_0x01ff:
        r14 = r11.getLayoutParams();
        r14 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r14;
        r14.a();
        r21 = r13;
        r13 = r14.am;
        if (r13 == 0) goto L_0x0216;
    L_0x020e:
        r13 = 0;
        r14.am = r13;
    L_0x0211:
        r23 = r6;
        r22 = r8;
        goto L_0x0250;
    L_0x0216:
        if (r12 == 0) goto L_0x0211;
    L_0x0218:
        r13 = r51.getResources();	 Catch:{ NotFoundException -> 0x0211 }
        r22 = r8;
        r8 = r11.getId();	 Catch:{ NotFoundException -> 0x024e }
        r8 = r13.getResourceName(r8);	 Catch:{ NotFoundException -> 0x024e }
        r13 = r11.getId();	 Catch:{ NotFoundException -> 0x024e }
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ NotFoundException -> 0x024e }
        r23 = r6;
        r6 = 0;
        r0.setDesignInformation(r6, r8, r13);	 Catch:{ NotFoundException -> 0x024c }
        r6 = "id/";	 Catch:{ NotFoundException -> 0x024c }
        r6 = r8.indexOf(r6);	 Catch:{ NotFoundException -> 0x024c }
        r6 = r6 + 3;	 Catch:{ NotFoundException -> 0x024c }
        r6 = r8.substring(r6);	 Catch:{ NotFoundException -> 0x024c }
        r8 = r11.getId();	 Catch:{ NotFoundException -> 0x024c }
        r8 = r0.b(r8);	 Catch:{ NotFoundException -> 0x024c }
        r8.a(r6);	 Catch:{ NotFoundException -> 0x024c }
        goto L_0x0250;
        goto L_0x0250;
    L_0x024e:
        r23 = r6;
    L_0x0250:
        r6 = r11.getVisibility();
        r15.e(r6);
        r6 = r14.aa;
        if (r6 == 0) goto L_0x0260;
    L_0x025b:
        r6 = 8;
        r15.e(r6);
    L_0x0260:
        r15.a(r11);
        r6 = r0.b;
        r6.a(r15);
        r6 = r14.W;
        if (r6 == 0) goto L_0x0270;
    L_0x026c:
        r6 = r14.V;
        if (r6 != 0) goto L_0x0275;
    L_0x0270:
        r6 = r0.h;
        r6.add(r15);
    L_0x0275:
        r6 = r14.Y;
        if (r6 == 0) goto L_0x02b0;
    L_0x0279:
        r15 = (defpackage.dg) r15;
        r6 = r14.ai;
        r8 = r14.aj;
        r11 = r14.ak;
        r13 = android.os.Build.VERSION.SDK_INT;
        r24 = r6;
        r6 = 17;
        if (r13 >= r6) goto L_0x0290;
    L_0x0289:
        r6 = r14.a;
        r8 = r14.b;
        r11 = r14.c;
        goto L_0x0292;
    L_0x0290:
        r6 = r24;
    L_0x0292:
        r13 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r13 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1));
        if (r13 == 0) goto L_0x02a3;
    L_0x0298:
        r15.e(r11);
    L_0x029b:
        r37 = r3;
        r36 = r4;
        r30 = r5;
        goto L_0x05a8;
    L_0x02a3:
        r11 = -1;
        if (r6 == r11) goto L_0x02aa;
    L_0x02a6:
        r15.s(r6);
        goto L_0x029b;
    L_0x02aa:
        if (r8 == r11) goto L_0x029b;
    L_0x02ac:
        r15.t(r8);
        goto L_0x029b;
    L_0x02b0:
        r11 = -1;
        r6 = r14.d;
        if (r6 != r11) goto L_0x02f9;
    L_0x02b5:
        r6 = r14.e;
        if (r6 != r11) goto L_0x02f9;
    L_0x02b9:
        r6 = r14.f;
        if (r6 != r11) goto L_0x02f9;
    L_0x02bd:
        r6 = r14.g;
        if (r6 != r11) goto L_0x02f9;
    L_0x02c1:
        r6 = r14.q;
        if (r6 != r11) goto L_0x02f9;
    L_0x02c5:
        r6 = r14.p;
        if (r6 != r11) goto L_0x02f9;
    L_0x02c9:
        r6 = r14.r;
        if (r6 != r11) goto L_0x02f9;
    L_0x02cd:
        r6 = r14.s;
        if (r6 != r11) goto L_0x02f9;
    L_0x02d1:
        r6 = r14.h;
        if (r6 != r11) goto L_0x02f9;
    L_0x02d5:
        r6 = r14.i;
        if (r6 != r11) goto L_0x02f9;
    L_0x02d9:
        r6 = r14.j;
        if (r6 != r11) goto L_0x02f9;
    L_0x02dd:
        r6 = r14.k;
        if (r6 != r11) goto L_0x02f9;
    L_0x02e1:
        r6 = r14.l;
        if (r6 != r11) goto L_0x02f9;
    L_0x02e5:
        r6 = r14.Q;
        if (r6 != r11) goto L_0x02f9;
    L_0x02e9:
        r6 = r14.R;
        if (r6 != r11) goto L_0x02f9;
    L_0x02ed:
        r6 = r14.m;
        if (r6 != r11) goto L_0x02f9;
    L_0x02f1:
        r6 = r14.width;
        if (r6 == r11) goto L_0x02f9;
    L_0x02f5:
        r6 = r14.height;
        if (r6 != r11) goto L_0x029b;
    L_0x02f9:
        r6 = r14.ab;
        r8 = r14.ac;
        r11 = r14.ad;
        r13 = r14.ae;
        r25 = r6;
        r6 = r14.af;
        r26 = r6;
        r6 = r14.ag;
        r27 = r6;
        r6 = r14.ah;
        r28 = r6;
        r6 = android.os.Build.VERSION.SDK_INT;
        r29 = r8;
        r8 = 17;
        if (r6 >= r8) goto L_0x0372;
    L_0x0317:
        r6 = r14.d;
        r11 = r14.e;
        r13 = r14.f;
        r8 = r14.g;
        r30 = r5;
        r5 = r14.t;
        r31 = r5;
        r5 = r14.v;
        r32 = r5;
        r5 = r14.z;
        r33 = r5;
        r5 = -1;
        if (r6 != r5) goto L_0x0343;
    L_0x0330:
        if (r11 != r5) goto L_0x0343;
    L_0x0332:
        r34 = r6;
        r6 = r14.q;
        if (r6 == r5) goto L_0x033b;
    L_0x0338:
        r6 = r14.q;
        goto L_0x0347;
    L_0x033b:
        r6 = r14.p;
        if (r6 == r5) goto L_0x0345;
    L_0x033f:
        r6 = r14.p;
        r11 = r6;
        goto L_0x0345;
    L_0x0343:
        r34 = r6;
    L_0x0345:
        r6 = r34;
    L_0x0347:
        if (r13 != r5) goto L_0x0365;
    L_0x0349:
        if (r8 != r5) goto L_0x0365;
    L_0x034b:
        r35 = r6;
        r6 = r14.r;
        if (r6 == r5) goto L_0x0359;
    L_0x0351:
        r6 = r14.r;
        r37 = r3;
        r36 = r4;
        r13 = r6;
        goto L_0x036b;
    L_0x0359:
        r6 = r14.s;
        if (r6 == r5) goto L_0x0367;
    L_0x035d:
        r6 = r14.s;
        r37 = r3;
        r36 = r4;
        r8 = r6;
        goto L_0x036b;
    L_0x0365:
        r35 = r6;
    L_0x0367:
        r37 = r3;
        r36 = r4;
    L_0x036b:
        r19 = r31;
        r4 = r33;
        r6 = r35;
        goto L_0x0385;
    L_0x0372:
        r30 = r5;
        r5 = -1;
        r37 = r3;
        r36 = r4;
        r8 = r13;
        r6 = r25;
        r19 = r26;
        r32 = r27;
        r4 = r28;
        r13 = r11;
        r11 = r29;
    L_0x0385:
        r3 = r14.m;
        if (r3 == r5) goto L_0x039c;
    L_0x0389:
        r3 = r14.m;
        r3 = r0.b(r3);
        if (r3 == 0) goto L_0x0398;
    L_0x0391:
        r4 = r14.o;
        r6 = r14.n;
        r15.a(r3, r4, r6);
    L_0x0398:
        r6 = r14;
        r3 = r15;
        goto L_0x04e8;
    L_0x039c:
        if (r6 == r5) goto L_0x03b9;
    L_0x039e:
        r16 = r0.b(r6);
        if (r16 == 0) goto L_0x03b4;
    L_0x03a4:
        r3 = defpackage.db.LEFT;
        r17 = defpackage.db.LEFT;
        r5 = r14.leftMargin;
        r6 = r14;
        r14 = r15;
        r11 = r15;
        r15 = r3;
        r18 = r5;
        r14.a(r15, r16, r17, r18, r19);
        goto L_0x03b6;
    L_0x03b4:
        r6 = r14;
        r11 = r15;
    L_0x03b6:
        r3 = r11;
    L_0x03b7:
        r5 = -1;
        goto L_0x03d0;
    L_0x03b9:
        r6 = r14;
        r3 = r15;
        if (r11 == r5) goto L_0x03d0;
    L_0x03bd:
        r16 = r0.b(r11);
        if (r16 == 0) goto L_0x03b7;
    L_0x03c3:
        r15 = defpackage.db.LEFT;
        r17 = defpackage.db.RIGHT;
        r5 = r6.leftMargin;
        r14 = r3;
        r18 = r5;
        r14.a(r15, r16, r17, r18, r19);
        goto L_0x03b7;
    L_0x03d0:
        if (r13 == r5) goto L_0x03e7;
    L_0x03d2:
        r16 = r0.b(r13);
        if (r16 == 0) goto L_0x03fd;
    L_0x03d8:
        r15 = defpackage.db.RIGHT;
        r17 = defpackage.db.LEFT;
        r5 = r6.rightMargin;
        r14 = r3;
        r18 = r5;
        r19 = r32;
        r14.a(r15, r16, r17, r18, r19);
        goto L_0x03fd;
    L_0x03e7:
        if (r8 == r5) goto L_0x03fd;
    L_0x03e9:
        r16 = r0.b(r8);
        if (r16 == 0) goto L_0x03fd;
    L_0x03ef:
        r15 = defpackage.db.RIGHT;
        r17 = defpackage.db.RIGHT;
        r5 = r6.rightMargin;
        r14 = r3;
        r18 = r5;
        r19 = r32;
        r14.a(r15, r16, r17, r18, r19);
    L_0x03fd:
        r5 = r6.h;
        r8 = -1;
        if (r5 == r8) goto L_0x041b;
    L_0x0402:
        r5 = r6.h;
        r16 = r0.b(r5);
        if (r16 == 0) goto L_0x0438;
    L_0x040a:
        r15 = defpackage.db.TOP;
        r17 = defpackage.db.TOP;
        r5 = r6.topMargin;
        r8 = r6.u;
        r14 = r3;
        r18 = r5;
        r19 = r8;
        r14.a(r15, r16, r17, r18, r19);
        goto L_0x0438;
    L_0x041b:
        r5 = r6.i;
        r8 = -1;
        if (r5 == r8) goto L_0x0438;
    L_0x0420:
        r5 = r6.i;
        r16 = r0.b(r5);
        if (r16 == 0) goto L_0x0438;
    L_0x0428:
        r15 = defpackage.db.TOP;
        r17 = defpackage.db.BOTTOM;
        r5 = r6.topMargin;
        r8 = r6.u;
        r14 = r3;
        r18 = r5;
        r19 = r8;
        r14.a(r15, r16, r17, r18, r19);
    L_0x0438:
        r5 = r6.j;
        r8 = -1;
        if (r5 == r8) goto L_0x0456;
    L_0x043d:
        r5 = r6.j;
        r16 = r0.b(r5);
        if (r16 == 0) goto L_0x0473;
    L_0x0445:
        r15 = defpackage.db.BOTTOM;
        r17 = defpackage.db.TOP;
        r5 = r6.bottomMargin;
        r8 = r6.w;
        r14 = r3;
        r18 = r5;
        r19 = r8;
        r14.a(r15, r16, r17, r18, r19);
        goto L_0x0473;
    L_0x0456:
        r5 = r6.k;
        r8 = -1;
        if (r5 == r8) goto L_0x0473;
    L_0x045b:
        r5 = r6.k;
        r16 = r0.b(r5);
        if (r16 == 0) goto L_0x0473;
    L_0x0463:
        r15 = defpackage.db.BOTTOM;
        r17 = defpackage.db.BOTTOM;
        r5 = r6.bottomMargin;
        r8 = r6.w;
        r14 = r3;
        r18 = r5;
        r19 = r8;
        r14.a(r15, r16, r17, r18, r19);
    L_0x0473:
        r5 = r6.l;
        r8 = -1;
        if (r5 == r8) goto L_0x04c9;
    L_0x0478:
        r5 = r0.a;
        r8 = r6.l;
        r5 = r5.get(r8);
        r5 = (android.view.View) r5;
        r8 = r6.l;
        r8 = r0.b(r8);
        if (r8 == 0) goto L_0x04c9;
    L_0x048a:
        if (r5 == 0) goto L_0x04c9;
    L_0x048c:
        r11 = r5.getLayoutParams();
        r11 = r11 instanceof androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;
        if (r11 == 0) goto L_0x04c9;
    L_0x0494:
        r5 = r5.getLayoutParams();
        r5 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r5;
        r11 = 1;
        r6.X = r11;
        r5.X = r11;
        r5 = defpackage.db.BASELINE;
        r13 = r3.a(r5);
        r5 = defpackage.db.BASELINE;
        r14 = r8.a(r5);
        r15 = 0;
        r16 = -1;
        r17 = defpackage.da.STRONG;
        r18 = 0;
        r19 = 1;
        r13.a(r14, r15, r16, r17, r18, r19);
        r5 = defpackage.db.TOP;
        r5 = r3.a(r5);
        r5.h();
        r5 = defpackage.db.BOTTOM;
        r5 = r3.a(r5);
        r5.h();
    L_0x04c9:
        r5 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r8 = 0;
        r11 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r11 < 0) goto L_0x04d7;
    L_0x04d0:
        r11 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1));
        if (r11 == 0) goto L_0x04d7;
    L_0x04d4:
        r3.a(r4);
    L_0x04d7:
        r4 = r6.A;
        r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r4 < 0) goto L_0x04e8;
    L_0x04dd:
        r4 = r6.A;
        r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1));
        if (r4 == 0) goto L_0x04e8;
    L_0x04e3:
        r4 = r6.A;
        r3.b(r4);
    L_0x04e8:
        if (r12 == 0) goto L_0x04fa;
    L_0x04ea:
        r4 = r6.Q;
        r5 = -1;
        if (r4 != r5) goto L_0x04f3;
    L_0x04ef:
        r4 = r6.R;
        if (r4 == r5) goto L_0x04fa;
    L_0x04f3:
        r4 = r6.Q;
        r5 = r6.R;
        r3.a(r4, r5);
    L_0x04fa:
        r4 = r6.V;
        if (r4 != 0) goto L_0x0527;
    L_0x04fe:
        r4 = r6.width;
        r5 = -1;
        if (r4 != r5) goto L_0x051d;
    L_0x0503:
        r4 = defpackage.dd.MATCH_PARENT;
        r3.a(r4);
        r4 = defpackage.db.LEFT;
        r4 = r3.a(r4);
        r5 = r6.leftMargin;
        r4.d = r5;
        r4 = defpackage.db.RIGHT;
        r4 = r3.a(r4);
        r5 = r6.rightMargin;
        r4.d = r5;
        goto L_0x0531;
    L_0x051d:
        r4 = defpackage.dd.MATCH_CONSTRAINT;
        r3.a(r4);
        r4 = 0;
        r3.i(r4);
        goto L_0x0531;
    L_0x0527:
        r4 = defpackage.dd.FIXED;
        r3.a(r4);
        r4 = r6.width;
        r3.i(r4);
    L_0x0531:
        r4 = r6.W;
        if (r4 != 0) goto L_0x055e;
    L_0x0535:
        r4 = r6.height;
        r5 = -1;
        if (r4 != r5) goto L_0x0554;
    L_0x053a:
        r4 = defpackage.dd.MATCH_PARENT;
        r3.b(r4);
        r4 = defpackage.db.TOP;
        r4 = r3.a(r4);
        r5 = r6.topMargin;
        r4.d = r5;
        r4 = defpackage.db.BOTTOM;
        r4 = r3.a(r4);
        r5 = r6.bottomMargin;
        r4.d = r5;
        goto L_0x0568;
    L_0x0554:
        r4 = defpackage.dd.MATCH_CONSTRAINT;
        r3.b(r4);
        r4 = 0;
        r3.j(r4);
        goto L_0x0568;
    L_0x055e:
        r4 = defpackage.dd.FIXED;
        r3.b(r4);
        r4 = r6.height;
        r3.j(r4);
    L_0x0568:
        r4 = r6.B;
        if (r4 == 0) goto L_0x0571;
    L_0x056c:
        r4 = r6.B;
        r3.b(r4);
    L_0x0571:
        r4 = r6.E;
        r3.c(r4);
        r4 = r6.F;
        r3.d(r4);
        r4 = r6.G;
        r3.p(r4);
        r4 = r6.H;
        r3.q(r4);
        r4 = r6.I;
        r5 = r6.K;
        r8 = r6.M;
        r11 = r6.O;
        r3.a(r4, r5, r8, r11);
        r4 = r6.J;
        r5 = r6.L;
        r8 = r6.N;
        r6 = r6.P;
        r3.b(r4, r5, r8, r6);
        goto L_0x05a8;
    L_0x059c:
        r37 = r3;
        r36 = r4;
        r30 = r5;
        r23 = r6;
        r22 = r8;
        r21 = r13;
    L_0x05a8:
        r7 = r7 + 1;
        r13 = r21;
        r8 = r22;
        r6 = r23;
        r5 = r30;
        r4 = r36;
        r3 = r37;
        goto L_0x01f3;
    L_0x05b8:
        r37 = r3;
        r36 = r4;
        r30 = r5;
        r23 = r6;
        goto L_0x05cb;
    L_0x05c1:
        r37 = r3;
        r36 = r4;
        r30 = r5;
        r23 = r6;
        r20 = r7;
    L_0x05cb:
        r22 = r8;
        r3 = 1;
        goto L_0x05dc;
    L_0x05cf:
        r37 = r3;
        r36 = r4;
        r30 = r5;
        r23 = r6;
        r20 = r7;
        r22 = r8;
        r3 = 0;
    L_0x05dc:
        r4 = r0.n;
        r5 = 8;
        r4 = r4 & r5;
        if (r4 != r5) goto L_0x05e5;
    L_0x05e3:
        r4 = 1;
        goto L_0x05e6;
    L_0x05e5:
        r4 = 0;
    L_0x05e6:
        if (r4 == 0) goto L_0x08e0;
    L_0x05e8:
        r8 = r0.b;
        r8.L();
        r8 = r0.b;
        r8.f(r9, r10);
        r8 = r51.getPaddingTop();
        r11 = r51.getPaddingBottom();
        r8 = r8 + r11;
        r11 = r51.getPaddingLeft();
        r12 = r51.getPaddingRight();
        r11 = r11 + r12;
        r12 = r51.getChildCount();
        r13 = 0;
    L_0x0609:
        if (r13 >= r12) goto L_0x06e8;
    L_0x060b:
        r14 = r0.getChildAt(r13);
        r15 = r14.getVisibility();
        r5 = 8;
        if (r15 == r5) goto L_0x06d2;
    L_0x0617:
        r5 = r14.getLayoutParams();
        r5 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r5;
        r6 = r5.al;
        r15 = r5.Y;
        if (r15 != 0) goto L_0x06d2;
    L_0x0623:
        r15 = r5.Z;
        if (r15 != 0) goto L_0x06d2;
    L_0x0627:
        r15 = r14.getVisibility();
        r6.e(r15);
        r15 = r5.width;
        r7 = r5.height;
        if (r15 == 0) goto L_0x06bb;
    L_0x0634:
        if (r7 != 0) goto L_0x0638;
    L_0x0636:
        goto L_0x06bb;
    L_0x0638:
        r38 = r10;
        r10 = -2;
        if (r15 != r10) goto L_0x0642;
    L_0x063d:
        r39 = r9;
        r16 = 1;
        goto L_0x0646;
    L_0x0642:
        r39 = r9;
        r16 = 0;
    L_0x0646:
        r9 = getChildMeasureSpec(r1, r11, r15);
        if (r7 != r10) goto L_0x0650;
    L_0x064c:
        r40 = r4;
        r10 = 1;
        goto L_0x0653;
    L_0x0650:
        r40 = r4;
        r10 = 0;
    L_0x0653:
        r4 = getChildMeasureSpec(r2, r8, r7);
        r14.measure(r9, r4);
        r4 = r0.t;
        if (r4 == 0) goto L_0x066b;
    L_0x065e:
        r4 = r0.t;
        r41 = r3;
        r2 = r4.a;
        r17 = 1;
        r2 = r2 + r17;
        r4.a = r2;
        goto L_0x066d;
    L_0x066b:
        r41 = r3;
    L_0x066d:
        r2 = -2;
        if (r15 != r2) goto L_0x0672;
    L_0x0670:
        r3 = 1;
        goto L_0x0673;
    L_0x0672:
        r3 = 0;
    L_0x0673:
        r6.b(r3);
        if (r7 != r2) goto L_0x067a;
    L_0x0678:
        r2 = 1;
        goto L_0x067b;
    L_0x067a:
        r2 = 0;
    L_0x067b:
        r6.c(r2);
        r2 = r14.getMeasuredWidth();
        r3 = r14.getMeasuredHeight();
        r6.i(r2);
        r6.j(r3);
        if (r16 == 0) goto L_0x0691;
    L_0x068e:
        r6.m(r2);
    L_0x0691:
        if (r10 == 0) goto L_0x0696;
    L_0x0693:
        r6.n(r3);
    L_0x0696:
        r4 = r5.X;
        if (r4 == 0) goto L_0x06a4;
    L_0x069a:
        r4 = r14.getBaseline();
        r7 = -1;
        if (r4 == r7) goto L_0x06a4;
    L_0x06a1:
        r6.o(r4);
    L_0x06a4:
        r4 = r5.V;
        if (r4 == 0) goto L_0x06da;
    L_0x06a8:
        r4 = r5.W;
        if (r4 == 0) goto L_0x06da;
    L_0x06ac:
        r4 = r6.j();
        r4.a(r2);
        r2 = r6.k();
        r2.a(r3);
        goto L_0x06da;
    L_0x06bb:
        r41 = r3;
        r40 = r4;
        r39 = r9;
        r38 = r10;
        r2 = r6.j();
        r2.d();
        r2 = r6.k();
        r2.d();
        goto L_0x06da;
    L_0x06d2:
        r41 = r3;
        r40 = r4;
        r39 = r9;
        r38 = r10;
    L_0x06da:
        r13 = r13 + 1;
        r10 = r38;
        r9 = r39;
        r4 = r40;
        r3 = r41;
        r2 = r53;
        goto L_0x0609;
    L_0x06e8:
        r41 = r3;
        r40 = r4;
        r39 = r9;
        r38 = r10;
        r2 = r0.b;
        r2.M();
        r2 = 0;
    L_0x06f6:
        if (r2 >= r12) goto L_0x08db;
    L_0x06f8:
        r3 = r0.getChildAt(r2);
        r4 = r3.getVisibility();
        r5 = 8;
        if (r4 == r5) goto L_0x08c9;
    L_0x0704:
        r4 = r3.getLayoutParams();
        r4 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r4;
        r5 = r4.al;
        r6 = r4.Y;
        if (r6 != 0) goto L_0x08c9;
    L_0x0710:
        r6 = r4.Z;
        if (r6 != 0) goto L_0x08c9;
    L_0x0714:
        r6 = r3.getVisibility();
        r5.e(r6);
        r6 = r4.width;
        r7 = r4.height;
        if (r6 == 0) goto L_0x0723;
    L_0x0721:
        if (r7 != 0) goto L_0x08c9;
    L_0x0723:
        r9 = defpackage.db.LEFT;
        r9 = r5.a(r9);
        r9 = r9.a();
        r10 = defpackage.db.RIGHT;
        r10 = r5.a(r10);
        r10 = r10.a();
        r13 = defpackage.db.LEFT;
        r13 = r5.a(r13);
        r13 = r13.f();
        if (r13 == 0) goto L_0x0751;
    L_0x0743:
        r13 = defpackage.db.RIGHT;
        r13 = r5.a(r13);
        r13 = r13.f();
        if (r13 == 0) goto L_0x0751;
    L_0x074f:
        r13 = 1;
        goto L_0x0752;
    L_0x0751:
        r13 = 0;
    L_0x0752:
        r14 = defpackage.db.TOP;
        r14 = r5.a(r14);
        r14 = r14.a();
        r15 = defpackage.db.BOTTOM;
        r15 = r5.a(r15);
        r15 = r15.a();
        r42 = r12;
        r12 = defpackage.db.TOP;
        r12 = r5.a(r12);
        r12 = r12.f();
        if (r12 == 0) goto L_0x0782;
    L_0x0774:
        r12 = defpackage.db.BOTTOM;
        r12 = r5.a(r12);
        r12 = r12.f();
        if (r12 == 0) goto L_0x0782;
    L_0x0780:
        r12 = 1;
        goto L_0x0783;
    L_0x0782:
        r12 = 0;
    L_0x0783:
        if (r6 != 0) goto L_0x0790;
    L_0x0785:
        if (r7 != 0) goto L_0x0790;
    L_0x0787:
        if (r13 == 0) goto L_0x0790;
    L_0x0789:
        if (r12 != 0) goto L_0x078c;
    L_0x078b:
        goto L_0x0790;
    L_0x078c:
        r43 = r2;
        goto L_0x08cd;
    L_0x0790:
        r43 = r2;
        r2 = r0.b;
        r2 = r2.F();
        r44 = r4;
        r4 = defpackage.dd.WRAP_CONTENT;
        if (r2 == r4) goto L_0x07a0;
    L_0x079e:
        r2 = 1;
        goto L_0x07a1;
    L_0x07a0:
        r2 = 0;
    L_0x07a1:
        r4 = r0.b;
        r4 = r4.G();
        r0 = defpackage.dd.WRAP_CONTENT;
        if (r4 == r0) goto L_0x07ad;
    L_0x07ab:
        r0 = 1;
        goto L_0x07ae;
    L_0x07ad:
        r0 = 0;
    L_0x07ae:
        if (r2 != 0) goto L_0x07b7;
    L_0x07b0:
        r4 = r5.j();
        r4.d();
    L_0x07b7:
        if (r0 != 0) goto L_0x07c0;
    L_0x07b9:
        r4 = r5.k();
        r4.d();
    L_0x07c0:
        if (r6 != 0) goto L_0x07f2;
    L_0x07c2:
        if (r2 == 0) goto L_0x07ea;
    L_0x07c4:
        r4 = r5.e();
        if (r4 == 0) goto L_0x07ea;
    L_0x07ca:
        if (r13 == 0) goto L_0x07ea;
    L_0x07cc:
        r4 = r9.f();
        if (r4 == 0) goto L_0x07ea;
    L_0x07d2:
        r4 = r10.f();
        if (r4 == 0) goto L_0x07ea;
    L_0x07d8:
        r4 = r10.c();
        r6 = r9.c();
        r4 = r4 - r6;
        r6 = (int) r4;
        r4 = r5.j();
        r4.a(r6);
        goto L_0x0802;
    L_0x07ea:
        r4 = -2;
        r2 = getChildMeasureSpec(r1, r11, r4);
        r4 = 1;
        r9 = 0;
        goto L_0x080c;
    L_0x07f2:
        r4 = -2;
        r9 = -1;
        if (r6 != r9) goto L_0x07fe;
    L_0x07f6:
        r10 = getChildMeasureSpec(r1, r11, r9);
        r9 = r2;
        r2 = r10;
        r4 = 0;
        goto L_0x080c;
    L_0x07fe:
        if (r6 != r4) goto L_0x0802;
    L_0x0800:
        r4 = 1;
        goto L_0x0803;
    L_0x0802:
        r4 = 0;
    L_0x0803:
        r9 = getChildMeasureSpec(r1, r11, r6);
        r50 = r9;
        r9 = r2;
        r2 = r50;
    L_0x080c:
        if (r7 != 0) goto L_0x0843;
    L_0x080e:
        if (r0 == 0) goto L_0x0838;
    L_0x0810:
        r10 = r5.f();
        if (r10 == 0) goto L_0x0838;
    L_0x0816:
        if (r12 == 0) goto L_0x0838;
    L_0x0818:
        r10 = r14.f();
        if (r10 == 0) goto L_0x0838;
    L_0x081e:
        r10 = r15.f();
        if (r10 == 0) goto L_0x0838;
    L_0x0824:
        r7 = r15.c();
        r10 = r14.c();
        r7 = r7 - r10;
        r7 = (int) r7;
        r10 = r5.k();
        r10.a(r7);
        r10 = r53;
        goto L_0x0854;
    L_0x0838:
        r10 = r53;
        r12 = -2;
        r0 = getChildMeasureSpec(r10, r8, r12);
        r13 = r0;
        r0 = 0;
        r12 = 1;
        goto L_0x0859;
    L_0x0843:
        r10 = r53;
        r12 = -2;
        r13 = -1;
        if (r7 != r13) goto L_0x0850;
    L_0x0849:
        r14 = getChildMeasureSpec(r10, r8, r13);
        r13 = r14;
        r12 = 0;
        goto L_0x0859;
    L_0x0850:
        if (r7 != r12) goto L_0x0854;
    L_0x0852:
        r12 = 1;
        goto L_0x0855;
    L_0x0854:
        r12 = 0;
    L_0x0855:
        r13 = getChildMeasureSpec(r10, r8, r7);
    L_0x0859:
        r3.measure(r2, r13);
        r2 = r51;
        r13 = r2.t;
        if (r13 == 0) goto L_0x086c;
    L_0x0862:
        r13 = r2.t;
        r14 = r13.a;
        r16 = 1;
        r14 = r14 + r16;
        r13.a = r14;
    L_0x086c:
        r13 = -2;
        if (r6 != r13) goto L_0x0871;
    L_0x086f:
        r6 = 1;
        goto L_0x0872;
    L_0x0871:
        r6 = 0;
    L_0x0872:
        r5.b(r6);
        if (r7 != r13) goto L_0x0879;
    L_0x0877:
        r6 = 1;
        goto L_0x087a;
    L_0x0879:
        r6 = 0;
    L_0x087a:
        r5.c(r6);
        r6 = r3.getMeasuredWidth();
        r7 = r3.getMeasuredHeight();
        r5.i(r6);
        r5.j(r7);
        if (r4 == 0) goto L_0x0890;
    L_0x088d:
        r5.m(r6);
    L_0x0890:
        if (r12 == 0) goto L_0x0895;
    L_0x0892:
        r5.n(r7);
    L_0x0895:
        if (r9 == 0) goto L_0x089f;
    L_0x0897:
        r4 = r5.j();
        r4.a(r6);
        goto L_0x08a6;
    L_0x089f:
        r4 = r5.j();
        r4.c();
    L_0x08a6:
        if (r0 == 0) goto L_0x08b2;
    L_0x08a8:
        r0 = r5.k();
        r0.a(r7);
    L_0x08af:
        r4 = r44;
        goto L_0x08ba;
    L_0x08b2:
        r0 = r5.k();
        r0.c();
        goto L_0x08af;
    L_0x08ba:
        r0 = r4.X;
        if (r0 == 0) goto L_0x08d0;
    L_0x08be:
        r0 = r3.getBaseline();
        r3 = -1;
        if (r0 == r3) goto L_0x08d0;
    L_0x08c5:
        r5.o(r0);
        goto L_0x08d0;
    L_0x08c9:
        r43 = r2;
        r42 = r12;
    L_0x08cd:
        r10 = r53;
        r2 = r0;
    L_0x08d0:
        r0 = r43 + 1;
        r12 = r42;
        r50 = r2;
        r2 = r0;
        r0 = r50;
        goto L_0x06f6;
    L_0x08db:
        r2 = r0;
        r10 = r53;
        goto L_0x09fc;
    L_0x08e0:
        r41 = r3;
        r40 = r4;
        r39 = r9;
        r38 = r10;
        r10 = r2;
        r2 = r0;
        r0 = r51.getPaddingTop();
        r3 = r51.getPaddingBottom();
        r0 = r0 + r3;
        r3 = r51.getPaddingLeft();
        r4 = r51.getPaddingRight();
        r3 = r3 + r4;
        r4 = r51.getChildCount();
        r5 = 0;
    L_0x0901:
        if (r5 >= r4) goto L_0x09fc;
    L_0x0903:
        r6 = r2.getChildAt(r5);
        r7 = r6.getVisibility();
        r8 = 8;
        if (r7 == r8) goto L_0x09f0;
    L_0x090f:
        r7 = r6.getLayoutParams();
        r7 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r7;
        r8 = r7.al;
        r9 = r7.Y;
        if (r9 != 0) goto L_0x09f0;
    L_0x091b:
        r9 = r7.Z;
        if (r9 != 0) goto L_0x09f0;
    L_0x091f:
        r9 = r6.getVisibility();
        r8.e(r9);
        r9 = r7.width;
        r11 = r7.height;
        r12 = r7.V;
        if (r12 != 0) goto L_0x0951;
    L_0x092e:
        r12 = r7.W;
        if (r12 != 0) goto L_0x0951;
    L_0x0932:
        r12 = r7.V;
        if (r12 != 0) goto L_0x093c;
    L_0x0936:
        r12 = r7.I;
        r13 = 1;
        if (r12 == r13) goto L_0x0951;
    L_0x093b:
        goto L_0x093d;
    L_0x093c:
        r13 = 1;
    L_0x093d:
        r12 = r7.width;
        r14 = -1;
        if (r12 == r14) goto L_0x0951;
    L_0x0942:
        r12 = r7.W;
        if (r12 != 0) goto L_0x094f;
    L_0x0946:
        r12 = r7.J;
        if (r12 == r13) goto L_0x0951;
    L_0x094a:
        r12 = r7.height;
        if (r12 != r14) goto L_0x094f;
    L_0x094e:
        goto L_0x0951;
    L_0x094f:
        r12 = 0;
        goto L_0x0952;
    L_0x0951:
        r12 = 1;
    L_0x0952:
        if (r12 == 0) goto L_0x09cb;
    L_0x0954:
        if (r9 != 0) goto L_0x095f;
    L_0x0956:
        r12 = -2;
        r13 = getChildMeasureSpec(r1, r3, r12);
        r14 = r13;
        r13 = -1;
        r15 = 1;
        goto L_0x0977;
    L_0x095f:
        r12 = -2;
        r13 = -1;
        if (r9 != r13) goto L_0x0969;
    L_0x0963:
        r14 = getChildMeasureSpec(r1, r3, r13);
        r15 = 0;
        goto L_0x0977;
    L_0x0969:
        if (r9 != r12) goto L_0x096d;
    L_0x096b:
        r14 = 1;
        goto L_0x096e;
    L_0x096d:
        r14 = 0;
    L_0x096e:
        r15 = getChildMeasureSpec(r1, r3, r9);
        r50 = r15;
        r15 = r14;
        r14 = r50;
    L_0x0977:
        if (r11 != 0) goto L_0x0981;
    L_0x0979:
        r16 = getChildMeasureSpec(r10, r0, r12);
        r12 = r16;
        r13 = 1;
        goto L_0x0997;
    L_0x0981:
        if (r11 != r13) goto L_0x098b;
    L_0x0983:
        r16 = getChildMeasureSpec(r10, r0, r13);
        r12 = r16;
        r13 = 0;
        goto L_0x0997;
    L_0x098b:
        if (r11 != r12) goto L_0x098f;
    L_0x098d:
        r12 = 1;
        goto L_0x0990;
    L_0x098f:
        r12 = 0;
    L_0x0990:
        r16 = getChildMeasureSpec(r10, r0, r11);
        r13 = r12;
        r12 = r16;
    L_0x0997:
        r6.measure(r14, r12);
        r12 = r2.t;
        if (r12 == 0) goto L_0x09ad;
    L_0x099e:
        r12 = r2.t;
        r45 = r3;
        r46 = r4;
        r3 = r12.a;
        r16 = 1;
        r3 = r3 + r16;
        r12.a = r3;
        goto L_0x09b1;
    L_0x09ad:
        r45 = r3;
        r46 = r4;
    L_0x09b1:
        r3 = -2;
        if (r9 != r3) goto L_0x09b6;
    L_0x09b4:
        r4 = 1;
        goto L_0x09b7;
    L_0x09b6:
        r4 = 0;
    L_0x09b7:
        r8.b(r4);
        if (r11 != r3) goto L_0x09be;
    L_0x09bc:
        r3 = 1;
        goto L_0x09bf;
    L_0x09be:
        r3 = 0;
    L_0x09bf:
        r8.c(r3);
        r9 = r6.getMeasuredWidth();
        r11 = r6.getMeasuredHeight();
        goto L_0x09d1;
    L_0x09cb:
        r45 = r3;
        r46 = r4;
        r13 = 0;
        r15 = 0;
    L_0x09d1:
        r8.i(r9);
        r8.j(r11);
        if (r15 == 0) goto L_0x09dc;
    L_0x09d9:
        r8.m(r9);
    L_0x09dc:
        if (r13 == 0) goto L_0x09e1;
    L_0x09de:
        r8.n(r11);
    L_0x09e1:
        r3 = r7.X;
        if (r3 == 0) goto L_0x09f4;
    L_0x09e5:
        r3 = r6.getBaseline();
        r4 = -1;
        if (r3 == r4) goto L_0x09f4;
    L_0x09ec:
        r8.o(r3);
        goto L_0x09f4;
    L_0x09f0:
        r45 = r3;
        r46 = r4;
    L_0x09f4:
        r5 = r5 + 1;
        r3 = r45;
        r4 = r46;
        goto L_0x0901;
    L_0x09fc:
        r0 = r51.getChildCount();
        r3 = 0;
    L_0x0a01:
        if (r3 >= r0) goto L_0x0a13;
    L_0x0a03:
        r4 = r2.getChildAt(r3);
        r5 = r4 instanceof androidx.constraintlayout.widget.Placeholder;
        if (r5 == 0) goto L_0x0a10;
    L_0x0a0b:
        r4 = (androidx.constraintlayout.widget.Placeholder) r4;
        r4.b();
    L_0x0a10:
        r3 = r3 + 1;
        goto L_0x0a01;
    L_0x0a13:
        r0 = r2.g;
        r0 = r0.size();
        if (r0 <= 0) goto L_0x0a26;
    L_0x0a1b:
        r3 = 0;
    L_0x0a1c:
        if (r3 >= r0) goto L_0x0a26;
    L_0x0a1e:
        r4 = r2.g;
        r4.get(r3);
        r3 = r3 + 1;
        goto L_0x0a1c;
    L_0x0a26:
        r0 = r51.getChildCount();
        if (r0 <= 0) goto L_0x0a33;
    L_0x0a2c:
        if (r41 == 0) goto L_0x0a33;
    L_0x0a2e:
        r0 = r2.b;
        defpackage.cu.a(r0);
    L_0x0a33:
        r0 = r2.b;
        r0 = r0.as;
        if (r0 == 0) goto L_0x0a92;
    L_0x0a39:
        r0 = r2.b;
        r0 = r0.at;
        if (r0 == 0) goto L_0x0a61;
    L_0x0a3f:
        r0 = r37;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r0 != r3) goto L_0x0a5e;
    L_0x0a45:
        r3 = r2.b;
        r3 = r3.av;
        r4 = r36;
        if (r3 >= r4) goto L_0x0a56;
    L_0x0a4d:
        r3 = r2.b;
        r5 = r2.b;
        r5 = r5.av;
        r3.i(r5);
    L_0x0a56:
        r3 = r2.b;
        r5 = defpackage.dd.FIXED;
        r3.a(r5);
        goto L_0x0a65;
    L_0x0a5e:
        r4 = r36;
        goto L_0x0a65;
    L_0x0a61:
        r4 = r36;
        r0 = r37;
    L_0x0a65:
        r3 = r2.b;
        r3 = r3.au;
        if (r3 == 0) goto L_0x0a8d;
    L_0x0a6b:
        r3 = r30;
        r5 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r3 != r5) goto L_0x0a8a;
    L_0x0a71:
        r5 = r2.b;
        r5 = r5.aw;
        r6 = r23;
        if (r5 >= r6) goto L_0x0a82;
    L_0x0a79:
        r5 = r2.b;
        r7 = r2.b;
        r7 = r7.aw;
        r5.j(r7);
    L_0x0a82:
        r5 = r2.b;
        r7 = defpackage.dd.FIXED;
        r5.b(r7);
        goto L_0x0a9a;
    L_0x0a8a:
        r6 = r23;
        goto L_0x0a9a;
    L_0x0a8d:
        r6 = r23;
        r3 = r30;
        goto L_0x0a9a;
    L_0x0a92:
        r6 = r23;
        r3 = r30;
        r4 = r36;
        r0 = r37;
    L_0x0a9a:
        r5 = r2.n;
        r7 = 32;
        r5 = r5 & r7;
        if (r5 != r7) goto L_0x0afb;
    L_0x0aa1:
        r5 = r2.b;
        r5 = r5.p();
        r7 = r2.b;
        r7 = r7.r();
        r8 = r2.r;
        if (r8 == r5) goto L_0x0abe;
    L_0x0ab1:
        r8 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r0 != r8) goto L_0x0ac0;
    L_0x0ab5:
        r0 = r2.b;
        r0 = r0.ar;
        r9 = 0;
        defpackage.cu.a(r0, r9, r5);
        goto L_0x0ac0;
    L_0x0abe:
        r8 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
    L_0x0ac0:
        r0 = r2.s;
        if (r0 == r7) goto L_0x0ace;
    L_0x0ac4:
        if (r3 != r8) goto L_0x0ace;
    L_0x0ac6:
        r0 = r2.b;
        r0 = r0.ar;
        r3 = 1;
        defpackage.cu.a(r0, r3, r7);
    L_0x0ace:
        r0 = r2.b;
        r0 = r0.at;
        if (r0 == 0) goto L_0x0ae3;
    L_0x0ad4:
        r0 = r2.b;
        r0 = r0.av;
        if (r0 <= r4) goto L_0x0ae3;
    L_0x0ada:
        r0 = r2.b;
        r0 = r0.ar;
        r7 = 0;
        defpackage.cu.a(r0, r7, r4);
        goto L_0x0ae4;
    L_0x0ae3:
        r7 = 0;
    L_0x0ae4:
        r0 = r2.b;
        r0 = r0.au;
        if (r0 == 0) goto L_0x0af9;
    L_0x0aea:
        r0 = r2.b;
        r0 = r0.aw;
        if (r0 <= r6) goto L_0x0af9;
    L_0x0af0:
        r0 = r2.b;
        r0 = r0.ar;
        r3 = 1;
        defpackage.cu.a(r0, r3, r6);
        goto L_0x0afd;
    L_0x0af9:
        r3 = 1;
        goto L_0x0afd;
    L_0x0afb:
        r3 = 1;
        r7 = 0;
    L_0x0afd:
        r0 = r51.getChildCount();
        if (r0 <= 0) goto L_0x0b06;
    L_0x0b03:
        r51.b();
    L_0x0b06:
        r0 = r2.h;
        r0 = r0.size();
        r4 = r51.getPaddingBottom();
        r8 = r22 + r4;
        r4 = r51.getPaddingRight();
        r4 = r20 + r4;
        if (r0 <= 0) goto L_0x0d25;
    L_0x0b1a:
        r6 = r2.b;
        r6 = r6.F();
        r9 = defpackage.dd.WRAP_CONTENT;
        if (r6 != r9) goto L_0x0b26;
    L_0x0b24:
        r6 = 1;
        goto L_0x0b27;
    L_0x0b26:
        r6 = 0;
    L_0x0b27:
        r9 = r2.b;
        r9 = r9.G();
        r11 = defpackage.dd.WRAP_CONTENT;
        if (r9 != r11) goto L_0x0b33;
    L_0x0b31:
        r9 = 1;
        goto L_0x0b34;
    L_0x0b33:
        r9 = 0;
    L_0x0b34:
        r11 = r2.b;
        r11 = r11.p();
        r12 = r2.i;
        r11 = java.lang.Math.max(r11, r12);
        r12 = r2.b;
        r12 = r12.r();
        r13 = r2.j;
        r12 = java.lang.Math.max(r12, r13);
        r13 = r11;
        r14 = r12;
        r11 = 0;
        r12 = 0;
        r15 = 0;
    L_0x0b51:
        if (r11 >= r0) goto L_0x0c7e;
    L_0x0b53:
        r3 = r2.h;
        r3 = r3.get(r11);
        r3 = (defpackage.dc) r3;
        r16 = r3.B();
        r7 = r16;
        r7 = (android.view.View) r7;
        if (r7 == 0) goto L_0x0c6b;
    L_0x0b65:
        r16 = r7.getLayoutParams();
        r5 = r16;
        r5 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r5;
        r48 = r0;
        r0 = r5.Z;
        if (r0 != 0) goto L_0x0c6d;
    L_0x0b73:
        r0 = r5.Y;
        if (r0 != 0) goto L_0x0c6d;
    L_0x0b77:
        r0 = r7.getVisibility();
        r49 = r12;
        r12 = 8;
        if (r0 == r12) goto L_0x0c6f;
    L_0x0b81:
        if (r40 == 0) goto L_0x0b97;
    L_0x0b83:
        r0 = r3.j();
        r0 = r0.f();
        if (r0 == 0) goto L_0x0b97;
    L_0x0b8d:
        r0 = r3.k();
        r0 = r0.f();
        if (r0 != 0) goto L_0x0c6f;
    L_0x0b97:
        r0 = r5.width;
        r12 = -2;
        if (r0 != r12) goto L_0x0ba7;
    L_0x0b9c:
        r0 = r5.V;
        if (r0 == 0) goto L_0x0ba7;
    L_0x0ba0:
        r0 = r5.width;
        r0 = getChildMeasureSpec(r1, r4, r0);
        goto L_0x0bb1;
    L_0x0ba7:
        r0 = r3.p();
        r12 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r12);
    L_0x0bb1:
        r12 = r5.height;
        r1 = -2;
        if (r12 != r1) goto L_0x0bc1;
    L_0x0bb6:
        r12 = r5.W;
        if (r12 == 0) goto L_0x0bc1;
    L_0x0bba:
        r12 = r5.height;
        r12 = getChildMeasureSpec(r10, r8, r12);
        goto L_0x0bcb;
    L_0x0bc1:
        r12 = r3.r();
        r1 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r12 = android.view.View.MeasureSpec.makeMeasureSpec(r12, r1);
    L_0x0bcb:
        r7.measure(r0, r12);
        r0 = r2.t;
        if (r0 == 0) goto L_0x0bdc;
    L_0x0bd2:
        r0 = r2.t;
        r1 = r0.b;
        r16 = 1;
        r1 = r1 + r16;
        r0.b = r1;
    L_0x0bdc:
        r0 = r7.getMeasuredWidth();
        r1 = r7.getMeasuredHeight();
        r2 = r3.p();
        if (r0 == r2) goto L_0x0c14;
    L_0x0bea:
        r3.i(r0);
        if (r40 == 0) goto L_0x0bf6;
    L_0x0bef:
        r2 = r3.j();
        r2.a(r0);
    L_0x0bf6:
        if (r6 == 0) goto L_0x0c12;
    L_0x0bf8:
        r0 = r3.x();
        if (r0 <= r13) goto L_0x0c12;
    L_0x0bfe:
        r0 = r3.x();
        r2 = defpackage.db.RIGHT;
        r2 = r3.a(r2);
        r2 = r2.d();
        r0 = r0 + r2;
        r0 = java.lang.Math.max(r13, r0);
        r13 = r0;
    L_0x0c12:
        r12 = 1;
        goto L_0x0c16;
    L_0x0c14:
        r12 = r49;
    L_0x0c16:
        r0 = r3.r();
        if (r1 == r0) goto L_0x0c45;
    L_0x0c1c:
        r3.j(r1);
        if (r40 == 0) goto L_0x0c28;
    L_0x0c21:
        r0 = r3.k();
        r0.a(r1);
    L_0x0c28:
        if (r9 == 0) goto L_0x0c44;
    L_0x0c2a:
        r0 = r3.y();
        if (r0 <= r14) goto L_0x0c44;
    L_0x0c30:
        r0 = r3.y();
        r1 = defpackage.db.BOTTOM;
        r1 = r3.a(r1);
        r1 = r1.d();
        r0 = r0 + r1;
        r0 = java.lang.Math.max(r14, r0);
        r14 = r0;
    L_0x0c44:
        r12 = 1;
    L_0x0c45:
        r0 = r5.X;
        if (r0 == 0) goto L_0x0c5b;
    L_0x0c49:
        r0 = r7.getBaseline();
        r1 = -1;
        if (r0 == r1) goto L_0x0c5c;
    L_0x0c50:
        r2 = r3.A();
        if (r0 == r2) goto L_0x0c5c;
    L_0x0c56:
        r3.o(r0);
        r12 = 1;
        goto L_0x0c5c;
    L_0x0c5b:
        r1 = -1;
    L_0x0c5c:
        r0 = android.os.Build.VERSION.SDK_INT;
        r2 = 11;
        if (r0 < r2) goto L_0x0c72;
    L_0x0c62:
        r0 = r7.getMeasuredState();
        r15 = combineMeasuredStates(r15, r0);
        goto L_0x0c72;
    L_0x0c6b:
        r48 = r0;
    L_0x0c6d:
        r49 = r12;
    L_0x0c6f:
        r1 = -1;
        r12 = r49;
    L_0x0c72:
        r11 = r11 + 1;
        r0 = r48;
        r1 = r52;
        r2 = r51;
        r3 = 1;
        r7 = 0;
        goto L_0x0b51;
    L_0x0c7e:
        r48 = r0;
        r49 = r12;
        if (r49 == 0) goto L_0x0cc6;
    L_0x0c84:
        r0 = r51;
        r1 = r0.b;
        r2 = r39;
        r1.i(r2);
        r1 = r0.b;
        r2 = r38;
        r1.j(r2);
        if (r40 == 0) goto L_0x0c9b;
    L_0x0c96:
        r1 = r0.b;
        r1.M();
    L_0x0c9b:
        r51.b();
        r1 = r0.b;
        r1 = r1.p();
        if (r1 >= r13) goto L_0x0cad;
    L_0x0ca6:
        r1 = r0.b;
        r1.i(r13);
        r12 = 1;
        goto L_0x0cae;
    L_0x0cad:
        r12 = 0;
    L_0x0cae:
        r1 = r0.b;
        r1 = r1.r();
        if (r1 >= r14) goto L_0x0cbe;
    L_0x0cb6:
        r1 = r0.b;
        r1.j(r14);
        r47 = 1;
        goto L_0x0cc0;
    L_0x0cbe:
        r47 = r12;
    L_0x0cc0:
        if (r47 == 0) goto L_0x0cc8;
    L_0x0cc2:
        r51.b();
        goto L_0x0cc8;
    L_0x0cc6:
        r0 = r51;
    L_0x0cc8:
        r1 = r48;
        r2 = 0;
    L_0x0ccb:
        if (r2 >= r1) goto L_0x0d27;
    L_0x0ccd:
        r3 = r0.h;
        r3 = r3.get(r2);
        r3 = (defpackage.dc) r3;
        r5 = r3.B();
        r5 = (android.view.View) r5;
        if (r5 == 0) goto L_0x0d1c;
    L_0x0cdd:
        r6 = r5.getMeasuredWidth();
        r7 = r3.p();
        if (r6 != r7) goto L_0x0cf1;
    L_0x0ce7:
        r6 = r5.getMeasuredHeight();
        r7 = r3.r();
        if (r6 == r7) goto L_0x0d1c;
    L_0x0cf1:
        r6 = r3.l();
        r7 = 8;
        if (r6 == r7) goto L_0x0d1e;
    L_0x0cf9:
        r6 = r3.p();
        r9 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r6 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r9);
        r3 = r3.r();
        r3 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r9);
        r5.measure(r6, r3);
        r3 = r0.t;
        if (r3 == 0) goto L_0x0d20;
    L_0x0d12:
        r3 = r0.t;
        r5 = r3.b;
        r11 = 1;
        r5 = r5 + r11;
        r3.b = r5;
        goto L_0x0d22;
    L_0x0d1c:
        r7 = 8;
    L_0x0d1e:
        r9 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
    L_0x0d20:
        r11 = 1;
    L_0x0d22:
        r2 = r2 + 1;
        goto L_0x0ccb;
    L_0x0d25:
        r0 = r2;
        r15 = 0;
    L_0x0d27:
        r1 = r0.b;
        r1 = r1.p();
        r1 = r1 + r4;
        r2 = r0.b;
        r2 = r2.r();
        r2 = r2 + r8;
        r3 = android.os.Build.VERSION.SDK_INT;
        r4 = 11;
        if (r3 < r4) goto L_0x0d74;
    L_0x0d3b:
        r3 = r52;
        r1 = resolveSizeAndState(r1, r3, r15);
        r3 = r15 << 16;
        r2 = resolveSizeAndState(r2, r10, r3);
        r3 = 16777215; // 0xffffff float:2.3509886E-38 double:8.2890456E-317;
        r1 = r1 & r3;
        r2 = r2 & r3;
        r3 = r0.k;
        r1 = java.lang.Math.min(r3, r1);
        r3 = r0.l;
        r2 = java.lang.Math.min(r3, r2);
        r3 = r0.b;
        r3 = r3.I();
        r4 = 16777216; // 0x1000000 float:2.3509887E-38 double:8.289046E-317;
        if (r3 == 0) goto L_0x0d63;
    L_0x0d62:
        r1 = r1 | r4;
    L_0x0d63:
        r3 = r0.b;
        r3 = r3.J();
        if (r3 == 0) goto L_0x0d6c;
    L_0x0d6b:
        r2 = r2 | r4;
    L_0x0d6c:
        r0.setMeasuredDimension(r1, r2);
        r0.r = r1;
        r0.s = r2;
        return;
    L_0x0d74:
        r0.setMeasuredDimension(r1, r2);
        r0.r = r1;
        r0.s = r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.onMeasure(int, int):void");
    }

    private void b() {
        this.b.K();
        if (this.t != null) {
            co coVar = this.t;
            coVar.c++;
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        i2 = 0;
        for (i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            dc dcVar = layoutParams.al;
            if ((childAt.getVisibility() != 8 || layoutParams.Y || layoutParams.Z || isInEditMode) && !layoutParams.aa) {
                int t = dcVar.t();
                int u = dcVar.u();
                int p = dcVar.p() + t;
                int r = dcVar.r() + u;
                childAt.layout(t, u, p, r);
                if (childAt instanceof Placeholder) {
                    childAt = ((Placeholder) childAt).a();
                    if (childAt != null) {
                        childAt.setVisibility(0);
                        childAt.layout(t, u, p, r);
                    }
                }
            }
        }
        childCount = this.g.size();
        if (childCount > 0) {
            while (i2 < childCount) {
                ((ConstraintHelper) this.g.get(i2)).e();
                i2++;
            }
        }
    }

    public void setOptimizationLevel(int i) {
        this.b.a(i);
    }

    protected static LayoutParams a() {
        return new LayoutParams(-2, -2);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void setConstraintSet(b bVar) {
        this.o = bVar;
    }

    public final View a(int i) {
        return (View) this.a.get(i);
    }

    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int childCount = getChildCount();
            float width = (float) getWidth();
            float height = (float) getHeight();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                if (childAt.getVisibility() != 8) {
                    Object tag = childAt.getTag();
                    if (tag != null && (tag instanceof String)) {
                        String[] split = ((String) tag).split(",");
                        if (split.length == 4) {
                            int parseInt = Integer.parseInt(split[0]);
                            int parseInt2 = Integer.parseInt(split[1]);
                            parseInt = (int) ((((float) parseInt) / 1080.0f) * width);
                            parseInt2 = (int) ((((float) parseInt2) / 1920.0f) * height);
                            int parseInt3 = (int) ((((float) Integer.parseInt(split[2])) / 1080.0f) * width);
                            int parseInt4 = (int) ((((float) Integer.parseInt(split[3])) / 1920.0f) * height);
                            Paint paint = new Paint();
                            paint.setColor(-65536);
                            float f = (float) parseInt;
                            float f2 = (float) parseInt2;
                            float f3 = (float) (parseInt + parseInt3);
                            Canvas canvas2 = canvas;
                            float f4 = f2;
                            float f5 = f2;
                            f2 = f3;
                            float f6 = f;
                            Paint paint2 = paint;
                            canvas2.drawLine(f, f4, f2, f5, paint);
                            float f7 = (float) (parseInt2 + parseInt4);
                            float f8 = f3;
                            f = f7;
                            canvas2.drawLine(f8, f5, f2, f, paint);
                            f4 = f7;
                            f2 = f6;
                            canvas2.drawLine(f8, f4, f2, f, paint);
                            f8 = f6;
                            canvas2.drawLine(f8, f4, f2, f5, paint);
                            paint.setColor(-16711936);
                            f2 = f3;
                            Paint paint3 = paint;
                            canvas2.drawLine(f8, f5, f2, f7, paint);
                            canvas2.drawLine(f8, f7, f2, f5, paint);
                        }
                    }
                }
            }
        }
        ConstraintLayout constraintLayout = this;
    }

    public void requestLayout() {
        super.requestLayout();
        this.m = true;
        this.r = -1;
        this.s = -1;
        this.c = -1;
        this.d = -1;
        this.e = 0;
        this.f = 0;
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }
}
