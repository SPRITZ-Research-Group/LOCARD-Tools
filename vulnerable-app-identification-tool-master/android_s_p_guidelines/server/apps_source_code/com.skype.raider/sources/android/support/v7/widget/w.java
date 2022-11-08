package android.support.v7.widget;

import android.os.SystemClock;
import android.support.annotation.RestrictTo;
import android.support.v7.view.menu.r;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewParent;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public abstract class w implements OnAttachStateChangeListener, OnTouchListener {
    private final float a;
    private final int b;
    final View c;
    private final int d;
    private Runnable e;
    private Runnable f;
    private boolean g;
    private int h;
    private final int[] i = new int[2];

    private class a implements Runnable {
        final /* synthetic */ w a;

        a(w wVar) {
            this.a = wVar;
        }

        public final void run() {
            ViewParent parent = this.a.c.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    private class b implements Runnable {
        final /* synthetic */ w a;

        b(w wVar) {
            this.a = wVar;
        }

        public final void run() {
            this.a.d();
        }
    }

    public abstract r a();

    public w(View src) {
        this.c = src;
        src.setLongClickable(true);
        src.addOnAttachStateChangeListener(this);
        this.a = (float) ViewConfiguration.get(src.getContext()).getScaledTouchSlop();
        this.b = ViewConfiguration.getTapTimeout();
        this.d = (this.b + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(View v, MotionEvent event) {
        boolean forwarding;
        boolean wasForwarding = this.g;
        View view;
        boolean z;
        int actionMasked;
        if (wasForwarding) {
            view = this.c;
            r a = a();
            if (a == null || !a.f()) {
                z = false;
            } else {
                u uVar = (u) a.g();
                if (uVar == null || !uVar.isShown()) {
                    z = false;
                } else {
                    MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(event);
                    int[] iArr = this.i;
                    view.getLocationOnScreen(iArr);
                    obtainNoHistory.offsetLocation((float) iArr[0], (float) iArr[1]);
                    int[] iArr2 = this.i;
                    uVar.getLocationOnScreen(iArr2);
                    obtainNoHistory.offsetLocation((float) (-iArr2[0]), (float) (-iArr2[1]));
                    boolean a2 = uVar.a(obtainNoHistory, this.h);
                    obtainNoHistory.recycle();
                    actionMasked = event.getActionMasked();
                    if (actionMasked == 1 || actionMasked == 3) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (a2 && r0) {
                        z = true;
                    } else {
                        z = false;
                    }
                }
            }
            forwarding = z || !c();
        } else {
            view = this.c;
            if (view.isEnabled()) {
                switch (event.getActionMasked()) {
                    case 0:
                        this.h = event.getPointerId(0);
                        if (this.e == null) {
                            this.e = new a(this);
                        }
                        view.postDelayed(this.e, (long) this.b);
                        if (this.f == null) {
                            this.f = new b(this);
                        }
                        view.postDelayed(this.f, (long) this.d);
                    case 1:
                    case 3:
                        e();
                    case 2:
                        actionMasked = event.findPointerIndex(this.h);
                        if (actionMasked >= 0) {
                            float x = event.getX(actionMasked);
                            float y = event.getY(actionMasked);
                            float f = this.a;
                            if (x < (-f) || y < (-f) || x >= ((float) (view.getRight() - view.getLeft())) + f || y >= ((float) (view.getBottom() - view.getTop())) + f) {
                                z = false;
                            } else {
                                z = true;
                            }
                            if (!z) {
                                e();
                                view.getParent().requestDisallowInterceptTouchEvent(true);
                                z = true;
                                break;
                            }
                        }
                        break;
                }
            }
            z = false;
            forwarding = z && b();
            if (forwarding) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent e = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                this.c.onTouchEvent(e);
                e.recycle();
            }
        }
        this.g = forwarding;
        if (forwarding || wasForwarding) {
            return true;
        }
        return false;
    }

    public void onViewAttachedToWindow(View v) {
    }

    public void onViewDetachedFromWindow(View v) {
        this.g = false;
        this.h = -1;
        if (this.e != null) {
            this.c.removeCallbacks(this.e);
        }
    }

    protected boolean b() {
        r popup = a();
        if (!(popup == null || popup.f())) {
            popup.c();
        }
        return true;
    }

    protected boolean c() {
        r popup = a();
        if (popup != null && popup.f()) {
            popup.e();
        }
        return true;
    }

    private void e() {
        if (this.f != null) {
            this.c.removeCallbacks(this.f);
        }
        if (this.e != null) {
            this.c.removeCallbacks(this.e);
        }
    }

    final void d() {
        e();
        View src = this.c;
        if (src.isEnabled() && !src.isLongClickable() && b()) {
            src.getParent().requestDisallowInterceptTouchEvent(true);
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent e = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            src.onTouchEvent(e);
            e.recycle();
            this.g = true;
        }
    }
}
