package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import defpackage.dh;
import java.util.Arrays;

public abstract class ConstraintHelper extends View {
    protected int[] a;
    protected int b;
    protected Context c;
    protected dh d;
    protected boolean e;
    private String f;

    public void e() {
    }

    public void onDraw(Canvas canvas) {
    }

    public ConstraintHelper(Context context) {
        super(context);
        this.a = new int[32];
        this.e = false;
        this.c = context;
        a(null);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new int[32];
        this.e = false;
        this.c = context;
        a(attributeSet);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new int[32];
        this.e = false;
        this.c = context;
        a(attributeSet);
    }

    protected void a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, f.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == f.ConstraintLayout_Layout_constraint_referenced_ids) {
                    this.f = obtainStyledAttributes.getString(index);
                    b(this.f);
                }
            }
        }
    }

    public final int[] c() {
        return Arrays.copyOf(this.a, this.b);
    }

    public void setReferencedIds(int[] iArr) {
        int i = 0;
        this.b = 0;
        while (i < iArr.length) {
            setTag(iArr[i], null);
            i++;
        }
    }

    public void setTag(int i, Object obj) {
        if (this.b + 1 > this.a.length) {
            this.a = Arrays.copyOf(this.a, this.a.length * 2);
        }
        this.a[this.b] = i;
        this.b++;
    }

    protected void onMeasure(int i, int i2) {
        if (this.e) {
            super.onMeasure(i, i2);
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public final void d() {
        if (this.d != null) {
            LayoutParams layoutParams = getLayoutParams();
            if (layoutParams instanceof ConstraintLayout.LayoutParams) {
                ((ConstraintLayout.LayoutParams) layoutParams).al = this.d;
            }
        }
    }

    private void a(java.lang.String r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.constraintlayout.widget.ConstraintHelper.a(java.lang.String):void. bs: []
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
        r4 = this;
        if (r5 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r0 = r4.c;
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return;
    L_0x0008:
        r5 = r5.trim();
        r0 = 0;
        r1 = 0;
        r2 = androidx.constraintlayout.widget.e.class;	 Catch:{ Exception -> 0x0019 }
        r2 = r2.getField(r5);	 Catch:{ Exception -> 0x0019 }
        r2 = r2.getInt(r1);	 Catch:{ Exception -> 0x0019 }
        r0 = r2;
    L_0x0019:
        if (r0 != 0) goto L_0x002d;
    L_0x001b:
        r0 = r4.c;
        r0 = r0.getResources();
        r2 = "id";
        r3 = r4.c;
        r3 = r3.getPackageName();
        r0 = r0.getIdentifier(r5, r2, r3);
    L_0x002d:
        if (r0 != 0) goto L_0x0053;
    L_0x002f:
        r2 = r4.isInEditMode();
        if (r2 == 0) goto L_0x0053;
    L_0x0035:
        r2 = r4.getParent();
        r2 = r2 instanceof androidx.constraintlayout.widget.ConstraintLayout;
        if (r2 == 0) goto L_0x0053;
    L_0x003d:
        r2 = r4.getParent();
        r2 = (androidx.constraintlayout.widget.ConstraintLayout) r2;
        r2 = r2.a(r5);
        if (r2 == 0) goto L_0x0053;
    L_0x0049:
        r3 = r2 instanceof java.lang.Integer;
        if (r3 == 0) goto L_0x0053;
    L_0x004d:
        r2 = (java.lang.Integer) r2;
        r0 = r2.intValue();
    L_0x0053:
        if (r0 == 0) goto L_0x0059;
    L_0x0055:
        r4.setTag(r0, r1);
        return;
    L_0x0059:
        r0 = "ConstraintHelper";
        r1 = new java.lang.StringBuilder;
        r2 = "Could not find id of \"";
        r1.<init>(r2);
        r1.append(r5);
        r5 = "\"";
        r1.append(r5);
        r5 = r1.toString();
        android.util.Log.w(r0, r5);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintHelper.a(java.lang.String):void");
    }

    private void b(String str) {
        if (str != null) {
            int i = 0;
            while (true) {
                int indexOf = str.indexOf(44, i);
                if (indexOf == -1) {
                    a(str.substring(i));
                    return;
                } else {
                    a(str.substring(i, indexOf));
                    i = indexOf + 1;
                }
            }
        }
    }

    public void a(ConstraintLayout constraintLayout) {
        if (isInEditMode()) {
            b(this.f);
        }
        if (this.d != null) {
            this.d.I();
            for (int i = 0; i < this.b; i++) {
                View a = constraintLayout.a(this.a[i]);
                if (a != null) {
                    this.d.a(constraintLayout.a(a));
                }
            }
        }
    }
}
