package android.support.v4.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.a;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;

public class PagerTabStrip extends PagerTitleStrip {
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private final Paint m;
    private final Rect n;
    private int o;
    private boolean p;
    private boolean q;
    private int r;
    private boolean s;
    private float t;
    private float u;
    private int v;

    public PagerTabStrip(Context context) {
        this(context, null);
    }

    public PagerTabStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.m = new Paint();
        this.n = new Rect();
        this.o = 255;
        this.p = false;
        this.q = false;
        this.g = this.f;
        this.m.setColor(this.g);
        float density = context.getResources().getDisplayMetrics().density;
        this.h = (int) ((3.0f * density) + 0.5f);
        this.i = (int) ((6.0f * density) + 0.5f);
        this.j = (int) (64.0f * density);
        this.l = (int) ((16.0f * density) + 0.5f);
        this.r = (int) ((1.0f * density) + 0.5f);
        this.k = (int) ((32.0f * density) + 0.5f);
        this.v = ViewConfiguration.get(context).getScaledTouchSlop();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(b());
        setWillNotDraw(false);
        this.b.setFocusable(true);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PagerTabStrip a;

            {
                this.a = this$0;
            }

            public final void onClick(View v) {
                this.a.a.setCurrentItem(this.a.a.c - 1);
            }
        });
        this.d.setFocusable(true);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PagerTabStrip a;

            {
                this.a = this$0;
            }

            public final void onClick(View v) {
                this.a.a.setCurrentItem(this.a.a.c + 1);
            }
        });
        if (getBackground() == null) {
            this.p = true;
        }
    }

    public void setTabIndicatorColor(@ColorInt int color) {
        this.g = color;
        this.m.setColor(this.g);
        invalidate();
    }

    public void setTabIndicatorColorResource(@ColorRes int resId) {
        setTabIndicatorColor(a.c(getContext(), resId));
    }

    public void setPadding(int left, int top, int right, int bottom) {
        if (bottom < this.i) {
            bottom = this.i;
        }
        super.setPadding(left, top, right, bottom);
    }

    public void setTextSpacing(int textSpacing) {
        if (textSpacing < this.j) {
            textSpacing = this.j;
        }
        super.setTextSpacing(textSpacing);
    }

    public void setBackgroundDrawable(Drawable d) {
        super.setBackgroundDrawable(d);
        if (!this.q) {
            this.p = d == null;
        }
    }

    public void setBackgroundColor(@ColorInt int color) {
        super.setBackgroundColor(color);
        if (!this.q) {
            this.p = (-16777216 & color) == 0;
        }
    }

    public void setBackgroundResource(@DrawableRes int resId) {
        super.setBackgroundResource(resId);
        if (!this.q) {
            this.p = resId == 0;
        }
    }

    public void setDrawFullUnderline(boolean drawFull) {
        this.p = drawFull;
        this.q = true;
        invalidate();
    }

    final int a() {
        return Math.max(super.a(), this.k);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action != 0 && this.s) {
            return false;
        }
        float x = ev.getX();
        float y = ev.getY();
        switch (action) {
            case 0:
                this.t = x;
                this.u = y;
                this.s = false;
                break;
            case 1:
                if (x >= ((float) (this.c.getLeft() - this.l))) {
                    if (x > ((float) (this.c.getRight() + this.l))) {
                        this.a.setCurrentItem(this.a.c + 1);
                        break;
                    }
                }
                this.a.setCurrentItem(this.a.c - 1);
                break;
                break;
            case 2:
                if (Math.abs(x - this.t) > ((float) this.v) || Math.abs(y - this.u) > ((float) this.v)) {
                    this.s = true;
                    break;
                }
        }
        return true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int left = this.c.getLeft() - this.l;
        int right = this.c.getRight() + this.l;
        int top = height - this.h;
        this.m.setColor((this.o << 24) | (this.g & 16777215));
        canvas.drawRect((float) left, (float) top, (float) right, (float) height, this.m);
        if (this.p) {
            this.m.setColor(-16777216 | (this.g & 16777215));
            canvas.drawRect((float) getPaddingLeft(), (float) (height - this.r), (float) (getWidth() - getPaddingRight()), (float) height, this.m);
        }
    }

    final void a(int position, float positionOffset, boolean force) {
        Rect r = this.n;
        int bottom = getHeight();
        int top = bottom - this.h;
        r.set(this.c.getLeft() - this.l, top, this.c.getRight() + this.l, bottom);
        super.a(position, positionOffset, force);
        this.o = (int) ((Math.abs(positionOffset - 0.5f) * 2.0f) * 255.0f);
        r.union(this.c.getLeft() - this.l, top, this.c.getRight() + this.l, bottom);
        invalidate(r);
    }
}
