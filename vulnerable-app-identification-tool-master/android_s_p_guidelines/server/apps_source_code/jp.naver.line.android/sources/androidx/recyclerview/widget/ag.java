package androidx.recyclerview.widget;

import android.graphics.Canvas;
import android.view.animation.Interpolator;
import defpackage.hs;
import defpackage.lx;
import java.util.List;

public abstract class ag {
    private static final Interpolator a = new Interpolator() {
        public final float getInterpolation(float f) {
            return (((f * f) * f) * f) * f;
        }
    };
    private static final Interpolator b = new Interpolator() {
        public final float getInterpolation(float f) {
            f -= 1.0f;
            return ((((f * f) * f) * f) * f) + 1.0f;
        }
    };
    private int c = -1;

    public static float a(float f) {
        return f;
    }

    public static int a(int i, int i2) {
        int i3 = i & 789516;
        if (i3 == 0) {
            return i;
        }
        i &= i3 ^ -1;
        if (i2 == 0) {
            return i | (i3 << 2);
        }
        i2 = i3 << 1;
        return (i | (-789517 & i2)) | ((i2 & 789516) << 2);
    }

    public static float b(float f) {
        return f;
    }

    public static int b(int i, int i2) {
        return (i << 16) | ((i2 << 8) | ((i2 | i) << 0));
    }

    public static int c(int i, int i2) {
        int i3 = i & 3158064;
        if (i3 == 0) {
            return i;
        }
        i &= i3 ^ -1;
        if (i2 == 0) {
            return i | (i3 >> 2);
        }
        i2 = i3 >> 1;
        return (i | (-3158065 & i2)) | ((i2 & 3158064) >> 2);
    }

    public abstract int a(RecyclerView recyclerView, cb cbVar);

    public abstract void a(cb cbVar);

    public boolean a() {
        return true;
    }

    public abstract boolean a(cb cbVar, cb cbVar2);

    public boolean b() {
        return true;
    }

    public float c() {
        return 0.5f;
    }

    final int b(RecyclerView recyclerView, cb cbVar) {
        return c(a(recyclerView, cbVar), hs.g(recyclerView));
    }

    final boolean c(RecyclerView recyclerView, cb cbVar) {
        return (b(recyclerView, cbVar) & 16711680) != 0;
    }

    public static cb a(cb cbVar, List<cb> list, int i, int i2) {
        int width = cbVar.itemView.getWidth() + i;
        int height = cbVar.itemView.getHeight() + i2;
        int left = i - cbVar.itemView.getLeft();
        int top = i2 - cbVar.itemView.getTop();
        int size = list.size();
        cb cbVar2 = null;
        int i3 = -1;
        for (int i4 = 0; i4 < size; i4++) {
            int right;
            cb cbVar3 = (cb) list.get(i4);
            if (left > 0) {
                right = cbVar3.itemView.getRight() - width;
                if (right < 0 && cbVar3.itemView.getRight() > cbVar.itemView.getRight()) {
                    right = Math.abs(right);
                    if (right > i3) {
                        cbVar2 = cbVar3;
                        i3 = right;
                    }
                }
            }
            if (left < 0) {
                right = cbVar3.itemView.getLeft() - i;
                if (right > 0 && cbVar3.itemView.getLeft() < cbVar.itemView.getLeft()) {
                    right = Math.abs(right);
                    if (right > i3) {
                        cbVar2 = cbVar3;
                        i3 = right;
                    }
                }
            }
            if (top < 0) {
                right = cbVar3.itemView.getTop() - i2;
                if (right > 0 && cbVar3.itemView.getTop() < cbVar.itemView.getTop()) {
                    right = Math.abs(right);
                    if (right > i3) {
                        cbVar2 = cbVar3;
                        i3 = right;
                    }
                }
            }
            if (top > 0) {
                right = cbVar3.itemView.getBottom() - height;
                if (right < 0 && cbVar3.itemView.getBottom() > cbVar.itemView.getBottom()) {
                    right = Math.abs(right);
                    if (right > i3) {
                        cbVar2 = cbVar3;
                        i3 = right;
                    }
                }
            }
        }
        return cbVar2;
    }

    public void a(cb cbVar, int i) {
        if (cbVar != null) {
            al alVar = am.a;
        }
    }

    public static void a(RecyclerView recyclerView, cb cbVar, cb cbVar2, int i) {
        bj layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof ak) {
            ((ak) layoutManager).a(cbVar.itemView, cbVar2.itemView);
            return;
        }
        if (layoutManager.h()) {
            if (layoutManager.i(cbVar2.itemView) <= recyclerView.getPaddingLeft()) {
                recyclerView.scrollToPosition(i);
            }
            if (layoutManager.k(cbVar2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                recyclerView.scrollToPosition(i);
            }
        }
        if (layoutManager.i()) {
            if (layoutManager.j(cbVar2.itemView) <= recyclerView.getPaddingTop()) {
                recyclerView.scrollToPosition(i);
            }
            if (layoutManager.l(cbVar2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                recyclerView.scrollToPosition(i);
            }
        }
    }

    public void d(RecyclerView recyclerView, cb cbVar) {
        am.a.a(cbVar.itemView);
    }

    public void a(Canvas canvas, RecyclerView recyclerView, cb cbVar, float f, float f2, int i, boolean z) {
        am.a.a(recyclerView, cbVar.itemView, f, f2, z);
    }

    public void b(Canvas canvas, RecyclerView recyclerView, cb cbVar, float f, float f2, int i, boolean z) {
        al alVar = am.a;
    }

    public long a(RecyclerView recyclerView, int i) {
        be itemAnimator = recyclerView.getItemAnimator();
        if (itemAnimator == null) {
            return i == 8 ? 200 : 250;
        } else {
            if (i == 8) {
                return itemAnimator.e();
            }
            return itemAnimator.i();
        }
    }

    public int a(RecyclerView recyclerView, int i, int i2, int i3, long j) {
        if (this.c == -1) {
            this.c = recyclerView.getResources().getDimensionPixelSize(lx.item_touch_helper_max_drag_scroll_per_frame);
        }
        int signum = (int) Math.signum((float) i2);
        float f = 1.0f;
        int interpolation = (int) (((float) (signum * this.c)) * b.getInterpolation(Math.min(1.0f, (((float) Math.abs(i2)) * 1.0f) / ((float) i))));
        if (j <= 2000) {
            f = ((float) j) / 2000.0f;
        }
        interpolation = (int) (((float) interpolation) * a.getInterpolation(f));
        if (interpolation != 0) {
            return interpolation;
        }
        if (i2 > 0) {
            return 1;
        }
        return -1;
    }
}
