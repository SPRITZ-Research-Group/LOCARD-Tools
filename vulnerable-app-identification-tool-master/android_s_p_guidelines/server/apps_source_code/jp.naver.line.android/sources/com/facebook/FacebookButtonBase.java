package com.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.facebook.common.h;
import com.facebook.internal.ad;
import defpackage.amm;

public abstract class FacebookButtonBase extends Button {
    private String a;
    private String b;
    private OnClickListener c;
    private OnClickListener d;
    private boolean e;
    private int f;
    private int g;
    private ad h;

    protected abstract int a();

    protected void a(android.content.Context r10, android.util.AttributeSet r11, int r12, int r13) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r10_4 ?) in PHI: PHI: (r10_7 ?) = (r10_4 ?), (r10_0 android.content.Context) binds: {(r10_0 android.content.Context)=B:33:0x0109}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ssa.SSATransform.inlinePhiInsn(SSATransform.java:391)
	at jadx.core.dex.visitors.ssa.SSATransform.replacePhiWithMove(SSATransform.java:359)
	at jadx.core.dex.visitors.ssa.SSATransform.fixPhiWithSameArgs(SSATransform.java:299)
	at jadx.core.dex.visitors.ssa.SSATransform.fixUselessPhi(SSATransform.java:274)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:60)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
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
        r9 = this;
        r0 = isInEditMode();
        r1 = 1;
        r2 = 0;
        if (r0 != 0) goto L_0x0041;
    L_0x0008:
        r0 = new int[r1];
        r3 = 16842964; // 0x10100d4 float:2.3694152E-38 double:8.32153E-317;
        r0[r2] = r3;
        r3 = r10.getTheme();
        r0 = r3.obtainStyledAttributes(r11, r0, r12, r13);
        r3 = r0.hasValue(r2);	 Catch:{ all -> 0x003c }
        if (r3 == 0) goto L_0x002f;	 Catch:{ all -> 0x003c }
    L_0x001d:
        r3 = r0.getResourceId(r2, r2);	 Catch:{ all -> 0x003c }
        if (r3 == 0) goto L_0x0027;	 Catch:{ all -> 0x003c }
    L_0x0023:
        setBackgroundResource(r3);	 Catch:{ all -> 0x003c }
        goto L_0x0038;	 Catch:{ all -> 0x003c }
    L_0x0027:
        r3 = r0.getColor(r2, r2);	 Catch:{ all -> 0x003c }
        setBackgroundColor(r3);	 Catch:{ all -> 0x003c }
        goto L_0x0038;	 Catch:{ all -> 0x003c }
    L_0x002f:
        r3 = com.facebook.common.b.com_facebook_blue;	 Catch:{ all -> 0x003c }
        r3 = androidx.core.content.a.c(r10, r3);	 Catch:{ all -> 0x003c }
        setBackgroundColor(r3);	 Catch:{ all -> 0x003c }
    L_0x0038:
        r0.recycle();
        goto L_0x0041;
    L_0x003c:
        r10 = move-exception;
        r0.recycle();
        throw r10;
    L_0x0041:
        r0 = 5;
        r0 = new int[r0];
        r0 = {16843119, 16843117, 16843120, 16843118, 16843121};
        r3 = r10.getTheme();
        r0 = r3.obtainStyledAttributes(r11, r0, r12, r13);
        r3 = r0.getResourceId(r2, r2);	 Catch:{ all -> 0x010d }
        r4 = r0.getResourceId(r1, r2);	 Catch:{ all -> 0x010d }
        r5 = 2;	 Catch:{ all -> 0x010d }
        r6 = r0.getResourceId(r5, r2);	 Catch:{ all -> 0x010d }
        r7 = 3;	 Catch:{ all -> 0x010d }
        r8 = r0.getResourceId(r7, r2);	 Catch:{ all -> 0x010d }
        setCompoundDrawablesWithIntrinsicBounds(r3, r4, r6, r8);	 Catch:{ all -> 0x010d }
        r3 = 4;	 Catch:{ all -> 0x010d }
        r4 = r0.getDimensionPixelSize(r3, r2);	 Catch:{ all -> 0x010d }
        setCompoundDrawablePadding(r4);	 Catch:{ all -> 0x010d }
        r0.recycle();
        r0 = new int[r3];
        r0 = {16842966, 16842967, 16842968, 16842969};
        r3 = r10.getTheme();
        r0 = r3.obtainStyledAttributes(r11, r0, r12, r13);
        r3 = r0.getDimensionPixelSize(r2, r2);	 Catch:{ all -> 0x0108 }
        r4 = r0.getDimensionPixelSize(r1, r2);	 Catch:{ all -> 0x0108 }
        r6 = r0.getDimensionPixelSize(r5, r2);	 Catch:{ all -> 0x0108 }
        r8 = r0.getDimensionPixelSize(r7, r2);	 Catch:{ all -> 0x0108 }
        setPadding(r3, r4, r6, r8);	 Catch:{ all -> 0x0108 }
        r0.recycle();
        r0 = new int[r1];
        r3 = 16842904; // 0x1010098 float:2.3693984E-38 double:8.3215E-317;
        r0[r2] = r3;
        r3 = r10.getTheme();
        r0 = r3.obtainStyledAttributes(r11, r0, r12, r13);
        r3 = r0.getColorStateList(r2);	 Catch:{ all -> 0x0103 }
        setTextColor(r3);	 Catch:{ all -> 0x0103 }
        r0.recycle();
        r0 = new int[r1];
        r3 = 16842927; // 0x10100af float:2.3694048E-38 double:8.3215116E-317;
        r0[r2] = r3;
        r3 = r10.getTheme();
        r0 = r3.obtainStyledAttributes(r11, r0, r12, r13);
        r3 = 17;
        r3 = r0.getInt(r2, r3);	 Catch:{ all -> 0x00fe }
        setGravity(r3);	 Catch:{ all -> 0x00fe }
        r0.recycle();
        r0 = new int[r7];
        r0 = {16842901, 16842903, 16843087};
        r10 = r10.getTheme();
        r10 = r10.obtainStyledAttributes(r11, r0, r12, r13);
        r11 = r10.getDimensionPixelSize(r2, r2);	 Catch:{ all -> 0x00f9 }
        r11 = (float) r11;	 Catch:{ all -> 0x00f9 }
        setTextSize(r2, r11);	 Catch:{ all -> 0x00f9 }
        r11 = r10.getInt(r1, r1);	 Catch:{ all -> 0x00f9 }
        r11 = android.graphics.Typeface.defaultFromStyle(r11);	 Catch:{ all -> 0x00f9 }
        setTypeface(r11);	 Catch:{ all -> 0x00f9 }
        r11 = r10.getString(r5);	 Catch:{ all -> 0x00f9 }
        setText(r11);	 Catch:{ all -> 0x00f9 }
        r10.recycle();
        r10 = new com.facebook.FacebookButtonBase$1;
        r10.<init>(r9);
        super.setOnClickListener(r10);
        return;
    L_0x00f9:
        r11 = move-exception;
        r10.recycle();
        throw r11;
    L_0x00fe:
        r10 = move-exception;
        r0.recycle();
        throw r10;
    L_0x0103:
        r10 = move-exception;
        r0.recycle();
        throw r10;
    L_0x0108:
        r10 = move-exception;
        r0.recycle();
        throw r10;
    L_0x010d:
        r10 = move-exception;
        r0.recycle();
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookButtonBase.a(android.content.Context, android.util.AttributeSet, int, int):void");
    }

    protected int f() {
        return 0;
    }

    protected FacebookButtonBase(Context context, AttributeSet attributeSet, int i, String str, String str2) {
        super(context, attributeSet, 0);
        int f = f();
        if (f == 0) {
            f = h.com_facebook_button;
        }
        a(context, attributeSet, i, f);
        this.a = str;
        this.b = str2;
        setClickable(true);
        setFocusable(true);
    }

    public void setFragment(Fragment fragment) {
        this.h = new ad(fragment);
    }

    public void setFragment(android.app.Fragment fragment) {
        this.h = new ad(fragment);
    }

    public final Fragment b() {
        return this.h != null ? this.h.b() : null;
    }

    public final android.app.Fragment c() {
        return this.h != null ? this.h.a() : null;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.c = onClickListener;
    }

    public int d() {
        return a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            amm.a(getContext()).b(this.a, null);
        }
    }

    protected void onDraw(Canvas canvas) {
        if (((getGravity() & 1) != 0 ? 1 : null) != null) {
            int compoundPaddingLeft = getCompoundPaddingLeft();
            int compoundPaddingRight = getCompoundPaddingRight();
            int min = Math.min((((getWidth() - (getCompoundDrawablePadding() + compoundPaddingLeft)) - compoundPaddingRight) - a(getText().toString())) / 2, (compoundPaddingLeft - getPaddingLeft()) / 2);
            this.f = compoundPaddingLeft - min;
            this.g = compoundPaddingRight + min;
            this.e = true;
        }
        super.onDraw(canvas);
        this.e = false;
    }

    public int getCompoundPaddingLeft() {
        if (this.e) {
            return this.f;
        }
        return super.getCompoundPaddingLeft();
    }

    public int getCompoundPaddingRight() {
        if (this.e) {
            return this.g;
        }
        return super.getCompoundPaddingRight();
    }

    protected final Activity e() {
        boolean z;
        Context context = getContext();
        while (true) {
            z = context instanceof Activity;
            if (!z && (context instanceof ContextWrapper)) {
                context = ((ContextWrapper) context).getBaseContext();
            } else if (z) {
                return (Activity) context;
            } else {
                throw new n("Unable to get Activity.");
            }
        }
        if (z) {
            return (Activity) context;
        }
        throw new n("Unable to get Activity.");
    }

    protected final int a(String str) {
        return (int) Math.ceil((double) getPaint().measureText(str));
    }

    protected final void a(View view) {
        if (this.c != null) {
            this.c.onClick(view);
        }
    }

    protected final void a(OnClickListener onClickListener) {
        this.d = onClickListener;
    }
}
