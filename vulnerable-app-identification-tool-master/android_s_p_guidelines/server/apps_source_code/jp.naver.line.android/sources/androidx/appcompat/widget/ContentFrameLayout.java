package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import defpackage.hs;

public class ContentFrameLayout extends FrameLayout {
    private TypedValue a;
    private TypedValue b;
    private TypedValue c;
    private TypedValue d;
    private TypedValue e;
    private TypedValue f;
    private final Rect g;
    private am h;

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = new Rect();
    }

    public final void a(Rect rect) {
        fitSystemWindows(rect);
    }

    public void setAttachListener(am amVar) {
        this.h = amVar;
    }

    public void setDecorPadding(int i, int i2, int i3, int i4) {
        this.g.set(i, i2, i3, i4);
        if (hs.C(this)) {
            requestLayout();
        }
    }

    protected void onMeasure(int i, int i2) {
        int dimension;
        Object obj;
        TypedValue typedValue;
        TypedValue typedValue2;
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        Object obj2 = 1;
        Object obj3 = displayMetrics.widthPixels < displayMetrics.heightPixels ? 1 : null;
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            TypedValue typedValue3 = obj3 != null ? this.d : this.c;
            if (!(typedValue3 == null || typedValue3.type == 0)) {
                dimension = typedValue3.type == 5 ? (int) typedValue3.getDimension(displayMetrics) : typedValue3.type == 6 ? (int) typedValue3.getFraction((float) displayMetrics.widthPixels, (float) displayMetrics.widthPixels) : 0;
                if (dimension > 0) {
                    dimension = MeasureSpec.makeMeasureSpec(Math.min(dimension - (this.g.left + this.g.right), MeasureSpec.getSize(i)), 1073741824);
                    obj = 1;
                    if (mode2 == Integer.MIN_VALUE) {
                        typedValue = obj3 == null ? this.e : this.f;
                        if (!(typedValue == null || typedValue.type == 0)) {
                            mode2 = typedValue.type != 5 ? (int) typedValue.getDimension(displayMetrics) : typedValue.type != 6 ? (int) typedValue.getFraction((float) displayMetrics.heightPixels, (float) displayMetrics.heightPixels) : 0;
                            if (mode2 > 0) {
                                i2 = MeasureSpec.makeMeasureSpec(Math.min(mode2 - (this.g.top + this.g.bottom), MeasureSpec.getSize(i2)), 1073741824);
                            }
                        }
                    }
                    super.onMeasure(dimension, i2);
                    mode2 = getMeasuredWidth();
                    dimension = MeasureSpec.makeMeasureSpec(mode2, 1073741824);
                    if (obj == null && mode == Integer.MIN_VALUE) {
                        typedValue2 = obj3 == null ? this.b : this.a;
                        if (!(typedValue2 == null || typedValue2.type == 0)) {
                            i = typedValue2.type != 5 ? (int) typedValue2.getDimension(displayMetrics) : typedValue2.type != 6 ? (int) typedValue2.getFraction((float) displayMetrics.widthPixels, (float) displayMetrics.widthPixels) : 0;
                            if (i > 0) {
                                i -= this.g.left + this.g.right;
                            }
                            if (mode2 < i) {
                                dimension = MeasureSpec.makeMeasureSpec(i, 1073741824);
                                if (obj2 == null) {
                                    super.onMeasure(dimension, i2);
                                }
                            }
                        }
                    }
                    obj2 = null;
                    if (obj2 == null) {
                        super.onMeasure(dimension, i2);
                    }
                }
            }
        }
        dimension = i;
        obj = null;
        if (mode2 == Integer.MIN_VALUE) {
            if (obj3 == null) {
            }
            if (typedValue.type != 5) {
                if (typedValue.type != 6) {
                }
            }
            if (mode2 > 0) {
                i2 = MeasureSpec.makeMeasureSpec(Math.min(mode2 - (this.g.top + this.g.bottom), MeasureSpec.getSize(i2)), 1073741824);
            }
        }
        super.onMeasure(dimension, i2);
        mode2 = getMeasuredWidth();
        dimension = MeasureSpec.makeMeasureSpec(mode2, 1073741824);
        if (obj3 == null) {
        }
        if (typedValue2.type != 5) {
            if (typedValue2.type != 6) {
            }
        }
        if (i > 0) {
            i -= this.g.left + this.g.right;
        }
        if (mode2 < i) {
            dimension = MeasureSpec.makeMeasureSpec(i, 1073741824);
            if (obj2 == null) {
                super.onMeasure(dimension, i2);
            }
        }
        obj2 = null;
        if (obj2 == null) {
            super.onMeasure(dimension, i2);
        }
    }

    public final TypedValue a() {
        if (this.a == null) {
            this.a = new TypedValue();
        }
        return this.a;
    }

    public final TypedValue b() {
        if (this.b == null) {
            this.b = new TypedValue();
        }
        return this.b;
    }

    public final TypedValue c() {
        if (this.c == null) {
            this.c = new TypedValue();
        }
        return this.c;
    }

    public final TypedValue d() {
        if (this.d == null) {
            this.d = new TypedValue();
        }
        return this.d;
    }

    public final TypedValue e() {
        if (this.e == null) {
            this.e = new TypedValue();
        }
        return this.e;
    }

    public final TypedValue f() {
        if (this.f == null) {
            this.f = new TypedValue();
        }
        return this.f;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.h != null) {
            this.h.a();
        }
    }
}
