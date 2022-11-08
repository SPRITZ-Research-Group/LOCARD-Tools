package androidx.recyclerview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;
import com.google.android.gms.common.api.Api.BaseClientBuilder;
import defpackage.hs;
import defpackage.ij;
import defpackage.im;
import defpackage.lz;

public abstract class bj {
    private final cl a = new cl(this) {
        final /* synthetic */ bj a;

        {
            this.a = r1;
        }

        public final View a(int i) {
            return this.a.g(i);
        }

        public final int a() {
            return this.a.E();
        }

        public final int b() {
            return this.a.C() - this.a.G();
        }

        public final int a(View view) {
            return this.a.i(view) - ((LayoutParams) view.getLayoutParams()).leftMargin;
        }

        public final int b(View view) {
            return this.a.k(view) + ((LayoutParams) view.getLayoutParams()).rightMargin;
        }
    };
    private final cl b = new cl(this) {
        final /* synthetic */ bj a;

        {
            this.a = r1;
        }

        public final View a(int i) {
            return this.a.g(i);
        }

        public final int a() {
            return this.a.F();
        }

        public final int b() {
            return this.a.D() - this.a.H();
        }

        public final int a(View view) {
            return this.a.j(view) - ((LayoutParams) view.getLayoutParams()).topMargin;
        }

        public final int b(View view) {
            return this.a.l(view) + ((LayoutParams) view.getLayoutParams()).bottomMargin;
        }
    };
    private boolean c = true;
    private boolean d = true;
    private int e;
    private int f;
    private int g;
    private int h;
    j p;
    RecyclerView q;
    cj r = new cj(this.a);
    cj s = new cj(this.b);
    bv t;
    boolean u = false;
    boolean v = false;
    boolean w = false;
    int x;
    boolean y;

    public void M() {
    }

    public int a(int i, bs bsVar, by byVar) {
        return 0;
    }

    public View a(View view, int i, bs bsVar, by byVar) {
        return null;
    }

    public void a() {
    }

    public void a(int i, int i2) {
    }

    public void a(int i, int i2, by byVar, bk bkVar) {
    }

    public void a(int i, bk bkVar) {
    }

    public void a(Parcelable parcelable) {
    }

    public void a(RecyclerView recyclerView, bs bsVar) {
    }

    public void a(by byVar) {
    }

    public boolean a(LayoutParams layoutParams) {
        return layoutParams != null;
    }

    public int b(int i, bs bsVar, by byVar) {
        return 0;
    }

    public int b(by byVar) {
        return 0;
    }

    public abstract LayoutParams b();

    public void b(int i, int i2) {
    }

    public void b(RecyclerView recyclerView, int i, int i2) {
    }

    public int c(by byVar) {
        return 0;
    }

    public void c(int i, int i2) {
    }

    public void c(RecyclerView recyclerView) {
    }

    public int d(by byVar) {
        return 0;
    }

    public int e(by byVar) {
        return 0;
    }

    public void e(int i) {
    }

    public boolean e() {
        return false;
    }

    public int f(by byVar) {
        return 0;
    }

    public int g(by byVar) {
        return 0;
    }

    public Parcelable g() {
        return null;
    }

    public boolean h() {
        return false;
    }

    public boolean i() {
        return false;
    }

    public void j(int i) {
    }

    boolean n() {
        return false;
    }

    final void a(RecyclerView recyclerView) {
        if (recyclerView == null) {
            this.q = null;
            this.p = null;
            this.g = 0;
            this.h = 0;
        } else {
            this.q = recyclerView;
            this.p = recyclerView.mChildHelper;
            this.g = recyclerView.getWidth();
            this.h = recyclerView.getHeight();
        }
        this.e = 1073741824;
        this.f = 1073741824;
    }

    final void e(int i, int i2) {
        this.g = MeasureSpec.getSize(i);
        this.e = MeasureSpec.getMode(i);
        if (this.e == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
            this.g = 0;
        }
        this.h = MeasureSpec.getSize(i2);
        this.f = MeasureSpec.getMode(i2);
        if (this.f == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
            this.h = 0;
        }
    }

    final void f(int i, int i2) {
        int z = z();
        if (z == 0) {
            this.q.defaultOnMeasure(i, i2);
            return;
        }
        int i3 = BaseClientBuilder.API_PRIORITY_OTHER;
        int i4 = BaseClientBuilder.API_PRIORITY_OTHER;
        int i5 = Integer.MIN_VALUE;
        int i6 = Integer.MIN_VALUE;
        for (int i7 = 0; i7 < z; i7++) {
            View g = g(i7);
            Rect rect = this.q.mTempRect;
            RecyclerView.getDecoratedBoundsWithMarginsInt(g, rect);
            if (rect.left < i3) {
                i3 = rect.left;
            }
            if (rect.right > i5) {
                i5 = rect.right;
            }
            if (rect.top < i4) {
                i4 = rect.top;
            }
            if (rect.bottom > i6) {
                i6 = rect.bottom;
            }
        }
        this.q.mTempRect.set(i3, i4, i5, i6);
        a(this.q.mTempRect, i, i2);
    }

    public void a(Rect rect, int i, int i2) {
        g(a(i, (rect.width() + E()) + G(), hs.m(this.q)), a(i2, (rect.height() + F()) + H(), hs.n(this.q)));
    }

    public final void s() {
        if (this.q != null) {
            this.q.requestLayout();
        }
    }

    public static int a(int i, int i2, int i3) {
        int mode = MeasureSpec.getMode(i);
        i = MeasureSpec.getSize(i);
        if (mode != Integer.MIN_VALUE) {
            return mode != 1073741824 ? Math.max(i2, i3) : i;
        } else {
            return Math.min(i, Math.max(i2, i3));
        }
    }

    public void a(String str) {
        if (this.q != null) {
            this.q.assertNotInLayoutOrScroll(str);
        }
    }

    public boolean f() {
        return this.w;
    }

    public final void t() {
        if (this.d) {
            this.d = false;
            this.x = 0;
            if (this.q != null) {
                this.q.mRecycler.b();
            }
        }
    }

    public final boolean u() {
        return this.d;
    }

    final void b(RecyclerView recyclerView) {
        this.v = true;
        c(recyclerView);
    }

    final void b(RecyclerView recyclerView, bs bsVar) {
        this.v = false;
        a(recyclerView, bsVar);
    }

    public final boolean a(Runnable runnable) {
        return this.q != null ? this.q.removeCallbacks(runnable) : false;
    }

    public final boolean v() {
        return this.q != null && this.q.mClipToPadding;
    }

    public void c(bs bsVar, by byVar) {
        Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
    }

    public LayoutParams a(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public LayoutParams a(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public void a(RecyclerView recyclerView, int i) {
        Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
    }

    public final void a(bv bvVar) {
        if (!(this.t == null || bvVar == this.t || !this.t.f())) {
            this.t.d();
        }
        this.t = bvVar;
        this.t.a(this.q, this);
    }

    public final boolean w() {
        return this.t != null && this.t.f();
    }

    public final int x() {
        return hs.g(this.q);
    }

    public final void b(View view) {
        a(view, 0, true);
    }

    public final void a(View view, int i) {
        a(view, i, false);
    }

    private void a(View view, int i, boolean z) {
        cb childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (z || childViewHolderInt.isRemoved()) {
            this.q.mViewInfoStore.d(childViewHolderInt);
        } else {
            this.q.mViewInfoStore.e(childViewHolderInt);
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (childViewHolderInt.wasReturnedFromScrap() || childViewHolderInt.isScrap()) {
            if (childViewHolderInt.isScrap()) {
                childViewHolderInt.unScrap();
            } else {
                childViewHolderInt.clearReturnedFromScrapFlag();
            }
            this.p.a(view, i, view.getLayoutParams(), false);
        } else if (view.getParent() == this.q) {
            int c = this.p.c(view);
            if (i == -1) {
                i = this.p.a();
            }
            if (c == -1) {
                StringBuilder stringBuilder = new StringBuilder("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:");
                stringBuilder.append(this.q.indexOfChild(view));
                stringBuilder.append(this.q.exceptionLabel());
                throw new IllegalStateException(stringBuilder.toString());
            } else if (c != i) {
                this.q.mLayout.d(c, i);
            }
        } else {
            this.p.a(view, i, false);
            layoutParams.e = true;
            if (this.t != null && this.t.f()) {
                this.t.a(view);
            }
        }
        if (layoutParams.f) {
            childViewHolderInt.itemView.invalidate();
            layoutParams.f = false;
        }
    }

    private void a(int i) {
        if (g(i) != null) {
            this.p.a(i);
        }
    }

    public final void y() {
        for (int z = z() - 1; z >= 0; z--) {
            this.p.a(z);
        }
    }

    public static int d(View view) {
        return ((LayoutParams) view.getLayoutParams()).c.getLayoutPosition();
    }

    public final View e(View view) {
        if (this.q == null) {
            return null;
        }
        view = this.q.findContainingItemView(view);
        if (view == null || this.p.d(view)) {
            return null;
        }
        return view;
    }

    public View c(int i) {
        int z = z();
        for (int i2 = 0; i2 < z; i2++) {
            View g = g(i2);
            cb childViewHolderInt = RecyclerView.getChildViewHolderInt(g);
            if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == i && !childViewHolderInt.shouldIgnore() && (this.q.mState.g || !childViewHolderInt.isRemoved())) {
                return g;
            }
        }
        return null;
    }

    public final void f(View view) {
        int c = this.p.c(view);
        if (c >= 0) {
            d(c);
        }
    }

    private void b(int i) {
        g(i);
        d(i);
    }

    private void d(int i) {
        this.p.d(i);
    }

    public final void b(View view, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        cb childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt.isRemoved()) {
            this.q.mViewInfoStore.d(childViewHolderInt);
        } else {
            this.q.mViewInfoStore.e(childViewHolderInt);
        }
        this.p.a(view, i, layoutParams, childViewHolderInt.isRemoved());
    }

    private void d(int i, int i2) {
        View g = g(i);
        if (g != null) {
            b(i);
            b(g, i2);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder("Cannot move a child from non-existing index:");
        stringBuilder.append(i);
        stringBuilder.append(this.q.toString());
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public final void a(int i, bs bsVar) {
        a(bsVar, i, g(i));
    }

    public final void b(int i, bs bsVar) {
        View g = g(i);
        a(i);
        bsVar.a(g);
    }

    public final int z() {
        return this.p != null ? this.p.a() : 0;
    }

    public final View g(int i) {
        return this.p != null ? this.p.b(i) : null;
    }

    public final int A() {
        return this.e;
    }

    public final int B() {
        return this.f;
    }

    public final int C() {
        return this.g;
    }

    public final int D() {
        return this.h;
    }

    public final int E() {
        return this.q != null ? this.q.getPaddingLeft() : 0;
    }

    public final int F() {
        return this.q != null ? this.q.getPaddingTop() : 0;
    }

    public final int G() {
        return this.q != null ? this.q.getPaddingRight() : 0;
    }

    public final int H() {
        return this.q != null ? this.q.getPaddingBottom() : 0;
    }

    public final int I() {
        return this.q != null ? hs.k(this.q) : 0;
    }

    public final int J() {
        return this.q != null ? hs.l(this.q) : 0;
    }

    public final View K() {
        if (this.q == null) {
            return null;
        }
        View focusedChild = this.q.getFocusedChild();
        if (focusedChild == null || this.p.d(focusedChild)) {
            return null;
        }
        return focusedChild;
    }

    public final int L() {
        az adapter = this.q != null ? this.q.getAdapter() : null;
        return adapter != null ? adapter.getItemCount() : 0;
    }

    public void h(int i) {
        if (this.q != null) {
            this.q.offsetChildrenHorizontal(i);
        }
    }

    public void i(int i) {
        if (this.q != null) {
            this.q.offsetChildrenVertical(i);
        }
    }

    public final void a(bs bsVar) {
        for (int z = z() - 1; z >= 0; z--) {
            a(bsVar, z, g(z));
        }
    }

    private void a(bs bsVar, int i, View view) {
        cb childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (!childViewHolderInt.shouldIgnore()) {
            if (!childViewHolderInt.isInvalid() || childViewHolderInt.isRemoved() || this.q.mAdapter.hasStableIds()) {
                b(i);
                bsVar.c(view);
                this.q.mViewInfoStore.e(childViewHolderInt);
                return;
            }
            a(i);
            bsVar.a(childViewHolderInt);
        }
    }

    final boolean a(View view, int i, int i2, LayoutParams layoutParams) {
        return (this.c && b(view.getMeasuredWidth(), i, layoutParams.width) && b(view.getMeasuredHeight(), i2, layoutParams.height)) ? false : true;
    }

    final boolean b(View view, int i, int i2, LayoutParams layoutParams) {
        return (!view.isLayoutRequested() && this.c && b(view.getWidth(), i, layoutParams.width) && b(view.getHeight(), i2, layoutParams.height)) ? false : true;
    }

    private static boolean b(int i, int i2, int i3) {
        int mode = MeasureSpec.getMode(i2);
        i2 = MeasureSpec.getSize(i2);
        if (i3 > 0 && i != i3) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return i2 >= i;
        } else {
            if (mode != 0) {
                return mode == 1073741824 && i2 == i;
            } else {
                return true;
            }
        }
    }

    public final void c(View view, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect itemDecorInsetsForChild = this.q.getItemDecorInsetsForChild(view);
        int i2 = (itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom) + 0;
        i = a(this.g, this.e, (((E() + G()) + layoutParams.leftMargin) + layoutParams.rightMargin) + (i + (itemDecorInsetsForChild.left + itemDecorInsetsForChild.right)), layoutParams.width, h());
        int a = a(this.h, this.f, (((F() + H()) + layoutParams.topMargin) + layoutParams.bottomMargin) + i2, layoutParams.height, i());
        if (b(view, i, a, layoutParams)) {
            view.measure(i, a);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(int i, int i2, int i3, int i4, boolean z) {
        i -= i3;
        i3 = 0;
        i = Math.max(0, i);
        if (z) {
            if (i4 < 0) {
                if (i4 == -1) {
                    if (i2 != Integer.MIN_VALUE) {
                        if (i2 != 0) {
                        }
                    }
                    i4 = i;
                    i3 = i2;
                    return MeasureSpec.makeMeasureSpec(i4, i3);
                }
                i4 = 0;
                return MeasureSpec.makeMeasureSpec(i4, i3);
            }
        } else if (i4 < 0) {
            if (i4 != -1) {
                if (i4 == -2) {
                    if (i2 == Integer.MIN_VALUE || i2 == 1073741824) {
                        i3 = Integer.MIN_VALUE;
                    }
                    i4 = i;
                    return MeasureSpec.makeMeasureSpec(i4, i3);
                }
                i4 = 0;
                return MeasureSpec.makeMeasureSpec(i4, i3);
            }
            i4 = i;
            i3 = i2;
            return MeasureSpec.makeMeasureSpec(i4, i3);
        }
        i3 = 1073741824;
        return MeasureSpec.makeMeasureSpec(i4, i3);
    }

    public int g(View view) {
        Rect rect = ((LayoutParams) view.getLayoutParams()).d;
        return (view.getMeasuredWidth() + rect.left) + rect.right;
    }

    public int h(View view) {
        Rect rect = ((LayoutParams) view.getLayoutParams()).d;
        return (view.getMeasuredHeight() + rect.top) + rect.bottom;
    }

    public void a(View view, int i, int i2, int i3, int i4) {
        Rect rect = ((LayoutParams) view.getLayoutParams()).d;
        view.layout(i + rect.left, i2 + rect.top, i3 - rect.right, i4 - rect.bottom);
    }

    public static void b(View view, int i, int i2, int i3, int i4) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect = layoutParams.d;
        view.layout((i + rect.left) + layoutParams.leftMargin, (i2 + rect.top) + layoutParams.topMargin, (i3 - rect.right) - layoutParams.rightMargin, (i4 - rect.bottom) - layoutParams.bottomMargin);
    }

    public final void a(View view, Rect rect) {
        Rect rect2 = ((LayoutParams) view.getLayoutParams()).d;
        rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
        if (this.q != null) {
            Matrix matrix = view.getMatrix();
            if (!(matrix == null || matrix.isIdentity())) {
                RectF rectF = this.q.mTempRectF;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom));
            }
        }
        rect.offset(view.getLeft(), view.getTop());
    }

    public static void b(View view, Rect rect) {
        RecyclerView.getDecoratedBoundsWithMarginsInt(view, rect);
    }

    public int i(View view) {
        return view.getLeft() - ((LayoutParams) view.getLayoutParams()).d.left;
    }

    public int j(View view) {
        return view.getTop() - m(view);
    }

    public int k(View view) {
        return view.getRight() + ((LayoutParams) view.getLayoutParams()).d.right;
    }

    public int l(View view) {
        return view.getBottom() + n(view);
    }

    public final void c(View view, Rect rect) {
        if (this.q == null) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(this.q.getItemDecorInsetsForChild(view));
        }
    }

    public static int m(View view) {
        return ((LayoutParams) view.getLayoutParams()).d.top;
    }

    public static int n(View view) {
        return ((LayoutParams) view.getLayoutParams()).d.bottom;
    }

    public void a(RecyclerView recyclerView, int i, int i2) {
        b(recyclerView, i, i2);
    }

    public void a(bs bsVar, by byVar, int i, int i2) {
        this.q.defaultOnMeasure(i, i2);
    }

    public final void g(int i, int i2) {
        this.q.setMeasuredDimension(i, i2);
    }

    final void N() {
        if (this.t != null) {
            this.t.d();
        }
    }

    public final void c(bs bsVar) {
        for (int z = z() - 1; z >= 0; z--) {
            if (!RecyclerView.getChildViewHolderInt(g(z)).shouldIgnore()) {
                b(z, bsVar);
            }
        }
    }

    final void a(View view, ij ijVar) {
        cb childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && !this.p.d(childViewHolderInt.itemView)) {
            a(this.q.mRecycler, this.q.mState, view, ijVar);
        }
    }

    public void a(bs bsVar, by byVar, View view, ij ijVar) {
        ijVar.b(im.a(i() ? d(view) : 0, 1, h() ? d(view) : 0, 1, false));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(bs bsVar, by byVar) {
        if (this.q == null || this.q.mAdapter == null || !i()) {
            return 1;
        }
        return this.q.mAdapter.getItemCount();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int b(bs bsVar, by byVar) {
        if (this.q == null || this.q.mAdapter == null || !h()) {
            return 1;
        }
        return this.q.mAdapter.getItemCount();
    }

    public static bl a(Context context, AttributeSet attributeSet, int i, int i2) {
        bl blVar = new bl();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, lz.RecyclerView, i, i2);
        blVar.a = obtainStyledAttributes.getInt(lz.RecyclerView_android_orientation, 1);
        blVar.b = obtainStyledAttributes.getInt(lz.RecyclerView_spanCount, 1);
        blVar.c = obtainStyledAttributes.getBoolean(lz.RecyclerView_reverseLayout, false);
        blVar.d = obtainStyledAttributes.getBoolean(lz.RecyclerView_stackFromEnd, false);
        obtainStyledAttributes.recycle();
        return blVar;
    }

    final void d(RecyclerView recyclerView) {
        e(MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
    }

    public final void a(View view) {
        a(view, -1, true);
    }

    public final void c(View view) {
        a(view, -1, false);
    }

    public final void a(View view, bs bsVar) {
        this.p.b(view);
        bsVar.a(view);
    }

    final void b(bs bsVar) {
        int size = bsVar.a.size();
        for (int i = size - 1; i >= 0; i--) {
            View view = ((cb) bsVar.a.get(i)).itemView;
            cb childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.setIsRecyclable(false);
                if (childViewHolderInt.isTmpDetached()) {
                    this.q.removeDetachedView(view, false);
                }
                if (this.q.mItemAnimator != null) {
                    this.q.mItemAnimator.c(childViewHolderInt);
                }
                childViewHolderInt.setIsRecyclable(true);
                bsVar.b(view);
            }
        }
        bsVar.a.clear();
        if (bsVar.b != null) {
            bsVar.b.clear();
        }
        if (size > 0) {
            this.q.invalidate();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        int[] iArr = new int[2];
        int E = E();
        int F = F();
        int G = this.g - G();
        int H = this.h - H();
        int left = (view.getLeft() + rect.left) - view.getScrollX();
        int top = (view.getTop() + rect.top) - view.getScrollY();
        int width = rect.width() + left;
        int height = rect.height() + top;
        left -= E;
        int min = Math.min(0, left);
        top -= F;
        F = Math.min(0, top);
        width -= G;
        G = Math.max(0, width);
        height = Math.max(0, height - H);
        if (hs.g(this.q) != 1) {
            if (min == 0) {
                min = Math.min(left, G);
            }
            G = min;
        } else if (G == 0) {
            G = Math.max(min, width);
        }
        if (F == 0) {
            F = Math.min(top, height);
        }
        iArr[0] = G;
        iArr[1] = F;
        width = iArr[0];
        height = iArr[1];
        if (z2) {
            Object obj;
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild != null) {
                int E2 = E();
                F = F();
                G = this.g - G();
                H = this.h - H();
                Rect rect2 = this.q.mTempRect;
                RecyclerView.getDecoratedBoundsWithMarginsInt(focusedChild, rect2);
                if (rect2.left - width < G && rect2.right - width > E2 && rect2.top - height < H && rect2.bottom - height > F) {
                    obj = 1;
                }
            }
            obj = null;
        }
        if (!(width == 0 && height == 0)) {
            if (z) {
                recyclerView.scrollBy(width, height);
            } else {
                recyclerView.smoothScrollBy(width, height);
            }
            return true;
        }
        return false;
    }

    public void a(AccessibilityEvent accessibilityEvent) {
        if (this.q != null && accessibilityEvent != null) {
            boolean z = true;
            if (!(this.q.canScrollVertically(1) || this.q.canScrollVertically(-1) || this.q.canScrollHorizontally(-1) || this.q.canScrollHorizontally(1))) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            if (this.q.mAdapter != null) {
                accessibilityEvent.setItemCount(this.q.mAdapter.getItemCount());
            }
        }
    }

    final boolean k(int i) {
        if (this.q == null) {
            return false;
        }
        int E;
        if (i == 4096) {
            i = this.q.canScrollVertically(1) ? (this.h - F()) - H() : 0;
            if (this.q.canScrollHorizontally(1)) {
                E = (this.g - E()) - G();
                if (i != 0) {
                }
                this.q.smoothScrollBy(E, i);
                return true;
            }
        } else if (i != 8192) {
            i = 0;
        } else {
            i = this.q.canScrollVertically(-1) ? -((this.h - F()) - H()) : 0;
            if (this.q.canScrollHorizontally(-1)) {
                E = -((this.g - E()) - G());
                if (i != 0 && E == 0) {
                    return false;
                }
                this.q.smoothScrollBy(E, i);
                return true;
            }
        }
        E = 0;
        if (i != 0) {
        }
        this.q.smoothScrollBy(E, i);
        return true;
    }
}
