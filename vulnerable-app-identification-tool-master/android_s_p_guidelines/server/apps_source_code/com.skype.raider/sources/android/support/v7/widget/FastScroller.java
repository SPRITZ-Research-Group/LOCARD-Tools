package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView.f;
import android.support.v7.widget.RecyclerView.j;
import android.support.v7.widget.RecyclerView.k;
import android.view.MotionEvent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@VisibleForTesting
final class FastScroller extends f implements j {
    private static final int[] g = new int[]{16842919};
    private static final int[] h = new int[0];
    private final int[] A = new int[2];
    private final ValueAnimator B = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
    private int C = 0;
    private final Runnable D = new Runnable(this) {
        final /* synthetic */ FastScroller a;

        {
            this.a = this$0;
        }

        public final void run() {
            this.a.a();
        }
    };
    private final k E = new k(this) {
        final /* synthetic */ FastScroller a;

        {
            this.a = this$0;
        }

        public final void a(RecyclerView recyclerView, int dx, int dy) {
            this.a.a(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
        }
    };
    @VisibleForTesting
    int a;
    @VisibleForTesting
    int b;
    @VisibleForTesting
    float c;
    @VisibleForTesting
    int d;
    @VisibleForTesting
    int e;
    @VisibleForTesting
    float f;
    private final int i;
    private final int j;
    private final StateListDrawable k;
    private final Drawable l;
    private final int m;
    private final int n;
    private final StateListDrawable o;
    private final Drawable p;
    private final int q;
    private final int r;
    private int s = 0;
    private int t = 0;
    private RecyclerView u;
    private boolean v = false;
    private boolean w = false;
    private int x = 0;
    private int y = 0;
    private final int[] z = new int[2];

    @Retention(RetentionPolicy.SOURCE)
    private @interface AnimationState {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface DragState {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface State {
    }

    private class a extends AnimatorListenerAdapter {
        final /* synthetic */ FastScroller a;
        private boolean b;

        private a(FastScroller fastScroller) {
            this.a = fastScroller;
            this.b = false;
        }

        /* synthetic */ a(FastScroller x0, byte b) {
            this(x0);
        }

        public final void onAnimationEnd(Animator animation) {
            if (this.b) {
                this.b = false;
            } else if (((Float) this.a.B.getAnimatedValue()).floatValue() == 0.0f) {
                this.a.C = 0;
                this.a.a(0);
            } else {
                this.a.C = 2;
                this.a.u.invalidate();
            }
        }

        public final void onAnimationCancel(Animator animation) {
            this.b = true;
        }
    }

    private class b implements AnimatorUpdateListener {
        final /* synthetic */ FastScroller a;

        private b(FastScroller fastScroller) {
            this.a = fastScroller;
        }

        /* synthetic */ b(FastScroller x0, byte b) {
            this(x0);
        }

        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            int alpha = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            this.a.k.setAlpha(alpha);
            this.a.l.setAlpha(alpha);
            this.a.u.invalidate();
        }
    }

    FastScroller(RecyclerView recyclerView, StateListDrawable verticalThumbDrawable, Drawable verticalTrackDrawable, StateListDrawable horizontalThumbDrawable, Drawable horizontalTrackDrawable, int defaultWidth, int scrollbarMinimumRange, int margin) {
        boolean z = false;
        this.k = verticalThumbDrawable;
        this.l = verticalTrackDrawable;
        this.o = horizontalThumbDrawable;
        this.p = horizontalTrackDrawable;
        this.m = Math.max(defaultWidth, verticalThumbDrawable.getIntrinsicWidth());
        this.n = Math.max(defaultWidth, verticalTrackDrawable.getIntrinsicWidth());
        this.q = Math.max(defaultWidth, horizontalThumbDrawable.getIntrinsicWidth());
        this.r = Math.max(defaultWidth, horizontalTrackDrawable.getIntrinsicWidth());
        this.i = scrollbarMinimumRange;
        this.j = margin;
        this.k.setAlpha(255);
        this.l.setAlpha(255);
        this.B.addListener(new a());
        this.B.addUpdateListener(new b());
        if (this.u != recyclerView) {
            if (this.u != null) {
                RecyclerView recyclerView2 = this.u;
                if (recyclerView2.m != null) {
                    recyclerView2.m.a("Cannot remove item decoration during a scroll  or layout");
                }
                recyclerView2.o.remove(this);
                if (recyclerView2.o.isEmpty()) {
                    if (recyclerView2.getOverScrollMode() == 2) {
                        z = true;
                    }
                    recyclerView2.setWillNotDraw(z);
                }
                recyclerView2.m();
                recyclerView2.requestLayout();
                this.u.b((j) this);
                this.u.b(this.E);
                d();
            }
            this.u = recyclerView;
            if (this.u != null) {
                this.u.a((f) this);
                this.u.a((j) this);
                this.u.a(this.E);
            }
        }
    }

    private void a(int state) {
        if (state == 2 && this.x != 2) {
            this.k.setState(g);
            d();
        }
        if (state == 0) {
            this.u.invalidate();
        } else {
            c();
        }
        if (this.x == 2 && state != 2) {
            this.k.setState(h);
            b(1200);
        } else if (state == 1) {
            b(1500);
        }
        this.x = state;
    }

    private boolean b() {
        return ViewCompat.f(this.u) == 1;
    }

    private void c() {
        switch (this.C) {
            case 0:
                break;
            case 3:
                this.B.cancel();
                break;
            default:
                return;
        }
        this.C = 1;
        this.B.setFloatValues(new float[]{((Float) this.B.getAnimatedValue()).floatValue(), 1.0f});
        this.B.setDuration(500);
        this.B.setStartDelay(0);
        this.B.start();
    }

    @VisibleForTesting
    final void a() {
        switch (this.C) {
            case 1:
                this.B.cancel();
                break;
            case 2:
                break;
            default:
                return;
        }
        this.C = 3;
        this.B.setFloatValues(new float[]{((Float) this.B.getAnimatedValue()).floatValue(), 0.0f});
        this.B.setDuration(500);
        this.B.start();
    }

    private void d() {
        this.u.removeCallbacks(this.D);
    }

    private void b(int delay) {
        d();
        this.u.postDelayed(this.D, (long) delay);
    }

    public final void a(Canvas canvas) {
        if (this.s != this.u.getWidth() || this.t != this.u.getHeight()) {
            this.s = this.u.getWidth();
            this.t = this.u.getHeight();
            a(0);
        } else if (this.C != 0) {
            int i;
            int i2;
            if (this.v) {
                i = this.s - this.m;
                i2 = this.b - (this.a / 2);
                this.k.setBounds(0, 0, this.m, this.a);
                this.l.setBounds(0, 0, this.n, this.t);
                if (b()) {
                    this.l.draw(canvas);
                    canvas.translate((float) this.m, (float) i2);
                    canvas.scale(-1.0f, 1.0f);
                    this.k.draw(canvas);
                    canvas.scale(1.0f, 1.0f);
                    canvas.translate((float) (-this.m), (float) (-i2));
                } else {
                    canvas.translate((float) i, 0.0f);
                    this.l.draw(canvas);
                    canvas.translate(0.0f, (float) i2);
                    this.k.draw(canvas);
                    canvas.translate((float) (-i), (float) (-i2));
                }
            }
            if (this.w) {
                i = this.t - this.q;
                i2 = this.e - (this.d / 2);
                this.o.setBounds(0, 0, this.d, this.q);
                this.p.setBounds(0, 0, this.s, this.r);
                canvas.translate(0.0f, (float) i);
                this.p.draw(canvas);
                canvas.translate((float) i2, 0.0f);
                this.o.draw(canvas);
                canvas.translate((float) (-i2), (float) (-i));
            }
        }
    }

    final void a(int offsetX, int offsetY) {
        boolean z;
        int verticalContentLength = this.u.computeVerticalScrollRange();
        int verticalVisibleLength = this.t;
        if (verticalContentLength - verticalVisibleLength <= 0 || this.t < this.i) {
            z = false;
        } else {
            z = true;
        }
        this.v = z;
        int horizontalContentLength = this.u.computeHorizontalScrollRange();
        int horizontalVisibleLength = this.s;
        if (horizontalContentLength - horizontalVisibleLength <= 0 || this.s < this.i) {
            z = false;
        } else {
            z = true;
        }
        this.w = z;
        if (this.v || this.w) {
            if (this.v) {
                this.b = (int) ((((float) verticalVisibleLength) * (((float) offsetY) + (((float) verticalVisibleLength) / 2.0f))) / ((float) verticalContentLength));
                this.a = Math.min(verticalVisibleLength, (verticalVisibleLength * verticalVisibleLength) / verticalContentLength);
            }
            if (this.w) {
                this.e = (int) ((((float) horizontalVisibleLength) * (((float) offsetX) + (((float) horizontalVisibleLength) / 2.0f))) / ((float) horizontalContentLength));
                this.d = Math.min(horizontalVisibleLength, (horizontalVisibleLength * horizontalVisibleLength) / horizontalContentLength);
            }
            if (this.x == 0 || this.x == 1) {
                a(1);
            }
        } else if (this.x != 0) {
            a(0);
        }
    }

    public final boolean a(MotionEvent ev) {
        if (this.x == 1) {
            boolean insideVerticalThumb = a(ev.getX(), ev.getY());
            boolean insideHorizontalThumb = b(ev.getX(), ev.getY());
            if (ev.getAction() != 0 || (!insideVerticalThumb && !insideHorizontalThumb)) {
                return false;
            }
            if (insideHorizontalThumb) {
                this.y = 1;
                this.f = (float) ((int) ev.getX());
            } else if (insideVerticalThumb) {
                this.y = 2;
                this.c = (float) ((int) ev.getY());
            }
            a(2);
            return true;
        } else if (this.x == 2) {
            return true;
        } else {
            return false;
        }
    }

    public final void b(MotionEvent me) {
        if (this.x != 0) {
            if (me.getAction() == 0) {
                boolean insideVerticalThumb = a(me.getX(), me.getY());
                boolean insideHorizontalThumb = b(me.getX(), me.getY());
                if (insideVerticalThumb || insideHorizontalThumb) {
                    if (insideHorizontalThumb) {
                        this.y = 1;
                        this.f = (float) ((int) me.getX());
                    } else if (insideVerticalThumb) {
                        this.y = 2;
                        this.c = (float) ((int) me.getY());
                    }
                    a(2);
                }
            } else if (me.getAction() == 1 && this.x == 2) {
                this.c = 0.0f;
                this.f = 0.0f;
                a(1);
                this.y = 0;
            } else if (me.getAction() == 2 && this.x == 2) {
                float x;
                int[] iArr;
                float max;
                int a;
                c();
                if (this.y == 1) {
                    x = me.getX();
                    this.A[0] = this.j;
                    this.A[1] = this.s - this.j;
                    iArr = this.A;
                    max = Math.max((float) iArr[0], Math.min((float) iArr[1], x));
                    if (Math.abs(((float) this.e) - max) >= 2.0f) {
                        a = a(this.f, max, iArr, this.u.computeHorizontalScrollRange(), this.u.computeHorizontalScrollOffset(), this.s);
                        if (a != 0) {
                            this.u.scrollBy(a, 0);
                        }
                        this.f = max;
                    }
                }
                if (this.y == 2) {
                    x = me.getY();
                    this.z[0] = this.j;
                    this.z[1] = this.t - this.j;
                    iArr = this.z;
                    max = Math.max((float) iArr[0], Math.min((float) iArr[1], x));
                    if (Math.abs(((float) this.b) - max) >= 2.0f) {
                        a = a(this.c, max, iArr, this.u.computeVerticalScrollRange(), this.u.computeVerticalScrollOffset(), this.t);
                        if (a != 0) {
                            this.u.scrollBy(0, a);
                        }
                        this.c = max;
                    }
                }
            }
        }
    }

    private static int a(float oldDragPos, float newDragPos, int[] scrollbarRange, int scrollRange, int scrollOffset, int viewLength) {
        int scrollbarLength = scrollbarRange[1] - scrollbarRange[0];
        if (scrollbarLength == 0) {
            return 0;
        }
        int totalPossibleOffset = scrollRange - viewLength;
        int scrollingBy = (int) (((float) totalPossibleOffset) * ((newDragPos - oldDragPos) / ((float) scrollbarLength)));
        int absoluteOffset = scrollOffset + scrollingBy;
        if (absoluteOffset >= totalPossibleOffset || absoluteOffset < 0) {
            return 0;
        }
        return scrollingBy;
    }

    @VisibleForTesting
    private boolean a(float x, float y) {
        if (b() ? x <= ((float) (this.m / 2)) : x >= ((float) (this.s - this.m))) {
            if (y >= ((float) (this.b - (this.a / 2))) && y <= ((float) (this.b + (this.a / 2)))) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    private boolean b(float x, float y) {
        return y >= ((float) (this.t - this.q)) && x >= ((float) (this.e - (this.d / 2))) && x <= ((float) (this.e + (this.d / 2)));
    }
}
