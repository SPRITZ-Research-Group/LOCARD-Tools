package androidx.recyclerview.widget;

import android.os.Build.VERSION;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import com.google.android.gms.common.api.Api.BaseClientBuilder;
import defpackage.hs;

final class ca implements Runnable {
    OverScroller a;
    Interpolator b = RecyclerView.sQuinticInterpolator;
    final /* synthetic */ RecyclerView c;
    private int d;
    private int e;
    private boolean f = false;
    private boolean g = false;

    ca(RecyclerView recyclerView) {
        this.c = recyclerView;
        this.a = new OverScroller(recyclerView.getContext(), RecyclerView.sQuinticInterpolator);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        if (this.c.mLayout == null) {
            b();
            return;
        }
        boolean z;
        r0.g = false;
        r0.f = true;
        r0.c.consumePendingUpdateOperations();
        OverScroller overScroller = r0.a;
        bv bvVar = r0.c.mLayout.t;
        if (overScroller.computeScrollOffset()) {
            int i;
            int i2;
            int i3;
            int i4;
            int b;
            int[] iArr = r0.c.mScrollConsumed;
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int i5 = currX - r0.d;
            int i6 = currY - r0.e;
            r0.d = currX;
            r0.e = currY;
            if (r0.c.dispatchNestedPreScroll(i5, i6, iArr, null, 1)) {
                i5 -= iArr[0];
                i6 -= iArr[1];
            }
            if (r0.c.mAdapter != null) {
                r0.c.scrollStep(i5, i6, r0.c.mScrollStepConsumed);
                i = r0.c.mScrollStepConsumed[0];
                i2 = r0.c.mScrollStepConsumed[1];
                i3 = i5 - i;
                i4 = i6 - i2;
                if (!(bvVar == null || bvVar.e() || !bvVar.f())) {
                    b = r0.c.mState.b();
                    if (b == 0) {
                        bvVar.d();
                    } else {
                        if (bvVar.g() >= b) {
                            bvVar.c(b - 1);
                        }
                        bvVar.a(i5 - i3, i6 - i4);
                    }
                }
            } else {
                i = 0;
                i2 = 0;
                i3 = 0;
                i4 = 0;
            }
            if (!r0.c.mItemDecorations.isEmpty()) {
                r0.c.invalidate();
            }
            if (r0.c.getOverScrollMode() != 2) {
                r0.c.considerReleasingGlowsOnScroll(i5, i6);
            }
            if (!(r0.c.dispatchNestedScroll(i, i2, i3, i4, null, 1) || (i3 == 0 && i4 == 0))) {
                int i7;
                b = (int) overScroller.getCurrVelocity();
                if (i3 != currX) {
                    if (i3 < 0) {
                        i7 = -b;
                    } else if (i3 > 0) {
                        i7 = b;
                    }
                    if (i4 != currY) {
                        if (i4 < 0) {
                            b = -b;
                        }
                        if (r0.c.getOverScrollMode() != 2) {
                            r0.c.absorbGlows(i7, b);
                        }
                        if ((i7 != 0 || i3 == currX || overScroller.getFinalX() == 0) && (b != 0 || i4 == currY || overScroller.getFinalY() == 0)) {
                            overScroller.abortAnimation();
                        }
                    }
                    b = 0;
                    if (r0.c.getOverScrollMode() != 2) {
                        r0.c.absorbGlows(i7, b);
                    }
                    overScroller.abortAnimation();
                }
                i7 = 0;
                if (i4 != currY) {
                    if (i4 < 0) {
                        b = -b;
                    }
                    if (r0.c.getOverScrollMode() != 2) {
                        r0.c.absorbGlows(i7, b);
                    }
                    overScroller.abortAnimation();
                }
                b = 0;
                if (r0.c.getOverScrollMode() != 2) {
                    r0.c.absorbGlows(i7, b);
                }
                overScroller.abortAnimation();
            }
            if (!(i == 0 && i2 == 0)) {
                r0.c.dispatchOnScrolled(i, i2);
            }
            if (!r0.c.awakenScrollBars()) {
                r0.c.invalidate();
            }
            Object obj = (i6 != 0 && r0.c.mLayout.i() && i2 == i6) ? 1 : null;
            Object obj2 = (i5 != 0 && r0.c.mLayout.h() && i == i5) ? 1 : null;
            obj = (!(i5 == 0 && i6 == 0) && obj2 == null && obj == null) ? null : 1;
            if (overScroller.isFinished() || (obj == null && !r0.c.hasNestedScrollingParent(1))) {
                r0.c.setScrollState(0);
                if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                    r0.c.mPrefetchRegistry.a();
                }
                r0.c.stopNestedScroll(1);
            } else {
                a();
                if (r0.c.mGapWorker != null) {
                    r0.c.mGapWorker.a(r0.c, i5, i6);
                }
            }
        }
        if (bvVar != null) {
            if (bvVar.e()) {
                z = false;
                bvVar.a(0, 0);
            } else {
                z = false;
            }
            if (!r0.g) {
                bvVar.d();
            }
        } else {
            z = false;
        }
        r0.f = z;
        if (r0.g) {
            a();
        }
    }

    final void a() {
        if (this.f) {
            this.g = true;
            return;
        }
        this.c.removeCallbacks(this);
        hs.a(this.c, (Runnable) this);
    }

    public final void a(int i, int i2) {
        this.c.setScrollState(2);
        this.e = 0;
        this.d = 0;
        this.a.fling(0, 0, i, i2, Integer.MIN_VALUE, BaseClientBuilder.API_PRIORITY_OTHER, Integer.MIN_VALUE, BaseClientBuilder.API_PRIORITY_OTHER);
        a();
    }

    public final void b(int i, int i2) {
        a(i, i2, c(i, i2));
    }

    private static float a(float f) {
        return (float) Math.sin((double) ((f - 0.5f) * 0.47123894f));
    }

    private int c(int i, int i2) {
        int abs = Math.abs(i);
        int abs2 = Math.abs(i2);
        Object obj = abs > abs2 ? 1 : null;
        int sqrt = (int) Math.sqrt(0.0d);
        i = (int) Math.sqrt((double) ((i * i) + (i2 * i2)));
        i2 = obj != null ? this.c.getWidth() : this.c.getHeight();
        int i3 = i2 / 2;
        float f = (float) i2;
        float f2 = (float) i3;
        f2 += a(Math.min(1.0f, (((float) i) * 1.0f) / f)) * f2;
        if (sqrt > 0) {
            i = Math.round(Math.abs(f2 / ((float) sqrt)) * 1000.0f) * 4;
        } else {
            if (obj == null) {
                abs = abs2;
            }
            i = (int) (((((float) abs) / f) + 1.0f) * 300.0f);
        }
        return Math.min(i, 2000);
    }

    public final void a(int i, int i2, int i3) {
        a(i, i2, i3, RecyclerView.sQuinticInterpolator);
    }

    public final void a(int i, int i2, Interpolator interpolator) {
        int c = c(i, i2);
        if (interpolator == null) {
            interpolator = RecyclerView.sQuinticInterpolator;
        }
        a(i, i2, c, interpolator);
    }

    public final void a(int i, int i2, int i3, Interpolator interpolator) {
        if (this.b != interpolator) {
            this.b = interpolator;
            this.a = new OverScroller(this.c.getContext(), interpolator);
        }
        this.c.setScrollState(2);
        this.e = 0;
        this.d = 0;
        this.a.startScroll(0, 0, i, i2, i3);
        if (VERSION.SDK_INT < 23) {
            this.a.computeScrollOffset();
        }
        a();
    }

    public final void b() {
        this.c.removeCallbacks(this);
        this.a.abortAnimation();
    }
}
