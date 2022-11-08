package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.a.j;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;

public class ForegroundLinearLayout extends LinearLayoutCompat {
    protected boolean a;
    boolean b;
    private Drawable c;
    private final Rect d;
    private final Rect e;
    private int f;

    public ForegroundLinearLayout(Context context) {
        super(context);
        this.d = new Rect();
        this.e = new Rect();
        this.f = 119;
        this.a = true;
        this.b = false;
    }

    public ForegroundLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ForegroundLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.d = new Rect();
        this.e = new Rect();
        this.f = 119;
        this.a = true;
        this.b = false;
        TypedArray a = context.obtainStyledAttributes(attrs, j.ForegroundLinearLayout, defStyle, 0);
        this.f = a.getInt(j.ForegroundLinearLayout_android_foregroundGravity, this.f);
        Drawable d = a.getDrawable(j.ForegroundLinearLayout_android_foreground);
        if (d != null) {
            setForeground(d);
        }
        this.a = a.getBoolean(j.ForegroundLinearLayout_foregroundInsidePadding, true);
        a.recycle();
    }

    public int getForegroundGravity() {
        return this.f;
    }

    public void setForegroundGravity(int foregroundGravity) {
        if (this.f != foregroundGravity) {
            if ((8388615 & foregroundGravity) == 0) {
                foregroundGravity |= 8388611;
            }
            if ((foregroundGravity & 112) == 0) {
                foregroundGravity |= 48;
            }
            this.f = foregroundGravity;
            if (this.f == 119 && this.c != null) {
                this.c.getPadding(new Rect());
            }
            requestLayout();
        }
    }

    protected boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || who == this.c;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.c != null) {
            this.c.jumpToCurrentState();
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.c != null && this.c.isStateful()) {
            this.c.setState(getDrawableState());
        }
    }

    public void setForeground(Drawable drawable) {
        if (this.c != drawable) {
            if (this.c != null) {
                this.c.setCallback(null);
                unscheduleDrawable(this.c);
            }
            this.c = drawable;
            if (drawable != null) {
                setWillNotDraw(false);
                drawable.setCallback(this);
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
                if (this.f == 119) {
                    drawable.getPadding(new Rect());
                }
            } else {
                setWillNotDraw(true);
            }
            requestLayout();
            invalidate();
        }
    }

    public Drawable getForeground() {
        return this.c;
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.b |= changed;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.b = true;
    }

    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        if (this.c != null) {
            Drawable foreground = this.c;
            if (this.b) {
                this.b = false;
                Rect selfBounds = this.d;
                Rect overlayBounds = this.e;
                int w = getRight() - getLeft();
                int h = getBottom() - getTop();
                if (this.a) {
                    selfBounds.set(0, 0, w, h);
                } else {
                    selfBounds.set(getPaddingLeft(), getPaddingTop(), w - getPaddingRight(), h - getPaddingBottom());
                }
                Gravity.apply(this.f, foreground.getIntrinsicWidth(), foreground.getIntrinsicHeight(), selfBounds, overlayBounds);
                foreground.setBounds(overlayBounds);
            }
            foreground.draw(canvas);
        }
    }

    public void drawableHotspotChanged(float x, float y) {
        super.drawableHotspotChanged(x, y);
        if (this.c != null) {
            this.c.setHotspot(x, y);
        }
    }
}
