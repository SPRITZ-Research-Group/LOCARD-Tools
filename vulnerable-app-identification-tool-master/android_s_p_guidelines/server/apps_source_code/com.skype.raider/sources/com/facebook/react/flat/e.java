package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.animation.Animation;
import com.facebook.common.logging.FLog;
import com.facebook.react.uimanager.r;
import com.facebook.react.uimanager.s;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.Nullable;

abstract class e extends i {
    private static final String f = e.class.getSimpleName();
    protected float[] a = ah.a;
    protected float[] b = ah.a;
    protected float[] c = ah.a;
    protected float[] d = ah.a;
    protected final Rect e = new Rect();
    private final t g;
    private h[] h = h.b;
    private x[] i = x.a;
    private int j;
    private int k;
    private SparseIntArray l = ah.b;
    private final SparseArray<View> m = new SparseArray();
    private final SparseArray<View> n = new SparseArray();
    private final ArrayList<View> o = new ArrayList();
    private final ArrayList<r> p = new ArrayList();

    abstract int a();

    abstract int a(float f);

    abstract int a(int i);

    abstract boolean a(int i, float f);

    e(t flatViewGroup, h[] drawCommands) {
        this.g = flatViewGroup;
        SparseIntArray sparseIntArray = this.l;
        float[] fArr = this.a;
        float[] fArr2 = this.b;
        this.h = drawCommands;
        this.a = fArr;
        this.b = fArr2;
        this.l = sparseIntArray;
        if (this.e.bottom != this.e.top) {
            this.j = a();
            this.k = a(this.j);
        }
        b();
    }

    @Nullable
    public final x a(float touchX, float touchY) {
        int i = a(touchY);
        while (true) {
            int i2 = i;
            i = i2 - 1;
            if (i2 <= 0) {
                break;
            }
            x nodeRegion = this.i[i];
            if (nodeRegion.d) {
                if (a(i, touchY)) {
                    break;
                } else if (nodeRegion.a(touchX, touchY)) {
                    return nodeRegion;
                }
            }
        }
        return null;
    }

    @Nullable
    public final x b(float touchX, float touchY) {
        int i = a(touchY);
        while (true) {
            int i2 = i;
            i = i2 - 1;
            if (i2 <= 0) {
                break;
            }
            x nodeRegion = this.i[i];
            if (a(i, touchY)) {
                break;
            } else if (nodeRegion.a(touchX, touchY)) {
                return nodeRegion;
            }
        }
        return null;
    }

    private void b(int id) {
        this.m.remove(id);
    }

    private boolean c(int id) {
        return this.m.get(id) == null;
    }

    public final boolean b() {
        s.a(this.g, this.e);
        if (this.g.getParent() == null || this.e.top == this.e.bottom) {
            return false;
        }
        int start = a();
        int stop = a(start);
        if (this.j > start || stop > this.k) {
            int i;
            int i2;
            int i3;
            m mVar;
            this.j = start;
            this.k = stop;
            int childCount = this.g.getChildCount();
            for (i = 0; i < childCount; i++) {
                View childAt = this.g.getChildAt(i);
                i2 = this.l.get(childAt.getId());
                if (this.j > i2 || i2 >= this.k) {
                    i2 = 0;
                } else {
                    i2 = 1;
                }
                if (i2 == 0) {
                    Animation animation = childAt.getAnimation();
                    if (animation == null || animation.hasEnded()) {
                        i2 = 0;
                    } else {
                        i2 = 1;
                    }
                    if (i2 == 0) {
                        this.n.append(i, childAt);
                        this.m.put(childAt.getId(), childAt);
                    }
                }
                this.o.add(childAt);
            }
            i = this.n.size();
            int i4 = i > 2 ? 1 : 0;
            if (i4 == 0) {
                while (true) {
                    i2 = i;
                    i = i2 - 1;
                    if (i2 <= 0) {
                        break;
                    }
                    this.g.removeViewsInLayout(this.n.keyAt(i), 1);
                }
            } else {
                this.g.detachAllViewsFromParent();
                for (childCount = 0; childCount < i; childCount++) {
                    this.g.a((View) this.n.valueAt(childCount));
                }
            }
            this.n.clear();
            i2 = this.j;
            int size = this.o.size();
            childCount = 0;
            int i5 = i2;
            for (int i6 = 0; i6 < size; i6++) {
                View view = (View) this.o.get(i6);
                int i7 = this.l.get(view.getId());
                if (i5 <= i7) {
                    i3 = childCount;
                    int i8 = i5;
                    while (i8 != i7) {
                        if (this.h[i8] instanceof m) {
                            mVar = (m) this.h[i8];
                            childCount = i3 + 1;
                            this.g.a((View) this.m.get(mVar.d), i3);
                            b(mVar.d);
                            i5 = childCount;
                        } else {
                            i5 = i3;
                        }
                        i3 = i5;
                        i8++;
                    }
                    i5 = i8 + 1;
                    childCount = i3;
                }
                if (i4 != 0) {
                    this.g.b(view, childCount);
                }
                childCount++;
            }
            this.o.clear();
            while (true) {
                i3 = i5;
                if (i3 < this.k) {
                    if (this.h[i3] instanceof m) {
                        mVar = (m) this.h[i3];
                        i = childCount + 1;
                        this.g.a((View) this.m.get(mVar.d), childCount);
                        b(mVar.d);
                        i2 = i;
                    } else {
                        i2 = childCount;
                    }
                    i5 = i3 + 1;
                    childCount = i2;
                } else {
                    c();
                    return true;
                }
            }
        }
        c();
        return false;
    }

    private void c() {
        int children = this.p.size();
        for (int i = 0; i < children; i++) {
            r view = (r) this.p.get(i);
            if (c(((View) view).getId())) {
                view.b_();
            }
        }
    }

    public final void a(Rect outClippingRect) {
        outClippingRect.set(this.e);
    }

    public final void a(Canvas canvas) {
        int commandIndex;
        int commandIndex2 = this.j;
        int size = this.g.getChildCount();
        for (int i = 0; i < size; i++) {
            int viewIndex = this.l.get(this.g.getChildAt(i).getId());
            if (this.k < viewIndex) {
                while (commandIndex2 < this.k) {
                    commandIndex = commandIndex2 + 1;
                    this.h[commandIndex2].a(this.g, canvas);
                    commandIndex2 = commandIndex;
                }
            } else if (commandIndex2 <= viewIndex) {
                commandIndex = commandIndex2;
                while (commandIndex < viewIndex) {
                    commandIndex2 = commandIndex + 1;
                    this.h[commandIndex].a(this.g, canvas);
                    commandIndex = commandIndex2;
                }
                commandIndex2 = commandIndex + 1;
            }
            this.h[viewIndex].a(this.g, canvas);
        }
        while (commandIndex2 < this.k) {
            commandIndex = commandIndex2 + 1;
            h command = this.h[commandIndex2];
            if (command instanceof m) {
                FLog.w(f, "Unexpected DrawView command at index " + (commandIndex - 1) + " with mStop=" + this.k + ". " + Arrays.toString(this.h));
                commandIndex2 = commandIndex;
            } else {
                command.a(this.g, canvas);
                commandIndex2 = commandIndex;
            }
        }
    }

    final void b(Canvas canvas) {
        for (h drawCommand : this.h) {
            if (!(drawCommand instanceof m) || c(((m) drawCommand).d)) {
                drawCommand.b(this.g, canvas);
            }
        }
    }
}
