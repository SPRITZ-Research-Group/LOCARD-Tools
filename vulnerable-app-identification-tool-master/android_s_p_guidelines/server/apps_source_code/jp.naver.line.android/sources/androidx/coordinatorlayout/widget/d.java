package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior;
import defpackage.dt;

public final class d extends MarginLayoutParams {
    Behavior a;
    boolean b = false;
    public int c = 0;
    public int d = 0;
    public int e = -1;
    int f = -1;
    public int g = 0;
    public int h = 0;
    int i;
    int j;
    View k;
    View l;
    final Rect m = new Rect();
    Object n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;

    public d() {
        super(-2, -2);
    }

    d(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, dt.CoordinatorLayout_Layout);
        this.c = obtainStyledAttributes.getInteger(dt.CoordinatorLayout_Layout_android_layout_gravity, 0);
        this.f = obtainStyledAttributes.getResourceId(dt.CoordinatorLayout_Layout_layout_anchor, -1);
        this.d = obtainStyledAttributes.getInteger(dt.CoordinatorLayout_Layout_layout_anchorGravity, 0);
        this.e = obtainStyledAttributes.getInteger(dt.CoordinatorLayout_Layout_layout_keyline, -1);
        this.g = obtainStyledAttributes.getInt(dt.CoordinatorLayout_Layout_layout_insetEdge, 0);
        this.h = obtainStyledAttributes.getInt(dt.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
        this.b = obtainStyledAttributes.hasValue(dt.CoordinatorLayout_Layout_layout_behavior);
        if (this.b) {
            this.a = CoordinatorLayout.parseBehavior(context, attributeSet, obtainStyledAttributes.getString(dt.CoordinatorLayout_Layout_layout_behavior));
        }
        obtainStyledAttributes.recycle();
        if (this.a != null) {
            this.a.onAttachedToLayoutParams(this);
        }
    }

    public d(d dVar) {
        super(dVar);
    }

    public d(MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
    }

    public d(LayoutParams layoutParams) {
        super(layoutParams);
    }

    public final int a() {
        return this.f;
    }

    public final Behavior b() {
        return this.a;
    }

    public final void a(Behavior behavior) {
        if (this.a != behavior) {
            if (this.a != null) {
                this.a.onDetachedFromLayoutParams();
            }
            this.a = behavior;
            this.n = null;
            this.b = true;
            if (behavior != null) {
                behavior.onAttachedToLayoutParams(this);
            }
        }
    }

    final boolean c() {
        if (this.a == null) {
            this.o = false;
        }
        return this.o;
    }

    final boolean a(CoordinatorLayout coordinatorLayout, View view) {
        if (this.o) {
            return true;
        }
        boolean blocksInteractionBelow = (this.a != null ? this.a.blocksInteractionBelow(coordinatorLayout, view) : 0) | this.o;
        this.o = blocksInteractionBelow;
        return blocksInteractionBelow;
    }

    final void d() {
        this.o = false;
    }

    final void a(int i, boolean z) {
        switch (i) {
            case 0:
                this.p = z;
                return;
            case 1:
                this.q = z;
                break;
        }
    }

    final boolean a(int i) {
        switch (i) {
            case 0:
                return this.p;
            case 1:
                return this.q;
            default:
                return false;
        }
    }

    final boolean e() {
        return this.r;
    }

    final void a(boolean z) {
        this.r = z;
    }

    final void f() {
        this.r = false;
    }
}
