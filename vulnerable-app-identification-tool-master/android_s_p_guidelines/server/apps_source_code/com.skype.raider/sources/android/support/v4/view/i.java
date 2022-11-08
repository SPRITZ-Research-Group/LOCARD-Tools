package android.support.v4.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewParent;

public final class i {
    private ViewParent a;
    private ViewParent b;
    private final View c;
    private boolean d;
    private int[] e;

    public i(@NonNull View view) {
        this.c = view;
    }

    public final void a(boolean enabled) {
        if (this.d) {
            ViewCompat.A(this.c);
        }
        this.d = enabled;
    }

    public final boolean a() {
        return this.d;
    }

    public final boolean b() {
        return a(0);
    }

    public final boolean a(int type) {
        return d(type) != null;
    }

    public final boolean b(int axes) {
        return a(axes, 0);
    }

    public final boolean a(int axes, int type) {
        if (a(type)) {
            return true;
        }
        if (this.d) {
            View child = this.c;
            for (ViewParent p = this.c.getParent(); p != null; p = p.getParent()) {
                if (r.a(p, child, this.c, axes, type)) {
                    a(type, p);
                    r.b(p, child, this.c, axes, type);
                    return true;
                }
                if (p instanceof View) {
                    child = (View) p;
                }
            }
        }
        return false;
    }

    public final void c() {
        c(0);
    }

    public final void c(int type) {
        ViewParent parent = d(type);
        if (parent != null) {
            r.a(parent, this.c, type);
            a(type, null);
        }
    }

    public final boolean a(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow) {
        return a(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, 0);
    }

    public final boolean a(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {
        return a(dx, dy, consumed, offsetInWindow, 0);
    }

    public final boolean a(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        if (this.d) {
            ViewParent parent = d(type);
            if (parent == null) {
                return false;
            }
            if (dx != 0 || dy != 0) {
                int startX = 0;
                int startY = 0;
                if (offsetInWindow != null) {
                    this.c.getLocationInWindow(offsetInWindow);
                    startX = offsetInWindow[0];
                    startY = offsetInWindow[1];
                }
                if (consumed == null) {
                    if (this.e == null) {
                        this.e = new int[2];
                    }
                    consumed = this.e;
                }
                consumed[0] = 0;
                consumed[1] = 0;
                r.a(parent, this.c, dx, dy, consumed, type);
                if (offsetInWindow != null) {
                    this.c.getLocationInWindow(offsetInWindow);
                    offsetInWindow[0] = offsetInWindow[0] - startX;
                    offsetInWindow[1] = offsetInWindow[1] - startY;
                }
                return (consumed[0] == 0 && consumed[1] == 0) ? false : true;
            } else if (offsetInWindow != null) {
                offsetInWindow[0] = 0;
                offsetInWindow[1] = 0;
            }
        }
        return false;
    }

    public final boolean a(float velocityX, float velocityY, boolean consumed) {
        if (!this.d) {
            return false;
        }
        ViewParent parent = d(0);
        if (parent != null) {
            return r.a(parent, this.c, velocityX, velocityY, consumed);
        }
        return false;
    }

    public final boolean a(float velocityX, float velocityY) {
        if (!this.d) {
            return false;
        }
        ViewParent parent = d(0);
        if (parent != null) {
            return r.a(parent, this.c, velocityX, velocityY);
        }
        return false;
    }

    private ViewParent d(int type) {
        switch (type) {
            case 0:
                return this.a;
            case 1:
                return this.b;
            default:
                return null;
        }
    }

    private void a(int type, ViewParent p) {
        switch (type) {
            case 0:
                this.a = p;
                return;
            case 1:
                this.b = p;
                return;
            default:
                return;
        }
    }

    public final boolean a(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        if (this.d) {
            ViewParent parent = d(type);
            if (parent == null) {
                return false;
            }
            if (dxConsumed != 0 || dyConsumed != 0 || dxUnconsumed != 0 || dyUnconsumed != 0) {
                int startX = 0;
                int startY = 0;
                if (offsetInWindow != null) {
                    this.c.getLocationInWindow(offsetInWindow);
                    startX = offsetInWindow[0];
                    startY = offsetInWindow[1];
                }
                r.a(parent, this.c, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
                if (offsetInWindow != null) {
                    this.c.getLocationInWindow(offsetInWindow);
                    offsetInWindow[0] = offsetInWindow[0] - startX;
                    offsetInWindow[1] = offsetInWindow[1] - startY;
                }
                return true;
            } else if (offsetInWindow != null) {
                offsetInWindow[0] = 0;
                offsetInWindow[1] = 0;
            }
        }
        return false;
    }
}
