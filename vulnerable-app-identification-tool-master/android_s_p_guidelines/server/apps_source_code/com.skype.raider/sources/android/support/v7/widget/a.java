package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.s;
import android.support.v4.view.t;
import android.support.v7.appcompat.a.j;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

abstract class a extends ViewGroup {
    protected final a a;
    protected final Context b;
    protected ActionMenuView c;
    protected ActionMenuPresenter d;
    protected int e;
    protected s f;
    private boolean g;
    private boolean h;

    protected class a implements t {
        int a;
        final /* synthetic */ a b;
        private boolean c = false;

        protected a(a this$0) {
            this.b = this$0;
        }

        public final a a(s animation, int visibility) {
            this.b.f = animation;
            this.a = visibility;
            return this;
        }

        public final void a(View view) {
            super.setVisibility(0);
            this.c = false;
        }

        public final void b(View view) {
            if (!this.c) {
                this.b.f = null;
                super.setVisibility(this.a);
            }
        }

        public final void c(View view) {
            this.c = true;
        }
    }

    a(Context context) {
        this(context, null);
    }

    a(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    a(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.a = new a(this);
        TypedValue tv = new TypedValue();
        if (!context.getTheme().resolveAttribute(android.support.v7.appcompat.a.a.actionBarPopupTheme, tv, true) || tv.resourceId == 0) {
            this.b = context;
        } else {
            this.b = new ContextThemeWrapper(context, tv.resourceId);
        }
    }

    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        TypedArray a = getContext().obtainStyledAttributes(null, j.ActionBar, android.support.v7.appcompat.a.a.actionBarStyle, 0);
        setContentHeight(a.getLayoutDimension(j.ActionBar_height, 0));
        a.recycle();
        if (this.d != null) {
            this.d.e();
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        if (action == 0) {
            this.g = false;
        }
        if (!this.g) {
            boolean handled = super.onTouchEvent(ev);
            if (action == 0 && !handled) {
                this.g = true;
            }
        }
        if (action == 1 || action == 3) {
            this.g = false;
        }
        return true;
    }

    public boolean onHoverEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        if (action == 9) {
            this.h = false;
        }
        if (!this.h) {
            boolean handled = super.onHoverEvent(ev);
            if (action == 9 && !handled) {
                this.h = true;
            }
        }
        if (action == 10 || action == 3) {
            this.h = false;
        }
        return true;
    }

    public void setContentHeight(int height) {
        this.e = height;
        requestLayout();
    }

    public s a(int visibility, long duration) {
        if (this.f != null) {
            this.f.b();
        }
        s anim;
        if (visibility == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            anim = ViewCompat.p(this).a(1.0f);
            anim.a(duration);
            anim.a(this.a.a(anim, visibility));
            return anim;
        }
        anim = ViewCompat.p(this).a(0.0f);
        anim.a(duration);
        anim.a(this.a.a(anim, visibility));
        return anim;
    }

    public void setVisibility(int visibility) {
        if (visibility != getVisibility()) {
            if (this.f != null) {
                this.f.b();
            }
            super.setVisibility(visibility);
        }
    }

    public boolean a() {
        if (this.d != null) {
            return this.d.g();
        }
        return false;
    }

    protected static int a(View child, int availableWidth, int childSpecHeight) {
        child.measure(MeasureSpec.makeMeasureSpec(availableWidth, Integer.MIN_VALUE), childSpecHeight);
        return Math.max(0, (availableWidth - child.getMeasuredWidth()) + 0);
    }

    protected static int a(int x, int val, boolean isRtl) {
        return isRtl ? x - val : x + val;
    }

    protected static int a(View child, int x, int y, int contentHeight, boolean reverse) {
        int childWidth = child.getMeasuredWidth();
        int childHeight = child.getMeasuredHeight();
        int childTop = y + ((contentHeight - childHeight) / 2);
        if (reverse) {
            child.layout(x - childWidth, childTop, x, childTop + childHeight);
        } else {
            child.layout(x, childTop, x + childWidth, childTop + childHeight);
        }
        return reverse ? -childWidth : childWidth;
    }
}
