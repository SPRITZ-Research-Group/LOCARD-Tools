package androidx.appcompat.widget;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnHoverListener;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import com.google.android.gms.common.api.Api.BaseClientBuilder;
import defpackage.hs;
import defpackage.hv;

final class bz implements OnAttachStateChangeListener, OnHoverListener, OnLongClickListener {
    private static bz j;
    private static bz k;
    private final View a;
    private final CharSequence b;
    private final int c;
    private final Runnable d = new Runnable(this) {
        final /* synthetic */ bz a;

        {
            this.a = r1;
        }

        public final void run() {
            this.a.a(false);
        }
    };
    private final Runnable e = new Runnable(this) {
        final /* synthetic */ bz a;

        {
            this.a = r1;
        }

        public final void run() {
            this.a.a();
        }
    };
    private int f;
    private int g;
    private ca h;
    private boolean i;

    public final void onViewAttachedToWindow(View view) {
    }

    public static void a(View view, CharSequence charSequence) {
        if (j != null && j.a == view) {
            a(null);
        }
        if (TextUtils.isEmpty(charSequence)) {
            if (k != null && k.a == view) {
                k.a();
            }
            view.setOnLongClickListener(null);
            view.setLongClickable(false);
            view.setOnHoverListener(null);
            return;
        }
        bz bzVar = new bz(view, charSequence);
    }

    private bz(View view, CharSequence charSequence) {
        this.a = view;
        this.b = charSequence;
        this.c = hv.b(ViewConfiguration.get(this.a.getContext()));
        d();
        this.a.setOnLongClickListener(this);
        this.a.setOnHoverListener(this);
    }

    public final boolean onLongClick(View view) {
        this.f = view.getWidth() / 2;
        this.g = view.getHeight() / 2;
        a(true);
        return true;
    }

    public final boolean onHover(View view, MotionEvent motionEvent) {
        if (this.h != null && this.i) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.a.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                d();
                a();
            }
        } else if (this.a.isEnabled() && this.h == null) {
            Object obj;
            action = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (Math.abs(action - this.f) > this.c || Math.abs(y - this.g) > this.c) {
                this.f = action;
                this.g = y;
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                a(this);
            }
        }
        return false;
    }

    public final void onViewDetachedFromWindow(View view) {
        a();
    }

    final void a(boolean z) {
        if (hs.F(this.a)) {
            long j;
            a(null);
            if (k != null) {
                k.a();
            }
            k = this;
            this.i = z;
            this.h = new ca(this.a.getContext());
            this.h.a(this.a, this.f, this.g, this.i, this.b);
            this.a.addOnAttachStateChangeListener(this);
            if (this.i) {
                j = 2500;
            } else if ((hs.s(this.a) & 1) == 1) {
                j = 3000 - ((long) ViewConfiguration.getLongPressTimeout());
            } else {
                j = 15000 - ((long) ViewConfiguration.getLongPressTimeout());
            }
            this.a.removeCallbacks(this.e);
            this.a.postDelayed(this.e, j);
        }
    }

    final void a() {
        if (k == this) {
            k = null;
            if (this.h != null) {
                this.h.a();
                this.h = null;
                d();
                this.a.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (j == this) {
            a(null);
        }
        this.a.removeCallbacks(this.e);
    }

    private static void a(bz bzVar) {
        if (j != null) {
            j.c();
        }
        j = bzVar;
        if (bzVar != null) {
            j.b();
        }
    }

    private void b() {
        this.a.postDelayed(this.d, (long) ViewConfiguration.getLongPressTimeout());
    }

    private void c() {
        this.a.removeCallbacks(this.d);
    }

    private void d() {
        this.f = BaseClientBuilder.API_PRIORITY_OTHER;
        this.g = BaseClientBuilder.API_PRIORITY_OTHER;
    }
}
