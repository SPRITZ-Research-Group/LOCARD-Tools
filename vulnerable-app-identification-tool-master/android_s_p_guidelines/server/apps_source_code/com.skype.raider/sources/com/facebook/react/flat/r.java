package com.facebook.react.flat;

import android.graphics.Rect;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.h;
import com.facebook.react.uimanager.w;
import javax.annotation.Nullable;

class r extends h {
    static final r[] a = new r[0];
    private static final Rect d = new Rect();
    private static final m f = new m();
    float b;
    boolean c = false;
    private h[] g = h.b;
    private c[] h = c.a;
    private x[] i = x.a;
    private r[] j = a;
    private x k = x.b;
    private int l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    @Nullable
    private m q;
    @Nullable
    private f r;
    private boolean s = true;
    private boolean t;
    private boolean u;
    private Rect v = d;

    r() {
    }

    final void c() {
        if (!this.t) {
            this.t = true;
            int childCount = y();
            for (int i = 0; i != childCount; i++) {
                w child = c(i);
                if (child instanceof r) {
                    ((r) child).j();
                }
            }
        }
    }

    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(int backgroundColor) {
        this.r = backgroundColor == 0 ? null : new f(backgroundColor);
        h();
    }

    public void setOverflow(String overflow) {
        super.setOverflow(overflow);
        this.c = "hidden".equals(overflow);
        if (this.c) {
            this.u = false;
            if (this.b > 0.5f) {
                j();
            }
        } else {
            boolean z;
            Rect rect;
            int y;
            int i;
            w c;
            boolean z2;
            boolean z3 = false;
            int c2 = (int) (this.k.c() - this.k.a());
            int d = (int) (this.k.d() - this.k.b());
            float f = (float) c2;
            float f2 = 0.0f;
            float f3 = (float) d;
            if (!this.c && d > 0 && c2 > 0) {
                x[] xVarArr = this.i;
                int length = xVarArr.length;
                float f4 = 0.0f;
                int i2 = 0;
                while (i2 < length) {
                    float c3;
                    boolean z4;
                    float f5;
                    boolean z5;
                    x xVar = xVarArr[i2];
                    if (xVar.a() < f4) {
                        f4 = xVar.a();
                        z3 = true;
                    }
                    if (xVar.c() > f) {
                        c3 = xVar.c();
                        z4 = true;
                    } else {
                        f5 = f;
                        z4 = z3;
                        c3 = f5;
                    }
                    if (xVar.b() < f2) {
                        f = xVar.b();
                        z = true;
                    } else {
                        f5 = f2;
                        z = z4;
                        f = f5;
                    }
                    if (xVar.d() > f3) {
                        f2 = xVar.d();
                        z5 = true;
                    } else {
                        f5 = f3;
                        z5 = z;
                        f2 = f5;
                    }
                    i2++;
                    f5 = f2;
                    f2 = f;
                    f = c3;
                    z3 = z5;
                    f3 = f5;
                }
                if (z3) {
                    rect = new Rect((int) f4, (int) f2, (int) (f - ((float) c2)), (int) (f3 - ((float) d)));
                    z = z3;
                    if (!(z || this.k == x.b)) {
                        y = y();
                        i = 0;
                        while (i < y) {
                            c = c(i);
                            if ((c instanceof r) || !((r) c).u) {
                                z2 = z;
                            } else {
                                Rect rect2;
                                Rect rect3 = ((r) c).v;
                                if (rect == null) {
                                    rect2 = new Rect();
                                } else {
                                    rect2 = rect;
                                }
                                rect2.union(rect3);
                                rect = rect2;
                                z2 = true;
                            }
                            i++;
                            z = z2;
                        }
                    }
                    if (this.u != z) {
                        this.u = z;
                        if (rect == null) {
                            rect = d;
                        }
                        this.v = rect;
                    }
                }
            }
            rect = null;
            z = z3;
            y = y();
            i = 0;
            while (i < y) {
                c = c(i);
                if (c instanceof r) {
                }
                z2 = z;
                i++;
                z = z2;
            }
            if (this.u != z) {
                this.u = z;
                if (rect == null) {
                    rect = d;
                }
                this.v = rect;
            }
        }
        h();
    }

    public final int d() {
        return this.l;
    }

    public final int e() {
        return this.m;
    }

    public final int f() {
        if (l()) {
            return this.n - this.l;
        }
        return Math.round(this.k.c() - this.k.a());
    }

    public final int g() {
        if (l()) {
            return this.o - this.m;
        }
        return Math.round(this.k.d() - this.k.b());
    }

    public void a(w child, int i) {
        super.a(child, i);
        if (this.t && (child instanceof r)) {
            ((r) child).j();
        }
    }

    protected final void h() {
        r node = this;
        while (true) {
            if (node.l()) {
                if (!node.s) {
                    node.s = true;
                } else {
                    return;
                }
            }
            w parent = node.C();
            if (parent != null) {
                node = (r) parent;
            } else {
                return;
            }
        }
    }

    public void i() {
        super.i();
        this.s = true;
        h();
    }

    final void j() {
        if (!a() && this.q == null) {
            this.q = f;
            h();
            this.k = x.b;
        }
    }

    private boolean l() {
        return this.q != null;
    }

    final void k() {
        this.p = true;
    }
}
