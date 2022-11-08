package androidx.appcompat.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import androidx.appcompat.view.menu.af;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public abstract class av implements OnAttachStateChangeListener, OnTouchListener {
    private final float a;
    private final int b;
    final View c;
    private final int d;
    private Runnable e;
    private Runnable f;
    private boolean g;
    private int h;
    private final int[] i = new int[2];

    public abstract af a();

    public void onViewAttachedToWindow(View view) {
    }

    public av(View view) {
        this.c = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.a = (float) ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        this.b = ViewConfiguration.getTapTimeout();
        this.d = (this.b + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        boolean z2 = this.g;
        View view2;
        Object obj;
        if (z2) {
            view2 = this.c;
            af a = a();
            if (a != null && a.c()) {
                aq aqVar = (aq) a.d();
                if (aqVar != null && aqVar.isShown()) {
                    MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                    int[] iArr = this.i;
                    view2.getLocationOnScreen(iArr);
                    obtainNoHistory.offsetLocation((float) iArr[0], (float) iArr[1]);
                    int[] iArr2 = this.i;
                    aqVar.getLocationOnScreen(iArr2);
                    obtainNoHistory.offsetLocation((float) (-iArr2[0]), (float) (-iArr2[1]));
                    boolean a2 = aqVar.a(obtainNoHistory, this.h);
                    obtainNoHistory.recycle();
                    int actionMasked = motionEvent.getActionMasked();
                    obj = (actionMasked == 1 || actionMasked == 3) ? null : 1;
                    if (a2 && obj != null) {
                        obj = 1;
                        z = obj == null || !c();
                    }
                }
            }
            obj = null;
            if (obj == null) {
            }
        } else {
            view2 = this.c;
            if (view2.isEnabled()) {
                switch (motionEvent.getActionMasked()) {
                    case 0:
                        this.h = motionEvent.getPointerId(0);
                        if (this.e == null) {
                            this.e = new aw(this);
                        }
                        view2.postDelayed(this.e, (long) this.b);
                        if (this.f == null) {
                            this.f = new ax(this);
                        }
                        view2.postDelayed(this.f, (long) this.d);
                    case 1:
                    case 3:
                        e();
                    case 2:
                        int findPointerIndex = motionEvent.findPointerIndex(this.h);
                        if (findPointerIndex >= 0) {
                            float x = motionEvent.getX(findPointerIndex);
                            float y = motionEvent.getY(findPointerIndex);
                            float f = this.a;
                            float f2 = -f;
                            obj = (x < f2 || y < f2 || x >= ((float) (view2.getRight() - view2.getLeft())) + f || y >= ((float) (view2.getBottom() - view2.getTop())) + f) ? null : 1;
                            if (obj == null) {
                                e();
                                view2.getParent().requestDisallowInterceptTouchEvent(true);
                                obj = 1;
                                break;
                            }
                        }
                        break;
                }
            }
            obj = null;
            z = obj != null && b();
            if (z) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
                this.c.onTouchEvent(obtain);
                obtain.recycle();
            }
        }
        this.g = z;
        return z || z2;
    }

    public void onViewDetachedFromWindow(View view) {
        this.g = false;
        this.h = -1;
        if (this.e != null) {
            this.c.removeCallbacks(this.e);
        }
    }

    protected boolean b() {
        af a = a();
        if (!(a == null || a.c())) {
            a.a();
        }
        return true;
    }

    protected boolean c() {
        af a = a();
        if (a != null && a.c()) {
            a.b();
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

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void d() {
        e();
        View view = this.c;
        if (view.isEnabled() && !view.isLongClickable() && b()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
            view.onTouchEvent(obtain);
            obtain.recycle();
            this.g = true;
        }
    }
}
