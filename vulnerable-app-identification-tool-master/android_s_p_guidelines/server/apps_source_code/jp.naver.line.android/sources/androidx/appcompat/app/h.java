package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ListAdapter;

public final class h {
    private final d a;
    private final int b;

    public h(Context context) {
        this(context, g.a(context, 0));
    }

    private h(Context context, int i) {
        this.a = new d(new ContextThemeWrapper(context, g.a(context, i)));
        this.b = i;
    }

    public final Context a() {
        return this.a.a;
    }

    public final h a(CharSequence charSequence) {
        this.a.f = charSequence;
        return this;
    }

    public final h a(View view) {
        this.a.g = view;
        return this;
    }

    public final h a(int i) {
        this.a.h = this.a.a.getText(i);
        return this;
    }

    public final h b(CharSequence charSequence) {
        this.a.h = charSequence;
        return this;
    }

    public final h b(int i) {
        this.a.c = i;
        return this;
    }

    public final h a(Drawable drawable) {
        this.a.d = drawable;
        return this;
    }

    public final h a(int i, OnClickListener onClickListener) {
        this.a.i = this.a.a.getText(i);
        this.a.k = onClickListener;
        return this;
    }

    public final h a(CharSequence charSequence, OnClickListener onClickListener) {
        this.a.i = charSequence;
        this.a.k = onClickListener;
        return this;
    }

    public final h b(int i, OnClickListener onClickListener) {
        this.a.l = this.a.a.getText(i);
        this.a.n = onClickListener;
        return this;
    }

    public final h b(CharSequence charSequence, OnClickListener onClickListener) {
        this.a.l = charSequence;
        this.a.n = onClickListener;
        return this;
    }

    public final h c(CharSequence charSequence, OnClickListener onClickListener) {
        this.a.o = charSequence;
        this.a.q = onClickListener;
        return this;
    }

    public final h a(OnKeyListener onKeyListener) {
        this.a.u = onKeyListener;
        return this;
    }

    public final h c(int i, OnClickListener onClickListener) {
        this.a.v = this.a.a.getResources().getTextArray(i);
        this.a.x = onClickListener;
        return this;
    }

    public final h a(CharSequence[] charSequenceArr, OnClickListener onClickListener) {
        this.a.v = charSequenceArr;
        this.a.x = onClickListener;
        return this;
    }

    public final h a(ListAdapter listAdapter, OnClickListener onClickListener) {
        this.a.w = listAdapter;
        this.a.x = onClickListener;
        return this;
    }

    public final h a(int i, boolean[] zArr, OnMultiChoiceClickListener onMultiChoiceClickListener) {
        this.a.v = this.a.a.getResources().getTextArray(i);
        this.a.J = onMultiChoiceClickListener;
        this.a.F = zArr;
        this.a.G = true;
        return this;
    }

    public final h a(CharSequence[] charSequenceArr, boolean[] zArr, OnMultiChoiceClickListener onMultiChoiceClickListener) {
        this.a.v = charSequenceArr;
        this.a.J = onMultiChoiceClickListener;
        this.a.F = zArr;
        this.a.G = true;
        return this;
    }

    public final h a(int i, int i2, OnClickListener onClickListener) {
        this.a.v = this.a.a.getResources().getTextArray(i);
        this.a.x = onClickListener;
        this.a.I = i2;
        this.a.H = true;
        return this;
    }

    public final h a(CharSequence[] charSequenceArr, int i, OnClickListener onClickListener) {
        this.a.v = charSequenceArr;
        this.a.x = onClickListener;
        this.a.I = i;
        this.a.H = true;
        return this;
    }

    public final h b(View view) {
        this.a.z = view;
        this.a.y = 0;
        this.a.E = false;
        return this;
    }

    public final androidx.appcompat.app.g b() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r12_0 android.widget.ListAdapter) in PHI: PHI: (r12_5 android.widget.ListAdapter) = (r12_0 android.widget.ListAdapter), (r12_1 android.widget.ListAdapter), (r12_2 android.widget.ListAdapter), (r12_3 android.widget.ListAdapter), (r12_4 android.widget.ListAdapter) binds: {(r12_0 android.widget.ListAdapter)=B:43:0x00a8, (r12_1 android.widget.ListAdapter)=B:44:0x00b7, (r12_2 android.widget.ListAdapter)=B:52:0x00d4, (r12_3 android.widget.ListAdapter)=B:55:0x00f5, (r12_4 android.widget.ListAdapter)=B:56:0x00f8}
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
        r18 = this;
        r0 = r18;
        r1 = new androidx.appcompat.app.g;
        r2 = r0.a;
        r2 = r2.a;
        r3 = r0.b;
        r1.<init>(r2, r3);
        r2 = r0.a;
        r10 = r1.a;
        r3 = r2.g;
        if (r3 == 0) goto L_0x001b;
    L_0x0015:
        r3 = r2.g;
        r10.a(r3);
        goto L_0x0043;
    L_0x001b:
        r3 = r2.f;
        if (r3 == 0) goto L_0x0024;
    L_0x001f:
        r3 = r2.f;
        r10.a(r3);
    L_0x0024:
        r3 = r2.d;
        if (r3 == 0) goto L_0x002d;
    L_0x0028:
        r3 = r2.d;
        r10.a(r3);
    L_0x002d:
        r3 = r2.c;
        if (r3 == 0) goto L_0x0036;
    L_0x0031:
        r3 = r2.c;
        r10.b(r3);
    L_0x0036:
        r3 = r2.e;
        if (r3 == 0) goto L_0x0043;
    L_0x003a:
        r3 = r2.e;
        r3 = r10.c(r3);
        r10.b(r3);
    L_0x0043:
        r3 = r2.h;
        if (r3 == 0) goto L_0x004c;
    L_0x0047:
        r3 = r2.h;
        r10.b(r3);
    L_0x004c:
        r3 = r2.i;
        if (r3 != 0) goto L_0x0054;
    L_0x0050:
        r3 = r2.j;
        if (r3 == 0) goto L_0x0060;
    L_0x0054:
        r4 = -1;
        r5 = r2.i;
        r6 = r2.k;
        r7 = 0;
        r8 = r2.j;
        r3 = r10;
        r3.a(r4, r5, r6, r7, r8);
    L_0x0060:
        r3 = r2.l;
        if (r3 != 0) goto L_0x0068;
    L_0x0064:
        r3 = r2.m;
        if (r3 == 0) goto L_0x0074;
    L_0x0068:
        r4 = -2;
        r5 = r2.l;
        r6 = r2.n;
        r7 = 0;
        r8 = r2.m;
        r3 = r10;
        r3.a(r4, r5, r6, r7, r8);
    L_0x0074:
        r3 = r2.o;
        if (r3 != 0) goto L_0x007c;
    L_0x0078:
        r3 = r2.p;
        if (r3 == 0) goto L_0x0088;
    L_0x007c:
        r4 = -3;
        r5 = r2.o;
        r6 = r2.q;
        r7 = 0;
        r8 = r2.p;
        r3 = r10;
        r3.a(r4, r5, r6, r7, r8);
    L_0x0088:
        r3 = r2.v;
        r11 = 1;
        if (r3 != 0) goto L_0x0095;
    L_0x008d:
        r3 = r2.K;
        if (r3 != 0) goto L_0x0095;
    L_0x0091:
        r3 = r2.w;
        if (r3 == 0) goto L_0x013b;
    L_0x0095:
        r3 = r2.b;
        r4 = r10.l;
        r5 = 0;
        r3 = r3.inflate(r4, r5);
        r3 = (androidx.appcompat.app.AlertController.RecycleListView) r3;
        r4 = r2.G;
        if (r4 == 0) goto L_0x00c5;
    L_0x00a4:
        r4 = r2.K;
        if (r4 != 0) goto L_0x00b7;
    L_0x00a8:
        r12 = new androidx.appcompat.app.d$1;
        r6 = r2.a;
        r7 = r10.m;
        r8 = r2.v;
        r4 = r12;
        r5 = r2;
        r9 = r3;
        r4.<init>(r5, r6, r7, r8, r9);
        goto L_0x0101;
    L_0x00b7:
        r12 = new androidx.appcompat.app.d$2;
        r6 = r2.a;
        r7 = r2.K;
        r4 = r12;
        r5 = r2;
        r8 = r3;
        r9 = r10;
        r4.<init>(r5, r6, r7, r8, r9);
        goto L_0x0101;
    L_0x00c5:
        r4 = r2.H;
        if (r4 == 0) goto L_0x00cd;
    L_0x00c9:
        r4 = r10.n;
    L_0x00cb:
        r14 = r4;
        goto L_0x00d0;
    L_0x00cd:
        r4 = r10.o;
        goto L_0x00cb;
    L_0x00d0:
        r4 = r2.K;
        if (r4 == 0) goto L_0x00f1;
    L_0x00d4:
        r4 = new android.widget.SimpleCursorAdapter;
        r13 = r2.a;
        r15 = r2.K;
        r5 = new java.lang.String[r11];
        r6 = r2.L;
        r7 = 0;
        r5[r7] = r6;
        r6 = new int[r11];
        r8 = 16908308; // 0x1020014 float:2.3877285E-38 double:8.353814E-317;
        r6[r7] = r8;
        r12 = r4;
        r16 = r5;
        r17 = r6;
        r12.<init>(r13, r14, r15, r16, r17);
        goto L_0x0101;
    L_0x00f1:
        r4 = r2.w;
        if (r4 == 0) goto L_0x00f8;
    L_0x00f5:
        r12 = r2.w;
        goto L_0x0101;
    L_0x00f8:
        r12 = new androidx.appcompat.app.f;
        r4 = r2.a;
        r5 = r2.v;
        r12.<init>(r4, r14, r5);
    L_0x0101:
        r10.j = r12;
        r4 = r2.I;
        r10.k = r4;
        r4 = r2.x;
        if (r4 == 0) goto L_0x0114;
    L_0x010b:
        r4 = new androidx.appcompat.app.d$3;
        r4.<init>(r2, r10);
        r3.setOnItemClickListener(r4);
        goto L_0x0120;
    L_0x0114:
        r4 = r2.J;
        if (r4 == 0) goto L_0x0120;
    L_0x0118:
        r4 = new androidx.appcompat.app.d$4;
        r4.<init>(r2, r3, r10);
        r3.setOnItemClickListener(r4);
    L_0x0120:
        r4 = r2.N;
        if (r4 == 0) goto L_0x0129;
    L_0x0124:
        r4 = r2.N;
        r3.setOnItemSelectedListener(r4);
    L_0x0129:
        r4 = r2.H;
        if (r4 == 0) goto L_0x0131;
    L_0x012d:
        r3.setChoiceMode(r11);
        goto L_0x0139;
    L_0x0131:
        r4 = r2.G;
        if (r4 == 0) goto L_0x0139;
    L_0x0135:
        r4 = 2;
        r3.setChoiceMode(r4);
    L_0x0139:
        r10.b = r3;
    L_0x013b:
        r3 = r2.z;
        if (r3 == 0) goto L_0x0158;
    L_0x013f:
        r3 = r2.E;
        if (r3 == 0) goto L_0x0152;
    L_0x0143:
        r4 = r2.z;
        r5 = r2.A;
        r6 = r2.B;
        r7 = r2.C;
        r8 = r2.D;
        r3 = r10;
        r3.a(r4, r5, r6, r7, r8);
        goto L_0x0161;
    L_0x0152:
        r2 = r2.z;
        r10.b(r2);
        goto L_0x0161;
    L_0x0158:
        r3 = r2.y;
        if (r3 == 0) goto L_0x0161;
    L_0x015c:
        r2 = r2.y;
        r10.a(r2);
    L_0x0161:
        r2 = r0.a;
        r2 = r2.r;
        r1.setCancelable(r2);
        r2 = r0.a;
        r2 = r2.r;
        if (r2 == 0) goto L_0x0171;
    L_0x016e:
        r1.setCanceledOnTouchOutside(r11);
    L_0x0171:
        r2 = r0.a;
        r2 = r2.s;
        r1.setOnCancelListener(r2);
        r2 = r0.a;
        r2 = r2.t;
        r1.setOnDismissListener(r2);
        r2 = r0.a;
        r2 = r2.u;
        if (r2 == 0) goto L_0x018c;
    L_0x0185:
        r2 = r0.a;
        r2 = r2.u;
        r1.setOnKeyListener(r2);
    L_0x018c:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.h.b():androidx.appcompat.app.g");
    }

    public final g c() {
        g b = b();
        b.show();
        return b;
    }
}
