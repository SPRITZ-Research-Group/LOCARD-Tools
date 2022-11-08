package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.skype.Defines;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerView extends ViewGroup implements android.support.v4.view.h {
    static final Interpolator H = new Interpolator() {
        public final float getInterpolation(float t) {
            t -= 1.0f;
            return ((((t * t) * t) * t) * t) + 1.0f;
        }
    };
    private static final int[] I = new int[]{16843830};
    private static final int[] J = new int[]{16842987};
    private static final boolean K;
    private static final boolean L;
    private static final boolean M;
    private static final Class<?>[] N = new Class[]{Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE};
    static final boolean a;
    static final boolean b;
    static final boolean c;
    a A;
    final State B;
    boolean C;
    boolean D;
    boolean E;
    af F;
    @VisibleForTesting
    final List<s> G;
    private final o O;
    private SavedState P;
    private final Rect Q;
    private final ArrayList<j> R;
    private j S;
    private int T;
    private boolean U;
    private int V;
    private final AccessibilityManager W;
    private android.support.v4.view.i aA;
    private final int[] aB;
    private final int[] aC;
    private final int[] aD;
    private Runnable aE;
    private final b aF;
    private List<Object> aa;
    private int ab;
    private int ac;
    private EdgeEffect ad;
    private EdgeEffect ae;
    private EdgeEffect af;
    private EdgeEffect ag;
    private int ah;
    private int ai;
    private VelocityTracker aj;
    private int ak;
    private int al;
    private int am;
    private int an;
    private int ao;
    private i ap;
    private final int aq;
    private final int ar;
    private float as;
    private float at;
    private boolean au;
    private k av;
    private List<k> aw;
    private a ax;
    private d ay;
    private final int[] az;
    final m d;
    e e;
    p f;
    final aw g;
    boolean h;
    final Runnable i;
    final Rect j;
    final RectF k;
    a l;
    @VisibleForTesting
    g m;
    n n;
    final ArrayList<f> o;
    boolean p;
    boolean q;
    boolean r;
    @VisibleForTesting
    boolean s;
    boolean t;
    boolean u;
    boolean v;
    boolean w;
    ItemAnimator x;
    final r y;
    x z;

    public static abstract class s {
        private static final List<Object> o = Collections.EMPTY_LIST;
        public final View a;
        WeakReference<RecyclerView> b;
        int c = -1;
        int d = -1;
        long e = -1;
        int f = -1;
        int g = -1;
        s h = null;
        s i = null;
        List<Object> j = null;
        List<Object> k = null;
        @VisibleForTesting
        int l = -1;
        RecyclerView m;
        private int n;
        private int p = 0;
        private m q = null;
        private boolean r = false;
        private int s = 0;

        public s(View itemView) {
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.a = itemView;
        }

        final void a(int offset, boolean applyToPreLayout) {
            if (this.d == -1) {
                this.d = this.c;
            }
            if (this.g == -1) {
                this.g = this.c;
            }
            if (applyToPreLayout) {
                this.g += offset;
            }
            this.c += offset;
            if (this.a.getLayoutParams() != null) {
                ((h) this.a.getLayoutParams()).e = true;
            }
        }

        final void a() {
            this.d = -1;
            this.g = -1;
        }

        final boolean b() {
            return (this.n & 128) != 0;
        }

        public final int c() {
            return this.g == -1 ? this.c : this.g;
        }

        public final int d() {
            if (this.m == null) {
                return -1;
            }
            return this.m.b(this);
        }

        final boolean e() {
            return this.q != null;
        }

        final void f() {
            this.q.b(this);
        }

        final boolean g() {
            return (this.n & 32) != 0;
        }

        final void h() {
            this.n &= -33;
        }

        final void i() {
            this.n &= -257;
        }

        final void a(m recycler, boolean isChangeScrap) {
            this.q = recycler;
            this.r = isChangeScrap;
        }

        final boolean j() {
            return (this.n & 4) != 0;
        }

        final boolean k() {
            return (this.n & 2) != 0;
        }

        final boolean l() {
            return (this.n & 1) != 0;
        }

        final boolean m() {
            return (this.n & 8) != 0;
        }

        final boolean a(int flags) {
            return (this.n & flags) != 0;
        }

        final boolean n() {
            return (this.n & Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE) != 0;
        }

        final void a(int flags, int mask) {
            this.n = (this.n & (mask ^ -1)) | (flags & mask);
        }

        final void b(int flags) {
            this.n |= flags;
        }

        final void a(Object payload) {
            if (payload == null) {
                b(1024);
            } else if ((this.n & 1024) == 0) {
                if (this.j == null) {
                    this.j = new ArrayList();
                    this.k = Collections.unmodifiableList(this.j);
                }
                this.j.add(payload);
            }
        }

        final void o() {
            if (this.j != null) {
                this.j.clear();
            }
            this.n &= -1025;
        }

        final List<Object> p() {
            if ((this.n & 1024) != 0) {
                return o;
            }
            if (this.j == null || this.j.size() == 0) {
                return o;
            }
            return this.k;
        }

        final void q() {
            this.n = 0;
            this.c = -1;
            this.d = -1;
            this.e = -1;
            this.g = -1;
            this.p = 0;
            this.h = null;
            this.i = null;
            o();
            this.s = 0;
            this.l = -1;
            RecyclerView.a(this);
        }

        public String toString() {
            Object obj;
            StringBuilder sb = new StringBuilder("ViewHolder{" + Integer.toHexString(hashCode()) + " position=" + this.c + " id=" + this.e + ", oldPos=" + this.d + ", pLpos:" + this.g);
            if (e()) {
                sb.append(" scrap ").append(this.r ? "[changeScrap]" : "[attachedScrap]");
            }
            if (j()) {
                sb.append(" invalid");
            }
            if (!l()) {
                sb.append(" unbound");
            }
            if (k()) {
                sb.append(" update");
            }
            if (m()) {
                sb.append(" removed");
            }
            if (b()) {
                sb.append(" ignored");
            }
            if (n()) {
                sb.append(" tmpDetached");
            }
            if (!r()) {
                sb.append(" not recyclable(" + this.p + ")");
            }
            if ((this.n & 512) != 0 || j()) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                sb.append(" undefined adapter position");
            }
            if (this.a.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public final void a(boolean recyclable) {
            this.p = recyclable ? this.p - 1 : this.p + 1;
            if (this.p < 0) {
                this.p = 0;
                new StringBuilder("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ").append(this);
            } else if (!recyclable && this.p == 1) {
                this.n |= 16;
            } else if (recyclable && this.p == 0) {
                this.n &= -17;
            }
        }

        public final boolean r() {
            return (this.n & 16) == 0 && !ViewCompat.c(this.a);
        }

        final boolean s() {
            return (this.n & 2) != 0;
        }

        static /* synthetic */ void a(s x0, RecyclerView x1) {
            x0.s = ViewCompat.e(x0.a);
            x1.a(x0, 4);
        }

        static /* synthetic */ void b(s x0, RecyclerView x1) {
            x1.a(x0, x0.s);
            x0.s = 0;
        }

        static /* synthetic */ boolean a(s x0) {
            return (x0.n & 16) == 0 && ViewCompat.c(x0.a);
        }

        static /* synthetic */ boolean e(s x0) {
            return (x0.n & 16) != 0;
        }
    }

    public static abstract class a<VH extends s> {
        private final b a = new b();
        private boolean b = false;

        public abstract int a();

        public abstract VH a(ViewGroup viewGroup, int i);

        public abstract void a(VH vh, int i);

        public final void b(VH holder, int position) {
            holder.c = position;
            if (this.b) {
                holder.e = a(position);
            }
            holder.a(1, 519);
            android.support.v4.os.d.a("RV OnBindView");
            holder.p();
            a((s) holder, position);
            holder.o();
            LayoutParams layoutParams = holder.a.getLayoutParams();
            if (layoutParams instanceof h) {
                ((h) layoutParams).e = true;
            }
            android.support.v4.os.d.a();
        }

        public int b(int position) {
            return 0;
        }

        public long a(int position) {
            return -1;
        }

        public final boolean e() {
            return this.b;
        }

        public void a(VH vh) {
        }

        public final void a(c observer) {
            this.a.registerObserver(observer);
        }

        public final void b(c observer) {
            this.a.unregisterObserver(observer);
        }

        public final void f() {
            this.a.b();
        }

        public final void d() {
            if (this.a.a()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.b = true;
        }
    }

    public static abstract class k {
        public void a(RecyclerView recyclerView, int newState) {
        }

        public void a(RecyclerView recyclerView, int dx, int dy) {
        }
    }

    public static abstract class f {
        public void a(Canvas c) {
        }

        public void a(Rect outRect, View view) {
            ((h) view.getLayoutParams()).c.c();
            outRect.set(0, 0, 0, 0);
        }
    }

    public interface j {
        boolean a(MotionEvent motionEvent);

        void b(MotionEvent motionEvent);
    }

    public static class h extends MarginLayoutParams {
        s c;
        final Rect d = new Rect();
        boolean e = true;
        boolean f = false;

        public h(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public h(int width, int height) {
            super(width, height);
        }

        public h(MarginLayoutParams source) {
            super(source);
        }

        public h(LayoutParams source) {
            super(source);
        }

        public h(h source) {
            super(source);
        }
    }

    public static abstract class g {
        private final b a = new b(this) {
            final /* synthetic */ g a;

            {
                this.a = this$0;
            }

            public final View a(int index) {
                return this.a.g(index);
            }

            public final int a() {
                return this.a.x();
            }

            public final int b() {
                return this.a.v() - this.a.z();
            }

            public final int a(View view) {
                return g.i(view) - ((h) view.getLayoutParams()).leftMargin;
            }

            public final int b(View view) {
                return g.k(view) + ((h) view.getLayoutParams()).rightMargin;
            }
        };
        private final b b = new b(this) {
            final /* synthetic */ g a;

            {
                this.a = this$0;
            }

            public final View a(int index) {
                return this.a.g(index);
            }

            public final int a() {
                return this.a.y();
            }

            public final int b() {
                return this.a.w() - this.a.A();
            }

            public final int a(View view) {
                return g.j(view) - ((h) view.getLayoutParams()).topMargin;
            }

            public final int b(View view) {
                return g.l(view) + ((h) view.getLayoutParams()).bottomMargin;
            }
        };
        private boolean c = true;
        private boolean d = true;
        private int e;
        private int f;
        private int g;
        private int h;
        p p;
        RecyclerView q;
        ViewBoundsCheck r = new ViewBoundsCheck(this.a);
        ViewBoundsCheck s = new ViewBoundsCheck(this.b);
        @Nullable
        p t;
        boolean u = false;
        boolean v = false;
        boolean w = false;
        int x;
        boolean y;

        public interface a {
            void a(int i, int i2);
        }

        public static class b {
            public int a;
            public int b;
            public boolean c;
            public boolean d;
        }

        public abstract h b();

        final void a(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.q = null;
                this.p = null;
                this.g = 0;
                this.h = 0;
            } else {
                this.q = recyclerView;
                this.p = recyclerView.f;
                this.g = recyclerView.getWidth();
                this.h = recyclerView.getHeight();
            }
            this.e = ErrorDialogData.SUPPRESSED;
            this.f = ErrorDialogData.SUPPRESSED;
        }

        final void f(int wSpec, int hSpec) {
            this.g = MeasureSpec.getSize(wSpec);
            this.e = MeasureSpec.getMode(wSpec);
            if (this.e == 0 && !RecyclerView.b) {
                this.g = 0;
            }
            this.h = MeasureSpec.getSize(hSpec);
            this.f = MeasureSpec.getMode(hSpec);
            if (this.f == 0 && !RecyclerView.b) {
                this.h = 0;
            }
        }

        final void g(int widthSpec, int heightSpec) {
            int count = s();
            if (count == 0) {
                this.q.d(widthSpec, heightSpec);
                return;
            }
            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;
            for (int i = 0; i < count; i++) {
                View child = g(i);
                Rect bounds = this.q.j;
                RecyclerView.a(child, bounds);
                if (bounds.left < minX) {
                    minX = bounds.left;
                }
                if (bounds.right > maxX) {
                    maxX = bounds.right;
                }
                if (bounds.top < minY) {
                    minY = bounds.top;
                }
                if (bounds.bottom > maxY) {
                    maxY = bounds.bottom;
                }
            }
            this.q.j.set(minX, minY, maxX, maxY);
            a(this.q.j, widthSpec, heightSpec);
        }

        public void a(Rect childrenBounds, int wSpec, int hSpec) {
            h(a(wSpec, (childrenBounds.width() + x()) + z(), ViewCompat.n(this.q)), a(hSpec, (childrenBounds.height() + y()) + A(), ViewCompat.o(this.q)));
        }

        public final void n() {
            if (this.q != null) {
                this.q.requestLayout();
            }
        }

        public static int a(int spec, int desired, int min) {
            int mode = MeasureSpec.getMode(spec);
            int size = MeasureSpec.getSize(spec);
            switch (mode) {
                case Integer.MIN_VALUE:
                    return Math.min(size, Math.max(desired, min));
                case ErrorDialogData.SUPPRESSED /*1073741824*/:
                    return size;
                default:
                    return Math.max(desired, min);
            }
        }

        public void a(String message) {
            if (this.q != null) {
                this.q.a(message);
            }
        }

        public final void o() {
            this.w = true;
        }

        public boolean c() {
            return false;
        }

        public final boolean p() {
            return this.d;
        }

        public void a(int dx, int dy, State state, a layoutPrefetchRegistry) {
        }

        public void a(int adapterItemCount, a layoutPrefetchRegistry) {
        }

        final void b(RecyclerView view, m recycler) {
            this.v = false;
            a(view, recycler);
        }

        public final boolean a(Runnable action) {
            if (this.q != null) {
                return this.q.removeCallbacks(action);
            }
            return false;
        }

        @CallSuper
        public void a(RecyclerView view, m recycler) {
        }

        public final boolean q() {
            return this.q != null && this.q.h;
        }

        public void c(m recycler, State state) {
        }

        public void a(State state) {
        }

        public boolean a(h lp) {
            return lp != null;
        }

        public h a(LayoutParams lp) {
            if (lp instanceof h) {
                return new h((h) lp);
            }
            if (lp instanceof MarginLayoutParams) {
                return new h((MarginLayoutParams) lp);
            }
            return new h(lp);
        }

        public h a(Context c, AttributeSet attrs) {
            return new h(c, attrs);
        }

        public int a(int dx, m recycler, State state) {
            return 0;
        }

        public int b(int dy, m recycler, State state) {
            return 0;
        }

        public boolean e() {
            return false;
        }

        public boolean f() {
            return false;
        }

        public void e(int position) {
        }

        public void a(RecyclerView recyclerView, int position) {
        }

        public final void a(p smoothScroller) {
            if (!(this.t == null || smoothScroller == this.t || !this.t.f())) {
                this.t.d();
            }
            this.t = smoothScroller;
            this.t.a(this.q, this);
        }

        public final boolean r() {
            return this.t != null && this.t.f();
        }

        public final void b(View child) {
            a(child, 0, true);
        }

        public final void d(View child) {
            a(child, 0, false);
        }

        private void a(View child, int index, boolean disappearing) {
            s holder = RecyclerView.d(child);
            if (disappearing || holder.m()) {
                this.q.g.d(holder);
            } else {
                this.q.g.e(holder);
            }
            h lp = (h) child.getLayoutParams();
            if (holder.g() || holder.e()) {
                if (holder.e()) {
                    holder.f();
                } else {
                    holder.h();
                }
                this.p.a(child, index, child.getLayoutParams(), false);
            } else if (child.getParent() == this.q) {
                int currentIndex = this.p.c(child);
                if (index == -1) {
                    index = this.p.a();
                }
                if (currentIndex == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.q.indexOfChild(child) + this.q.a());
                } else if (currentIndex != index) {
                    g gVar = this.q.m;
                    View g = gVar.g(currentIndex);
                    if (g == null) {
                        throw new IllegalArgumentException("Cannot move a child from non-existing index:" + currentIndex + gVar.q.toString());
                    }
                    gVar.b(currentIndex);
                    h hVar = (h) g.getLayoutParams();
                    s d = RecyclerView.d(g);
                    if (d.m()) {
                        gVar.q.g.d(d);
                    } else {
                        gVar.q.g.e(d);
                    }
                    gVar.p.a(g, index, hVar, d.m());
                }
            } else {
                this.p.a(child, index, false);
                lp.e = true;
                if (this.t != null && this.t.f()) {
                    this.t.a(child);
                }
            }
            if (lp.f) {
                holder.a.invalidate();
                lp.f = false;
            }
        }

        private void a(int index) {
            if (g(index) != null) {
                this.p.a(index);
            }
        }

        public static int e(View view) {
            return ((h) view.getLayoutParams()).c.c();
        }

        @Nullable
        public final View f(View view) {
            if (this.q == null) {
                return null;
            }
            View found = this.q.c(view);
            if (found == null) {
                return null;
            }
            if (this.p.d(found)) {
                return null;
            }
            return found;
        }

        public View c(int position) {
            int childCount = s();
            for (int i = 0; i < childCount; i++) {
                View child = g(i);
                s vh = RecyclerView.d(child);
                if (vh != null && vh.c() == position && !vh.b() && (this.q.B.f || !vh.m())) {
                    return child;
                }
            }
            return null;
        }

        private void b(int index) {
            g(index);
            this.p.d(index);
        }

        public final void a(int index, m recycler) {
            View view = g(index);
            a(index);
            recycler.a(view);
        }

        public final int s() {
            return this.p != null ? this.p.a() : 0;
        }

        public final View g(int index) {
            return this.p != null ? this.p.b(index) : null;
        }

        public final int t() {
            return this.e;
        }

        public final int u() {
            return this.f;
        }

        public final int v() {
            return this.g;
        }

        public final int w() {
            return this.h;
        }

        public final int x() {
            return this.q != null ? this.q.getPaddingLeft() : 0;
        }

        public final int y() {
            return this.q != null ? this.q.getPaddingTop() : 0;
        }

        public final int z() {
            return this.q != null ? this.q.getPaddingRight() : 0;
        }

        public final int A() {
            return this.q != null ? this.q.getPaddingBottom() : 0;
        }

        public final View B() {
            if (this.q == null) {
                return null;
            }
            View focused = this.q.getFocusedChild();
            if (focused == null || this.p.d(focused)) {
                return null;
            }
            return focused;
        }

        public void h(int dx) {
            if (this.q != null) {
                RecyclerView recyclerView = this.q;
                int a = recyclerView.f.a();
                for (int i = 0; i < a; i++) {
                    recyclerView.f.b(i).offsetLeftAndRight(dx);
                }
            }
        }

        public void i(int dy) {
            if (this.q != null) {
                RecyclerView recyclerView = this.q;
                int a = recyclerView.f.a();
                for (int i = 0; i < a; i++) {
                    recyclerView.f.b(i).offsetTopAndBottom(dy);
                }
            }
        }

        public final void a(m recycler) {
            for (int i = s() - 1; i >= 0; i--) {
                View v = g(i);
                s d = RecyclerView.d(v);
                if (!d.b()) {
                    if (!d.j() || d.m() || this.q.l.e()) {
                        b(i);
                        recycler.c(v);
                        this.q.g.e(d);
                    } else {
                        a(i);
                        recycler.a(d);
                    }
                }
            }
        }

        final void b(m recycler) {
            int scrapCount = recycler.a.size();
            for (int i = scrapCount - 1; i >= 0; i--) {
                View scrap = ((s) recycler.a.get(i)).a;
                s vh = RecyclerView.d(scrap);
                if (!vh.b()) {
                    vh.a(false);
                    if (vh.n()) {
                        this.q.removeDetachedView(scrap, false);
                    }
                    if (this.q.x != null) {
                        this.q.x.c(vh);
                    }
                    vh.a(true);
                    recycler.b(scrap);
                }
            }
            recycler.a.clear();
            if (recycler.b != null) {
                recycler.b.clear();
            }
            if (scrapCount > 0) {
                this.q.invalidate();
            }
        }

        final boolean a(View child, int widthSpec, int heightSpec, h lp) {
            return (this.c && b(child.getMeasuredWidth(), widthSpec, lp.width) && b(child.getMeasuredHeight(), heightSpec, lp.height)) ? false : true;
        }

        final boolean b(View child, int widthSpec, int heightSpec, h lp) {
            return (!child.isLayoutRequested() && this.c && b(child.getWidth(), widthSpec, lp.width) && b(child.getHeight(), heightSpec, lp.height)) ? false : true;
        }

        private static boolean b(int childSize, int spec, int dimension) {
            int specMode = MeasureSpec.getMode(spec);
            int specSize = MeasureSpec.getSize(spec);
            if (dimension > 0 && childSize != dimension) {
                return false;
            }
            switch (specMode) {
                case Integer.MIN_VALUE:
                    if (specSize >= childSize) {
                        return true;
                    }
                    return false;
                case 0:
                    return true;
                case ErrorDialogData.SUPPRESSED /*1073741824*/:
                    if (specSize == childSize) {
                        return true;
                    }
                    return false;
                default:
                    return false;
            }
        }

        public static int a(int parentSize, int parentMode, int padding, int childDimension, boolean canScroll) {
            int size = Math.max(0, parentSize - padding);
            int resultSize = 0;
            int resultMode = 0;
            if (canScroll) {
                if (childDimension >= 0) {
                    resultSize = childDimension;
                    resultMode = ErrorDialogData.SUPPRESSED;
                } else if (childDimension == -1) {
                    switch (parentMode) {
                        case Integer.MIN_VALUE:
                        case ErrorDialogData.SUPPRESSED /*1073741824*/:
                            resultSize = size;
                            resultMode = parentMode;
                            break;
                        case 0:
                            resultSize = 0;
                            resultMode = 0;
                            break;
                    }
                } else if (childDimension == -2) {
                    resultSize = 0;
                    resultMode = 0;
                }
            } else if (childDimension >= 0) {
                resultSize = childDimension;
                resultMode = ErrorDialogData.SUPPRESSED;
            } else if (childDimension == -1) {
                resultSize = size;
                resultMode = parentMode;
            } else if (childDimension == -2) {
                resultSize = size;
                if (parentMode == Integer.MIN_VALUE || parentMode == ErrorDialogData.SUPPRESSED) {
                    resultMode = Integer.MIN_VALUE;
                }
                resultMode = 0;
            }
            return MeasureSpec.makeMeasureSpec(resultSize, resultMode);
        }

        public static int g(View child) {
            Rect insets = ((h) child.getLayoutParams()).d;
            return (child.getMeasuredWidth() + insets.left) + insets.right;
        }

        public static int h(View child) {
            Rect insets = ((h) child.getLayoutParams()).d;
            return (child.getMeasuredHeight() + insets.top) + insets.bottom;
        }

        public static void a(View child, int left, int top, int right, int bottom) {
            h lp = (h) child.getLayoutParams();
            Rect insets = lp.d;
            child.layout((insets.left + left) + lp.leftMargin, (insets.top + top) + lp.topMargin, (right - insets.right) - lp.rightMargin, (bottom - insets.bottom) - lp.bottomMargin);
        }

        public final void a(View child, Rect out) {
            Rect insets = ((h) child.getLayoutParams()).d;
            out.set(-insets.left, -insets.top, child.getWidth() + insets.right, child.getHeight() + insets.bottom);
            if (this.q != null) {
                Matrix childMatrix = child.getMatrix();
                if (!(childMatrix == null || childMatrix.isIdentity())) {
                    RectF tempRectF = this.q.k;
                    tempRectF.set(out);
                    childMatrix.mapRect(tempRectF);
                    out.set((int) Math.floor((double) tempRectF.left), (int) Math.floor((double) tempRectF.top), (int) Math.ceil((double) tempRectF.right), (int) Math.ceil((double) tempRectF.bottom));
                }
            }
            out.offset(child.getLeft(), child.getTop());
        }

        public static int i(View child) {
            return child.getLeft() - ((h) child.getLayoutParams()).d.left;
        }

        public static int j(View child) {
            return child.getTop() - ((h) child.getLayoutParams()).d.top;
        }

        public static int k(View child) {
            return ((h) child.getLayoutParams()).d.right + child.getRight();
        }

        public static int l(View child) {
            return ((h) child.getLayoutParams()).d.bottom + child.getBottom();
        }

        public final void b(View child, Rect outRect) {
            if (this.q == null) {
                outRect.set(0, 0, 0, 0);
            } else {
                outRect.set(this.q.f(child));
            }
        }

        @Nullable
        public View a(View focused, int direction, m recycler, State state) {
            return null;
        }

        public void a() {
        }

        public void a(int positionStart, int itemCount) {
        }

        public void b(int positionStart, int itemCount) {
        }

        public void c(int positionStart, int itemCount) {
        }

        public void d(int from, int to) {
        }

        public int d(State state) {
            return 0;
        }

        public int b(State state) {
            return 0;
        }

        public int f(State state) {
            return 0;
        }

        public int e(State state) {
            return 0;
        }

        public int c(State state) {
            return 0;
        }

        public int g(State state) {
            return 0;
        }

        public void a(m recycler, State state, int widthSpec, int heightSpec) {
            this.q.d(widthSpec, heightSpec);
        }

        public final void h(int widthSize, int heightSize) {
            this.q.setMeasuredDimension(widthSize, heightSize);
        }

        public Parcelable d() {
            return null;
        }

        public void a(Parcelable state) {
        }

        final void C() {
            if (this.t != null) {
                this.t.d();
            }
        }

        public void j(int state) {
        }

        public final void c(m recycler) {
            for (int i = s() - 1; i >= 0; i--) {
                if (!RecyclerView.d(g(i)).b()) {
                    a(i, recycler);
                }
            }
        }

        public void a(AccessibilityEvent event) {
            boolean z = true;
            m mVar = this.q.d;
            State state = this.q.B;
            if (this.q != null && event != null) {
                if (!(this.q.canScrollVertically(1) || this.q.canScrollVertically(-1) || this.q.canScrollHorizontally(-1) || this.q.canScrollHorizontally(1))) {
                    z = false;
                }
                event.setScrollable(z);
                if (this.q.l != null) {
                    event.setItemCount(this.q.l.a());
                }
            }
        }

        final void a(View host, android.support.v4.view.accessibility.b info) {
            s vh = RecyclerView.d(host);
            if (vh != null && !vh.m() && !this.p.d(vh.a)) {
                a(this.q.d, this.q.B, host, info);
            }
        }

        public void a(m recycler, State state, View host, android.support.v4.view.accessibility.b info) {
            int rowIndexGuess;
            int columnIndexGuess;
            if (f()) {
                rowIndexGuess = e(host);
            } else {
                rowIndexGuess = 0;
            }
            if (e()) {
                columnIndexGuess = e(host);
            } else {
                columnIndexGuess = 0;
            }
            info.b(android.support.v4.view.accessibility.b.l.a(rowIndexGuess, 1, columnIndexGuess, 1, false));
        }

        public int a(m recycler, State state) {
            if (this.q == null || this.q.l == null || !f()) {
                return 1;
            }
            return this.q.l.a();
        }

        public int b(m recycler, State state) {
            if (this.q == null || this.q.l == null || !e()) {
                return 1;
            }
            return this.q.l.a();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        final boolean k(int action) {
            m mVar = this.q.d;
            State state = this.q.B;
            if (this.q == null) {
                return false;
            }
            int y;
            int i;
            switch (action) {
                case 4096:
                    if (this.q.canScrollVertically(1)) {
                        y = (this.h - y()) - A();
                    } else {
                        y = 0;
                    }
                    if (this.q.canScrollHorizontally(1)) {
                        i = y;
                        y = (this.g - x()) - z();
                        break;
                    }
                case 8192:
                    if (this.q.canScrollVertically(-1)) {
                        y = -((this.h - y()) - A());
                    } else {
                        y = 0;
                    }
                    if (this.q.canScrollHorizontally(-1)) {
                        i = y;
                        y = -((this.g - x()) - z());
                        break;
                    }
                default:
                    y = 0;
                    i = 0;
                    break;
            }
            if (i == 0 && y == 0) {
                return false;
            }
            this.q.scrollBy(y, i);
            return true;
        }

        public static b a(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            b properties = new b();
            TypedArray a = context.obtainStyledAttributes(attrs, android.support.v7.recyclerview.a.b.RecyclerView, defStyleAttr, defStyleRes);
            properties.a = a.getInt(android.support.v7.recyclerview.a.b.RecyclerView_android_orientation, 1);
            properties.b = a.getInt(android.support.v7.recyclerview.a.b.RecyclerView_spanCount, 1);
            properties.c = a.getBoolean(android.support.v7.recyclerview.a.b.RecyclerView_reverseLayout, false);
            properties.d = a.getBoolean(android.support.v7.recyclerview.a.b.RecyclerView_stackFromEnd, false);
            a.recycle();
            return properties;
        }

        final void b(RecyclerView recyclerView) {
            f(MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), ErrorDialogData.SUPPRESSED), MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), ErrorDialogData.SUPPRESSED));
        }

        boolean j() {
            return false;
        }

        public final void a(View child) {
            a(child, -1, true);
        }

        public final void c(View child) {
            a(child, -1, false);
        }

        public final void a(View child, m recycler) {
            this.p.b(child);
            recycler.a(child);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean a(RecyclerView parent, View child, Rect rect, boolean immediate, boolean focusedChildVisible) {
            int[] scrollAmount = new int[2];
            int x = x();
            int y = y();
            int z = this.g - z();
            int A = this.h - A();
            int left = (child.getLeft() + rect.left) - child.getScrollX();
            int top = (child.getTop() + rect.top) - child.getScrollY();
            int width = left + rect.width();
            int height = top + rect.height();
            int min = Math.min(0, left - x);
            int min2 = Math.min(0, top - y);
            int max = Math.max(0, width - z);
            A = Math.max(0, height - A);
            if (ViewCompat.f(this.q) == 1) {
                if (max == 0) {
                    max = Math.max(min, width - z);
                }
                min = max;
            } else {
                if (min != 0) {
                    max = min;
                } else {
                    max = Math.min(left - x, max);
                }
                min = max;
            }
            if (min2 != 0) {
                max = min2;
            } else {
                max = Math.min(top - y, A);
            }
            scrollAmount[0] = min;
            scrollAmount[1] = max;
            int dx = scrollAmount[0];
            int dy = scrollAmount[1];
            if (focusedChildVisible) {
                View focusedChild = parent.getFocusedChild();
                Object obj;
                if (focusedChild == null) {
                    obj = null;
                } else {
                    min = x();
                    min2 = y();
                    x = this.g - z();
                    y = this.h - A();
                    Rect rect2 = this.q.j;
                    RecyclerView.a(focusedChild, rect2);
                    if (rect2.left - dx >= x || rect2.right - dx <= min || rect2.top - dy >= y || rect2.bottom - dy <= min2) {
                        obj = null;
                    } else {
                        obj = 1;
                    }
                }
            }
            if (!(dx == 0 && dy == 0)) {
                if (immediate) {
                    parent.scrollBy(dx, dy);
                } else {
                    parent.a(dx, dy);
                }
                return true;
            }
            return false;
        }

        static /* synthetic */ void a(g x0, p x1) {
            if (x0.t == x1) {
                x0.t = null;
            }
        }
    }

    public static abstract class ItemAnimator {
        private a a = null;
        private ArrayList<Object> b = new ArrayList();
        private long c = 120;
        private long d = 120;
        private long e = 250;
        private long f = 250;

        @Retention(RetentionPolicy.SOURCE)
        public @interface AdapterChanges {
        }

        interface a {
            void a(s sVar);
        }

        public static class b {
            public int a;
            public int b;
            public int c;
            public int d;

            public final b a(s holder) {
                View view = holder.a;
                this.a = view.getLeft();
                this.b = view.getTop();
                this.c = view.getRight();
                this.d = view.getBottom();
                return this;
            }
        }

        public abstract void a();

        public abstract boolean a(@NonNull s sVar, @NonNull b bVar, @Nullable b bVar2);

        public abstract boolean a(@NonNull s sVar, @NonNull s sVar2, @NonNull b bVar, @NonNull b bVar2);

        public abstract boolean b();

        public abstract boolean b(@NonNull s sVar, @Nullable b bVar, @NonNull b bVar2);

        public abstract void c(s sVar);

        public abstract boolean c(@NonNull s sVar, @NonNull b bVar, @NonNull b bVar2);

        public abstract void d();

        public final long e() {
            return this.e;
        }

        public final long f() {
            return this.c;
        }

        public final long g() {
            return this.d;
        }

        public final long h() {
            return this.f;
        }

        final void a(a listener) {
            this.a = listener;
        }

        static int d(s viewHolder) {
            int flags = viewHolder.n & 14;
            if (viewHolder.j()) {
                return 4;
            }
            if ((flags & 4) == 0) {
                int oldPos = viewHolder.d;
                int pos = viewHolder.d();
                if (!(oldPos == -1 || pos == -1 || oldPos == pos)) {
                    flags |= 2048;
                }
            }
            return flags;
        }

        public final void e(s viewHolder) {
            if (this.a != null) {
                this.a.a(viewHolder);
            }
        }

        public boolean f(@NonNull s viewHolder) {
            return true;
        }

        public boolean a(@NonNull s viewHolder, @NonNull List<Object> list) {
            return f(viewHolder);
        }

        public final void i() {
            int count = this.b.size();
            for (int i = 0; i < count; i++) {
                this.b.get(i);
            }
            this.b.clear();
        }
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
            public final /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SavedState[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        Parcelable b;

        SavedState(Parcel in, ClassLoader loader) {
            super(in, loader);
            if (loader == null) {
                loader = g.class.getClassLoader();
            }
            this.b = in.readParcelable(loader);
        }

        SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelable(this.b, 0);
        }
    }

    public static class State {
        int a = 0;
        int b = 0;
        int c = 1;
        int d = 0;
        boolean e = false;
        boolean f = false;
        boolean g = false;
        boolean h = false;
        boolean i = false;
        boolean j = false;
        int k;
        long l;
        int m;
        int n;
        int o;
        private int p = -1;
        private SparseArray<Object> q;

        @Retention(RetentionPolicy.SOURCE)
        @interface LayoutState {
        }

        final void a(int accepted) {
            if ((this.c & accepted) == 0) {
                throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(accepted) + " but it is " + Integer.toBinaryString(this.c));
            }
        }

        public final int a() {
            return this.p;
        }

        public final boolean b() {
            return this.p != -1;
        }

        public final int c() {
            return this.f ? this.a - this.b : this.d;
        }

        public final String toString() {
            return "State{mTargetPosition=" + this.p + ", mData=" + this.q + ", mItemCount=" + this.d + ", mPreviousLayoutItemCount=" + this.a + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.b + ", mStructureChanged=" + this.e + ", mInPreLayout=" + this.f + ", mRunSimpleAnimations=" + this.i + ", mRunPredictiveAnimations=" + this.j + '}';
        }
    }

    static class b extends Observable<c> {
        b() {
        }

        public final boolean a() {
            return !this.mObservers.isEmpty();
        }

        public final void b() {
            for (int i = this.mObservers.size() - 1; i >= 0; i--) {
                ((c) this.mObservers.get(i)).a();
            }
        }
    }

    public static abstract class c {
        public void a() {
        }
    }

    public interface d {
        int a();
    }

    private class e implements a {
        final /* synthetic */ RecyclerView a;

        e(RecyclerView recyclerView) {
            this.a = recyclerView;
        }

        public final void a(s item) {
            item.a(true);
            if (item.h != null && item.i == null) {
                item.h = null;
            }
            item.i = null;
            if (!s.e(item) && !this.a.a(item.a) && item.n()) {
                this.a.removeDetachedView(item.a, false);
            }
        }
    }

    public static abstract class i {
        public abstract boolean a(int i, int i2);
    }

    public static class l {
        SparseArray<a> a = new SparseArray();
        private int b = 0;

        static class a {
            ArrayList<s> a = new ArrayList();
            int b = 5;
            long c = 0;
            long d = 0;

            a() {
            }
        }

        private static long a(long oldAverage, long newValue) {
            return oldAverage == 0 ? newValue : ((oldAverage / 4) * 3) + (newValue / 4);
        }

        final void a(int viewType, long createTimeNs) {
            a a = a(viewType);
            a.c = a(a.c, createTimeNs);
        }

        final void b(int viewType, long bindTimeNs) {
            a a = a(viewType);
            a.d = a(a.d, bindTimeNs);
        }

        final boolean a(int viewType, long approxCurrentNs, long deadlineNs) {
            long expectedDurationNs = a(viewType).c;
            return expectedDurationNs == 0 || approxCurrentNs + expectedDurationNs < deadlineNs;
        }

        final boolean b(int viewType, long approxCurrentNs, long deadlineNs) {
            long expectedDurationNs = a(viewType).d;
            return expectedDurationNs == 0 || approxCurrentNs + expectedDurationNs < deadlineNs;
        }

        final void a() {
            this.b++;
        }

        final void b() {
            this.b--;
        }

        final void a(a oldAdapter, a newAdapter, boolean compatibleWithPrevious) {
            if (oldAdapter != null) {
                b();
            }
            if (!compatibleWithPrevious && this.b == 0) {
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= this.a.size()) {
                        break;
                    }
                    ((a) this.a.valueAt(i2)).a.clear();
                    i = i2 + 1;
                }
            }
            if (newAdapter != null) {
                a();
            }
        }

        private a a(int viewType) {
            a scrapData = (a) this.a.get(viewType);
            if (scrapData != null) {
                return scrapData;
            }
            scrapData = new a();
            this.a.put(viewType, scrapData);
            return scrapData;
        }

        public final void a(s scrap) {
            int viewType = scrap.f;
            ArrayList<s> scrapHeap = a(viewType).a;
            if (((a) this.a.get(viewType)).b > scrapHeap.size()) {
                scrap.q();
                scrapHeap.add(scrap);
            }
        }
    }

    public final class m {
        final ArrayList<s> a = new ArrayList();
        ArrayList<s> b = null;
        final ArrayList<s> c = new ArrayList();
        int d = 2;
        l e;
        final /* synthetic */ RecyclerView f;
        private final List<s> g = Collections.unmodifiableList(this.a);
        private int h = 2;
        private q i;

        public m(RecyclerView this$0) {
            this.f = this$0;
        }

        public final void a() {
            this.a.clear();
            d();
        }

        public final void a(int viewCount) {
            this.h = viewCount;
            b();
        }

        final void b() {
            this.d = this.h + (this.f.m != null ? this.f.m.x : 0);
            for (int i = this.c.size() - 1; i >= 0 && this.c.size() > this.d; i--) {
                d(i);
            }
        }

        public final List<s> c() {
            return this.g;
        }

        public final int b(int position) {
            if (position >= 0 && position < this.f.B.c()) {
                return !this.f.B.f ? position : this.f.e.b(position);
            } else {
                throw new IndexOutOfBoundsException("invalid position " + position + ". State item count is " + this.f.B.c() + this.f.a());
            }
        }

        @Nullable
        final s a(int position, long deadlineNs) {
            if (position < 0 || position >= this.f.B.c()) {
                throw new IndexOutOfBoundsException("Invalid item position " + position + "(" + position + "). Item count:" + this.f.B.c() + this.f.a());
            }
            boolean z;
            int offsetPosition;
            h rvLayoutParams;
            boolean fromScrapOrHiddenOrCache = false;
            s holder = null;
            if (this.f.B.f) {
                holder = e(position);
                fromScrapOrHiddenOrCache = holder != null;
            }
            if (holder == null) {
                holder = f(position);
                if (holder != null) {
                    if (holder.m()) {
                        z = this.f.B.f;
                    } else if (holder.c < 0 || holder.c >= this.f.l.a()) {
                        throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + holder + this.f.a());
                    } else if (!this.f.B.f && this.f.l.b(holder.c) != holder.f) {
                        z = false;
                    } else if (!this.f.l.e() || holder.e == this.f.l.a(holder.c)) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        fromScrapOrHiddenOrCache = true;
                    } else {
                        holder.b(4);
                        if (holder.e()) {
                            this.f.removeDetachedView(holder.a, false);
                            holder.f();
                        } else if (holder.g()) {
                            holder.h();
                        }
                        a(holder);
                        holder = null;
                    }
                }
            }
            if (holder == null) {
                offsetPosition = this.f.e.b(position);
                if (offsetPosition < 0 || offsetPosition >= this.f.l.a()) {
                    throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + position + "(offset:" + offsetPosition + ").state:" + this.f.B.c() + this.f.a());
                }
                int type = this.f.l.b(offsetPosition);
                if (this.f.l.e()) {
                    holder = a(this.f.l.a(offsetPosition), type);
                    if (holder != null) {
                        holder.c = offsetPosition;
                        fromScrapOrHiddenOrCache = true;
                    }
                }
                if (holder == null && this.i != null) {
                    View view = this.i.a();
                    if (view != null) {
                        holder = this.f.b(view);
                        if (holder == null) {
                            throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder" + this.f.a());
                        } else if (holder.b()) {
                            throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view." + this.f.a());
                        }
                    }
                }
                if (holder == null) {
                    a aVar = (a) e().a.get(type);
                    if (aVar == null || aVar.a.isEmpty()) {
                        holder = null;
                    } else {
                        ArrayList arrayList = aVar.a;
                        holder = (s) arrayList.remove(arrayList.size() - 1);
                    }
                    if (holder != null) {
                        holder.q();
                        if (RecyclerView.a && (holder.a instanceof ViewGroup)) {
                            a((ViewGroup) holder.a, false);
                        }
                    }
                }
                if (holder == null) {
                    long start = RecyclerView.q();
                    if (deadlineNs != Long.MAX_VALUE && !this.e.a(type, start, deadlineNs)) {
                        return null;
                    }
                    a aVar2 = this.f.l;
                    ViewGroup viewGroup = this.f;
                    android.support.v4.os.d.a("RV CreateView");
                    holder = aVar2.a(viewGroup, type);
                    holder.f = type;
                    android.support.v4.os.d.a();
                    if (RecyclerView.K) {
                        RecyclerView innerView = RecyclerView.g(holder.a);
                        if (innerView != null) {
                            holder.b = new WeakReference(innerView);
                        }
                    }
                    this.e.a(type, RecyclerView.q() - start);
                }
            }
            if (fromScrapOrHiddenOrCache && !this.f.B.f && holder.a(8192)) {
                holder.a(0, 8192);
                if (this.f.B.i) {
                    ItemAnimator.d(holder);
                    ItemAnimator itemAnimator = this.f.x;
                    State state = this.f.B;
                    holder.p();
                    this.f.a(holder, new b().a(holder));
                }
            }
            boolean bound = false;
            if (this.f.B.f && holder.l()) {
                holder.g = position;
            } else if (!holder.l() || holder.k() || holder.j()) {
                offsetPosition = this.f.e.b(position);
                holder.m = this.f;
                int i = holder.f;
                long q = RecyclerView.q();
                if (deadlineNs == Long.MAX_VALUE || this.e.b(i, q, deadlineNs)) {
                    this.f.l.b(holder, offsetPosition);
                    this.e.b(holder.f, RecyclerView.q() - q);
                    if (this.f.k()) {
                        View view2 = holder.a;
                        if (ViewCompat.e(view2) == 0) {
                            ViewCompat.a(view2, 1);
                        }
                        if (!ViewCompat.b(view2)) {
                            holder.b(16384);
                            ViewCompat.a(view2, this.f.F.c);
                        }
                    }
                    if (this.f.B.f) {
                        holder.g = position;
                    }
                    bound = true;
                } else {
                    bound = false;
                }
            }
            LayoutParams lp = holder.a.getLayoutParams();
            if (lp == null) {
                rvLayoutParams = (h) this.f.generateDefaultLayoutParams();
                holder.a.setLayoutParams(rvLayoutParams);
            } else if (this.f.checkLayoutParams(lp)) {
                rvLayoutParams = (h) lp;
            } else {
                LayoutParams rvLayoutParams2 = (h) this.f.generateLayoutParams(lp);
                holder.a.setLayoutParams(rvLayoutParams2);
            }
            rvLayoutParams2.c = holder;
            z = fromScrapOrHiddenOrCache && bound;
            rvLayoutParams2.f = z;
            return holder;
        }

        private void a(ViewGroup viewGroup, boolean invalidateThis) {
            for (int i = viewGroup.getChildCount() - 1; i >= 0; i--) {
                View view = viewGroup.getChildAt(i);
                if (view instanceof ViewGroup) {
                    a((ViewGroup) view, true);
                }
            }
            if (!invalidateThis) {
                return;
            }
            if (viewGroup.getVisibility() == 4) {
                viewGroup.setVisibility(0);
                viewGroup.setVisibility(4);
                return;
            }
            int visibility = viewGroup.getVisibility();
            viewGroup.setVisibility(4);
            viewGroup.setVisibility(visibility);
        }

        public final void a(View view) {
            s holder = RecyclerView.d(view);
            if (holder.n()) {
                this.f.removeDetachedView(view, false);
            }
            if (holder.e()) {
                holder.f();
            } else if (holder.g()) {
                holder.h();
            }
            a(holder);
        }

        final void d() {
            for (int i = this.c.size() - 1; i >= 0; i--) {
                d(i);
            }
            this.c.clear();
            if (RecyclerView.K) {
                this.f.A.a();
            }
        }

        final void d(int cachedViewIndex) {
            a((s) this.c.get(cachedViewIndex), true);
            this.c.remove(cachedViewIndex);
        }

        final void a(s holder) {
            boolean z = false;
            if (holder.e() || holder.a.getParent() != null) {
                StringBuilder append = new StringBuilder("Scrapped or attached views may not be recycled. isScrap:").append(holder.e()).append(" isAttached:");
                if (holder.a.getParent() != null) {
                    z = true;
                }
                throw new IllegalArgumentException(append.append(z).append(this.f.a()).toString());
            } else if (holder.n()) {
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + holder + this.f.a());
            } else if (holder.b()) {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + this.f.a());
            } else {
                boolean transientStatePreventsRecycling = s.a(holder);
                if (this.f.l != null && transientStatePreventsRecycling) {
                    a aVar = this.f.l;
                }
                boolean cached = false;
                boolean recycled = false;
                if (holder.r()) {
                    if (this.d > 0 && !holder.a(526)) {
                        int cachedViewSize = this.c.size();
                        if (cachedViewSize >= this.d && cachedViewSize > 0) {
                            d(0);
                            cachedViewSize--;
                        }
                        int targetCacheIndex = cachedViewSize;
                        if (RecyclerView.K && cachedViewSize > 0 && !this.f.A.a(holder.c)) {
                            int cacheIndex = cachedViewSize - 1;
                            while (cacheIndex >= 0) {
                                if (!this.f.A.a(((s) this.c.get(cacheIndex)).c)) {
                                    break;
                                }
                                cacheIndex--;
                            }
                            targetCacheIndex = cacheIndex + 1;
                        }
                        this.c.add(targetCacheIndex, holder);
                        cached = true;
                    }
                    if (!cached) {
                        a(holder, true);
                        recycled = true;
                    }
                }
                this.f.g.f(holder);
                if (!cached && !recycled && transientStatePreventsRecycling) {
                    holder.m = null;
                }
            }
        }

        final void a(s holder, boolean dispatchRecycled) {
            RecyclerView.a(holder);
            if (holder.a(16384)) {
                holder.a(0, 16384);
                ViewCompat.a(holder.a, null);
            }
            if (dispatchRecycled) {
                if (this.f.n != null) {
                    n nVar = this.f.n;
                }
                if (this.f.l != null) {
                    this.f.l.a(holder);
                }
                if (this.f.B != null) {
                    this.f.g.f(holder);
                }
            }
            holder.m = null;
            e().a(holder);
        }

        final void b(View view) {
            s holder = RecyclerView.d(view);
            holder.q = null;
            holder.r = false;
            holder.h();
            a(holder);
        }

        final void c(View view) {
            s holder = RecyclerView.d(view);
            if (!holder.a(12) && holder.s()) {
                boolean z;
                RecyclerView recyclerView = this.f;
                if (recyclerView.x == null || recyclerView.x.a(holder, holder.p())) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    if (this.b == null) {
                        this.b = new ArrayList();
                    }
                    holder.a(this, true);
                    this.b.add(holder);
                    return;
                }
            }
            if (!holder.j() || holder.m() || this.f.l.e()) {
                holder.a(this, false);
                this.a.add(holder);
                return;
            }
            throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + this.f.a());
        }

        final void b(s holder) {
            if (holder.r) {
                this.b.remove(holder);
            } else {
                this.a.remove(holder);
            }
            holder.q = null;
            holder.r = false;
            holder.h();
        }

        private s e(int position) {
            if (this.b != null) {
                int changedScrapSize = this.b.size();
                if (changedScrapSize != 0) {
                    s holder;
                    int i = 0;
                    while (i < changedScrapSize) {
                        holder = (s) this.b.get(i);
                        if (holder.g() || holder.c() != position) {
                            i++;
                        } else {
                            holder.b(32);
                            return holder;
                        }
                    }
                    if (this.f.l.e()) {
                        int offsetPosition = this.f.e.a(position, 0);
                        if (offsetPosition > 0 && offsetPosition < this.f.l.a()) {
                            long id = this.f.l.a(offsetPosition);
                            i = 0;
                            while (i < changedScrapSize) {
                                holder = (s) this.b.get(i);
                                if (holder.g() || holder.e != id) {
                                    i++;
                                } else {
                                    holder.b(32);
                                    return holder;
                                }
                            }
                        }
                    }
                    return null;
                }
            }
            return null;
        }

        private s f(int position) {
            s holder;
            View view;
            int scrapCount = this.a.size();
            int i = 0;
            while (i < scrapCount) {
                holder = (s) this.a.get(i);
                if (holder.g() || holder.c() != position || holder.j() || (!this.f.B.f && holder.m())) {
                    i++;
                } else {
                    holder.b(32);
                    return holder;
                }
            }
            p pVar = this.f.f;
            int size = pVar.c.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view2 = (View) pVar.c.get(i2);
                s b = pVar.a.b(view2);
                if (b.c() == position && !b.j() && !b.m()) {
                    view = view2;
                    break;
                }
            }
            view = null;
            if (view != null) {
                s vh = RecyclerView.d(view);
                this.f.f.f(view);
                int layoutIndex = this.f.f.c(view);
                if (layoutIndex == -1) {
                    throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + vh + this.f.a());
                }
                this.f.f.d(layoutIndex);
                c(view);
                vh.b(8224);
                return vh;
            }
            int cacheSize = this.c.size();
            i = 0;
            while (i < cacheSize) {
                holder = (s) this.c.get(i);
                if (holder.j() || holder.c() != position) {
                    i++;
                } else {
                    this.c.remove(i);
                    return holder;
                }
            }
            return null;
        }

        private s a(long id, int type) {
            int i;
            s holder;
            for (i = this.a.size() - 1; i >= 0; i--) {
                holder = (s) this.a.get(i);
                if (holder.e == id && !holder.g()) {
                    if (type == holder.f) {
                        holder.b(32);
                        if (!holder.m() || this.f.B.f) {
                            return holder;
                        }
                        holder.a(2, 14);
                        return holder;
                    }
                    this.a.remove(i);
                    this.f.removeDetachedView(holder.a, false);
                    b(holder.a);
                }
            }
            i = this.c.size() - 1;
            while (i >= 0) {
                holder = (s) this.c.get(i);
                if (holder.e != id) {
                    i--;
                } else if (type == holder.f) {
                    this.c.remove(i);
                    return holder;
                } else {
                    d(i);
                    return null;
                }
            }
            return null;
        }

        final void a(q extension) {
            this.i = extension;
        }

        final l e() {
            if (this.e == null) {
                this.e = new l();
            }
            return this.e;
        }

        public final View c(int position) {
            return a(position, Long.MAX_VALUE).a;
        }
    }

    public interface n {
    }

    private class o extends c {
        final /* synthetic */ RecyclerView a;

        o(RecyclerView recyclerView) {
            this.a = recyclerView;
        }

        public final void a() {
            this.a.a(null);
            this.a.B.e = true;
            this.a.n();
            if (!this.a.e.d()) {
                this.a.requestLayout();
            }
        }
    }

    public static abstract class p {
        private int a = -1;
        private RecyclerView b;
        private g c;
        private boolean d;
        private boolean e;
        private View f;
        private final a g = new a();

        public interface b {
            PointF d(int i);
        }

        public static class a {
            private int a;
            private int b;
            private int c;
            private int d;
            private Interpolator e;
            private boolean f;
            private int g;

            public a() {
                this((byte) 0);
            }

            private a(byte b) {
                this.d = -1;
                this.f = false;
                this.g = 0;
                this.a = 0;
                this.b = 0;
                this.c = Integer.MIN_VALUE;
                this.e = null;
            }

            public final void a(int targetPosition) {
                this.d = targetPosition;
            }

            final boolean a() {
                return this.d >= 0;
            }

            final void a(RecyclerView recyclerView) {
                if (this.d >= 0) {
                    int position = this.d;
                    this.d = -1;
                    recyclerView.c(position);
                    this.f = false;
                } else if (!this.f) {
                    this.g = 0;
                } else if (this.e != null && this.c <= 0) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                } else if (this.c <= 0) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                } else {
                    if (this.e != null) {
                        recyclerView.y.a(this.a, this.b, this.c, this.e);
                    } else if (this.c == Integer.MIN_VALUE) {
                        recyclerView.y.b(this.a, this.b);
                    } else {
                        recyclerView.y.a(this.a, this.b, this.c);
                    }
                    this.g++;
                    this.f = false;
                }
            }

            public final void a(int dx, int dy, int duration, Interpolator interpolator) {
                this.a = dx;
                this.b = dy;
                this.c = duration;
                this.e = interpolator;
                this.f = true;
            }
        }

        protected abstract void a();

        protected abstract void a(int i, int i2, a aVar);

        protected abstract void a(View view, a aVar);

        final void a(RecyclerView recyclerView, g layoutManager) {
            this.b = recyclerView;
            this.c = layoutManager;
            if (this.a == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            this.b.B.p = this.a;
            this.e = true;
            this.d = true;
            this.f = this.b.m.c(this.a);
            this.b.y.a();
        }

        public final void d(int targetPosition) {
            this.a = targetPosition;
        }

        @Nullable
        public final g c() {
            return this.c;
        }

        protected final void d() {
            if (this.e) {
                a();
                this.b.B.p = -1;
                this.f = null;
                this.a = -1;
                this.d = false;
                this.e = false;
                g.a(this.c, this);
                this.c = null;
                this.b = null;
            }
        }

        public final boolean e() {
            return this.d;
        }

        public final boolean f() {
            return this.e;
        }

        public final int g() {
            return this.a;
        }

        public final int h() {
            return this.b.m.s();
        }

        protected final void a(View child) {
            if (RecyclerView.e(child) == this.a) {
                this.f = child;
            }
        }

        static /* synthetic */ void a(p x0, int x1, int x2) {
            RecyclerView recyclerView = x0.b;
            if (!x0.e || x0.a == -1 || recyclerView == null) {
                x0.d();
            }
            x0.d = false;
            if (x0.f != null) {
                if (RecyclerView.e(x0.f) == x0.a) {
                    View view = x0.f;
                    State state = recyclerView.B;
                    x0.a(view, x0.g);
                    x0.g.a(recyclerView);
                    x0.d();
                } else {
                    x0.f = null;
                }
            }
            if (x0.e) {
                State state2 = recyclerView.B;
                x0.a(x1, x2, x0.g);
                boolean a = x0.g.a();
                x0.g.a(recyclerView);
                if (!a) {
                    return;
                }
                if (x0.e) {
                    x0.d = true;
                    recyclerView.y.a();
                    return;
                }
                x0.d();
            }
        }
    }

    public static abstract class q {
        public abstract View a();
    }

    class r implements Runnable {
        Interpolator a = RecyclerView.H;
        final /* synthetic */ RecyclerView b;
        private int c;
        private int d;
        private OverScroller e;
        private boolean f = false;
        private boolean g = false;

        r(RecyclerView this$0) {
            this.b = this$0;
            this.e = new OverScroller(this$0.getContext(), RecyclerView.H);
        }

        public final void run() {
            if (this.b.m == null) {
                b();
                return;
            }
            this.g = false;
            this.f = true;
            this.b.f();
            OverScroller scroller = this.e;
            p smoothScroller = this.b.m.t;
            if (scroller.computeScrollOffset()) {
                int hresult;
                int[] scrollConsumed = this.b.aC;
                int x = scroller.getCurrX();
                int y = scroller.getCurrY();
                int dx = x - this.c;
                int dy = y - this.d;
                int vresult = 0;
                this.c = x;
                this.d = y;
                int overscrollX = 0;
                int overscrollY = 0;
                if (this.b.a(dx, dy, scrollConsumed, null, 1)) {
                    dx -= scrollConsumed[0];
                    dy -= scrollConsumed[1];
                }
                if (this.b.l != null) {
                    this.b.g();
                    this.b.i();
                    android.support.v4.os.d.a("RV Scroll");
                    this.b.a(this.b.B);
                    if (dx != 0) {
                        hresult = this.b.m.a(dx, this.b.d, this.b.B);
                        overscrollX = dx - hresult;
                    } else {
                        hresult = 0;
                    }
                    if (dy != 0) {
                        vresult = this.b.m.b(dy, this.b.d, this.b.B);
                        overscrollY = dy - vresult;
                    }
                    android.support.v4.os.d.a();
                    this.b.p();
                    this.b.j();
                    this.b.a(false);
                    if (!(smoothScroller == null || smoothScroller.e() || !smoothScroller.f())) {
                        int adapterSize = this.b.B.c();
                        if (adapterSize == 0) {
                            smoothScroller.d();
                        } else {
                            if (smoothScroller.g() >= adapterSize) {
                                smoothScroller.d(adapterSize - 1);
                            }
                            p.a(smoothScroller, dx - overscrollX, dy - overscrollY);
                        }
                    }
                } else {
                    hresult = 0;
                }
                if (!this.b.o.isEmpty()) {
                    this.b.invalidate();
                }
                if (this.b.getOverScrollMode() != 2) {
                    this.b.b(dx, dy);
                }
                if (!(this.b.a(hresult, vresult, overscrollX, overscrollY, null, 1) || (overscrollX == 0 && overscrollY == 0))) {
                    int vel = (int) scroller.getCurrVelocity();
                    int velX = 0;
                    if (overscrollX != x) {
                        velX = overscrollX < 0 ? -vel : overscrollX > 0 ? vel : 0;
                    }
                    int velY = 0;
                    if (overscrollY != y) {
                        velY = overscrollY < 0 ? -vel : overscrollY > 0 ? vel : 0;
                    }
                    if (this.b.getOverScrollMode() != 2) {
                        this.b.c(velX, velY);
                    }
                    if ((velX != 0 || overscrollX == x || scroller.getFinalX() == 0) && (velY != 0 || overscrollY == y || scroller.getFinalY() == 0)) {
                        scroller.abortAnimation();
                    }
                }
                if (!(hresult == 0 && vresult == 0)) {
                    this.b.e(hresult, vresult);
                }
                if (!this.b.awakenScrollBars()) {
                    this.b.invalidate();
                }
                boolean fullyConsumedVertical = dy != 0 && this.b.m.f() && vresult == dy;
                boolean fullyConsumedHorizontal = dx != 0 && this.b.m.e() && hresult == dx;
                boolean fullyConsumedAny = (dx == 0 && dy == 0) || fullyConsumedHorizontal || fullyConsumedVertical;
                if (scroller.isFinished() || !(fullyConsumedAny || this.b.r())) {
                    this.b.a(0);
                    if (RecyclerView.K) {
                        this.b.A.a();
                    }
                    this.b.f(1);
                } else {
                    a();
                    if (this.b.z != null) {
                        this.b.z.a(this.b, dx, dy);
                    }
                }
            }
            if (smoothScroller != null) {
                if (smoothScroller.e()) {
                    p.a(smoothScroller, 0, 0);
                }
                if (!this.g) {
                    smoothScroller.d();
                }
            }
            this.f = false;
            if (this.g) {
                a();
            }
        }

        final void a() {
            if (this.f) {
                this.g = true;
                return;
            }
            this.b.removeCallbacks(this);
            ViewCompat.a(this.b, (Runnable) this);
        }

        public final void a(int velocityX, int velocityY) {
            this.b.a(2);
            this.d = 0;
            this.c = 0;
            this.e.fling(0, 0, velocityX, velocityY, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            a();
        }

        public final void b(int dx, int dy) {
            a(dx, dy, d(dx, dy));
        }

        private int d(int dx, int dy) {
            int duration;
            int absDx = Math.abs(dx);
            int absDy = Math.abs(dy);
            boolean horizontal = absDx > absDy;
            int velocity = (int) Math.sqrt(0.0d);
            int delta = (int) Math.sqrt((double) ((dx * dx) + (dy * dy)));
            int containerSize = horizontal ? this.b.getWidth() : this.b.getHeight();
            int halfContainerSize = containerSize / 2;
            float distance = ((float) halfContainerSize) + (((float) halfContainerSize) * ((float) Math.sin((double) ((Math.min(1.0f, (1.0f * ((float) delta)) / ((float) containerSize)) - 0.5f) * 0.47123894f))));
            if (velocity > 0) {
                duration = Math.round(1000.0f * Math.abs(distance / ((float) velocity))) * 4;
            } else {
                if (!horizontal) {
                    absDx = absDy;
                }
                duration = (int) (((((float) absDx) / ((float) containerSize)) + 1.0f) * 300.0f);
            }
            return Math.min(duration, 2000);
        }

        public final void a(int dx, int dy, int duration) {
            a(dx, dy, duration, RecyclerView.H);
        }

        public final void c(int dx, int dy) {
            a(dx, dy, d(dx, dy), RecyclerView.H);
        }

        public final void a(int dx, int dy, int duration, Interpolator interpolator) {
            if (this.a != interpolator) {
                this.a = interpolator;
                this.e = new OverScroller(this.b.getContext(), interpolator);
            }
            this.b.a(2);
            this.d = 0;
            this.c = 0;
            this.e.startScroll(0, 0, dx, dy, duration);
            if (VERSION.SDK_INT < 23) {
                this.e.computeScrollOffset();
            }
            a();
        }

        public final void b() {
            this.b.removeCallbacks(this);
            this.e.abortAnimation();
        }
    }

    static {
        boolean z = VERSION.SDK_INT == 18 || VERSION.SDK_INT == 19 || VERSION.SDK_INT == 20;
        a = z;
        if (VERSION.SDK_INT >= 23) {
            z = true;
        } else {
            z = false;
        }
        b = z;
        if (VERSION.SDK_INT >= 16) {
            z = true;
        } else {
            z = false;
        }
        c = z;
        if (VERSION.SDK_INT >= 21) {
            z = true;
        } else {
            z = false;
        }
        K = z;
        if (VERSION.SDK_INT <= 15) {
            z = true;
        } else {
            z = false;
        }
        L = z;
        if (VERSION.SDK_INT <= 15) {
            z = true;
        } else {
            z = false;
        }
        M = z;
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    public RecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        TypedArray a;
        super(context, attrs, defStyle);
        this.O = new o(this);
        this.d = new m(this);
        this.g = new aw();
        this.i = new Runnable(this) {
            final /* synthetic */ RecyclerView a;

            {
                this.a = this$0;
            }

            public final void run() {
                if (this.a.s && !this.a.isLayoutRequested()) {
                    if (!this.a.p) {
                        this.a.requestLayout();
                    } else if (this.a.u) {
                        this.a.t = true;
                    } else {
                        this.a.f();
                    }
                }
            }
        };
        this.j = new Rect();
        this.Q = new Rect();
        this.k = new RectF();
        this.o = new ArrayList();
        this.R = new ArrayList();
        this.T = 0;
        this.w = false;
        this.ab = 0;
        this.ac = 0;
        this.x = new s();
        this.ah = 0;
        this.ai = -1;
        this.as = Float.MIN_VALUE;
        this.at = Float.MIN_VALUE;
        this.au = true;
        this.y = new r(this);
        this.A = K ? new a() : null;
        this.B = new State();
        this.C = false;
        this.D = false;
        this.ax = new e(this);
        this.E = false;
        this.az = new int[2];
        this.aB = new int[2];
        this.aC = new int[2];
        this.aD = new int[2];
        this.G = new ArrayList();
        this.aE = new Runnable(this) {
            final /* synthetic */ RecyclerView a;

            {
                this.a = this$0;
            }

            public final void run() {
                if (this.a.x != null) {
                    this.a.x.a();
                }
                this.a.E = false;
            }
        };
        this.aF = new b(this) {
            final /* synthetic */ RecyclerView a;

            {
                this.a = this$0;
            }

            public final void a(s viewHolder, @NonNull b info, @Nullable b postInfo) {
                this.a.d.b(viewHolder);
                this.a.b(viewHolder, info, postInfo);
            }

            public final void b(s viewHolder, b preInfo, b info) {
                this.a.a(viewHolder, preInfo, info);
            }

            public final void c(s viewHolder, @NonNull b preInfo, @NonNull b postInfo) {
                viewHolder.a(false);
                if (this.a.w) {
                    if (this.a.x.a(viewHolder, viewHolder, preInfo, postInfo)) {
                        this.a.l();
                    }
                } else if (this.a.x.c(viewHolder, preInfo, postInfo)) {
                    this.a.l();
                }
            }

            public final void a(s viewHolder) {
                this.a.m.a(viewHolder.a, this.a.d);
            }
        };
        if (attrs != null) {
            a = context.obtainStyledAttributes(attrs, J, defStyle, 0);
            this.h = a.getBoolean(0, true);
            a.recycle();
        } else {
            this.h = true;
        }
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration vc = ViewConfiguration.get(context);
        this.ao = vc.getScaledTouchSlop();
        this.as = android.support.v4.view.p.a(vc, context);
        this.at = android.support.v4.view.p.b(vc, context);
        this.aq = vc.getScaledMinimumFlingVelocity();
        this.ar = vc.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.x.a(this.ax);
        this.e = new e(new a(this) {
            final /* synthetic */ RecyclerView a;

            {
                this.a = this$0;
            }

            public final s a(int position) {
                s vh;
                RecyclerView recyclerView = this.a;
                int b = recyclerView.f.b();
                int i = 0;
                s sVar = null;
                while (i < b) {
                    s d = RecyclerView.d(recyclerView.f.c(i));
                    if (d != null && !d.m() && d.c == position) {
                        if (!recyclerView.f.d(d.a)) {
                            vh = d;
                            break;
                        }
                    } else {
                        d = sVar;
                    }
                    i++;
                    sVar = d;
                }
                vh = sVar;
                if (vh == null) {
                    return null;
                }
                if (this.a.f.d(vh.a)) {
                    return null;
                }
                return vh;
            }

            public final void a(int start, int count) {
                this.a.a(start, count, true);
                this.a.C = true;
                State state = this.a.B;
                state.b += count;
            }

            public final void b(int positionStart, int itemCount) {
                this.a.a(positionStart, itemCount, false);
                this.a.C = true;
            }

            public final void a(int positionStart, int itemCount, Object payload) {
                int i;
                RecyclerView recyclerView = this.a;
                int b = recyclerView.f.b();
                int i2 = positionStart + itemCount;
                for (i = 0; i < b; i++) {
                    View c = recyclerView.f.c(i);
                    s d = RecyclerView.d(c);
                    if (d != null && !d.b() && d.c >= positionStart && d.c < i2) {
                        d.b(2);
                        d.a(payload);
                        ((h) c.getLayoutParams()).e = true;
                    }
                }
                m mVar = recyclerView.d;
                b = positionStart + itemCount;
                for (i = mVar.c.size() - 1; i >= 0; i--) {
                    s sVar = (s) mVar.c.get(i);
                    if (sVar != null) {
                        i2 = sVar.c;
                        if (i2 >= positionStart && i2 < b) {
                            sVar.b(2);
                            mVar.d(i);
                        }
                    }
                }
                this.a.D = true;
            }

            public final void a(b op) {
                c(op);
            }

            private void c(b op) {
                switch (op.a) {
                    case 1:
                        this.a.m.a(op.b, op.d);
                        return;
                    case 2:
                        this.a.m.b(op.b, op.d);
                        return;
                    case 4:
                        this.a.m.c(op.b, op.d);
                        return;
                    case 8:
                        this.a.m.d(op.b, op.d);
                        return;
                    default:
                        return;
                }
            }

            public final void b(b op) {
                c(op);
            }

            public final void c(int positionStart, int itemCount) {
                int i;
                RecyclerView recyclerView = this.a;
                int b = recyclerView.f.b();
                for (i = 0; i < b; i++) {
                    s d = RecyclerView.d(recyclerView.f.c(i));
                    if (!(d == null || d.b() || d.c < positionStart)) {
                        d.a(itemCount, false);
                        recyclerView.B.e = true;
                    }
                }
                m mVar = recyclerView.d;
                int size = mVar.c.size();
                for (i = 0; i < size; i++) {
                    s sVar = (s) mVar.c.get(i);
                    if (sVar != null && sVar.c >= positionStart) {
                        sVar.a(itemCount, true);
                    }
                }
                recyclerView.requestLayout();
                this.a.C = true;
            }

            public final void d(int from, int to) {
                int i;
                int i2;
                int i3;
                int i4;
                int i5 = -1;
                RecyclerView recyclerView = this.a;
                int b = recyclerView.f.b();
                if (from < to) {
                    i = -1;
                    i2 = to;
                    i3 = from;
                } else {
                    i = 1;
                    i2 = from;
                    i3 = to;
                }
                for (i4 = 0; i4 < b; i4++) {
                    s d = RecyclerView.d(recyclerView.f.c(i4));
                    if (d != null && d.c >= i3 && d.c <= i2) {
                        if (d.c == from) {
                            d.a(to - from, false);
                        } else {
                            d.a(i, false);
                        }
                        recyclerView.B.e = true;
                    }
                }
                m mVar = recyclerView.d;
                if (from < to) {
                    i2 = to;
                    i3 = from;
                } else {
                    i5 = 1;
                    i2 = from;
                    i3 = to;
                }
                int size = mVar.c.size();
                for (i4 = 0; i4 < size; i4++) {
                    s sVar = (s) mVar.c.get(i4);
                    if (sVar != null && sVar.c >= r4 && sVar.c <= r3) {
                        if (sVar.c == from) {
                            sVar.a(to - from, false);
                        } else {
                            sVar.a(i5, false);
                        }
                    }
                }
                recyclerView.requestLayout();
                this.a.C = true;
            }
        });
        this.f = new p(new b(this) {
            final /* synthetic */ RecyclerView a;

            {
                this.a = this$0;
            }

            public final int a() {
                return this.a.getChildCount();
            }

            public final void a(View child, int index) {
                this.a.addView(child, index);
                this.a.i(child);
            }

            public final int a(View view) {
                return this.a.indexOfChild(view);
            }

            public final void a(int index) {
                View child = this.a.getChildAt(index);
                if (child != null) {
                    this.a.h(child);
                    child.clearAnimation();
                }
                this.a.removeViewAt(index);
            }

            public final View b(int offset) {
                return this.a.getChildAt(offset);
            }

            public final s b(View view) {
                return RecyclerView.d(view);
            }

            public final void a(View child, int index, LayoutParams layoutParams) {
                s vh = RecyclerView.d(child);
                if (vh != null) {
                    if (vh.n() || vh.b()) {
                        vh.i();
                    } else {
                        throw new IllegalArgumentException("Called attach on a child which is not detached: " + vh + this.a.a());
                    }
                }
                this.a.attachViewToParent(child, index, layoutParams);
            }

            public final void c(int offset) {
                View view = b(offset);
                if (view != null) {
                    s vh = RecyclerView.d(view);
                    if (vh != null) {
                        if (!vh.n() || vh.b()) {
                            vh.b((int) Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE);
                        } else {
                            throw new IllegalArgumentException("called detach on an already detached child " + vh + this.a.a());
                        }
                    }
                }
                this.a.detachViewFromParent(offset);
            }

            public final void c(View child) {
                s vh = RecyclerView.d(child);
                if (vh != null) {
                    s.a(vh, this.a);
                }
            }

            public final void d(View child) {
                s vh = RecyclerView.d(child);
                if (vh != null) {
                    s.b(vh, this.a);
                }
            }

            public final void b() {
                int count = this.a.getChildCount();
                for (int i = 0; i < count; i++) {
                    View child = b(i);
                    this.a.h(child);
                    child.clearAnimation();
                }
                this.a.removeAllViews();
            }
        });
        if (ViewCompat.e(this) == 0) {
            ViewCompat.a((View) this, 1);
        }
        this.W = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new af(this));
        boolean nestedScrollingEnabled = true;
        if (attrs != null) {
            a = context.obtainStyledAttributes(attrs, android.support.v7.recyclerview.a.b.RecyclerView, defStyle, 0);
            String layoutManagerName = a.getString(android.support.v7.recyclerview.a.b.RecyclerView_layoutManager);
            if (a.getInt(android.support.v7.recyclerview.a.b.RecyclerView_android_descendantFocusability, -1) == -1) {
                setDescendantFocusability(262144);
            }
            this.r = a.getBoolean(android.support.v7.recyclerview.a.b.RecyclerView_fastScrollEnabled, false);
            if (this.r) {
                StateListDrawable verticalThumbDrawable = (StateListDrawable) a.getDrawable(android.support.v7.recyclerview.a.b.RecyclerView_fastScrollVerticalThumbDrawable);
                Drawable verticalTrackDrawable = a.getDrawable(android.support.v7.recyclerview.a.b.RecyclerView_fastScrollVerticalTrackDrawable);
                StateListDrawable horizontalThumbDrawable = (StateListDrawable) a.getDrawable(android.support.v7.recyclerview.a.b.RecyclerView_fastScrollHorizontalThumbDrawable);
                Drawable horizontalTrackDrawable = a.getDrawable(android.support.v7.recyclerview.a.b.RecyclerView_fastScrollHorizontalTrackDrawable);
                if (verticalThumbDrawable == null || verticalTrackDrawable == null || horizontalThumbDrawable == null || horizontalTrackDrawable == null) {
                    throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + a());
                }
                Resources resources = getContext().getResources();
                FastScroller fastScroller = new FastScroller(this, verticalThumbDrawable, verticalTrackDrawable, horizontalThumbDrawable, horizontalTrackDrawable, resources.getDimensionPixelSize(android.support.v7.recyclerview.a.a.fastscroll_default_thickness), resources.getDimensionPixelSize(android.support.v7.recyclerview.a.a.fastscroll_minimum_range), resources.getDimensionPixelOffset(android.support.v7.recyclerview.a.a.fastscroll_margin));
            }
            a.recycle();
            if (layoutManagerName != null) {
                String trim = layoutManagerName.trim();
                if (!trim.isEmpty()) {
                    String str;
                    if (trim.charAt(0) == '.') {
                        str = context.getPackageName() + trim;
                    } else if (trim.contains(".")) {
                        str = trim;
                    } else {
                        str = RecyclerView.class.getPackage().getName() + '.' + trim;
                    }
                    try {
                        ClassLoader classLoader;
                        Object[] objArr;
                        Constructor constructor;
                        if (isInEditMode()) {
                            classLoader = getClass().getClassLoader();
                        } else {
                            classLoader = context.getClassLoader();
                        }
                        Class asSubclass = classLoader.loadClass(str).asSubclass(g.class);
                        try {
                            objArr = new Object[]{context, attrs, Integer.valueOf(defStyle), Integer.valueOf(0)};
                            constructor = asSubclass.getConstructor(N);
                        } catch (Throwable e) {
                            constructor = asSubclass.getConstructor(new Class[0]);
                            objArr = null;
                        }
                        constructor.setAccessible(true);
                        setLayoutManager((g) constructor.newInstance(objArr));
                    } catch (Throwable e2) {
                        e2.initCause(e);
                        throw new IllegalStateException(attrs.getPositionDescription() + ": Error creating LayoutManager " + str, e2);
                    } catch (Throwable e3) {
                        throw new IllegalStateException(attrs.getPositionDescription() + ": Unable to find LayoutManager " + str, e3);
                    } catch (Throwable e32) {
                        throw new IllegalStateException(attrs.getPositionDescription() + ": Could not instantiate the LayoutManager: " + str, e32);
                    } catch (Throwable e322) {
                        throw new IllegalStateException(attrs.getPositionDescription() + ": Could not instantiate the LayoutManager: " + str, e322);
                    } catch (Throwable e3222) {
                        throw new IllegalStateException(attrs.getPositionDescription() + ": Cannot access non-public constructor " + str, e3222);
                    } catch (Throwable e32222) {
                        throw new IllegalStateException(attrs.getPositionDescription() + ": Class is not a LayoutManager " + str, e32222);
                    }
                }
            }
            if (VERSION.SDK_INT >= 21) {
                a = context.obtainStyledAttributes(attrs, I, defStyle, 0);
                nestedScrollingEnabled = a.getBoolean(0, true);
                a.recycle();
            }
        } else {
            setDescendantFocusability(262144);
        }
        setNestedScrollingEnabled(nestedScrollingEnabled);
    }

    final String a() {
        return " " + super.toString() + ", adapter:" + this.l + ", layout:" + this.m + ", context:" + getContext();
    }

    public void setAccessibilityDelegateCompat(af accessibilityDelegate) {
        this.F = accessibilityDelegate;
        ViewCompat.a((View) this, this.F);
    }

    public void setHasFixedSize(boolean hasFixedSize) {
        this.q = hasFixedSize;
    }

    public void setClipToPadding(boolean clipToPadding) {
        if (clipToPadding != this.h) {
            z();
        }
        this.h = clipToPadding;
        super.setClipToPadding(clipToPadding);
        if (this.s) {
            requestLayout();
        }
    }

    public boolean getClipToPadding() {
        return this.h;
    }

    public void setScrollingTouchSlop(int slopConstant) {
        ViewConfiguration vc = ViewConfiguration.get(getContext());
        switch (slopConstant) {
            case 0:
                break;
            case 1:
                this.ao = vc.getScaledPagingTouchSlop();
                return;
            default:
                new StringBuilder("setScrollingTouchSlop(): bad argument constant ").append(slopConstant).append("; using default value");
                break;
        }
        this.ao = vc.getScaledTouchSlop();
    }

    public final void a(a adapter) {
        setLayoutFrozen(false);
        a(adapter, true);
        requestLayout();
    }

    public void setAdapter(a adapter) {
        setLayoutFrozen(false);
        a(adapter, false);
        requestLayout();
    }

    final void b() {
        if (this.x != null) {
            this.x.d();
        }
        if (this.m != null) {
            this.m.c(this.d);
            this.m.b(this.d);
        }
        this.d.a();
    }

    private void a(a adapter, boolean compatibleWithPrevious) {
        if (this.l != null) {
            this.l.b(this.O);
        }
        b();
        this.e.a();
        a oldAdapter = this.l;
        this.l = adapter;
        if (adapter != null) {
            adapter.a(this.O);
        }
        m mVar = this.d;
        a aVar = this.l;
        mVar.a();
        mVar.e().a(oldAdapter, aVar, compatibleWithPrevious);
        this.B.e = true;
        n();
    }

    public final a c() {
        return this.l;
    }

    public void setRecyclerListener(n listener) {
        this.n = listener;
    }

    public int getBaseline() {
        if (this.m != null) {
            return -1;
        }
        return super.getBaseline();
    }

    public void setLayoutManager(g layout) {
        if (layout != this.m) {
            t();
            if (this.m != null) {
                if (this.x != null) {
                    this.x.d();
                }
                this.m.c(this.d);
                this.m.b(this.d);
                this.d.a();
                if (this.p) {
                    this.m.b(this, this.d);
                }
                this.m.a(null);
                this.m = null;
            } else {
                this.d.a();
            }
            p pVar = this.f;
            a aVar = pVar.b;
            while (true) {
                aVar.a = 0;
                if (aVar.b == null) {
                    break;
                }
                aVar = aVar.b;
            }
            for (int size = pVar.c.size() - 1; size >= 0; size--) {
                pVar.a.d((View) pVar.c.get(size));
                pVar.c.remove(size);
            }
            pVar.a.b();
            this.m = layout;
            if (layout != null) {
                if (layout.q != null) {
                    throw new IllegalArgumentException("LayoutManager " + layout + " is already attached to a RecyclerView:" + layout.q.a());
                }
                this.m.a(this);
                if (this.p) {
                    this.m.v = true;
                }
            }
            this.d.b();
            requestLayout();
        }
    }

    public void setOnFlingListener(@Nullable i onFlingListener) {
        this.ap = onFlingListener;
    }

    @Nullable
    public final i d() {
        return this.ap;
    }

    protected Parcelable onSaveInstanceState() {
        SavedState state = new SavedState(super.onSaveInstanceState());
        if (this.P != null) {
            state.b = this.P.b;
        } else if (this.m != null) {
            state.b = this.m.d();
        } else {
            state.b = null;
        }
        return state;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            this.P = (SavedState) state;
            super.onRestoreInstanceState(this.P.a());
            if (this.m != null && this.P.b != null) {
                this.m.a(this.P.b);
                return;
            }
            return;
        }
        super.onRestoreInstanceState(state);
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    private void c(s viewHolder) {
        View view = viewHolder.a;
        boolean alreadyParented = view.getParent() == this;
        this.d.b(b(view));
        if (viewHolder.n()) {
            this.f.a(view, -1, view.getLayoutParams(), true);
        } else if (alreadyParented) {
            this.f.e(view);
        } else {
            this.f.a(view);
        }
    }

    final boolean a(View view) {
        g();
        boolean removed = this.f.g(view);
        if (removed) {
            s viewHolder = d(view);
            this.d.b(viewHolder);
            this.d.a(viewHolder);
        }
        a(!removed);
        return removed;
    }

    public g e() {
        return this.m;
    }

    public void setRecycledViewPool(l pool) {
        m mVar = this.d;
        if (mVar.e != null) {
            mVar.e.b();
        }
        mVar.e = pool;
        if (pool != null) {
            mVar.e.a();
        }
    }

    public void setViewCacheExtension(q extension) {
        this.d.a(extension);
    }

    public void setItemViewCacheSize(int size) {
        this.d.a(size);
    }

    final void a(int state) {
        if (state != this.ah) {
            this.ah = state;
            if (state != 2) {
                u();
            }
            if (this.m != null) {
                this.m.j(state);
            }
            e(state);
            if (this.av != null) {
                this.av.a(this, state);
            }
            if (this.aw != null) {
                for (int size = this.aw.size() - 1; size >= 0; size--) {
                    ((k) this.aw.get(size)).a(this, state);
                }
            }
        }
    }

    public void setChildDrawingOrderCallback(d childDrawingOrderCallback) {
        if (childDrawingOrderCallback != this.ay) {
            this.ay = childDrawingOrderCallback;
            setChildrenDrawingOrderEnabled(this.ay != null);
        }
    }

    @Deprecated
    public void setOnScrollListener(k listener) {
        this.av = listener;
    }

    public final void a(k listener) {
        if (this.aw == null) {
            this.aw = new ArrayList();
        }
        this.aw.add(listener);
    }

    public final void b(k listener) {
        if (this.aw != null) {
            this.aw.remove(listener);
        }
    }

    public final void b(int position) {
        if (!this.u) {
            t();
            if (this.m != null) {
                this.m.e(position);
                awakenScrollBars();
            }
        }
    }

    final void c(int position) {
        if (this.m != null) {
            this.m.e(position);
            awakenScrollBars();
        }
    }

    public final void d(int position) {
        if (!this.u && this.m != null) {
            this.m.a(this, position);
        }
    }

    public void scrollTo(int x, int y) {
    }

    public void scrollBy(int x, int y) {
        if (this.m != null && !this.u) {
            boolean canScrollHorizontal = this.m.e();
            boolean canScrollVertical = this.m.f();
            if (canScrollHorizontal || canScrollVertical) {
                if (!canScrollHorizontal) {
                    x = 0;
                }
                if (!canScrollVertical) {
                    y = 0;
                }
                a(x, y, null);
            }
        }
    }

    final void f() {
        boolean z = false;
        if (!this.s || this.w) {
            android.support.v4.os.d.a("RV FullInvalidate");
            F();
            android.support.v4.os.d.a();
        } else if (!this.e.d()) {
        } else {
            if (this.e.a(4) && !this.e.a(11)) {
                android.support.v4.os.d.a("RV PartialInvalidate");
                g();
                i();
                this.e.b();
                if (!this.t) {
                    int a = this.f.a();
                    for (int i = 0; i < a; i++) {
                        s d = d(this.f.b(i));
                        if (d != null && !d.b() && d.s()) {
                            z = true;
                            break;
                        }
                    }
                    if (z) {
                        F();
                    } else {
                        this.e.c();
                    }
                }
                a(true);
                b(true);
                android.support.v4.os.d.a();
            } else if (this.e.d()) {
                android.support.v4.os.d.a("RV FullInvalidate");
                F();
                android.support.v4.os.d.a();
            }
        }
    }

    private boolean a(int x, int y, MotionEvent ev) {
        int unconsumedX = 0;
        int unconsumedY = 0;
        int consumedX = 0;
        int consumedY = 0;
        f();
        if (this.l != null) {
            g();
            i();
            android.support.v4.os.d.a("RV Scroll");
            a(this.B);
            if (x != 0) {
                consumedX = this.m.a(x, this.d, this.B);
                unconsumedX = x - consumedX;
            }
            if (y != 0) {
                consumedY = this.m.b(y, this.d, this.B);
                unconsumedY = y - consumedY;
            }
            android.support.v4.os.d.a();
            p();
            b(true);
            a(false);
        }
        if (!this.o.isEmpty()) {
            invalidate();
        }
        if (a(consumedX, consumedY, unconsumedX, unconsumedY, this.aB, 0)) {
            this.am -= this.aB[0];
            this.an -= this.aB[1];
            if (ev != null) {
                ev.offsetLocation((float) this.aB[0], (float) this.aB[1]);
            }
            int[] iArr = this.aD;
            iArr[0] = iArr[0] + this.aB[0];
            iArr = this.aD;
            iArr[1] = iArr[1] + this.aB[1];
        } else if (getOverScrollMode() != 2) {
            if (ev != null) {
                Object obj;
                if ((ev.getSource() & 8194) == 8194) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj == null) {
                    float x2 = ev.getX();
                    float f = (float) unconsumedX;
                    float y2 = ev.getY();
                    float f2 = (float) unconsumedY;
                    obj = null;
                    if (f < 0.0f) {
                        v();
                        android.support.v4.widget.f.a(this.ad, (-f) / ((float) getWidth()), 1.0f - (y2 / ((float) getHeight())));
                        obj = 1;
                    } else if (f > 0.0f) {
                        w();
                        android.support.v4.widget.f.a(this.af, f / ((float) getWidth()), y2 / ((float) getHeight()));
                        obj = 1;
                    }
                    if (f2 < 0.0f) {
                        x();
                        android.support.v4.widget.f.a(this.ae, (-f2) / ((float) getHeight()), x2 / ((float) getWidth()));
                        obj = 1;
                    } else if (f2 > 0.0f) {
                        y();
                        android.support.v4.widget.f.a(this.ag, f2 / ((float) getHeight()), 1.0f - (x2 / ((float) getWidth())));
                        obj = 1;
                    }
                    if (!(obj == null && f == 0.0f && f2 == 0.0f)) {
                        ViewCompat.d(this);
                    }
                }
            }
            b(x, y);
        }
        if (!(consumedX == 0 && consumedY == 0)) {
            e(consumedX, consumedY);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        if (consumedX == 0 && consumedY == 0) {
            return false;
        }
        return true;
    }

    public int computeHorizontalScrollOffset() {
        if (this.m != null && this.m.e()) {
            return this.m.b(this.B);
        }
        return 0;
    }

    public int computeHorizontalScrollExtent() {
        if (this.m != null && this.m.e()) {
            return this.m.d(this.B);
        }
        return 0;
    }

    public int computeHorizontalScrollRange() {
        if (this.m != null && this.m.e()) {
            return this.m.f(this.B);
        }
        return 0;
    }

    public int computeVerticalScrollOffset() {
        if (this.m != null && this.m.f()) {
            return this.m.c(this.B);
        }
        return 0;
    }

    public int computeVerticalScrollExtent() {
        if (this.m != null && this.m.f()) {
            return this.m.e(this.B);
        }
        return 0;
    }

    public int computeVerticalScrollRange() {
        if (this.m != null && this.m.f()) {
            return this.m.g(this.B);
        }
        return 0;
    }

    final void g() {
        this.T++;
        if (this.T == 1 && !this.u) {
            this.t = false;
        }
    }

    final void a(boolean performLayoutChildren) {
        if (this.T <= 0) {
            this.T = 1;
        }
        if (!performLayoutChildren) {
            this.t = false;
        }
        if (this.T == 1) {
            if (!(!performLayoutChildren || !this.t || this.u || this.m == null || this.l == null)) {
                F();
            }
            if (!this.u) {
                this.t = false;
            }
        }
        this.T--;
    }

    public void setLayoutFrozen(boolean frozen) {
        if (frozen != this.u) {
            a("Do not setLayoutFrozen in layout or scroll");
            if (frozen) {
                long uptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
                this.u = true;
                this.U = true;
                t();
                return;
            }
            this.u = false;
            if (!(!this.t || this.m == null || this.l == null)) {
                requestLayout();
            }
            this.t = false;
        }
    }

    public final void a(int dx, int dy) {
        int i = 0;
        if (this.m != null && !this.u) {
            if (!this.m.e()) {
                dx = 0;
            }
            if (this.m.f()) {
                i = dy;
            }
            if (dx != 0 || i != 0) {
                this.y.c(dx, i);
            }
        }
    }

    private void t() {
        a(0);
        u();
    }

    private void u() {
        this.y.b();
        if (this.m != null) {
            this.m.C();
        }
    }

    public final int h() {
        return this.aq;
    }

    final void b(int dx, int dy) {
        boolean needsInvalidate = false;
        if (!(this.ad == null || this.ad.isFinished() || dx <= 0)) {
            this.ad.onRelease();
            needsInvalidate = this.ad.isFinished();
        }
        if (!(this.af == null || this.af.isFinished() || dx >= 0)) {
            this.af.onRelease();
            needsInvalidate |= this.af.isFinished();
        }
        if (!(this.ae == null || this.ae.isFinished() || dy <= 0)) {
            this.ae.onRelease();
            needsInvalidate |= this.ae.isFinished();
        }
        if (!(this.ag == null || this.ag.isFinished() || dy >= 0)) {
            this.ag.onRelease();
            needsInvalidate |= this.ag.isFinished();
        }
        if (needsInvalidate) {
            ViewCompat.d(this);
        }
    }

    final void c(int velocityX, int velocityY) {
        if (velocityX < 0) {
            v();
            this.ad.onAbsorb(-velocityX);
        } else if (velocityX > 0) {
            w();
            this.af.onAbsorb(velocityX);
        }
        if (velocityY < 0) {
            x();
            this.ae.onAbsorb(-velocityY);
        } else if (velocityY > 0) {
            y();
            this.ag.onAbsorb(velocityY);
        }
        if (velocityX != 0 || velocityY != 0) {
            ViewCompat.d(this);
        }
    }

    private void v() {
        if (this.ad == null) {
            this.ad = new EdgeEffect(getContext());
            if (this.h) {
                this.ad.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.ad.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    private void w() {
        if (this.af == null) {
            this.af = new EdgeEffect(getContext());
            if (this.h) {
                this.af.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.af.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    private void x() {
        if (this.ae == null) {
            this.ae = new EdgeEffect(getContext());
            if (this.h) {
                this.ae.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.ae.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    private void y() {
        if (this.ag == null) {
            this.ag = new EdgeEffect(getContext());
            if (this.h) {
                this.ag.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.ag.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    private void z() {
        this.ag = null;
        this.ae = null;
        this.af = null;
        this.ad = null;
    }

    public View focusSearch(View focused, int direction) {
        View result;
        boolean canRunFocusFailure = (this.l == null || this.m == null || C() || this.u) ? false : true;
        FocusFinder ff = FocusFinder.getInstance();
        if (canRunFocusFailure && (direction == 2 || direction == 1)) {
            int absDir;
            boolean needsFocusFailureLayout = false;
            if (this.m.f()) {
                absDir = direction == 2 ? 130 : 33;
                needsFocusFailureLayout = ff.findNextFocus(this, focused, absDir) == null;
                if (L) {
                    direction = absDir;
                }
            }
            if (!needsFocusFailureLayout && this.m.e()) {
                absDir = ((direction == 2 ? 1 : 0) ^ (ViewCompat.f(this.m.q) == 1)) != 0 ? 66 : 17;
                needsFocusFailureLayout = ff.findNextFocus(this, focused, absDir) == null;
                if (L) {
                    direction = absDir;
                }
            }
            if (needsFocusFailureLayout) {
                f();
                if (c(focused) == null) {
                    return null;
                }
                g();
                this.m.a(focused, direction, this.d, this.B);
                a(false);
            }
            result = ff.findNextFocus(this, focused, direction);
        } else {
            result = ff.findNextFocus(this, focused, direction);
            if (result == null && canRunFocusFailure) {
                f();
                if (c(focused) == null) {
                    return null;
                }
                g();
                result = this.m.a(focused, direction, this.d, this.B);
                a(false);
            }
        }
        if (result == null || result.hasFocusable()) {
            boolean z;
            if (result == null || result == this) {
                z = false;
            } else if (focused == null) {
                z = true;
            } else if (direction == 2 || direction == 1) {
                if (a(focused, result, ((direction == 2 ? 1 : 0) ^ (ViewCompat.f(this.m.q) == 1 ? 1 : 0)) != 0 ? 66 : 17)) {
                    z = true;
                } else if (direction == 2) {
                    z = a(focused, result, 130);
                } else {
                    z = a(focused, result, 33);
                }
            } else {
                z = a(focused, result, direction);
            }
            if (z) {
                return result;
            }
            return super.focusSearch(focused, direction);
        } else if (getFocusedChild() == null) {
            return super.focusSearch(focused, direction);
        } else {
            a(result, null);
            return focused;
        }
    }

    private boolean a(View focused, View next, int direction) {
        this.j.set(0, 0, focused.getWidth(), focused.getHeight());
        this.Q.set(0, 0, next.getWidth(), next.getHeight());
        offsetDescendantRectToMyCoords(focused, this.j);
        offsetDescendantRectToMyCoords(next, this.Q);
        switch (direction) {
            case 17:
                if ((this.j.right > this.Q.right || this.j.left >= this.Q.right) && this.j.left > this.Q.left) {
                    return true;
                }
                return false;
            case 33:
                if ((this.j.bottom > this.Q.bottom || this.j.top >= this.Q.bottom) && this.j.top > this.Q.top) {
                    return true;
                }
                return false;
            case 66:
                if ((this.j.left < this.Q.left || this.j.right <= this.Q.left) && this.j.right < this.Q.right) {
                    return true;
                }
                return false;
            case 130:
                if ((this.j.top < this.Q.top || this.j.bottom <= this.Q.top) && this.j.bottom < this.Q.bottom) {
                    return true;
                }
                return false;
            default:
                throw new IllegalArgumentException("direction must be absolute. received:" + direction + a());
        }
    }

    public void requestChildFocus(View child, View focused) {
        Object obj;
        if (this.m.r() || C()) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj == null && focused != null) {
            a(child, focused);
        }
        super.requestChildFocus(child, focused);
    }

    private void a(@NonNull View child, @Nullable View focused) {
        View rectView;
        boolean z;
        boolean z2 = true;
        if (focused != null) {
            rectView = focused;
        } else {
            rectView = child;
        }
        this.j.set(0, 0, rectView.getWidth(), rectView.getHeight());
        LayoutParams focusedLayoutParams = rectView.getLayoutParams();
        if (focusedLayoutParams instanceof h) {
            h lp = (h) focusedLayoutParams;
            if (!lp.e) {
                Rect insets = lp.d;
                Rect rect = this.j;
                rect.left -= insets.left;
                rect = this.j;
                rect.right += insets.right;
                rect = this.j;
                rect.top -= insets.top;
                rect = this.j;
                rect.bottom += insets.bottom;
            }
        }
        if (focused != null) {
            offsetDescendantRectToMyCoords(focused, this.j);
            offsetRectIntoDescendantCoords(child, this.j);
        }
        g gVar = this.m;
        Rect rect2 = this.j;
        if (this.s) {
            z = false;
        } else {
            z = true;
        }
        if (focused != null) {
            z2 = false;
        }
        gVar.a(this, child, rect2, z, z2);
    }

    public boolean requestChildRectangleOnScreen(View child, Rect rect, boolean immediate) {
        return this.m.a(this, child, rect, immediate, false);
    }

    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        super.addFocusables(views, direction, focusableMode);
    }

    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        if (C()) {
            return false;
        }
        return super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.ab = 0;
        this.p = true;
        boolean z = this.s && !isLayoutRequested();
        this.s = z;
        if (this.m != null) {
            this.m.v = true;
        }
        this.E = false;
        if (K) {
            this.z = (x) x.a.get();
            if (this.z == null) {
                this.z = new x();
                Display display = ViewCompat.F(this);
                float refreshRate = 60.0f;
                if (!(isInEditMode() || display == null)) {
                    float displayRefreshRate = display.getRefreshRate();
                    if (displayRefreshRate >= 30.0f) {
                        refreshRate = displayRefreshRate;
                    }
                }
                this.z.d = (long) (1.0E9f / refreshRate);
                x.a.set(this.z);
            }
            this.z.b.add(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.x != null) {
            this.x.d();
        }
        t();
        this.p = false;
        if (this.m != null) {
            this.m.b(this, this.d);
        }
        this.G.clear();
        removeCallbacks(this.aE);
        a.b();
        if (K) {
            this.z.b.remove(this);
            this.z = null;
        }
    }

    public boolean isAttachedToWindow() {
        return this.p;
    }

    final void a(String message) {
        if (C()) {
            if (message == null) {
                throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + a());
            }
            throw new IllegalStateException(message);
        } else if (this.ac > 0) {
            IllegalStateException illegalStateException = new IllegalStateException(a());
        }
    }

    public final void a(j listener) {
        this.R.add(listener);
    }

    public final void b(j listener) {
        this.R.remove(listener);
        if (this.S == listener) {
            this.S = null;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (this.u) {
            return false;
        }
        Object obj;
        int action = e.getAction();
        if (action == 3 || action == 0) {
            this.S = null;
        }
        int size = this.R.size();
        for (int i = 0; i < size; i++) {
            j jVar = (j) this.R.get(i);
            if (jVar.a(e) && action != 3) {
                this.S = jVar;
                obj = 1;
                break;
            }
        }
        obj = null;
        if (obj != null) {
            B();
            return true;
        } else if (this.m == null) {
            return false;
        } else {
            boolean canScrollHorizontally = this.m.e();
            boolean canScrollVertically = this.m.f();
            if (this.aj == null) {
                this.aj = VelocityTracker.obtain();
            }
            this.aj.addMovement(e);
            int action2 = e.getActionMasked();
            int actionIndex = e.getActionIndex();
            int x;
            switch (action2) {
                case 0:
                    if (this.U) {
                        this.U = false;
                    }
                    this.ai = e.getPointerId(0);
                    x = (int) (e.getX() + 0.5f);
                    this.am = x;
                    this.ak = x;
                    x = (int) (e.getY() + 0.5f);
                    this.an = x;
                    this.al = x;
                    if (this.ah == 2) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        a(1);
                    }
                    int[] iArr = this.aD;
                    this.aD[1] = 0;
                    iArr[0] = 0;
                    int nestedScrollAxis = 0;
                    if (canScrollHorizontally) {
                        nestedScrollAxis = 1;
                    }
                    if (canScrollVertically) {
                        nestedScrollAxis |= 2;
                    }
                    f(nestedScrollAxis, 0);
                    break;
                case 1:
                    this.aj.clear();
                    f(0);
                    break;
                case 2:
                    int index = e.findPointerIndex(this.ai);
                    if (index >= 0) {
                        int x2 = (int) (e.getX(index) + 0.5f);
                        int y = (int) (e.getY(index) + 0.5f);
                        if (this.ah != 1) {
                            int dx = x2 - this.ak;
                            int dy = y - this.al;
                            boolean startScroll = false;
                            if (canScrollHorizontally && Math.abs(dx) > this.ao) {
                                this.am = x2;
                                startScroll = true;
                            }
                            if (canScrollVertically && Math.abs(dy) > this.ao) {
                                this.an = y;
                                startScroll = true;
                            }
                            if (startScroll) {
                                a(1);
                                break;
                            }
                        }
                    }
                    new StringBuilder("Error processing scroll; pointer index for id ").append(this.ai).append(" not found. Did any MotionEvents get skipped?");
                    return false;
                    break;
                case 3:
                    B();
                    break;
                case 5:
                    this.ai = e.getPointerId(actionIndex);
                    x = (int) (e.getX(actionIndex) + 0.5f);
                    this.am = x;
                    this.ak = x;
                    x = (int) (e.getY(actionIndex) + 0.5f);
                    this.an = x;
                    this.al = x;
                    break;
                case 6:
                    a(e);
                    break;
            }
            if (this.ah == 1) {
                return true;
            }
            return false;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        int listenerCount = this.R.size();
        for (int i = 0; i < listenerCount; i++) {
            this.R.get(i);
        }
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(MotionEvent e) {
        if (this.u || this.U) {
            return false;
        }
        Object obj;
        boolean canScrollHorizontally;
        boolean canScrollVertically;
        boolean eventAddedToVelocityTracker;
        MotionEvent vtev;
        int action;
        int actionIndex;
        int[] iArr;
        int nestedScrollAxis;
        float xvel;
        float yvel;
        int i;
        boolean e2;
        boolean f;
        int i2;
        boolean z;
        int index;
        int x;
        int y;
        int dx;
        int dy;
        boolean startScroll;
        int action2 = e.getAction();
        if (this.S != null) {
            if (action2 == 0) {
                this.S = null;
            } else {
                this.S.b(e);
                if (action2 == 3 || action2 == 1) {
                    this.S = null;
                }
                obj = 1;
                if (obj != null) {
                    B();
                    return true;
                } else if (this.m == null) {
                    return false;
                } else {
                    canScrollHorizontally = this.m.e();
                    canScrollVertically = this.m.f();
                    if (this.aj == null) {
                        this.aj = VelocityTracker.obtain();
                    }
                    eventAddedToVelocityTracker = false;
                    vtev = MotionEvent.obtain(e);
                    action = e.getActionMasked();
                    actionIndex = e.getActionIndex();
                    if (action == 0) {
                        iArr = this.aD;
                        this.aD[1] = 0;
                        iArr[0] = 0;
                    }
                    vtev.offsetLocation((float) this.aD[0], (float) this.aD[1]);
                    switch (action) {
                        case 0:
                            this.ai = e.getPointerId(0);
                            action2 = (int) (e.getX() + 0.5f);
                            this.am = action2;
                            this.ak = action2;
                            action2 = (int) (e.getY() + 0.5f);
                            this.an = action2;
                            this.al = action2;
                            nestedScrollAxis = 0;
                            if (canScrollHorizontally) {
                                nestedScrollAxis = 1;
                            }
                            if (canScrollVertically) {
                                nestedScrollAxis |= 2;
                            }
                            f(nestedScrollAxis, 0);
                            break;
                        case 1:
                            this.aj.addMovement(vtev);
                            eventAddedToVelocityTracker = true;
                            this.aj.computeCurrentVelocity(Constants.ONE_SECOND, (float) this.ar);
                            xvel = canScrollHorizontally ? -this.aj.getXVelocity(this.ai) : 0.0f;
                            yvel = canScrollVertically ? -this.aj.getYVelocity(this.ai) : 0.0f;
                            if (!(xvel == 0.0f && yvel == 0.0f)) {
                                i = (int) xvel;
                                action2 = (int) yvel;
                                if (!(this.m == null || this.u)) {
                                    e2 = this.m.e();
                                    f = this.m.f();
                                    if (e2 || Math.abs(i) < this.aq) {
                                        i2 = 0;
                                    } else {
                                        i2 = i;
                                    }
                                    if (f || Math.abs(action2) < this.aq) {
                                        i = 0;
                                    } else {
                                        i = action2;
                                    }
                                    if (!(i2 == 0 && i == 0)) {
                                        if (!dispatchNestedPreFling((float) i2, (float) i)) {
                                            z = e2 || f;
                                            dispatchNestedFling((float) i2, (float) i, z);
                                            if (this.ap != null || !this.ap.a(i2, i)) {
                                                if (z) {
                                                    action2 = 0;
                                                    if (e2) {
                                                        action2 = 1;
                                                    }
                                                    if (f) {
                                                        action2 |= 2;
                                                    }
                                                    f(action2, 1);
                                                    this.y.a(Math.max(-this.ar, Math.min(i2, this.ar)), Math.max(-this.ar, Math.min(i, this.ar)));
                                                    obj = 1;
                                                    break;
                                                }
                                            }
                                            obj = 1;
                                            break;
                                        }
                                    }
                                }
                                obj = null;
                                break;
                            }
                            a(0);
                            A();
                            break;
                        case 2:
                            index = e.findPointerIndex(this.ai);
                            if (index < 0) {
                                x = (int) (e.getX(index) + 0.5f);
                                y = (int) (e.getY(index) + 0.5f);
                                dx = this.am - x;
                                dy = this.an - y;
                                if (a(dx, dy, this.aC, this.aB, 0)) {
                                    dx -= this.aC[0];
                                    dy -= this.aC[1];
                                    vtev.offsetLocation((float) this.aB[0], (float) this.aB[1]);
                                    iArr = this.aD;
                                    iArr[0] = iArr[0] + this.aB[0];
                                    iArr = this.aD;
                                    iArr[1] = iArr[1] + this.aB[1];
                                }
                                if (this.ah != 1) {
                                    startScroll = false;
                                    if (canScrollHorizontally && Math.abs(dx) > this.ao) {
                                        if (dx <= 0) {
                                            dx -= this.ao;
                                        } else {
                                            dx += this.ao;
                                        }
                                        startScroll = true;
                                    }
                                    if (canScrollVertically && Math.abs(dy) > this.ao) {
                                        if (dy <= 0) {
                                            dy -= this.ao;
                                        } else {
                                            dy += this.ao;
                                        }
                                        startScroll = true;
                                    }
                                    if (startScroll) {
                                        a(1);
                                    }
                                }
                                if (this.ah == 1) {
                                    this.am = x - this.aB[0];
                                    this.an = y - this.aB[1];
                                    if (canScrollHorizontally) {
                                        i = 0;
                                    } else {
                                        i = dx;
                                    }
                                    if (canScrollVertically) {
                                        action2 = 0;
                                    } else {
                                        action2 = dy;
                                    }
                                    if (a(i, action2, vtev)) {
                                        getParent().requestDisallowInterceptTouchEvent(true);
                                    }
                                    if (!(this.z == null || (dx == 0 && dy == 0))) {
                                        this.z.a(this, dx, dy);
                                        break;
                                    }
                                }
                            }
                            new StringBuilder("Error processing scroll; pointer index for id ").append(this.ai).append(" not found. Did any MotionEvents get skipped?");
                            return false;
                            break;
                        case 3:
                            B();
                            break;
                        case 5:
                            this.ai = e.getPointerId(actionIndex);
                            action2 = (int) (e.getX(actionIndex) + 0.5f);
                            this.am = action2;
                            this.ak = action2;
                            action2 = (int) (e.getY(actionIndex) + 0.5f);
                            this.an = action2;
                            this.al = action2;
                            break;
                        case 6:
                            a(e);
                            break;
                    }
                    if (!eventAddedToVelocityTracker) {
                        this.aj.addMovement(vtev);
                    }
                    vtev.recycle();
                    return true;
                }
            }
        }
        if (action2 != 0) {
            i2 = this.R.size();
            for (i = 0; i < i2; i++) {
                j jVar = (j) this.R.get(i);
                if (jVar.a(e)) {
                    this.S = jVar;
                    obj = 1;
                    break;
                }
            }
        }
        obj = null;
        if (obj != null) {
            B();
            return true;
        } else if (this.m == null) {
            return false;
        } else {
            canScrollHorizontally = this.m.e();
            canScrollVertically = this.m.f();
            if (this.aj == null) {
                this.aj = VelocityTracker.obtain();
            }
            eventAddedToVelocityTracker = false;
            vtev = MotionEvent.obtain(e);
            action = e.getActionMasked();
            actionIndex = e.getActionIndex();
            if (action == 0) {
                iArr = this.aD;
                this.aD[1] = 0;
                iArr[0] = 0;
            }
            vtev.offsetLocation((float) this.aD[0], (float) this.aD[1]);
            switch (action) {
                case 0:
                    this.ai = e.getPointerId(0);
                    action2 = (int) (e.getX() + 0.5f);
                    this.am = action2;
                    this.ak = action2;
                    action2 = (int) (e.getY() + 0.5f);
                    this.an = action2;
                    this.al = action2;
                    nestedScrollAxis = 0;
                    if (canScrollHorizontally) {
                        nestedScrollAxis = 1;
                    }
                    if (canScrollVertically) {
                        nestedScrollAxis |= 2;
                    }
                    f(nestedScrollAxis, 0);
                    break;
                case 1:
                    this.aj.addMovement(vtev);
                    eventAddedToVelocityTracker = true;
                    this.aj.computeCurrentVelocity(Constants.ONE_SECOND, (float) this.ar);
                    if (canScrollHorizontally) {
                    }
                    if (canScrollVertically) {
                    }
                    i = (int) xvel;
                    action2 = (int) yvel;
                    e2 = this.m.e();
                    f = this.m.f();
                    if (e2) {
                        break;
                    }
                    i2 = 0;
                    if (f) {
                        break;
                    }
                    i = 0;
                    if (dispatchNestedPreFling((float) i2, (float) i)) {
                        if (!e2) {
                        }
                        dispatchNestedFling((float) i2, (float) i, z);
                        if (this.ap != null) {
                            break;
                        }
                        if (z) {
                            action2 = 0;
                            if (e2) {
                                action2 = 1;
                            }
                            if (f) {
                                action2 |= 2;
                            }
                            f(action2, 1);
                            this.y.a(Math.max(-this.ar, Math.min(i2, this.ar)), Math.max(-this.ar, Math.min(i, this.ar)));
                            obj = 1;
                            break;
                        }
                    }
                    obj = null;
                    break;
                case 2:
                    index = e.findPointerIndex(this.ai);
                    if (index < 0) {
                        x = (int) (e.getX(index) + 0.5f);
                        y = (int) (e.getY(index) + 0.5f);
                        dx = this.am - x;
                        dy = this.an - y;
                        if (a(dx, dy, this.aC, this.aB, 0)) {
                            dx -= this.aC[0];
                            dy -= this.aC[1];
                            vtev.offsetLocation((float) this.aB[0], (float) this.aB[1]);
                            iArr = this.aD;
                            iArr[0] = iArr[0] + this.aB[0];
                            iArr = this.aD;
                            iArr[1] = iArr[1] + this.aB[1];
                        }
                        if (this.ah != 1) {
                            startScroll = false;
                            if (dx <= 0) {
                                dx += this.ao;
                            } else {
                                dx -= this.ao;
                            }
                            startScroll = true;
                            if (dy <= 0) {
                                dy += this.ao;
                            } else {
                                dy -= this.ao;
                            }
                            startScroll = true;
                            if (startScroll) {
                                a(1);
                            }
                            break;
                        }
                        if (this.ah == 1) {
                            this.am = x - this.aB[0];
                            this.an = y - this.aB[1];
                            if (canScrollHorizontally) {
                                i = 0;
                            } else {
                                i = dx;
                            }
                            if (canScrollVertically) {
                                action2 = 0;
                            } else {
                                action2 = dy;
                            }
                            if (a(i, action2, vtev)) {
                                getParent().requestDisallowInterceptTouchEvent(true);
                            }
                            this.z.a(this, dx, dy);
                            break;
                        }
                    }
                    new StringBuilder("Error processing scroll; pointer index for id ").append(this.ai).append(" not found. Did any MotionEvents get skipped?");
                    return false;
                    break;
                case 3:
                    B();
                    break;
                case 5:
                    this.ai = e.getPointerId(actionIndex);
                    action2 = (int) (e.getX(actionIndex) + 0.5f);
                    this.am = action2;
                    this.ak = action2;
                    action2 = (int) (e.getY(actionIndex) + 0.5f);
                    this.an = action2;
                    this.al = action2;
                    break;
                case 6:
                    a(e);
                    break;
            }
            if (eventAddedToVelocityTracker) {
                this.aj.addMovement(vtev);
            }
            vtev.recycle();
            return true;
        }
    }

    private void A() {
        int i = 0;
        if (this.aj != null) {
            this.aj.clear();
        }
        f(0);
        if (this.ad != null) {
            this.ad.onRelease();
            i = this.ad.isFinished();
        }
        if (this.ae != null) {
            this.ae.onRelease();
            i |= this.ae.isFinished();
        }
        if (this.af != null) {
            this.af.onRelease();
            i |= this.af.isFinished();
        }
        if (this.ag != null) {
            this.ag.onRelease();
            i |= this.ag.isFinished();
        }
        if (i != 0) {
            ViewCompat.d(this);
        }
    }

    private void B() {
        A();
        a(0);
    }

    private void a(MotionEvent e) {
        int actionIndex = e.getActionIndex();
        if (e.getPointerId(actionIndex) == this.ai) {
            int newIndex = actionIndex == 0 ? 1 : 0;
            this.ai = e.getPointerId(newIndex);
            int x = (int) (e.getX(newIndex) + 0.5f);
            this.am = x;
            this.ak = x;
            x = (int) (e.getY(newIndex) + 0.5f);
            this.an = x;
            this.al = x;
        }
    }

    public boolean onGenericMotionEvent(MotionEvent event) {
        if (!(this.m == null || this.u || event.getAction() != 8)) {
            float vScroll;
            float hScroll;
            if ((event.getSource() & 2) != 0) {
                if (this.m.f()) {
                    vScroll = -event.getAxisValue(9);
                } else {
                    vScroll = 0.0f;
                }
                if (this.m.e()) {
                    hScroll = event.getAxisValue(10);
                } else {
                    hScroll = 0.0f;
                }
            } else if ((event.getSource() & 4194304) != 0) {
                float axisScroll = event.getAxisValue(26);
                if (this.m.f()) {
                    vScroll = -axisScroll;
                    hScroll = 0.0f;
                } else if (this.m.e()) {
                    vScroll = 0.0f;
                    hScroll = axisScroll;
                } else {
                    vScroll = 0.0f;
                    hScroll = 0.0f;
                }
            } else {
                vScroll = 0.0f;
                hScroll = 0.0f;
            }
            if (!(vScroll == 0.0f && hScroll == 0.0f)) {
                a((int) (this.as * hScroll), (int) (this.at * vScroll), event);
            }
        }
        return false;
    }

    protected void onMeasure(int widthSpec, int heightSpec) {
        boolean skipMeasure = false;
        if (this.m == null) {
            d(widthSpec, heightSpec);
        } else if (this.m.w) {
            int widthMode = MeasureSpec.getMode(widthSpec);
            int heightMode = MeasureSpec.getMode(heightSpec);
            if (widthMode == ErrorDialogData.SUPPRESSED && heightMode == ErrorDialogData.SUPPRESSED) {
                skipMeasure = true;
            }
            this.m.a(this.d, this.B, widthSpec, heightSpec);
            if (!skipMeasure && this.l != null) {
                if (this.B.c == 1) {
                    I();
                }
                this.m.f(widthSpec, heightSpec);
                this.B.h = true;
                J();
                this.m.g(widthSpec, heightSpec);
                if (this.m.j()) {
                    this.m.f(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), ErrorDialogData.SUPPRESSED), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), ErrorDialogData.SUPPRESSED));
                    this.B.h = true;
                    J();
                    this.m.g(widthSpec, heightSpec);
                }
            }
        } else if (this.q) {
            this.m.a(this.d, this.B, widthSpec, heightSpec);
        } else {
            if (this.v) {
                g();
                i();
                E();
                b(true);
                if (this.B.j) {
                    this.B.f = true;
                } else {
                    this.e.e();
                    this.B.f = false;
                }
                this.v = false;
                a(false);
            } else if (this.B.j) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            if (this.l != null) {
                this.B.d = this.l.a();
            } else {
                this.B.d = 0;
            }
            g();
            this.m.a(this.d, this.B, widthSpec, heightSpec);
            a(false);
            this.B.f = false;
        }
    }

    final void d(int widthSpec, int heightSpec) {
        setMeasuredDimension(g.a(widthSpec, getPaddingLeft() + getPaddingRight(), ViewCompat.n(this)), g.a(heightSpec, getPaddingTop() + getPaddingBottom(), ViewCompat.o(this)));
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            z();
        }
    }

    public void setItemAnimator(ItemAnimator animator) {
        if (this.x != null) {
            this.x.d();
            this.x.a(null);
        }
        this.x = animator;
        if (this.x != null) {
            this.x.a(this.ax);
        }
    }

    final void i() {
        this.ab++;
    }

    final void j() {
        b(true);
    }

    final void b(boolean enableChangeEvents) {
        this.ab--;
        if (this.ab <= 0) {
            this.ab = 0;
            if (enableChangeEvents) {
                int i = this.V;
                this.V = 0;
                if (i != 0 && k()) {
                    AccessibilityEvent obtain = AccessibilityEvent.obtain();
                    obtain.setEventType(2048);
                    android.support.v4.view.accessibility.a.a(obtain, i);
                    sendAccessibilityEventUnchecked(obtain);
                }
                for (int size = this.G.size() - 1; size >= 0; size--) {
                    s sVar = (s) this.G.get(size);
                    if (sVar.a.getParent() == this && !sVar.b()) {
                        int i2 = sVar.l;
                        if (i2 != -1) {
                            ViewCompat.a(sVar.a, i2);
                            sVar.l = -1;
                        }
                    }
                }
                this.G.clear();
            }
        }
    }

    final boolean k() {
        return this.W != null && this.W.isEnabled();
    }

    private boolean C() {
        return this.ab > 0;
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent event) {
        int i = 0;
        if (C()) {
            int a;
            if (event != null) {
                a = android.support.v4.view.accessibility.a.a(event);
            } else {
                a = 0;
            }
            if (a != 0) {
                i = a;
            }
            this.V = i | this.V;
            i = 1;
        }
        if (i == 0) {
            super.sendAccessibilityEventUnchecked(event);
        }
    }

    final void l() {
        if (!this.E && this.p) {
            ViewCompat.a((View) this, this.aE);
            this.E = true;
        }
    }

    private boolean D() {
        return this.x != null && this.m.c();
    }

    private void E() {
        boolean z;
        boolean z2 = true;
        if (this.w) {
            this.e.a();
            this.m.a();
        }
        if (D()) {
            this.e.b();
        } else {
            this.e.e();
        }
        boolean animationTypeSupported;
        if (this.C || this.D) {
            animationTypeSupported = true;
        } else {
            animationTypeSupported = false;
        }
        State state = this.B;
        if (!this.s || this.x == null || (!(this.w || animationTypeSupported || this.m.u) || (this.w && !this.l.e()))) {
            z = false;
        } else {
            z = true;
        }
        state.i = z;
        State state2 = this.B;
        if (!(this.B.i && animationTypeSupported && !this.w && D())) {
            z2 = false;
        }
        state2.j = z2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void F() {
        if (this.l != null && this.m != null) {
            Object obj;
            s sVar;
            int i;
            this.B.h = false;
            if (this.B.c == 1) {
                I();
                this.m.b(this);
                J();
            } else {
                e eVar = this.e;
                if (eVar.b.isEmpty() || eVar.a.isEmpty()) {
                    obj = null;
                } else {
                    obj = 1;
                }
                if (obj == null && this.m.v() == getWidth() && this.m.w() == getHeight()) {
                    this.m.b(this);
                } else {
                    this.m.b(this);
                    J();
                }
            }
            this.B.a(4);
            g();
            i();
            this.B.c = 1;
            if (this.B.i) {
                for (int a = this.f.a() - 1; a >= 0; a--) {
                    s d = d(this.f.b(a));
                    if (!d.b()) {
                        long d2 = d(d);
                        b a2 = new b().a(d);
                        sVar = (s) this.g.b.a(d2);
                        if (!(sVar == null || sVar.b())) {
                            boolean a3 = this.g.a(sVar);
                            boolean a4 = this.g.a(d);
                            if (!(a3 && sVar == d)) {
                                b b = this.g.b(sVar);
                                this.g.b(d, a2);
                                a2 = this.g.c(d);
                                if (b == null) {
                                    int a5 = this.f.a();
                                    i = 0;
                                    while (i < a5) {
                                        s d3 = d(this.f.b(i));
                                        if (d3 == d || d(d3) != d2) {
                                            i++;
                                        } else if (this.l == null || !this.l.e()) {
                                            throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + d3 + " \n View Holder 2:" + d + a());
                                        } else {
                                            throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + d3 + " \n View Holder 2:" + d + a());
                                        }
                                    }
                                    new StringBuilder("Problem while matching changed view holders with the newones. The pre-layout information for the change holder ").append(sVar).append(" cannot be found but it is necessary for ").append(d).append(a());
                                } else {
                                    sVar.a(false);
                                    if (a3) {
                                        c(sVar);
                                    }
                                    if (sVar != d) {
                                        if (a4) {
                                            c(d);
                                        }
                                        sVar.h = d;
                                        c(sVar);
                                        this.d.b(sVar);
                                        d.a(false);
                                        d.i = sVar;
                                    }
                                    if (this.x.a(sVar, d, b, a2)) {
                                        l();
                                    }
                                }
                            }
                        }
                        this.g.b(d, a2);
                    }
                }
                this.g.a(this.aF);
            }
            this.m.b(this.d);
            this.B.a = this.B.d;
            this.w = false;
            this.B.i = false;
            this.B.j = false;
            this.m.u = false;
            if (this.d.b != null) {
                this.d.b.clear();
            }
            if (this.m.y) {
                this.m.x = 0;
                this.m.y = false;
                this.d.b();
            }
            this.m.a(this.B);
            j();
            a(false);
            this.g.a();
            int i2 = this.az[0];
            i = this.az[1];
            a(this.az);
            if (this.az[0] == i2 && this.az[1] == i) {
                obj = null;
            } else {
                obj = 1;
            }
            if (obj != null) {
                e(0, 0);
            }
            if (!(!this.au || this.l == null || !hasFocus() || getDescendantFocusability() == 393216 || (getDescendantFocusability() == 131072 && isFocused()))) {
                View focusedChild;
                if (!isFocused()) {
                    focusedChild = getFocusedChild();
                    if (M && (focusedChild.getParent() == null || !focusedChild.hasFocus())) {
                        if (this.f.a() == 0) {
                            requestFocus();
                        }
                    }
                }
                sVar = null;
                if (this.B.l != -1 && this.l.e()) {
                    sVar = a(this.B.l);
                }
                View view = null;
                if (sVar != null && !this.f.d(sVar.a) && sVar.a.hasFocusable()) {
                    view = sVar.a;
                } else if (this.f.a() > 0) {
                    view = H();
                }
                if (view != null) {
                    if (((long) this.B.m) != -1) {
                        focusedChild = view.findViewById(this.B.m);
                        if (focusedChild != null) {
                        }
                    }
                    focusedChild = view;
                    focusedChild.requestFocus();
                }
            }
            G();
        }
    }

    private void G() {
        this.B.l = -1;
        this.B.k = -1;
        this.B.m = -1;
    }

    @Nullable
    private View H() {
        s nextFocus;
        int startFocusSearchIndex = this.B.k != -1 ? this.B.k : 0;
        int itemCount = this.B.c();
        int i = startFocusSearchIndex;
        while (i < itemCount) {
            nextFocus = g(i);
            if (nextFocus == null) {
                break;
            } else if (nextFocus.a.hasFocusable()) {
                return nextFocus.a;
            } else {
                i++;
            }
        }
        for (i = Math.min(itemCount, startFocusSearchIndex) - 1; i >= 0; i--) {
            nextFocus = g(i);
            if (nextFocus == null) {
                return null;
            }
            if (nextFocus.a.hasFocusable()) {
                return nextFocus.a;
            }
        }
        return null;
    }

    final void a(State state) {
        if (this.ah == 2) {
            OverScroller scroller = this.y.e;
            state.n = scroller.getFinalX() - scroller.getCurrX();
            state.o = scroller.getFinalY() - scroller.getCurrY();
            return;
        }
        state.n = 0;
        state.o = 0;
    }

    private void I() {
        s sVar;
        State state;
        int i;
        int id;
        int i2;
        this.B.a(1);
        a(this.B);
        this.B.h = false;
        g();
        this.g.a();
        i();
        E();
        View view = null;
        if (this.au && hasFocus() && this.l != null) {
            view = getFocusedChild();
        }
        if (view == null) {
            sVar = null;
        } else {
            view = c(view);
            sVar = view == null ? null : b(view);
        }
        if (sVar == null) {
            G();
        } else {
            long j;
            State state2 = this.B;
            if (this.l.e()) {
                j = sVar.e;
            } else {
                j = -1;
            }
            state2.l = j;
            state = this.B;
            if (this.w) {
                i = -1;
            } else if (sVar.m()) {
                i = sVar.d;
            } else {
                i = sVar.d();
            }
            state.k = i;
            State state3 = this.B;
            View view2 = sVar.a;
            view = view2;
            id = view2.getId();
            while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
                View focusedChild = ((ViewGroup) view).getFocusedChild();
                if (focusedChild.getId() != -1) {
                    i = focusedChild.getId();
                } else {
                    i = id;
                }
                id = i;
                view = focusedChild;
            }
            state3.m = id;
        }
        state = this.B;
        boolean z = this.B.i && this.D;
        state.g = z;
        this.D = false;
        this.C = false;
        this.B.f = this.B.j;
        this.B.d = this.l.a();
        a(this.az);
        if (this.B.i) {
            int count = this.f.a();
            for (i2 = 0; i2 < count; i2++) {
                s holder = d(this.f.b(i2));
                if (!holder.b() && (!holder.j() || this.l.e())) {
                    ItemAnimator.d(holder);
                    holder.p();
                    this.g.a(holder, new b().a(holder));
                    if (!(!this.B.g || !holder.s() || holder.m() || holder.b() || holder.j())) {
                        this.g.a(d(holder), holder);
                    }
                }
            }
        }
        if (this.B.j) {
            id = this.f.b();
            for (i = 0; i < id; i++) {
                s d = d(this.f.c(i));
                if (!d.b() && d.d == -1) {
                    d.d = d.c;
                }
            }
            boolean didStructureChange = this.B.e;
            this.B.e = false;
            this.m.c(this.d, this.B);
            this.B.e = didStructureChange;
            for (i2 = 0; i2 < this.f.a(); i2++) {
                s viewHolder = d(this.f.b(i2));
                if (!viewHolder.b()) {
                    Object obj;
                    a aVar = (a) this.g.a.get(viewHolder);
                    if (aVar == null || (aVar.a & 4) == 0) {
                        obj = null;
                    } else {
                        obj = 1;
                    }
                    if (obj == null) {
                        ItemAnimator.d(viewHolder);
                        boolean wasHidden = viewHolder.a(8192);
                        viewHolder.p();
                        b animationInfo = new b().a(viewHolder);
                        if (wasHidden) {
                            a(viewHolder, animationInfo);
                        } else {
                            aw awVar = this.g;
                            aVar = (a) awVar.a.get(viewHolder);
                            if (aVar == null) {
                                aVar = a.a();
                                awVar.a.put(viewHolder, aVar);
                            }
                            aVar.a |= 2;
                            aVar.b = animationInfo;
                        }
                    }
                }
            }
            K();
        } else {
            K();
        }
        b(true);
        a(false);
        this.B.c = 2;
    }

    private void J() {
        boolean z;
        g();
        i();
        this.B.a(6);
        this.e.e();
        this.B.d = this.l.a();
        this.B.b = 0;
        this.B.f = false;
        this.m.c(this.d, this.B);
        this.B.e = false;
        this.P = null;
        State state = this.B;
        if (!this.B.i || this.x == null) {
            z = false;
        } else {
            z = true;
        }
        state.i = z;
        this.B.c = 4;
        b(true);
        a(false);
    }

    final void a(s viewHolder, b animationInfo) {
        viewHolder.a(0, 8192);
        if (this.B.g && viewHolder.s() && !viewHolder.m() && !viewHolder.b()) {
            this.g.a(d(viewHolder), viewHolder);
        }
        this.g.a(viewHolder, animationInfo);
    }

    private void a(int[] into) {
        int count = this.f.a();
        if (count == 0) {
            into[0] = -1;
            into[1] = -1;
            return;
        }
        int minPositionPreLayout = Integer.MAX_VALUE;
        int maxPositionPreLayout = Integer.MIN_VALUE;
        for (int i = 0; i < count; i++) {
            s holder = d(this.f.b(i));
            if (!holder.b()) {
                int pos = holder.c();
                if (pos < minPositionPreLayout) {
                    minPositionPreLayout = pos;
                }
                if (pos > maxPositionPreLayout) {
                    maxPositionPreLayout = pos;
                }
            }
        }
        into[0] = minPositionPreLayout;
        into[1] = maxPositionPreLayout;
    }

    protected void removeDetachedView(View child, boolean animate) {
        s vh = d(child);
        if (vh != null) {
            if (vh.n()) {
                vh.i();
            } else if (!vh.b()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + vh + a());
            }
        }
        child.clearAnimation();
        h(child);
        super.removeDetachedView(child, animate);
    }

    private long d(s holder) {
        if (this.l.e()) {
            return holder.e;
        }
        return (long) holder.c;
    }

    final void a(@NonNull s itemHolder, @Nullable b preLayoutInfo, @NonNull b postLayoutInfo) {
        itemHolder.a(false);
        if (this.x.b(itemHolder, preLayoutInfo, postLayoutInfo)) {
            l();
        }
    }

    final void b(@NonNull s holder, @NonNull b preLayoutInfo, @Nullable b postLayoutInfo) {
        c(holder);
        holder.a(false);
        if (this.x.a(holder, preLayoutInfo, postLayoutInfo)) {
            l();
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        android.support.v4.os.d.a("RV OnLayout");
        F();
        android.support.v4.os.d.a();
        this.s = true;
    }

    public void requestLayout() {
        if (this.T != 0 || this.u) {
            this.t = true;
        } else {
            super.requestLayout();
        }
    }

    final void m() {
        int childCount = this.f.b();
        for (int i = 0; i < childCount; i++) {
            ((h) this.f.c(i).getLayoutParams()).e = true;
        }
        m mVar = this.d;
        int size = mVar.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            h hVar = (h) ((s) mVar.c.get(i2)).a.getLayoutParams();
            if (hVar != null) {
                hVar.e = true;
            }
        }
    }

    public void draw(Canvas c) {
        int restore;
        int padding;
        int i;
        int i2 = 1;
        super.draw(c);
        int count = this.o.size();
        for (int i3 = 0; i3 < count; i3++) {
            ((f) this.o.get(i3)).a(c);
        }
        boolean needsInvalidate = false;
        if (!(this.ad == null || this.ad.isFinished())) {
            restore = c.save();
            if (this.h) {
                padding = getPaddingBottom();
            } else {
                padding = 0;
            }
            c.rotate(270.0f);
            c.translate((float) ((-getHeight()) + padding), 0.0f);
            if (this.ad == null || !this.ad.draw(c)) {
                needsInvalidate = false;
            } else {
                needsInvalidate = true;
            }
            c.restoreToCount(restore);
        }
        if (!(this.ae == null || this.ae.isFinished())) {
            restore = c.save();
            if (this.h) {
                c.translate((float) getPaddingLeft(), (float) getPaddingTop());
            }
            if (this.ae == null || !this.ae.draw(c)) {
                i = 0;
            } else {
                i = 1;
            }
            needsInvalidate |= i;
            c.restoreToCount(restore);
        }
        if (!(this.af == null || this.af.isFinished())) {
            restore = c.save();
            int width = getWidth();
            if (this.h) {
                padding = getPaddingTop();
            } else {
                padding = 0;
            }
            c.rotate(90.0f);
            c.translate((float) (-padding), (float) (-width));
            if (this.af == null || !this.af.draw(c)) {
                i = 0;
            } else {
                i = 1;
            }
            needsInvalidate |= i;
            c.restoreToCount(restore);
        }
        if (!(this.ag == null || this.ag.isFinished())) {
            restore = c.save();
            c.rotate(180.0f);
            if (this.h) {
                c.translate((float) ((-getWidth()) + getPaddingRight()), (float) ((-getHeight()) + getPaddingBottom()));
            } else {
                c.translate((float) (-getWidth()), (float) (-getHeight()));
            }
            if (this.ag == null || !this.ag.draw(c)) {
                i2 = 0;
            }
            needsInvalidate |= i2;
            c.restoreToCount(restore);
        }
        if (!needsInvalidate && this.x != null && this.o.size() > 0 && this.x.b()) {
            needsInvalidate = true;
        }
        if (needsInvalidate) {
            ViewCompat.d(this);
        }
    }

    public void onDraw(Canvas c) {
        super.onDraw(c);
        int count = this.o.size();
        for (int i = 0; i < count; i++) {
            this.o.get(i);
        }
    }

    protected boolean checkLayoutParams(LayoutParams p) {
        return (p instanceof h) && this.m.a((h) p);
    }

    protected LayoutParams generateDefaultLayoutParams() {
        if (this.m != null) {
            return this.m.b();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + a());
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        if (this.m != null) {
            return this.m.a(getContext(), attrs);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + a());
    }

    protected LayoutParams generateLayoutParams(LayoutParams p) {
        if (this.m != null) {
            return this.m.a(p);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + a());
    }

    private void K() {
        int i;
        int i2 = 0;
        int childCount = this.f.b();
        for (int i3 = 0; i3 < childCount; i3++) {
            s holder = d(this.f.c(i3));
            if (!holder.b()) {
                holder.a();
            }
        }
        m mVar = this.d;
        int size = mVar.c.size();
        for (i = 0; i < size; i++) {
            ((s) mVar.c.get(i)).a();
        }
        size = mVar.a.size();
        for (i = 0; i < size; i++) {
            ((s) mVar.a.get(i)).a();
        }
        if (mVar.b != null) {
            i = mVar.b.size();
            while (i2 < i) {
                ((s) mVar.b.get(i2)).a();
                i2++;
            }
        }
    }

    final void a(int positionStart, int itemCount, boolean applyToPreLayout) {
        int i;
        int positionEnd = positionStart + itemCount;
        int childCount = this.f.b();
        for (int i2 = 0; i2 < childCount; i2++) {
            s holder = d(this.f.c(i2));
            if (!(holder == null || holder.b())) {
                if (holder.c >= positionEnd) {
                    holder.a(-itemCount, applyToPreLayout);
                    this.B.e = true;
                } else if (holder.c >= positionStart) {
                    int i3 = positionStart - 1;
                    i = -itemCount;
                    holder.b(8);
                    holder.a(i, applyToPreLayout);
                    holder.c = i3;
                    this.B.e = true;
                }
            }
        }
        m mVar = this.d;
        int i4 = positionStart + itemCount;
        for (i = mVar.c.size() - 1; i >= 0; i--) {
            s sVar = (s) mVar.c.get(i);
            if (sVar != null) {
                if (sVar.c >= i4) {
                    sVar.a(-itemCount, applyToPreLayout);
                } else if (sVar.c >= positionStart) {
                    sVar.b(8);
                    mVar.d(i);
                }
            }
        }
        requestLayout();
    }

    final void n() {
        int i;
        this.w = true;
        int b = this.f.b();
        for (i = 0; i < b; i++) {
            s d = d(this.f.c(i));
            if (!(d == null || d.b())) {
                d.b(6);
            }
        }
        m();
        m mVar = this.d;
        if (mVar.f.l == null || !mVar.f.l.e()) {
            mVar.d();
            return;
        }
        int size = mVar.c.size();
        for (i = 0; i < size; i++) {
            s sVar = (s) mVar.c.get(i);
            if (sVar != null) {
                sVar.b(6);
                sVar.a(null);
            }
        }
    }

    public void setPreserveFocusAfterLayout(boolean preserveFocusAfterLayout) {
        this.au = preserveFocusAfterLayout;
    }

    public final s b(View child) {
        Object parent = child.getParent();
        if (parent == null || parent == this) {
            return d(child);
        }
        throw new IllegalArgumentException("View " + child + " is not a direct child of " + this);
    }

    @Nullable
    public final View c(View view) {
        View parent = view.getParent();
        while (parent != null && parent != this && (parent instanceof View)) {
            view = parent;
            parent = view.getParent();
        }
        return parent == this ? view : null;
    }

    static s d(View child) {
        if (child == null) {
            return null;
        }
        return ((h) child.getLayoutParams()).c;
    }

    public static int e(View child) {
        s holder = d(child);
        return holder != null ? holder.c() : -1;
    }

    private s g(int position) {
        if (this.w) {
            return null;
        }
        int childCount = this.f.b();
        s hidden = null;
        for (int i = 0; i < childCount; i++) {
            s holder = d(this.f.c(i));
            if (!(holder == null || holder.m() || b(holder) != position)) {
                if (!this.f.d(holder.a)) {
                    return holder;
                }
                hidden = holder;
            }
        }
        return hidden;
    }

    private s a(long id) {
        if (this.l == null || !this.l.e()) {
            return null;
        }
        int childCount = this.f.b();
        s hidden = null;
        for (int i = 0; i < childCount; i++) {
            s holder = d(this.f.c(i));
            if (!(holder == null || holder.m() || holder.e != id)) {
                if (!this.f.d(holder.a)) {
                    return holder;
                }
                hidden = holder;
            }
        }
        return hidden;
    }

    public boolean drawChild(Canvas canvas, View child, long drawingTime) {
        return super.drawChild(canvas, child, drawingTime);
    }

    static void a(View view, Rect outBounds) {
        h lp = (h) view.getLayoutParams();
        Rect insets = lp.d;
        outBounds.set((view.getLeft() - insets.left) - lp.leftMargin, (view.getTop() - insets.top) - lp.topMargin, (view.getRight() + insets.right) + lp.rightMargin, (view.getBottom() + insets.bottom) + lp.bottomMargin);
    }

    final Rect f(View child) {
        h lp = (h) child.getLayoutParams();
        if (!lp.e) {
            return lp.d;
        }
        if (this.B.f && (lp.c.s() || lp.c.j())) {
            return lp.d;
        }
        Rect insets = lp.d;
        insets.set(0, 0, 0, 0);
        int decorCount = this.o.size();
        for (int i = 0; i < decorCount; i++) {
            this.j.set(0, 0, 0, 0);
            ((f) this.o.get(i)).a(this.j, child);
            insets.left += this.j.left;
            insets.top += this.j.top;
            insets.right += this.j.right;
            insets.bottom += this.j.bottom;
        }
        lp.e = false;
        return insets;
    }

    final void e(int hresult, int vresult) {
        this.ac++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        if (this.av != null) {
            this.av.a(this, hresult, vresult);
        }
        if (this.aw != null) {
            for (int i = this.aw.size() - 1; i >= 0; i--) {
                ((k) this.aw.get(i)).a(this, hresult, vresult);
            }
        }
        this.ac--;
    }

    public void e(int state) {
    }

    public final boolean o() {
        return !this.s || this.w || this.e.d();
    }

    final void p() {
        int count = this.f.a();
        for (int i = 0; i < count; i++) {
            View view = this.f.b(i);
            s holder = b(view);
            if (!(holder == null || holder.i == null)) {
                View shadowingView = holder.i.a;
                int left = view.getLeft();
                int top = view.getTop();
                if (left != shadowingView.getLeft() || top != shadowingView.getTop()) {
                    shadowingView.layout(left, top, shadowingView.getWidth() + left, shadowingView.getHeight() + top);
                }
            }
        }
    }

    @Nullable
    static RecyclerView g(@NonNull View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup parent = (ViewGroup) view;
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View descendant = g(parent.getChildAt(i));
            if (descendant != null) {
                return descendant;
            }
        }
        return null;
    }

    static void a(@NonNull s holder) {
        if (holder.b != null) {
            View item = (View) holder.b.get();
            while (item != null) {
                if (item != holder.a) {
                    ViewParent parent = item.getParent();
                    if (parent instanceof View) {
                        item = (View) parent;
                    } else {
                        item = null;
                    }
                } else {
                    return;
                }
            }
            holder.b = null;
        }
    }

    static long q() {
        if (K) {
            return System.nanoTime();
        }
        return 0;
    }

    final void h(View child) {
        d(child);
        if (this.aa != null) {
            for (int i = this.aa.size() - 1; i >= 0; i--) {
                this.aa.get(i);
            }
        }
    }

    final void i(View child) {
        d(child);
        if (this.aa != null) {
            for (int i = this.aa.size() - 1; i >= 0; i--) {
                this.aa.get(i);
            }
        }
    }

    @VisibleForTesting
    final boolean a(s viewHolder, int importantForAccessibility) {
        if (C()) {
            viewHolder.l = importantForAccessibility;
            this.G.add(viewHolder);
            return false;
        }
        ViewCompat.a(viewHolder.a, importantForAccessibility);
        return true;
    }

    final int b(s viewHolder) {
        if (viewHolder.a(524) || !viewHolder.l()) {
            return -1;
        }
        e eVar = this.e;
        int i = viewHolder.c;
        int size = eVar.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = (b) eVar.a.get(i2);
            switch (bVar.a) {
                case 1:
                    if (bVar.b > i) {
                        break;
                    }
                    i += bVar.d;
                    break;
                case 2:
                    if (bVar.b <= i) {
                        if (bVar.b + bVar.d <= i) {
                            i -= bVar.d;
                            break;
                        }
                        return -1;
                    }
                    continue;
                case 8:
                    if (bVar.b != i) {
                        if (bVar.b < i) {
                            i--;
                        }
                        if (bVar.d > i) {
                            break;
                        }
                        i++;
                        break;
                    }
                    i = bVar.d;
                    break;
                default:
                    break;
            }
        }
        return i;
    }

    public void setNestedScrollingEnabled(boolean enabled) {
        L().a(enabled);
    }

    public boolean isNestedScrollingEnabled() {
        return L().a();
    }

    public boolean startNestedScroll(int axes) {
        return L().b(axes);
    }

    private boolean f(int axes, int type) {
        return L().a(axes, type);
    }

    public void stopNestedScroll() {
        L().c();
    }

    public final void f(int type) {
        L().c(type);
    }

    public boolean hasNestedScrollingParent() {
        return L().b();
    }

    public final boolean r() {
        return L().a(1);
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return L().a(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    public final boolean a(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow, int type) {
        return L().a(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return L().a(dx, dy, consumed, offsetInWindow);
    }

    public final boolean a(int dx, int dy, int[] consumed, int[] offsetInWindow, int type) {
        return L().a(dx, dy, consumed, offsetInWindow, type);
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return L().a(velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return L().a(velocityX, velocityY);
    }

    protected int getChildDrawingOrder(int childCount, int i) {
        if (this.ay == null) {
            return super.getChildDrawingOrder(childCount, i);
        }
        return this.ay.a();
    }

    private android.support.v4.view.i L() {
        if (this.aA == null) {
            this.aA = new android.support.v4.view.i(this);
        }
        return this.aA;
    }

    public final void a(f decor) {
        if (this.m != null) {
            this.m.a("Cannot add item decoration during a scroll  or layout");
        }
        if (this.o.isEmpty()) {
            setWillNotDraw(false);
        }
        this.o.add(decor);
        m();
        requestLayout();
    }
}
