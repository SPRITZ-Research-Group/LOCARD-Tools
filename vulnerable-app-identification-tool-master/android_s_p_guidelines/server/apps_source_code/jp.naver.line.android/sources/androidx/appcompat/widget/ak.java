package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.core.widget.d;
import androidx.core.widget.l;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.v;
import java.lang.ref.WeakReference;

final class ak {
    private final TextView a;
    private bs b;
    private bs c;
    private bs d;
    private bs e;
    private bs f;
    private bs g;
    private final al h;
    private int i = 0;
    private Typeface j;
    private boolean k;

    ak(TextView textView) {
        this.a = textView;
        this.h = new al(this.a);
    }

    @SuppressLint({"NewApi"})
    final void a(AttributeSet attributeSet, int i) {
        bu a;
        Object obj;
        boolean z;
        ColorStateList e;
        ColorStateList e2;
        AttributeSet attributeSet2 = attributeSet;
        int i2 = i;
        Context context = this.a.getContext();
        y a2 = y.a();
        bu a3 = bu.a(context, attributeSet2, v.AppCompatTextHelper, i2, 0);
        int g = a3.g(v.AppCompatTextHelper_android_textAppearance, -1);
        if (a3.h(v.AppCompatTextHelper_android_drawableLeft)) {
            r0.b = a(context, a2, a3.g(v.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (a3.h(v.AppCompatTextHelper_android_drawableTop)) {
            r0.c = a(context, a2, a3.g(v.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (a3.h(v.AppCompatTextHelper_android_drawableRight)) {
            r0.d = a(context, a2, a3.g(v.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (a3.h(v.AppCompatTextHelper_android_drawableBottom)) {
            r0.e = a(context, a2, a3.g(v.AppCompatTextHelper_android_drawableBottom, 0));
        }
        if (VERSION.SDK_INT >= 17) {
            if (a3.h(v.AppCompatTextHelper_android_drawableStart)) {
                r0.f = a(context, a2, a3.g(v.AppCompatTextHelper_android_drawableStart, 0));
            }
            if (a3.h(v.AppCompatTextHelper_android_drawableEnd)) {
                r0.g = a(context, a2, a3.g(v.AppCompatTextHelper_android_drawableEnd, 0));
            }
        }
        a3.a();
        boolean z2 = r0.a.getTransformationMethod() instanceof PasswordTransformationMethod;
        Object obj2 = 1;
        ColorStateList colorStateList = null;
        if (g != -1) {
            a = bu.a(context, g, v.TextAppearance);
            if (z2 || !a.h(v.TextAppearance_textAllCaps)) {
                obj = null;
                z = false;
            } else {
                z = a.a(v.TextAppearance_textAllCaps, false);
                obj = 1;
            }
            a(context, a);
            if (VERSION.SDK_INT < 23) {
                e = a.h(v.TextAppearance_android_textColor) ? a.e(v.TextAppearance_android_textColor) : null;
                e2 = a.h(v.TextAppearance_android_textColorHint) ? a.e(v.TextAppearance_android_textColorHint) : null;
                if (a.h(v.TextAppearance_android_textColorLink)) {
                    colorStateList = a.e(v.TextAppearance_android_textColorLink);
                }
                ColorStateList colorStateList2 = e;
                e = colorStateList;
                colorStateList = colorStateList2;
            } else {
                e = null;
                e2 = e;
            }
            a.a();
        } else {
            e = null;
            e2 = e;
            obj = null;
            z = false;
        }
        a = bu.a(context, attributeSet2, v.TextAppearance, i2, 0);
        if (z2 || !a.h(v.TextAppearance_textAllCaps)) {
            obj2 = obj;
        } else {
            z = a.a(v.TextAppearance_textAllCaps, false);
        }
        if (VERSION.SDK_INT < 23) {
            if (a.h(v.TextAppearance_android_textColor)) {
                colorStateList = a.e(v.TextAppearance_android_textColor);
            }
            if (a.h(v.TextAppearance_android_textColorHint)) {
                e2 = a.e(v.TextAppearance_android_textColorHint);
            }
            if (a.h(v.TextAppearance_android_textColorLink)) {
                e = a.e(v.TextAppearance_android_textColorLink);
            }
        }
        if (VERSION.SDK_INT >= 28 && a.h(v.TextAppearance_android_textSize) && a.e(v.TextAppearance_android_textSize, -1) == 0) {
            r0.a.setTextSize(0, BitmapDescriptorFactory.HUE_RED);
        }
        a(context, a);
        a.a();
        if (colorStateList != null) {
            r0.a.setTextColor(colorStateList);
        }
        if (e2 != null) {
            r0.a.setHintTextColor(e2);
        }
        if (e != null) {
            r0.a.setLinkTextColor(e);
        }
        if (!(z2 || obj2 == null)) {
            a(z);
        }
        if (r0.j != null) {
            r0.a.setTypeface(r0.j, r0.i);
        }
        r0.h.a(attributeSet2, i2);
        if (d.d && r0.h.a() != 0) {
            int[] e3 = r0.h.e();
            if (e3.length > 0) {
                if (((float) r0.a.getAutoSizeStepGranularity()) != -1.0f) {
                    r0.a.setAutoSizeTextTypeUniformWithConfiguration(r0.h.c(), r0.h.d(), r0.h.b(), 0);
                } else {
                    r0.a.setAutoSizeTextTypeUniformWithPresetSizes(e3, 0);
                }
            }
        }
        bu a4 = bu.a(context, attributeSet2, v.AppCompatTextView);
        i2 = a4.e(v.AppCompatTextView_firstBaselineToTopHeight, -1);
        int e4 = a4.e(v.AppCompatTextView_lastBaselineToBottomHeight, -1);
        int e5 = a4.e(v.AppCompatTextView_lineHeight, -1);
        a4.a();
        if (i2 != -1) {
            l.b(r0.a, i2);
        }
        if (e4 != -1) {
            l.c(r0.a, e4);
        }
        if (e5 != -1) {
            l.d(r0.a, e5);
        }
    }

    private void a(android.content.Context r5, androidx.appcompat.widget.bu r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.appcompat.widget.ak.a(android.content.Context, androidx.appcompat.widget.bu):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
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
        r4 = this;
        r0 = defpackage.v.TextAppearance_android_textStyle;
        r1 = r4.i;
        r0 = r6.a(r0, r1);
        r4.i = r0;
        r0 = defpackage.v.TextAppearance_android_fontFamily;
        r0 = r6.h(r0);
        r1 = 1;
        r2 = 0;
        if (r0 != 0) goto L_0x0041;
    L_0x0014:
        r0 = defpackage.v.TextAppearance_fontFamily;
        r0 = r6.h(r0);
        if (r0 == 0) goto L_0x001d;
    L_0x001c:
        goto L_0x0041;
    L_0x001d:
        r5 = defpackage.v.TextAppearance_android_typeface;
        r5 = r6.h(r5);
        if (r5 == 0) goto L_0x0040;
    L_0x0025:
        r4.k = r2;
        r5 = defpackage.v.TextAppearance_android_typeface;
        r5 = r6.a(r5, r1);
        switch(r5) {
            case 1: goto L_0x003b;
            case 2: goto L_0x0036;
            case 3: goto L_0x0031;
            default: goto L_0x0030;
        };
    L_0x0030:
        goto L_0x0040;
    L_0x0031:
        r5 = android.graphics.Typeface.MONOSPACE;
        r4.j = r5;
        goto L_0x0040;
    L_0x0036:
        r5 = android.graphics.Typeface.SERIF;
        r4.j = r5;
        return;
    L_0x003b:
        r5 = android.graphics.Typeface.SANS_SERIF;
        r4.j = r5;
        return;
    L_0x0040:
        return;
    L_0x0041:
        r0 = 0;
        r4.j = r0;
        r0 = defpackage.v.TextAppearance_fontFamily;
        r0 = r6.h(r0);
        if (r0 == 0) goto L_0x004f;
    L_0x004c:
        r0 = defpackage.v.TextAppearance_fontFamily;
        goto L_0x0051;
    L_0x004f:
        r0 = defpackage.v.TextAppearance_android_fontFamily;
    L_0x0051:
        r5 = r5.isRestricted();
        if (r5 != 0) goto L_0x0075;
    L_0x0057:
        r5 = new java.lang.ref.WeakReference;
        r3 = r4.a;
        r5.<init>(r3);
        r3 = new androidx.appcompat.widget.ak$1;
        r3.<init>(r4, r5);
        r5 = r4.i;	 Catch:{ UnsupportedOperationException -> 0x0074, UnsupportedOperationException -> 0x0074 }
        r5 = r6.a(r0, r5, r3);	 Catch:{ UnsupportedOperationException -> 0x0074, UnsupportedOperationException -> 0x0074 }
        r4.j = r5;	 Catch:{ UnsupportedOperationException -> 0x0074, UnsupportedOperationException -> 0x0074 }
        r5 = r4.j;	 Catch:{ UnsupportedOperationException -> 0x0074, UnsupportedOperationException -> 0x0074 }
        if (r5 != 0) goto L_0x0070;	 Catch:{ UnsupportedOperationException -> 0x0074, UnsupportedOperationException -> 0x0074 }
    L_0x006f:
        goto L_0x0071;	 Catch:{ UnsupportedOperationException -> 0x0074, UnsupportedOperationException -> 0x0074 }
    L_0x0070:
        r1 = 0;	 Catch:{ UnsupportedOperationException -> 0x0074, UnsupportedOperationException -> 0x0074 }
    L_0x0071:
        r4.k = r1;	 Catch:{ UnsupportedOperationException -> 0x0074, UnsupportedOperationException -> 0x0074 }
        goto L_0x0075;
    L_0x0075:
        r5 = r4.j;
        if (r5 != 0) goto L_0x0087;
    L_0x0079:
        r5 = r6.d(r0);
        if (r5 == 0) goto L_0x0087;
    L_0x007f:
        r6 = r4.i;
        r5 = android.graphics.Typeface.create(r5, r6);
        r4.j = r5;
    L_0x0087:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ak.a(android.content.Context, androidx.appcompat.widget.bu):void");
    }

    final void a(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.k) {
            this.j = typeface;
            TextView textView = (TextView) weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.i);
            }
        }
    }

    final void a(Context context, int i) {
        bu a = bu.a(context, i, v.TextAppearance);
        if (a.h(v.TextAppearance_textAllCaps)) {
            a(a.a(v.TextAppearance_textAllCaps, false));
        }
        if (VERSION.SDK_INT < 23 && a.h(v.TextAppearance_android_textColor)) {
            ColorStateList e = a.e(v.TextAppearance_android_textColor);
            if (e != null) {
                this.a.setTextColor(e);
            }
        }
        if (a.h(v.TextAppearance_android_textSize) && a.e(v.TextAppearance_android_textSize, -1) == 0) {
            this.a.setTextSize(0, BitmapDescriptorFactory.HUE_RED);
        }
        a(context, a);
        a.a();
        if (this.j != null) {
            this.a.setTypeface(this.j, this.i);
        }
    }

    final void a(boolean z) {
        this.a.setAllCaps(z);
    }

    final void a() {
        Drawable[] compoundDrawables;
        if (!(this.b == null && this.c == null && this.d == null && this.e == null)) {
            compoundDrawables = this.a.getCompoundDrawables();
            a(compoundDrawables[0], this.b);
            a(compoundDrawables[1], this.c);
            a(compoundDrawables[2], this.d);
            a(compoundDrawables[3], this.e);
        }
        if (VERSION.SDK_INT < 17) {
            return;
        }
        if (this.f != null || this.g != null) {
            compoundDrawables = this.a.getCompoundDrawablesRelative();
            a(compoundDrawables[0], this.f);
            a(compoundDrawables[2], this.g);
        }
    }

    private void a(Drawable drawable, bs bsVar) {
        if (drawable != null && bsVar != null) {
            y.a(drawable, bsVar, this.a.getDrawableState());
        }
    }

    private static bs a(Context context, y yVar, int i) {
        ColorStateList b = yVar.b(context, i);
        if (b == null) {
            return null;
        }
        bs bsVar = new bs();
        bsVar.d = true;
        bsVar.a = b;
        return bsVar;
    }

    final void b() {
        if (!d.d) {
            this.h.f();
        }
    }

    final void a(int i, float f) {
        if (!d.d && !this.h.g()) {
            this.h.a(i, f);
        }
    }

    final void c() {
        this.h.f();
    }

    final boolean d() {
        return this.h.g();
    }

    final void a(int i) {
        this.h.a(i);
    }

    final void a(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        this.h.a(i, i2, i3, i4);
    }

    final void a(int[] iArr, int i) throws IllegalArgumentException {
        this.h.a(iArr, i);
    }

    final int e() {
        return this.h.a();
    }

    final int f() {
        return this.h.b();
    }

    final int g() {
        return this.h.c();
    }

    final int h() {
        return this.h.d();
    }

    final int[] i() {
        return this.h.e();
    }
}
