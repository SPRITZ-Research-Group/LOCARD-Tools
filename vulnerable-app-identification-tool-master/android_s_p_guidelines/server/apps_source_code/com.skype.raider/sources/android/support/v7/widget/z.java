package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.g;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.p;
import android.support.v7.widget.RecyclerView.p.a;
import android.support.v7.widget.RecyclerView.p.b;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class z extends p {
    protected final LinearInterpolator a = new LinearInterpolator();
    protected final DecelerateInterpolator b = new DecelerateInterpolator();
    protected PointF c;
    protected int d = 0;
    protected int e = 0;
    private final float f;

    public z(Context context) {
        this.f = a(context.getResources().getDisplayMetrics());
    }

    protected void a(View targetView, a action) {
        int dy;
        int dx = a(targetView, b());
        int i = (this.c == null || this.c.y == 0.0f) ? 0 : this.c.y > 0.0f ? 1 : -1;
        g c = c();
        if (c == null || !c.f()) {
            dy = 0;
        } else {
            h hVar = (h) targetView.getLayoutParams();
            dy = a(g.j(targetView) - hVar.topMargin, hVar.bottomMargin + g.l(targetView), c.y(), c.w() - c.A(), i);
        }
        int time = a((int) Math.sqrt((double) ((dx * dx) + (dy * dy))));
        if (time > 0) {
            action.a(-dx, -dy, time, this.b);
        }
    }

    protected final void a(int dx, int dy, a action) {
        if (h() == 0) {
            d();
            return;
        }
        this.d = a(this.d, dx);
        this.e = a(this.e, dy);
        if (this.d == 0 && this.e == 0) {
            PointF c = c(g());
            if (c == null || (c.x == 0.0f && c.y == 0.0f)) {
                action.a(g());
                d();
                return;
            }
            float sqrt = (float) Math.sqrt((double) ((c.x * c.x) + (c.y * c.y)));
            c.x /= sqrt;
            c.y /= sqrt;
            this.c = c;
            this.d = (int) (c.x * 10000.0f);
            this.e = (int) (c.y * 10000.0f);
            action.a((int) (((float) this.d) * 1.2f), (int) (((float) this.e) * 1.2f), (int) (((float) b(10000)) * 1.2f), this.a);
        }
    }

    protected final void a() {
        this.e = 0;
        this.d = 0;
        this.c = null;
    }

    protected float a(DisplayMetrics displayMetrics) {
        return 25.0f / ((float) displayMetrics.densityDpi);
    }

    protected final int a(int dx) {
        return (int) Math.ceil(((double) b(dx)) / 0.3356d);
    }

    protected int b(int dx) {
        return (int) Math.ceil((double) (((float) Math.abs(dx)) * this.f));
    }

    protected int b() {
        if (this.c == null || this.c.x == 0.0f) {
            return 0;
        }
        return this.c.x > 0.0f ? 1 : -1;
    }

    private static int a(int tmpDt, int dt) {
        int before = tmpDt;
        tmpDt -= dt;
        if (before * tmpDt <= 0) {
            return 0;
        }
        return tmpDt;
    }

    public static int a(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
        switch (snapPreference) {
            case -1:
                return boxStart - viewStart;
            case 0:
                int dtStart = boxStart - viewStart;
                if (dtStart > 0) {
                    return dtStart;
                }
                int dtEnd = boxEnd - viewEnd;
                if (dtEnd < 0) {
                    return dtEnd;
                }
                return 0;
            case 1:
                return boxEnd - viewEnd;
            default:
                throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
    }

    public int a(View view, int snapPreference) {
        g layoutManager = c();
        if (layoutManager == null || !layoutManager.e()) {
            return 0;
        }
        h params = (h) view.getLayoutParams();
        return a(g.i(view) - params.leftMargin, g.k(view) + params.rightMargin, layoutManager.x(), layoutManager.v() - layoutManager.z(), snapPreference);
    }

    @Nullable
    public PointF c(int targetPosition) {
        g layoutManager = c();
        if (layoutManager instanceof b) {
            return ((b) layoutManager).d(targetPosition);
        }
        new StringBuilder("You should override computeScrollVectorForPosition when the LayoutManager does not implement ").append(b.class.getCanonicalName());
        return null;
    }
}
