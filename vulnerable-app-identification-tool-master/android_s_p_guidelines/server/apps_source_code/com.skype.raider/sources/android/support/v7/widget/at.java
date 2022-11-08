package android.support.v7.widget;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnHoverListener;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

@RestrictTo({a.LIBRARY_GROUP})
final class at implements OnAttachStateChangeListener, OnHoverListener, OnLongClickListener {
    private static at i;
    private final View a;
    private final CharSequence b;
    private final Runnable c = new Runnable(this) {
        final /* synthetic */ at a;

        {
            this.a = this$0;
        }

        public final void run() {
            this.a.a(false);
        }
    };
    private final Runnable d = new Runnable(this) {
        final /* synthetic */ at a;

        {
            this.a = this$0;
        }

        public final void run() {
            this.a.a();
        }
    };
    private int e;
    private int f;
    private au g;
    private boolean h;

    public static void a(View view, CharSequence tooltipText) {
        if (TextUtils.isEmpty(tooltipText)) {
            if (i != null && i.a == view) {
                i.a();
            }
            view.setOnLongClickListener(null);
            view.setLongClickable(false);
            view.setOnHoverListener(null);
            return;
        }
        at atVar = new at(view, tooltipText);
    }

    private at(View anchor, CharSequence tooltipText) {
        this.a = anchor;
        this.b = tooltipText;
        this.a.setOnLongClickListener(this);
        this.a.setOnHoverListener(this);
    }

    public final boolean onLongClick(View v) {
        this.e = v.getWidth() / 2;
        this.f = v.getHeight() / 2;
        a(true);
        return true;
    }

    public final boolean onHover(View v, MotionEvent event) {
        if (this.g == null || !this.h) {
            AccessibilityManager manager = (AccessibilityManager) this.a.getContext().getSystemService("accessibility");
            if (!(manager.isEnabled() && manager.isTouchExplorationEnabled())) {
                switch (event.getAction()) {
                    case 7:
                        if (this.a.isEnabled() && this.g == null) {
                            this.e = (int) event.getX();
                            this.f = (int) event.getY();
                            this.a.removeCallbacks(this.c);
                            this.a.postDelayed(this.c, (long) ViewConfiguration.getLongPressTimeout());
                            break;
                        }
                    case 10:
                        a();
                        break;
                }
            }
        }
        return false;
    }

    public final void onViewAttachedToWindow(View v) {
    }

    public final void onViewDetachedFromWindow(View v) {
        a();
    }

    private void a(boolean fromTouch) {
        if (ViewCompat.D(this.a)) {
            long timeout;
            if (i != null) {
                i.a();
            }
            i = this;
            this.h = fromTouch;
            this.g = new au(this.a.getContext());
            this.g.a(this.a, this.e, this.f, this.h, this.b);
            this.a.addOnAttachStateChangeListener(this);
            if (this.h) {
                timeout = 2500;
            } else if ((ViewCompat.s(this.a) & 1) == 1) {
                timeout = 3000 - ((long) ViewConfiguration.getLongPressTimeout());
            } else {
                timeout = 15000 - ((long) ViewConfiguration.getLongPressTimeout());
            }
            this.a.removeCallbacks(this.d);
            this.a.postDelayed(this.d, timeout);
        }
    }

    private void a() {
        if (i == this) {
            i = null;
            if (this.g != null) {
                this.g.a();
                this.g = null;
                this.a.removeOnAttachStateChangeListener(this);
            }
        }
        this.a.removeCallbacks(this.c);
        this.a.removeCallbacks(this.d);
    }
}
