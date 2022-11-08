package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class ContentFrameLayout extends FrameLayout {
    private TypedValue a;
    private TypedValue b;
    private TypedValue c;
    private TypedValue d;
    private TypedValue e;
    private TypedValue f;
    private final Rect g;
    private a h;

    public interface a {
        void a();
    }

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    public ContentFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.g = new Rect();
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final void a(Rect insets) {
        fitSystemWindows(insets);
    }

    public void setAttachListener(a attachListener) {
        this.h = attachListener;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void setDecorPadding(int left, int top, int right, int bottom) {
        this.g.set(left, top, right, bottom);
        if (ViewCompat.B(this)) {
            requestLayout();
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        boolean isPortrait = metrics.widthPixels < metrics.heightPixels;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        boolean fixedWidth = false;
        if (widthMode == Integer.MIN_VALUE) {
            TypedValue tvw = isPortrait ? this.d : this.c;
            if (!(tvw == null || tvw.type == 0)) {
                int w = 0;
                if (tvw.type == 5) {
                    w = (int) tvw.getDimension(metrics);
                } else if (tvw.type == 6) {
                    w = (int) tvw.getFraction((float) metrics.widthPixels, (float) metrics.widthPixels);
                }
                if (w > 0) {
                    widthMeasureSpec = MeasureSpec.makeMeasureSpec(Math.min(w - (this.g.left + this.g.right), MeasureSpec.getSize(widthMeasureSpec)), ErrorDialogData.SUPPRESSED);
                    fixedWidth = true;
                }
            }
        }
        if (heightMode == Integer.MIN_VALUE) {
            TypedValue tvh = isPortrait ? this.e : this.f;
            if (!(tvh == null || tvh.type == 0)) {
                int h = 0;
                if (tvh.type == 5) {
                    h = (int) tvh.getDimension(metrics);
                } else if (tvh.type == 6) {
                    h = (int) tvh.getFraction((float) metrics.heightPixels, (float) metrics.heightPixels);
                }
                if (h > 0) {
                    heightMeasureSpec = MeasureSpec.makeMeasureSpec(Math.min(h - (this.g.top + this.g.bottom), MeasureSpec.getSize(heightMeasureSpec)), ErrorDialogData.SUPPRESSED);
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        boolean measure = false;
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, ErrorDialogData.SUPPRESSED);
        if (!fixedWidth && widthMode == Integer.MIN_VALUE) {
            TypedValue tv = isPortrait ? this.b : this.a;
            if (!(tv == null || tv.type == 0)) {
                int min = 0;
                if (tv.type == 5) {
                    min = (int) tv.getDimension(metrics);
                } else if (tv.type == 6) {
                    min = (int) tv.getFraction((float) metrics.widthPixels, (float) metrics.widthPixels);
                }
                if (min > 0) {
                    min -= this.g.left + this.g.right;
                }
                if (width < min) {
                    widthMeasureSpec = MeasureSpec.makeMeasureSpec(min, ErrorDialogData.SUPPRESSED);
                    measure = true;
                }
            }
        }
        if (measure) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
