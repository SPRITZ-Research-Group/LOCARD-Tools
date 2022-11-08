package android.support.design.widget;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.n;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class SwipeDismissBehavior<V extends View> extends android.support.design.widget.CoordinatorLayout.b<V> {
    private n a;
    private a b;
    private boolean c;
    private float d = 0.0f;
    private boolean e;
    private int f = 2;
    private float g = 0.5f;
    private float h = 0.0f;
    private float i = 0.5f;
    private final android.support.v4.widget.n.a j = new android.support.v4.widget.n.a(this) {
        final /* synthetic */ SwipeDismissBehavior a;
        private int b;

        {
            this.a = r1;
        }

        public final boolean a(View child) {
            return this.a.b(child);
        }

        public final void b(View capturedChild) {
            this.b = capturedChild.getLeft();
        }

        public final void a(int state) {
            if (this.a.b != null) {
                this.a.b.a(state);
            }
        }

        public final void a(View child, float xvel) {
            int targetLeft;
            Object obj = 1;
            int childWidth = child.getWidth();
            boolean dismiss = false;
            if (xvel != 0.0f) {
                Object obj2;
                if (ViewCompat.f(child) == 1) {
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                if (this.a.f != 2) {
                    if (this.a.f == 0) {
                        if (obj2 != null) {
                            if (xvel >= 0.0f) {
                                obj = null;
                            }
                        } else if (xvel <= 0.0f) {
                            obj = null;
                        }
                    } else if (this.a.f != 1) {
                        obj = null;
                    } else if (obj2 != null) {
                        if (xvel <= 0.0f) {
                            obj = null;
                        }
                    } else if (xvel >= 0.0f) {
                        obj = null;
                    }
                }
            } else {
                if (Math.abs(child.getLeft() - this.b) < Math.round(((float) child.getWidth()) * this.a.g)) {
                    obj = null;
                }
            }
            if (obj != null) {
                targetLeft = child.getLeft() < this.b ? this.b - childWidth : this.b + childWidth;
                dismiss = true;
            } else {
                targetLeft = this.b;
            }
            if (this.a.a.a(targetLeft, child.getTop())) {
                ViewCompat.a(child, new b(this.a, child, dismiss));
            } else if (dismiss && this.a.b != null) {
                this.a.b.a();
            }
        }

        public final int c(View child) {
            return child.getWidth();
        }

        public final int a(View child, int left) {
            int min;
            int max;
            boolean isRtl = ViewCompat.f(child) == 1;
            if (this.a.f == 0) {
                if (isRtl) {
                    min = this.b - child.getWidth();
                    max = this.b;
                } else {
                    min = this.b;
                    max = this.b + child.getWidth();
                }
            } else if (this.a.f != 1) {
                min = this.b - child.getWidth();
                max = this.b + child.getWidth();
            } else if (isRtl) {
                min = this.b;
                max = this.b + child.getWidth();
            } else {
                min = this.b - child.getWidth();
                max = this.b;
            }
            return Math.min(Math.max(min, left), max);
        }

        public final int d(View child) {
            return child.getTop();
        }

        public final void b(View child, int left) {
            float startAlphaDistance = ((float) this.b) + (((float) child.getWidth()) * this.a.h);
            float endAlphaDistance = ((float) this.b) + (((float) child.getWidth()) * this.a.i);
            if (((float) left) <= startAlphaDistance) {
                ViewCompat.b(child, 1.0f);
            } else if (((float) left) >= endAlphaDistance) {
                ViewCompat.b(child, 0.0f);
            } else {
                ViewCompat.b(child, SwipeDismissBehavior.b(1.0f - SwipeDismissBehavior.a(startAlphaDistance, endAlphaDistance, (float) left)));
            }
        }
    };

    public interface a {
        void a();

        void a(int i);
    }

    private class b implements Runnable {
        final /* synthetic */ SwipeDismissBehavior a;
        private final View b;
        private final boolean c;

        b(SwipeDismissBehavior swipeDismissBehavior, View view, boolean dismiss) {
            this.a = swipeDismissBehavior;
            this.b = view;
            this.c = dismiss;
        }

        public final void run() {
            if (this.a.a != null && this.a.a.g()) {
                ViewCompat.a(this.b, (Runnable) this);
            } else if (this.c && this.a.b != null) {
                this.a.b.a();
            }
        }
    }

    public final void a(a listener) {
        this.b = listener;
    }

    public final void a() {
        this.f = 0;
    }

    public final void b() {
        this.h = b(0.1f);
    }

    public final void c() {
        this.i = b(0.6f);
    }

    public boolean a(CoordinatorLayout parent, V child, MotionEvent event) {
        switch (event.getActionMasked()) {
            case 1:
            case 3:
                if (this.c) {
                    this.c = false;
                    return false;
                }
                break;
            default:
                boolean z;
                if (parent.a((View) child, (int) event.getX(), (int) event.getY())) {
                    z = false;
                } else {
                    z = true;
                }
                this.c = z;
                break;
        }
        if (this.c) {
            return false;
        }
        if (this.a == null) {
            n a;
            if (this.e) {
                a = n.a((ViewGroup) parent, this.d, this.j);
            } else {
                a = n.a((ViewGroup) parent, this.j);
            }
            this.a = a;
        }
        return this.a.a(event);
    }

    public final boolean b(CoordinatorLayout parent, V v, MotionEvent event) {
        if (this.a == null) {
            return false;
        }
        this.a.b(event);
        return true;
    }

    public boolean b(@NonNull View view) {
        return true;
    }

    private static float b(float value) {
        return Math.min(Math.max(0.0f, value), 1.0f);
    }

    public final int d() {
        return this.a != null ? this.a.a() : 0;
    }

    static float a(float startValue, float endValue, float value) {
        return (value - startValue) / (endValue - startValue);
    }
}
