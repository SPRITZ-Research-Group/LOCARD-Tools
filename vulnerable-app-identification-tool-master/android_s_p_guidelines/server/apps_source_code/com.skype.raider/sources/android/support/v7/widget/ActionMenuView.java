package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.g;
import android.support.v7.view.menu.i;
import android.support.v7.view.menu.o;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class ActionMenuView extends LinearLayoutCompat implements android.support.v7.view.menu.g.b, o {
    android.support.v7.view.menu.g.a a;
    e b;
    private g c;
    private Context d;
    private int e;
    private boolean f;
    private ActionMenuPresenter g;
    private android.support.v7.view.menu.n.a h;
    private boolean i;
    private int j;
    private int k;
    private int l;

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public interface a {
        boolean f();

        boolean g();
    }

    private static class b implements android.support.v7.view.menu.n.a {
        b() {
        }

        public final void a(g menu, boolean allMenusAreClosing) {
        }

        public final boolean a(g subMenu) {
            return false;
        }
    }

    public static class c extends android.support.v7.widget.LinearLayoutCompat.a {
        @ExportedProperty
        public boolean a;
        @ExportedProperty
        public int b;
        @ExportedProperty
        public int c;
        @ExportedProperty
        public boolean d;
        @ExportedProperty
        public boolean e;
        boolean f;

        public c(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public c(LayoutParams other) {
            super(other);
        }

        public c(c other) {
            super(other);
            this.a = other.a;
        }

        public c() {
            super(-2, -2);
            this.a = false;
        }
    }

    private class d implements android.support.v7.view.menu.g.a {
        final /* synthetic */ ActionMenuView a;

        d(ActionMenuView actionMenuView) {
            this.a = actionMenuView;
        }

        public final boolean a(g menu, MenuItem item) {
            return this.a.b != null && this.a.b.a(item);
        }

        public final void a(g menu) {
            if (this.a.a != null) {
                this.a.a.a(menu);
            }
        }
    }

    public interface e {
        boolean a(MenuItem menuItem);
    }

    public final /* synthetic */ android.support.v7.widget.LinearLayoutCompat.a a(AttributeSet attributeSet) {
        return b(attributeSet);
    }

    protected final /* synthetic */ android.support.v7.widget.LinearLayoutCompat.a b(LayoutParams layoutParams) {
        return a(layoutParams);
    }

    protected /* synthetic */ LayoutParams generateDefaultLayoutParams() {
        return n();
    }

    public /* synthetic */ LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return b(attributeSet);
    }

    protected /* synthetic */ LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return a(layoutParams);
    }

    protected final /* synthetic */ android.support.v7.widget.LinearLayoutCompat.a j() {
        return n();
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public ActionMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBaselineAligned(false);
        float density = context.getResources().getDisplayMetrics().density;
        this.k = (int) (56.0f * density);
        this.l = (int) (4.0f * density);
        this.d = context;
        this.e = 0;
    }

    public void setPopupTheme(@StyleRes int resId) {
        if (this.e != resId) {
            this.e = resId;
            if (resId == 0) {
                this.d = getContext();
            } else {
                this.d = new ContextThemeWrapper(getContext(), resId);
            }
        }
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void setPresenter(ActionMenuPresenter presenter) {
        this.g = presenter;
        this.g.a(this);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.g != null) {
            this.g.a(false);
            if (this.g.k()) {
                this.g.h();
                this.g.g();
            }
        }
    }

    public void setOnMenuItemClickListener(e listener) {
        this.b = listener;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        boolean wasFormatted = this.i;
        this.i = MeasureSpec.getMode(widthMeasureSpec) == ErrorDialogData.SUPPRESSED;
        if (wasFormatted != this.i) {
            this.j = 0;
        }
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (!(!this.i || this.c == null || widthSize == this.j)) {
            this.j = widthSize;
            this.c.a(true);
        }
        int childCount = getChildCount();
        c cVar;
        if (!this.i || childCount <= 0) {
            for (int i = 0; i < childCount; i++) {
                cVar = (c) getChildAt(i).getLayoutParams();
                cVar.rightMargin = 0;
                cVar.leftMargin = 0;
            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int size2 = MeasureSpec.getSize(heightMeasureSpec);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop, -2);
        int i2 = size - paddingLeft;
        int i3 = i2 / this.k;
        size = i2 % this.k;
        if (i3 == 0) {
            setMeasuredDimension(i2, 0);
            return;
        }
        View childAt;
        int i4;
        Object obj;
        Object obj2;
        int i5 = this.k + (size / i3);
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        paddingLeft = 0;
        Object obj3 = null;
        long j = 0;
        int childCount2 = getChildCount();
        int i9 = 0;
        while (i9 < childCount2) {
            int i10;
            int i11;
            long j2;
            int i12;
            childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                boolean z = childAt instanceof ActionMenuItemView;
                i4 = paddingLeft + 1;
                if (z) {
                    childAt.setPadding(this.l, 0, this.l, 0);
                }
                cVar = (c) childAt.getLayoutParams();
                cVar.f = false;
                cVar.c = 0;
                cVar.b = 0;
                cVar.d = false;
                cVar.leftMargin = 0;
                cVar.rightMargin = 0;
                boolean z2 = z && ((ActionMenuItemView) childAt).e();
                cVar.e = z2;
                if (cVar.a) {
                    paddingLeft = 1;
                } else {
                    paddingLeft = i3;
                }
                int a = a(childAt, i5, paddingLeft, childMeasureSpec, paddingTop);
                i7 = Math.max(i7, a);
                if (cVar.d) {
                    paddingLeft = i8 + 1;
                } else {
                    paddingLeft = i8;
                }
                if (cVar.a) {
                    obj = 1;
                } else {
                    obj = obj3;
                }
                int i13 = i3 - a;
                i8 = Math.max(i6, childAt.getMeasuredHeight());
                if (a == 1) {
                    long j3 = ((long) (1 << i9)) | j;
                    i10 = i8;
                    i11 = i13;
                    i8 = paddingLeft;
                    obj3 = obj;
                    j2 = j3;
                    i3 = i7;
                    i12 = i4;
                } else {
                    i12 = i4;
                    i3 = i7;
                    long j4 = j;
                    i10 = i8;
                    i11 = i13;
                    obj3 = obj;
                    i8 = paddingLeft;
                    j2 = j4;
                }
            } else {
                i12 = paddingLeft;
                j2 = j;
                i10 = i6;
                i11 = i3;
                i3 = i7;
            }
            i9++;
            i7 = i3;
            i6 = i10;
            i3 = i11;
            j = j2;
            paddingLeft = i12;
        }
        if (obj3 == null || paddingLeft != 2) {
            obj2 = null;
        } else {
            obj2 = 1;
        }
        Object obj4 = null;
        long j5 = j;
        paddingTop = i3;
        while (i8 > 0 && paddingTop > 0) {
            int i14;
            i4 = Integer.MAX_VALUE;
            j = 0;
            i3 = 0;
            size = 0;
            while (true) {
                i14 = size;
                if (i14 >= childCount2) {
                    break;
                }
                cVar = (c) getChildAt(i14).getLayoutParams();
                if (cVar.d) {
                    if (cVar.b < i4) {
                        i3 = cVar.b;
                        j = (long) (1 << i14);
                        size = 1;
                    } else if (cVar.b == i4) {
                        j |= (long) (1 << i14);
                        size = i3 + 1;
                        i3 = i4;
                    }
                    i14++;
                    i4 = i3;
                }
                size = i3;
                i3 = i4;
                i14++;
                i4 = i3;
            }
            j5 |= j;
            if (i3 > paddingTop) {
                break;
            }
            i14 = i4 + 1;
            i4 = 0;
            i3 = paddingTop;
            long j6 = j5;
            while (i4 < childCount2) {
                View childAt2 = getChildAt(i4);
                cVar = (c) childAt2.getLayoutParams();
                if ((((long) (1 << i4)) & j) != 0) {
                    if (obj2 != null && cVar.e && i3 == 1) {
                        childAt2.setPadding(this.l + i5, 0, this.l, 0);
                    }
                    cVar.b++;
                    cVar.f = true;
                    size = i3 - 1;
                } else if (cVar.b == i14) {
                    j6 |= (long) (1 << i4);
                    size = i3;
                } else {
                    size = i3;
                }
                i4++;
                i3 = size;
            }
            j5 = j6;
            i9 = 1;
            paddingTop = i3;
        }
        j = j5;
        obj = (obj3 == null && paddingLeft == 1) ? 1 : null;
        if (paddingTop <= 0 || j == 0 || (paddingTop >= paddingLeft - 1 && obj == null && i7 <= 1)) {
            obj2 = obj4;
        } else {
            float f;
            View childAt3;
            float bitCount = (float) Long.bitCount(j);
            if (obj == null) {
                if (!((1 & j) == 0 || ((c) getChildAt(0).getLayoutParams()).e)) {
                    bitCount -= 0.5f;
                }
                if (!((((long) (1 << (childCount2 - 1))) & j) == 0 || ((c) getChildAt(childCount2 - 1).getLayoutParams()).e)) {
                    f = bitCount - 0.5f;
                    if (f <= 0.0f) {
                        paddingLeft = (int) (((float) (paddingTop * i5)) / f);
                    } else {
                        paddingLeft = 0;
                    }
                    i3 = 0;
                    obj2 = obj4;
                    while (i3 < childCount2) {
                        if ((((long) (1 << i3)) & j) != 0) {
                            childAt3 = getChildAt(i3);
                            cVar = (c) childAt3.getLayoutParams();
                            if (childAt3 instanceof ActionMenuItemView) {
                                cVar.c = paddingLeft;
                                cVar.f = true;
                                if (i3 == 0 && !cVar.e) {
                                    cVar.leftMargin = (-paddingLeft) / 2;
                                }
                                obj = 1;
                            } else if (cVar.a) {
                                if (i3 != 0) {
                                    cVar.leftMargin = paddingLeft / 2;
                                }
                                if (i3 != childCount2 - 1) {
                                    cVar.rightMargin = paddingLeft / 2;
                                }
                            } else {
                                cVar.c = paddingLeft;
                                cVar.f = true;
                                cVar.rightMargin = (-paddingLeft) / 2;
                                obj = 1;
                            }
                            i3++;
                            obj2 = obj;
                        }
                        obj = obj2;
                        i3++;
                        obj2 = obj;
                    }
                }
            }
            f = bitCount;
            if (f <= 0.0f) {
                paddingLeft = 0;
            } else {
                paddingLeft = (int) (((float) (paddingTop * i5)) / f);
            }
            i3 = 0;
            obj2 = obj4;
            while (i3 < childCount2) {
                if ((((long) (1 << i3)) & j) != 0) {
                    childAt3 = getChildAt(i3);
                    cVar = (c) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        cVar.c = paddingLeft;
                        cVar.f = true;
                        cVar.leftMargin = (-paddingLeft) / 2;
                        obj = 1;
                    } else if (cVar.a) {
                        if (i3 != 0) {
                            cVar.leftMargin = paddingLeft / 2;
                        }
                        if (i3 != childCount2 - 1) {
                            cVar.rightMargin = paddingLeft / 2;
                        }
                    } else {
                        cVar.c = paddingLeft;
                        cVar.f = true;
                        cVar.rightMargin = (-paddingLeft) / 2;
                        obj = 1;
                    }
                    i3++;
                    obj2 = obj;
                }
                obj = obj2;
                i3++;
                obj2 = obj;
            }
        }
        if (obj2 != null) {
            size = 0;
            while (true) {
                paddingLeft = size;
                if (paddingLeft >= childCount2) {
                    break;
                }
                childAt = getChildAt(paddingLeft);
                cVar = (c) childAt.getLayoutParams();
                if (cVar.f) {
                    childAt.measure(MeasureSpec.makeMeasureSpec(cVar.c + (cVar.b * i5), ErrorDialogData.SUPPRESSED), childMeasureSpec);
                }
                size = paddingLeft + 1;
            }
        }
        if (mode == 1073741824) {
            i6 = size2;
        }
        setMeasuredDimension(i2, i6);
    }

    static int a(View child, int cellSize, int cellsRemaining, int parentHeightMeasureSpec, int parentHeightPadding) {
        c lp = (c) child.getLayoutParams();
        int childHeightSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(parentHeightMeasureSpec) - parentHeightPadding, MeasureSpec.getMode(parentHeightMeasureSpec));
        ActionMenuItemView itemView = child instanceof ActionMenuItemView ? (ActionMenuItemView) child : null;
        boolean hasText = itemView != null && itemView.e();
        int cellsUsed = 0;
        if (cellsRemaining > 0 && (!hasText || cellsRemaining >= 2)) {
            child.measure(MeasureSpec.makeMeasureSpec(cellSize * cellsRemaining, Integer.MIN_VALUE), childHeightSpec);
            int measuredWidth = child.getMeasuredWidth();
            cellsUsed = measuredWidth / cellSize;
            if (measuredWidth % cellSize != 0) {
                cellsUsed++;
            }
            if (hasText && cellsUsed < 2) {
                cellsUsed = 2;
            }
        }
        boolean expandable = !lp.a && hasText;
        lp.d = expandable;
        lp.b = cellsUsed;
        child.measure(MeasureSpec.makeMeasureSpec(cellsUsed * cellSize, ErrorDialogData.SUPPRESSED), childHeightSpec);
        return cellsUsed;
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (this.i) {
            int i;
            View v;
            int height;
            int l;
            int t;
            int childCount = getChildCount();
            int midVertical = (bottom - top) / 2;
            int dividerWidth = l();
            int nonOverflowCount = 0;
            int widthRemaining = ((right - left) - getPaddingRight()) - getPaddingLeft();
            boolean hasOverflow = false;
            boolean isLayoutRtl = ax.a(this);
            for (i = 0; i < childCount; i++) {
                v = getChildAt(i);
                if (v.getVisibility() != 8) {
                    c p = (c) v.getLayoutParams();
                    if (p.a) {
                        int r;
                        int overflowWidth = v.getMeasuredWidth();
                        if (b(i)) {
                            overflowWidth += dividerWidth;
                        }
                        height = v.getMeasuredHeight();
                        if (isLayoutRtl) {
                            l = getPaddingLeft() + p.leftMargin;
                            r = l + overflowWidth;
                        } else {
                            r = (getWidth() - getPaddingRight()) - p.rightMargin;
                            l = r - overflowWidth;
                        }
                        t = midVertical - (height / 2);
                        v.layout(l, t, r, t + height);
                        widthRemaining -= overflowWidth;
                        hasOverflow = true;
                    } else {
                        widthRemaining -= (v.getMeasuredWidth() + p.leftMargin) + p.rightMargin;
                        b(i);
                        nonOverflowCount++;
                    }
                }
            }
            int width;
            if (childCount != 1 || hasOverflow) {
                int spacerCount = nonOverflowCount - (hasOverflow ? 0 : 1);
                int spacerSize = Math.max(0, spacerCount > 0 ? widthRemaining / spacerCount : 0);
                c lp;
                if (isLayoutRtl) {
                    int startRight = getWidth() - getPaddingRight();
                    for (i = 0; i < childCount; i++) {
                        v = getChildAt(i);
                        lp = (c) v.getLayoutParams();
                        if (!(v.getVisibility() == 8 || lp.a)) {
                            startRight -= lp.rightMargin;
                            width = v.getMeasuredWidth();
                            height = v.getMeasuredHeight();
                            t = midVertical - (height / 2);
                            v.layout(startRight - width, t, startRight, t + height);
                            startRight -= (lp.leftMargin + width) + spacerSize;
                        }
                    }
                    return;
                }
                int startLeft = getPaddingLeft();
                for (i = 0; i < childCount; i++) {
                    v = getChildAt(i);
                    lp = (c) v.getLayoutParams();
                    if (!(v.getVisibility() == 8 || lp.a)) {
                        startLeft += lp.leftMargin;
                        width = v.getMeasuredWidth();
                        height = v.getMeasuredHeight();
                        t = midVertical - (height / 2);
                        v.layout(startLeft, t, startLeft + width, t + height);
                        startLeft += (lp.rightMargin + width) + spacerSize;
                    }
                }
                return;
            }
            v = getChildAt(0);
            width = v.getMeasuredWidth();
            height = v.getMeasuredHeight();
            l = ((right - left) / 2) - (width / 2);
            t = midVertical - (height / 2);
            v.layout(l, t, l + width, t + height);
            return;
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        i();
    }

    public void setOverflowIcon(@Nullable Drawable icon) {
        c();
        this.g.a(icon);
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final boolean a() {
        return this.f;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void setOverflowReserved(boolean reserveOverflow) {
        this.f = reserveOverflow;
    }

    private static c n() {
        c params = new c();
        params.h = 16;
        return params;
    }

    private c b(AttributeSet attrs) {
        return new c(getContext(), attrs);
    }

    protected static c a(LayoutParams p) {
        if (p == null) {
            return n();
        }
        c result = p instanceof c ? new c((c) p) : new c(p);
        if (result.h > 0) {
            return result;
        }
        result.h = 16;
        return result;
    }

    protected boolean checkLayoutParams(LayoutParams p) {
        return p != null && (p instanceof c);
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public static c b() {
        c result = n();
        result.a = true;
        return result;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final boolean a(i item) {
        return this.c.a((MenuItem) item, 0);
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final void a(g menu) {
        this.c = menu;
    }

    public final Menu c() {
        if (this.c == null) {
            Context context = getContext();
            this.c = new g(context);
            this.c.a(new d(this));
            this.g = new ActionMenuPresenter(context);
            this.g.f();
            this.g.a(this.h != null ? this.h : new b());
            this.c.a(this.g, this.d);
            this.g.a(this);
        }
        return this.c;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void setMenuCallbacks(android.support.v7.view.menu.n.a pcb, android.support.v7.view.menu.g.a mcb) {
        this.h = pcb;
        this.a = mcb;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final g d() {
        return this.c;
    }

    public final boolean e() {
        return this.g != null && this.g.g();
    }

    public final boolean f() {
        return this.g != null && this.g.h();
    }

    public final boolean g() {
        return this.g != null && this.g.k();
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final boolean h() {
        if (this.g != null) {
            boolean z;
            ActionMenuPresenter actionMenuPresenter = this.g;
            if (actionMenuPresenter.j != null || actionMenuPresenter.k()) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final void i() {
        if (this.g != null) {
            this.g.i();
        }
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    private boolean b(int childIndex) {
        if (childIndex == 0) {
            return false;
        }
        View childBefore = getChildAt(childIndex - 1);
        View child = getChildAt(childIndex);
        boolean result = false;
        if (childIndex < getChildCount() && (childBefore instanceof a)) {
            result = ((a) childBefore).g() | 0;
        }
        if (childIndex <= 0 || !(child instanceof a)) {
            return result;
        }
        return result | ((a) child).f();
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return false;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void setExpandedActionViewsExclusive(boolean exclusive) {
        this.g.c(exclusive);
    }
}
