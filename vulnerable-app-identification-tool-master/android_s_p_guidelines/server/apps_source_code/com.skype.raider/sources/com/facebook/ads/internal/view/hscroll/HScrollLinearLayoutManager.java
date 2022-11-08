package com.facebook.ads.internal.view.hscroll;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.g;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.m;
import android.support.v7.widget.z;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class HScrollLinearLayoutManager extends LinearLayoutManager {
    private final c a;
    private final a b;
    private final Context c;
    private int[] d;
    private int e = 0;
    private float f = 50.0f;
    private a g;
    private int h;

    private class a extends z {
        final /* synthetic */ HScrollLinearLayoutManager f;

        public a(HScrollLinearLayoutManager hScrollLinearLayoutManager, Context context) {
            this.f = hScrollLinearLayoutManager;
            super(context);
        }

        protected final float a(DisplayMetrics displayMetrics) {
            return this.f.f / ((float) displayMetrics.densityDpi);
        }

        public final int a(View view, int i) {
            g c = c();
            if (!c.e()) {
                return 0;
            }
            h hVar = (h) view.getLayoutParams();
            return z.a(g.i(view) - hVar.leftMargin, hVar.rightMargin + g.k(view), c.x(), c.v() - c.z(), i) + this.f.e;
        }

        protected final int b() {
            return -1;
        }

        public final PointF c(int i) {
            return this.f.d(i);
        }
    }

    public HScrollLinearLayoutManager(Context context, c cVar, a aVar) {
        super(context);
        this.c = context;
        this.a = cVar;
        this.b = aVar;
        this.h = -1;
        this.g = new a(this, this.c);
    }

    public final void a(double d) {
        if (d <= 0.0d) {
            d = 1.0d;
        }
        this.f = (float) (50.0d / d);
        this.g = new a(this, this.c);
    }

    final void a(int i) {
        this.h = i;
    }

    public final void a(m mVar, State state, int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        if ((mode == ErrorDialogData.SUPPRESSED && g() == 1) || (mode2 == ErrorDialogData.SUPPRESSED && g() == 0)) {
            super.a(mVar, state, i, i2);
            return;
        }
        int[] a;
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (this.b.b(this.h)) {
            a = this.b.a(this.h);
        } else {
            int[] iArr = new int[]{0, 0};
            if (state.c() > 0) {
                int s = s() > 0 ? 1 : s();
                for (int i3 = 0; i3 < s; i3++) {
                    View c = c(i3);
                    h hVar = (h) c.getLayoutParams();
                    c.measure(ViewGroup.getChildMeasureSpec(MeasureSpec.makeMeasureSpec(0, 0), c.getPaddingLeft() + c.getPaddingRight(), hVar.width), ViewGroup.getChildMeasureSpec(MeasureSpec.makeMeasureSpec(0, 0), c.getPaddingTop() + c.getPaddingBottom(), hVar.height));
                    int[] iArr2 = new int[2];
                    iArr2[0] = (c.getMeasuredWidth() + hVar.leftMargin) + hVar.rightMargin;
                    iArr2[1] = hVar.topMargin + (c.getMeasuredHeight() + hVar.bottomMargin);
                    this.d = iArr2;
                    if (g() == 0) {
                        iArr[0] = iArr[0] + this.d[0];
                        if (i3 == 0) {
                            iArr[1] = (this.d[1] + y()) + A();
                        }
                    } else {
                        iArr[1] = iArr[1] + this.d[1];
                        if (i3 == 0) {
                            iArr[0] = (this.d[0] + x()) + z();
                        }
                    }
                }
                if (this.h != -1) {
                    this.b.a(this.h, iArr);
                }
            }
            a = iArr;
        }
        if (mode == ErrorDialogData.SUPPRESSED) {
            a[0] = size;
        }
        if (mode2 == ErrorDialogData.SUPPRESSED) {
            a[1] = size2;
        }
        h(a[0], a[1]);
    }

    public final void a(RecyclerView recyclerView, int i) {
        this.g.d(i);
        a(this.g);
    }

    public final void e(int i) {
        super.e(i, this.e);
    }

    public final void l(int i) {
        this.e = i;
    }
}
