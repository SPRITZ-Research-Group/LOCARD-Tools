package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.a.i;
import android.support.design.a.j;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.m;
import android.support.v4.view.w;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class ScrimInsetsFrameLayout extends FrameLayout {
    private Drawable a;
    private Rect b;
    private Rect c;

    public ScrimInsetsFrameLayout(Context context) {
        this(context, null);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.c = new Rect();
        TypedArray a = context.obtainStyledAttributes(attrs, j.ScrimInsetsFrameLayout, defStyleAttr, i.Widget_Design_ScrimInsetsFrameLayout);
        this.a = a.getDrawable(j.ScrimInsetsFrameLayout_insetForeground);
        a.recycle();
        setWillNotDraw(true);
        ViewCompat.a((View) this, new m(this) {
            final /* synthetic */ ScrimInsetsFrameLayout a;

            {
                this.a = r1;
            }

            public final w a(View v, w insets) {
                if (this.a.b == null) {
                    this.a.b = new Rect();
                }
                this.a.b.set(insets.a(), insets.b(), insets.c(), insets.d());
                ScrimInsetsFrameLayout scrimInsetsFrameLayout = this.a;
                boolean z = this.a.b.isEmpty() || this.a.a == null;
                scrimInsetsFrameLayout.setWillNotDraw(z);
                ViewCompat.d(this.a);
                return insets.f();
            }
        });
    }

    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.b != null && this.a != null) {
            int sc = canvas.save();
            canvas.translate((float) getScrollX(), (float) getScrollY());
            this.c.set(0, 0, width, this.b.top);
            this.a.setBounds(this.c);
            this.a.draw(canvas);
            this.c.set(0, height - this.b.bottom, width, height);
            this.a.setBounds(this.c);
            this.a.draw(canvas);
            this.c.set(0, this.b.top, this.b.left, height - this.b.bottom);
            this.a.setBounds(this.c);
            this.a.draw(canvas);
            this.c.set(width - this.b.right, this.b.top, width, height - this.b.bottom);
            this.a.setBounds(this.c);
            this.a.draw(canvas);
            canvas.restoreToCount(sc);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a != null) {
            this.a.setCallback(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            this.a.setCallback(null);
        }
    }
}
