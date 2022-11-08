package android.support.v7.widget;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.a;
import android.support.v7.widget.RecyclerView.g;
import android.support.v7.widget.RecyclerView.p;
import android.support.v7.widget.RecyclerView.p.b;
import android.util.DisplayMetrics;
import android.view.View;

public final class ae extends ak {
    @Nullable
    private ad b;
    @Nullable
    private ad c;

    @Nullable
    public final int[] a(@NonNull g layoutManager, @NonNull View targetView) {
        int[] out = new int[2];
        if (layoutManager.e()) {
            out[0] = a(layoutManager, targetView, d(layoutManager));
        } else {
            out[0] = 0;
        }
        if (layoutManager.f()) {
            out[1] = a(layoutManager, targetView, c(layoutManager));
        } else {
            out[1] = 0;
        }
        return out;
    }

    @Nullable
    public final View a(g layoutManager) {
        if (layoutManager.f()) {
            return a(layoutManager, c(layoutManager));
        }
        if (layoutManager.e()) {
            return a(layoutManager, d(layoutManager));
        }
        return null;
    }

    public final int a(g layoutManager, int velocityX, int velocityY) {
        a aVar;
        int itemCount;
        if (layoutManager.q != null) {
            aVar = layoutManager.q.l;
        } else {
            aVar = null;
        }
        if (aVar != null) {
            itemCount = aVar.a();
        } else {
            itemCount = 0;
        }
        if (itemCount == 0) {
            return -1;
        }
        View mStartMostChildView = null;
        if (layoutManager.f()) {
            mStartMostChildView = b(layoutManager, c(layoutManager));
        } else if (layoutManager.e()) {
            mStartMostChildView = b(layoutManager, d(layoutManager));
        }
        if (mStartMostChildView == null) {
            return -1;
        }
        int centerPosition = g.e(mStartMostChildView);
        if (centerPosition == -1) {
            return -1;
        }
        boolean forwardDirection = layoutManager.e() ? velocityX > 0 : velocityY > 0;
        boolean reverseLayout = false;
        if (layoutManager instanceof b) {
            PointF vectorForEnd = ((b) layoutManager).d(itemCount - 1);
            if (vectorForEnd != null) {
                reverseLayout = vectorForEnd.x < 0.0f || vectorForEnd.y < 0.0f;
            }
        }
        return reverseLayout ? forwardDirection ? centerPosition - 1 : centerPosition : forwardDirection ? centerPosition + 1 : centerPosition;
    }

    protected final z b(g layoutManager) {
        if (layoutManager instanceof b) {
            return new z(this, this.a.getContext()) {
                final /* synthetic */ ae f;

                protected final void a(View targetView, p.a action) {
                    int[] snapDistances = this.f.a(this.f.a.e(), targetView);
                    int dx = snapDistances[0];
                    int dy = snapDistances[1];
                    int time = a(Math.max(Math.abs(dx), Math.abs(dy)));
                    if (time > 0) {
                        action.a(dx, dy, time, this.b);
                    }
                }

                protected final float a(DisplayMetrics displayMetrics) {
                    return 100.0f / ((float) displayMetrics.densityDpi);
                }

                protected final int b(int dx) {
                    return Math.min(100, super.b(dx));
                }
            };
        }
        return null;
    }

    private static int a(@NonNull g layoutManager, @NonNull View targetView, ad helper) {
        int containerCenter;
        int childCenter = helper.a(targetView) + (helper.e(targetView) / 2);
        if (layoutManager.q()) {
            containerCenter = helper.c() + (helper.f() / 2);
        } else {
            containerCenter = helper.e() / 2;
        }
        return childCenter - containerCenter;
    }

    @Nullable
    private static View a(g layoutManager, ad helper) {
        int childCount = layoutManager.s();
        if (childCount == 0) {
            return null;
        }
        int center;
        View closestChild = null;
        if (layoutManager.q()) {
            center = helper.c() + (helper.f() / 2);
        } else {
            center = helper.e() / 2;
        }
        int absClosest = Integer.MAX_VALUE;
        for (int i = 0; i < childCount; i++) {
            View child = layoutManager.g(i);
            int absDistance = Math.abs((helper.a(child) + (helper.e(child) / 2)) - center);
            if (absDistance < absClosest) {
                absClosest = absDistance;
                closestChild = child;
            }
        }
        return closestChild;
    }

    @Nullable
    private static View b(g layoutManager, ad helper) {
        int childCount = layoutManager.s();
        if (childCount == 0) {
            return null;
        }
        View closestChild = null;
        int startest = Integer.MAX_VALUE;
        for (int i = 0; i < childCount; i++) {
            View child = layoutManager.g(i);
            int childStart = helper.a(child);
            if (childStart < startest) {
                startest = childStart;
                closestChild = child;
            }
        }
        return closestChild;
    }

    @NonNull
    private ad c(@NonNull g layoutManager) {
        if (this.b == null || this.b.a != layoutManager) {
            this.b = ad.b(layoutManager);
        }
        return this.b;
    }

    @NonNull
    private ad d(@NonNull g layoutManager) {
        if (this.c == null || this.c.a != layoutManager) {
            this.c = ad.a(layoutManager);
        }
        return this.c;
    }
}
