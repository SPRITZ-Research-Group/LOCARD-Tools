package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.hs;
import defpackage.hz;
import defpackage.m;
import defpackage.v;

abstract class a extends ViewGroup {
    protected final b a;
    protected final Context b;
    protected ActionMenuView c;
    protected ActionMenuPresenter d;
    protected int e;
    protected hz f;
    private boolean g;
    private boolean h;

    protected static int a(int i, int i2, boolean z) {
        return z ? i - i2 : i + i2;
    }

    a(Context context) {
        this(context, null);
    }

    a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new b(this);
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(m.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.b = context;
        } else {
            this.b = new ContextThemeWrapper(context, typedValue.resourceId);
        }
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, v.ActionBar, m.actionBarStyle, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(v.ActionBar_height, 0));
        obtainStyledAttributes.recycle();
        if (this.d != null) {
            this.d.b();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.g = false;
        }
        if (!this.g) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.g = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.g = false;
        }
        return true;
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.h = false;
        }
        if (!this.h) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.h = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.h = false;
        }
        return true;
    }

    public void setContentHeight(int i) {
        this.e = i;
        requestLayout();
    }

    public hz a(int i, long j) {
        if (this.f != null) {
            this.f.e();
        }
        hz a;
        if (i == 0) {
            if (getVisibility() != 0) {
                setAlpha(BitmapDescriptorFactory.HUE_RED);
            }
            a = hs.o(this).a(1.0f);
            a.a(j);
            a.a(this.a.a(a, i));
            return a;
        }
        a = hs.o(this).a((float) BitmapDescriptorFactory.HUE_RED);
        a.a(j);
        a.a(this.a.a(a, i));
        return a;
    }

    public void setVisibility(int i) {
        if (i != getVisibility()) {
            if (this.f != null) {
                this.f.e();
            }
            super.setVisibility(i);
        }
    }

    public boolean a() {
        return this.d != null ? this.d.e() : false;
    }

    protected static int a(View view, int i, int i2) {
        view.measure(MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - 0);
    }

    protected static int a(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        i2 += (i3 - measuredHeight) / 2;
        if (z) {
            view.layout(i - measuredWidth, i2, i, measuredHeight + i2);
        } else {
            view.layout(i, i2, i + measuredWidth, measuredHeight + i2);
        }
        return z ? -measuredWidth : measuredWidth;
    }
}
