package android.support.v4.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import com.adjust.sdk.Constants;
import java.util.Arrays;

public final class n {
    private static final Interpolator v = new Interpolator() {
        public final float getInterpolation(float t) {
            t -= 1.0f;
            return ((((t * t) * t) * t) * t) + 1.0f;
        }
    };
    private int a;
    private int b;
    private int c = -1;
    private float[] d;
    private float[] e;
    private float[] f;
    private float[] g;
    private int[] h;
    private int[] i;
    private int[] j;
    private int k;
    private VelocityTracker l;
    private float m;
    private float n;
    private int o;
    private int p;
    private OverScroller q;
    private final a r;
    private View s;
    private boolean t;
    private final ViewGroup u;
    private final Runnable w = new Runnable(this) {
        final /* synthetic */ n a;

        {
            this.a = this$0;
        }

        public final void run() {
            this.a.b(0);
        }
    };

    public static abstract class a {
        public abstract boolean a(View view);

        public void a(int state) {
        }

        public void b(View changedView, int left) {
        }

        public void b(View capturedChild) {
        }

        public void a(View releasedChild, float xvel) {
        }

        public void b() {
        }

        public void a(int edgeFlags, int pointerId) {
        }

        public int c(View child) {
            return 0;
        }

        public int a(View child, int left) {
            return 0;
        }

        public int d(View child) {
            return 0;
        }
    }

    public static n a(ViewGroup forParent, a cb) {
        return new n(forParent.getContext(), forParent, cb);
    }

    public static n a(ViewGroup forParent, float sensitivity, a cb) {
        n helper = a(forParent, cb);
        helper.b = (int) (((float) helper.b) * (1.0f / sensitivity));
        return helper;
    }

    private n(Context context, ViewGroup forParent, a cb) {
        if (forParent == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        } else if (cb == null) {
            throw new IllegalArgumentException("Callback may not be null");
        } else {
            this.u = forParent;
            this.r = cb;
            ViewConfiguration vc = ViewConfiguration.get(context);
            this.o = (int) ((20.0f * context.getResources().getDisplayMetrics().density) + 0.5f);
            this.b = vc.getScaledTouchSlop();
            this.m = (float) vc.getScaledMaximumFlingVelocity();
            this.n = (float) vc.getScaledMinimumFlingVelocity();
            this.q = new OverScroller(context, v);
        }
    }

    public final void a(float minVel) {
        this.n = minVel;
    }

    public final int a() {
        return this.a;
    }

    public final void a(int edgeFlags) {
        this.p = edgeFlags;
    }

    public final int b() {
        return this.o;
    }

    public final void a(View childView, int activePointerId) {
        if (childView.getParent() != this.u) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.u + ")");
        }
        this.s = childView;
        this.c = activePointerId;
        this.r.b(childView);
        b(1);
    }

    public final View c() {
        return this.s;
    }

    public final int d() {
        return this.b;
    }

    public final void e() {
        this.c = -1;
        if (this.d != null) {
            Arrays.fill(this.d, 0.0f);
            Arrays.fill(this.e, 0.0f);
            Arrays.fill(this.f, 0.0f);
            Arrays.fill(this.g, 0.0f);
            Arrays.fill(this.h, 0);
            Arrays.fill(this.i, 0);
            Arrays.fill(this.j, 0);
            this.k = 0;
        }
        if (this.l != null) {
            this.l.recycle();
            this.l = null;
        }
    }

    public final void f() {
        e();
        if (this.a == 2) {
            this.q.getCurrX();
            this.q.getCurrY();
            this.q.abortAnimation();
            int newX = this.q.getCurrX();
            this.q.getCurrY();
            this.r.b(this.s, newX);
        }
        b(0);
    }

    public final boolean a(View child, int finalLeft, int finalTop) {
        this.s = child;
        this.c = -1;
        boolean continueSliding = a(finalLeft, finalTop, 0, 0);
        if (!(continueSliding || this.a != 0 || this.s == null)) {
            this.s = null;
        }
        return continueSliding;
    }

    public final boolean a(int finalLeft, int finalTop) {
        if (this.t) {
            return a(finalLeft, finalTop, (int) this.l.getXVelocity(this.c), (int) this.l.getYVelocity(this.c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    private boolean a(int finalLeft, int finalTop, int xvel, int yvel) {
        int startLeft = this.s.getLeft();
        int startTop = this.s.getTop();
        int dx = finalLeft - startLeft;
        int dy = finalTop - startTop;
        if (dx == 0 && dy == 0) {
            this.q.abortAnimation();
            b(0);
            return false;
        }
        View view = this.s;
        int b = b(xvel, (int) this.n, (int) this.m);
        int b2 = b(yvel, (int) this.n, (int) this.m);
        int abs = Math.abs(dx);
        int abs2 = Math.abs(dy);
        int abs3 = Math.abs(b);
        int abs4 = Math.abs(b2);
        int i = abs3 + abs4;
        int i2 = abs + abs2;
        this.q.startScroll(startLeft, startTop, dx, dy, (int) (((b2 != 0 ? ((float) abs4) / ((float) i) : ((float) abs2) / ((float) i2)) * ((float) a(dy, b2, 0))) + ((b != 0 ? ((float) abs3) / ((float) i) : ((float) abs) / ((float) i2)) * ((float) a(dx, b, this.r.c(view))))));
        b(2);
        return true;
    }

    private int a(int delta, int velocity, int motionRange) {
        if (delta == 0) {
            return 0;
        }
        int duration;
        int width = this.u.getWidth();
        int halfWidth = width / 2;
        float distance = ((float) halfWidth) + (((float) halfWidth) * ((float) Math.sin((double) ((Math.min(1.0f, ((float) Math.abs(delta)) / ((float) width)) - 0.5f) * 0.47123894f))));
        velocity = Math.abs(velocity);
        if (velocity > 0) {
            duration = Math.round(1000.0f * Math.abs(distance / ((float) velocity))) * 4;
        } else {
            duration = (int) (((((float) Math.abs(delta)) / ((float) motionRange)) + 1.0f) * 256.0f);
        }
        return Math.min(duration, 600);
    }

    private static int b(int value, int absMin, int absMax) {
        int absValue = Math.abs(value);
        if (absValue < absMin) {
            return 0;
        }
        if (absValue <= absMax) {
            return value;
        }
        if (value <= 0) {
            return -absMax;
        }
        return absMax;
    }

    private static float a(float value, float absMin, float absMax) {
        float absValue = Math.abs(value);
        if (absValue < absMin) {
            return 0.0f;
        }
        if (absValue <= absMax) {
            return value;
        }
        if (value <= 0.0f) {
            return -absMax;
        }
        return absMax;
    }

    public final boolean g() {
        if (this.a == 2) {
            boolean keepGoing = this.q.computeScrollOffset();
            int x = this.q.getCurrX();
            int y = this.q.getCurrY();
            int dx = x - this.s.getLeft();
            int dy = y - this.s.getTop();
            if (dx != 0) {
                ViewCompat.c(this.s, dx);
            }
            if (dy != 0) {
                ViewCompat.b(this.s, dy);
            }
            if (!(dx == 0 && dy == 0)) {
                this.r.b(this.s, x);
            }
            if (keepGoing && x == this.q.getFinalX() && y == this.q.getFinalY()) {
                this.q.abortAnimation();
                keepGoing = false;
            }
            if (!keepGoing) {
                this.u.post(this.w);
            }
        }
        return this.a == 2;
    }

    private void b(float xvel) {
        this.t = true;
        this.r.a(this.s, xvel);
        this.t = false;
        if (this.a == 1) {
            b(0);
        }
    }

    private void c(int pointerId) {
        if (this.d != null && d(pointerId)) {
            this.d[pointerId] = 0.0f;
            this.e[pointerId] = 0.0f;
            this.f[pointerId] = 0.0f;
            this.g[pointerId] = 0.0f;
            this.h[pointerId] = 0;
            this.i[pointerId] = 0;
            this.j[pointerId] = 0;
            this.k &= (1 << pointerId) ^ -1;
        }
    }

    private void a(float x, float y, int pointerId) {
        int i = 0;
        if (this.d == null || this.d.length <= pointerId) {
            Object obj = new float[(pointerId + 1)];
            Object obj2 = new float[(pointerId + 1)];
            Object obj3 = new float[(pointerId + 1)];
            Object obj4 = new float[(pointerId + 1)];
            Object obj5 = new int[(pointerId + 1)];
            Object obj6 = new int[(pointerId + 1)];
            Object obj7 = new int[(pointerId + 1)];
            if (this.d != null) {
                System.arraycopy(this.d, 0, obj, 0, this.d.length);
                System.arraycopy(this.e, 0, obj2, 0, this.e.length);
                System.arraycopy(this.f, 0, obj3, 0, this.f.length);
                System.arraycopy(this.g, 0, obj4, 0, this.g.length);
                System.arraycopy(this.h, 0, obj5, 0, this.h.length);
                System.arraycopy(this.i, 0, obj6, 0, this.i.length);
                System.arraycopy(this.j, 0, obj7, 0, this.j.length);
            }
            this.d = obj;
            this.e = obj2;
            this.f = obj3;
            this.g = obj4;
            this.h = obj5;
            this.i = obj6;
            this.j = obj7;
        }
        float[] fArr = this.d;
        this.f[pointerId] = x;
        fArr[pointerId] = x;
        fArr = this.e;
        this.g[pointerId] = y;
        fArr[pointerId] = y;
        int[] iArr = this.h;
        int i2 = (int) x;
        int i3 = (int) y;
        if (i2 < this.u.getLeft() + this.o) {
            i = 1;
        }
        if (i3 < this.u.getTop() + this.o) {
            i |= 4;
        }
        if (i2 > this.u.getRight() - this.o) {
            i |= 2;
        }
        if (i3 > this.u.getBottom() - this.o) {
            i |= 8;
        }
        iArr[pointerId] = i;
        this.k |= 1 << pointerId;
    }

    private void c(MotionEvent ev) {
        int pointerCount = ev.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = ev.getPointerId(i);
            if (e(pointerId)) {
                float x = ev.getX(i);
                float y = ev.getY(i);
                this.f[pointerId] = x;
                this.g[pointerId] = y;
            }
        }
    }

    private boolean d(int pointerId) {
        return (this.k & (1 << pointerId)) != 0;
    }

    final void b(int state) {
        this.u.removeCallbacks(this.w);
        if (this.a != state) {
            this.a = state;
            this.r.a(state);
            if (this.a == 0) {
                this.s = null;
            }
        }
    }

    private boolean b(View toCapture, int pointerId) {
        if (toCapture == this.s && this.c == pointerId) {
            return true;
        }
        if (toCapture == null || !this.r.a(toCapture)) {
            return false;
        }
        this.c = pointerId;
        a(toCapture, pointerId);
        return true;
    }

    public final boolean a(MotionEvent ev) {
        int action = ev.getActionMasked();
        int actionIndex = ev.getActionIndex();
        if (action == 0) {
            e();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(ev);
        float x;
        float y;
        int pointerId;
        View toCapture;
        switch (action) {
            case 0:
                x = ev.getX();
                y = ev.getY();
                pointerId = ev.getPointerId(0);
                a(x, y, pointerId);
                toCapture = b((int) x, (int) y);
                if (toCapture == this.s && this.a == 2) {
                    b(toCapture, pointerId);
                }
                if ((this.h[pointerId] & this.p) != 0) {
                    this.r.b();
                    break;
                }
                break;
            case 1:
            case 3:
                e();
                break;
            case 2:
                if (!(this.d == null || this.e == null)) {
                    int pointerCount = ev.getPointerCount();
                    for (int i = 0; i < pointerCount; i++) {
                        pointerId = ev.getPointerId(i);
                        if (e(pointerId)) {
                            x = ev.getX(i);
                            y = ev.getY(i);
                            float dx = x - this.d[pointerId];
                            float dy = y - this.e[pointerId];
                            toCapture = b((int) x, (int) y);
                            boolean pastSlop = toCapture != null && a(toCapture, dx);
                            if (pastSlop) {
                                int oldLeft = toCapture.getLeft();
                                int newLeft = this.r.a(toCapture, oldLeft + ((int) dx));
                                toCapture.getTop();
                                this.r.d(toCapture);
                                int hDragRange = this.r.c(toCapture);
                                if (hDragRange != 0) {
                                    if (hDragRange > 0 && newLeft == oldLeft) {
                                    }
                                }
                                c(ev);
                                break;
                            }
                            b(dx, dy, pointerId);
                            if (this.a != 1) {
                                if (pastSlop && b(toCapture, pointerId)) {
                                }
                            }
                            c(ev);
                        }
                    }
                    c(ev);
                }
                break;
            case 5:
                pointerId = ev.getPointerId(actionIndex);
                x = ev.getX(actionIndex);
                y = ev.getY(actionIndex);
                a(x, y, pointerId);
                if (this.a != 0) {
                    if (this.a == 2) {
                        toCapture = b((int) x, (int) y);
                        if (toCapture == this.s) {
                            b(toCapture, pointerId);
                            break;
                        }
                    }
                } else if ((this.h[pointerId] & this.p) != 0) {
                    this.r.b();
                    break;
                }
                break;
            case 6:
                c(ev.getPointerId(actionIndex));
                break;
        }
        if (this.a == 1) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b(MotionEvent ev) {
        int action = ev.getActionMasked();
        int actionIndex = ev.getActionIndex();
        if (action == 0) {
            e();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(ev);
        float x;
        float y;
        int pointerId;
        View toCapture;
        int pointerCount;
        int i;
        switch (action) {
            case 0:
                x = ev.getX();
                y = ev.getY();
                pointerId = ev.getPointerId(0);
                toCapture = b((int) x, (int) y);
                a(x, y, pointerId);
                b(toCapture, pointerId);
                if ((this.h[pointerId] & this.p) != 0) {
                    this.r.b();
                    return;
                }
                return;
            case 1:
                if (this.a == 1) {
                    i();
                }
                e();
                return;
            case 2:
                if (this.a == 1) {
                    if (e(this.c)) {
                        int index = ev.findPointerIndex(this.c);
                        x = ev.getX(index);
                        int idx = (int) (x - this.f[this.c]);
                        int idy = (int) (ev.getY(index) - this.g[this.c]);
                        int left = this.s.getLeft() + idx;
                        this.s.getTop();
                        int left2 = this.s.getLeft();
                        int top = this.s.getTop();
                        if (idx != 0) {
                            left = this.r.a(this.s, left);
                            ViewCompat.c(this.s, left - left2);
                        }
                        if (idy != 0) {
                            ViewCompat.b(this.s, this.r.d(this.s) - top);
                        }
                        if (!(idx == 0 && idy == 0)) {
                            this.r.b(this.s, left);
                        }
                        c(ev);
                        return;
                    }
                    return;
                }
                pointerCount = ev.getPointerCount();
                for (i = 0; i < pointerCount; i++) {
                    pointerId = ev.getPointerId(i);
                    if (e(pointerId)) {
                        x = ev.getX(i);
                        y = ev.getY(i);
                        float dx = x - this.d[pointerId];
                        b(dx, y - this.e[pointerId], pointerId);
                        if (this.a != 1) {
                            toCapture = b((int) x, (int) y);
                            if (a(toCapture, dx) && b(toCapture, pointerId)) {
                            }
                        }
                        c(ev);
                        return;
                    }
                }
                c(ev);
                return;
            case 3:
                if (this.a == 1) {
                    b(0.0f);
                }
                e();
                return;
            case 5:
                pointerId = ev.getPointerId(actionIndex);
                x = ev.getX(actionIndex);
                y = ev.getY(actionIndex);
                a(x, y, pointerId);
                if (this.a == 0) {
                    b(b((int) x, (int) y), pointerId);
                    if ((this.h[pointerId] & this.p) != 0) {
                        this.r.b();
                        return;
                    }
                    return;
                }
                if (b(this.s, (int) x, (int) y)) {
                    b(this.s, pointerId);
                    return;
                }
                return;
            case 6:
                pointerId = ev.getPointerId(actionIndex);
                if (this.a == 1 && pointerId == this.c) {
                    int newActivePointer = -1;
                    pointerCount = ev.getPointerCount();
                    i = 0;
                    while (i < pointerCount) {
                        int id = ev.getPointerId(i);
                        if (id != this.c && b((int) x, (int) ev.getY(i)) == this.s) {
                            if (b(this.s, id)) {
                                newActivePointer = this.c;
                                if (newActivePointer == -1) {
                                    i();
                                }
                            }
                        }
                        i++;
                    }
                    if (newActivePointer == -1) {
                        i();
                    }
                }
                c(pointerId);
                return;
            default:
                return;
        }
    }

    private void b(float dx, float dy, int pointerId) {
        int dragsStarted = 0;
        if (a(dx, dy, pointerId, 1)) {
            dragsStarted = 1;
        }
        if (a(dy, dx, pointerId, 4)) {
            dragsStarted |= 4;
        }
        if (a(dx, dy, pointerId, 2)) {
            dragsStarted |= 2;
        }
        if (a(dy, dx, pointerId, 8)) {
            dragsStarted |= 8;
        }
        if (dragsStarted != 0) {
            int[] iArr = this.i;
            iArr[pointerId] = iArr[pointerId] | dragsStarted;
            this.r.a(dragsStarted, pointerId);
        }
    }

    private boolean a(float delta, float odelta, int pointerId, int edge) {
        float absDelta = Math.abs(delta);
        float absODelta = Math.abs(odelta);
        if ((this.h[pointerId] & edge) != edge || (this.p & edge) == 0 || (this.j[pointerId] & edge) == edge || (this.i[pointerId] & edge) == edge) {
            return false;
        }
        if ((absDelta > ((float) this.b) || absODelta > ((float) this.b)) && (this.i[pointerId] & edge) == 0 && absDelta > ((float) this.b)) {
            return true;
        }
        return false;
    }

    private boolean a(View child, float dx) {
        if (child == null) {
            return false;
        }
        if (!(this.r.c(child) > 0) || Math.abs(dx) <= ((float) this.b)) {
            return false;
        }
        return true;
    }

    public final boolean h() {
        int count = this.d.length;
        for (int i = 0; i < count; i++) {
            boolean z;
            if (d(i)) {
                float f = this.f[i] - this.d[i];
                float f2 = this.g[i] - this.e[i];
                z = (f * f) + (f2 * f2) > ((float) (this.b * this.b));
            } else {
                z = false;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    private void i() {
        this.l.computeCurrentVelocity(Constants.ONE_SECOND, this.m);
        float xvel = a(this.l.getXVelocity(this.c), this.n, this.m);
        a(this.l.getYVelocity(this.c), this.n, this.m);
        b(xvel);
    }

    public static boolean b(View view, int x, int y) {
        if (view != null && x >= view.getLeft() && x < view.getRight() && y >= view.getTop() && y < view.getBottom()) {
            return true;
        }
        return false;
    }

    public final View b(int x, int y) {
        for (int i = this.u.getChildCount() - 1; i >= 0; i--) {
            View child = this.u.getChildAt(i);
            if (x >= child.getLeft() && x < child.getRight() && y >= child.getTop() && y < child.getBottom()) {
                return child;
            }
        }
        return null;
    }

    private boolean e(int pointerId) {
        if (d(pointerId)) {
            return true;
        }
        new StringBuilder("Ignoring pointerId=").append(pointerId).append(" because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }
}
