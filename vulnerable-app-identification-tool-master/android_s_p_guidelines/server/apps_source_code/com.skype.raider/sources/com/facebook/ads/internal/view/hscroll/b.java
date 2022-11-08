package com.facebook.ads.internal.view.hscroll;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class b extends d implements com.facebook.ads.internal.view.hscroll.d.a {
    private final HScrollLinearLayoutManager K;
    private a L;
    private int M = -1;
    private int N = -1;
    private int O = 0;
    private int P = 0;

    public interface a {
    }

    public b(Context context) {
        super(context);
        this.K = new HScrollLinearLayoutManager(context, new c(), new a());
        u();
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.K = new HScrollLinearLayoutManager(context, new c(), new a());
        u();
    }

    public b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.K = new HScrollLinearLayoutManager(context, new c(), new a());
        u();
    }

    private void u() {
        this.K.b(0);
        setLayoutManager(this.K);
        setSaveEnabled(false);
        setSnapDelegate(this);
    }

    protected final void a(int i, boolean z) {
        super.a(i, z);
        if (i != this.M || this.N != 0) {
            this.M = i;
            this.N = 0;
        }
    }

    public final int g(int i) {
        int abs = Math.abs(i);
        return abs <= this.I ? 0 : this.O == 0 ? 1 : (abs / this.O) + 1;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int round = Math.round(((float) getMeasuredWidth()) / 1.91f);
        switch (MeasureSpec.getMode(i2)) {
            case Integer.MIN_VALUE:
                round = Math.min(MeasureSpec.getSize(i2), round);
                break;
            case ErrorDialogData.SUPPRESSED /*1073741824*/:
                round = MeasureSpec.getSize(i2);
                break;
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        round -= paddingTop;
        int i3 = this.P * 2;
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - i3;
        int a = c().a();
        int i4 = 0;
        int i5 = Integer.MAX_VALUE;
        while (i5 > round) {
            i4++;
            if (i4 >= a) {
                setMeasuredDimension(getMeasuredWidth(), round + paddingTop);
                setChildWidth(round + (this.P * 2));
            }
            i5 = (int) (((float) (measuredWidth - (i4 * i3))) / (((float) i4) + 0.333f));
        }
        round = i5;
        setMeasuredDimension(getMeasuredWidth(), round + paddingTop);
        setChildWidth(round + (this.P * 2));
    }

    public void setAdapter(@Nullable android.support.v7.widget.RecyclerView.a aVar) {
        this.K.a(aVar == null ? -1 : aVar.hashCode());
        super.setAdapter(aVar);
    }

    public void setChildSpacing(int i) {
        this.P = i;
    }

    public void setChildWidth(int i) {
        this.O = i;
        int measuredWidth = getMeasuredWidth();
        this.K.l((((measuredWidth - getPaddingLeft()) - getPaddingRight()) - this.O) / 2);
        this.K.a(((double) this.O) / ((double) measuredWidth));
    }

    public void setCurrentPosition(int i) {
        a(i, false);
    }

    public void setOnPageChangedListener(a aVar) {
        this.L = aVar;
    }

    public final int t() {
        return this.P;
    }
}
