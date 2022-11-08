package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.HorizontalScrollView;
import android.widget.Spinner;
import androidx.appcompat.app.a;
import androidx.appcompat.widget.LinearLayoutCompat.LayoutParams;
import defpackage.an;
import defpackage.m;

public class ScrollingTabContainerView extends HorizontalScrollView implements OnItemSelectedListener {
    private static final Interpolator k = new DecelerateInterpolator();
    Runnable a;
    LinearLayoutCompat b;
    int c;
    int d;
    protected ViewPropertyAnimator e;
    protected final bi f = new bi(this);
    private Spinner g;
    private boolean h;
    private int i;
    private int j;

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        setHorizontalScrollBarEnabled(false);
        an a = an.a(context);
        setContentHeight(a.e());
        this.d = a.g();
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getContext(), null, m.actionBarTabBarStyle);
        linearLayoutCompat.setMeasureWithLargestChildEnabled(true);
        linearLayoutCompat.setGravity(17);
        linearLayoutCompat.setLayoutParams(new LayoutParams(-2, -1));
        this.b = linearLayoutCompat;
        addView(this.b, new ViewGroup.LayoutParams(-2, -1));
    }

    public void onMeasure(int i, int i2) {
        i2 = MeasureSpec.getMode(i);
        Object obj = 1;
        boolean z = i2 == 1073741824;
        setFillViewport(z);
        int childCount = this.b.getChildCount();
        if (childCount <= 1 || !(i2 == 1073741824 || i2 == Integer.MIN_VALUE)) {
            this.c = -1;
        } else {
            if (childCount > 2) {
                this.c = (int) (((float) MeasureSpec.getSize(i)) * 0.4f);
            } else {
                this.c = MeasureSpec.getSize(i) / 2;
            }
            this.c = Math.min(this.c, this.d);
        }
        i2 = MeasureSpec.makeMeasureSpec(this.i, 1073741824);
        if (z || !this.h) {
            obj = null;
        }
        if (obj != null) {
            this.b.measure(0, i2);
            if (this.b.getMeasuredWidth() <= MeasureSpec.getSize(i)) {
                b();
            } else if (!a()) {
                if (this.g == null) {
                    Spinner appCompatSpinner = new AppCompatSpinner(getContext(), null, m.actionDropDownStyle);
                    appCompatSpinner.setLayoutParams(new LayoutParams(-2, -1));
                    appCompatSpinner.setOnItemSelectedListener(this);
                    this.g = appCompatSpinner;
                }
                removeView(this.b);
                addView(this.g, new ViewGroup.LayoutParams(-2, -1));
                if (this.g.getAdapter() == null) {
                    this.g.setAdapter(new bg(this));
                }
                if (this.a != null) {
                    removeCallbacks(this.a);
                    this.a = null;
                }
                this.g.setSelection(this.j);
            }
        } else {
            b();
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i, i2);
        i = getMeasuredWidth();
        if (z && measuredWidth != i) {
            setTabSelected(this.j);
        }
    }

    private boolean a() {
        return this.g != null && this.g.getParent() == this;
    }

    public void setAllowCollapse(boolean z) {
        this.h = z;
    }

    private boolean b() {
        if (!a()) {
            return false;
        }
        removeView(this.g);
        addView(this.b, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.g.getSelectedItemPosition());
        return false;
    }

    public void setTabSelected(int i) {
        this.j = i;
        int childCount = this.b.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = this.b.getChildAt(i2);
            boolean z = i2 == i;
            childAt.setSelected(z);
            if (z) {
                childAt = this.b.getChildAt(i);
                if (this.a != null) {
                    removeCallbacks(this.a);
                }
                this.a = new Runnable(this) {
                    final /* synthetic */ ScrollingTabContainerView b;

                    public final void run() {
                        this.b.smoothScrollTo(childAt.getLeft() - ((this.b.getWidth() - childAt.getWidth()) / 2), 0);
                        this.b.a = null;
                    }
                };
                post(this.a);
            }
            i2++;
        }
        if (this.g != null && i >= 0) {
            this.g.setSelection(i);
        }
    }

    public void setContentHeight(int i) {
        this.i = i;
        requestLayout();
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        an a = an.a(getContext());
        setContentHeight(a.e());
        this.d = a.g();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a != null) {
            post(this.a);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            removeCallbacks(this.a);
        }
    }

    final bh a(a aVar) {
        bh bhVar = new bh(this, getContext(), aVar);
        bhVar.setBackgroundDrawable(null);
        bhVar.setLayoutParams(new AbsListView.LayoutParams(-1, this.i));
        return bhVar;
    }
}
