package android.support.v4.view;

import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewParent;

public final class r {
    static final c a;

    static class c {
        c() {
        }

        public boolean a(ViewParent parent, View child, View target, int nestedScrollAxes) {
            if (parent instanceof j) {
                return ((j) parent).onStartNestedScroll(child, target, nestedScrollAxes);
            }
            return false;
        }

        public void b(ViewParent parent, View child, View target, int nestedScrollAxes) {
            if (parent instanceof j) {
                ((j) parent).onNestedScrollAccepted(child, target, nestedScrollAxes);
            }
        }

        public void a(ViewParent parent, View target) {
            if (parent instanceof j) {
                ((j) parent).onStopNestedScroll(target);
            }
        }

        public void a(ViewParent parent, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
            if (parent instanceof j) {
                ((j) parent).onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
            }
        }

        public void a(ViewParent parent, View target, int dx, int dy, int[] consumed) {
            if (parent instanceof j) {
                ((j) parent).onNestedPreScroll(target, dx, dy, consumed);
            }
        }

        public boolean a(ViewParent parent, View target, float velocityX, float velocityY, boolean consumed) {
            if (parent instanceof j) {
                return ((j) parent).onNestedFling(target, velocityX, velocityY, consumed);
            }
            return false;
        }

        public boolean a(ViewParent parent, View target, float velocityX, float velocityY) {
            if (parent instanceof j) {
                return ((j) parent).onNestedPreFling(target, velocityX, velocityY);
            }
            return false;
        }
    }

    @RequiresApi(19)
    static class a extends c {
        a() {
        }
    }

    @RequiresApi(21)
    static class b extends a {
        b() {
        }

        public final boolean a(ViewParent parent, View child, View target, int nestedScrollAxes) {
            try {
                return parent.onStartNestedScroll(child, target, nestedScrollAxes);
            } catch (AbstractMethodError e) {
                new StringBuilder("ViewParent ").append(parent).append(" does not implement interface method onStartNestedScroll");
                return false;
            }
        }

        public final void b(ViewParent parent, View child, View target, int nestedScrollAxes) {
            try {
                parent.onNestedScrollAccepted(child, target, nestedScrollAxes);
            } catch (AbstractMethodError e) {
                new StringBuilder("ViewParent ").append(parent).append(" does not implement interface method onNestedScrollAccepted");
            }
        }

        public final void a(ViewParent parent, View target) {
            try {
                parent.onStopNestedScroll(target);
            } catch (AbstractMethodError e) {
                new StringBuilder("ViewParent ").append(parent).append(" does not implement interface method onStopNestedScroll");
            }
        }

        public final void a(ViewParent parent, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
            try {
                parent.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
            } catch (AbstractMethodError e) {
                new StringBuilder("ViewParent ").append(parent).append(" does not implement interface method onNestedScroll");
            }
        }

        public final void a(ViewParent parent, View target, int dx, int dy, int[] consumed) {
            try {
                parent.onNestedPreScroll(target, dx, dy, consumed);
            } catch (AbstractMethodError e) {
                new StringBuilder("ViewParent ").append(parent).append(" does not implement interface method onNestedPreScroll");
            }
        }

        public final boolean a(ViewParent parent, View target, float velocityX, float velocityY, boolean consumed) {
            try {
                return parent.onNestedFling(target, velocityX, velocityY, consumed);
            } catch (AbstractMethodError e) {
                new StringBuilder("ViewParent ").append(parent).append(" does not implement interface method onNestedFling");
                return false;
            }
        }

        public final boolean a(ViewParent parent, View target, float velocityX, float velocityY) {
            try {
                return parent.onNestedPreFling(target, velocityX, velocityY);
            } catch (AbstractMethodError e) {
                new StringBuilder("ViewParent ").append(parent).append(" does not implement interface method onNestedPreFling");
                return false;
            }
        }
    }

    static {
        if (VERSION.SDK_INT >= 21) {
            a = new b();
        } else if (VERSION.SDK_INT >= 19) {
            a = new a();
        } else {
            a = new c();
        }
    }

    public static boolean a(ViewParent parent, View child, View target, int nestedScrollAxes, int type) {
        if (parent instanceof k) {
            return ((k) parent).a();
        }
        if (type == 0) {
            return a.a(parent, child, target, nestedScrollAxes);
        }
        return false;
    }

    public static void b(ViewParent parent, View child, View target, int nestedScrollAxes, int type) {
        if (!(parent instanceof k) && type == 0) {
            a.b(parent, child, target, nestedScrollAxes);
        }
    }

    public static void a(ViewParent parent, View target, int type) {
        if (!(parent instanceof k) && type == 0) {
            a.a(parent, target);
        }
    }

    public static void a(ViewParent parent, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        if (!(parent instanceof k) && type == 0) {
            a.a(parent, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        }
    }

    public static void a(ViewParent parent, View target, int dx, int dy, int[] consumed, int type) {
        if (!(parent instanceof k) && type == 0) {
            a.a(parent, target, dx, dy, consumed);
        }
    }

    public static boolean a(ViewParent parent, View target, float velocityX, float velocityY, boolean consumed) {
        return a.a(parent, target, velocityX, velocityY, consumed);
    }

    public static boolean a(ViewParent parent, View target, float velocityX, float velocityY) {
        return a.a(parent, target, velocityX, velocityY);
    }
}
